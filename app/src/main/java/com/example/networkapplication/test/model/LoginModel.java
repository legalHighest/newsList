package com.example.networkapplication.test.model;


import android.os.Handler;

public class LoginModel {
    private Handler handler;


    public  LoginModel(){
        handler=new Handler();
    }


    public  interface  OnLoginCallback{
        void onResponse(boolean success);
    }


    /**
     * 相当于写接口请求
     * @param username
     * @param password
     */
    public  void login(String username,String password,final  OnLoginCallback onLoginCallback){
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean result=true;
                onLoginCallback.onResponse(result);
            }
        }).start();
    }
}
