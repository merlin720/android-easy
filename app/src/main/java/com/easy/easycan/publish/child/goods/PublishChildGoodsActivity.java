package com.easy.easycan.publish.child.goods;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.textclassifier.TextLinks;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnDismissListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.easy.easycan.R;
import com.easy.easycan.base.BaseActivity;
import com.easy.easycan.goods.detail.model.ExcellentGoodsDetailBean;
import com.easy.easycan.goods.detail.view.ExceellentGoodsDetailView;
import com.easy.easycan.util.AreaPickerUtils;
import com.easy.easycan.util.SizeUtils;
import com.jakewharton.rxbinding2.view.RxView;
import com.qmuiteam.qmui.util.QMUIViewHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.zhy.view.flowlayout.TagFlowLayout;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author merlin
 * date 2019年09月21日
 * desc 发布货源的发布页。
 */
public class PublishChildGoodsActivity extends BaseActivity implements
    ExceellentGoodsDetailView {

  private QMUITopBar mTopBar;
  /**
   * 装货地
   */
  private TextView mDepartureTv;
  private TextView mDestinationTv;
  private EditText mGoodsNameTv;
  private TextView goodsTypeTv;
  private TextView mTankRequirementsTv;
  private TextView mLoadingTimeTv;
  private EditText goodsNumEd;
  private EditText contactNameEd;
  private EditText contactPhoneEd;

  private CheckBox checkBox;
  private TextView addressBookTv;

  /** 普通货源 */
  private RelativeLayout mNormalGoodsRl;
  private ImageView mNormalGoodsRightImg;
  private View mNormalGoodsBottomImg;
  private LinearLayout mNormalGoodsLl;
  /** 竞价货源 */
  private RelativeLayout mBiddingGoodsRl;
  private ImageView mBiddingGoodsRightImg;
  private View mBiddingGoodsBottomImg;
  private LinearLayout mBiddingGoodsLl;
  /** 定价货源 */
  private RelativeLayout mPricingGoodsRl;
  private ImageView mPricingGoodsRightImg;
  private View mPricingGoodsBottomImg;
  private LinearLayout mPricingGoodsLl;

  private Switch biddingSwitch;
  private TextView biddingSwitchText;
  private TextView biddingPaymentTv;
  private TextView biddingdeadlineTv;

  private Switch pricingSwitch;
  private TextView pricingSwitchText;
  private TextView pricingPaymentTv;
  private TextView pricingdeadlineTv;

  private List<String> mFiltrateData = new ArrayList<>();
  private List<String> mTimeData = new ArrayList<>();
  private List<String> mPaymentMethodData = new ArrayList<>();

  @Override protected int setLayoutId() {
    return R.layout.activity_publish_child_goods;
  }

  @Override protected void initData(@Nullable Bundle savedInstanceState) {
    super.initData(savedInstanceState);
  }

  @Override protected void initView() {
    initTopBar();
    initGoodsData();
    mDepartureTv = findViewById(R.id.publish_child_goods_dep);
    mDestinationTv = findViewById(R.id.publish_child_goods_destination);
    mGoodsNameTv = findViewById(R.id.publish_goods_goods_name);
    goodsTypeTv = findViewById(R.id.publish_goods_goods_type);
    mTankRequirementsTv = findViewById(R.id.publish_goods_tank_requirements);
    mLoadingTimeTv = findViewById(R.id.publish_goods_loading_time);
    goodsNumEd = findViewById(R.id.publish_goods_goods_num);
    addressBookTv = findViewById(R.id.publish_goods_address_book);
    contactNameEd = findViewById(R.id.publish_goods_contact_name);
    contactPhoneEd = findViewById(R.id.publish_goods_contact_phone);
    checkBox = findViewById(R.id.publish_goods_protocol_check);
    AreaPickerUtils.getInstance().initJsonData(this);

    initCheck();
    initAllSourceInput();
  }

  private void initTopBar() {
    mTopBar = findViewById(R.id.publish_child_goods_title);
    mTopBar.setVisibility(View.VISIBLE);
    mTopBar.setTitle(R.string.publish_goods_title);
    Button button = mTopBar.addRightTextButton(R.string.publish_regular_source,
        QMUIViewHelper.generateViewId());
    RxView.clicks(button)
        .throttleFirst(500, TimeUnit.MILLISECONDS)
        .subscribe(new Consumer<Object>() {
          @Override public void accept(Object o) throws Exception {

          }
        });
    mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });
  }

  /**
   * 设置checkbox
   */
  private void initCheck() {
    SpannableString spannableString = new SpannableString(getString(R.string.publish_protocol));
    URLSpan urlSpan = new URLSpan("https://www.baidu.com");
    spannableString.setSpan(urlSpan, 7, 14, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
    checkBox.setMovementMethod(LinkMovementMethod.getInstance());
    checkBox.setText(spannableString);
  }

  /**
   * 三种货源
   */
  private void initAllSourceInput() {
    mNormalGoodsRl = findViewById(R.id.publish_goods_normal_source_rl);
    mNormalGoodsRightImg = findViewById(R.id.publish_goods_normal_source_right_img);
    mNormalGoodsBottomImg = findViewById(R.id.publish_goods_normal_source_bottom_view);
    mNormalGoodsLl = findViewById(R.id.normal_goods_source_ll);

    mBiddingGoodsRl = findViewById(R.id.publish_goods_bidding_source_rl);
    mBiddingGoodsRightImg = findViewById(R.id.publish_goods_bidding_source_img);
    mBiddingGoodsBottomImg = findViewById(R.id.publish_goods_bidding_source_view);
    mBiddingGoodsLl = findViewById(R.id.bidding_goods_source_ll);

    mPricingGoodsRl = findViewById(R.id.publish_goods_pricing_source_rl);
    mPricingGoodsRightImg = findViewById(R.id.publish_goods_pricing_source_img);
    mPricingGoodsBottomImg = findViewById(R.id.publish_goods_pricing_source_view);
    mPricingGoodsLl = findViewById(R.id.pricing_goods_source_ll);

    Disposable disposable = RxView.clicks(mNormalGoodsRl)
        .throttleFirst(500, TimeUnit.MILLISECONDS)
        .subscribe(o -> {
          resetAllSourceState();
          mNormalGoodsRightImg.setVisibility(View.VISIBLE);
          mNormalGoodsBottomImg.setVisibility(View.GONE);
          mNormalGoodsLl.setVisibility(View.VISIBLE);
          mBiddingGoodsLl.setVisibility(View.GONE);
          mPricingGoodsLl.setVisibility(View.GONE);
        });

    Disposable disposable1 = RxView.clicks(mBiddingGoodsRl)
        .throttleFirst(500, TimeUnit.MILLISECONDS)
        .subscribe(o -> {
          resetAllSourceState();
          mBiddingGoodsRightImg.setVisibility(View.VISIBLE);
          mBiddingGoodsBottomImg.setVisibility(View.GONE);
          mNormalGoodsLl.setVisibility(View.GONE);
          mBiddingGoodsLl.setVisibility(View.VISIBLE);
          mPricingGoodsLl.setVisibility(View.GONE);
        });

    Disposable disposable2 = RxView.clicks(mPricingGoodsRl)
        .throttleFirst(500, TimeUnit.MILLISECONDS)
        .subscribe(o -> {
          resetAllSourceState();
          mPricingGoodsRightImg.setVisibility(View.VISIBLE);
          mPricingGoodsBottomImg.setVisibility(View.GONE);
          mNormalGoodsLl.setVisibility(View.GONE);
          mBiddingGoodsLl.setVisibility(View.GONE);
          mPricingGoodsLl.setVisibility(View.VISIBLE);
        });
    allSourceInner();
  }

  private void allSourceInner() {

    biddingSwitch = findViewById(R.id.bidding_goods_source_switch);
    biddingSwitchText = findViewById(R.id.bidding_goods_source_switch_text);
    biddingSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
      if (isChecked) {
        biddingSwitchText.setText("是");
      } else {
        biddingSwitchText.setText("否");
      }
    });

    biddingPaymentTv = findViewById(R.id.bidding_goods_source_payment_tv);
    biddingdeadlineTv = findViewById(R.id.bidding_goods_source_deadline_tv);

    Disposable disposable = RxView.clicks(biddingPaymentTv)
        .throttleFirst(500, TimeUnit.MILLISECONDS)
        .subscribe(o -> {
          initNoLinkOptionsPicker(2);
        });

    Disposable disposable2 = RxView.clicks(biddingdeadlineTv)
        .throttleFirst(500, TimeUnit.MILLISECONDS)
        .subscribe(o -> {
          initCustomTimePicker(2);
        });
    pricingSwitch = findViewById(R.id.pricing_goods_source_switch);
    pricingSwitchText = findViewById(R.id.pricing_goods_source_switch_text);
    pricingSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
      if (isChecked) {
        pricingSwitchText.setText("是");
      } else {
        pricingSwitchText.setText("否");
      }
    });
    pricingPaymentTv = findViewById(R.id.pricing_goods_source_payment_tv);
    pricingdeadlineTv = findViewById(R.id.pricing_goods_source_deadline_tv);

    Disposable disposable3 = RxView.clicks(pricingPaymentTv)
        .throttleFirst(500, TimeUnit.MILLISECONDS)
        .subscribe(o -> {
          initNoLinkOptionsPicker(3);
        });
    Disposable disposable4 = RxView.clicks(pricingdeadlineTv)
        .throttleFirst(500, TimeUnit.MILLISECONDS)
        .subscribe(o -> {
          initCustomTimePicker(3);
        });
  }

  private void resetAllSourceState() {
    mNormalGoodsRightImg.setVisibility(View.GONE);
    mNormalGoodsBottomImg.setVisibility(View.VISIBLE);

    mBiddingGoodsRightImg.setVisibility(View.GONE);
    mBiddingGoodsBottomImg.setVisibility(View.VISIBLE);

    mPricingGoodsRightImg.setVisibility(View.GONE);
    mPricingGoodsBottomImg.setVisibility(View.VISIBLE);
  }

  @Override protected void setListener() {
    Disposable disposable = RxView.clicks(mDestinationTv)
        .throttleFirst(500, TimeUnit.MILLISECONDS)
        .subscribe(o -> AreaPickerUtils.getInstance().showPickerView(PublishChildGoodsActivity.this,
            null, commitStr -> {
              mDestinationTv.setText(commitStr);
              mDestinationTv.setTextColor(
                  ContextCompat.getColor(PublishChildGoodsActivity.this, R.color.text_color));
            }));

    Disposable disposable1 = RxView.clicks(mDepartureTv)
        .throttleFirst(500, TimeUnit.MILLISECONDS)
        .subscribe(o -> AreaPickerUtils.getInstance().showPickerView(PublishChildGoodsActivity.this,
            null, commitStr -> {
              mDepartureTv.setText(commitStr);
              mDepartureTv.setTextColor(
                  ContextCompat.getColor(PublishChildGoodsActivity.this, R.color.text_color));
            }));

    Disposable disposable2 = RxView.clicks(goodsTypeTv)
        .throttleFirst(500, TimeUnit.MILLISECONDS)
        .subscribe(o -> {
          initNoLinkOptionsPicker(1);
        });
    Disposable disposable3 = RxView.clicks(mTankRequirementsTv)
        .throttleFirst(500, TimeUnit.MILLISECONDS)
        .subscribe(o -> {
          initNoLinkOptionsPicker(1);
        });
    Disposable disposable4 = RxView.clicks(mLoadingTimeTv)
        .throttleFirst(500, TimeUnit.MILLISECONDS)
        .subscribe(o -> {
          //pvCustomTime.show();
          initCustomTimePicker(1);
        });
    //Disposable disposable5 = RxView.clicks(goodsTypeTv)
    //    .throttleFirst(500, TimeUnit.MILLISECONDS)
    //    .subscribe(o -> {
    //      pvCustomTime.show();
    //    });
    Disposable disposable7 = RxView.clicks(addressBookTv)
        .throttleFirst(500, TimeUnit.MILLISECONDS)
        .subscribe(o -> {
          Intent intent = new Intent();
          intent.setAction("android.intent.action.PICK");
          intent.addCategory("android.intent.category.DEFAULT");
          intent.setType("vnd.android.cursor.dir/phone_v2");
          startActivityForResult(intent, 0x30);
        });
  }

  @Override public void showData(ExcellentGoodsDetailBean model) {

  }

  /**
   * 选择联系人之后返回的结果处理
   */
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == 0x30) {
      if (data != null) {
        Uri uri = data.getData();
        String phoneNum = null;
        String contactName = null;
        // 创建内容解析者
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = null;
        if (uri != null) {
          cursor = contentResolver.query(uri,
              new String[] { "display_name", "data1" }, null, null, null);
        }
        while (cursor.moveToNext()) {
          contactName = cursor.getString(
              cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
          phoneNum = cursor.getString(
              cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
        }
        cursor.close();
        //  把电话号码中的  -  符号 替换成空格
        if (phoneNum != null) {
          phoneNum = phoneNum.replaceAll("-", " ");
          // 空格去掉  为什么不直接-替换成"" 因为测试的时候发现还是会有空格 只能这么处理
          phoneNum = phoneNum.replaceAll(" ", "");
        }

        contactNameEd.setText(contactName);
        contactPhoneEd.setText(phoneNum);
      }
    }
  }

  private AlertDialog alertDialog;

  /**
   * 货物名称
   */
  private void createDialog(String str) {
    View mLmCountView =
        LayoutInflater.from(this).inflate(R.layout.publish_goods_choose_goods_num, null);
    TagFlowLayout tagFlowLayout = mLmCountView.findViewById(R.id.publish_goods_choose_goods_flow);
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

  /**
   * 货物类型和车辆罐体这两个弹窗。
   *
   * @param type 1是货物类型点击弹窗的
   * 2是结款方式
   */
  private void initNoLinkOptionsPicker(int type) {
    OptionsPickerView pvNoLinkOptions =
        new OptionsPickerBuilder(this, new OnOptionsSelectListener() {

          @Override
          public void onOptionsSelect(int options1, int options2, int options3, View v) {
            switch (type) {
              case 1:
                goodsTypeTv.setText(mFiltrateData.get(options1));
                goodsTypeTv.setTextColor(
                    ContextCompat.getColor(PublishChildGoodsActivity.this, R.color.text_color));
                mTankRequirementsTv.setTextColor(
                    ContextCompat.getColor(PublishChildGoodsActivity.this, R.color.text_color));
                mTankRequirementsTv.setText(mTimeData.get(options2));
                break;
              case 2:
                biddingPaymentTv.setText(mPaymentMethodData.get(options1));
                biddingPaymentTv.setTextColor(
                    ContextCompat.getColor(PublishChildGoodsActivity.this, R.color.text_color));
                break;
              case 3:
                pricingPaymentTv.setText(mPaymentMethodData.get(options1));
                pricingPaymentTv.setTextColor(
                    ContextCompat.getColor(PublishChildGoodsActivity.this, R.color.text_color));
                break;
            }
          }
        })

            // .setSelectOptions(0, 1, 1)
            .build();
    switch (type) {
      case 1:
        pvNoLinkOptions.setNPicker(mFiltrateData, mTimeData, null);
        break;
      case 2:
      case 3:
        pvNoLinkOptions.setNPicker(mPaymentMethodData, null, null);
        break;
    }

    pvNoLinkOptions.setSelectOptions(0, 1, 1);
    pvNoLinkOptions.show();
  }

  private TimePickerView pvCustomTime;

  private void initCustomTimePicker(int type) {

    /**
     * @description
     *
     * 注意事项：
     * 1.自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针.
     * 具体可参考demo 里面的两个自定义layout布局。
     * 2.因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
     * setRangDate方法控制起始终止时间(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
     */
    Calendar selectedDate = Calendar.getInstance();//系统当前时间
    Calendar startDate = Calendar.getInstance();
    //startDate.set(2014, 1, 23);
    Calendar endDate = Calendar.getInstance();
    endDate.set(2020, 1, 1);
    //时间选择器 ，自定义布局
    pvCustomTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
      @Override
      public void onTimeSelect(Date date, View v) {//选中事件回调
        switch (type) {
          case 1:
            mLoadingTimeTv.setText(getTime(date));
            mLoadingTimeTv.setTextColor(
                ContextCompat.getColor(PublishChildGoodsActivity.this, R.color.text_color));
            break;
          case 2:
            biddingdeadlineTv.setText(getTime(date));
            biddingdeadlineTv.setTextColor(
                ContextCompat.getColor(PublishChildGoodsActivity.this, R.color.text_color));
            break;
          case 3:
            pricingdeadlineTv.setText(getTime(date));
            pricingdeadlineTv.setTextColor(
                ContextCompat.getColor(PublishChildGoodsActivity.this, R.color.text_color));
            break;
        }
      }
    })

        .setDate(selectedDate)
        .setRangDate(startDate, endDate)
        .setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {

          @Override
          public void customLayout(View v) {
            final TextView tvSubmit = v.findViewById(R.id.tv_finish);
            TextView ivCancel = v.findViewById(R.id.tv_cancel);
            tvSubmit.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                pvCustomTime.returnData();
                pvCustomTime.dismiss();
              }
            });
            ivCancel.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                pvCustomTime.dismiss();
              }
            });
          }
        })
        .setContentTextSize(18)
        .setType(new boolean[] { true, true, true, false, false, false })
        .setLabel("年", "月", "日", "时", "分", "秒")
        .setLineSpacingMultiplier(1.2f)
        .setTextXOffset(40, 40, -40, 0, 0, -40)
        .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
        .setDividerColor(0xFF24AD9D)
        .build();
    pvCustomTime.show();
  }

  private String getTime(Date date) {//可根据需要自行截取数据显示
    Log.d("getTime()", "choice date millis: " + date.getTime());
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    return format.format(date);
  }

  private void initGoodsData() {
    mFiltrateData.add("一类 爆炸品");
    mFiltrateData.add("二类 压缩气体和液体气体");
    mFiltrateData.add("三类 易燃液体");
    mFiltrateData.add("四类 易燃固体和");
    mFiltrateData.add("五类 氧化剂和邮寄过氧化物");
    mFiltrateData.add("六类 毒害品和感染性物品");
    mFiltrateData.add("七类 放射性物品");
    mFiltrateData.add("八类 腐蚀品");
    mFiltrateData.add("九类 杂类");
    mFiltrateData.add("普通货物");

    mTimeData.add("不限");
    mTimeData.add("罐车");
    mTimeData.add("不锈钢罐车");
    mTimeData.add("铝合金罐车");
    mTimeData.add("铁罐车");
    mTimeData.add("压力罐车");
    mTimeData.add("保温罐车");
    mTimeData.add("铝罐车");
    mTimeData.add("非罐车");
    mTimeData.add("微型车");
    mTimeData.add("平板车");
    mTimeData.add("高栏车");
    mTimeData.add("集装箱式车");
    mTimeData.add("飞翼车");

    mPaymentMethodData.add("卸车结款");
    mPaymentMethodData.add("见榜单/见票结款");
    mPaymentMethodData.add("7-15天结款");
    mPaymentMethodData.add("15-30天结款");
    mPaymentMethodData.add("30-45天结款");
  }
}
