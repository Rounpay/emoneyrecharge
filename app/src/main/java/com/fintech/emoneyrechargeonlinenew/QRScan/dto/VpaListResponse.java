package com.fintech.emoneyrechargeonlinenew.QRScan.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VpaListResponse {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("vpa")
    @Expose
    private String vpa;
    @SerializedName("accountHolder")
    @Expose
    private String accountHolder;
    @SerializedName("senderNo")
    @Expose
    private String senderNo;
    @SerializedName("isVerified")
    @Expose
    private boolean isVerified;

    public int getId() {
        return id;
    }

    public String getVpa() {
        return vpa;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public String getSenderNo() {
        return senderNo;
    }

    public boolean isVerified() {
        return isVerified;
    }
}
