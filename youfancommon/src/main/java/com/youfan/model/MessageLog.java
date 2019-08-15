package com.youfan.model;

import java.util.Date;

/**
 * Created by Administrator on 2018/9/29.
 */
public class MessageLog {
    private int id;//主键
    private String ywtype;//业务类型：比如：order
    private int ywid;//业务id
    private String ywmessage;//业务消息实体
    private int ywmessagestatus;//业务消息状态： 1未发送，2，已发送，3发送失败 4,消费者未接受 5，消费成功，6消费失败
    private Date msgcreatedate;//第一次发送的时间
    private Date msgupdatedate;//更新时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYwtype() {
        return ywtype;
    }

    public void setYwtype(String ywtype) {
        this.ywtype = ywtype;
    }

    public int getYwid() {
        return ywid;
    }

    public void setYwid(int ywid) {
        this.ywid = ywid;
    }

    public String getYwmessage() {
        return ywmessage;
    }

    public void setYwmessage(String ywmessage) {
        this.ywmessage = ywmessage;
    }

    public int getYwmessagestatus() {
        return ywmessagestatus;
    }

    public void setYwmessagestatus(int ywmessagestatus) {
        this.ywmessagestatus = ywmessagestatus;
    }

    public Date getMsgcreatedate() {
        return msgcreatedate;
    }

    public void setMsgcreatedate(Date msgcreatedate) {
        this.msgcreatedate = msgcreatedate;
    }

    public Date getMsgupdatedate() {
        return msgupdatedate;
    }

    public void setMsgupdatedate(Date msgupdatedate) {
        this.msgupdatedate = msgupdatedate;
    }
}
