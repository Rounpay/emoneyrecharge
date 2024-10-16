package com.fintech.emoneyrechargeonlinenew.QRScan.dto;

import com.fintech.emoneyrechargeonlinenew.Api.Request.BasicRequest;
import com.fintech.emoneyrechargeonlinenew.Util.Senderobject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class VPAListUPIPaymentRequest extends BasicRequest {

    @SerializedName("senderRequest")
    @Expose
    private Senderobject senderRequest;

    public VPAListUPIPaymentRequest(Senderobject senderRequest, String userID, String loginTypeID, String appid, String imei, String regKey, String version, String serialNo, String sessionID, String session) {
        this.senderRequest = senderRequest;
        this.userID = userID;
        this.loginTypeID = loginTypeID;
        this.appid = appid;
        this.imei = imei;
        this.regKey = regKey;
        this.version = version;
        this.serialNo = serialNo;
        this.sessionID = sessionID;
        this.session = session;
    }

    @SerializedName("accountNo")
    @Expose
    private String accountNo;

    public VPAListUPIPaymentRequest(String accountNo, String userID, String loginTypeID, String appid, String imei, String regKey, String version, String serialNo, String sessionID, String session) {
        this.accountNo = accountNo;
        this.userID = userID;
        this.loginTypeID = loginTypeID;
        this.appid = appid;
        this.imei = imei;
        this.regKey = regKey;
        this.version = version;
        this.serialNo = serialNo;
        this.sessionID = sessionID;
        this.session = session;
    }
}
