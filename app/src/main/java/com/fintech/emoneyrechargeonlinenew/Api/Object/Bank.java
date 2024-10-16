package com.fintech.emoneyrechargeonlinenew.Api.Object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.fintech.emoneyrechargeonlinenew.Util.PaymentMode;

import java.io.Serializable;
import java.util.List;

public class Bank implements Serializable {

    @SerializedName("bankName")
    @Expose
    public String bankName;
    @SerializedName("logo")
    @Expose
    public String logo;
    @SerializedName("bankQRLogo")
    @Expose
    public String bankQRLogo;
    @SerializedName("cid")
    @Expose
    public String cid;
    @SerializedName("entryBy")
    @Expose
    public int entryBy;
    @SerializedName("lt")
    @Expose
    public int lt;
    @SerializedName("bankMasters")
    @Expose
    public String bankMasters;
    @SerializedName("qrPath")
    @Expose
    public String qrPath;
    @SerializedName("preStatusofQR")
    @Expose
    public int preStatusofQR;
    @SerializedName("mode")
    @Expose
    public List<PaymentMode> mode = null;
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("branchName")
    @Expose
    public String branchName;
    @SerializedName("accountHolder")
    @Expose
    public String accountHolder;
    @SerializedName("accountNo")
    @Expose
    public String accountNo;
    @SerializedName("isqrenable")
    @Expose
    public boolean isqrenable;
    @SerializedName("neftStatus")
    @Expose
    public boolean neftStatus;
    @SerializedName("neftID")
    @Expose
    public int neftID;
    @SerializedName("rtgsStatus")
    @Expose
    public boolean rtgsStatus;
    @SerializedName("rtgsid")
    @Expose
    public int rtgsid;
    @SerializedName("impsStatus")
    @Expose
    public boolean impsStatus;
    @SerializedName("impsid")
    @Expose
    public int impsid;
    @SerializedName("thirdPartyTransferStatus")
    @Expose
    public boolean thirdPartyTransferStatus;
    @SerializedName("thirdPartyTransferID")
    @Expose
    public int thirdPartyTransferID;
    @SerializedName("cashDepositStatus")
    @Expose
    public boolean cashDepositStatus;
    @SerializedName("cashDepositID")
    @Expose
    public int cashDepositID;
    @SerializedName("gccStatus")
    @Expose
    public boolean gccStatus;
    @SerializedName("gccid")
    @Expose
    public int gccid;
    @SerializedName("chequeStatus")
    @Expose
    public boolean chequeStatus;
    @SerializedName("chequeID")
    @Expose
    public int chequeID;
    @SerializedName("scanPayStatus")
    @Expose
    public boolean scanPayStatus;
    @SerializedName("scanPayID")
    @Expose
    public int scanPayID;
    @SerializedName("upiStatus")
    @Expose
    public boolean upiStatus;
    @SerializedName("upiid")
    @Expose
    public int upiid;
    @SerializedName("exchangeStatus")
    @Expose
    public boolean exchangeStatus;
    @SerializedName("exchangeID")
    @Expose
    public int exchangeID;
    @SerializedName("ifscCode")
    @Expose
    public String ifscCode;
    @SerializedName("charge")
    @Expose
    public double charge;
    @SerializedName("rImageUrl")
    @Expose
    public String rImageUrl;
    @SerializedName("isbankLogoAvailable")
    @Expose
    public boolean isbankLogoAvailable;
    @SerializedName(value = "UPINUmber",alternate = {"UPINumber","upinumber","upinUmber"})
    @Expose
    public String upinumber;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBankQRLogo() {
        return bankQRLogo;
    }

