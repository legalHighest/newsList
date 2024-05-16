package com.example.networkapplication.test.view;

public interface BaseView {
    /**
     * 更新登录结果
     *
     * @param loginResult
     */
    void notifyLoginResult(boolean loginResult);

    /**
     * Toast提示
     *
     * @param tips
     */
    void showTips(String tips);
}
