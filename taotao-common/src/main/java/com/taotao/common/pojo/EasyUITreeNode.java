package com.taotao.common.pojo;

import java.io.Serializable;

public class EasyUITreeNode implements Serializable {

    //将返回的pojo转换为符合页面格式的json数据
    private long id;
    private String text;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    private String state;


}
