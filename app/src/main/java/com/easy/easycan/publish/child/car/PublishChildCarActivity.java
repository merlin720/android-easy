package com.easy.easycan.publish.child.car;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.easy.easycan.R;
import com.easy.easycan.base.BaseActivity;
import com.easy.easycan.goods.detail.model.ExcellentGoodsDetailBean;
import com.easy.easycan.goods.detail.view.ExceellentGoodsDetailView;
import com.easy.easycan.me.managecar.CarManagementActivity;
import com.easy.easycan.publish.child.goods.PublishChildGoodsActivity;
import com.easy.easycan.util.AreaPickerUtils;
import com.jakewharton.rxbinding2.view.RxView;
import com.qmuiteam.qmui.widget.QMUITopBar;
import io.reactivex.disposables.Disposable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author merlin
 * date 2019年09月21日
 * desc 发布货源的发布页。
 */
public class PublishChildCarActivity extends BaseActivity implements
    ExceellentGoodsDetailView {


  private QMUITopBar mTopBar;
  /**
   * 装货地
   */
  private TextView mDepartureTv;
  private TextView mDestinationTv;

  private TextView publishTimeTv;
  private TextView addressBookTv;

  private EditText contactNameEd;
  private EditText contactPhoneEd;

  private TextView manageCarTv;

  @Override protected int setLayoutId() {
    return R.layout.activity_publish_child_car;
  }

  @Override protected void initData(@Nullable Bundle savedInstanceState) {
    super.initData(savedInstanceState);
  }

  @Override protected void initView() {
    initTopBar();
    mDepartureTv = findViewById(R.id.publish_child_goods_dep);
    mDestinationTv = findViewById(R.id.publish_child_goods_destination);
    publishTimeTv = findViewById(R.id.publish_car_publish_time);
    addressBookTv = findViewById(R.id.publish_goods_address_book);
    contactNameEd = findViewById(R.id.publish_goods_contact_name);
    contactPhoneEd = findViewById(R.id.publish_goods_contact_phone);
    manageCarTv = findViewById(R.id.publish_car_manage_car);
    AreaPickerUtils.getInstance().initJsonData(this);
    initCustomTimePicker();
  }

  private void initTopBar() {
    mTopBar = findViewById(R.id.publish_child_car_title);
    mTopBar.setVisibility(View.VISIBLE);
    mTopBar.setTitle(R.string.publish_car_title);

    mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });
  }

  @Override protected void setListener() {
    Disposable disposable = RxView.clicks(mDestinationTv)
        .throttleFirst(500, TimeUnit.MILLISECONDS)
        .subscribe(o -> AreaPickerUtils.getInstance().showPickerView(PublishChildCarActivity.this,
            null, commitStr -> {
              mDestinationTv.setText(commitStr);
              mDestinationTv.setTextColor(
                  ContextCompat.getColor(PublishChildCarActivity.this, R.color.text_color));
            }));

    Disposable disposable1 = RxView.clicks(mDepartureTv)
        .throttleFirst(500, TimeUnit.MILLISECONDS)
        .subscribe(o -> AreaPickerUtils.getInstance().showPickerView(PublishChildCarActivity.this,
            null, commitStr -> {
              mDepartureTv.setText(commitStr);
              mDepartureTv.setTextColor(
                  ContextCompat.getColor(PublishChildCarActivity.this, R.color.text_color));
            }));
    Disposable disposable2 = RxView.clicks(publishTimeTv)
        .throttleFirst(500, TimeUnit.MILLISECONDS)
        .subscribe(o -> {
          pvCustomTime.show();
        });
    Disposable disposable3 = RxView.clicks(manageCarTv)
        .throttleFirst(500, TimeUnit.MILLISECONDS)
        .subscribe(o -> {
          startActivity(new Intent(PublishChildCarActivity.this, CarManagementActivity.class));
        });

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

  /**
   * 时间弹窗
   */
  private TimePickerView pvCustomTime;
  private void initCustomTimePicker() {

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

        publishTimeTv.setText(getTime(date));
        publishTimeTv.setTextColor(
                ContextCompat.getColor(PublishChildCarActivity.this, R.color.text_color));


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

  }

  private String getTime(Date date) {//可根据需要自行截取数据显示
    Log.d("getTime()", "choice date millis: " + date.getTime());
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    return format.format(date);
  }
}
