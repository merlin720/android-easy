/*
 * Copyright (C) 2015 Square, Inc, 2017 Jeff Gilfelt.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.easy.easycan.util;

import android.text.TextUtils;


import com.easy.easycan.network.HttpRequestQueue;
import com.easy.easycan.network.NetWorkTransaction;
import com.easy.easycan.network.NetworkUtils;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSource;
import okio.GzipSource;
import okio.Okio;

/**
 * An OkHttp Interceptor which persists and displays HTTP activity in your application for later inspection.
 */
public final class HttpLogInterceptor implements Interceptor {
    private long maxContentLength = 250000L;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        NetWorkTransaction netWorkTransaction = new NetWorkTransaction();

        NetworkUtils.NetworkType networkType = NetworkUtils.getNetworkType();
        String network_ = networkType.name().replace("NETWORK_", "");
        netWorkTransaction.netWorkType = network_;
        long startTime = System.currentTimeMillis();
        netWorkTransaction.startTime = startTime + "";
        netWorkTransaction.setUrl(request.url().toString());

        RequestBody requestBody = request.body();
        if (null != requestBody) {
            BufferedSource source = getNativeSource(new Buffer(), bodyGzipped(request.headers()));
            Buffer buffer = source.buffer();
            requestBody.writeTo(buffer);
            String body = readFromBuffer(buffer, Charset.forName("UTF-8"));
            if (body.contains("request_id")) {
                int index = body.indexOf("request_id");
                netWorkTransaction.request_id = body.substring(index + 11, index + 43);
            }
        }

        long startNs = System.nanoTime();
        Response response;
        try {
            response = chain.proceed(request);
        } catch (Exception e) {
            throw e;
        }
        long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);

        netWorkTransaction.tookMs = String.valueOf(tookMs);
        long endTime = System.currentTimeMillis();
        netWorkTransaction.endTime = endTime + "";

        if (!TextUtils.isEmpty(netWorkTransaction.request_id) && tookMs > NetWorkTransaction.REQUEST_TIME) {
            HttpRequestQueue.getInstance().putNetWorkTransaction(netWorkTransaction);
        }

        return response;
    }

    private String readFromBuffer(Buffer buffer, Charset charset) {
        long bufferSize = buffer.size();
        long maxBytes = Math.min(bufferSize, maxContentLength);
        String body = "";
        try {
            body = buffer.readString(maxBytes, charset);
        } catch (EOFException e) {
            body += "\\n\\n--- Unexpected end of content ---";
            ;
        }
        if (bufferSize > maxContentLength) {
            body += "\\n\\n--- Content truncated ---";
        }
        return body;
    }

    private boolean bodyGzipped(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return "gzip".equalsIgnoreCase(contentEncoding);
    }

    private BufferedSource getNativeSource(BufferedSource input, boolean isGzipped) {
        if (isGzipped) {
            GzipSource source = new GzipSource(input);
            return Okio.buffer(source);
        } else {
            return input;
        }
    }


}
