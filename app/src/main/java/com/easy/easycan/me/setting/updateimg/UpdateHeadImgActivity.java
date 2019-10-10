package com.easy.easycan.me.setting.updateimg;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.easy.easycan.R;
import com.easy.easycan.base.BaseActivity;
import com.easy.easycan.view.PickPhotoDialog;
import com.jakewharton.rxbinding2.view.RxView;
import com.qmuiteam.qmui.widget.QMUITopBar;

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


    @Override
    protected int setLayoutId() {
        return R.layout.activity_update_head_image;
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
    }

    @Override
    protected void initView() {
        initTopBar();
        uploadPictureImg = findViewById(R.id.upload_picture_img);
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
        PickPhotoDialog dialog = builder.create(1);
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
}
