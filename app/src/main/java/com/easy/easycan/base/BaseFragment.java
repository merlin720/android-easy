package com.easy.easycan.base;

import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.easy.easycan.home.HomeFragment;
import com.qmuiteam.qmui.arch.QMUIFragment;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;

/**
 * @author merlin720
 * @date 2019-09-16
 * @mail zy44638@gmail.com
 * @description
 */
public abstract class BaseFragment extends QMUIFragment {



  @Override protected View onCreateView() {
    View view = LayoutInflater.from(getActivity()).inflate(getLayoutId(),null);

    initView(view);
    initData();
    setListener();
    return view;
  }
  protected abstract void initView(View view);

  protected abstract @LayoutRes int getLayoutId();

  protected void initData(){}

  protected void setListener(){}


  @Override
  protected int backViewInitOffset() {
    return QMUIDisplayHelper.dp2px(getContext(), 100);
  }

  @Override
  public void onResume() {
    super.onResume();

  }

  @Override
  public Object onLastFragmentFinish() {
    return new HomeFragment();

  }

  protected void goToWebExplorer(@NonNull String url, @Nullable String title) {
    //Intent intent = QDMainActivity.createWebExplorerIntent(getContext(), url, title);
    //startActivity(intent);
  }


}
