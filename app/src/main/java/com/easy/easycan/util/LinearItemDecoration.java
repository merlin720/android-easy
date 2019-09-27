package com.easy.easycan.util;

import android.graphics.Rect;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author merlin720
 * @date 2019-09-26
 * @mail zy44638@gmail.com
 * @description
 */
public class LinearItemDecoration extends RecyclerView.ItemDecoration {

  private int bottom;

  public LinearItemDecoration(int bottom) {
    this.bottom = bottom;
  }

  @Override public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
      @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
    outRect.bottom = outRect.bottom + bottom;
    super.getItemOffsets(outRect, view, parent, state);
  }
}
