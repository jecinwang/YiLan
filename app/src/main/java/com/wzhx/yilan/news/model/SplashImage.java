package com.wzhx.yilan.news.model;

import java.io.Serializable;

/**
 * Created by wzhx on 2017/6/17.
 */

public class SplashImage implements Serializable {
    private String text;//图片出处
    private String img;//图片地址

    public String getText() {
        return text;
    }

    public String getImg() {
        return img;
    }

    @Override
    public String toString() {
        return "SplashImage{" +
                "text='" + text + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
