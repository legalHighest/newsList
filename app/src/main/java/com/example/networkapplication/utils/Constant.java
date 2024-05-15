package com.example.networkapplication.utils;

public enum Constant {

    BASEURL("https://v.juhe.cn/");


    private final String url;

    Constant(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
