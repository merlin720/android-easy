package com.easy.easycan.view;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;

import com.easy.easycan.R;
import com.easy.easycan.util.ClickUtils;


/**
 * Created by zhouyang on 2017/5/24.
 */

public class PickPhotoDialog extends Dialog {

    public PickPhotoDialog(@NonNull Context context) {
        super(context);
    }

    public PickPhotoDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    public static class Builder {
        private Activity context;

        private View contentView;


        public Builder(Activity context) {
            this.context = context;
        }

        private ObjectAnimator animator;

        public PickPhotoDialog create(final int index,ResultPhoto resultPhoto) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final PickPhotoDialog dialog = new PickPhotoDialog(context, R.style.Common_Theme_Dialog);
            View layout = inflater.inflate(R.layout.choose_pic_camera, null);
            dialog.addContentView(layout, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            dialog.setCanceledOnTouchOutside(true);

            LinearLayout ll = (LinearLayout) layout.findViewById(R.id.choose_pic_out_ll);
            animator = ObjectAnimator.ofFloat(ll, "translationY", 1000, 0);
            animator.setDuration(200);
            animator.setInterpolator(new AccelerateInterpolator());
            animator.start();

            TextView cancel = (TextView) layout.findViewById(R.id.choose_pic_cancel);
            final TextView takePhotoTv = (TextView) layout.findViewById(R.id.choose_pic_take_photo);
            final TextView pickPhoto = (TextView) layout.findViewById(R.id.choose_pic_pic_photo);
            final Button button = (Button) layout.findViewById(R.id.choose_pick_btn);
            ClickUtils.click(cancel, dialog::dismiss);

            ClickUtils.click(takePhotoTv, () -> {
                dialog.dismiss();
                if (null != resultPhoto) {
                    resultPhoto.takePhoto();
                }
            });
            ClickUtils.click(pickPhoto, () -> {
                dialog.dismiss();
                if (null != resultPhoto) {
                    resultPhoto.pickPhoto();
                }
            });

            ClickUtils.click(button, dialog::dismiss);
            dialog.setContentView(layout);

            return dialog;
        }
    }

    public interface ResultPhoto{
        void takePhoto();
        void pickPhoto();
    }

}
