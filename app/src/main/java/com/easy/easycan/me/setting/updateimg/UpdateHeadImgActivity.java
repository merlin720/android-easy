package com.easy.easycan.me.setting.updateimg;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.SPUtils;
import com.easy.easycan.R;
import com.easy.easycan.base.BaseActivity;
import com.easy.easycan.home.bean.NewsTitleBaseBean;
import com.easy.easycan.me.setting.SettingActivity;
import com.easy.easycan.me.setting.updateimg.bean.UploadBean;
import com.easy.easycan.network.NetHelper;
import com.easy.easycan.util.ClickCallback;
import com.easy.easycan.util.CommonUtils;
import com.easy.easycan.util.LogUtils;
import com.easy.easycan.util.img.EasyGlide;
import com.easy.easycan.view.PickPhotoDialog;
import com.google.gson.Gson;
import com.hjq.toast.ToastUtils;
import com.jakewharton.rxbinding2.view.RxView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.qmuiteam.qmui.widget.QMUITopBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;

/**
 * @author merlin
 * date 2019年09月21日
 * desc 更新头像。
 */
public class UpdateHeadImgActivity extends BaseActivity {

    private QMUITopBar mTopBar;
    private ImageView uploadPictureImg;
    private OSSAsyncTask task;

    public OSS oss;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_update_head_image;
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        initData();
    }
private UploadBean uploadBean;
    private void initData(){
        String url ="http://www.huagongwuliu.com/index_2018.php/oss/main-js-upload";
        NetHelper.get(url, null, new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                String data = "";
                try {
                    int errorCode = response.getInt("code");
                    if (0 == errorCode) {
                        data = response.getString("data");
                        UploadBean bean = new Gson().fromJson(data, UploadBean.class);
                        uploadBean = bean;
                        initOss();
                    } else {
                        ToastUtils.show(response.getString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(ANError anError) {

            }
        });
    }
    @Override
    protected void initView() {
        initTopBar();
        uploadPictureImg = findViewById(R.id.upload_picture_img);
        EasyGlide.loadImage(this, SPUtils.getInstance().getString(CommonUtils.AVATAR),uploadPictureImg);
    }

    private void initTopBar() {
        mTopBar = findViewById(R.id.setting_title);
        mTopBar.setVisibility(View.VISIBLE);
        mTopBar.setTitle(R.string.my_head);

        mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 打开弹窗
     */
    private void uploadPic() {
        PickPhotoDialog.Builder builder = new PickPhotoDialog.Builder(UpdateHeadImgActivity.this);
        PickPhotoDialog dialog = builder.create(1, new PickPhotoDialog.ResultPhoto() {
            @Override
            public void takePhoto() {

            }

            @Override
            public void pickPhoto() {
                PictureSelector.create(UpdateHeadImgActivity.this)
                        .openGallery(PictureMimeType.ofAll())
                        .maxSelectNum(1)
                        .forResult(PictureConfig.CHOOSE_REQUEST);
            }
        });
        dialog.show();
    }

    @Override
    protected void setListener() {

        RxView.clicks(uploadPictureImg)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        uploadPic();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
//                    adapter.setList(selectList);
//                    adapter.notifyDataSetChanged();
                    LogUtils.e(selectList.get(0).getPath());
                    EasyGlide.loadImage(this, selectList.get(0).getPath(),uploadPictureImg);
                    uploadPicture(selectList.get(0).getPath());
                    break;
            }
        }
    }
    private void initOss() {
        String endpoint = uploadBean.getEndpoint();

        //该配置类如果不设置，会有默认配置，具体可看该类
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求数，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        OSSLog.enableLog(); //这个开启会支持写入手机sd卡中的一份日志文件位置在SDCard_path\OSSLog\logs.csv

        OSSCredentialProvider credentialProvider = new OSSStsTokenCredentialProvider(uploadBean.getAccessKeyId(), uploadBean.getAccessKeySecret(), uploadBean.getStsToken());
        oss = new OSSClient(getApplicationContext(), endpoint, credentialProvider, conf);
    }

    private void uploadPicture(String path){
        String pic = path.substring(path.lastIndexOf("/") + 1);
        LogUtils.d(pic);
        PutObjectRequest request = new PutObjectRequest(uploadBean.getBucket(),  uploadBean.getPrefix()+pic, path);
//        String str = new String(Base64.decode(callBackModel.callback, Base64.DEFAULT));
//        LogUtils.d(str);
//        GalleryCallbackParamsModel callbackParamsModel = new Gson().fromJson(str, GalleryCallbackParamsModel.class);
        // 异步上传时可以设置进度回调。
        request.setProgressCallback((request1, currentSize, totalSize) -> runOnUiThread(() -> {
//            progressRl.setVisibility(View.VISIBLE);
//            progressTv.setText("上传第" + (currentNum) + "张图片，进度" + currentSize * 100 / totalSize + "%");

        }));
//        request.setCallbackParam(new HashMap<String, String>() {{
//            LogUtils.d(callbackParamsModel.getCallbackUrl());
//            put("callbackUrl", callbackParamsModel.getCallbackUrl());
//            put("callbackHost", callBackModel.callback_host);
//            put("callbackBodyType", callbackParamsModel.getCallbackBodyType());
//            put("callbackBody", callbackParamsModel.getCallbackBody() + albumId);
//        }});

        task = oss.asyncPutObject(request, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                Log.d("PutObject", "UploadSuccess");
//                runOnUiThread(() -> progressRl.setVisibility(View.GONE));
                // 只有设置了servercallback，这个值才有数据
                String serverCallbackReturnJson = result.getServerCallbackReturnBody();

//                if (currentNum < totalSize) {
//                    upload(pics.get(currentNum));
//                } else {
//                    Gson gson = new Gson();
//                    UploadPicBaseBean model = gson.fromJson(serverCallbackReturnJson, UploadPicBaseBean.class);
//                    LogUtils.d(model.ret);
//                    if (model.ret.equals("1") && null != model.getData()) {
//
//                        presenter.getData(albumId, page, size);
//                    }
//                }
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientException, ServiceException serviceException) {
                // 请求异常
//                runOnUiThread(() -> progressRl.setVisibility(View.GONE));
                if (clientException != null) {
                    // 本地异常如网络异常等
                    clientException.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常
                    Log.e("ErrorCode", serviceException.getErrorCode());
                    Log.e("RequestId", serviceException.getRequestId());
                    Log.e("HostId", serviceException.getHostId());
                    Log.e("RawMessage", serviceException.getRawMessage());
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (task != null)
            task.cancel();
    }
}
