package com.easy.easycan.base;

import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.LayoutRes;

import com.qmuiteam.qmui.arch.QMUIFragment;

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
    return view;
  }
  protected abstract void initView(View view);

  protected abstract @LayoutRes int getLayoutId();
}
