package com.fintech.emoneyrechargeonlinenew.QRScan.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MapQRToUserRequest {

    String QRIntent;
    @SerializedName("userID")
    @Expose
    private String userID;
    @SerializedName("loginTypeID")
    @Expose
    private String loginTypeID;
    @SerializedName("appid")
    @Expose
    private String appid;
    @SerializedName("imei")
    @Expose
    private String imei;
    @SerializedName("regKey")
    @Expose
    private String regKey;
    @SerializedName("version")
    @Expose
    private String version;
    @SerializedName("serialNo")
    @Expose
    private String serialNo;
    @SerializedName("sessionID")
    @Expose
    private String sessionID;
    @SerializedName("session")
    @Expose
    private String session;



    public MapQRToUserRequest(String QRIntent,String userID, String loginTypeID, String appid, String imei, String regKey, String version, String serialNo, String sessionID, String session) {
        this.QRIntent = QRIntent;
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
