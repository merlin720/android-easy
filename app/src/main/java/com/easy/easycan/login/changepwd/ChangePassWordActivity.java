package com.easy.easycan.login.changepwd;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.easy.easycan.R;
import com.easy.easycan.base.BaseActivity;
import com.easy.easycan.login.changepwd.presenter.ChangePasswordPresenter;
import com.easy.easycan.login.changepwd.view.ChangePasswordView;
import com.hjq.toast.ToastUtils;
import com.jakewharton.rxbinding2.view.RxView;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.concurrent.TimeUnit;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * @author merlin
 * date 2019年09月21日
 * desc 我要找货。
 */
public class ChangePassWordActivity extends BaseActivity implements ChangePasswordView {

    private QMUITopBar mTopBar;

    private EditText oldPassWordEt;
    private ImageView oldCancelImg;

    private EditText newPasswordEt;
    private ImageView newPasswordImg;

    private EditText mConfirmPasswordEt;
    private ImageView mConfirmPasswordImg;

    private TextView commitTv;

    private ChangePasswordPresenter presenter;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_change_password;
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        presenter = new ChangePasswordPresenter(this);
    }

    @Override
    protected void initView() {
        initTopBar();
        oldPassWordEt = findViewById(R.id.change_pwd_origin_pass_et);
        oldCancelImg = findViewById(R.id.change_pwd_origin_pass_cancel);

        newPasswordEt = findViewById(R.id.change_pwd_new_pass_et);
        newPasswordImg = findViewById(R.id.change_pwd_new_pass_cancel);

        mConfirmPasswordEt = findViewById(R.id.change_pwd_new_pass_confirm);
        mConfirmPasswordImg = findViewById(R.id.change_pwd_new_pass_confirm_cancel);

        commitTv = findViewById(R.id.change_password_commit);
    }

    private void initTopBar() {
        mTopBar = findViewById(R.id.setting_title);
        mTopBar.setVisibility(View.VISIBLE);
        mTopBar.setTitle(R.string.change_password_title);

        mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    protected void setListener() {
        oldPassWordEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    oldCancelImg.setVisibility(View.VISIBLE);
                } else {
                    oldCancelImg.setVisibility(View.GONE);
                }
            }
        });

        newPasswordEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    newPasswordImg.setVisibility(View.VISIBLE);
                } else {
                    newPasswordImg.setVisibility(View.GONE);
                }
            }
        });
        mConfirmPasswordEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    mConfirmPasswordImg.setVisibility(View.VISIBLE);
                } else {
                    mConfirmPasswordImg.setVisibility(View.GONE);
                }
            }
        });

        Disposable disposable = RxView.clicks(oldCancelImg)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        oldPassWordEt.getText().clear();
                    }
                });
        Disposable disposable1 = RxView.clicks(newPasswordImg)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        newPasswordEt.getText().clear();
                    }
                });
        Disposable disposable2 = RxView.clicks(mConfirmPasswordImg)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        mConfirmPasswordEt.getText().clear();
                    }
                });
        Disposable disposable3 = RxView.clicks(commitTv)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        String password = oldPassWordEt.getText().toString().trim();
                        String newPassword = newPasswordEt.getText().toString().trim();
                        String confirmPassword = mConfirmPasswordEt.getText().toString().trim();
                        if (TextUtils.isEmpty(password)){
                            ToastUtils.show("请输入老密码");
                            return;
                        }
                        if (TextUtils.isEmpty(newPassword)){
                            ToastUtils.show("请输入新密码");
                            return;
                        }
                        if (TextUtils.isEmpty(confirmPassword)){
                            ToastUtils.show("请输入确认密码");
                            return;
                        }
                        if (!newPassword.equals(confirmPassword)){
                            ToastUtils.show("两次输入的新密码必须一致");
                            return;
                        }
                        if (password.equals(confirmPassword)){
                            ToastUtils.show("新旧密码不能一样");
                            return;
                        }
                        presenter.commit(password,newPassword,confirmPassword);
                    }
                });
    }

    @Override
    public void showSuccess() {

    }
}
