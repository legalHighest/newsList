package com.example.networkapplication.test.logic;

import com.example.networkapplication.test.view.BaseView;
import com.example.networkapplication.test.view.LoginActivity;
import com.example.networkapplication.test.model.LoginModel;

import java.util.regex.Pattern;

public class LoginPresenter implements BasePresenter {
    private LoginModel model;
    private BaseView activity;
    private  String verifyMsg;

    public LoginPresenter(LoginActivity activity){
        this.activity=activity;
        model=new LoginModel();
    }


    @Override
    public void executeLogin(String username, String password) {
        boolean verfyBefore=verifyBeforeLogin(username,password);
        if (verfyBefore){
//            校验通过，请求登录
            model.login(username, password, new LoginModel.OnLoginCallback() {
                @Override
                public void onResponse(boolean success) {
                    activity.notifyLoginResult(success);
                }
            });
        }else {
            activity.showTips(verifyMsg);
        }
    }
    private boolean verifyBeforeLogin(String username, String password) {
        boolean isEmpty = isEmpty(username) || isEmpty(password);
        boolean isValid = isValid(username) && isValid(password);
        if (isEmpty) {
            verifyMsg = "请输入帐号或密码";
            return false;
        }
        if (isValid) {
            return true;
        }
        verifyMsg = "帐号或密码错误";
        return false;
    }

    private boolean isValid(String s) {
        return Pattern.compile("^[A-Za-z0-9]{3,20}+$").matcher(s).matches();
    }

    private boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }


}
