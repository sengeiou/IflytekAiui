package com.zhengpu.iflytekaiui.ipc.entity;

/**
 * sayid ....
 * Created by wengmf on 2017/12/26.
 */

public class SendMessage {

    private String message;
    private String service;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
