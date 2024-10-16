package com.fintech.emoneyrechargeonlinenew.DTHSubscription.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DthSubscriptionReport {


    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("tid")
    @Expose
    public int tid;
    @SerializedName("transactionID")
    @Expose
    public String transactionID;
    @SerializedName("outletName")
    @Expose
    public String outletName;
    @SerializedName("mobileNo")
    @Expose
    public String mobileNo;
    @SerializedName("account")
    @Expose
    public String account;
    @SerializedName("opening")
    @Expose
    public double opening;
    @SerializedName("requestedAmount")
    @Expose
    public double requestedAmount;
    @SerializedName("amount")
    @Expose
    public double amount;
    @SerializedName("balance")
    @Expose
    public double balance;
    @SerializedName("commission")
    @Expose
    public double commission;
    @SerializedName("bookingStatus")
    @Expose
    public int bookingStatus;
    @SerializedName("bookingStatus_")
    @Expose
    public String bookingStatus_;
    @SerializedName("status")
    @Expose
    public int status;
    @SerializedName("status_")
    @Expose
    public String status_;
    @SerializedName("customerNumber")
    @Expose
    public String customerNumber;
    @SerializedName("customerName")
    @Expose
    public String customerName;
    @SerializedName("address")
    @Expose
    public String address;
    @SerializedName("pincode")
    @Expose
    public String pincode;
    @SerializedName("pid")
    @Expose
    public int pid;
    @SerializedName("packageName")
    @Expose
    public String packageName;
    @SerializedName("operator")
    @Expose
    public String operator;
    @SerializedName("oid")
    @Expose
    public int oid;
    @SerializedName("api")
    @Expose
    public String api;
    @SerializedName("apiRequestID")
    @Expose
    public Object apiRequestID;
    @SerializedName("liveID")
    @Expose
    public String liveID;
    @SerializedName("remark")
    @Expose
    public String remark;
    @SerializedName("entryDate")
    @Expose
    public String entryDate;
    @SerializedName("modifyDate")
    @Expose
    public String modifyDate;
    String requestMode;
    private boolean commType;

    public int getId() {
        return id;
    }

    public int getTid() {
        return tid;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public String getOutletName() {
        return outletName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public String getAccount() {
        return account;
    }

    public double getOpening() {
        return opening;
    }

    public double getRequestedAmount() {
        return requestedAmount;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalance() {
        return balance;
    }

    public double getCommission() {
        return commission;
    }

    public int getBookingStatus() {
        return bookingStatus;
    }

    public String getBookingStatus_() {
        return bookingStatus_;
    }

    public int getStatus() {
        return status;
    }

    public String getStatus_() {
        return status_;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getAddress() {
        return address;
    }

    public String getPincode() {
        return pincode;
    }

    public int getPid() {
        return pid;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getOperator() {
        return operator;
    }

    public int getOid() {
        return oid;
    }

    public String getApi() {
        return api;
    }

    public Object getApiRequestID() {
        return apiRequestID;
    }

    public String getLiveID() {
        return liveID;
    }

    public String getRemark() {
        return remark;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public String getRequestMode() {
        return requestMode;
    }

    public boolean isCommType() {
        return commType;
    }
}
