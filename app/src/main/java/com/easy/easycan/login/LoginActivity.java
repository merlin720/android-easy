package com.easy.easycan.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.SPUtils;
import com.easy.easycan.R;
import com.easy.easycan.base.BaseActivity;
import com.easy.easycan.login.bean.LoginBean;
import com.easy.easycan.login.changepwd.ChangePassWordActivity;
import com.easy.easycan.login.presenter.LoginPresenter;
import com.easy.easycan.login.view.LoginView;
import com.easy.easycan.me.setting.updateimg.UpdateHeadImgActivity;
import com.easy.easycan.util.CommonUtils;
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
public class LoginActivity extends BaseActivity implements LoginView {

    private QMUITopBar mTopBar;

    private EditText userNameEt;
    private EditText passwordEt;

    private TextView commit;

    private LoginPresenter presenter;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        presenter = new LoginPresenter(this);
    }

    @Override
    protected void initView() {
        initTopBar();
        userNameEt = findViewById(R.id.login_phone_et);
        passwordEt = findViewById(R.id.login_pass_et);
        commit = findViewById(R.id.login_commit);
    }

    private void initTopBar() {
        mTopBar = findViewById(R.id.setting_title);
        mTopBar.setVisibility(View.VISIBLE);
        mTopBar.setTitle(R.string.setting);

        mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    protected void setListener() {
        Disposable disposable = RxView.clicks(commit)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        String phone = userNameEt.getText().toString().trim();
                        String password = passwordEt.getText().toString().trim();

                        presenter.login(phone, password, "password");
                    }
                });

    }

    @Override
    public void loginSuccess(LoginBean model) {
            SPUtils.getInstance().put(CommonUtils.accessToken, model.getAccessToken());
    }
}
