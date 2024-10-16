package com.fintech.emoneyrechargeonlinenew.Api.Object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UserList {
    @SerializedName("rental")
    @Expose
    public String rental;
    @SerializedName("rentalAmt")
    @Expose
    public Integer rentalAmt;
    @SerializedName("capping")
    @Expose
    public Double capping;
    @SerializedName("bCapping")
    @Expose
    public double bCapping;
    @SerializedName("referalID")
    @Expose
    public Integer referalID;
    @SerializedName("roleID")
    @Expose
    public int roleID;
    @SerializedName("commRate")
    @Expose
    public Double commRate;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("role")
    @Expose
    public String role;
    @SerializedName("kycStatus")
    @Expose
    public String kycStatusStr;
    @SerializedName("kycStatus_")
    @Expose
    public Integer kycStatus;
    @SerializedName("outletName")
    @Expose
    public String outletName;
    @SerializedName("mobileNo")
    @Expose
    public String mobileNo;
    @SerializedName("status")
    @Expose
    public boolean status;
    @SerializedName("isOTP")
    @Expose
    public boolean isOTP;
    @SerializedName("joinDate")
    @Expose
    public String joinDate;
    @SerializedName("joinBy")
    @Expose
    public String joinBy;
    @SerializedName("slab")
    @Expose
    public String slab;
    @SerializedName("websiteName")
    @Expose
    public String websiteName;
    @SerializedName("balance")
    @Expose
    public String balance;
    @SerializedName("uBalance")
    @Expose
    public double uBalance;
    @SerializedName("bBalance")
    @Expose
    public double bBalance;
    @SerializedName("cBalance")
    @Expose
    public double cBalance;
    @SerializedName("idBalnace")
    @Expose
    public double idBalnace;
    @SerializedName("pacakgeBalance")
    @Expose
    public double pacakgeBalance;
    @SerializedName("osBalance")
    @Expose
    public double osBalance;
    @SerializedName("name")
    @Expose
    public Object name;
    @SerializedName("prefix")
    @Expose
    public String prefix;
    public ArrayList<BalanceType> balanceTypes;

    public void setBalanceTypes(ArrayList<BalanceType> balanceTypes) {
        this.balanceTypes = balanceTypes;
    }

    public ArrayList<BalanceType> getBalanceTypes() {
        return balanceTypes;
    }

    public double getPacakgeBalance() {
        return pacakgeBalance;
    }

    public double getOsBalance() {
        return osBalance;
    }

    public double getuBalance() {
        return uBalance;
    }

    public double getbBalance() {
        return bBalance;
    }

    public double getcBalance() {
        return cBalance;
    }

    public double getIdBalnace() {
        return idBalnace;
    }

    public String getRental() {
        return rental;
    }

    public Integer getRentalAmt() {
        return rentalAmt;
    }

    public Double getCapping() {
        return capping;
    }

    public Integer getReferalID() {
        return referalID;
    }

    public int getRoleID() {
        return roleID;
    }

    public Double getCommRate() {
        return commRate;
    }

    public Integer getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public Integer getKycStatus() {
        return kycStatus;
    }

    public String getKycStatusStr() {
        return kycStatusStr;
    }

    public String getOutletName() {
        return outletName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getOTP() {
        return isOTP;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public String getJoinBy() {
        return joinBy;
    }

    public String getSlab() {
        return slab;
    }

    public String getWebsiteName() {
        return websiteName;
    }

    public String getBalance() {
        return balance;
    }

    public Object getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public double getbCapping() {
        return bCapping;
    }
}
