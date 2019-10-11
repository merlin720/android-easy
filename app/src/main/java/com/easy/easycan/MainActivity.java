package com.easy.easycan;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.SparseArray;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.blankj.utilcode.util.SPUtils;
import com.easy.easycan.base.BaseActivity;
import com.easy.easycan.goods.ExcellentGoodsFragment;
import com.easy.easycan.home.HomeFragment;
import com.easy.easycan.me.ProfileFragment;
import com.easy.easycan.publish.PublishFragment;
import com.easy.easycan.trade.TradeFloorFragment;
import com.gyf.immersionbar.ImmersionBar;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
  /**
   * 需要进行检测的权限数组
   */
  protected String[] needPermissions = {
      Manifest.permission.ACCESS_COARSE_LOCATION,
      Manifest.permission.ACCESS_FINE_LOCATION,
      Manifest.permission.WRITE_EXTERNAL_STORAGE,
      Manifest.permission.READ_EXTERNAL_STORAGE,
      Manifest.permission.READ_PHONE_STATE,
      BACK_LOCATION_PERMISSION
  };
  //如果设置了target > 28，需要增加这个权限，否则不会弹出"始终允许"这个选择框
  private static String BACK_LOCATION_PERMISSION = "android.permission.ACCESS_BACKGROUND_LOCATION";
  private SparseArray<Fragment> fragments;
  private HomeFragment homeFragment;
  private TradeFloorFragment tradeFloorFragment;
  private PublishFragment publishFragment;
  private ExcellentGoodsFragment excellentGoodsFragment;
  private ProfileFragment profileFragment;

  private RadioGroup mRadioGroup;

  @Override
  protected int setLayoutId() {
    return R.layout.activity_main;
  }

  @Override
  protected void initView() {
    initFragment();
    mRadioGroup = findViewById(R.id.radio_group_button);
    initImmersionBar();
  }

  //是否需要检测后台定位权限，设置为true时，如果用户没有给予后台定位权限会弹窗提示
  private boolean needCheckBackLocation = false;
  /**
   * 初始化沉浸式
   */
  protected void initImmersionBar() {
    mImmersionBar = ImmersionBar.with(this);
    mImmersionBar
            .fitsSystemWindows(false)
            .statusBarDarkFont(true, 0.2f)
            .navigationBarWithKitkatEnable(false)
            .init();
  }
  @Override
  protected void initData(@Nullable Bundle savedInstanceState) {
    super.initData(savedInstanceState);
    if (Build.VERSION.SDK_INT > 28
        && getApplicationContext().getApplicationInfo().targetSdkVersion > 28) {
      needPermissions = new String[] {
          Manifest.permission.ACCESS_COARSE_LOCATION,
          Manifest.permission.ACCESS_FINE_LOCATION,
          Manifest.permission.WRITE_EXTERNAL_STORAGE,
          Manifest.permission.READ_EXTERNAL_STORAGE,
          Manifest.permission.READ_PHONE_STATE,
          BACK_LOCATION_PERMISSION
      };
      needCheckBackLocation = true;
    }
    initDate();
  }

  private void initDate() {

  }

  /**
   * 判断是否需要检测，防止不停的弹框
   */
  private boolean isNeedCheck;

  @Override protected void onResume() {
    super.onResume();
    isNeedCheck = SPUtils.getInstance().getBoolean("isNeedCheck");
    if (Build.VERSION.SDK_INT >= 23) {
      if (!isNeedCheck) {
        checkPermissions(needPermissions);
      }
    }
  }

  /**
   * @since 2.5.0
   */
  @TargetApi(23)
  private void checkPermissions(String... permissions) {
    try {
      if (Build.VERSION.SDK_INT >= 23 && getApplicationInfo().targetSdkVersion >= 23) {
        List<String> needRequestPermissonList = findDeniedPermissions(permissions);
        if (null != needRequestPermissonList
            && needRequestPermissonList.size() > 0) {
          try {
            String[] array =
                needRequestPermissonList.toArray(new String[needRequestPermissonList.size()]);
            Method
                method = getClass().getMethod("requestPermissions",
                new Class[] { String[].class, int.class });
            method.invoke(this, array, 0);
          } catch (Throwable e) {

          }
        }
      }
    } catch (Throwable e) {
      e.printStackTrace();
    }
  }

  /**
   * 获取权限集中需要申请权限的列表
   *
   * @since 2.5.0
   */
  @TargetApi(23)
  private List<String> findDeniedPermissions(String[] permissions) {
    try {
      List<String> needRequestPermissonList = new ArrayList<String>();
      if (Build.VERSION.SDK_INT >= 23 && getApplicationInfo().targetSdkVersion >= 23) {
        for (String perm : permissions) {
          if (checkMySelfPermission(perm) != PackageManager.PERMISSION_GRANTED
              || shouldShowMyRequestPermissionRationale(perm)) {
            if (!needCheckBackLocation
                && BACK_LOCATION_PERMISSION.equals(perm)) {
              continue;
            }
            needRequestPermissonList.add(perm);
          }
        }
      }
      return needRequestPermissonList;
    } catch (Throwable e) {
      e.printStackTrace();
    }
    return null;
  }

  private int checkMySelfPermission(String perm) {
    try {
      Method method = getClass().getMethod("checkSelfPermission", new Class[] { String.class });
      Integer permissionInt = (Integer) method.invoke(this, perm);
      return permissionInt;
    } catch (Throwable e) {
    }
    return -1;
  }

  private boolean shouldShowMyRequestPermissionRationale(String perm) {
    try {
      Method method = getClass().getMethod("shouldShowRequestPermissionRationale",
          new Class[] { String.class });
      Boolean permissionInt = (Boolean) method.invoke(this, perm);
      return permissionInt;
    } catch (Throwable e) {
    }
    return false;
  }

  /**
   * 检测是否说有的权限都已经授权
   *
   * @since 2.5.0
   */
  private boolean verifyPermissions(int[] grantResults) {
    try {
      for (int result : grantResults) {
        if (result != PackageManager.PERMISSION_GRANTED) {
          return false;
        }
      }
    } catch (Throwable e) {
      e.printStackTrace();
    }
    return true;
  }

  @TargetApi(23)
  public void onRequestPermissionsResult(int requestCode,
      String[] permissions, int[] paramArrayOfInt) {
    try {
      if (Build.VERSION.SDK_INT >= 23) {
        if (requestCode == PERMISSON_REQUESTCODE) {
          if (!verifyPermissions(paramArrayOfInt)) {
            showMissingPermissionDialog();
            SPUtils.getInstance().put("isNeedCheck", true);
          }
        }
      }
    } catch (Throwable e) {
      e.printStackTrace();
    }
  }

  private static final int PERMISSON_REQUESTCODE = 0;

  /**
   * 显示提示信息
   *
   * @since 2.5.0
   */
  private void showMissingPermissionDialog() {
    try {
      AlertDialog.Builder builder = new AlertDialog.Builder(this);
      builder.setTitle("提示");
      builder.setMessage("当前应用缺少必要权限。\\n\\n请点击\\\"设置\\\"-\\\"权限\\\"-打开所需权限");

      // 拒绝, 退出应用
      builder.setNegativeButton("取消",
          new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              try {
                finish();
              } catch (Throwable e) {
                e.printStackTrace();
              }
            }
          });

      builder.setPositiveButton("设置",
          new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              try {
                startAppSettings();
              } catch (Throwable e) {
                e.printStackTrace();
              }
            }
          });

      builder.setCancelable(false);

      builder.show();
    } catch (Throwable e) {
      e.printStackTrace();
    }
  }

  /**
   * 启动应用的设置
   *
   * @since 2.5.0
   */
  private void startAppSettings() {
    try {
      Intent intent = new Intent(
          Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
      intent.setData(Uri.parse("package:" + getPackageName()));
      startActivity(intent);
    } catch (Throwable e) {
      e.printStackTrace();
    }
  }

  private void initFragment() {
    fragments = new SparseArray<>();
    homeFragment = new HomeFragment();
    tradeFloorFragment = new TradeFloorFragment();
    publishFragment = new PublishFragment();
    excellentGoodsFragment = new ExcellentGoodsFragment();
    profileFragment = new ProfileFragment();
    fragments.put(0, homeFragment);
    fragments.put(1, tradeFloorFragment);
    fragments.put(2, publishFragment);
    fragments.put(3, excellentGoodsFragment);
    fragments.put(4, profileFragment);
    getSupportFragmentManager().beginTransaction()
        .replace(R.id.home_container, homeFragment)
        .commit();
  }

  @Override
  protected void setListener() {
    mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
      Fragment mFragment = null;

      @Override
      public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
          case R.id.radio_button_home:
            mFragment = fragments.get(0);
            break;
          case R.id.radio_button_discovery:
            mFragment = fragments.get(1);
            break;
          case R.id.radio_button_attention:
            mFragment = fragments.get(2);
            break;
          case R.id.radio_button_boutique_source:
            mFragment = fragments.get(3);
            break;
          case R.id.radio_button_profile:
            mFragment = fragments.get(4);
            break;
        }
        if (fragments != null) {
          getSupportFragmentManager().beginTransaction()
              .replace(R.id.home_container, mFragment)
              .commit();
        }
      }
    });
  }

  public void switchToFragment(int index) {
    if (index >= 0) {
      ((RadioButton)mRadioGroup.getChildAt(index)).setChecked(true);

    }
  }

  @Override
  protected boolean translucentFull() {
    return true;
  }
}
