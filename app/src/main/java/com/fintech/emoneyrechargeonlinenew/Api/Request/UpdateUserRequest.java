package com.fintech.emoneyrechargeonlinenew.Api.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateUserRequest extends BasicRequest {
    @SerializedName("editUser")
    @Expose
    private EditUser editUser;

    public UpdateUserRequest(String userID, String loginTypeID, String appid, String imei, String regKey, String version, String serialNo, String sessionID, String session, EditUser editUser) {
        this.userID = userID;
        this.loginTypeID = loginTypeID;
        this.appid = appid;
        this.imei = imei;
        this.regKey = regKey;
        this.version = version;
        this.serialNo = serialNo;
        this.sessionID = sessionID;
        this.session = session;
        this.editUser = editUser;
    }
}
