package com.fintech.emoneyrechargeonlinenew.AppUser.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AscReport implements Serializable {
    @SerializedName("statusCode")
    @Expose
    private int statusCode;
    @SerializedName("status")
    @Expose
    private Object status;
    @SerializedName("opening")
    @Expose
    private double opening;
    @SerializedName("purchase")
    @Expose
    private double purchase;
    @SerializedName("sales")
    @Expose
    private double sales;
    @SerializedName("cCollection")
    @Expose
    private double cCollection;
    @SerializedName("closing")
    @Expose
    private double closing;
    @SerializedName("fundDeducted")
    @Expose
    private double fundDeducted;
    @SerializedName("return")
    @Expose
    private double _return;
    @SerializedName("requested")
    @Expose
    private double requested;
    @SerializedName("debited")
    @Expose
    private double debited;
    @SerializedName("debited2202")
    @Expose
    private double debited2202;
    @SerializedName("refunded")
    @Expose
    private double refunded;
    @SerializedName("commission")
    @Expose
    private double commission;
    @SerializedName("ccfCommission")
    @Expose
    private double ccfCommission;
    @SerializedName("surcharge")
    @Expose
    private double surcharge;
    @SerializedName("fundTransfered")
    @Expose
    private double fundTransfered;
    @SerializedName("otherCharge")
    @Expose
    private double otherCharge;
    @SerializedName("ccfCommDebited")
    @Expose
    private double ccfCommDebited;
    @SerializedName("expected")
    @Expose
    private double expected;
    @SerializedName("ccf")
    @Expose
    private double ccf;
    @SerializedName("roleID")
    @Expose
    private int roleID;
    @SerializedName("userID")
    @Expose
    private int userID;
    @SerializedName("outletName")
    @Expose
    private String outletName;
    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("setTarget")
    @Expose
    private int setTarget;
    @SerializedName("targetTillDate")
    @Expose
    private int targetTillDate;
    @SerializedName("isGift")
    @Expose
    private boolean isGift;
    @SerializedName("dCommission")
    @Expose
    private double dCommission;
    @SerializedName("lsale")
    @Expose
    private double lsale;
    @SerializedName("lCollection")
    @Expose
    private double lCollection;
    @SerializedName("lsDate")
    @Expose
    private String lsDate;
    @SerializedName("lcDate")
    @Expose
    private String lcDate;
    @SerializedName("isPrepaid")
    @Expose
    private boolean isPrepaid;
    @SerializedName("isUtility")
    @Expose
    private boolean isUtility;
    @SerializedName("balance")
    @Expose
    private double balance;
    @SerializedName("uBalance")
    @Expose
    private double uBalance;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public double getOpening() {
        return opening;
    }

    public void setOpening(double opening) {
        this.opening = opening;
    }

    public double getPurchase() {
        return purchase;
    }

    public void setPurchase(double purchase) {
        this.purchase = purchase;
    }

    public double getSales() {
        return sales;
    }

    public void setSales(double sales) {
        this.sales = sales;
    }

    public double getcCollection() {
        return cCollection;
    }

    public void setcCollection(double cCollection) {
        this.cCollection = cCollection;
    }

    public double getClosing() {
        return closing;
    }

    public void setClosing(double closing) {
        this.closing = closing;
    }

    public double getFundDeducted() {
        return fundDeducted;
    }

    public void setFundDeducted(double fundDeducted) {
        this.fundDeducted = fundDeducted;
    }

    public double get_return() {
        return _return;
    }

    public void set_return(double _return) {
        this._return = _return;
    }

    public double getRequested() {
        return requested;
    }

    public void setRequested(double requested) {
        this.requested = requested;
    }

    public double getDebited() {
        return debited;
    }

    public void setDebited(double debited) {
        this.debited = debited;
    }

    public double getDebited2202() {
        return debited2202;
    }

    public void setDebited2202(double debited2202) {
        this.debited2202 = debited2202;
    }

    public double getRefunded() {
        return refunded;
    }

    public void setRefunded(double refunded) {
        this.refunded = refunded;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public double getCcfCommission() {
        return ccfCommission;
    }

    public void setCcfCommission(double ccfCommission) {
        this.ccfCommission = ccfCommission;
    }

    public double getSurcharge() {
        return surcharge;
    }

    public void setSurcharge(double surcharge) {
        this.surcharge = surcharge;
    }

    public double getFundTransfered() {
        return fundTransfered;
    }

    public void setFundTransfered(double fundTransfered) {
        this.fundTransfered = fundTransfered;
    }

    public double getOtherCharge() {
        return otherCharge;
    }

    public void setOtherCharge(double otherCharge) {
        this.otherCharge = otherCharge;
    }

    public double getCcfCommDebited() {
        return ccfCommDebited;
    }

    public void setCcfCommDebited(double ccfCommDebited) {
        this.ccfCommDebited = ccfCommDebited;
    }

    public double getExpected() {
        return expected;
    }

    public void setExpected(double expected) {
        this.expected = expected;
    }

    public double getCcf() {
        return ccf;
    }

    public void setCcf(double ccf) {
        this.ccf = ccf;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getOutletName() {
        return outletName;
    }

    public void setOutletName(String outletName) {
        this.outletName = outletName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getSetTarget() {
        return setTarget;
    }

    public void setSetTarget(int setTarget) {
        this.setTarget = setTarget;
    }

    public int getTargetTillDate() {
        return targetTillDate;
    }

    public void setTargetTillDate(int targetTillDate) {
        this.targetTillDate = targetTillDate;
    }

    public boolean isGift() {
        return isGift;
    }

    public void setGift(boolean gift) {
        isGift = gift;
    }

    public double getdCommission() {
        return dCommission;
    }

    public void setdCommission(double dCommission) {
        this.dCommission = dCommission;
    }

    public double getLsale() {
        return lsale;
    }

    public void setLsale(double lsale) {
        this.lsale = lsale;
    }

    public double getlCollection() {
        return lCollection;
    }

    public void setlCollection(double lCollection) {
        this.lCollection = lCollection;
    }

    public String getLsDate() {
        return lsDate;
    }

    public void setLsDate(String lsDate) {
        this.lsDate = lsDate;
    }

    public String getLcDate() {
        return lcDate;
    }

    public void setLcDate(String lcDate) {
        this.lcDate = lcDate;
    }

    public boolean isPrepaid() {
        return isPrepaid;
    }

    public void setPrepaid(boolean prepaid) {
        isPrepaid = prepaid;
    }

    public boolean isUtility() {
        return isUtility;
    }

    public void setUtility(boolean utility) {
        isUtility = utility;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getuBalance() {
        return uBalance;
    }

    public void setuBalance(double uBalance) {
        this.uBalance = uBalance;
    }
}
