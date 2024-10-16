package com.fintech.emoneyrechargeonlinenew.Api.Object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DmrRequest {
    @SerializedName("topRows")
    @Expose
    private int topRows;
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("apiid")
    @Expose
    private String apiid;
    @SerializedName("fromDate")
    @Expose
    private String fromDate;
    @SerializedName("toDate")
    @Expose
    private String toDate;
    @SerializedName("childMobile")
    @Expose
    private String childMobile;
    @SerializedName("transactionID")
    @Expose
    private String transactionID;
    @SerializedName("accountNo")
    @Expose
    private String accountNo;
    @SerializedName("isExport")
    @Expose
    private Boolean isExport;
    @SerializedName("userID")
    @Expose
    private String userID;
    @SerializedName("sessionID")
    @Expose
    private String sessionID;
    @SerializedName("session")
    @Expose
    private String session;
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
    @SerializedName("loginTypeID")
    @Expose
    private String loginTypeID;

    public DmrRequest(int topRows, int status, String apiid, String fromDate, String toDate, String transactionID, String accountNo,String childMobile, Boolean isExport, String userID, String sessionID, String session, String appid, String imei, String regKey, String version, String serialNo, String loginTypeID) {
        this.topRows = topRows;
        this.status = status;
        this.apiid = apiid;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.transactionID = transactionID;
        this.accountNo = accountNo;
        this.childMobile=childMobile;
        this.isExport = isExport;
        this.userID = userID;
        this.sessionID = sessionID;
        this.session = session;
        this.appid = appid;
        this.imei = imei;
        this.regKey = regKey;
        this.version = version;
        this.serialNo = serialNo;
        this.loginTypeID = loginTypeID;
    }
}
