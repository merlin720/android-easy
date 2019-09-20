package com.easy.easycan.home.news;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.easy.easycan.base.BaseWebActivity;
import com.easy.easycan.util.CommonUtils;
import com.easy.easycan.util.LogUtils;

public class NewsDetailActivity extends BaseWebActivity {

  private String channelId;
  private String id;

  public static void goActivity(Context context,String channelId,String id){
    Intent intent = new Intent(context,NewsDetailActivity.class);
    intent.putExtra("channelId", channelId);
    intent.putExtra("id", id);
    context.startActivity(intent);
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  @Override public String getUrl() {
    channelId = getIntent().getStringExtra("channelId");
    id = getIntent().getStringExtra("id");
    String url = String.format(CommonUtils.HOME_NEWS_LIST_DETAIL, channelId,id);
    LogUtils.e(url);
    return url;
  }
}