    public void setBankQRLogo(String bankQRLogo) {
        this.bankQRLogo = bankQRLogo;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public int getEntryBy() {
        return entryBy;
    }

    public void setEntryBy(int entryBy) {
        this.entryBy = entryBy;
    }

    public int getLt() {
        return lt;
    }

    public void setLt(int lt) {
        this.lt = lt;
    }

    public String getBankMasters() {
        return bankMasters;
    }

    public void setBankMasters(String bankMasters) {
        this.bankMasters = bankMasters;
    }

    public String getQrPath() {
        return qrPath;
    }

    public void setQrPath(String qrPath) {
        this.qrPath = qrPath;
    }

    public int getPreStatusofQR() {
        return preStatusofQR;
    }

    public void setPreStatusofQR(int preStatusofQR) {
        this.preStatusofQR = preStatusofQR;
    }

    public List<PaymentMode> getMode() {
        return mode;
    }

    public void setMode(List<PaymentMode> mode) {
        this.mode = mode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public boolean getIsqrenable() {
        return isqrenable;
    }

    public void setIsqrenable(boolean isqrenable) {
        this.isqrenable = isqrenable;
    }

    public boolean getIsNeftStatus() {
        return neftStatus;
    }

    public void setNeftStatus(boolean neftStatus) {
        this.neftStatus = neftStatus;
    }

    public int getNeftID() {
        return neftID;
    }

    public void setNeftID(int neftID) {
        this.neftID = neftID;
    }

    public boolean getIsRtgsStatus() {
        return rtgsStatus;
    }

    public void setRtgsStatus(boolean rtgsStatus) {
        this.rtgsStatus = rtgsStatus;
    }

    public int getRtgsid() {
        return rtgsid;
    }

    public void setRtgsid(int rtgsid) {
        this.rtgsid = rtgsid;
    }

    public boolean getIsImpsStatus() {
        return impsStatus;
    }

    public void setImpsStatus(boolean impsStatus) {
        this.impsStatus = impsStatus;
    }

    public int getImpsid() {
        return impsid;
    }

    public void setImpsid(int impsid) {
        this.impsid = impsid;
    }

    public boolean getIsThirdPartyTransferStatus() {
        return thirdPartyTransferStatus;
    }

    public void setThirdPartyTransferStatus(boolean thirdPartyTransferStatus) {
        this.thirdPartyTransferStatus = thirdPartyTransferStatus;
    }

    public int getThirdPartyTransferID() {
        return thirdPartyTransferID;
    }

    public void setThirdPartyTransferID(int thirdPartyTransferID) {
        this.thirdPartyTransferID = thirdPartyTransferID;
    }

    public boolean getIsCashDepositStatus() {
        return cashDepositStatus;
    }

    public void setCashDepositStatus(boolean cashDepositStatus) {
        this.cashDepositStatus = cashDepositStatus;
    }

    public int getCashDepositID() {
        return cashDepositID;
    }

    public void setCashDepositID(int cashDepositID) {
        this.cashDepositID = cashDepositID;
    }

    public boolean getIsGccStatus() {
        return gccStatus;
    }

    public void setGccStatus(boolean gccStatus) {
        this.gccStatus = gccStatus;
    }

    public int getGccid() {
        return gccid;
    }

    public void setGccid(int gccid) {
        this.gccid = gccid;
    }

    public boolean getIsChequeStatus() {
        return chequeStatus;
    }

    public void setChequeStatus(boolean chequeStatus) {
        this.chequeStatus = chequeStatus;
    }

    public int getChequeID() {
        return chequeID;
    }

    public void setChequeID(int chequeID) {
        this.chequeID = chequeID;
    }

    public boolean getIsScanPayStatus() {
        return scanPayStatus;
    }

    public void setScanPayStatus(boolean scanPayStatus) {
        this.scanPayStatus = scanPayStatus;
    }

    public int getScanPayID() {
        return scanPayID;
    }

    public void setScanPayID(int scanPayID) {
        this.scanPayID = scanPayID;
    }

    public boolean getIsUpiStatus() {
        return upiStatus;
    }

    public void setUpiStatus(boolean upiStatus) {
        this.upiStatus = upiStatus;
    }

    public int getUpiid() {
        return upiid;
    }

    public void setUpiid(int upiid) {
        this.upiid = upiid;
    }

    public boolean getIsExchangeStatus() {
        return exchangeStatus;
    }

    public void setExchangeStatus(boolean exchangeStatus) {
        this.exchangeStatus = exchangeStatus;
    }

    public int getExchangeID() {
        return exchangeID;
    }

    public void setExchangeID(int exchangeID) {
        this.exchangeID = exchangeID;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    public String getrImageUrl() {
        return rImageUrl;
    }

    public void setrImageUrl(String rImageUrl) {
        this.rImageUrl = rImageUrl;
    }

    public boolean getIsbankLogoAvailable() {
        return isbankLogoAvailable;
    }

    public void setIsbankLogoAvailable(boolean isbankLogoAvailable) {
        this.isbankLogoAvailable = isbankLogoAvailable;
    }

    public String getUpinumber() {
        return upinumber;
    }

}