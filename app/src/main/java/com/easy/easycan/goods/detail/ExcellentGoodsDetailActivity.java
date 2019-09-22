package com.easy.easycan.goods.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.easy.easycan.R;
import com.easy.easycan.base.BaseActivity;
import com.easy.easycan.base.BaseWebActivity;
import com.easy.easycan.goods.detail.model.ExcellentGoodsDetailBean;
import com.easy.easycan.goods.detail.presenter.ExcllentGoodsDetailPresenter;
import com.easy.easycan.goods.detail.view.ExceellentGoodsDetailView;
import com.easy.easycan.goods.detail.view.ExcellentGoodsDetailAdapter;
import com.easy.easycan.util.CommonUtils;
import com.easy.easycan.util.LogUtils;
import com.easy.easycan.util.SizeUtils;
import com.jakewharton.rxbinding2.view.RxView;
import com.qmuiteam.qmui.widget.QMUITopBar;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author merlin
 * date 2019年09月21日
 * desc 精品货源详情
 */
public class ExcellentGoodsDetailActivity extends BaseActivity implements
    ExceellentGoodsDetailView {
  private QMUITopBar mTopBar;
  private String channelId;
  private String id;

  private TextView mRemainingQualityTv;
  private TextView mRemainingTimeTv;

  private TextView goToMapTv;

  private ExcllentGoodsDetailPresenter presenter;

  private RecyclerView recyclerView;
  private ExcellentGoodsDetailAdapter adapter;
  private View header;

  public static void goActivity(Context context, String channelId, String id) {
    Intent intent = new Intent(context, ExcellentGoodsDetailActivity.class);
    intent.putExtra("channelId", channelId);
    intent.putExtra("id", id);
    context.startActivity(intent);
  }

  @Override protected int setLayoutId() {
    return R.layout.activity_excellent_goods_detail;
  }

  @Override protected void initData(@Nullable Bundle savedInstanceState) {
    super.initData(savedInstanceState);
    presenter = new ExcllentGoodsDetailPresenter(this);
    presenter.getData();
  }

  @Override protected void initView() {
    initTopBar();

    recyclerView = findViewById(R.id.exc_goods_detail_recycler);
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(linearLayoutManager);
    adapter = new ExcellentGoodsDetailAdapter();
    recyclerView.setAdapter(adapter);
    initHeader();
    adapter.setHeaderView(header);
  }



  private void initHeader() {
    header = LayoutInflater.from(this).inflate(R.layout.excellent_goods_detail_header, null);
    mRemainingQualityTv = header.findViewById(R.id.exc_goods_detail_header_remaining);
    mRemainingTimeTv = header.findViewById(R.id.exc_goods_detail_header_remaining_time);
    goToMapTv = header.findViewById(R.id.exc_goods_detail_header_map);
  }

  private void initTopBar() {
    mTopBar = findViewById(R.id.excellent_goods_detail_title);
    mTopBar.setVisibility(View.VISIBLE);
    mTopBar.setTitle(R.string.exc_goods_detail_title);
    mTopBar.addRightImageButton(R.drawable.fenxiang, R.id.topbar_right_about_button)
        .setOnClickListener(new View.OnClickListener() {
          @Override public void onClick(View view) {
            share();
          }
        });
    mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });
  }
  @Override protected void setListener() {
    RxView.clicks(goToMapTv)
        .throttleFirst(500, TimeUnit.MILLISECONDS)
        .subscribe(new Consumer<Object>() {
          @Override public void accept(Object o) throws Exception {

            startActivity(new Intent(ExcellentGoodsDetailActivity.this,ExcellentGoodsDetailMapActivity.class));
          }
        });
  }
  /**
   * 分享
   */
  private void share() {

  }

  @Override public void showData(ExcellentGoodsDetailBean model) {
    SpannableString ss = new SpannableString("仅剩：120吨");
    ss.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.tips_color)), 3,
        ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    ss.setSpan(new RelativeSizeSpan(1.5f), 3, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    mRemainingQualityTv.setText(ss);

    SpannableString ss1 = new SpannableString("竞价截止：1天23小时17分");
    ss1.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.tips_color)), 5,
        ss1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    ss1.setSpan(new RelativeSizeSpan(1.5f), 5, ss1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    mRemainingTimeTv.setText(ss1);

    List<ExcellentGoodsDetailBean> list = new ArrayList<>();
    for (int i = 0; i < 15; i++) {
      list.add(new ExcellentGoodsDetailBean());
    }
    adapter.setNewData(list);
  }

  private AlertDialog alertDialog;

  /**
   * 抢单
   * @param str
   */
  private void createDialog(String str) {
    View mLmCountView = LayoutInflater.from(this).inflate(R.layout.qmui_dialog_layout, null);

    AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.Dialog_Fullscreen);
    builder.setCancelable(true);
    builder.setView(mLmCountView);
    alertDialog = builder.create();

    if (null != alertDialog && !alertDialog.isShowing()) {
      alertDialog.show();
      Window window = alertDialog.getWindow();
      window.setDimAmount(0.4f);
      window.setGravity(Gravity.CENTER);
      WindowManager.LayoutParams lp = window.getAttributes();
      lp.width = SizeUtils.getDisplayWidth(this);
      lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
      window.setAttributes(lp);
    }
  }

  public void hideDialog() {
    if (null != alertDialog && alertDialog.isShowing()) {
      alertDialog.dismiss();
    }
  }
}
