package com.fintech.emoneyrechargeonlinenew.Api.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.fintech.emoneyrechargeonlinenew.Api.Object.BBPSResponse;

public class FetchBillResponse {

    @SerializedName("bBPSResponse")
    @Expose
    public BBPSResponse bBPSResponse;
    @SerializedName("statuscode")
    @Expose
    public Integer statuscode;
    @SerializedName("msg")
    @Expose
    public String msg;
    @SerializedName("isVersionValid")
    @Expose
    public Boolean isVersionValid;
    @SerializedName("isAppValid")
    @Expose
    public Boolean isAppValid;
    @SerializedName("checkID")
    @Expose
    public Integer checkID;

    public BBPSResponse getbBPSResponse() {
        return bBPSResponse;
    }

    public Integer getStatuscode() {
        return statuscode;
    }

    public String getMsg() {
        return msg;
    }

    public Boolean getVersionValid() {
        return isVersionValid;
    }

    public Boolean getAppValid() {
        return isAppValid;
    }

    public Integer getCheckID() {
        return checkID;
    }
}
