package com.fintech.emoneyrechargeonlinenew.Api.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FetchBillRequest {





    @SerializedName("oid")
    @Expose
    public String oid;
    @SerializedName("accountNo")
    @Expose
    public String accountNo;
    @SerializedName("o1")
    @Expose
    private String o1;
    @SerializedName("o2")
    @Expose
    private String o2;
    @SerializedName("o3")
    @Expose
    private String o3;
    @SerializedName("o4")
    @Expose
    private String o4;
    @SerializedName("geoCode")
    @Expose
    private String geoCode;
    @SerializedName("amount")
    @Expose
    public String amount;
    @SerializedName("outletID")
    @Expose
    public String outletID;

    @SerializedName("userID")
    @Expose
    public String userID;
    @SerializedName("sessionID")
    @Expose
    public String sessionID;
    @SerializedName("session")
    @Expose
    public String session;
    @SerializedName("appid")
    @Expose
    public String appid;
    @SerializedName("imei")
    @Expose
    public String imei;
    @SerializedName("regKey")
    @Expose
    public String regKey;
    @SerializedName("version")
    @Expose
    public String version;
    @SerializedName("serialNo")
    @Expose
    public String serialNo;
    @SerializedName("loginTypeID")
    @Expose
    public String loginTypeID;
    @SerializedName("customerNo")
    @Expose
    public String customerNo;

    public FetchBillRequest(String oid, String accountNo,String o1, String o2, String o3,
                            String o4,String geoCode, String amount, String outletID, String userID, String loginTypeID, String appid, String imei, String regKey, String version, String serialNo, String sessionID, String session,String customerNo) {
        this.oid = oid;
        this.accountNo = accountNo;
        this.o1 = o1;
        this.o2 = o2;
        this.o3 = o3;
        this.o4 = o4;
        this.geoCode = geoCode;
        this.amount = amount;
        this.outletID = outletID;
        this.userID = userID;
        this.sessionID = sessionID;
        this.session = session;
        this.appid = appid;
        this.imei = imei;
        this.regKey = regKey;
        this.version = version;
        this.serialNo = serialNo;
        this.loginTypeID = loginTypeID;
        this.customerNo = customerNo;
    }

}
