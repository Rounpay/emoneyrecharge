package com.fintech.emoneyrechargeonlinenew.Util;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaDrm;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;
import android.provider.Settings;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.fintech.emoneyrechargeonlinenew.AEPS.FingPay.UI.EKycDialogFragment;
import com.fintech.emoneyrechargeonlinenew.AEPS.FingPay.dto.GenerateDepositOTPRequest;
import com.fintech.emoneyrechargeonlinenew.AEPS.FingPay.dto.GenerateDepositOTPResponse;
import com.fintech.emoneyrechargeonlinenew.AEPS.FingPay.dto.GetAEPSResponse;
import com.fintech.emoneyrechargeonlinenew.AEPS.FingPay.dto.GetAepsRequest;
import com.fintech.emoneyrechargeonlinenew.AEPS.FingPay.dto.PidData;
import com.fintech.emoneyrechargeonlinenew.AccountOpen.dto.AccountOpenListRequest;
import com.fintech.emoneyrechargeonlinenew.AccountOpen.dto.AccountOpenListResponse;
import com.fintech.emoneyrechargeonlinenew.AccountSettlement.API.SettlementAccountResponse;
import com.fintech.emoneyrechargeonlinenew.AccountSettlement.API.UpdateSettlementAccountRequest;
import com.fintech.emoneyrechargeonlinenew.Activities.InsurenceWebview;
import com.fintech.emoneyrechargeonlinenew.Activities.MoveToWalletActivity;
import com.fintech.emoneyrechargeonlinenew.Api.Object.BalanceType;
import com.fintech.emoneyrechargeonlinenew.Api.Object.SlabDetailDisplayLvl;
import com.fintech.emoneyrechargeonlinenew.Api.Request.AchieveTargetRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.CallBackRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.FundTransferRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.IncentiveDetailRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.RealApiChangeRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.SubmitSocialDetailsRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.UpdateBankRequest;
import com.fintech.emoneyrechargeonlinenew.AppUser.dto.ASPayCollectRequest;
import com.fintech.emoneyrechargeonlinenew.AppUser.dto.AppGetAMRequest;
import com.fintech.emoneyrechargeonlinenew.AppUser.dto.AppGetAMResponse;
import com.fintech.emoneyrechargeonlinenew.AppUser.dto.FosAccStmtAndCollReportRequest;
import com.fintech.emoneyrechargeonlinenew.AppUser.dto.FosAccStmtAndCollReportResponse;
import com.fintech.emoneyrechargeonlinenew.BuildConfig;
import com.fintech.emoneyrechargeonlinenew.CommissionSlab.dto.RSlabRangDetailRequest;
import com.fintech.emoneyrechargeonlinenew.CommissionSlab.dto.RSlabRangDetailResponse;
import com.fintech.emoneyrechargeonlinenew.CommissionSlab.ui.CommissionSlabDetailAdapter;
import com.fintech.emoneyrechargeonlinenew.DMRPipe.dto.ValiSenderRequest;
import com.fintech.emoneyrechargeonlinenew.DTHSubscription.dto.DTHSubscriptionRequest;
import com.fintech.emoneyrechargeonlinenew.DTHSubscription.dto.DthPackage;
import com.fintech.emoneyrechargeonlinenew.DTHSubscription.dto.DthSubscriptionReportRequest;
import com.fintech.emoneyrechargeonlinenew.DTHSubscription.dto.DthSubscriptionReportResponse;
import com.fintech.emoneyrechargeonlinenew.DTHSubscription.dto.GetDthPackageChannelRequest;
import com.fintech.emoneyrechargeonlinenew.DTHSubscription.dto.GetDthPackageRequest;
import com.fintech.emoneyrechargeonlinenew.DTHSubscription.dto.GetDthPackageResponse;
import com.fintech.emoneyrechargeonlinenew.DTHSubscription.dto.PincodeAreaRequest;
import com.fintech.emoneyrechargeonlinenew.DTHSubscription.dto.PincodeAreaResponse;
import com.fintech.emoneyrechargeonlinenew.DashBoard.CustomAllTypeService;
import com.fintech.emoneyrechargeonlinenew.DashBoard.UI.DashBoardLayout;
import com.fintech.emoneyrechargeonlinenew.MoveToWallet.Api.MoveToBankReportRequest;
import com.fintech.emoneyrechargeonlinenew.MoveToWallet.Api.MoveToBankReportResponse;
import com.fintech.emoneyrechargeonlinenew.PSA.ui.PanApplicationActivity;
import com.fintech.emoneyrechargeonlinenew.QRScan.dto.GetVAResponse;
import com.fintech.emoneyrechargeonlinenew.QRScan.dto.UPIPaymentReq;
import com.fintech.emoneyrechargeonlinenew.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.fintech.emoneyrechargeonlinenew.Activities.AEPSReportRequest;
import com.fintech.emoneyrechargeonlinenew.Activities.AEPSResponse;
import com.fintech.emoneyrechargeonlinenew.Activities.AddMoneyActivity;
import com.fintech.emoneyrechargeonlinenew.Activities.BrowsePlan.dto.ResponsePlan;
import com.fintech.emoneyrechargeonlinenew.Activities.BrowsePlan.ui.BrowsePlanScreen;
import com.fintech.emoneyrechargeonlinenew.Activities.ROffer.UI.ROffer;
import com.fintech.emoneyrechargeonlinenew.Activities.ROffer.dto.RofferResponse;
import com.fintech.emoneyrechargeonlinenew.Activities.UserDayBookActivity;
import com.fintech.emoneyrechargeonlinenew.Api.Object.AssignedOpType;
import com.fintech.emoneyrechargeonlinenew.Api.Object.BeneDetail;
import com.fintech.emoneyrechargeonlinenew.Api.Object.DmrRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Object.NumberList;
import com.fintech.emoneyrechargeonlinenew.Api.Request.AddFundRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.BalanceRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.BasicRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.ChangePinPasswordRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.DFStatusRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.FetchBillRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.FundDCReportRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.GetChargedAmountRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.GetDMTReceiptRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.GetSenderRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.IntiateUPIRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.LedgerReportRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.LogoutRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.MoveToWalletRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.NewsRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.OnboardingRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.PurchaseTokenRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.RefundLogRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.UpdateFcmRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.UpdateUPIRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.UpgradePackageRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Request.UserDayBookRequest;
import com.fintech.emoneyrechargeonlinenew.Api.Response.AppUserListResponse;
import com.fintech.emoneyrechargeonlinenew.Api.Response.BalanceResponse;
import com.fintech.emoneyrechargeonlinenew.Api.Response.BankListResponse;
import com.fintech.emoneyrechargeonlinenew.Api.Response.BasicResponse;
import com.fintech.emoneyrechargeonlinenew.Api.Response.DFStatusResponse;
import com.fintech.emoneyrechargeonlinenew.Api.Response.DMTReceiptResponse;
import com.fintech.emoneyrechargeonlinenew.Api.Response.FetchBillResponse;
import com.fintech.emoneyrechargeonlinenew.Api.Response.FundreqToResponse;
import com.fintech.emoneyrechargeonlinenew.Api.Response.GetAvailablePackageResponse;
import com.fintech.emoneyrechargeonlinenew.Api.Response.GetBankAndPaymentModeResponse;
import com.fintech.emoneyrechargeonlinenew.Api.Response.InitiateUpiResponse;
import com.fintech.emoneyrechargeonlinenew.Api.Response.NumberListResponse;
import com.fintech.emoneyrechargeonlinenew.Api.Response.OnboardingResponse;
import com.fintech.emoneyrechargeonlinenew.Api.Response.RechargeReportResponse;
import com.fintech.emoneyrechargeonlinenew.Api.Response.SlabCommissionResponse;
import com.fintech.emoneyrechargeonlinenew.Api.Response.TransactionModeResponse;
import com.fintech.emoneyrechargeonlinenew.Api.Response.WalletTypeResponse;
import com.fintech.emoneyrechargeonlinenew.Auth.LoginActivity;
import com.fintech.emoneyrechargeonlinenew.Auth.dto.LoginResponse;
import com.fintech.emoneyrechargeonlinenew.CommissionSlab.ui.CommissionScreen;
import com.fintech.emoneyrechargeonlinenew.DMROld.ui.DMRReciept;
import com.fintech.emoneyrechargeonlinenew.Fragments.dto.OperatorList;
import com.fintech.emoneyrechargeonlinenew.Splash.Splash;
import com.fintech.emoneyrechargeonlinenew.usefull.CustomLoader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.NetworkInterface;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public enum UtilMethods {

    INSTANCE;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    EditText edMobileOtp;
    public String deviceId;
    public double getLongitude;
    public double getLattitude;
    AlertDialog alertDialogVersion,alertDialog;
    CustomAlertDialog customPassDialog;
    AlertDialog alertDialogSlabRange;
    Dialog dialogOTP;
    private AlertDialog alertDialogMobile;
    public int ERROR_NETWORK = 2;
    public int ERROR_OTHER=1;
    private RequestOptions requestOptions;
    public  boolean isSocialEmailVerfiedChecked,isBioMetricDialogShown;
    BottomSheetDialog socialAlertBottomSheetDialog;
    private CountDownTimer countDownTimer;
    private String onboardingOTPReffId;

    public String getUPILogoFromVPAStr(String upi){
        String vpaStr="";
        if(upi!=null && upi.contains("@"))
        {
            vpaStr = upi.substring(upi.lastIndexOf("@") + 1);

        }
        return vpaStr;
    }

    public boolean validateUPI(String upi){
        final Pattern VALID_UPI_ID_REGEX = Pattern.compile("^(.+)@(.+)$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_UPI_ID_REGEX.matcher(upi);
        return matcher.find();
    }

    public String getUniqueId(String userId){
        String clientUniqueId="";
        int randomAlphaCount=12;

        String NumericString = "0123456789";

        if(userId!=null && !userId.isEmpty()){
            int length= userId.length();
            randomAlphaCount=12-length;
        }

        StringBuilder stringBuilder = new StringBuilder(randomAlphaCount);

        for (int i = 0; i < randomAlphaCount; i++) {
            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index = (int)(NumericString.length() * Math.random());

            // add Character one by one in end of sb
            stringBuilder.append(NumericString
                    .charAt(index));
        }

        clientUniqueId=userId+stringBuilder.toString();
        return clientUniqueId;
    }

    public static final String md5Convertor(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte[] messageDigest = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public boolean isPackageInstalled(String packagename, PackageManager packageManager) {
        try {
            packageManager.getPackageInfo(packagename, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public String getRecentLogin(Context context) {
        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        String key = myPrefs.getString(ApplicationConstant.INSTANCE.regRecentLoginPref, null);
        return key;
    }

    public String getRoleID(Context context) {

        String loginPref = getLoginPref(context);
        LoginResponse loginResponse = new Gson().fromJson(loginPref, LoginResponse.class);
        return loginResponse.getData().getRoleID();
    }

    public void setRecentLogin(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.regRecentLoginPref, key);
        editor.commit();
    }

    public ArrayList<BalanceType> getUserActiveBalance(BalanceResponse balanceCheckResponse) {
        ArrayList<BalanceType> mBalanceTypes=new ArrayList<>();
        if (balanceCheckResponse != null) {
            if (balanceCheckResponse.getBalanceData().isBalance()) {
                mBalanceTypes.add(new BalanceType("Prepaid Wallet", balanceCheckResponse.getBalanceData().getBalance() + ""));
            }
            if (balanceCheckResponse.getBalanceData().isUBalance()) {
                mBalanceTypes.add(new BalanceType("Utility Wallet", balanceCheckResponse.getBalanceData().getuBalance() + ""));
            }
            if (balanceCheckResponse.getBalanceData().isBBalance()) {
                mBalanceTypes.add(new BalanceType("Bank Wallet", balanceCheckResponse.getBalanceData().getbBalance() + ""));
            }
            if (balanceCheckResponse.getBalanceData().isCBalance()) {
                mBalanceTypes.add(new BalanceType("Card Wallet", balanceCheckResponse.getBalanceData().getcBalance() + ""));
            }
            if (balanceCheckResponse.getBalanceData().isIDBalance()) {
                mBalanceTypes.add(new BalanceType("Registration Wallet", balanceCheckResponse.getBalanceData().getIdBalnace() + ""));
            }
            if (balanceCheckResponse.getBalanceData().isAEPSBalance()) {
                mBalanceTypes.add(new BalanceType("Aeps Wallet", balanceCheckResponse.getBalanceData().getAepsBalnace() + ""));
            }
            if (balanceCheckResponse.getBalanceData().isPacakgeBalance()) {
                mBalanceTypes.add(new BalanceType("Package Wallet", balanceCheckResponse.getBalanceData().getPacakgeBalance()+""));
            }

        }

        return mBalanceTypes;
    }

    public void NumberList(final Activity context, final CustomLoader loader,final  ApiCallBack apiCallBack) {
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<NumberListResponse> call = git.GetNumberList(new NunberListRequest(
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context)));
            call.enqueue(new Callback<NumberListResponse>() {
                @Override
                public void onResponse(Call<NumberListResponse> call, final retrofit2.Response<NumberListResponse> response) {

                    try {
                        if (loader != null && loader.isShowing()) {
                            loader.dismiss();
                        }
                        if (response.body() != null && response.body().getStatuscode() != null) {
                            if (response.body().getStatuscode().equalsIgnoreCase("1")) {
                                setNumberList(context, new Gson().toJson(response.body()),response.body().isTakeCustomerNo());

                                ArrayList<OperatorList> mOperatorLists = new ArrayList<>();
                                for (OperatorList op : response.body().getData().getOperators()) {
                                    if (op.getOpType()==14 && op.isActive()) {
                                        mOperatorLists.add(op);
                                    }
                                }

                                setDMTOperatorList(context, mOperatorLists);

                                if(apiCallBack!=null)
                                    apiCallBack.onSucess(response.body());
                            } else {
                                if (response.body().getMsg() != null) {
                                    Error(context, response.body().getMsg());
                                } else {
                                    Error(context, context.getResources().getString(R.string.err_something_went_wrong));
                                }

                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Error(context, e.getMessage());
                       if (loader != null && loader.isShowing()) {
                            loader.dismiss();
                        }
                    }

                }

                @Override
                public void onFailure(Call<NumberListResponse> call, Throwable t) {
                    if (loader != null && loader.isShowing()) {
                            loader.dismiss();
                        }
                    try {
                        if (t.getMessage() != null && !t.getMessage().isEmpty()) {

                            if (t.getMessage().contains("No address associated with hostname")) {
                                NetworkError(context, context.getResources().getString(R.string.err_msg_network_title),
                                        context.getResources().getString(R.string.err_msg_network));
                            } else {
                                Error(context, t.getMessage());

                            }

                        } else {
                            Error(context, context.getResources().getString(R.string.some_thing_error));

                        }
                    } catch (IllegalStateException ise) {
                        Error(context, ise.getMessage());

                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
           if (loader != null && loader.isShowing()) {
                            loader.dismiss();
                        }
        }
    }

    public void secureLogin(final Activity context, String mobile, final String password, final CustomLoader loader) {
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<LoginResponse> call = git.secureLogin(new LoginRequest(1, mobile, password,
                    ApplicationConstant.INSTANCE.Domain,
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context)));
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, final retrofit2.Response<LoginResponse> response) {
                    try {
                        if (response.body() != null && response.body().getStatuscode() != null) {
                            if (response.body().getStatuscode().equalsIgnoreCase("1")) {
                                UtilMethods.INSTANCE.setDTHInfoPref(context, response.body().getIsDTHInfo());
                                UtilMethods.INSTANCE.setROfferPref(context, response.body().getIsRoffer());
                                UtilMethods.INSTANCE.setIsDTHInfoAutoCall(context, response.body().isDTHInfoCall());
                                UtilMethods.INSTANCE.setIsShowPDFPlan(context, response.body().isShowPDFPlan());
                                UtilMethods.INSTANCE.setIsAutoBilling(context, response.body().isAutoBilling());
                                UtilMethods.INSTANCE.setRealApiPref(context, response.body().isRealAPIPerTransaction());
                                UtilMethods.INSTANCE.setDenoIncentivePref(context, response.body().isDenominationIncentive());
                                UtilMethods.INSTANCE.setIsReferral(context, response.body().isReferral());

                                UtilMethods.INSTANCE.setDoubleFactorPref(context, response.body().getData().isDoubleFactor());
                                UtilMethods.INSTANCE.setPinRequiredPref(context, response.body().getData().isPinRequired());
                                UtilMethods.INSTANCE.setWIDPref(context, response.body().getData().getWid());
                                setLoginPref(context, response.body().getData().getMobileNo(), new Gson().toJson(response.body()));
                                updateFcm(context);
                                UtilMethods.INSTANCE.GetActiveService(context, new UtilMethods.ApiCallBackTwoMethod() {
                                    @Override
                                    public void onSucess(Object object) {
                                        if(object instanceof CustomAllTypeService){
                                            CustomAllTypeService apiData= (CustomAllTypeService)object;

                                            if (loader != null && loader.isShowing()) {
                                                    loader.dismiss();
                                            }

                                            ((LoginActivity) context).startDashboard();

                                        }
                                    }

                                    @Override
                                    public void onError(Object object) {
                                        if (loader != null && loader.isShowing()) {
                                            loader.dismiss();
                                        }
                                        ((LoginActivity) context).startDashboard();
                                    }
                                });



                            } else if (response.body().getStatuscode().equalsIgnoreCase("2")) {
                                openOTPDialog(context,  new DialogCallBack() {
                                    @Override
                                    public void onPositiveClick(String value) {
                                        loader.show();
                                        ValidateOTP(context, value, response.body().getOtpSession(), 1, mobile, loader);
                                    }

                                    @Override
                                    public void onCancelClick() {

                                    }
                                });


                            } else if (response.body().getStatuscode().equalsIgnoreCase("-1")) {
                                if (response.body().getIsVersionValid() != null && response.body().getIsVersionValid().equalsIgnoreCase("false")) {
                                    if (loader != null && loader.isShowing()) {
                                        loader.dismiss();
                                    }
                                    versionDialog(context);
                                } else {
                                    if (loader != null && loader.isShowing()) {
                                        loader.dismiss();
                                    }
                                    Error(context, response.body().getMsg() + "");
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {

                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }
                    try {
                        if (t.getMessage() != null && !t.getMessage().isEmpty()) {

                            if (t.getMessage().contains("No address associated with hostname")) {
                                NetworkError(context, context.getResources().getString(R.string.err_msg_network_title),
                                        context.getResources().getString(R.string.err_msg_network));
                            } else {
                                Error(context, t.getMessage());

                            }

                        } else {
                            Error(context, context.getResources().getString(R.string.some_thing_error));

                        }
                    } catch (IllegalStateException ise) {
                        Error(context, ise.getMessage());

                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            if (loader != null && loader.isShowing()) {
                loader.dismiss();
            }
        }
    }

    public void updateFcm(final Context context) {
        try {
            final String fcmId = getFCMRegKey(context);

            if (fcmId == null || fcmId.isEmpty()) {
                // Use FirebaseMessaging to get the new token
                FirebaseMessaging.getInstance().getToken()
                        .addOnSuccessListener(new OnSuccessListener<String>() {
                            @Override
                            public void onSuccess(String newToken) {
                                if (newToken != null && !newToken.isEmpty()) {
                                    // Save the new token
                                    setFCMRegKey(context, newToken);
                                    // Call the update method with the new token
                                    updateFcm(context, newToken);
                                } else {
                                    // If the new token is empty, call update with the current FCM ID (fcmId)
                                    updateFcm(context, fcmId);
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // If token retrieval fails, continue with the old token
                                updateFcm(context, fcmId);
                            }
                        });
            } else {
                // If fcmId is already available, call the update method
                updateFcm(context, fcmId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateFcm(final Context context, String fcmId) {
        try {

            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            Call<BasicResponse> call = git.UpdateFCMID(new UpdateFcmRequest(
                    LoginDataResponse.getData().getUserID(), LoginDataResponse.getData().getLoginTypeID(),
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession(), fcmId));

            call.enqueue(new Callback<BasicResponse>() {
                @Override
                public void onResponse(Call<BasicResponse> call, final retrofit2.Response<BasicResponse> response) {
                    // Log.e("activity_login", "is : " + new Gson().toJson(response.body()).toString());

                    try {
                        if (response.body() != null) {
                            if (response.body().getStatuscode() == 1) {

                            } else if (response.body().getStatuscode() == 2) {

                            } else if (response.body().getStatuscode() == -1) {

                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                }

                @Override
                public void onFailure(Call<BasicResponse> call, Throwable t) {


                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ForgotPass(final Activity context, String userId, final CustomLoader loader) {
        try {


            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);

            Call<BasicResponse> call = git.ForgetPassword(new LoginRequest(1, userId, "",
                    ApplicationConstant.INSTANCE.Domain,
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context)));

            call.enqueue(new Callback<BasicResponse>() {
                @Override
                public void onResponse(Call<BasicResponse> call, final retrofit2.Response<BasicResponse> response) {
                    // Log.e("activity_login", "is : " + new Gson().toJson(response.body()).toString());

                    try {
                        loader.dismiss();
                        if (response.body() != null) {
                            if (response.body().getStatuscode() == 1) {
                                Successful(context, response.body().getMsg());
                            } else if (response.body().getStatuscode() == 2) {
                                Failed(context, response.body().getMsg());
                            } else if (response.body().getStatuscode() == -1) {
                                Failed(context, response.body().getMsg());
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        loader.dismiss();
                        Error(context, e.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<BasicResponse> call, Throwable t) {
                    loader.dismiss();
                    try {
                        if (t.getMessage() != null && !t.getMessage().isEmpty()) {

                            if (t.getMessage().contains("No address associated with hostname")) {
                                NetworkError(context, context.getResources().getString(R.string.err_msg_network_title),
                                        context.getResources().getString(R.string.err_msg_network));
                            } else {
                                Error(context, t.getMessage());

                            }

                        } else {
                            Error(context, context.getResources().getString(R.string.some_thing_error));

                        }
                    } catch (IllegalStateException ise) {
                        Error(context, ise.getMessage());

                    }
                }
            });

        } catch (Exception e) {
            loader.dismiss();
            e.printStackTrace();
        }
    }

    public void Balancecheck(final Activity context, final CustomLoader loader, final ApiCallBack mApiCallBack) {
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            Call<BalanceResponse> call = git.Balancecheck(new BalanceRequest(LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getLoginTypeID() + "",
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));
            call.enqueue(new Callback<BalanceResponse>() {
                @Override
                public void onResponse(Call<BalanceResponse> call, final retrofit2.Response<BalanceResponse> response) {

                    try {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }

                        if (response.body() != null && response.body().getBalanceData() != null && response.body().getBalanceData().isPN()) {

                            CustomAlertDialog customPassDialog = new CustomAlertDialog(context, true);
                            customPassDialog.WarningWithSingleBtnCallBack(context.getResources().getString(R.string.pin_password_expired_msg), "Create", false, new CustomAlertDialog.DialogCallBack() {
                                @Override
                                public void onPositiveClick() {
                                    new ChangePassUtils(context).changePassword(true, false);
                                }

                                @Override
                                public void onNegativeClick() {

                                }
                            });

                        } else if (response.body() != null && response.body().getIsPasswordExpired()) {
                            CustomAlertDialog customPassDialog = new CustomAlertDialog(context, true);
                            customPassDialog.WarningWithSingleBtnCallBack(context.getResources().getString(R.string.password_expired_msg), "Change", false, new CustomAlertDialog.DialogCallBack() {
                                @Override
                                public void onPositiveClick() {
                                    new ChangePassUtils(context).changePassword(false, false);
                                }

                                @Override
                                public void onNegativeClick() {

                                }
                            });

                        }


                        if (response.body() != null && response.body().getStatuscode() != null) {

                            if (response.body().getStatuscode() == 1) {
                                if (mApiCallBack != null) {
                                    mApiCallBack.onSucess(response.body());
                                }
                                setBalanceCheck(context, new Gson().toJson(response.body()));
                                UtilMethods.INSTANCE.setDTHInfoPref(context, response.body().isDTHInfo());
                                UtilMethods.INSTANCE.setROfferPref(context, response.body().isRoffer());
                                UtilMethods.INSTANCE.setIsDTHInfoAutoCall(context, response.body().isDTHInfoCall());
                                UtilMethods.INSTANCE.setIsShowPDFPlan(context, response.body().isShowPDFPlan());
                                UtilMethods.INSTANCE.setIsLookUpFromAPI(context, response.body().isLookUpFromAPI());
                                UtilMethods.INSTANCE.setIsReferral(context, response.body().isReferral());
                               /* UtilMethods.INSTANCE.setSettlementAccountVerification(context, response.body().isSattlemntAccountVerify());*/

                            }  else if (response.body().getStatuscode() == -1) {
                                if (response.body().getVersionValid() != null && response.body().getVersionValid() == false) {
                                    versionDialog(context);
                                } else {
                                    Error(context, response.body().getMsg() + "");
                                }
                            }
                        }
                    } catch (Exception e) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                    }

                }

                @Override
                public void onFailure(Call<BalanceResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }
                    try {
                        if (t.getMessage() != null && !t.getMessage().isEmpty()) {

                            if (t.getMessage().contains("No address associated with hostname")) {
                                NetworkError(context, context.getResources().getString(R.string.err_msg_network_title),
                                        context.getResources().getString(R.string.err_msg_network));
                            } else {
                                Error(context, t.getMessage());

                            }

                        } else {
                            Error(context, context.getResources().getString(R.string.some_thing_error));

                        }
                    } catch (IllegalStateException ise) {
                        Error(context, ise.getMessage());
                    }

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void CallOnboarding(final Activity context, final boolean isPan,final int operatorId, final String otp,  String otpRefId, final String pidData, final int bioAuthType, boolean isBio, final FragmentManager fragmentManager, TextView timerTv, View resendCodeTv, final Dialog mDialog , final CustomLoader loader, final ApiCallBackTwoMethod mApiCallBack) {
        customPassDialog = new CustomAlertDialog(context, true);
        loader.show();
        EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
        String LoginResponse = getLoginPref(context);
        LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
        Call<OnboardingResponse> call = git.CallOnboarding(new OnboardingRequest(bioAuthType,isBio,otpRefId,pidData,String.valueOf(operatorId), otp, LoginDataResponse.getData().getOutletID() + "",
                LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getSessionID() + "", LoginDataResponse.getData().getSession() + "",
                ApplicationConstant.INSTANCE.APP_ID, getIMEI(context), getFCMRegKey(context), BuildConfig.VERSION_NAME,
                getSerialNo(context), LoginDataResponse.getData().getLoginTypeID() + ""));
        call.enqueue(new Callback<OnboardingResponse>() {
            @Override
            public void onResponse(Call<OnboardingResponse> call, Response<OnboardingResponse> response) {

                if (loader != null) {
                    if (loader.isShowing())
                        loader.dismiss();
                }
                try {
                    if(response.isSuccessful()){
                        if (response.body() != null) {
                            OnboardingResponse mOnBoardingResponse= response.body();
                            if (mOnBoardingResponse.getOtpRefID() != null && !mOnBoardingResponse.getOtpRefID().isEmpty()) {
                                onboardingOTPReffId = mOnBoardingResponse.getOtpRefID();
                            }

                                if (mOnBoardingResponse.isRedirectToExternal() && mOnBoardingResponse.getExternalURL() != null && !mOnBoardingResponse.getExternalURL().isEmpty()) {
                                    try {
                                        if (mDialog != null && mDialog.isShowing()) {
                                            mDialog.dismiss();
                                        }
                                        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse( /*ApplicationConstant.INSTANCE.baseUrl + */mOnBoardingResponse.getExternalURL())));
                                    } catch (android.content.ActivityNotFoundException anfe) {
                                        Intent dialIntent = new Intent(Intent.ACTION_VIEW);
                                        dialIntent.setData(Uri.parse(/*ApplicationConstant.INSTANCE.baseUrl + */mOnBoardingResponse.getExternalURL()));
                                        context.startActivity(dialIntent);
                                    }
                                }
                                else if (mOnBoardingResponse.isBioMetricRequired()) {

                                    if (mDialog != null && mDialog.isShowing()) {
                                        mDialog.dismiss();
                                    }

                                    if(mOnBoardingResponse.getStatuscode()==-1){
                                        Error(context, mOnBoardingResponse.getMsg() + "");
                                    }
                                    EKycDialogFragment mAEPSFingerPrintEKycDialog =
                                            new EKycDialogFragment();
                                    mAEPSFingerPrintEKycDialog.setOnBoardingData(context, fragmentManager, operatorId,
                                            onboardingOTPReffId, loader, LoginDataResponse,  mOnBoardingResponse.getBioAuthType(), mApiCallBack);
                                    mAEPSFingerPrintEKycDialog.show(fragmentManager, "");
                                    isBioMetricDialogShown=true;
                                }
                                else if (mOnBoardingResponse.isOTPRequired()) {

                                    if (mDialog != null && mDialog.isShowing()) {
                                        Successful(context, "OTP has been resend successfully");
                                        if (timerTv != null && resendCodeTv != null) {
                                            setTimer(timerTv, resendCodeTv);
                                        }
                                    }
                                    else {
                                        openOtpDialog(context, 7, LoginDataResponse.getData().getMobileNo(), new DialogOTPCallBack() {
                                            @Override
                                            public void onPositiveClick(EditText edMobileOtp, String otpValue, TextView timerTv, View resendCodeTv, Dialog mDialog) {
                                                loader.show();
                                                CallOnboarding(context, isPan, operatorId, otpValue, onboardingOTPReffId, pidData, mOnBoardingResponse.getBioAuthType(), false, fragmentManager, timerTv, resendCodeTv, mDialog, loader, mApiCallBack);


                                            }

                                            @Override
                                            public void onResetCallback(EditText edMobileOtp, String otpValue, TextView timerTv, View resendCodeTv, Dialog mDialog) {
                                                loader.show();
                                                CallOnboarding(context, isPan, operatorId, otpValue, onboardingOTPReffId, pidData, mOnBoardingResponse.getBioAuthType(), false, fragmentManager, timerTv, resendCodeTv, mDialog, loader, mApiCallBack);

                                            }


                                        });
                                    }
                                }
                                else {

                                    if (mDialog != null && mDialog.isShowing()) {
                                        mDialog.dismiss();
                                    }
                                    if (!showCallOnBoardingMsgs(context, mOnBoardingResponse, customPassDialog)) {
                                        if (mApiCallBack != null) {
                                            mApiCallBack.onError(mOnBoardingResponse);
                                        }
                                    } else if (mOnBoardingResponse.getStatuscode() == 1) {

                                        if(isBioMetricDialogShown && (mOnBoardingResponse.getSdkDetail()==null&& mOnBoardingResponse.getSdkType()==0)){

                                            CallOnboarding(context, isPan, operatorId, "", onboardingOTPReffId, pidData, mOnBoardingResponse.getBioAuthType(), false, fragmentManager, timerTv, resendCodeTv, mDialog, loader, mApiCallBack);
                                            isBioMetricDialogShown=false;
                                        }
                                        else{
                                            if(isPan){
                                                if (response.body().getPanid() != null && response.body().getPanid() != null) {
                                                    setPsaId(context, operatorId + "");
                                                    Intent i = new Intent(context, PanApplicationActivity.class);
                                                    i.putExtra("PANID", response.body().getPanid());
                                                    i.putExtra("outletId", LoginDataResponse.getData().getOutletID() + "");
                                                    i.putExtra("userId", LoginDataResponse.getData().getOutletID() + "");
                                                    i.putExtra("emailId", response.body().getEmailID());
                                                    i.putExtra("mobileNo", response.body().getMobileNo());
                                                    context.startActivity(i);


                                                } else {
                                                    Error(context, "Pan Id is not found!!");
                                                }
                                            }else{
                                                if (mApiCallBack != null) {
                                                    mOnBoardingResponse.setoId(operatorId + "");
                                                    mApiCallBack.onSucess(mOnBoardingResponse);
                                                }
                                            }

                                        }
                                    } else {
                                        if (!mOnBoardingResponse.isVersionValid()) {
                                            versionDialog(context);
                                        } else {
                                            Error(context,mOnBoardingResponse.getMsg()+"");
                                        }
                                    }
                                }

                        }
                        else {
                            Error(context, context.getResources().getString(R.string.some_thing_error));
                        }
                    }


                } catch (Exception ex) {
                    Error(context, ex.getMessage() + "");
                }


            }

            @Override
            public void onFailure(Call<OnboardingResponse> call, Throwable t) {
                if (loader != null) {
                    if (loader.isShowing())
                        loader.dismiss();
                }
                if ((t.getMessage() + "").contains("No address associated with hostname")) {
                    NetworkError(context, "Network Error!", context.getResources().getString(R.string.network_error));
                } else {
                    Error(context, t.getMessage() + "");
                }
            }
        });


    }

   public void openOtpDialog(final Activity context,int otpLength, String mobileNum, final DialogOTPCallBack mDialogCallBack) {

         if (dialogOTP != null && dialogOTP.isShowing()) {
            return;
        }
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.verifyotp, null);

        edMobileOtp = view.findViewById(R.id.ed_mobile_otp);
        final TextInputLayout tilMobileOtp = view.findViewById(R.id.til_mobile_otp);
        final Button okButton =view.findViewById(R.id.okButton);
        final Button cancelButton =view.findViewById(R.id.cancelButton);
       final TextView timerTv = view.findViewById(R.id.timer);
       final TextView resendTv = view.findViewById(R.id.resend);
        TextView numberTv = view.findViewById(R.id.number);
        numberTv.setText(mobileNum.replace(mobileNum.substring(0, 7), "XXXXXXX"));
       dialogOTP = new Dialog(context);
       dialogOTP.setCancelable(false);
       dialogOTP.setContentView(view);
       dialogOTP.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

       setTimer(timerTv, resendTv);

       cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogOTP.dismiss();
            }
        });



        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (edMobileOtp.getText().toString().isEmpty()) {
                tilMobileOtp.setError("Enter a Valid OTP");
                edMobileOtp.requestFocus();
            } else if (edMobileOtp.getText().toString().length()!=otpLength) {
                tilMobileOtp.setError("Enter a Valid OTP");
                edMobileOtp.requestFocus();
            } else {
                tilMobileOtp.setErrorEnabled(false);
                if (mDialogCallBack != null) {
                    mDialogCallBack.onPositiveClick(edMobileOtp,edMobileOtp.getText().toString(), timerTv, resendTv, dialogOTP);
                }
            }
            }
        });

        resendTv.setOnClickListener(v -> {
            if (edMobileOtp.getText().toString().isEmpty()) {
                tilMobileOtp.setError("Enter a Valid OTP");
                edMobileOtp.requestFocus();
            } else if (edMobileOtp.getText().toString().length()!=otpLength) {
                tilMobileOtp.setError("Enter a Valid OTP");
                edMobileOtp.requestFocus();
            } else {
                tilMobileOtp.setErrorEnabled(false);
                if (mDialogCallBack != null) {
                    mDialogCallBack.onResetCallback(edMobileOtp,edMobileOtp.getText().toString(), timerTv, resendTv, dialogOTP);
                }
            }
        });

        dialogOTP.setOnDismissListener(dialog1 -> {
            if (countDownTimer != null) {
                countDownTimer.cancel();
                countDownTimer = null;
            }
        });
       dialogOTP.show();
    }

    public void BalancecheckNew(final Activity context,final CustomLoader loader, CustomAlertDialog customAlertDialog, final ChangePassUtils mChangePassUtils,final ApiCallBack apiCallBack) {
        try {
            customPassDialog = customAlertDialog;
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            Call<BalanceResponse> call = git.Balancecheck(new BalanceRequest(LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getLoginTypeID() + "",
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));
            call.enqueue(new Callback<BalanceResponse>() {
                @Override
                public void onResponse(Call<BalanceResponse> call, final retrofit2.Response<BalanceResponse> response) {

                    try {
                        if (response.body() != null && response.body().getBalanceData() != null && response.body().getBalanceData().isPN()) {
                            if (customPassDialog != null) {
                                if (customPassDialog.returnDialog() != null && customPassDialog.returnDialog().isShowing()) {
                                    return;
                                }
                                customPassDialog.WarningWithSingleBtnCallBack(context.getResources().getString(R.string.pin_password_expired_msg), "Create", false, new CustomAlertDialog.DialogCallBack() {
                                    @Override
                                    public void onPositiveClick() {
                                        if (mChangePassUtils != null) {
                                            mChangePassUtils.changePassword(true, false);
                                        } else {
                                            new ChangePassUtils(context).changePassword(true, false);
                                        }

                                    }

                                    @Override
                                    public void onNegativeClick() {

                                    }
                                });
                            } else {
                                customPassDialog = new CustomAlertDialog(context, true);
                                customPassDialog.WarningWithSingleBtnCallBack(context.getResources().getString(R.string.pin_password_expired_msg), "Create", false, new CustomAlertDialog.DialogCallBack() {
                                    @Override
                                    public void onPositiveClick() {
                                        if (mChangePassUtils != null) {
                                            mChangePassUtils.changePassword(true, false);
                                        } else {
                                            new ChangePassUtils(context).changePassword(true, false);
                                        }
                                    }

                                    @Override
                                    public void onNegativeClick() {

                                    }
                                });
                            }

                        } else if (response.body() != null && response.body().getIsPasswordExpired()) {

                            if (customPassDialog != null) {
                                if (customPassDialog.returnDialog() != null && customPassDialog.returnDialog().isShowing()) {
                                    return;
                                }
                                customPassDialog.WarningWithSingleBtnCallBack(context.getResources().getString(R.string.password_expired_msg), "Change", false, new CustomAlertDialog.DialogCallBack() {
                                    @Override
                                    public void onPositiveClick() {
                                        if (mChangePassUtils != null) {
                                            mChangePassUtils.changePassword(false, false);
                                        } else {
                                            new ChangePassUtils(context).changePassword(false, false);
                                        }
                                    }

                                    @Override
                                    public void onNegativeClick() {

                                    }
                                });
                            } else {
                                customPassDialog = new CustomAlertDialog(context, true);
                                customPassDialog.WarningWithSingleBtnCallBack(context.getResources().getString(R.string.password_expired_msg), "Change", false, new CustomAlertDialog.DialogCallBack() {
                                    @Override
                                    public void onPositiveClick() {
                                        if (mChangePassUtils != null) {
                                            mChangePassUtils.changePassword(false, false);
                                        } else {
                                            new ChangePassUtils(context).changePassword(false, false);
                                        }
                                    }

                                    @Override
                                    public void onNegativeClick() {

                                    }
                                });
                            }
                        }else if (!isSocialEmailVerfiedChecked) {
                            if (!UtilMethods.INSTANCE.getEmailVerifiedPref(context) ||
                                    !UtilMethods.INSTANCE.getSocialLinkSavedPref(context)) {
                                long time = UtilMethods.INSTANCE.hourDifference(UtilMethods.INSTANCE.getSocialorEmailDialogTimePref(context), System.currentTimeMillis());
                                if (time >= 8) {
                                    CheckFlagsEmail(context, loader, LoginDataResponse);
                                }
                            }
                            isSocialEmailVerfiedChecked = true;
                        }


                        if (response.body() != null && response.body().getStatuscode() != null) {

                            if (response.body().getStatuscode() == 1) {
                                if(apiCallBack!=null)
                                {
                                    apiCallBack.onSucess(response.body());
                                }
                                setBalanceCheck(context, new Gson().toJson(response.body()));
                                UtilMethods.INSTANCE.setIsLookUpFromAPI(context, response.body().isLookUpFromAPI());
                                UtilMethods.INSTANCE.setDTHInfoPref(context, response.body().isDTHInfo());
                                UtilMethods.INSTANCE.setROfferPref(context, response.body().isRoffer());
                                UtilMethods.INSTANCE.setIsDTHInfoAutoCall(context, response.body().isDTHInfoCall());
                                UtilMethods.INSTANCE.setIsShowPDFPlan(context, response.body().isShowPDFPlan());
                                UtilMethods.INSTANCE.setIsReferral(context, response.body().isReferral());
                                UtilMethods.INSTANCE.setIsFlatCommission(context, response.body().isFlatCommission());
                               /* UtilMethods.INSTANCE.setSettlementAccountVerification(context, response.body().isSattlemntAccountVerify());*/

                                long time = hourDifference(getBalanceLowTime(context), System.currentTimeMillis());
                                if (response.body().getBalanceData().isLowBalance() && time >= 1) {
                                    if (customPassDialog != null) {
                                        if (customPassDialog.returnDialog() != null && customPassDialog.returnDialog().isShowing()) {
                                            return;
                                        } else {
                                            setBalanceLowTime(context, System.currentTimeMillis());

                                            String msg = "Your Balance is low.<br>Current Balance - " + context.getResources().getString(R.string.rupiya) + " " + response.body().getBalanceData().getBalance();customPassDialog.WarningWithCallBack(msg, "Low Balance", "Add Money", true, new CustomAlertDialog.DialogCallBack() {
                                                @Override
                                                public void onPositiveClick() {
                                                    Intent i = new Intent(context, AddMoneyActivity.class);
                                                    i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                                    context.startActivity(i);
                                                }

                                                @Override
                                                public void onNegativeClick() {

                                                }
                                            });
                                        }
                                    }
                                }
                            } else if (response.body().getStatuscode() == 2) {
                                //   openOTPDialog(context,loader,password);
                            } else if (response.body().getStatuscode() == -1) {
                                if (response.body().getVersionValid() != null && response.body().getVersionValid() == false) {
                                    versionDialog(context);
                                } else {
                                    Error(context, response.body().getMsg() + "");
                                }
                            }
                        }
                    } catch (Exception e) {

                    }

                }

                @Override
                public void onFailure(Call<BalanceResponse> call, Throwable t) {

                    try {
                        if (t.getMessage() != null && !t.getMessage().isEmpty()) {

                            if (t.getMessage().contains("No address associated with hostname")) {
                                NetworkError(context, context.getResources().getString(R.string.err_msg_network_title),
                                        context.getResources().getString(R.string.err_msg_network));
                            } else {
                                Error(context, t.getMessage());

                            }

                        } else {
                            Error(context, context.getResources().getString(R.string.some_thing_error));

                        }
                    } catch (IllegalStateException ise) {
                        Error(context, ise.getMessage());

                    }


                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void GetBanklist(final Activity context, final CustomLoader loader, final ApiCallBack mApiCallBack) {
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            Call<BankListResponse> call = git.GetBankList(new BalanceRequest(LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getLoginTypeID() + "",
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));
            call.enqueue(new Callback<BankListResponse>() {
                @Override
                public void onResponse(Call<BankListResponse> call, final retrofit2.Response<BankListResponse> response) {
                    Log.e("   GetBanklist", "is : " + new Gson().toJson(response.body()));
                    try {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                        if (response.body() != null && response.body().getStatuscode() != null) {

                            if (response.body().getStatuscode().equalsIgnoreCase("1")) {
                                setBankList(context, new Gson().toJson(response.body()));
                                if (mApiCallBack != null) {
                                    mApiCallBack.onSucess(response.body());
                                }
                            } else if (response.body().getStatuscode().equalsIgnoreCase("-1")) {
                                if (response.body().getIsVersionValid() != null && response.body().getIsVersionValid().equalsIgnoreCase("false")) {
                                    versionDialog(context);
                                } else {
                                    Error(context, response.body().getMsg() + "");
                                }
                            }
                        }
                    } catch (Exception e) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                    }

                }

                @Override
                public void onFailure(Call<BankListResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }
                    try {
                        if (t.getMessage() != null && !t.getMessage().isEmpty()) {

                            if (t.getMessage().contains("No address associated with hostname")) {
                                NetworkError(context, context.getResources().getString(R.string.err_msg_network_title),
                                        context.getResources().getString(R.string.err_msg_network));
                            } else {
                                Error(context, t.getMessage());

                            }

                        } else {
                            Error(context, context.getResources().getString(R.string.some_thing_error));

                        }
                    } catch (IllegalStateException ise) {
                        Error(context, ise.getMessage());

                    }

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void FundRequestTo(final Activity context, final CustomLoader loader, final ApiCallBack mApiCallBack) {
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            Call<FundreqToResponse> call = git.FundRequestTo(new BalanceRequest(LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getLoginTypeID() + "",
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));
            call.enqueue(new Callback<FundreqToResponse>() {
                @Override
                public void onResponse(Call<FundreqToResponse> call, final retrofit2.Response<FundreqToResponse> response) {

                    try {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                        if (response.body() != null && response.body().getStatuscode() != null) {

                            if (response.body().getStatuscode().equalsIgnoreCase("1")) {
                                setFundreqToList(context, new Gson().toJson(response.body()).toString());
                                if (mApiCallBack != null) {
                                    mApiCallBack.onSucess(response.body());
                                }

                            } else if (response.body().getStatuscode().equalsIgnoreCase("-1")) {
                                if (response.body().getIsVersionValid() != null && response.body().getIsVersionValid().equalsIgnoreCase("false")) {
                                    versionDialog(context);
                                } else {
                                    Error(context, response.body().getMsg() + "");
                                }
                            }
                        }
                    } catch (Exception e) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                    }

                }

                @Override
                public void onFailure(Call<FundreqToResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }
                    Error(context, context.getResources().getString(R.string.err_something_went_wrong));

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void GetBankAndPaymentMode(final Activity context, String Parentid, final CustomLoader loader, final ApiCallBack mApiCallBack) {
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);

            BalanceRequest mBalanceRequest = new BalanceRequest(Parentid, "",
                    LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getLoginTypeID() + "",
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession());
            String str = new Gson().toJson(mBalanceRequest);
            Call<GetBankAndPaymentModeResponse> call = git.GetBankAndPaymentMode(mBalanceRequest);
            call.enqueue(new Callback<GetBankAndPaymentModeResponse>() {
                @Override
                public void onResponse(Call<GetBankAndPaymentModeResponse> call, final retrofit2.Response<GetBankAndPaymentModeResponse> response) {

                    try {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                        if (response.body() != null && response.body().getStatuscode() != null) {
                            if (response.body().getStatuscode().equalsIgnoreCase("1")) {
                                // setFundreqToList(context, new Gson().toJson(response.body()).toString());
                                if (mApiCallBack != null) {
                                    mApiCallBack.onSucess(response.body());
                                }
                                /*FragmentActivityMessage activityActivityMessage =
                                        new FragmentActivityMessage(new Gson().toJson(response.body()).toString(), "SelectedBank");
                                GlobalBus.getBus().post(activityActivityMessage);*/
                            } else if (response.body().getStatuscode().equalsIgnoreCase("-1")) {
                                if (response.body().getIsVersionValid() == false) {
                                    versionDialog(context);
                                } else {
                                    Error(context, response.body().getMsg() + "");
                                }
                            }
                        }
                    } catch (Exception e) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                    }

                }

                @Override
                public void onFailure(Call<GetBankAndPaymentModeResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }
                    Error(context, context.getResources().getString(R.string.err_something_went_wrong));

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            if (loader != null) {
                if (loader.isShowing())
                    loader.dismiss();
            }
        }
    }


    public void GetAvailablePackage(final Activity context, final CustomLoader loader, final ApiCallBack mApiCallBack) {
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            Call<GetAvailablePackageResponse> call = git.GetAvailablePackages(new BasicRequest(LoginDataResponse.getData().getUserID() + "",
                    LoginDataResponse.getData().getLoginTypeID() + "",
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));


            call.enqueue(new Callback<GetAvailablePackageResponse>() {
                @Override
                public void onResponse(Call<GetAvailablePackageResponse> call, final retrofit2.Response<GetAvailablePackageResponse> response) {
                    try {
                        if (loader != null) {
                            if (loader.isShowing()) {
                                loader.dismiss();
                            }
                        }
                        if (response.body() != null) {

                            if (response.body().getStatuscode() == 1) {
                                if (response.body().getPackageDetail() != null && response.body().getPackageDetail().size() > 0) {
                                    if (mApiCallBack != null) {
                                        mApiCallBack.onSucess(response.body());
                                    }
                                } else {
                                    Error(context, "Data not found.");
                                }

                            } else if (response.body().getStatuscode() == -1) {
                                if (!response.body().getIsVersionValid()) {
                                    versionDialog(context);
                                } else {
                                    Error(context, response.body().getMsg() + "");
                                }
                            }
                        }
                    } catch (Exception e) {
                        if (loader != null) {
                            if (loader.isShowing()) {
                                loader.dismiss();
                            }
                        }
                    }

                }

                @Override
                public void onFailure(Call<GetAvailablePackageResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing()) {
                            loader.dismiss();
                        }
                    }
                    Error(context, context.getResources().getString(R.string.err_something_went_wrong));

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            if (loader != null) {
                if (loader.isShowing()) {
                    loader.dismiss();
                }
            }
        }
    }

    public void UpgradePackage(final Activity context, String uid, int packageId, final CustomLoader loader, final ApiCallBack mApiCallBack) {
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            Call<BasicResponse> call = git.UpgradePackage(new UpgradePackageRequest(uid, packageId, LoginDataResponse.getData().getUserID() + "",
                    LoginDataResponse.getData().getLoginTypeID() + "",
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));


            call.enqueue(new Callback<BasicResponse>() {
                @Override
                public void onResponse(Call<BasicResponse> call, final retrofit2.Response<BasicResponse> response) {
                    try {
                        if (loader != null) {
                            if (loader.isShowing()) {
                                loader.dismiss();
                            }
                        }
                        if (response.body() != null) {

                            if (response.body().getStatuscode() == 1) {
                                if (mApiCallBack != null) {
                                    mApiCallBack.onSucess(response.body());
                                }


                            } else if (response.body().getStatuscode() == -1) {
                                if (!response.body().getVersionValid()) {
                                    versionDialog(context);
                                } else {
                                    Error(context, response.body().getMsg() + "");
                                }
                            } else {
                                Error(context, response.body().getMsg() + "");
                            }
                        } else {
                            Error(context, context.getResources().getString(R.string.some_thing_error));
                        }
                    } catch (Exception e) {
                        if (loader != null) {
                            if (loader.isShowing()) {
                                loader.dismiss();
                            }
                        }
                        Error(context, e.getMessage() + "");
                    }

                }

                @Override
                public void onFailure(Call<BasicResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing()) {
                            loader.dismiss();
                        }
                    }
                    Error(context, t.getMessage() + "");

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            if (loader != null) {
                if (loader.isShowing()) {
                    loader.dismiss();
                }
            }
            Error(context, e.getMessage() + "");
        }
    }


    /*public void GetOpTypes(final Activity context, final CustomLoader loader) {
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            Call<OpTypeResponse> call = git.GetOpTypes(new BalanceRequest(LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getLoginTypeID() + "",
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));
            call.enqueue(new Callback<OpTypeResponse>() {
                @Override
                public void onResponse(Call<OpTypeResponse> call, final retrofit2.Response<OpTypeResponse> response) {
                    Log.e("balance", "is : " + new Gson().toJson(response.body()));
                    try {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                        if (response.body() != null && response.body().getStatuscode() != null) {

                            if (response.body().getStatuscode().equalsIgnoreCase("1")) {
                                setOPTypeResponse(context, new Gson().toJson(response.body()));
                            } else if (response.body().getStatuscode().equalsIgnoreCase("-1")) {
                                if (response.body().getIsVersionValid() != null && response.body().getIsVersionValid().equalsIgnoreCase("false")) {
                                    versionDialog(context);
                                } else {
                                    Error(context, response.body().getMsg() + "");
                                }
                            }
                        }
                    } catch (Exception e) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                    }

                }

                @Override
                public void onFailure(Call<OpTypeResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }
                    Error(context, context.getResources().getString(R.string.err_something_went_wrong));

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public void Recharge(final Activity context, int Opid, String AccountNo, String Amount, String o1, String o2, String o3, String o4, String customerNo, String refID, String GeoCode, String securityKey, final Integer fetchBillID,boolean isReal,final CustomLoader loader) {
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            Call<RechargeCResponse> call = git.Recharge(new RechargeRequest(
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getLoginTypeID(), Opid,
                    AccountNo, Amount, o1, o2, o3, o4, customerNo,
                    refID, GeoCode,
                    LoginDataResponse.getData().getUserID(), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession(), securityKey,isReal,fetchBillID)
            );
            call.enqueue(new Callback<RechargeCResponse>() {
                @Override
                public void onResponse(Call<RechargeCResponse> call, final retrofit2.Response<RechargeCResponse> response) {
                    Log.e("balance", "is : " + new Gson().toJson(response.body()));
                    try {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                        if (response.body() != null && response.body().getStatuscode() != null) {

                            if (response.body().getStatuscode().equalsIgnoreCase("1")) {
                                Processing(context, response.body().getMsg());
                            } else if (response.body().getStatuscode().equalsIgnoreCase("2")) {
                                Successful(context, response.body().getMsg());
                            } else if (response.body().getStatuscode().equalsIgnoreCase("3")) {
                                Failed(context, response.body().getMsg());
                            } else if (response.body().getStatuscode().equalsIgnoreCase("-1")) {
                                if (response.body().getIsVersionValid() != null && response.body().getIsVersionValid().equalsIgnoreCase("false")) {
                                    versionDialog(context);
                                } else {
                                    Error(context, response.body().getMsg() + "");
                                }
                            }
                            ActivityActivityMessage activityActivityMessage =
                                    new ActivityActivityMessage("" + new Gson().toJson(response.body()), "refreshvalue");
                            GlobalBus.getBus().post(activityActivityMessage);
                            Balancecheck(context, loader, null);
                        }
                    } catch (Exception e) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                    }

                }

                @Override
                public void onFailure(Call<RechargeCResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }
                    try {
                        if (t.getMessage() != null && !t.getMessage().isEmpty()) {

                            if (t.getMessage().contains("No address associated with hostname")) {
                                NetworkError(context, context.getResources().getString(R.string.err_msg_network_title),
                                        context.getResources().getString(R.string.err_msg_network));
                            } else {
                                Error(context, "Recharge Request Accepted");

                            }

                        } else {
                            Error(context, context.getResources().getString(R.string.some_thing_error));

                        }
                    } catch (IllegalStateException ise) {
                        Error(context, ise.getMessage());

                    }

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ROffer(final Activity context,boolean isPlanServiceUpdated, String Opid, String AccountNo, final CustomLoader loader) {
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<RofferResponse> call;
            if (isPlanServiceUpdated) {
                call = git.GetRNPRoffer(new ROfferRequest(Opid, AccountNo,
                        ApplicationConstant.INSTANCE.APP_ID,
                        getIMEI(context),
                        "", BuildConfig.VERSION_NAME, getSerialNo(context)));
            } else {
                call = git.ROffer(new ROfferRequest(Opid, AccountNo,
                        ApplicationConstant.INSTANCE.APP_ID,
                        getIMEI(context),
                        "", BuildConfig.VERSION_NAME, getSerialNo(context)));
            }
            /*Call<RofferResponse> call = git.ROffer(new ROfferRequest(Opid, AccountNo,
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context)));*/
            call.enqueue(new Callback<RofferResponse>() {
                @Override
                public void onResponse(Call<RofferResponse> call, final retrofit2.Response<RofferResponse> response) {

                    try {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                        if (response.body() != null) {

                            if (response.body().getStatuscode() == 1) {

                                if (response.body().getData() != null && response.body().getData().getRecords() != null && response.body().getData().getRecords().size() > 0 || response.body().getDataPA() != null && response.body().getDataPA().getError() == 0 && response.body().getDataPA().getRecords() != null && response.body().getDataPA().getRecords().size() > 0) {
                                    Intent browseIntent = new Intent(context, ROffer.class);
                                    browseIntent.putExtra("response", response.body());
                                    context.startActivity(browseIntent);
                                } else if (response.body().getRofferData() != null && response.body().getRofferData().size() > 0) {
                                    Intent browseIntent = new Intent(context, ROffer.class);
                                    browseIntent.putExtra("response", response.body());
                                    context.startActivity(browseIntent);

                                }else if (response.body().getDataPA() != null && response.body().getDataPA().getError() != 0) {
                                    Error(context, response.body().getDataPA().getMessage() + "");
                                } else {
                                    Error(context, "Records not found");
                                }
                            } else if (response.body().getStatuscode() == -1) {
                                if (!response.body().isVersionValid()) {
                                    versionDialog(context);
                                } else {
                                    Error(context, response.body().getMsg() + "");
                                }
                            }

                        }
                    } catch (Exception e) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                    }

                }

                @Override
                public void onFailure(Call<RofferResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }
                    try {
                        if (t.getMessage() != null && !t.getMessage().isEmpty()) {

                            if (t.getMessage().contains("No address associated with hostname")) {
                                NetworkError(context, context.getResources().getString(R.string.err_msg_network_title),
                                        context.getResources().getString(R.string.err_msg_network));
                            } else {
                                Error(context, t.getMessage());

                            }

                        } else {
                            Error(context, context.getResources().getString(R.string.some_thing_error));

                        }
                    } catch (IllegalStateException ise) {
                        Error(context, ise.getMessage());

                    }

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ViewPlan(final Activity context,boolean isPlanUpdated, String Opid, String CircleID, final CustomLoader loader) {
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<ResponsePlan> call;
            if(isPlanUpdated){
                call = git.RechargePlansUpdated(new PlanRequest(Opid, CircleID,
                        ApplicationConstant.INSTANCE.APP_ID,
                        getIMEI(context),
                        "", BuildConfig.VERSION_NAME, getSerialNo(context)));
            }else{
                call = git.Rechageplans(new PlanRequest(Opid, CircleID,
                        ApplicationConstant.INSTANCE.APP_ID,
                        getIMEI(context),
                        "", BuildConfig.VERSION_NAME, getSerialNo(context)));
            }

            call.enqueue(new Callback<ResponsePlan>() {
                @Override
                public void onResponse(Call<ResponsePlan> call, final retrofit2.Response<ResponsePlan> response) {

                    try {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                        if (response.body() != null && response.body().getStatuscode() != null) {

                            if (response.body().getStatuscode().equalsIgnoreCase("1")) {
                                if ((response.body().getData() != null && response.body().getData().getRecords() != null || response.body().getData() != null && response.body().getData().getTypes() != null && response.body().getData().getTypes().size() > 0 || response.body().getDataRP() != null && response.body().getDataRP().getRecords() != null || response.body().getDataPA() != null && response.body().getDataPA().getError() == 0 && response.body().getDataPA().getRecords() != null)) {

                                    Intent browseIntent = new Intent(context, BrowsePlanScreen.class);
                                    browseIntent.putExtra("response", "" + new Gson().toJson(response.body()));
                                    context.startActivityForResult(browseIntent, 45);
                                } else if (response.body().getDataPA() != null && response.body().getDataPA().getError() != 0) {
                                    Error(context, response.body().getDataPA().getMessage() + "");
                                } else if (response.body().getStatuscode().equalsIgnoreCase("1")) {
                                    Error(context, "Plan not found");
                                }
                            } else if (response.body().getStatuscode().equalsIgnoreCase("-1")) {
                                if (response.body().getIsVersionValid() != null && response.body().getIsVersionValid().equalsIgnoreCase("false")) {
                                    versionDialog(context);
                                } else {
                                    Error(context, response.body().getMsg() + "");
                                }
                            }

                        }
                    } catch (Exception e) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                    }

                }

                @Override
                public void onFailure(Call<ResponsePlan> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }
                    try {
                        if (t.getMessage() != null && !t.getMessage().isEmpty()) {

                            if (t.getMessage().contains("No address associated with hostname")) {
                                NetworkError(context, context.getResources().getString(R.string.err_msg_network_title),
                                        context.getResources().getString(R.string.err_msg_network));
                            } else {
                                Error(context, t.getMessage());

                            }

                        } else {
                            Error(context, context.getResources().getString(R.string.some_thing_error));

                        }
                    } catch (IllegalStateException ise) {
                        Error(context, ise.getMessage());

                    }

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void RechargeReport(final Activity context, boolean isRecent, String opTypeId, String topValue,
                               String status, String fromDate, String toDate, String transactionID, String accountNo, String childMobNo, String isExport, final CustomLoader loader) {
        try {
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<RechargeReportResponse> call = git.RechargeReport(new RechargeReportRequest(isRecent, opTypeId, "0",
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context),
                    topValue, status, fromDate, toDate, transactionID, accountNo, childMobNo, isExport,
                    LoginDataResponse.getData().getUserID()
                    , LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession(),
                    LoginDataResponse.getData().getLoginTypeID()));
            call.enqueue(new Callback<RechargeReportResponse>() {
                @Override
                public void onResponse(Call<RechargeReportResponse> call, final retrofit2.Response<RechargeReportResponse> response) {
                    Log.e("plan", "is : " + new Gson().toJson(response.body()));
                    try {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                        if (response.body() != null && response.body().getStatuscode() != null) {
                            if (response.body().getStatuscode().equalsIgnoreCase("1")) {
                                Balancecheck(context, loader, null);
                                ActivityActivityMessage activityActivityMessage =
                                        new ActivityActivityMessage("recent", "" + new Gson().toJson(response.body()));
                                GlobalBus.getBus().post(activityActivityMessage);
                            } else if (response.body().getStatuscode().equalsIgnoreCase("-1")) {
                                if (response.body().getIsVersionValid() != null && response.body().getIsVersionValid().equalsIgnoreCase("false")) {
                                    versionDialog(context);
                                } else {
                                    Error(context, response.body().getMsg() + "");
                                }
                            }
                        }
                    } catch (Exception e) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                    }

                }

                @Override
                public void onFailure(Call<RechargeReportResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }
                    try {
                        if (t.getMessage() != null && !t.getMessage().isEmpty()) {

                            if (t.getMessage().contains("No address associated with hostname")) {
                                NetworkError(context, context.getResources().getString(R.string.err_msg_network_title),
                                        context.getResources().getString(R.string.err_msg_network));
                            } else {
                                Error(context, t.getMessage());

                            }

                        } else {
                            Error(context, context.getResources().getString(R.string.some_thing_error));

                        }
                    } catch (IllegalStateException ise) {
                        Error(context, ise.getMessage());

                    }

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void LedgerReport(final Activity context, String oid, String topRow,
                             String status, String fromDate, String toDate, String transactionID, String accountNo, String isExport, int walletTypeID, final CustomLoader loader) {
        try {
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<RechargeReportResponse> call = git.LedgerReport(new LedgerReportRequest(
                    topRow, oid, status, fromDate, toDate, transactionID, accountNo, isExport, LoginDataResponse.getData().getUserID(),
                    LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession(),
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getLoginTypeID(), walletTypeID));
            call.enqueue(new Callback<RechargeReportResponse>() {
                @Override
                public void onResponse(Call<RechargeReportResponse> call, final retrofit2.Response<RechargeReportResponse> response) {

                    try {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                        if (response.body() != null && response.body().getStatuscode() != null) {
                            if (response.body().getStatuscode().equalsIgnoreCase("1")) {
                                Balancecheck(context, loader, null);
                                ActivityActivityMessage activityActivityMessage =
                                        new ActivityActivityMessage("ledger_respo", "" + new Gson().toJson(response.body()));
                                GlobalBus.getBus().post(activityActivityMessage);
                            } else if (response.body().getStatuscode().equalsIgnoreCase("-1")) {
                                if (response.body().getIsVersionValid() != null && response.body().getIsVersionValid().equalsIgnoreCase("false")) {
                                    versionDialog(context);
                                } else {
                                    Error(context, response.body().getMsg() + "");
                                }
                            }
                        }
                    } catch (Exception e) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                    }

                }

                @Override
                public void onFailure(Call<RechargeReportResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }
                    try {
                        if (t.getMessage() != null && !t.getMessage().isEmpty()) {

                            if (t.getMessage().contains("No address associated with hostname")) {
                                NetworkError(context, context.getResources().getString(R.string.err_msg_network_title),
                                        context.getResources().getString(R.string.err_msg_network));
                            } else {
                                Error(context, t.getMessage());

                            }

                        } else {
                            Error(context, context.getResources().getString(R.string.some_thing_error));

                        }
                    } catch (IllegalStateException ise) {
                        Error(context, ise.getMessage());

                    }

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void FundOrderReport(final Activity context, String topCount,
                                String status, String fromDate, String toDate, String transactionID, String accountNo, String isExport, String tMode, String isSelf, String uMobile, final CustomLoader loader) {
        try {
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<RechargeReportResponse> call = git.FundOrderReport(new LedgerReportRequest(
                    topCount, status, "", fromDate, toDate, transactionID, accountNo, isExport, LoginDataResponse.getData().getUserID(),
                    LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession(),
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getLoginTypeID(),
                    tMode, isSelf, uMobile));
            call.enqueue(new Callback<RechargeReportResponse>() {
                @Override
                public void onResponse(Call<RechargeReportResponse> call, final retrofit2.Response<RechargeReportResponse> response) {
                    Log.e("plan", "is : " + new Gson().toJson(response.body()));
                    try {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                        if (response.body() != null && response.body().getStatuscode() != null) {
                            if (response.body().getStatuscode().equalsIgnoreCase("1")) {
                                Balancecheck(context, loader, null);
                                ActivityActivityMessage activityActivityMessage =
                                        new ActivityActivityMessage("fund_receive", "" + new Gson().toJson(response.body()));
                                GlobalBus.getBus().post(activityActivityMessage);
                            } else if (response.body().getStatuscode().equalsIgnoreCase("-1")) {
                                if (response.body().getIsVersionValid() != null && response.body().getIsVersionValid().equalsIgnoreCase("false")) {
                                    versionDialog(context);
                                } else {
                                    Error(context, response.body().getMsg() + "");
                                }
                            }
                        }
                    } catch (Exception e) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                    }

                }

                @Override
                public void onFailure(Call<RechargeReportResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }
                    try {
                        if (t.getMessage() != null && !t.getMessage().isEmpty()) {

                            if (t.getMessage().contains("No address associated with hostname")) {
                                NetworkError(context, context.getResources().getString(R.string.err_msg_network_title),
                                        context.getResources().getString(R.string.err_msg_network));
                            } else {
                                Error(context, t.getMessage());

                            }

                        } else {
                            Error(context, context.getResources().getString(R.string.some_thing_error));

                        }
                    } catch (IllegalStateException ise) {
                        Error(context, ise.getMessage());

                    }

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Logout(final Activity context, String sessType) {
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            Call<RechargeCResponse> call = git.Logout(new LogoutRequest(sessType,
                    LoginDataResponse.getData().getUserID(), LoginDataResponse.getData().getLoginTypeID(),
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));

            call.enqueue(new Callback<RechargeCResponse>() {
                @Override
                public void onResponse(Call<RechargeCResponse> call, final retrofit2.Response<RechargeCResponse> response) {
                    Log.e("balance", "is : " + new Gson().toJson(response.body()));
                    try {

                        if (response.body() != null && response.body().getStatuscode() != null) {

                           /* if (response.body().getStatuscode().equalsIgnoreCase("1")) {
                                logout(context);
                                context.finish();
                            } else if (response.body().getStatuscode().equalsIgnoreCase("2")) {
                                Successful(context, response.body().getMsg());
                            } else if (response.body().getStatuscode().equalsIgnoreCase("3")) {
                                Failed(context, response.body().getMsg());
                            } else if (response.body().getStatuscode().equalsIgnoreCase("-1")) {

                                Error(context, response.body().getMsg() + "");

                            }*/
                            logout(context);
                            context.finish();

                        }
                    } catch (Exception e) {

                    }

                }

                @Override
                public void onFailure(Call<RechargeCResponse> call, Throwable t) {

                    try {
                        if (t.getMessage() != null && !t.getMessage().isEmpty()) {

                            if (t.getMessage().contains("No address associated with hostname")) {
                                NetworkError(context, context.getResources().getString(R.string.err_msg_network_title),
                                        context.getResources().getString(R.string.err_msg_network));
                            } else {
                                Error(context, t.getMessage());

                            }

                        } else {
                            Error(context, context.getResources().getString(R.string.some_thing_error));

                        }
                    } catch (IllegalStateException ise) {
                        Error(context, ise.getMessage());

                    }

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void NewsApi(final Activity context, boolean isLoginNews, final WebView mWebView,ApiCallBack apiCallBack) {
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            String LoginResponse = UtilMethods.INSTANCE.getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            Call<AppUserListResponse> call = git.GetAppNews(new NewsRequest(isLoginNews,
                    LoginDataResponse.getData().getUserID(), LoginDataResponse.getData().getLoginTypeID(),
                    ApplicationConstant.INSTANCE.APP_ID,
                    UtilMethods.INSTANCE.getIMEI(context),
                    "", BuildConfig.VERSION_NAME, UtilMethods.INSTANCE.getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));

            call.enqueue(new Callback<AppUserListResponse>() {
                @Override
                public void onResponse(Call<AppUserListResponse> call, final retrofit2.Response<AppUserListResponse> response) {

                    try {

                        if (response.body() != null && response.body().getStatuscode() != null) {

                            if (response.body().getNewsContent() != null && response.body().getNewsContent().getNewsDetail() != null && !response.body().getNewsContent().getNewsDetail().isEmpty()) {
                                String news="<marquee><body style='padding:0;margin:0;color:#fff000'>"+response.body().getNewsContent().getNewsDetail()+"</body></marquee>";
                                String newhtml_code = android.util.Base64.encodeToString(news.getBytes(), android.util.Base64.NO_PADDING);
                                mWebView.loadData(newhtml_code,"text/html", "base64");
                            } else {
                                if(apiCallBack!=null)
                                    apiCallBack.onSucess(response.body());
                            }

                        }

                    } catch (Exception e) {
                        if(apiCallBack!=null)
                            apiCallBack.onSucess(null);
                    }

                }

                @Override
                public void onFailure(Call<AppUserListResponse> call, Throwable t) {
                    if(apiCallBack!=null)
                        apiCallBack.onSucess(null);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            if(apiCallBack!=null)
                apiCallBack.onSucess(null);
        }
    }

    public void FundDCReport(final Activity context, boolean isSelf, int walletTypeID, int serviceID, String otherUserMob, String fromDate, String toDate, String accountNo, final CustomLoader loader) {
        try {
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            FundDCReportRequest mFundDCReportRequest = new FundDCReportRequest(isSelf, walletTypeID,
                    serviceID, otherUserMob, fromDate, toDate, accountNo, LoginDataResponse.getData().getUserID(),
                    LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession(),
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getLoginTypeID());
            /* String str =new Gson().toJson(mFundDCReportRequest);*/
            Call<RechargeReportResponse> call = git.FundDCReport(mFundDCReportRequest);
            call.enqueue(new Callback<RechargeReportResponse>() {
                @Override
                public void onResponse(Call<RechargeReportResponse> call, final retrofit2.Response<RechargeReportResponse> response) {

                    try {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                        if (response.body() != null && response.body().getStatuscode() != null) {
                            if (response.body().getStatuscode().equalsIgnoreCase("1")) {
                                Balancecheck(context, loader, null);
                                ActivityActivityMessage activityActivityMessage =
                                        new ActivityActivityMessage("fund_receive", "" + new Gson().toJson(response.body()));
                                GlobalBus.getBus().post(activityActivityMessage);
                            } else if (response.body().getStatuscode().equalsIgnoreCase("-1")) {
                                if (response.body().getIsVersionValid() != null && response.body().getIsVersionValid().equalsIgnoreCase("false")) {
                                    versionDialog(context);
                                } else {
                                    Error(context, response.body().getMsg() + "");
                                }
                            }
                        }
                    } catch (Exception e) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                    }

                }

                @Override
                public void onFailure(Call<RechargeReportResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }
                    try {
                        if (t.getMessage() != null && !t.getMessage().isEmpty()) {

                            if (t.getMessage().contains("No address associated with hostname")) {
                                NetworkError(context, context.getResources().getString(R.string.err_msg_network_title),
                                        context.getResources().getString(R.string.err_msg_network));
                            } else {
                                Error(context, t.getMessage());

                            }

                        } else {
                            Error(context, context.getResources().getString(R.string.some_thing_error));

                        }
                    } catch (IllegalStateException ise) {
                        Error(context, ise.getMessage());

                    }

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void RefundLog(final Activity context, Integer topRows, Integer criteria, String criteriaText, Integer status, String fromDate, String toDate, Integer dateType, String transactionID, final CustomLoader loader) {
        try {
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            RefundLogRequest mRefundLogRequest = new RefundLogRequest(topRows, criteria,
                    criteriaText, status, fromDate, toDate, dateType, transactionID, LoginDataResponse.getData().getUserID(),
                    LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession(),
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getLoginTypeID());
            /* String str =new Gson().toJson(mFundDCReportRequest);*/
            Call<AppUserListResponse> call = git.RefundLog(mRefundLogRequest);
            call.enqueue(new Callback<AppUserListResponse>() {
                @Override
                public void onResponse(Call<AppUserListResponse> call, final retrofit2.Response<AppUserListResponse> response) {
                    Log.e("plan", "is : " + new Gson().toJson(response.body()));
                    try {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                        if (response.body() != null && response.body().getStatuscode() != null) {
                            if (response.body().getStatuscode() == 1 && response.body().getRefundLog() != null && response.body().getRefundLog().size() > 0) {
                                ActivityActivityMessage activityActivityMessage =
                                        new ActivityActivityMessage("Refund_Log", "" + new Gson().toJson(response.body()));
                                GlobalBus.getBus().post(activityActivityMessage);
                            } else if (response.body().getStatuscode() == -1) {
                                if (response.body().getVersionValid() != null && response.body().getVersionValid() == false) {
                                    versionDialog(context);
                                } else {
                                    Error(context, response.body().getMsg() + "");
                                }
                            } else {
                                Error(context, "Report not found.");
                            }
                        }
                    } catch (Exception e) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                        Error(context, e.getMessage());
                    }

                }

                @Override
                public void onFailure(Call<AppUserListResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }
                    try {
                        if (t.getMessage() != null && !t.getMessage().isEmpty()) {

                            if (t.getMessage().contains("No address associated with hostname")) {
                                NetworkError(context, context.getResources().getString(R.string.err_msg_network_title),
                                        context.getResources().getString(R.string.err_msg_network));
                            } else {
                                Error(context, t.getMessage());

                            }

                        } else {
                            Error(context, context.getResources().getString(R.string.some_thing_error));

                        }
                    } catch (IllegalStateException ise) {
                        Error(context, ise.getMessage());

                    }

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            Error(context, e.getMessage());
        }
    }

    public void UserDayBook(final Activity context, String mobileNo, String fromDate, String toDate, final CustomLoader loader, final ApiCallBackTwoMethod mApiCallBack) {
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            String mLoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(mLoginResponse, LoginResponse.class);
            Call<AppUserListResponse> call = git.UserDaybook(new UserDayBookRequest(fromDate, toDate, mobileNo,
                    LoginDataResponse.getData().getUserID(), LoginDataResponse.getData().getLoginTypeID(),
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));


            call.enqueue(new Callback<AppUserListResponse>() {
                @Override
                public void onResponse(Call<AppUserListResponse> call, final retrofit2.Response<AppUserListResponse> response) {
                    try {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                        if (response.body() != null) {
                            if (response.body().getStatuscode() == 1) {

                                if (mApiCallBack != null) {
                                    mApiCallBack.onSucess(response.body());
                                }
                            } else if (response.body().getStatuscode() == -1) {
                                if (response.body().getVersionValid() == false) {
                                    versionDialog(context);
                                } else {
                                    Error(context, response.body().getMsg() + "");
                                }
                            }
                        }
                    } catch (Exception e) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                    }

                }

                @Override
                public void onFailure(Call<AppUserListResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }

                    if (context instanceof UserDayBookActivity) {
                        if (t != null && t.getMessage().contains("No address associated with hostname")) {
                            NetworkError(context, context.getResources().getString(R.string.err_msg_network_title),
                                    context.getResources().getString(R.string.err_msg_network));
                        } else if (t != null && t.getMessage() != null) {
                            Error(context, t.getMessage());

                        } else {
                            Error(context, context.getResources().getString(R.string.err_something_went_wrong));
                        }
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void UserDayBookDmt(final Activity context, String mobileNo, String fromDate, String toDate, final CustomLoader loader, final ApiCallBack mApiCallBack) {
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            Call<AppUserListResponse> call = git.UserDaybookDmt(new UserDayBookRequest(fromDate, toDate, mobileNo,
                    LoginDataResponse.getData().getUserID(), LoginDataResponse.getData().getLoginTypeID(),
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));


            call.enqueue(new Callback<AppUserListResponse>() {
                @Override
                public void onResponse(Call<AppUserListResponse> call, final retrofit2.Response<AppUserListResponse> response) {
                    try {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                        if (response.body() != null) {
                            if (response.body().getStatuscode() == 1) {
                                if (mApiCallBack != null) {
                                    mApiCallBack.onSucess(response.body());
                                }
                            } else if (response.body().getStatuscode() == -1) {
                                if (response.body().getVersionValid() == false) {
                                    versionDialog(context);
                                } else {
                                    Error(context, response.body().getMsg() + "");
                                }
                            }
                        }
                    } catch (Exception e) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                    }

                }

                @Override
                public void onFailure(Call<AppUserListResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }
                    Error(context, context.getResources().getString(R.string.err_something_went_wrong));

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* public void UserDayBook(final Activity context, String mobileNo, String fromDate, final CustomLoader loader, final ApiCallBack mApiCallBack) {
         try {
             EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
             String LoginResponse = getLoginPref(context);
             com.solution.fintech.gorecharge365.co.in.Auth.dto.LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
             Call<AppUserListResponse> call = git.UserDaybook(new UserDayBookRequest(fromDate, mobileNo,
                     LoginDataResponse.getData().getUserID(), LoginDataResponse.getData().getLoginTypeID(),
                     ApplicationConstant.INSTANCE.APP_ID,
                     getIMEI(context),
                     "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));


             call.enqueue(new Callback<AppUserListResponse>() {
                 @Override
                 public void onResponse(Call<AppUserListResponse> call, final retrofit2.Response<AppUserListResponse> response) {
                     Log.e("plan", "is : " + new Gson().toJson(response.body()));
                     try {
                         if (loader != null) {
                             if (loader.isShowing())
                                 loader.dismiss();
                         }
                         if (response.body() != null && response.body().getStatuscode() != null) {
                             if (response.body().getStatuscode() == 1) {
                                 if (mApiCallBack != null) {
                                     mApiCallBack.onSucess(response.body());
                                 }
                             } else if (response.body().getStatuscode() == -1 && !(context instanceof DashBoardLayout)) {
                                 if (!(context instanceof DashBoardLayout)) {
                                     if (response.body().getVersionValid() != null && response.body().getVersionValid() == false) {
                                         versionDialog(context);
                                     } else {
                                         Error(context, response.body().getMsg() + "");
                                     }
                                 }
                             }
                         }
                     } catch (Exception e) {
                         if (loader != null) {
                             if (loader.isShowing())
                                 loader.dismiss();
                         }
                     }

                 }

                 @Override
                 public void onFailure(Call<AppUserListResponse> call, Throwable t) {
                     if (loader != null) {
                         if (loader.isShowing())
                             loader.dismiss();
                     }
                     if (!(context instanceof DashBoardLayout)) {

                         try {
                             if (t.getMessage() != null && !t.getMessage().isEmpty()) {

                                 if (t.getMessage().contains("No address associated with hostname")) {
                                     NetworkError(context, context.getResources().getString(R.string.err_msg_network_title),
                                             context.getResources().getString(R.string.err_msg_network));
                                 } else {
                                     Error(context, t.getMessage());

                                 }

                             } else {
                                 Error(context, context.getResources().getString(R.string.some_thing_error));

                             }
                         } catch (IllegalStateException ise) {
                             Error(context, ise.getMessage());

                         }
                     }

                 }
             });

         } catch (Exception e) {
             e.printStackTrace();
         }
     }
 */
    public void LastRechargeReport(final Activity context, String oid,
                                   String status, String fromDate, String toDate, String transactionID, String accountNo, String isExport, final CustomLoader loader, final ApiCallBack mApiCallBack) {
        try {
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<RechargeReportResponse> call = git.RechargeReport(new RechargeReportRequest(true, "0", oid,
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context),
                    "5", status, fromDate, toDate, transactionID, accountNo, "", isExport,
                    LoginDataResponse.getData().getUserID()
                    , LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession(),
                    LoginDataResponse.getData().getLoginTypeID()));
            call.enqueue(new Callback<RechargeReportResponse>() {
                @Override
                public void onResponse(Call<RechargeReportResponse> call, final retrofit2.Response<RechargeReportResponse> response) {
                    Log.e("plan", "is : " + new Gson().toJson(response.body()).toString());
                    try {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                        if (response.body() != null && response.body().getStatuscode() != null) {
                            if (response.body().getStatuscode().equalsIgnoreCase("1")) {
                                Balancecheck(context, loader, null);
                                if (mApiCallBack != null) {
                                    mApiCallBack.onSucess(response.body());
                                }

                            } else if (response.body().getStatuscode().equalsIgnoreCase("-1")) {
                                if (response.body().getIsVersionValid() != null && response.body().getIsVersionValid().equalsIgnoreCase("false")) {
                                    versionDialog(context);
                                } else {
                                    Error(context, response.body().getMsg() + "");
                                }
                            }
                        }
                    } catch (Exception e) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                    }

                }

                @Override
                public void onFailure(Call<RechargeReportResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }
                    try {
                        if (t.getMessage() != null && !t.getMessage().isEmpty()) {

                            if (t.getMessage().contains("No address associated with hostname")) {
                                NetworkError(context, context.getResources().getString(R.string.err_msg_network_title),
                                        context.getResources().getString(R.string.err_msg_network));
                            } else {
                                Error(context, t.getMessage());

                            }

                        } else {
                            Error(context, context.getResources().getString(R.string.some_thing_error));

                        }
                    } catch (IllegalStateException ise) {
                        Error(context, ise.getMessage());

                    }

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void ValidateOTP(final Activity context, String Otp, final String Otpsession, final Integer otpType, final String password, final CustomLoader loader) {
        try {

            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<LoginResponse> call = git.ValidateOTP(new OtpRequest(Otp, Otpsession, otpType,
                    ApplicationConstant.INSTANCE.Domain,
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, ""));
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, final retrofit2.Response<LoginResponse> response) {

                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }
                    if (response.body() != null && response.body().getStatuscode() != null) {
                        if (response.body().getStatuscode().equalsIgnoreCase("1")) {
                            setDoubleFactorPref(context, response.body().getData().isDoubleFactor());
                            setPinRequiredPref(context, response.body().getData().isPinRequired());
                            setLoginPref(context, response.body().getData().getMobileNo(), new Gson().toJson(response.body()));
                            updateFcm(context);
                            if (loader != null)
                                ((LoginActivity) context).startDashboard();
                            else
                                ((Splash) context).startDashboard();
                        } else if (response.body().getStatuscode().equalsIgnoreCase("-1")) {
                            if (response.body().getIsVersionValid() != null && response.body().getIsVersionValid().equalsIgnoreCase("false")) {
                                versionDialog(context);
                            } else {
                                Error(context, response.body().getMsg());
                            }

                        }
                    }

                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }
                    try {
                        if (t.getMessage() != null && !t.getMessage().isEmpty()) {

                            if (t.getMessage().contains("No address associated with hostname")) {
                                NetworkError(context, context.getResources().getString(R.string.err_msg_network_title),
                                        context.getResources().getString(R.string.err_msg_network));
                            } else {
                                Error(context, t.getMessage());

                            }

                        } else {
                            Error(context, context.getResources().getString(R.string.some_thing_error));

                        }
                    } catch (IllegalStateException ise) {
                        Error(context, ise.getMessage());

                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void AppPopup(final Activity context,final CustomLoader loader) {
        try {
            String LoginResponse = UtilMethods.INSTANCE.getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<BalanceResponse> call = git.GetPopupAfterLogin(new BalanceRequest(LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getLoginTypeID() + "",
                    ApplicationConstant.INSTANCE.APP_ID,
                    UtilMethods.INSTANCE.getIMEI(context),
                    "", BuildConfig.VERSION_NAME, UtilMethods.INSTANCE.getSerialNo(context),
                    LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));
            call.enqueue(new Callback<BalanceResponse>() {
                @Override
                public void onResponse(Call<BalanceResponse> call, final retrofit2.Response<BalanceResponse> response) {

                    try {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }

                        if(response.body()!=null) {
                            if(response.body().getStatuscode()!=null&&response.body().getStatuscode()==1)
                            {
                                if(response.body().getPopup()!=null && response.body().getPopup().length()>0)
                                {
                                    openImageDialog(context,ApplicationConstant.INSTANCE.baseUrl+response.body().getPopup());
                                }
                            }

                        }


                    } catch (Exception e) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                    }

                }

                @Override
                public void onFailure(Call<BalanceResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }
                    if (t.getMessage().contains("No address associated with hostname")) {
                        UtilMethods.INSTANCE.Error(context, context.getResources().getString(R.string.network_error));
                    } else {
                        UtilMethods.INSTANCE.Error(context, t.getMessage());
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void rechargeConfiormDialog(Activity context, final boolean isPinPass, String logo, String number, String operator, String amount, final DialogCallBack mDialogCallBack) {
        if (alertDialogMobile != null && alertDialogMobile.isShowing()) {
            return;
        }
        androidx.appcompat.app.AlertDialog.Builder dialogBuilder;
        dialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(context);

        alertDialogMobile = dialogBuilder.create();
        alertDialogMobile.setCancelable(true);

        alertDialogMobile.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        LayoutInflater inflater = context.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_recharge_confiorm, null);
        alertDialogMobile.setView(dialogView);
        AppCompatTextView amountTv = dialogView.findViewById(R.id.amount);
        amountTv.setText(context.getResources().getString(R.string.rupiya) + " " + amount);
        final TextInputLayout til_pinPass = dialogView.findViewById(R.id.til_pinPass);
        final EditText pinPassEt = dialogView.findViewById(R.id.pinPassEt);
        if (isPinPass) {
            til_pinPass.setVisibility(View.VISIBLE);
        } else {
            til_pinPass.setVisibility(View.GONE);
        }
        AppCompatTextView operatorTv = dialogView.findViewById(R.id.operator);
        operatorTv.setText(operator);
        AppCompatTextView numberTv = dialogView.findViewById(R.id.number);
        numberTv.setText(number);
        AppCompatTextView cancelTv = dialogView.findViewById(R.id.cancel);
        AppCompatTextView okTv = dialogView.findViewById(R.id.ok);
        AppCompatImageView logoIv = dialogView.findViewById(R.id.logo);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(context)
                .load(logo)

                .apply(RequestOptions.circleCropTransform())
                .apply(RequestOptions.placeholderOf(R.mipmap.ic_launcher_round))
                .into(logoIv);
        okTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPinPass && pinPassEt.getText().toString().isEmpty()) {
                    pinPassEt.setError("Field can't be empty");
                    pinPassEt.requestFocus();
                    return;
                }
                alertDialogMobile.dismiss();
                if (mDialogCallBack != null) {
                    mDialogCallBack.onPositiveClick(pinPassEt.getText().toString());
                }
            }
        });
        cancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogMobile.dismiss();
                if (mDialogCallBack != null) {
                    mDialogCallBack.onCancelClick();
                }
            }
        });

        alertDialogMobile.show();

    }




    public void openImageDialog(final Context context, String imageurl) {

        RequestOptions requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.NONE) // because file name is always same
                .skipMemoryCache(true);


        Glide.with(context)
                .asBitmap()
                .load(imageurl)
                .apply(requestOptions)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {

                        if (resource != null) {
                            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                            View dialogLayout = inflater.inflate(R.layout.image_dialog_layout, null);
                            final ImageView dialogImage = dialogLayout.findViewById(R.id.dialogImage);
                            final ImageView btn_ok = dialogLayout.findViewById(R.id.btn_ok);

                            final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            alertDialog = builder.create();
                            alertDialog.setView(dialogLayout);
                            alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);


                            btn_ok.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    alertDialog.dismiss();
                                }
                            });


                            alertDialog.show();
                            dialogImage.setImageBitmap(resource);

                            ////////////////////////////////////////////////////////

                        }else {
                            alertDialog.dismiss();
                        }


                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }
                });

    }


    ////
    public String getOtpMessage(String otp) {
        edMobileOtp.setText(otp);
        return otp;
    }

    public String getDeviceId(Context context) {

        return getIMEI(context);
    }


    public String getIMEI(Context context) {

        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = "";
        try {
            if (Build.VERSION.SDK_INT >= 29) {
                deviceId = getUniqueID(context).replaceAll(" ", "").replaceAll("-", "").substring(0, 15);
            } else if (Build.VERSION.SDK_INT >= 26 && Build.VERSION.SDK_INT <= 28) {

                if (telephonyManager.getPhoneType() == TelephonyManager.PHONE_TYPE_CDMA) {
                    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

                    }
                    deviceId = telephonyManager.getImei();

                } else if (telephonyManager.getPhoneType() == TelephonyManager.PHONE_TYPE_GSM) {
                    deviceId = telephonyManager.getImei();


                } else if (telephonyManager.getPhoneType() == TelephonyManager.PHONE_TYPE_SIP) {
                    deviceId = telephonyManager.getImei();

                } else if (telephonyManager.getPhoneType() == TelephonyManager.PHONE_TYPE_NONE) {
                    deviceId = telephonyManager.getImei();
                }
            } else {
                deviceId = telephonyManager.getDeviceId();
            }

        } catch (SecurityException e) {
        } catch (Exception ex) {

        }
        return deviceId;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    String getUniqueID(Context mContext) {
        UUID wideVineUuid = new UUID(-0x121074568629b532L, -0x5c37d8232ae2de13L);
        try {
            MediaDrm wvDrm = new MediaDrm(wideVineUuid);
            byte[] wideVineId = wvDrm.getPropertyByteArray(MediaDrm.PROPERTY_DEVICE_UNIQUE_ID);
            String deviceID = Base64.getEncoder().encodeToString(wideVineId);
            return deviceID;
        } catch (Exception e) {
            return androidId(mContext);
        }

    }

    public String androidId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    public void NetworkError(final Activity context, String title, final String message) {
        new SweetAlertDialog(context, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                .setTitleText(title)
                .setContentText(message)
                .setCustomImage(R.drawable.ic_connection_lost_24dp)
                .show();
    }

    public void NetworkError(final Activity context) {
        new SweetAlertDialog(context, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                .setTitleText("Network Error!")
                .setContentText("Slow or No Internet Connection.")
                .setCustomImage(R.drawable.ic_connection_lost_24dp)
                .show();
    }


    public void Processing(final Activity context, final String message) {
        CustomAlertDialog customAlertDialog = new CustomAlertDialog(context, true);
        customAlertDialog.Warning(message);
    }

    public void Failed(final Activity context, final String message) {
        CustomAlertDialog customAlertDialog = new CustomAlertDialog(context, true);
        customAlertDialog.Failed(message);
    }

    public void Successful(final Activity context, final String message) {
        CustomAlertDialog customAlertDialog = new CustomAlertDialog(context, true);
        customAlertDialog.Successful(message);
    }

    public void SuccessfulWithTitle(final Activity context, final String title, final String message) {
        CustomAlertDialog customAlertDialog = new CustomAlertDialog(context, true);
        customAlertDialog.SuccessfulWithTitle(title, message);
    }

    public void ProcessingWithTitle(final Activity context, final String title, final String message) {
        CustomAlertDialog customAlertDialog = new CustomAlertDialog(context, true);
        customAlertDialog.Warning(title, message);
    }

    public void SuccessfulWithFinsh(final Activity context, final String message) {
        CustomAlertDialog customAlertDialog = new CustomAlertDialog(context, true);
        customAlertDialog.SuccessfulWithFinsh(message);
    }

    public void ErrorWithTitle(final Activity context, final String title, final String message) {
        CustomAlertDialog customAlertDialog = new CustomAlertDialog(context, true);
        customAlertDialog.ErrorWithTitle(title, message);
    }

    public void SuccessfulWithFinish(final String message, Activity activity, boolean isCancelable) {
        CustomAlertDialog customAlertDialog = new CustomAlertDialog(activity, true);
        customAlertDialog.SuccessfulWithFinsh(message, isCancelable);
    }

    public void Successfulok(final String message, Activity activity) {
        CustomAlertDialog customAlertDialog = new CustomAlertDialog(activity, true);
        customAlertDialog.Successfulok(message, activity);
    }

    public void Errorok(final Activity context, final String message, Activity activity) {
        CustomAlertDialog customAlertDialog = new CustomAlertDialog(context, true);
        customAlertDialog.Errorok(message, activity);
    }

    public void Error(final Activity context, final String message) {
        if (message.contains("(redirectToLogin)") || message.contains("(logout)")) {
            logout(context);
        } else {
            CustomAlertDialog customAlertDialog = new CustomAlertDialog(context, true);
            customAlertDialog.Error(message);
        }
    }

    public void Alert(final Activity context, final String message) {
        Log.e("messagealert", " messagealert " + message);
        new SweetAlertDialog(context, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                .setContentText(message)
                .setCustomImage(R.drawable.ic_done_black_24dp)
                .show();
    }

    public boolean isNetworkAvialable(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public boolean isValidMobile(String mobile) {

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String mobilePattern = "^(?:0091|\\\\+91|0)[7-9][0-9]{9}$";
        String mobileSecPattern = "[7-9][0-9]{9}$";

        return mobile.matches(mobilePattern) || mobile.matches(mobileSecPattern);
    }

    public boolean isValidEmail(String email) {

        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    public boolean Matcher(Context context, String pinpasscode) {
        SharedPreferences myPreferences = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, MODE_PRIVATE);
        String pinPass = myPreferences.getString(ApplicationConstant.INSTANCE.PinPasscode, null);

        return pinpasscode.equalsIgnoreCase(pinPass);
    }

    public void pinpasscode(Context context, String pinpasscode, boolean flagPinPasscodeFlag) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        if (pinpasscode.equalsIgnoreCase("")) {
            editor.putBoolean(ApplicationConstant.INSTANCE.PinPasscodeFlag, flagPinPasscodeFlag);
        } else {
            editor.putString(ApplicationConstant.INSTANCE.PinPasscode, pinpasscode);
        }

        editor.commit();
    }

    public String fetchOperator(Context context, String param) {
        int opid = 0;
        String circle = "";
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        String response = prefs.getString(ApplicationConstant.INSTANCE.numberListPref, null);
        NumberListResponse numberListResponse = new Gson().fromJson(response, NumberListResponse.class);
        for (NumberList numberList : numberListResponse.getData().getNumSeries()) {
            if (numberList.getSeries().equalsIgnoreCase(param)) {
                opid = numberList.getOid();
                circle = numberList.getCircleCode();
                break;
            }
        }
        return opid + "," + circle;
    }


    public String fetchShortCode(Context context, String operatorName) {
        String paramIReffOp = "";

        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        String response = prefs.getString(ApplicationConstant.INSTANCE.numberListPref, null);

        NumberListResponse numberListResponse = new Gson().fromJson(response, NumberListResponse.class);
//        for (NumberList numberList : numberListResponse.getNumberList()) {
//            // if (numberList.getOperator().equalsIgnoreCase(operatorName)&&numberList.getOperatorId()==opId) {
//            if (numberList.getOperator().equalsIgnoreCase(operatorName)) {
//
//                try {
//                    String str = numberList.getOperator();
//                    paramIReffOp = numberList.getIReffOp();
//                    Log.e("answer", paramIReffOp);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//                break;
//            }
//        }
        return paramIReffOp;
    }



    public void setDashboardStatus(Context context, boolean bool) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("Dashboard", bool);
        editor.commit();
    }

    public boolean isOnDashboard(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        return prefs.getBoolean("Dashboard", false);
    }


    public void setBalanceLowTime(Activity context, long data) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong(ApplicationConstant.INSTANCE.balanceLowTimePref, data);
        editor.commit();

    }

    public long getBalanceLowTime(Activity context) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        return prefs.getLong(ApplicationConstant.INSTANCE.balanceLowTimePref, 0);
    }

    public void setBalanceCheck(Context context, String balance) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.balancePref, balance);
        editor.commit();

    }

    public String getBalance(Activity activity){
        SharedPreferences myPreferences = activity.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        String balanceResponse = myPreferences.getString(ApplicationConstant.INSTANCE.balancePref, "");
        return balanceResponse;
    }
    public void setIsDMTWithPipe(Activity context, boolean data) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(ApplicationConstant.INSTANCE.isDMTWithPipePref, data);
        editor.commit();

    }

    public boolean getIsDMTWithPipe(Activity context) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        return prefs.getBoolean(ApplicationConstant.INSTANCE.isDMTWithPipePref, false);
    }

    public void setDMTOperatorList(Activity context, ArrayList<OperatorList> list) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.dmtOperatorListPref, new Gson().toJson(list));

        editor.commit();
    }

    public ArrayList<OperatorList> getDMTOperatorList(Activity context) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        String numberList = prefs.getString(ApplicationConstant.INSTANCE.dmtOperatorListPref, "");
        Type type = new TypeToken<ArrayList<OperatorList>>() {
        }.getType();
        return new Gson().fromJson(numberList, type);
    }
    public void seActiveService(Context context, String data, boolean isUpi, boolean isECollectEnable, boolean isPaymentGatway,boolean isAddMoneyEnable,boolean isUPIQR) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.activeServicePref, data);
        editor.putBoolean(ApplicationConstant.INSTANCE.isUpi, isUpi);
        editor.putBoolean(ApplicationConstant.INSTANCE.isUPIQR, isUPIQR);
        editor.putBoolean(ApplicationConstant.INSTANCE.isECollectEnable, isECollectEnable);
        editor.putBoolean(ApplicationConstant.INSTANCE.isPaymentGatway, isPaymentGatway);
        editor.putBoolean(ApplicationConstant.INSTANCE.isAddMoneyEnable, isAddMoneyEnable);
        editor.commit();

    }

    public boolean isUPiQR(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        return prefs.getBoolean(ApplicationConstant.INSTANCE.isUPIQR, false);

    }
    public boolean isUPi(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        return prefs.getBoolean(ApplicationConstant.INSTANCE.isUpi, false);

    }


    public boolean isPaymentGatway(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        return prefs.getBoolean(ApplicationConstant.INSTANCE.isPaymentGatway, false);

    }

    public boolean isECollectEnable(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        return prefs.getBoolean(ApplicationConstant.INSTANCE.isECollectEnable, false);

    }

    public void setPsaId(Context context, String data) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.psaIdPref, data);
        editor.commit();

    }

    public String getPsaId(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        return prefs.getString(ApplicationConstant.INSTANCE.psaIdPref, "24");

    }

    public void setIsLookUpFromAPI(Context context, boolean data) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(ApplicationConstant.INSTANCE.isLookUpFromAPIPref, data);
        editor.commit();

    }

    public boolean getIsLookUpFromAPI(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        return prefs.getBoolean(ApplicationConstant.INSTANCE.isLookUpFromAPIPref, false);
    }

    public void setDTHInfoPref(Context context, boolean value) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(ApplicationConstant.INSTANCE.DTHInfoPref, value);
        editor.commit();

    }

    public boolean getDTHInfoPref(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        return prefs.getBoolean(ApplicationConstant.INSTANCE.DTHInfoPref, false);

    }


    public void setROfferPref(Context context, boolean value) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(ApplicationConstant.INSTANCE.ROfferPref, value);
        editor.commit();

    }

    public boolean getROfferPref(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        return prefs.getBoolean(ApplicationConstant.INSTANCE.ROfferPref, false);

    }

    public void setIsDTHInfoAutoCall(Context context, boolean data) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(ApplicationConstant.INSTANCE.isDTHInfoAutoCallPref, data);
        editor.commit();

    }

    public boolean getIsDTHInfoAutoCall(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        return prefs.getBoolean(ApplicationConstant.INSTANCE.isDTHInfoAutoCallPref, false);
    }

    public void setIsShowPDFPlan(Context context, boolean data) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(ApplicationConstant.INSTANCE.isShowPDFPlanPref, data);
        editor.commit();

    }

    public boolean getIsShowPDFPlan(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        return prefs.getBoolean(ApplicationConstant.INSTANCE.isShowPDFPlanPref, false);
    }

    public void setIsAutoBilling(Context context, boolean data) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(ApplicationConstant.INSTANCE.isAutoBillingPref, data);
        editor.commit();

    }

    public boolean getIsAutoBilling(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        return prefs.getBoolean(ApplicationConstant.INSTANCE.isAutoBillingPref, false);
    }

    public void setIsReferral(Context context, boolean data) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(ApplicationConstant.INSTANCE.isReferralPref, data);
        editor.commit();

    }

    public boolean getIsReferral(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        return prefs.getBoolean(ApplicationConstant.INSTANCE.isReferralPref, false);
    }
    /*public void setSettlementAccountVerification(Activity context, boolean value) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(ApplicationConstant.INSTANCE.isSattlemntAccountVerifyPref, value);
        editor.commit();
    }
    public boolean getSettlementAccountVerification(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        return prefs.getBoolean(ApplicationConstant.INSTANCE.isSattlemntAccountVerifyPref, false);

    }*/
    public void setIsFlatCommission(Context context, boolean value) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(ApplicationConstant.INSTANCE.isFlatCommissionPref, value);
        editor.commit();

    }

    public boolean getIsFlatCommission(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        return prefs.getBoolean(ApplicationConstant.INSTANCE.isFlatCommissionPref, false);

    }


    public boolean CheckActiveOperator(Activity context, int id) {
        boolean bool = false;
        try {
            OpTypeResponse OpTypeResponse = new Gson().fromJson(UtilMethods.INSTANCE.getOPTypeResponse(context), OpTypeResponse.class);
            for (AssignedOpType at : OpTypeResponse.getData().getAssignedOpTypes()) {
                if (at.getServiceID() == id && !at.getIsActive()) {
                    bool = false;
                } else {
                    bool = true;
                }
            }
        } catch (Exception e) {
            UtilMethods.INSTANCE.Failed(context,e.getMessage());

        }
        return bool;
    }

    public String getActiveService(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        return prefs.getString(ApplicationConstant.INSTANCE.activeServicePref, "");
    }

    public void setCompanyProfile(Context context, String data) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.contactUsPref, data);
        editor.commit();

    }

    public String getCompanyProfile(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        return prefs.getString(ApplicationConstant.INSTANCE.contactUsPref, "");
    }

    public AppUserListResponse getCompanyProfileDetails(Context context) {
        AppUserListResponse response = null;
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        try {
            response = new Gson().fromJson(prefs.getString(ApplicationConstant.INSTANCE.contactUsPref, null), AppUserListResponse.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return response;
    }

    public void setFundreqToList(Context context, String FundreqTo) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.FundreqTo, FundreqTo);
        editor.commit();
    }

    public String getFundreqToList(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);

        return prefs.getString(ApplicationConstant.INSTANCE.FundreqTo, "");
    }

    private void openOTPDialog(final Activity context, final DialogCallBack dialogCallBack) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.verifyotp, null);

        edMobileOtp = view.findViewById(R.id.ed_mobile_otp);
        final TextInputLayout tilMobileOtp = view.findViewById(R.id.til_mobile_otp);
        final Button okButton = view.findViewById(R.id.okButton);
        final Button cancelButton = view.findViewById(R.id.cancelButton);
        final Dialog dialog = new Dialog(context);
        dialog.setCancelable(false);
        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        edMobileOtp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() < 6) {
                    tilMobileOtp.setError(context.getString(R.string.err_msg_otp));
                    okButton.setEnabled(false);
                } else {
                    tilMobileOtp.setErrorEnabled(false);
                    okButton.setEnabled(true);
                }
            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edMobileOtp.getText() != null && edMobileOtp.getText().length() == 6) {
                    tilMobileOtp.setErrorEnabled(false);
                    if(dialogCallBack!=null)
                        dialogCallBack.onPositiveClick(edMobileOtp.getText().toString());

                } else {
                    tilMobileOtp.setError(context.getString(R.string.err_msg_otp));
                }
            }
        });
        dialog.show();
    }

    public void setNumberList(Context context, String numberList,boolean isTakeCustomerNo) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.numberListPref, numberList);
        editor.putBoolean(ApplicationConstant.INSTANCE.isNumberListFetchPref, true);
        editor.putBoolean(ApplicationConstant.INSTANCE.TakeCustomerNoPref, isTakeCustomerNo);
        editor.commit();
    }

    public  boolean isTakeCustomerNumber(Context context){
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        return prefs.getBoolean(ApplicationConstant.INSTANCE.TakeCustomerNoPref,false);
    }


    public String getNumberList(Context context) {
        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        return myPrefs.getString(ApplicationConstant.INSTANCE.numberListPref, "");
    }

    public boolean getIsNumberListFetch(Context context) {
        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        return myPrefs.getBoolean(ApplicationConstant.INSTANCE.isNumberListFetchPref, false);
    }

    public void setFCMRegKey(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.regFCMKeyPref, key);
        editor.commit();
    }

    public String getFCMRegKey(Context context) {
        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        String key = myPrefs.getString(ApplicationConstant.INSTANCE.regFCMKeyPref, null);

        return key;
    }



    public void PaymentRequest(final Activity context, File selectedFile, int bankid, String txttranferAmount, String accountNumber, String transactionId, String ChecknumberID, String txtCardNo, String txtMobileNo, String txtAccountID, int PaymentModeID, String walletType, final View btnPaymentSubmit, final CustomLoader loader, final ApiCallBack mApiCallBack) {
        try {
            btnPaymentSubmit.setEnabled(false);
            int isImage = 0;
            if (selectedFile != null) {
                isImage = 1;
            }

            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);

            AddFundRequest mAddFundRequest = new AddFundRequest(isImage, bankid + "",
                    txttranferAmount, transactionId, txtMobileNo, ChecknumberID, txtCardNo, txtAccountID, accountNumber, PaymentModeID + "",
                    LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getLoginTypeID() + "",
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession(), walletType);

            String req = new Gson().toJson(mAddFundRequest);
            // Parsing any Media type file
            MultipartBody.Part fileToUpload = null;
            if (selectedFile != null) {
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), selectedFile);
                if (selectedFile != null) {
                    fileToUpload = MultipartBody.Part.createFormData("file", selectedFile.getName(), requestBody);
                }
            }
            RequestBody requestStr = RequestBody.create(MediaType.parse("text/plain"), req);
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<GetBankAndPaymentModeResponse> call = git.AppFundOrder(fileToUpload, requestStr);
            call.enqueue(new Callback<GetBankAndPaymentModeResponse>() {

                @Override
                public void onResponse(Call<GetBankAndPaymentModeResponse> call, retrofit2.Response<GetBankAndPaymentModeResponse> response) {
//
                    if (loader.isShowing())
                        loader.dismiss();

                    if (response.body() != null && response.body().getStatuscode() != null) {
                        if (response.body().getStatuscode().equalsIgnoreCase("1")) {
                            // setFundreqToList(context, new Gson().toJson(response.body()).toString());
                            SuccessfulWithFinish(response.body().getMsg() + "", context, false);
                            btnPaymentSubmit.setEnabled(false);
                            if (mApiCallBack != null) {
                                mApiCallBack.onSucess(response.body());
                            }
                            /*FragmentActivityMessage activityActivityMessage =
                                    new FragmentActivityMessage(new Gson().toJson(response.body()).toString(), "refreshvalue");
                            GlobalBus.getBus().post(activityActivityMessage);*/
                        } else if (response.body().getStatuscode().equalsIgnoreCase("-1")) {
                            btnPaymentSubmit.setEnabled(true);
                            if (response.body().getIsVersionValid() == false) {
                                versionDialog(context);
                            } else {
                                Error(context, response.body().getMsg() + "");
                            }
                        } else {
                            btnPaymentSubmit.setEnabled(true);
                        }

                    } else {
                        Error(context, context.getResources().getString(R.string.some_thing_error));
                        btnPaymentSubmit.setEnabled(true);
                    }
                }

                @Override
                public void onFailure(Call<GetBankAndPaymentModeResponse> call, Throwable t) {

                    btnPaymentSubmit.setEnabled(true);
                    if (loader.isShowing())
                        loader.dismiss();
                }
            });

        } catch (Exception e) {
            btnPaymentSubmit.setEnabled(true);
            e.printStackTrace();
        }

    }

    public void MyCommission(final Activity context, final CustomLoader loader, final ApiCallBack mApiCallBack) {
        try {
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<SlabCommissionResponse> call = git.DisplayCommission(new BalanceRequest("", LoginDataResponse.getData().getSlabID(),
                    LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getLoginTypeID() + "",
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));
            call.enqueue(new Callback<SlabCommissionResponse>() {

                @Override
                public void onResponse(Call<SlabCommissionResponse> call, retrofit2.Response<SlabCommissionResponse> response) {

                    if (loader!=null &&loader.isShowing())
                        loader.dismiss();

                    if (response.body() != null && response.body().getStatuscode() != null) {
                        if (response.body().getStatuscode() == 1 && response.body().getSlabDetailDisplayLvl() != null && response.body().getSlabDetailDisplayLvl().size() > 0) {
                            if (response.body().getSlabDetailDisplayLvl() != null && response.body().getSlabDetailDisplayLvl().size() > 0) {

                                setCommList(context, new Gson().toJson(response.body()));
                                if (mApiCallBack != null) {
                                    mApiCallBack.onSucess(response.body());
                                }
                            } else {
                                Error(context, "Data not found");
                            }
//
                        } else if (response.body().getStatuscode() == -1) {
                            if (response.body().getVersionValid() != null && response.body().getVersionValid() == false) {
                                if (context instanceof CommissionScreen) {
                                    versionDialog(context);
                                }
                            } else {
                                if (context instanceof CommissionScreen) {
                                    Error(context, response.body().getMsg() + "");
                                }
                            }
                        }

                    }
                }

                @Override
                public void onFailure(Call<SlabCommissionResponse> call, Throwable t) {
                    if (loader!=null &&loader.isShowing())
                        loader.dismiss();
                    if (context instanceof CommissionScreen) {
                        try {
                            if (t.getMessage() != null && !t.getMessage().isEmpty()) {

                                if (t.getMessage().contains("No address associated with hostname")) {
                                    NetworkError(context, context.getResources().getString(R.string.err_msg_network_title),
                                            context.getResources().getString(R.string.err_msg_network));
                                } else {
                                    Error(context, t.getMessage());

                                }

                            } else {
                                Error(context, context.getResources().getString(R.string.some_thing_error));

                            }
                        } catch (IllegalStateException ise) {
                            Error(context, ise.getMessage());

                        }
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            if (loader!=null &&loader.isShowing())
                loader.dismiss();
        }

    }

    public void GetActiveService(final Activity context, final ApiCallBackTwoMethod mApiCallBack) {
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            Call<OpTypeResponse> call = git.GetOpTypes(new BasicRequest(
                    LoginDataResponse.getData().getUserID(), LoginDataResponse.getData().getLoginTypeID(),
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));
            call.enqueue(new Callback<OpTypeResponse>() {

                @Override
                public void onResponse(Call<OpTypeResponse> call, retrofit2.Response<OpTypeResponse> response) {

                    OpTypeResponse apiData = response.body();
                    boolean isAepsReport=false,isDMTReport = false,isDTHSubscriptionReport=false,isMoveToBankEnable=false,isUPIPay=false;
                    UtilMethods.INSTANCE.setOPTypeResponse(context, new Gson().toJson(response.body()));

                    if (apiData != null) {
                        if (apiData.getStatuscode() == 1 && apiData.getData()!=null && apiData.getData().getAssignedOpTypes()!=null && apiData.getData().getAssignedOpTypes().size()>0) {
                            ArrayList<Integer> groupServiceId = new ArrayList<>();

                            /*RT*/
                            ArrayList<AssignedOpType> dashBoardBankingFundList=new ArrayList<>();
                            ArrayList<AssignedOpType> dashBoardRTReportList = new ArrayList<>();
                            ArrayList<AssignedOpType> dashBoardRTRechargeBillPayOption=new ArrayList<>();
                            /*Fos*/
                            ArrayList<AssignedOpType> dashBoardFosQuickList = new ArrayList<>();

                            /*Other*/
                            ArrayList<AssignedOpType> dashBoardOtherReportList = new ArrayList<>();
                            ArrayList<AssignedOpType> dashBoardOtherServiceList = new ArrayList<>();
                            ArrayList<AssignedOpType> dashBoardOtherBankingFundList=new ArrayList<>();
                            dashBoardBankingFundList.add(new AssignedOpType(100,"Fund Request",true,true))
;                            for(AssignedOpType item:apiData.getData().getAssignedOpTypes()){
                                if(item.getServiceID()==14){ //DMT
                                    isDMTReport=true;
                                    dashBoardBankingFundList.add(item);
                                }else if(item.getServiceID()==22){ //AEPS
                                    isAepsReport=true;
                                    isMoveToBankEnable=true;
                                    dashBoardBankingFundList.add(item);
                                }else if(item.getServiceID()==24){ //PSA
                                    dashBoardBankingFundList.add(item);
                                }else if(item.getServiceID()==44){ //Mini ATM
                                    isMoveToBankEnable=true;
                                    dashBoardBankingFundList.add(item);
                                }else if(item.getServiceID()==62){ //UPI Pay
                                    dashBoardBankingFundList.add(item);
                                    dashBoardBankingFundList.add(new AssignedOpType(63, "Scan & Pay", item.getIsActive(), item.getIsServiceActive()));
                                    isUPIPay=true;
                                }else{
                                    if(item.getServiceID()==35 || item.getServiceID()==36){ //Mini ATM
                                        isDTHSubscriptionReport=true;
                                    }

                                    if(item.getParentID()!=31){

                                        if (item.getDisplayService() && !groupServiceId.contains(item.getServiceID())) {
                                            ArrayList<AssignedOpType> selectedSubAssignOpType = new ArrayList<>();
                                            for (AssignedOpType subItem : apiData.getData().getAssignedOpTypes()) {
                                                if (subItem.getParentID() == item.getParentID() && subItem.getDisplayService()) {
                                                    groupServiceId.add(subItem.getServiceID());
                                                    selectedSubAssignOpType.add(subItem);
                                                }
                                            }
                                            dashBoardRTRechargeBillPayOption.add(new AssignedOpType(item.getServiceID(), item.getParentID(), item.getName(), item.getService(), item.getIsServiceActive(), item.getIsActive(), item.getDisplayService(), item.getUpline(), item.getUplineMobile(), item.getCcContact(), selectedSubAssignOpType));
                                        }
                                        else if (!groupServiceId.contains(item.getServiceID())) {
                                            dashBoardRTRechargeBillPayOption.add(new AssignedOpType(item.getServiceID(), item.getParentID(), item.getName(), item.getService(), item.getIsServiceActive(), item.getIsActive(), item.getIsServiceActive(), item.getUpline(), item.getUplineMobile(), item.getCcContact(), new ArrayList<>()));
                                        }

                                    }

                                }
                            }
                            if(apiData.isAddMoneyEnable()){
                                dashBoardBankingFundList.add(new AssignedOpType(37,"Add Money",true,true));
                            }

                            /*if(isUPIPay)
                                dashBoardBankingFundList.add(new AssignedOpType(63, "Scan & Pay", true, true));*/
                            dashBoardFosQuickList.addAll(apiData.getData().getFOSOptions(LoginDataResponse.isAccountStatement()));

                            /*RT*/
                            dashBoardRTReportList.addAll(apiData.getData().getRetailerReportOption(isDMTReport,isAepsReport,isDTHSubscriptionReport));

                            /*Other*/
                            dashBoardOtherBankingFundList.addAll(apiData.getData().getOtherBankingFundOption(apiData.isAddMoneyEnable,isUPIPay));
                            dashBoardOtherServiceList.addAll(apiData.getData().getOtherServiceOptions(isDMTReport,isAepsReport,isDTHSubscriptionReport,LoginDataResponse.isAccountStatement()));

                            dashBoardOtherReportList.addAll(apiData.getData().getOtherReportOption(isDMTReport,isAepsReport,isDTHSubscriptionReport,LoginDataResponse.isAccountStatement()));


                            CustomAllTypeService allTypeService = new CustomAllTypeService(dashBoardRTRechargeBillPayOption,dashBoardBankingFundList,dashBoardRTReportList,dashBoardOtherBankingFundList,dashBoardOtherServiceList,dashBoardOtherReportList,dashBoardFosQuickList,apiData.isUPIQR(),apiData.isUPI(),apiData.isPaymentGatway(),apiData.isDMTWithPipe(),apiData.isECollectEnable(),isDMTReport,isAepsReport,isDTHSubscriptionReport,isMoveToBankEnable);



                            seActiveService(context, new Gson().toJson(allTypeService), apiData.isUPI(), apiData.isECollectEnable(), apiData.isPaymentGatway(),apiData.isAddMoneyEnable(),apiData.isUPIQR());

                            setIsDMTWithPipe(context, apiData.isDMTWithPipe());
                            if (mApiCallBack != null) {
                                mApiCallBack.onSucess(allTypeService);
                            }

//
                        } else if (apiData.getStatuscode() == -1) {
                            if (apiData.getVersionValid() == false) {
                                versionDialog(context);
                            }

                            if (mApiCallBack != null) {
                                mApiCallBack.onError(response.body());
                            }
                        }else{
                            if (mApiCallBack != null) {
                                mApiCallBack.onError(response.body());
                            }
                        }

                    }
                }

                @Override
                public void onFailure(Call<OpTypeResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (mApiCallBack != null) {
                        mApiCallBack.onError(null);
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            if (mApiCallBack != null) {
                mApiCallBack.onError(null);
            }
        }

    }


    private void setOPTypeResponse(Context context, String toString) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.OpTypePref, toString);
        editor.commit();
    }
    public String getOPTypeResponse(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        return prefs.getString(ApplicationConstant.INSTANCE.OpTypePref, "");
    }

    public void WalletType(final Activity context, final CustomLoader loader, final ApiCallBack mApiCallBack) {
        try {
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<WalletTypeResponse> call = git.GetWalletType(new BalanceRequest("", LoginDataResponse.getData().getSlabID(),
                    LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getLoginTypeID() + "",
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));
            call.enqueue(new Callback<WalletTypeResponse>() {

                @Override
                public void onResponse(Call<WalletTypeResponse> call, retrofit2.Response<WalletTypeResponse> response) {

                    if (loader != null && loader.isShowing())
                        loader.dismiss();

                    if (response.body() != null && response.body().getStatuscode() != null) {
                        if (response.body().getStatuscode() == 1) {
                            if (response.body().getWalletTypes() != null && response.body().getWalletTypes().size() > 0) {

                                if (mApiCallBack != null) {

                                    mApiCallBack.onSucess(response.body());
                                }

                            }


                            setWalletType(context, new Gson().toJson(response.body()));
                        } else if (response.body().getStatuscode() == -1) {
                            if (response.body().getVersionValid() != null && response.body().getVersionValid() == false) {
                                if (!(context instanceof DashBoardLayout)) {
                                    versionDialog(context);
                                }
                            } else {
                                if (!(context instanceof DashBoardLayout)) {
                                    Error(context, response.body().getMsg() + "");
                                }
                            }
                        }

                    }
                }

                @Override
                public void onFailure(Call<WalletTypeResponse> call, Throwable t) {

                    if (loader != null && loader.isShowing())
                        loader.dismiss();
                    if (!(context instanceof DashBoardLayout)) {

                        try {
                            if (t.getMessage() != null && !t.getMessage().isEmpty()) {

                                if (t.getMessage().contains("No address associated with hostname")) {
                                    NetworkError(context, context.getResources().getString(R.string.err_msg_network_title),
                                            context.getResources().getString(R.string.err_msg_network));
                                } else {
                                    Error(context, t.getMessage());

                                }

                            } else {
                                Error(context, context.getResources().getString(R.string.some_thing_error));

                            }
                        } catch (IllegalStateException ise) {
                            Error(context, ise.getMessage());

                        }
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void GetCompanyProfile(final Activity context, final ApiCallBack mApiCallBack) {
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            Call<AppUserListResponse> call = git.GetCompanyProfile(new BasicRequest(
                    LoginDataResponse.getData().getUserID(), LoginDataResponse.getData().getLoginTypeID(),
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));
            call.enqueue(new Callback<AppUserListResponse>() {

                @Override
                public void onResponse(Call<AppUserListResponse> call, retrofit2.Response<AppUserListResponse> response) {

                    AppUserListResponse apiData = response.body();
                    if (apiData != null && apiData.getStatuscode() != null) {
                        if (apiData.getStatuscode() == 1) {
                            setCompanyProfile(context, new Gson().toJson(apiData));
                            if (mApiCallBack != null) {
                                mApiCallBack.onSucess(apiData);
                            }

//
                        } else if (apiData.getStatuscode() == -1) {
                            if (!(context instanceof DashBoardLayout)) {
                                if (apiData.getVersionValid() != null && apiData.getVersionValid() == false) {
                                    versionDialog(context);
                                } else {
                                    Error(context, apiData.getMsg() + "");
                                }
                            }
                        }

                    }
                }

                @Override
                public void onFailure(Call<AppUserListResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (!(context instanceof DashBoardLayout)) {
                        try {
                            if (t.getMessage() != null && !t.getMessage().isEmpty()) {

                                if (t.getMessage().contains("No address associated with hostname")) {
                                    NetworkError(context, context.getResources().getString(R.string.err_msg_network_title),
                                            context.getResources().getString(R.string.err_msg_network));
                                } else {
                                    Error(context, t.getMessage());

                                }

                            } else {
                                Error(context, context.getResources().getString(R.string.some_thing_error));

                            }
                        } catch (IllegalStateException ise) {
                            Error(context, ise.getMessage());

                        }
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void GetNotifications(final Activity context, final ApiCallBack mApiCallBack) {
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            Call<AppUserListResponse> call = git.GetAppNotification(new BasicRequest(
                    LoginDataResponse.getData().getUserID(), LoginDataResponse.getData().getLoginTypeID(),
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));
            call.enqueue(new Callback<AppUserListResponse>() {

                @Override
                public void onResponse(Call<AppUserListResponse> call, retrofit2.Response<AppUserListResponse> response) {

                    AppUserListResponse apiData = response.body();
                    if (apiData != null && apiData.getStatuscode() != null) {
                        if (apiData.getStatuscode() == 1) {
                            if (mApiCallBack != null) {
                                ArrayList<Integer> visibleId = new ArrayList<>();
                                int visibleCount = 0;
                                AppUserListResponse storedData = new Gson().fromJson(getNotificationList(context), AppUserListResponse.class);
                                if (storedData != null && storedData.getNotifications() != null && storedData.getNotifications().size() > 0) {

                                    for (int i = 0; i < storedData.getNotifications().size(); i++) {
                                        if (storedData.getNotifications().get(i).isSeen()) {
                                            visibleId.add(storedData.getNotifications().get(i).getId());
                                        }
                                    }
                                }
                                if (visibleId.size() > 0) {
                                    for (int i = 0; i < apiData.getNotifications().size(); i++) {
                                        if (visibleId.contains(apiData.getNotifications().get(i).getId())) {
                                            apiData.getNotifications().get(i).setSeen(true);
                                            visibleCount++;
                                        }
                                    }
                                }
                                setNotificationList(context, new Gson().toJson(apiData));

                                mApiCallBack.onSucess(apiData.getNotifications().size() - visibleCount);
                            }

//
                        } else if (apiData.getStatuscode() == -1) {
                            if (apiData.getVersionValid() != null && apiData.getVersionValid() == false) {
                                versionDialog(context);
                            } else {
                                Error(context, apiData.getMsg() + "");
                            }
                        }

                    }
                }

                @Override
                public void onFailure(Call<AppUserListResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    try {
                        if (t.getMessage() != null && !t.getMessage().isEmpty()) {

                            if (t.getMessage().contains("No address associated with hostname")) {
                                NetworkError(context, context.getResources().getString(R.string.err_msg_network_title),
                                        context.getResources().getString(R.string.err_msg_network));
                            } else {
                                Error(context, t.getMessage());

                            }

                        } else {
                            Error(context, context.getResources().getString(R.string.some_thing_error));

                        }
                    } catch (IllegalStateException ise) {
                        Error(context, ise.getMessage());

                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void GetVADetails(final Activity context, final CustomLoader loader, final ApiCallBack mApiCallBack) {
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            Call<GetVAResponse> call = git.GetVADetail(new BasicRequest(
                    LoginDataResponse.getData().getUserID(), LoginDataResponse.getData().getLoginTypeID(),
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));
            call.enqueue(new Callback<GetVAResponse>() {

                @Override
                public void onResponse(Call<GetVAResponse> call, retrofit2.Response<GetVAResponse> response) {
                    if (loader != null && loader.isShowing()) {
                        loader.dismiss();
                    }
                    GetVAResponse apiData = response.body();
                    if (apiData != null) {
                        if (apiData.getStatuscode() == 1) {
                            if (mApiCallBack != null) {
                                mApiCallBack.onSucess(apiData.getUserQRInfo());
                            }

//
                        } else if (apiData.getStatuscode() == -1) {
                            if (apiData.getVersionValid() == false) {
                                versionDialog(context);
                            } else {
                                Error(context, apiData.getMsg() + "");
                            }
                        }

                    }
                }

                @Override
                public void onFailure(Call<GetVAResponse> call, Throwable t) {
                    if (loader != null && loader.isShowing()) {
                        loader.dismiss();
                    }
                    try {
                        if (t.getMessage() != null && !t.getMessage().isEmpty()) {

                            if (t.getMessage().contains("No address associated with hostname")) {
                                NetworkError(context, context.getResources().getString(R.string.err_msg_network_title),
                                        context.getResources().getString(R.string.err_msg_network));
                            } else {
                                Error(context, t.getMessage());

                            }

                        } else {
                            Error(context, context.getResources().getString(R.string.some_thing_error));

                        }
                    } catch (IllegalStateException ise) {
                        Error(context, ise.getMessage());

                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void IntiateUPI(final Activity context, String walletID, String oid, String amount, String upiid, final CustomLoader loader, final ApiCallBack mApiCallBack) {
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            Call<InitiateUpiResponse> call = git.IntiateUPI(new IntiateUPIRequest(walletID, oid, amount, upiid,
                    LoginDataResponse.getData().getUserID(), LoginDataResponse.getData().getLoginTypeID(),
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));
            call.enqueue(new Callback<InitiateUpiResponse>() {

                @Override
                public void onResponse(Call<InitiateUpiResponse> call, retrofit2.Response<InitiateUpiResponse> response) {
                    if (loader != null && loader.isShowing()) {
                        loader.dismiss();
                    }
                    InitiateUpiResponse apiData = response.body();
                    if (apiData != null) {
                        if (apiData.getStatuscode() == 1) {
                            if (mApiCallBack != null) {
                                mApiCallBack.onSucess(apiData);
                            }

//
                        } else if (apiData.getStatuscode() == -1) {
                            if (apiData.isVersionValid() == false) {
                                versionDialog(context);
                            } else {
                                Error(context, apiData.getMsg() + "");
                            }
                        }

                    }
                }

                @Override
                public void onFailure(Call<InitiateUpiResponse> call, Throwable t) {
                    if (loader != null && loader.isShowing()) {
                        loader.dismiss();
                    }
                    try {
                        if (t.getMessage() != null && !t.getMessage().isEmpty()) {

                            if (t.getMessage().contains("No address associated with hostname")) {
                                NetworkError(context, context.getResources().getString(R.string.err_msg_network_title),
                                        context.getResources().getString(R.string.err_msg_network));
                            } else {
                                Error(context, t.getMessage());

                            }

                        } else {
                            Error(context, context.getResources().getString(R.string.some_thing_error));

                        }
                    } catch (IllegalStateException ise) {
                        Error(context, ise.getMessage());

                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void UPIPaymentUpdate(final Activity context, String tid, String bankStatus, final CustomLoader loader, final ApiCallBack mApiCallBack) {
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            Call<InitiateUpiResponse> call = git.UPIPaymentUpdate(new UpdateUPIRequest(tid, bankStatus,
                    LoginDataResponse.getData().getUserID(), LoginDataResponse.getData().getLoginTypeID(),
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context),
                    LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));
            call.enqueue(new Callback<InitiateUpiResponse>() {

                @Override
                public void onResponse(Call<InitiateUpiResponse> call, retrofit2.Response<InitiateUpiResponse> response) {
                    if (loader != null && loader.isShowing()) {
                        loader.dismiss();
                    }
                    InitiateUpiResponse apiData = response.body();
                    if (apiData != null) {
                        if (apiData.getStatuscode() == 1) {
                            if (mApiCallBack != null) {
                                mApiCallBack.onSucess(apiData);
                            }

//
                        } else if (apiData.getStatuscode() == -1) {
                            if (apiData.isVersionValid() == false) {
                                versionDialog(context);
                            } else {
                                Error(context, apiData.getMsg() + "");
                            }
                        }

                    }
                }

                @Override
                public void onFailure(Call<InitiateUpiResponse> call, Throwable t) {
                    if (loader != null && loader.isShowing()) {
                        loader.dismiss();
                    }
                    try {
                        if (t.getMessage() != null && !t.getMessage().isEmpty()) {

                            if (t.getMessage().contains("No address associated with hostname")) {
                                NetworkError(context, context.getResources().getString(R.string.err_msg_network_title),
                                        context.getResources().getString(R.string.err_msg_network));
                            } else {
                                Error(context, t.getMessage());

                            }

                        } else {
                            Error(context, context.getResources().getString(R.string.some_thing_error));

                        }
                    } catch (IllegalStateException ise) {
                        Error(context, ise.getMessage());

                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void Dispute(final Activity context, final String transactionId, final String tid, final CustomLoader loader, final ApiCallBack mApiCallBack) {
        try {
            loader.show();
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<RefundRequestResponse> call = git.RefundRequest(new RefundRequestRequest(tid, transactionId, LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getLoginTypeID() + "",
                    ApplicationConstant.INSTANCE.APP_ID,
                    UtilMethods.INSTANCE.getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));

            call.enqueue(new Callback<RefundRequestResponse>() {

                @Override
                public void onResponse(Call<RefundRequestResponse> call, retrofit2.Response<RefundRequestResponse> response) {

                    if (loader.isShowing())
                        loader.dismiss();

                    if (response.body() != null) {
                        if (response.body().getStatuscode() == "1") {

                            if (mApiCallBack != null) {
                                mApiCallBack.onSucess(response.body());
                            }
                            Successful(context, response.body().getMsg() + "");


                        } else if (response.body().getStatuscode() == "-1") {
                            if (response.body().isVersionValid() == false) {
                                versionDialog(context);
                            } else {
                                Error(context, response.body().getMsg() + "");
                            }
                        }

                    }
                }

                @Override
                public void onFailure(Call<RefundRequestResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (loader.isShowing())
                        loader.dismiss();
                    try {
                        if (t.getMessage() != null && !t.getMessage().isEmpty()) {

                            if (t.getMessage().contains("No address associated with hostname")) {
                                NetworkError(context, context.getResources().getString(R.string.err_msg_network_title),
                                        context.getResources().getString(R.string.err_msg_network));
                            } else {
                                Error(context, t.getMessage());

                            }

                        } else {
                            Error(context, context.getResources().getString(R.string.some_thing_error));

                        }
                    } catch (IllegalStateException ise) {
                        Error(context, ise.getMessage());

                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void FetchBillApi(final Activity context, String oid, String accountNum, String o1, String o2, String o3,
                             String o4, String geoCode, String amount, final String customerNo, final CustomLoader loader, final ApiCallBackTwoMethod mApiCallBack) {
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            String LoginResponse = UtilMethods.INSTANCE.getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            Call<FetchBillResponse> call = git.FetchBill(new FetchBillRequest(oid, accountNum, o1, o2, o3,
                    o4, geoCode, amount, LoginDataResponse.getData().getOutletID(),
                    LoginDataResponse.getData().getUserID(), LoginDataResponse.getData().getLoginTypeID(),
                    ApplicationConstant.INSTANCE.APP_ID,
                    UtilMethods.INSTANCE.getIMEI(context),
                    "", BuildConfig.VERSION_NAME, UtilMethods.INSTANCE.getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession(),customerNo));
            call.enqueue(new Callback<FetchBillResponse>() {

                @Override
                public void onResponse(Call<FetchBillResponse> call, retrofit2.Response<FetchBillResponse> response) {
                    if (loader != null && loader.isShowing()) {
                        loader.dismiss();
                    }
                    FetchBillResponse apiData = response.body();

                    try {
                        if (apiData != null) {
                            if (apiData.getStatuscode() != null && apiData.getStatuscode() == 1) {
                                if (apiData.getbBPSResponse() != null) {
                                    if (apiData.getbBPSResponse().getStatuscode() == 1) {
                                        if (apiData.getbBPSResponse().isEnablePayment()) {
                                            if (mApiCallBack != null) {
                                                mApiCallBack.onSucess(apiData);
                                            }
                                        } else {
                                            if (apiData.getbBPSResponse().isShowMsgOnly()) {
                                                if (mApiCallBack != null) {
                                                    if (apiData.getbBPSResponse().getErrmsg() != null && !apiData.getbBPSResponse().getErrmsg().isEmpty()) {
                                                        mApiCallBack.onError(apiData.getbBPSResponse().getErrmsg());

                                                    } else if (apiData.getbBPSResponse().getMsg() != null && !apiData.getbBPSResponse().getMsg().isEmpty()) {
                                                        mApiCallBack.onError(apiData.getbBPSResponse().getMsg() + "");
                                                    } else {
                                                        mApiCallBack.onError(context.getResources().getString(R.string.some_thing_error));
                                                    }

                                                }

                                            }

                                        }
                                    } else if (apiData.getbBPSResponse().getStatuscode() == -1) {
                                        if (apiData.getbBPSResponse().isShowMsgOnly()) {
                                            if (mApiCallBack != null) {
                                                if (apiData.getbBPSResponse().getErrmsg() != null && !apiData.getbBPSResponse().getErrmsg().isEmpty()) {
                                                    mApiCallBack.onError(apiData.getbBPSResponse().getErrmsg());

                                                } else if (apiData.getbBPSResponse().getMsg() != null && !apiData.getbBPSResponse().getMsg().isEmpty()) {
                                                    mApiCallBack.onError(apiData.getbBPSResponse().getMsg() + "");
                                                } else {
                                                    mApiCallBack.onError(context.getResources().getString(R.string.some_thing_error));
                                                }

                                            }

                                        } else {
                                            if (mApiCallBack != null) {

                                                if (apiData.getbBPSResponse().getMsg() != null && !apiData.getbBPSResponse().getMsg().isEmpty()) {
                                                    mApiCallBack.onError(apiData.getbBPSResponse().getMsg() + "");
                                                }
                                            }
                                        }
                                    } else {
                                        if (mApiCallBack != null) {

                                            if (apiData.getbBPSResponse().getMsg() != null && !apiData.getbBPSResponse().getMsg().isEmpty()) {
                                                mApiCallBack.onError(apiData.getbBPSResponse().getMsg() + "");
                                            }
                                        }
                                    }
                                }
                            } else {
                                if (apiData.getVersionValid() != null && apiData.getVersionValid() == false) {
                                    versionDialog(context);
                                } else {
                                    UtilMethods.INSTANCE.Error(context, apiData.getMsg() + "");
                                }
                            }
                        }
                    } catch (Exception ex) {
                        if (loader != null && loader.isShowing()) {
                            loader.dismiss();
                        }
                        mApiCallBack.onError(ex.getMessage() + "");
                    }





                    /*if (apiData != null) {   // commented on 24-12-2019 accoding to new requirement.....
                        if (apiData.getStatuscode() == 1 && apiData.getbBPSResponse() != null && apiData.getbBPSResponse().getStatuscode() == 1) {
                            if (mApiCallBack != null) {
                                mApiCallBack.onSucess(apiData);
                            }

                        } else if (apiData.getStatuscode() == 1 && apiData.getbBPSResponse() != null && apiData.getbBPSResponse().getStatuscode() != 1) {
                            if (mApiCallBack != null) {
                                mApiCallBack.onError(apiData.getbBPSResponse().getErrmsg());
                            }

//
                        } else if (apiData.getStatuscode() == -1) {
                            if (apiData.getVersionValid() != null && apiData.getVersionValid() == false) {
                                versionDialog(context);
                            } else {
                                if (mApiCallBack != null) {
                                    if (apiData.getbBPSResponse() != null && apiData.getbBPSResponse().getErrmsg() != null) {
                                        mApiCallBack.onError(apiData.getbBPSResponse().getErrmsg());
                                    } else if (apiData.getMsg() != null) {
                                        mApiCallBack.onError(apiData.getMsg());
                                    } else {
                                        mApiCallBack.onError(context.getResources().getString(R.string.some_thing_error));
                                    }

                                }
                                //UtilMethods.INSTANCE.Error(context, apiData.getMsg() + "");
                            }
                        } else {
                            if (mApiCallBack != null) {
                                if (apiData.getbBPSResponse() != null && apiData.getbBPSResponse().getErrmsg() != null) {
                                    mApiCallBack.onError(apiData.getbBPSResponse().getErrmsg());
                                } else if (apiData.getMsg() != null) {
                                    mApiCallBack.onError(apiData.getMsg());
                                } else {
                                    mApiCallBack.onError(context.getResources().getString(R.string.some_thing_error));
                                }

                            }
                           // UtilMethods.INSTANCE.Error(context, apiData.getMsg() + "");
                        }

                    } else {
                        if (mApiCallBack != null) {
                            mApiCallBack.onError(context.getResources().getString(R.string.some_thing_error));
                        }
                        //UtilMethods.INSTANCE.Error(context, context.getResources().getString(R.string.some_thing_error));
                    }*/
                }

                @Override
                public void onFailure(Call<FetchBillResponse> call, Throwable t) {
                    if (loader != null && loader.isShowing()) {
                        loader.dismiss();
                    }

                    displayingOnFailuireMessage(context, t,mApiCallBack);

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            if (loader != null && loader.isShowing()) {
                loader.dismiss();
            }
        }

    }

    private void setCommList(Context context, String toString) {
//        Gson gson = new Gson();
//        RechargeReportResponse transactions = gson.fromJson(toString, RechargeReportResponse.class);
//        ArrayList<SlabCommtObject> transactionsObjects = transactions.getSlabCommissions();
//        ArrayList<SlabCommtObject> transactionsNewObjects = new ArrayList<>();
//        for (int i = 0; i < transactionsObjects.size(); i++) {
//            SlabCommtObject slabCommtObject = new SlabCommtObject();
//            String operatorName = getname(transactionsObjects.get(i).getOid(), context);
//            slabCommtObject.setOperatorName(operatorName);
//            slabCommtObject.setComm(transactionsObjects.get(i).getComm());
//            slabCommtObject.setAmtType(transactionsObjects.get(i).getAmtType());
//            slabCommtObject.setCommType(transactionsObjects.get(i).getCommType());
//            slabCommtObject.setOid(transactionsObjects.get(i).getOid());
//            transactionsNewObjects.add(slabCommtObject);
//        }

        //   String response = new Gson().toJson(transactionsNewObjects).toString();
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.commList, toString);
        editor.commit();
    }

    private void setWalletType(Context context, String toString) {

        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.walletType, toString);
        editor.commit();
    }

    public String getWalletType(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        return prefs.getString(ApplicationConstant.INSTANCE.walletType, "");

    }

    public String getCommList(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        return prefs.getString(ApplicationConstant.INSTANCE.commList, "");

    }

    public void setNotificationList(Context context, String toString) {

        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.notificationListPref, toString);
        editor.commit();
    }

    public String getNotificationList(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        return prefs.getString(ApplicationConstant.INSTANCE.notificationListPref, "");

    }



    public void GetSender(final Activity context, final String MobileNumber, final CustomLoader loader) {
        try {
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<CreateSenderResponse> call = git.GetSender(new GetSenderRequest(new Senderobject(MobileNumber),
                    LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getLoginTypeID() + "",
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));

            call.enqueue(new Callback<CreateSenderResponse>() {

                @Override
                public void onResponse(Call<CreateSenderResponse> call, retrofit2.Response<CreateSenderResponse> response) {

                    if (loader.isShowing())
                        loader.dismiss();

                    if (response.body() != null && response.body().getStatuscode() != null) {
                        if (response.body().getStatuscode().equalsIgnoreCase("1")) {
                            if (!response.body().isSenderNotExists()) {
                                setSenderNumber(context, MobileNumber, response.body().getSenderName(), response.body().getSenderBalance(), response.body().toString());
                                GetBanklist(context, loader, null);
                                ActivityActivityMessage activityActivityMessage =
                                        new ActivityActivityMessage(MobileNumber + "," + response.body().getSenderName() + "," + response.body().getSenderBalance(), "GetSender");
                                GlobalBus.getBus().post(activityActivityMessage);

                            } else {
                                Error(context, response.body().getMsg() + "");
                                ActivityActivityMessage activityActivityMessage =
                                        new ActivityActivityMessage("", "CallgetSenderSender");
                                GlobalBus.getBus().post(activityActivityMessage);
                            }

                        } else if (response.body().getStatuscode().equalsIgnoreCase("-1")) {
                            if (response.body().getIsVersionValid() != null && response.body().getIsVersionValid().equalsIgnoreCase("false")) {
                                versionDialog(context);
                            } else {
                                Error(context, response.body().getMsg() + "");
                            }
                        }

                    }
                }

                @Override
                public void onFailure(Call<CreateSenderResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (loader.isShowing())
                        loader.dismiss();
                    try {
                        if (t.getMessage() != null && !t.getMessage().isEmpty()) {

                            if (t.getMessage().contains("No address associated with hostname")) {
                                NetworkError(context, context.getResources().getString(R.string.err_msg_network_title),
                                        context.getResources().getString(R.string.err_msg_network));
                            } else {
                                Error(context, t.getMessage());

                            }

                        } else {
                            Error(context, context.getResources().getString(R.string.some_thing_error));

                        }
                    } catch (IllegalStateException ise) {
                        Error(context, ise.getMessage());

                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void GetSenderNew(final Activity context, String oid, final String MobileNumber, final CustomLoader loader) {
        try {
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<CreateSenderResponse> call = git.GetSenderNew(new GetSenderRequest(oid, new Senderobject(MobileNumber),
                    LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getLoginTypeID() + "",
                    ApplicationConstant.INSTANCE.APP_ID, getIMEI(context), "", BuildConfig.VERSION_NAME, getSerialNo(context),
                    LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));

            call.enqueue(new Callback<CreateSenderResponse>() {

                @Override
                public void onResponse(Call<CreateSenderResponse> call, retrofit2.Response<CreateSenderResponse> response) {

                    if (loader.isShowing())
                        loader.dismiss();
                    if (response.isSuccessful()) {
                        if (response.body() != null && response.body().getStatuscode() != null) {
                            if (response.body().getStatuscode().equalsIgnoreCase("1")) {
                                if (!response.body().isSenderNotExists()) {
                                    setSenderNumber(context, MobileNumber, response.body().getSenderName(), response.body().getSenderBalance(), response.body().toString());
                                    GetBanklist(context, loader, null);
                                    ActivityActivityMessage activityActivityMessage =
                                            new ActivityActivityMessage(MobileNumber + "," + response.body().getSenderName() + "," + response.body().getSid() + "," + response.body().getAvailbleLimit() + "," + response.body().getRemainingLimit(), "GetSender");
                                    GlobalBus.getBus().post(activityActivityMessage);

                                } else {
                                    //Error(context, response.body().getMsg() + "");
                                    ActivityActivityMessage activityActivityMessage =
                                            new ActivityActivityMessage("", "CallgetSenderSender");
                                    GlobalBus.getBus().post(activityActivityMessage);
                                }

                            } else if (response.body().getStatuscode().equalsIgnoreCase("-1")) {
                                if (response.body().getIsVersionValid() != null && response.body().getIsVersionValid().equalsIgnoreCase("false")) {
                                    versionDialog(context);
                                } else {
                                    Error(context, response.body().getMsg() + "");
                                }
                            }

                        }
                    } else {
                        apiErrorHandle(context, response.code(), response.message());
                    }
                }

                @Override
                public void onFailure(Call<CreateSenderResponse> call, Throwable t) {

                    if (loader.isShowing())
                        loader.dismiss();
                    apiFailureError(context, t);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void GetBeneficiary(final Activity context, String MobileNumber, final CustomLoader loader,final ApiCallBack apiCallBack) {
        try {
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<RechargeReportResponse> call = git.GetBeneficiary(new GetSenderRequest(new Senderobject(MobileNumber),
                    LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getLoginTypeID() + "", ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));

            call.enqueue(new Callback<RechargeReportResponse>() {

                @Override
                public void onResponse(Call<RechargeReportResponse> call, retrofit2.Response<RechargeReportResponse> response) {

                    if (loader!=null && loader.isShowing())
                        loader.dismiss();

                    if (response.body() != null && response.body().getStatuscode() != null) {
                        if (response.body().getStatuscode().equalsIgnoreCase("1")) {
                            if(apiCallBack!=null){
                                apiCallBack.onSucess(response.body());
                            }
                            setBeneficiaryList(context, new Gson().toJson(response.body()));

                        } else if (response.body().getStatuscode().equalsIgnoreCase("-1")) {
                            if (response.body().getIsVersionValid() != null && response.body().getIsVersionValid().equalsIgnoreCase("false")) {
                                versionDialog(context);
                            } else {
                                Error(context, response.body().getMsg() + "");
                            }
                        }

                    }
                }

                @Override
                public void onFailure(Call<RechargeReportResponse> call, Throwable t) {

                    if (loader.isShowing())
                        loader.dismiss();
                    try {
                        if (t.getMessage() != null && !t.getMessage().isEmpty()) {

                            if (t.getMessage().contains("No address associated with hostname")) {
                                NetworkError(context, context.getResources().getString(R.string.err_msg_network_title),
                                        context.getResources().getString(R.string.err_msg_network));
                            } else {
                                Error(context, t.getMessage());

                            }

                        } else {
                            Error(context, context.getResources().getString(R.string.some_thing_error));

                        }
                    } catch (IllegalStateException ise) {
                        Error(context, ise.getMessage());

                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void GetBeneficiaryNew(final Activity context, String oid, String MobileNumber, final CustomLoader loader, ApiCallBack mApiCallBack) {
        try {
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<RechargeReportResponse> call = git.GetBeneficiaryNew(new GetSenderRequest(oid, new Senderobject(MobileNumber),
                    LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getLoginTypeID() + "",
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));

            call.enqueue(new Callback<RechargeReportResponse>() {

                @Override
                public void onResponse(Call<RechargeReportResponse> call, retrofit2.Response<RechargeReportResponse> response) {

                    if (loader.isShowing())
                        loader.dismiss();
                    if (response.isSuccessful()) {
                        if (response.body() != null && response.body().getStatuscode() != null) {
                            if (response.body().getStatuscode().equalsIgnoreCase("1")) {
                                setBeneficiaryList(context, new Gson().toJson(response.body()).toString());
                                if (mApiCallBack != null) {
                                    mApiCallBack.onSucess(response.body());
                                }

                            } else if (response.body().getStatuscode().equalsIgnoreCase("-1")) {
                                if (response.body().getIsVersionValid() != null && response.body().getIsVersionValid().equalsIgnoreCase("false")) {
                                    versionDialog(context);
                                } else {
                                    Error(context, response.body().getMsg() + "");
                                }
                            }

                        }
                    } else {
                        apiErrorHandle(context, response.code(), response.message());
                    }
                }

                @Override
                public void onFailure(Call<RechargeReportResponse> call, Throwable t) {

                    if (loader.isShowing())
                        loader.dismiss();
                    apiFailureError(context, t);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void GetChargedAmount(final Activity context, final String Amount, final String beneID, final String mobileNo, final String ifsc, final String accountNo, final String channel, final String bank, final String beneName, final CustomLoader loader, final Activity activity) {
        try {
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<RechargeReportResponse> call = git.GetChargedAmount(new GetChargedAmountRequest(Amount,
                    LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getLoginTypeID() + "",
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));

            call.enqueue(new Callback<RechargeReportResponse>() {

                @Override
                public void onResponse(Call<RechargeReportResponse> call, retrofit2.Response<RechargeReportResponse> response) {
                    Log.e("GetChargedAmount", "hello response : " + new Gson().toJson(response.body()));
                    if (loader.isShowing())
                        loader.dismiss();

                    if (response.body() != null && response.body().getStatuscode() != null) {
                        if (response.body().getStatuscode().equalsIgnoreCase("1")) {
                            double amount = Double.parseDouble(Amount);
                            double charged = Double.parseDouble(response.body().getChargedAmount());
                            double totalAmount = amount + charged;
                            ActivityActivityMessage activityActivityMessage = new ActivityActivityMessage(charged + "," + totalAmount, "SendMoney");
                            GlobalBus.getBus().post(activityActivityMessage);
//                            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
//
//
//                            LayoutInflater inflater = activity.getLayoutInflater();
//                            View view = inflater.inflate(R.layout.custom_dialog_send, null);
//                            AppCompatTextView message = (AppCompatTextView) view.findViewById(R.id.message);
//                            message.setText("You are going to transfer \u20B9 " + Amount + "\nyour Charged Amount is  \u20B9 " + response.body().getChargedAmount() + "\nNow total Transfer Amount is \u20B9 " + totalAmount);
//                            final Button okButton = (Button) view.findViewById(R.id.okButton);
//                            final Button cancelButton = (Button) view.findViewById(R.id.cancelButton);
//                            okButton.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//
//                                   // GetDMTReceipt(context,"S190604122026309F91E","All",loader);
//
//
//                                }
//                            });
//                            cancelButton.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    alertDialog.dismiss();
//                                }
//                            });
//                            dialogBuilder.setView(view);
//                            dialogBuilder.setCancelable(false);
//                            alertDialog = dialogBuilder.create();
//                            alertDialog.show();

                        } else if (response.body().getStatuscode().equalsIgnoreCase("-1")) {
                            if (response.body().getIsVersionValid() != null && response.body().getIsVersionValid().equalsIgnoreCase("false")) {
                                versionDialog(context);
                            } else {
                                Error(context, response.body().getMsg() + "");
                            }
                        }

                    }
                }

                @Override
                public void onFailure(Call<RechargeReportResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (loader.isShowing())
                        loader.dismiss();
                    try {
                        if (t.getMessage() != null && !t.getMessage().isEmpty()) {

                            if (t.getMessage().contains("No address associated with hostname")) {
                                NetworkError(context, context.getResources().getString(R.string.err_msg_network_title),
                                        context.getResources().getString(R.string.err_msg_network));
                            } else {
                                Error(context, t.getMessage());

                            }

                        } else {
                            Error(context, context.getResources().getString(R.string.some_thing_error));

                        }
                    } catch (IllegalStateException ise) {
                        Error(context, ise.getMessage());

                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void GetChargedAmountNew(final Activity context, final String oid, final String Amount, final String beneID, final String mobileNo, final String ifsc, final String accountNo, final String channel, final String bank, final String beneName, final CustomLoader loader, final Activity activity) {
        try {
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<RechargeReportResponse> call = git.GetChargedAmountNew(new GetChargedAmountRequest(oid, Amount,
                    LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getLoginTypeID() + "",
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));

            call.enqueue(new Callback<RechargeReportResponse>() {

                @Override
                public void onResponse(Call<RechargeReportResponse> call, retrofit2.Response<RechargeReportResponse> response) {

                    if (loader.isShowing())
                        loader.dismiss();
                    if (response.isSuccessful()) {
                        if (response.body() != null && response.body().getStatuscode() != null) {
                            if (response.body().getStatuscode().equalsIgnoreCase("1")) {
                                double amount = Double.parseDouble(Amount);
                                double charged = Double.parseDouble(response.body().getChargedAmount());
                                double totalAmount = amount + charged;
                                ActivityActivityMessage activityActivityMessage = new ActivityActivityMessage(charged + "," + totalAmount, "SendMoney");
                                GlobalBus.getBus().post(activityActivityMessage);
//                            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
//
//
//                            LayoutInflater inflater = activity.getLayoutInflater();
//                            View view = inflater.inflate(R.layout.custom_dialog_send, null);
//                            AppCompatTextView message = (AppCompatTextView) view.findViewById(R.id.message);
//                            message.setText("You are going to transfer \u20B9 " + Amount + "\nyour Charged Amount is  \u20B9 " + response.body().getChargedAmount() + "\nNow total Transfer Amount is \u20B9 " + totalAmount);
//                            final Button okButton = (Button) view.findViewById(R.id.okButton);
//                            final Button cancelButton = (Button) view.findViewById(R.id.cancelButton);
//                            okButton.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//
//                                   // GetDMTReceipt(context,"S190604122026309F91E","All",loader);
//
//
//                                }
//                            });
//                            cancelButton.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    alertDialog.dismiss();
//                                }
//                            });
//                            dialogBuilder.setView(view);
//                            dialogBuilder.setCancelable(false);
//                            alertDialog = dialogBuilder.create();
//                            alertDialog.show();

                            } else if (response.body().getStatuscode().equalsIgnoreCase("-1")) {
                                if (response.body().getIsVersionValid() != null && response.body().getIsVersionValid().equalsIgnoreCase("false")) {
                                    versionDialog(context);
                                } else {
                                    Error(context, response.body().getMsg() + "");
                                }
                            }

                        }
                    } else {
                        apiErrorHandle(context, response.code(), response.message());
                    }
                }

                @Override
                public void onFailure(Call<RechargeReportResponse> call, Throwable t) {

                    if (loader.isShowing())
                        loader.dismiss();
                    apiFailureError(context, t);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void SendMoney(final Activity context, String securityKey, String beneID, String mobileNo, String ifsc, String accountNo, String amount, String channel, String bank, String beneName, final CustomLoader loader, final Activity activity, TextView submitButton) {
        try {
            submitButton.setEnabled(false);
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<RechargeReportResponse> call = git.SendMoney(new SendMoneyRequest(new RequestSendMoney(beneID, mobileNo, ifsc, accountNo, amount, channel, bank, beneName), securityKey,
                    LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getLoginTypeID() + "",
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));

            call.enqueue(new Callback<RechargeReportResponse>() {

                @Override
                public void onResponse(Call<RechargeReportResponse> call, retrofit2.Response<RechargeReportResponse> response) {
                    Log.e("GetSender", "hello response : " + new Gson().toJson(response.body()));
                    if (loader.isShowing())
                        loader.dismiss();
                    if (response.body() != null && response.body().getStatuscode() != null) {
                        if (response.body().getStatuscode().equalsIgnoreCase("2")) {
                            // Successfulok(context, response.body().getMsg(), activity);
                            if (response.body().getGroupID() != null && !response.body().getGroupID().isEmpty()) {
                                GetDMTReceipt(context, response.body().getGroupID(), "All", loader);
                                activity.finish();
                            } else {
                                Successfulok(response.body().getMsg(), activity);
                            }

                        }else if (response.body().getStatuscode().equalsIgnoreCase("3")) {
                            if (response.body().getGroupID() != null && !response.body().getGroupID().isEmpty()) {
                                GetDMTReceipt(context, response.body().getGroupID(), "All", loader);
                                activity.finish();
                            } else {
                                Errorok(context, response.body().getMsg() + "", activity);
                            }

                        }else if (response.body().getStatuscode().equalsIgnoreCase("1")) {
                            Processing(context, response.body().getMsg() + "");
                        }  else  {
                            if (response.body().getIsVersionValid() != null && response.body().getIsVersionValid().equalsIgnoreCase("false")) {
                                versionDialog(context);
                            } else {
                                Errorok(context, response.body().getMsg() + "", activity);
                            }
                        } 

                    }
                }

                @Override
                public void onFailure(Call<RechargeReportResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (loader.isShowing())
                        loader.dismiss();
                    try {
                        if (t.getMessage() != null && !t.getMessage().isEmpty()) {

                            if (t.getMessage().contains("No address associated with hostname")) {
                                NetworkError(context, context.getResources().getString(R.string.err_msg_network_title),
                                        context.getResources().getString(R.string.err_msg_network));
                            } else {
                                Error(context, t.getMessage());

                            }

                        } else {
                            Error(context, context.getResources().getString(R.string.some_thing_error));

                        }
                    } catch (IllegalStateException ise) {
                        Error(context, ise.getMessage());

                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void SendMoneyNew(final Activity context, String oid, String securityKey, String beneID, String mobileNo,
                             String ifsc, String accountNo, String amount, String channel, String bank, String bankId,
                             String beneName, String beneMobile, final CustomLoader loader, final Activity activity, final TextView submitButton) {
        try {
            submitButton.setEnabled(false);
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<RechargeReportResponse> call = git.SendMoneyNew(new SendMoneyRequest(oid, new RequestSendMoney(oid, oid, beneID, mobileNo, ifsc, accountNo,
                    amount, channel, bank, bankId, beneName, beneMobile), securityKey,
                    LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getLoginTypeID() + "",
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));

            call.enqueue(new Callback<RechargeReportResponse>() {

                @Override
                public void onResponse(Call<RechargeReportResponse> call, retrofit2.Response<RechargeReportResponse> response) {

                    if (loader.isShowing())
                        loader.dismiss();
                    if (response.isSuccessful()) {
                        if (response.body() != null && response.body().getStatuscode() != null) {
                            submitButton.setEnabled(true);
                            if (response.body().getStatuscode().equalsIgnoreCase("2")) {
                                // Successfulok(context, response.body().getMsg(), activity);
                                if (response.body().getGroupID() != null && !response.body().getGroupID().isEmpty()) {
                                    GetDMTReceipt(activity, response.body().getGroupID(), "All", loader);

                                } else {
                                    Successfulok(response.body().getMsg(), activity);
                                }

                            }else if (response.body().getStatuscode().equalsIgnoreCase("3")) {
                                if (response.body().getGroupID() != null && !response.body().getGroupID().isEmpty()) {
                                    GetDMTReceipt(activity, response.body().getGroupID(), "All", loader);

                                } else {
                                    Error(context, response.body().getMsg() + "");
                                }

                            } else if (response.body().getStatuscode().equalsIgnoreCase("1")) {
                                Processing(context, response.body().getMsg() + "");
                            } else {
                                if (response.body().getIsVersionValid() != null && response.body().getIsVersionValid().equalsIgnoreCase("false")) {
                                    versionDialog(context);
                                } else {
                                    Error(context, response.body().getMsg() + "");
                                }
                            } 

                        }
                    } else {
                        apiErrorHandle(context, response.code(), response.message());
                    }
                }

                @Override
                public void onFailure(Call<RechargeReportResponse> call, Throwable t) {

                    submitButton.setEnabled(true);
                    if (loader.isShowing())
                        loader.dismiss();
                    try {
                        apiFailureError(context, t);
                    } catch (IllegalStateException ise) {
                        Error(context, ise.getMessage());

                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void GetAppPurchageToken(final Activity context, final String TotalToken, final String oid, final String PANID, final CustomLoader loader, final ApiCallBack mApiCallBack) {
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            Call<AppUserListResponse> call = git.GetAppPurchageToken(new PurchaseTokenRequest(oid,
                    PANID,
                    TotalToken,
                    LoginDataResponse.getData().getUserID(),
                    LoginDataResponse.getData().getSessionID(),
                    LoginDataResponse.getData().getSession(),
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context), "",
                    BuildConfig.VERSION_NAME,
                    getSerialNo(context),
                    LoginDataResponse.getData().getLoginTypeID(),
                    LoginDataResponse.getData().getOutletID()));

            call.enqueue(new Callback<AppUserListResponse>() {
                @Override
                public void onResponse(Call<AppUserListResponse> call, final Response<AppUserListResponse> response) {
                    Log.e("purchaseResponse", "is : " + new Gson().toJson(response.body()).toString());
                    try {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }

                        if (response.body() != null && response.body().getStatuscode() != null) {
                            if (response.body().getStatuscode() == 2) {
                                if (response.body().getMsg() != null) {
                                    if (mApiCallBack != null) {
                                        mApiCallBack.onSucess(response.body());
                                    }
                                    Successful(context, response.body().getMsg());
                                }

                            } else if (response.body().getStatuscode() == 3) {
                                if (response.body().getMsg() != null) {
                                    Error(context, response.body().getMsg());
                                }
                            } else if (response.body().getStatuscode() == 1) {
                                if (response.body().getMsg() != null) {
                                    if (mApiCallBack != null) {
                                        mApiCallBack.onSucess(response.body());
                                    }
                                    Successful(context, response.body().getMsg());
                                }
                            } else {
                                if (response.body().getMsg() != null) {
                                    Error(context, response.body().getMsg());
                                }
                            }
                        }

                    } catch (Exception e) {

                    }


                }

                @Override
                public void onFailure(Call<AppUserListResponse> call, Throwable t) {


                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void GetDMTReceipt(final Activity context, final String GroupID, final String flag, final CustomLoader loader) {
        try {
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<DMTReceiptResponse> call = git.GetDMTReceipt(new GetDMTReceiptRequest(GroupID,
                    LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getLoginTypeID() + "",
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));

            call.enqueue(new Callback<DMTReceiptResponse>() {

                @Override
                public void onResponse(Call<DMTReceiptResponse> call, retrofit2.Response<DMTReceiptResponse> response) {
                    Log.e("GetDMTReceipt", "hello response : " + new Gson().toJson(response.body()));
                    if (loader.isShowing())
                        loader.dismiss();

                    if (response.body() != null && response.body().getStatuscode() != null) {
                        if (response.body().getStatuscode().equalsIgnoreCase("1")) {
                            if (response.body().getTransactionDetail() != null && response.body().getTransactionDetail().getLists() != null) {
                                Intent i = new Intent(context, DMRReciept.class);
                                i.putExtra("response", new Gson().toJson(response.body()));
                                i.putExtra("flag", flag);
                                context.startActivity(i);
                            }
                            // Successful(context, response.body().getMsg());
                        } else if (response.body().getStatuscode().equalsIgnoreCase("-1")) {
                            if (response.body().getIsVersionValid() != null && response.body().getIsVersionValid().equalsIgnoreCase("false")) {
                                versionDialog(context);
                            } else {
                                Error(context, response.body().getMsg() + "");
                            }
                        } else if (response.body().getStatuscode().equalsIgnoreCase("3")) {
                            Error(context, response.body().getMsg() + "");
                        }

                    }
                }

                @Override
                public void onFailure(Call<DMTReceiptResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (loader.isShowing())
                        loader.dismiss();
                    try {
                        if (t.getMessage() != null && !t.getMessage().isEmpty()) {

                            if (t.getMessage().contains("No address associated with hostname")) {
                                NetworkError(context, context.getResources().getString(R.string.err_msg_network_title),
                                        context.getResources().getString(R.string.err_msg_network));
                            } else {
                                Error(context, t.getMessage());

                            }

                        } else {
                            Error(context, context.getResources().getString(R.string.some_thing_error));

                        }
                    } catch (IllegalStateException ise) {
                        Error(context, ise.getMessage());

                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void DMTReport(final Activity context, int status, String apiid, String fromDate, String toDate, String transactionID, String accountNo, String childMobNo,int topRows, final CustomLoader loader) {
        try {
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<RechargeReportResponse> call = git.DMTReport(new DmrRequest(topRows, status, apiid, fromDate, toDate, transactionID, accountNo, childMobNo, false,
                    LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession(),
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getLoginTypeID() + ""));

            call.enqueue(new Callback<RechargeReportResponse>() {

                @Override
                public void onResponse(Call<RechargeReportResponse> call, retrofit2.Response<RechargeReportResponse> response) {
                    Log.e("GetDMTReceipt", "hello response : " + new Gson().toJson(response.body()));
                    if (loader.isShowing())
                        loader.dismiss();

                    if (response.body() != null && response.body().getStatuscode() != null) {
                        if (response.body().getStatuscode().equalsIgnoreCase("1")) {
                            ActivityActivityMessage activityActivityMessage =
                                    new ActivityActivityMessage("DMRTransaction", new Gson().toJson(response.body()));
                            GlobalBus.getBus().post(activityActivityMessage);
                        } else if (response.body().getStatuscode().equalsIgnoreCase("-1")) {
                            if (response.body().getIsVersionValid() != null && response.body().getIsVersionValid().equalsIgnoreCase("false")) {
                                versionDialog(context);
                            } else {
                                Error(context, response.body().getMsg() + "");
                            }
                        } else if (response.body().getStatuscode().equalsIgnoreCase("3")) {
                            Error(context, response.body().getMsg() + "");
                        }

                    }
                }

                @Override
                public void onFailure(Call<RechargeReportResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (loader.isShowing())
                        loader.dismiss();
                    try {
                        if (t.getMessage() != null && !t.getMessage().isEmpty()) {

                            if (t.getMessage().contains("No address associated with hostname")) {
                                NetworkError(context, context.getResources().getString(R.string.err_msg_network_title),
                                        context.getResources().getString(R.string.err_msg_network));
                            } else {
                                Error(context, t.getMessage());

                            }

                        } else {
                            Error(context, context.getResources().getString(R.string.some_thing_error));

                        }
                    } catch (IllegalStateException ise) {
                        Error(context, ise.getMessage());

                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void CreateSender(final Activity context, final String MobileNumber, String name, String lastName, String pincode, String address, final String otp, String dob, final CustomLoader loader) {
        try {
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<RechargeReportResponse> call = git.CreateSender(new GetSenderRequest(new Senderobject(MobileNumber, name, lastName, pincode, address, otp, dob),
                    LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getLoginTypeID() + "",
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));

            call.enqueue(new Callback<RechargeReportResponse>() {

                @Override
                public void onResponse(Call<RechargeReportResponse> call, retrofit2.Response<RechargeReportResponse> response) {

                    if (loader!=null && loader.isShowing())
                        loader.dismiss();

                    if (response.body() != null && response.body().getStatuscode() != null) {
                        if (response.body().getStatuscode().equalsIgnoreCase("1")) {
                            openOTPDialog(context,   new DialogCallBack() {
                                @Override
                                public void onPositiveClick(String otp) {
                                    loader.show();
                                    VerifySender(context, MobileNumber, otp, loader);
                                }

                                @Override
                                public void onCancelClick() {

                                }
                            });

                        } else if (response.body().getStatuscode().equalsIgnoreCase("-1")) {
                            if (response.body().getIsVersionValid() != null && response.body().getIsVersionValid().equalsIgnoreCase("false")) {
                                versionDialog(context);
                            } else {
                                Error(context, response.body().getMsg() + "");
                            }
                        }

                    }
                }

                @Override
                public void onFailure(Call<RechargeReportResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (loader.isShowing())
                        loader.dismiss();
                    try {
                        if (t.getMessage() != null && !t.getMessage().isEmpty()) {

                            if (t.getMessage().contains("No address associated with hostname")) {
                                NetworkError(context, context.getResources().getString(R.string.err_msg_network_title),
                                        context.getResources().getString(R.string.err_msg_network));
                            } else {
                                Error(context, t.getMessage());

                            }

                        } else {
                            Error(context, context.getResources().getString(R.string.some_thing_error));

                        }
                    } catch (IllegalStateException ise) {
                        Error(context, ise.getMessage());

                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void CreateSenderNew(final Activity context, String oid, final String MobileNumber, String name, String lastName, String pincode, String address, final String otp, String dob, final CustomLoader loader) {
        try {
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<RechargeReportResponse> call = git.CreateSenderNew(new GetSenderRequest(oid, new Senderobject(MobileNumber, name, lastName, pincode, address, otp, dob), LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getLoginTypeID() + "", ApplicationConstant.INSTANCE.APP_ID, getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));

            call.enqueue(new Callback<RechargeReportResponse>() {

                @Override
                public void onResponse(Call<RechargeReportResponse> call, retrofit2.Response<RechargeReportResponse> response) {
                    if (loader.isShowing())
                        loader.dismiss();
                    if (response.isSuccessful()) {
                        if (response.body() != null && response.body().getStatuscode() != null) {
                            if (response.body().getStatuscode().equalsIgnoreCase("1")) {

                                openOTPDialog(context, new DialogCallBack() {
                                    @Override
                                    public void onPositiveClick(String otp) {
                                        loader.show();
                                        VerifySenderNew(context, oid, MobileNumber, otp, loader);
                                    }

                                    @Override
                                    public void onCancelClick() {

                                    }
                                });
                            } else if (response.body().getStatuscode().equalsIgnoreCase("-1")) {
                                if (response.body().getIsVersionValid() != null && response.body().getIsVersionValid().equalsIgnoreCase("false")) {
                                    versionDialog(context);
                                } else {
                                    Error(context, response.body().getMsg() + "");
                                }
                            }

                        }
                    } else {
                        apiErrorHandle(context, response.code(), response.message());
                    }
                }

                @Override
                public void onFailure(Call<RechargeReportResponse> call, Throwable t) {

                    if (loader.isShowing())
                        loader.dismiss();
                    apiFailureError(context, t);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void Deletebeneficiary(final Activity context, final String MobileNumber, String beneID, final CustomLoader loader) {
        try {
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<RechargeReportResponse> call = git.DeleteBeneficiary(new GetSenderRequest(new Senderobject(MobileNumber), new BeneDetail(beneID),
                    LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getLoginTypeID() + "",
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));

            call.enqueue(new Callback<RechargeReportResponse>() {

                @Override
                public void onResponse(Call<RechargeReportResponse> call, retrofit2.Response<RechargeReportResponse> response) {

                    if (loader.isShowing())
                        loader.dismiss();

                    if (response.body() != null && response.body().getStatuscode() != null) {
                        if (response.body().getStatuscode().equalsIgnoreCase("1")) {

                            openOTPDialog(context, new DialogCallBack() {
                                @Override
                                public void onPositiveClick(String value) {
                                    loader.show();
                                    VerifySender(context, MobileNumber, value, loader);
                                }

                                @Override
                                public void onCancelClick() {

                                }
                            });
                        } else if (response.body().getStatuscode().equalsIgnoreCase("-1")) {
                            if (response.body().getIsVersionValid() != null && response.body().getIsVersionValid().equalsIgnoreCase("false")) {
                                versionDialog(context);
                            } else {
                                Error(context, response.body().getMsg() + "");
                            }
                        }

                    }
                }

                @Override
                public void onFailure(Call<RechargeReportResponse> call, Throwable t) {

                    if (loader.isShowing())
                        loader.dismiss();
                    try {
                        if (t.getMessage() != null && !t.getMessage().isEmpty()) {

                            if (t.getMessage().contains("No address associated with hostname")) {
                                NetworkError(context, context.getResources().getString(R.string.err_msg_network_title),
                                        context.getResources().getString(R.string.err_msg_network));
                            } else {
                                Error(context, t.getMessage());

                            }

                        } else {
                            Error(context, context.getResources().getString(R.string.some_thing_error));

                        }
                    } catch (IllegalStateException ise) {
                        Error(context, ise.getMessage());

                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void DeleateBeneficiaryNew(final Activity context, String oid, String sid, String otp, final String MobileNumber,
                                     String beneID, final CustomLoader loader, ApiCallBack mApiCallBack) {
        try {
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<RechargeReportResponse> call = git.DeleteBeneficiaryNew(new GetSenderRequest(oid, new Senderobject(MobileNumber),
                    new BeneDetail(beneID), sid, otp,
                    LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getLoginTypeID() + "",
                    ApplicationConstant.INSTANCE.APP_ID, getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));

            call.enqueue(new Callback<RechargeReportResponse>() {

                @Override
                public void onResponse(Call<RechargeReportResponse> call, retrofit2.Response<RechargeReportResponse> response) {
                    if (loader.isShowing())
                        loader.dismiss();
                    if (response.isSuccessful()) {
                        if (response.body() != null && response.body().getStatuscode() != null) {
                            if (response.body().getStatuscode().equalsIgnoreCase("1")) {

                                if(response.body().isOTPRequired()){
                                    loader.show();
                                    UtilMethods.INSTANCE.openOTPDialog(context, new UtilMethods.DialogCallBack() {
                                        @Override
                                        public void onPositiveClick(String value) {
                                            loader.show();
                                            DeleateBeneficiaryNew(context,oid,sid,value,MobileNumber,beneID,loader,mApiCallBack);
                                        }



                                        @Override
                                        public void onCancelClick() {

                                        }
                                    });
                                }else{
                                    Successful(context, response.body().getMsg() + "");
                                    if (mApiCallBack != null) {
                                        mApiCallBack.onSucess(response.body());
                                    }
                                }


                            } else if (response.body().getStatuscode().equalsIgnoreCase("-1")) {
                                if (response.body().getIsVersionValid() != null && response.body().getIsVersionValid().equalsIgnoreCase("false")) {
                                    versionDialog(context);
                                } else {
                                    Error(context, response.body().getMsg() + "");
                                }
                            }

                        }
                    } else {
                        apiErrorHandle(context, response.code(), response.message());
                    }
                }

                @Override
                public void onFailure(Call<RechargeReportResponse> call, Throwable t) {

                    if (loader.isShowing())
                        loader.dismiss();
                    apiFailureError(context, t);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void VerifySender(final Activity context, final String MobileNumber, String otp, final CustomLoader loader) {
        try {
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<RechargeReportResponse> call = git.VerifySender(new GetSenderRequest(new Senderobject(MobileNumber, otp),
                    LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getLoginTypeID() + "", ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));

            call.enqueue(new Callback<RechargeReportResponse>() {

                @Override
                public void onResponse(Call<RechargeReportResponse> call, retrofit2.Response<RechargeReportResponse> response) {

                    if (loader.isShowing())
                        loader.dismiss();

                    if (response.body() != null && response.body().getStatuscode() != null) {
                        if (response.body().getStatuscode().equalsIgnoreCase("1")) {
                            Successful(context, response.body().getMsg() + "");
                            GetSender(context, MobileNumber, loader);
                        } else if (response.body().getStatuscode().equalsIgnoreCase("-1")) {
                            if (response.body().getIsVersionValid() != null && response.body().getIsVersionValid().equalsIgnoreCase("false")) {
                                versionDialog(context);
                            } else {
                                Error(context, response.body().getMsg() + "");
                            }
                        }

                    }
                }

                @Override
                public void onFailure(Call<RechargeReportResponse> call, Throwable t) {

                    if (loader.isShowing())
                        loader.dismiss();
                    try {
                        if (t.getMessage() != null && !t.getMessage().isEmpty()) {

                            if (t.getMessage().contains("No address associated with hostname")) {
                                NetworkError(context, context.getResources().getString(R.string.err_msg_network_title),
                                        context.getResources().getString(R.string.err_msg_network));
                            } else {
                                Error(context, t.getMessage());

                            }

                        } else {
                            Error(context, context.getResources().getString(R.string.some_thing_error));

                        }
                    } catch (IllegalStateException ise) {
                        Error(context, ise.getMessage());

                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void VerifySenderNew(final Activity context, String oid, final String MobileNumber, String otp, final CustomLoader loader) {
        try {
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<RechargeReportResponse> call = git.VerifySenderNew(new GetSenderRequest(oid, new Senderobject(MobileNumber, otp),
                    LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getLoginTypeID() + "",
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));

            call.enqueue(new Callback<RechargeReportResponse>() {

                @Override
                public void onResponse(Call<RechargeReportResponse> call, retrofit2.Response<RechargeReportResponse> response) {
                    if (loader.isShowing())
                        loader.dismiss();
                    if (response.isSuccessful()) {
                        if (response.body() != null && response.body().getStatuscode() != null) {
                            if (response.body().getStatuscode().equalsIgnoreCase("1")) {
                                Successful(context, response.body().getMsg() + "");
                                GetSenderNew(context, oid, MobileNumber, loader);
                            } else if (response.body().getStatuscode().equalsIgnoreCase("-1")) {
                                if (response.body().getIsVersionValid() != null && response.body().getIsVersionValid().equalsIgnoreCase("false")) {
                                    versionDialog(context);
                                } else {
                                    Error(context, response.body().getMsg() + "");
                                }
                            }

                        }
                    } else {
                        apiErrorHandle(context, response.code(), response.message());
                    }
                }

                @Override
                public void onFailure(Call<RechargeReportResponse> call, Throwable t) {

                    if (loader.isShowing())
                        loader.dismiss();
                    apiFailureError(context, t);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void ChangePinPassword(final Activity context, final boolean isPin, final String oldPass, String newPass, String confPass, final CustomLoader loader, final Dialog dialog) {
        try {
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<RechargeReportResponse> call = git.ChangePinOrPassword(new ChangePinPasswordRequest(isPin, oldPass, newPass, confPass,
                    LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getLoginTypeID() + "",
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));

            call.enqueue(new Callback<RechargeReportResponse>() {

                @Override
                public void onResponse(Call<RechargeReportResponse> call, retrofit2.Response<RechargeReportResponse> response) {
                    Log.e("CreateSender", "hello response : " + new Gson().toJson(response.body()));
                    if (loader.isShowing())
                        loader.dismiss();

                    if (response.body() != null && response.body().getStatuscode() != null) {
                        if (response.body().getStatuscode().equalsIgnoreCase("1")) {
                            dialog.dismiss();
                            Successful(context, response.body().getMsg() + "");
                           /* if (!isPin) {
                                logout(context);
                            }*/

                        } else if (response.body().getStatuscode().equalsIgnoreCase("-1")) {
                            if (response.body().getIsVersionValid() != null && response.body().getIsVersionValid().equalsIgnoreCase("false")) {
                                versionDialog(context);
                            } else {
                                Error(context, response.body().getMsg() + "");
                            }
                        }

                    }
                }

                @Override
                public void onFailure(Call<RechargeReportResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (loader.isShowing())
                        loader.dismiss();
                    try {
                        if (t.getMessage() != null && !t.getMessage().isEmpty()) {

                            if (t.getMessage().contains("No address associated with hostname")) {
                                NetworkError(context, context.getResources().getString(R.string.err_msg_network_title),
                                        context.getResources().getString(R.string.err_msg_network));
                            } else {
                                Error(context, t.getMessage());

                            }

                        } else {
                            Error(context, context.getResources().getString(R.string.some_thing_error));

                        }
                    } catch (IllegalStateException ise) {
                        Error(context, ise.getMessage());

                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void DoubleFactorOtp(final Activity context, boolean isDoubleFactor, final String otp, String reffid, final CustomLoader loader, final ApiCallBack mApiCallBack) {
        try {
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<DFStatusResponse> call = git.ChangeDFStatus(new DFStatusRequest(isDoubleFactor, otp, reffid,
                    LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getLoginTypeID() + "",
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));

            call.enqueue(new Callback<DFStatusResponse>() {

                @Override
                public void onResponse(Call<DFStatusResponse> call, retrofit2.Response<DFStatusResponse> response) {
                    Log.e("CreateSender", "hello response : " + new Gson().toJson(response.body()));
                    if (loader.isShowing())
                        loader.dismiss();

                    if (response.body() != null && response.body().getStatuscode() != null) {
                        if (response.body().getStatuscode() == 1) {
                            if (mApiCallBack != null) {
                                mApiCallBack.onSucess(response.body());
                            }
                        } else if (response.body().getStatuscode() == -1) {
                            if (response.body().isVersionValid() == false) {
                                versionDialog(context);
                            } else {
                                Error(context, response.body().getMsg() + "");
                            }
                        }

                    }
                }

                @Override
                public void onFailure(Call<DFStatusResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (loader.isShowing())
                        loader.dismiss();
                    try {
                        if (t.getMessage() != null && !t.getMessage().isEmpty()) {

                            if (t.getMessage().contains("No address associated with hostname")) {
                                NetworkError(context, context.getResources().getString(R.string.err_msg_network_title),
                                        context.getResources().getString(R.string.err_msg_network));
                            } else {
                                Error(context, t.getMessage());

                            }

                        } else {
                            Error(context, context.getResources().getString(R.string.some_thing_error));

                        }
                    } catch (IllegalStateException ise) {
                        Error(context, ise.getMessage());

                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void AddBeneficiary(final Activity context, String SenderNO, String BeneMobileNO, String beniName, String ifsc, String accountNo, String bankName, final CustomLoader loader, final Activity activity) {
        try {
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<RechargeReportResponse> call = git.AddBeneficiary(new GetSenderRequest(new Senderobject(SenderNO), new BeneDetail(BeneMobileNO, beniName, ifsc, accountNo, bankName),
                    LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getLoginTypeID() + "",
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));

            call.enqueue(new Callback<RechargeReportResponse>() {

                @Override
                public void onResponse(Call<RechargeReportResponse> call, retrofit2.Response<RechargeReportResponse> response) {
                    Log.e("CreateSender", "hello response : " + new Gson().toJson(response.body()));
                    if (loader.isShowing())
                        loader.dismiss();

                    if (response.body() != null && response.body().getStatuscode() != null) {
                        if (response.body().getStatuscode().equalsIgnoreCase("1")) {
                            Successfulok(response.body().getMsg() + "", activity);
                            ActivityActivityMessage activityActivityMessage =
                                    new ActivityActivityMessage("", "beneAdded");
                            GlobalBus.getBus().post(activityActivityMessage);

                        } else if (response.body().getStatuscode().equalsIgnoreCase("-1")) {
                            if (response.body().getIsVersionValid() != null && response.body().getIsVersionValid().equalsIgnoreCase("false")) {
                                versionDialog(context);
                            } else {
                                Error(context, response.body().getMsg() + "");
                            }
                        }

                    }
                }

                @Override
                public void onFailure(Call<RechargeReportResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (loader.isShowing())
                        loader.dismiss();
                    try {
                        if (t.getMessage() != null && !t.getMessage().isEmpty()) {

                            if (t.getMessage().contains("No address associated with hostname")) {
                                NetworkError(context, context.getResources().getString(R.string.err_msg_network_title),
                                        context.getResources().getString(R.string.err_msg_network));
                            } else {
                                Error(context, t.getMessage());

                            }

                        } else {
                            Error(context, context.getResources().getString(R.string.some_thing_error));

                        }
                    } catch (IllegalStateException ise) {
                        Error(context, ise.getMessage());

                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void AddBeneficiaryNew(final Activity context, String oid, String sid, String otp, String SenderNO, String BeneMobileNO, String beniName, String ifsc, String accountNo, String bankName, final String bankId, final CustomLoader loader, final Activity activity) {
        try {
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<RechargeReportResponse> call = git.AddBeneficiaryNew(new GetSenderRequest(oid, new Senderobject(SenderNO),
                    new BeneDetail(BeneMobileNO, beniName, ifsc, accountNo, bankName, bankId, 2), sid, otp,
                    LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getLoginTypeID() + "",
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));

            call.enqueue(new Callback<RechargeReportResponse>() {

                @Override
                public void onResponse(Call<RechargeReportResponse> call, retrofit2.Response<RechargeReportResponse> response) {
                    if (loader.isShowing())
                        loader.dismiss();
                    if (response.isSuccessful()) {
                        if (response.body() != null && response.body().getStatuscode() != null) {
                            if (response.body().getStatuscode().equalsIgnoreCase("1")) {
                                Successfulok(response.body().getMsg() + "", activity);
                                ActivityActivityMessage activityActivityMessage =
                                        new ActivityActivityMessage("", "beneAdded");
                                GlobalBus.getBus().post(activityActivityMessage);

                            } else if (response.body().getStatuscode().equalsIgnoreCase("-1")) {
                                if (response.body().getIsVersionValid() != null && response.body().getIsVersionValid().equalsIgnoreCase("false")) {
                                    versionDialog(context);
                                } else {
                                    Error(context, response.body().getMsg() + "");
                                }
                            }

                        }
                    } else {
                        apiErrorHandle(context, response.code(), response.message());
                    }
                }

                @Override
                public void onFailure(Call<RechargeReportResponse> call, Throwable t) {

                    if (loader.isShowing())
                        loader.dismiss();
                    apiFailureError(context, t);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void VerifyAccount(final Activity context, String mobileNo, String ifsc, String accountNo, String bankName, final CustomLoader loader) {
        try {
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<RechargeReportResponse> call = git.VerifyAccount(new GetSenderRequest(new Senderobject(mobileNo), new BeneDetail(mobileNo, ifsc, accountNo, bankName),
                    LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getLoginTypeID() + "",
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));

            call.enqueue(new Callback<RechargeReportResponse>() {

                @Override
                public void onResponse(Call<RechargeReportResponse> call, retrofit2.Response<RechargeReportResponse> response) {
                    Log.e("VerifyAccount", "hello response : " + new Gson().toJson(response.body()));
                    if (loader.isShowing())
                        loader.dismiss();
                    if (response.body() != null && response.body().getStatuscode() != null) {
                        if (response.body().getStatuscode().equalsIgnoreCase("2")) {
                            Successful(context, "Verifications successfully done.");
                            ActivityActivityMessage activityActivityMessage =
                                    new ActivityActivityMessage("AccountVerified", response.body().getBeneName());
                            GlobalBus.getBus().post(activityActivityMessage);
                        } else if (response.body().getStatuscode().equalsIgnoreCase("3") || response.body().getStatuscode().equalsIgnoreCase("-1")) {
                            Error(context, response.body().getMsg() + "");
                        }

                    }
                }

                @Override
                public void onFailure(Call<RechargeReportResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (loader.isShowing())
                        loader.dismiss();
                    try {
                        if (t.getMessage() != null && !t.getMessage().isEmpty()) {

                            if (t.getMessage().contains("No address associated with hostname")) {
                                NetworkError(context, context.getResources().getString(R.string.err_msg_network_title),
                                        context.getResources().getString(R.string.err_msg_network));
                            } else {
                                Error(context, t.getMessage());

                            }

                        } else {
                            Error(context, context.getResources().getString(R.string.some_thing_error));

                        }
                    } catch (IllegalStateException ise) {
                        Error(context, ise.getMessage());

                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void VerifyAccountNew(final Activity context, String oid, String mobileNo, String ifsc, String accountNo, String beneName,
                                 String bankName, String bankId, final CustomLoader loader) {
        try {
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<RechargeReportResponse> call = git.VerifyAccountNew(new GetSenderRequest(oid, new Senderobject(mobileNo),
                    new BeneDetail(mobileNo, beneName, ifsc, accountNo, bankName, bankId),
                    LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getLoginTypeID() + "",
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));

            call.enqueue(new Callback<RechargeReportResponse>() {

                @Override
                public void onResponse(Call<RechargeReportResponse> call, retrofit2.Response<RechargeReportResponse> response) {
                    if (loader.isShowing())
                        loader.dismiss();
                    if (response.isSuccessful()) {
                        if (response.body() != null && response.body().getStatuscode() != null) {
                            if (response.body().getStatuscode().equalsIgnoreCase("2")) {
                                Successful(context, "Verifications successfully done.");
                                ActivityActivityMessage activityActivityMessage =
                                        new ActivityActivityMessage("AccountVerified", response.body().getBeneName());
                                GlobalBus.getBus().post(activityActivityMessage);
                            } else if (response.body().getStatuscode().equalsIgnoreCase("3") || response.body().getStatuscode().equalsIgnoreCase("-1")) {
                                Error(context, response.body().getMsg() + "");
                            }

                        }
                    } else {
                        apiErrorHandle(context, response.code(), response.message());
                    }
                }

                @Override
                public void onFailure(Call<RechargeReportResponse> call, Throwable t) {
                    if (loader.isShowing())
                        loader.dismiss();
                    apiFailureError(context, t);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void dialogOk(final Activity context, String title, final String message, final int flag) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // "OK" button was clicked
                        if (flag == 4) {
                            ActivityActivityMessage activityActivityMessage =
                                    new ActivityActivityMessage("transferDone", "");
                            GlobalBus.getBus().post(activityActivityMessage);
                        } else if (flag == 3) {
                            // ((RegisterScreen) context).finishMethod();
                        } else if (flag == 8) {

                            ActivityActivityMessage activityActivityMessage = new ActivityActivityMessage("BeneficiaryListScreen", "");
                            GlobalBus.getBus().post(activityActivityMessage);

                        } else if (flag == 6) {
                            //Dispute on DMR
                            ActivityActivityMessage activityActivityMessage =
                                    new ActivityActivityMessage("disputeDMR", "");
                            GlobalBus.getBus().post(activityActivityMessage);

                        } else if (flag == 2) {


                        }

                    }
                })
                .show();
    }

    public void dialogOkSend(final Activity context, String title, final String message, final int flag) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // "OK" button was clicked
                        if (flag == 4) {
                            ActivityActivityMessage activityActivityMessage =
                                    new ActivityActivityMessage("transferDone", "");
                            GlobalBus.getBus().post(activityActivityMessage);
                        } else if (flag == 3) {
                            // ((RegisterScreen) context).finishMethod();
                        } else if (flag == 8) {

                            ActivityActivityMessage activityActivityMessage = new ActivityActivityMessage("BeneficiaryListScreen", "");
                            GlobalBus.getBus().post(activityActivityMessage);

                        } else if (flag == 6) {
                            //Dispute on DMR
                            ActivityActivityMessage activityActivityMessage =
                                    new ActivityActivityMessage("disputeDMR", "");
                            GlobalBus.getBus().post(activityActivityMessage);

                        } else if (flag == 2) {


                        }

                    }
                })
                .show();
    }

    public void logout(Context context) {
        setLoginPref(context, "", "");
        Intent startIntent = new Intent(context, Splash.class);
        startIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(startIntent);
    }

    public void setLoginPref(Context context, String mobile, String LoginPref) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.LoginPref, LoginPref);
        editor.putString(ApplicationConstant.INSTANCE.UMobile, mobile);

        if (LoginPref != null && !LoginPref.isEmpty()) {
            editor.putInt(ApplicationConstant.INSTANCE.IsLoginPref, 1);
        } else {
            editor.putInt(ApplicationConstant.INSTANCE.IsLoginPref, 0);
        }
        editor.commit();

    }

    public String getUserMobile(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        return prefs.getString(ApplicationConstant.INSTANCE.UMobile, "");
    }

    public int getIsLogin(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        return prefs.getInt(ApplicationConstant.INSTANCE.IsLoginPref, 0);
    }

    public void setUserDataPref(Context context, String value) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.UserDetailPref, value);
        editor.commit();

    }

    public String getUserDataPref(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, MODE_PRIVATE);
        return prefs.getString(ApplicationConstant.INSTANCE.UserDetailPref, "");

    }

    public void setPinRequiredPref(Context context, boolean value) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(ApplicationConstant.INSTANCE.PinRequiredPref, value);
        editor.commit();

    }

    public boolean getPinRequiredPref(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, MODE_PRIVATE);
        return prefs.getBoolean(ApplicationConstant.INSTANCE.PinRequiredPref, false);

    }

    public int getWIDPref(Activity context) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        return prefs.getInt(ApplicationConstant.INSTANCE.WidPref, 0);

    }

    public void setWIDPref(Context context, int value) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(ApplicationConstant.INSTANCE.WidPref, value);
        editor.commit();
    }


    public void setRealApiPref(Context context, boolean value) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(ApplicationConstant.INSTANCE.RealApiPref, value);
        editor.commit();

    }

    public boolean getRealApiPref(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, MODE_PRIVATE);
        return prefs.getBoolean(ApplicationConstant.INSTANCE.RealApiPref, false);

    }

    public void setDenoIncentivePref(Context context, boolean value) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(ApplicationConstant.INSTANCE.IncentiveInfoPref, value);
        editor.commit();

    }

    public boolean getIncentiveInfoPref(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        return prefs.getBoolean(ApplicationConstant.INSTANCE.IncentiveInfoPref, false);

    }
    public void setDoubleFactorPref(Context context, boolean value) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(ApplicationConstant.INSTANCE.DoubleFactorPref, value);
        editor.commit();

    }

    public boolean getDoubleFactorPref(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, MODE_PRIVATE);
        return prefs.getBoolean(ApplicationConstant.INSTANCE.DoubleFactorPref, false);

    }

    public String getSessionID(Context context) {
        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, MODE_PRIVATE);
        String getLoginPref = myPrefs.getString(ApplicationConstant.INSTANCE.LoginPref, "");
        LoginResponse LoginPrefResponse = new Gson().fromJson(getLoginPref, LoginResponse.class);
        return LoginPrefResponse.getData().getSessionID();
    }

    public String getSlabID(Context context) {
        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, MODE_PRIVATE);
        String getLoginPref = myPrefs.getString(ApplicationConstant.INSTANCE.LoginPref, "");
        LoginResponse LoginPrefResponse = new Gson().fromJson(getLoginPref, LoginResponse.class);
        return LoginPrefResponse.getData().getSlabID();
    }

    public String getLoginPref(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        return prefs.getString(ApplicationConstant.INSTANCE.LoginPref, "");
    }

    public void setServicesPref(Context context, String Postpaid, String Prepaid, String Landline, String DTH_Datacard, String Electricity,
                                String DMR, String Hotel, String Flight, String InsurancePremium, String Gas, String WaterBills,
                                String DTHConnections) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.servicesPref, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString(ApplicationConstant.INSTANCE.Services_Postpaid, Postpaid);
        editor.putString(ApplicationConstant.INSTANCE.Services_Prepaid, Prepaid);
        editor.putString(ApplicationConstant.INSTANCE.Services_Landline, Landline);
        editor.putString(ApplicationConstant.INSTANCE.Services_DTH_Datacard, DTH_Datacard);
        editor.putString(ApplicationConstant.INSTANCE.Services_Electricity, Electricity);
        editor.putString(ApplicationConstant.INSTANCE.Services_DMR, DMR);
        editor.putString(ApplicationConstant.INSTANCE.Services_Hotel, Hotel);
        editor.putString(ApplicationConstant.INSTANCE.Services_Flight, Flight);
        editor.putString(ApplicationConstant.INSTANCE.Services_InsurancePremium, InsurancePremium);
        editor.putString(ApplicationConstant.INSTANCE.Services_Gas, Gas);
        editor.putString(ApplicationConstant.INSTANCE.Services_WaterBills, WaterBills);
        editor.putString(ApplicationConstant.INSTANCE.Services_DTHConnections, DTHConnections);
        editor.commit();

    }

    public void setKeyId(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.regKeyIdPref, key);
        editor.commit();
    }

    public void setRegKeyStatus(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.regKeyStatusPref, key);
        editor.commit();
    }

    public String getRegKeyStatus(Context context) {
        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        String key = myPrefs.getString(ApplicationConstant.INSTANCE.regKeyStatusPref, null);
        return key;
    }

    public String getKeyId(Context context) {
        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        String key = myPrefs.getString(ApplicationConstant.INSTANCE.regKeyIdPref, null);
        return key;
    }

    public String getRoleId(Context context) {
        String loginPref = UtilMethods.INSTANCE.getLoginPref(context);
        if (loginPref != null && !loginPref.isEmpty()) {
            LoginResponse loginResponse = new Gson().fromJson(loginPref, LoginResponse.class);
            return loginResponse.getData().getRoleID();
        }
        return null;
    }

    public void setBankList(Context context, String bankList) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.bankListPref, bankList);
        editor.commit();
    }

    public String getBankList(Context context) {

        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        return prefs.getString(ApplicationConstant.INSTANCE.bankListPref, null);

    }

    public void setSenderNumber(Context context, String senderNumber, String senderName, String remainingbal, String senderInfo) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.senderNumberPref, senderNumber);
        editor.putString(ApplicationConstant.INSTANCE.senderNamePref, senderName);
        editor.putString(ApplicationConstant.INSTANCE.senderBalance, remainingbal);
        editor.putString(ApplicationConstant.INSTANCE.senderInfoPref, senderInfo);
        editor.commit();
    }

    public void setSenderInfo(Context context, String senderInfo, String senderNumber, boolean flag, final CustomLoader loader) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.senderInfoPref, senderInfo);
        editor.commit();
    }

    public void setBeneficiaryList(Context context, String beneficiaryList) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.beneficiaryListPref, beneficiaryList);
        editor.commit();

    }

    public void setBankDetailList(Context context, String bankList) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.bankDetailListPref, bankList);
        editor.commit();
    }

    public String getBusinessModuleID(Context context) {
        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, MODE_PRIVATE);
        String BusinessModuleID = myPrefs.getString(ApplicationConstant.INSTANCE.BusinessModuleID, null);

        return BusinessModuleID;
    }

    public void setSlabList(Context context, String bankList) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.slabListPref, bankList);
        editor.commit();
    }

    public boolean isVpnConnected(Context mContext) {
        ConnectivityManager m_ConnectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        List<NetworkInfo> connectedNetworks = new ArrayList<>();

        if (Build.VERSION.SDK_INT >= 21) {
            Network[] networks = m_ConnectivityManager.getAllNetworks();

            for (Network n : networks) {
                NetworkInfo ni = m_ConnectivityManager.getNetworkInfo(n);

                if (ni.isConnectedOrConnecting()) {
                    connectedNetworks.add(ni);
                }
            }
            boolean bHasVPN = false;
            for (NetworkInfo ni : connectedNetworks) {
                bHasVPN |= (ni.getType() == ConnectivityManager.TYPE_VPN);
            }
            return bHasVPN;
        } else {
            List<String> networkList = new ArrayList<>();
            try {
                for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                    if (networkInterface.isUp())
                        networkList.add(networkInterface.getName());
                }
            } catch (Exception ex) {

            }
            return networkList.contains("tun0") || networkList.contains("ppp0");
        }


    }

    public String getSerialNo(Context context) {
        String serialNo = "";

        if(android.os.Build.VERSION.SDK_INT >= 29)
        {
            serialNo=androidId(context);
        }
        else if (android.os.Build.VERSION.SDK_INT >= 26) {
            // only for gingerbread and newer versions

            if (ActivityCompat.checkSelfPermission(context,Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                return null;
            }
            serialNo = android.os.Build.getSerial() + "";
        } else if (android.os.Build.VERSION.SDK_INT <= 25) {
            serialNo = android.os.Build.SERIAL + "";
        }

        return serialNo;
    }

    public boolean isSimAvailable(Context context) {
        String[] nonActualDeviceArray = context.getResources().getStringArray(R.array.nonActualDeviceSimArray);
        ArrayList<String> nonActualDeviceStringArray = new ArrayList(Arrays.asList(nonActualDeviceArray));

        boolean isSimAvailable = true;
        boolean isActualDevice = true;
        TelephonyManager telMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        String networkProviderName = telMgr.getNetworkOperatorName();
        String networkSimOperatorName = telMgr.getSimOperatorName();

        if (networkProviderName != null && !networkProviderName.isEmpty() && nonActualDeviceStringArray.contains(networkProviderName) ||
                networkSimOperatorName != null && !networkSimOperatorName.isEmpty() && nonActualDeviceStringArray.contains(networkSimOperatorName)) {
            isSimAvailable = false;
            isActualDevice = false;
        } else {
            isActualDevice = true;
            int simState = telMgr.getSimState();
            switch (simState) {
                case TelephonyManager.SIM_STATE_ABSENT:
                    isSimAvailable = false;
                    break;
                case TelephonyManager.SIM_STATE_NETWORK_LOCKED:
                    isSimAvailable = false;
                    break;
                case TelephonyManager.SIM_STATE_PIN_REQUIRED:
                    isSimAvailable = false;
                    break;
                case TelephonyManager.SIM_STATE_PUK_REQUIRED:
                    isSimAvailable = false;
                    break;
                case TelephonyManager.SIM_STATE_READY:
                    isSimAvailable = true;
                    break;
                case TelephonyManager.SIM_STATE_UNKNOWN:
                    isSimAvailable = false;
                    break;
            }
        }
        if (!isSimAvailable && isActualDevice) {
            ArrayList<String> networkProvider = getNetworkProvider(context);
            if (networkProvider != null && networkProvider.size() > 0) {
                isSimAvailable = true;
            }
        }
        return isSimAvailable;
    }

    public ArrayList<String> getNetworkProvider(Context context) {
        ArrayList<String> carrierNameArray = new ArrayList<>();
        int permissionCheck = ContextCompat.checkSelfPermission(context,
                Manifest.permission.READ_PHONE_STATE);

        Activity activity = (Activity) context;
        String networkProvider = "";

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.READ_PHONE_STATE},
                    REQUEST_EXTERNAL_STORAGE);
        } else {
            TelephonyManager telephonyManager = (TelephonyManager)
                    context.getSystemService(Context.TELEPHONY_SERVICE);

            try {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
                    final SubscriptionManager subscriptionManager;
                    subscriptionManager = SubscriptionManager.from(context);
                    final List<SubscriptionInfo> activeSubscriptionInfoList = subscriptionManager.getActiveSubscriptionInfoList();
                    for (SubscriptionInfo subscriptionInfo : activeSubscriptionInfoList) {
                        final CharSequence carrierName = subscriptionInfo.getCarrierName();
                        carrierNameArray.add(carrierName.toString());
                    }
                } else {
                    networkProvider = telephonyManager.getNetworkOperatorName();
                    if (networkProvider != null && !networkProvider.isEmpty()) {
                        carrierNameArray.add(networkProvider);
                    }
                }
            } catch (Exception e) {

            }
        }


        return carrierNameArray;
    }

    public void versionDialog(final Activity mContext) {
        if (alertDialogVersion != null && alertDialogVersion.isShowing()) {
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext); // create an alert box
        builder.setTitle("Alert!!");
        builder.setMessage("New Update Available.");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", null);
        alertDialogVersion = builder.create();
        alertDialogVersion.show();
        alertDialogVersion.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToVersionUpdate(mContext);
            }
        });
    }

    private void goToVersionUpdate(Context mContext) {

        try {
            mContext.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=" + BuildConfig.APPLICATION_ID)));
        } catch (android.content.ActivityNotFoundException anfe) {
            mContext.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=" +
                            BuildConfig.APPLICATION_ID)));
        }
        // finish();
    }

    protected void makeLinkClickable(final Context context, SpannableStringBuilder strBuilder, final URLSpan span) {
        int start = strBuilder.getSpanStart(span);
        int end = strBuilder.getSpanEnd(span);
        int flags = strBuilder.getSpanFlags(span);
        ClickableSpan clickable = new ClickableSpan() {
            public void onClick(View view) {
                try {
                    context.startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse(span.getURL()))
                            .setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP));
                } catch (android.content.ActivityNotFoundException anfe) {

                }
            }
        };
        strBuilder.setSpan(clickable, start, end, flags);
        strBuilder.removeSpan(span);
    }

    public void setTextViewHTML(Context context, TextView text, String html) {
        CharSequence sequence = Html.fromHtml(html);
        SpannableStringBuilder strBuilder = new SpannableStringBuilder(sequence);
        URLSpan[] urls = strBuilder.getSpans(0, sequence.length(), URLSpan.class);
        for (URLSpan span : urls) {
            makeLinkClickable(context, strBuilder, span);
        }
        text.setText(strBuilder);
        text.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void setReferrerId(Context context, String value) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefRefferalDataPref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.UserReferralPref, value);
        editor.commit();

    }

    public String getReferrerId(Activity context) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefRefferalDataPref, context.MODE_PRIVATE);
        return prefs.getString(ApplicationConstant.INSTANCE.UserReferralPref, "");

    }


    public void setReferrerIdSetData(Context context, boolean value) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefRefferalDataPref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(ApplicationConstant.INSTANCE.IsUserReferralDataPref, value);
        editor.commit();

    }

    public boolean isReferrerIdSetData(Activity context) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefRefferalDataPref, context.MODE_PRIVATE);
        return prefs.getBoolean(ApplicationConstant.INSTANCE.IsUserReferralDataPref, false);

    }

    public long hourDifference(long millisFirst, long millisSecond) {
        return TimeUnit.MILLISECONDS.toHours(millisSecond - millisFirst);
    }



    public interface DialogCallBack {
        void onPositiveClick(String value);

        void onCancelClick();
    }

    public interface ApiCallBack {
        void onSucess(Object object);
    }

    public interface ApiCallBackTwoMethod {
        void onSucess(Object object);

        void onError(Object object);
    }



    public String formatedAmountWithRupees(String value) {

        if (value != null && !value.isEmpty()) {
            if (value.contains(".")) {
                String postfixValue = value.substring(value.indexOf("."));
                if (postfixValue.equalsIgnoreCase(".0")) {
                    return "\u20B9 " + value.replace(".0", "").trim();
                } else if (postfixValue.equalsIgnoreCase(".00")) {
                    return "\u20B9 " + value.replace(".00", "").trim();
                } else if (postfixValue.equalsIgnoreCase(".000")) {
                    return "\u20B9 " + value.replace(".000", "").trim();
                } else if (postfixValue.equalsIgnoreCase(".0000")) {
                    return "\u20B9 " + value.replace(".0000", "").trim();
                } else {
                    try {
                        return "\u20B9 " + String.format("%.2f", Double.parseDouble(value.trim()));
                    } catch (NumberFormatException nfe) {
                        return "\u20B9 " + value.trim();
                    }
                }
            } else {
                return "\u20B9 " + value.trim();
            }

        } else {
            return "\u20B9 0";
        }
    }
    public String formatedAmount(String value) {
        if (value != null && !value.isEmpty()) {
            if (value.contains(".")) {
                String postfixValue = value.substring(value.indexOf("."));
                if (postfixValue.equalsIgnoreCase(".0")) {
                    return value.replace(".0", "");
                } else if (postfixValue.equalsIgnoreCase(".00")) {
                    return value.replace(".00", "");
                } else if (postfixValue.equalsIgnoreCase(".000")) {
                    return value.replace(".000", "");
                } else if (postfixValue.equalsIgnoreCase(".0000")) {
                    return value.replace(".0000", "");
                } else {
                    return value;
                }
            } else {
                return value;
            }

        } else {
            return "0";
        }


    }


    private void setTotalTargetData(Context context,String toJson) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.totalTargetDataPref, toJson);
        editor.commit();
    }
    public String getTotalTargetData(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        return prefs.getString(ApplicationConstant.INSTANCE.totalTargetDataPref, "");
    }

    public void RefundRequest(final Context context, String tid, String transactionID, String remarkText, final CustomLoader loader, final TextView dispute,final ApiCallBack mApiCallBack) {

        String LoginResponse = UtilMethods.INSTANCE.getLoginPref(context);
        LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
        EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);

        Call<RefundRequestResponse> call = git.RefundRequest(
                new RefundRequestRequest(tid
                        , transactionID
                        , LoginDataResponse.getData().getUserID() + ""
                        , LoginDataResponse.getData().getSessionID() + ""
                        , LoginDataResponse.getData().getSession() + ""
                        , ApplicationConstant.INSTANCE.APP_ID
                        , UtilMethods.INSTANCE.getIMEI(context)
                        , ""
                        , BuildConfig.VERSION_NAME + ""
                        , UtilMethods.INSTANCE.getSerialNo(context) + ""
                        , LoginDataResponse.getData().getLoginTypeID() + ""));
        call.enqueue(new Callback<RefundRequestResponse>() {
            @Override
            public void onResponse(Call<RefundRequestResponse> call, Response<RefundRequestResponse> response) {

                if (loader != null)
                    if (loader.isShowing())
                        loader.dismiss();

                if (response.body() != null && response.body().getStatuscode() != null) {

                    if (response.body().getStatuscode().equalsIgnoreCase("1")) {
                        if(mApiCallBack!=null){
                            mApiCallBack.onSucess(response.body());
                        }

                        if (response.body() != null && response.body().getMsg() != null) {
                            UtilMethods.INSTANCE.Successful((Activity) context, response.body().getMsg() + "");
                        }
                        dispute.setVisibility(View.GONE);
                    } else if (response.body().getStatuscode().equalsIgnoreCase("-1")) {
                        if (response.body() != null && response.body().getMsg() != null) {
                            UtilMethods.INSTANCE.Error((Activity) context, response.body().getMsg() + "");
                        }
                    } else {
                        if (response.body() != null && response.body().getMsg() != null) {
                            UtilMethods.INSTANCE.Error((Activity) context, response.body().getMsg() + "");
                        }
                    }

                }
            }

            @Override
            public void onFailure(Call<RefundRequestResponse> call, Throwable t) {
                Log.e("response", "error ");
                if (loader != null)
                    if (loader.isShowing())
                        loader.dismiss();
                if (t.getMessage().contains("No address associated with hostname")) {
                    UtilMethods.INSTANCE.Error((Activity) context, context.getResources().getString(R.string.network_error));
                } else {
                    UtilMethods.INSTANCE.Error((Activity) context, t.getMessage());
                }
            }
        });
    }


    public void MakeW2RRequest(final Activity context, String tid, String transactionID, String RightAccount, final CustomLoader loader, final AppCompatTextView w2r) {
        try {
            String LoginResponse = UtilMethods.INSTANCE.getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<RefundRequestResponse> call = git.MakeW2RRequest(
                    new W2RRequest(ApplicationConstant.INSTANCE.APP_ID,
                            UtilMethods.INSTANCE.getIMEI(context),
                            LoginDataResponse.getData().getLoginTypeID() + "",
                            "", UtilMethods.INSTANCE.getSerialNo(context) + "",
                            LoginDataResponse.getData().getSession() + "",
                            LoginDataResponse.getData().getSessionID() + "",
                            LoginDataResponse.getData().getUserID() + "",
                            BuildConfig.VERSION_NAME + "",
                            tid,
                            transactionID,
                            RightAccount));
            call.enqueue(new Callback<RefundRequestResponse>() {
                @Override
                public void onResponse(Call<RefundRequestResponse> call, Response<RefundRequestResponse> response) {

                    if (loader != null)
                        if (loader.isShowing())
                            loader.dismiss();

                    if (response.body() != null && response.body().getStatuscode() != null) {

                        if (response.body().getStatuscode().equalsIgnoreCase("1")) {

                            if (response.body() != null && response.body().getMsg() != null) {
                                UtilMethods.INSTANCE.Successful((Activity) context, response.body().getMsg() + "");
                            }
                            w2r.setVisibility(View.GONE);
                        } else if (response.body().getStatuscode().equalsIgnoreCase("-1")) {
                            if (response.body() != null && response.body().getMsg() != null) {
                                UtilMethods.INSTANCE.Error((Activity) context, response.body().getMsg() + "");
                            }
                        } else {
                            if (response.body() != null && response.body().getMsg() != null) {
                                UtilMethods.INSTANCE.Error((Activity) context, response.body().getMsg() + "");
                            }
                        }

                    }
                }

                @Override
                public void onFailure(Call<RefundRequestResponse> call, Throwable t) {
                    if (loader != null)
                        if (loader.isShowing())
                            loader.dismiss();
                    displayingOnFailuireMessage(context, t,null);
                }
            });
        } catch (Exception ex) {
            if (loader != null)
                if (loader.isShowing())
                    loader.dismiss();
            Log.e("Exception", "" + ex.getMessage());
        }
    }



    public void AEPSReport(final Activity context, AEPSReportRequest aepsReportRequest, final CustomLoader loader, final ApiCallBackTwoMethod apiCallBackTwoMethod) {
        try {

            Log.e("AEPSReport", "Req : " + new Gson().toJson(aepsReportRequest));
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<AEPSResponse> call = git.getAEPSReport(aepsReportRequest);
            call.enqueue(new Callback<AEPSResponse>() {
                @Override
                public void onResponse(Call<AEPSResponse> call, final retrofit2.Response<AEPSResponse> response) {
                    Log.e("AEPSReport", "Response : " + new Gson().toJson(response.body()));
                    try {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                        if (response.body() != null) {
                            if (response.body().getStatuscode() == 1) {

                                if (apiCallBackTwoMethod != null) {
                                    apiCallBackTwoMethod.onSucess(response.body());
                                }

                            } else {
                                if (response.body().getVersionValid() != null && response.body().getVersionValid() == false) {
                                    versionDialog((Activity) context);
                                } else {
                                    UtilMethods.INSTANCE.Error((Activity) context, response.body().getMsg() + "");
                                }
                            }
                        }
                    } catch (Exception e) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                        Error(context, e.getMessage());
                    }

                }

                @Override
                public void onFailure(Call<AEPSResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }
                    displayingOnFailuireMessage(context, t,null);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            if (loader != null) {
                if (loader.isShowing())
                    loader.dismiss();
            }
        }
    }

    public void GetTransactionMode(final MoveToWalletActivity context, final CustomLoader loader) {
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            String LoginResponse = UtilMethods.INSTANCE.getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            Call<TransactionModeResponse> call = git.GetTransactionMode(new BalanceRequest(LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getLoginTypeID() + "",
                    ApplicationConstant.INSTANCE.APP_ID,
                    UtilMethods.INSTANCE.getIMEI(context),
                    "", BuildConfig.VERSION_NAME, UtilMethods.INSTANCE.getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));
            call.enqueue(new Callback<TransactionModeResponse>() {
                @Override
                public void onResponse(Call<TransactionModeResponse> call, Response<TransactionModeResponse> response) {
                    if (loader != null)
                        if (loader.isShowing())
                            loader.dismiss();
                    if (response.body() != null) {
                        if (response.body().getStatuscode().equalsIgnoreCase("1")) {

                            if (response.body().getTransactionModes() != null && response.body().getTransactionModes().size() > 0) {


                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<TransactionModeResponse> call, Throwable t) {
                    if (loader != null)
                        if (loader.isShowing())
                            loader.dismiss();

                    displayingOnFailuireMessage(context, t,null);

                }
            });

        } catch (Exception ex) {
            Log.e("Exception", "" + ex.getMessage());
            if (loader != null)
                if (loader.isShowing())
                    loader.dismiss();

        }
    }

    public void MoveToWallet(final Activity context, String actiontype, String transMode, String amount, int mtwid,final int oid,final CustomLoader loader) {
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            String LoginResponse = UtilMethods.INSTANCE.getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            Call<TransactionModeResponse> call = git.MoveToWallet(new MoveToWalletRequest(ApplicationConstant.INSTANCE.APP_ID, UtilMethods.INSTANCE.getIMEI(context),
                    LoginDataResponse.getData().getLoginTypeID() + "", "",
                    UtilMethods.INSTANCE.getSerialNo(context),
                    LoginDataResponse.getData().getSession(),
                    LoginDataResponse.getData().getSessionID(),
                    LoginDataResponse.getData().getUserID() + "",
                    BuildConfig.VERSION_NAME, actiontype, transMode, amount,mtwid,oid));
            call.enqueue(new Callback<TransactionModeResponse>() {
                @Override
                public void onResponse(Call<TransactionModeResponse> call, Response<TransactionModeResponse> response) {
                    if (loader != null)
                        if (loader.isShowing())
                            loader.dismiss();
                    if (response.body() != null) {
                        if (response.body().getStatuscode().equalsIgnoreCase("1")) {
                            UtilMethods.INSTANCE.Successfulok(response.body().getMsg(), (MoveToWalletActivity) context);
                        } else {
                            if (response.body().getMsg() != null) {
                                UtilMethods.INSTANCE.Error(context, response.body().getMsg());
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<TransactionModeResponse> call, Throwable t) {
                    if (loader != null)
                        if (loader.isShowing())
                            loader.dismiss();

                }
            });

        } catch (Exception ex) {

            if (loader != null)
                if (loader.isShowing())
                    loader.dismiss();
        }
    }

    public void viewRangeClick(SlabDetailDisplayLvl operator, CustomLoader loader, Activity context){

        if (UtilMethods.INSTANCE.isNetworkAvialable(context)) {
            UtilMethods.INSTANCE.RSlabRangDetail(context, operator.getOid() ,loader, new UtilMethods.ApiCallBack() {
                @Override
                public void onSucess(Object object) {
                    if(object!=null && object instanceof RSlabRangDetailResponse){
                        RSlabRangDetailResponse slabRangDetailResponse=(RSlabRangDetailResponse)object;
                        if(slabRangDetailResponse!=null  && slabRangDetailResponse.getSlabRangeDetail()!=null && slabRangDetailResponse.getSlabRangeDetail().size()>0){

                            AlertDialog.Builder dialogBuilder;
                            dialogBuilder = new AlertDialog.Builder(context);
                            alertDialogSlabRange = dialogBuilder.create();
                            //alertDialogSlabRange.getWindow().setBackgroundDrawableResource(android.R.color.white);
                            LayoutInflater inflater = context.getLayoutInflater();
                            View dialogView = inflater.inflate(R.layout.dialog_slab_range_details, null);
                            alertDialogSlabRange.setView(dialogView);
                            View maxCommView,fixedCommView;
                            ImageView closeView = dialogView.findViewById(R.id.iv_cancleView);
                            ImageView opImage = dialogView.findViewById(R.id.iv_opImage);
                            TextView opName = dialogView.findViewById(R.id.tv_opName);
                            TextView opRange = dialogView.findViewById(R.id.tv_opRange);
                            maxCommView = dialogView.findViewById(R.id.maxCommView);
                            fixedCommView = dialogView.findViewById(R.id.fixedCommView);
                            maxCommView.setVisibility(View.GONE);
                            fixedCommView.setVisibility(View.GONE);

                            RecyclerView slabRangeRecyclerView = dialogView.findViewById(R.id.rv_slabRange);
                            slabRangeRecyclerView.setLayoutManager(new LinearLayoutManager(context));

                            CommissionSlabDetailAdapter commissionSlabAdapter=new CommissionSlabDetailAdapter(slabRangDetailResponse.getSlabRangeDetail(),context,maxCommView,fixedCommView);
                            slabRangeRecyclerView.setAdapter(commissionSlabAdapter);

                            opName.setText(operator.getOperator()+"");
                            opRange.setText("Range : " + operator.getMin()  +" - " + operator.getMax() );

                            closeView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    alertDialogSlabRange.dismiss();
                                }
                            });

                            RequestOptions requestOptions = new RequestOptions();
                            requestOptions.placeholder(R.mipmap.ic_launcher);
                            requestOptions.error(R.mipmap.ic_launcher);
                            requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
                            Glide.with(context)
                                    .load(ApplicationConstant.INSTANCE.baseIconUrl + operator.getOid() + ".png")
                                    .apply(requestOptions)
                                    .into(opImage);

                            alertDialogSlabRange.show();

                        }else {
                            UtilMethods.INSTANCE.Error(context,"No Slab Range Data Found!!");
                        }
                    }
                }

            });

        }
    }

    public void RSlabRangDetail(final Activity context,final int oid ,final CustomLoader loader,final ApiCallBack apiCallBack) {
        try {
            String LoginResponse = UtilMethods.INSTANCE.getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse,LoginResponse.class);
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<RSlabRangDetailResponse> call = git.RSlabRangDetail(new RSlabRangDetailRequest(oid,
                    LoginDataResponse.getData().getUserID() + "",LoginDataResponse.getData().getLoginTypeID() + "",
                    ApplicationConstant.INSTANCE.APP_ID,
                    UtilMethods.INSTANCE.getIMEI(context),
                    "",BuildConfig.VERSION_NAME,UtilMethods.INSTANCE.getSerialNo(context),LoginDataResponse.getData().getSessionID(),LoginDataResponse.getData().getSession()));
            call.enqueue(new Callback<RSlabRangDetailResponse>() {

                @Override
                public void onResponse(Call<RSlabRangDetailResponse> call,retrofit2.Response<RSlabRangDetailResponse> response) {
                    if (loader !=null && loader.isShowing())
                        loader.dismiss();

                    try {
                        if (response.body() != null && response.body().getStatuscode() != null) {
                            if (response.body().getStatuscode() == 1) {
                                if(apiCallBack!=null)
                                {
                                    apiCallBack.onSucess(response.body());
                                }
                            } else if (response.body().getStatuscode() == -1) {
                                if (response.body().isVersionValid() == false) {
                                    versionDialog(context);
                                } else {
                                    UtilMethods.INSTANCE.Error(context,response.body().getMsg() + "");
                                }
                            }

                        }
                    }catch (Throwable t){

                        UtilMethods.INSTANCE.Error(context,t.getMessage());
                    }

                }

                @Override
                public void onFailure(Call<RSlabRangDetailResponse> call,Throwable t) {
                    if (loader !=null && loader.isShowing())
                        loader.dismiss();


                    UtilMethods.INSTANCE.Error(context,t.getMessage());

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            if (loader !=null && loader.isShowing())
                loader.dismiss();
        }

    }

    public void UserAchieveTarget(final Activity context, final boolean isTotal, final CustomLoader loader, LoginResponse LoginDataResponse, final ApiCallBackTwoMethod mApiCallBack) {
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<AppUserListResponse> call = git.GetTargetAchieved(new AchieveTargetRequest(isTotal,
                    LoginDataResponse.getData().getUserID(), LoginDataResponse.getData().getLoginTypeID(),
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));


            call.enqueue(new Callback<AppUserListResponse>() {
                @Override
                public void onResponse(Call<AppUserListResponse> call, final retrofit2.Response<AppUserListResponse> response) {

                    try {
                        if (loader != null) {
                            if (loader.isShowing()) {
                                loader.dismiss();
                            }
                        }
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                if (response.body().getStatuscode() == 1) {
                                    if (isTotal) {

                                        setTotalTargetData(context, new Gson().toJson(response.body()));
                                    }
                                    if (mApiCallBack != null) {
                                        mApiCallBack.onSucess(response.body());
                                    }
                                } else if (response.body().getStatuscode() == -1) {
                                    if (mApiCallBack != null) {
                                        mApiCallBack.onError(ERROR_OTHER);
                                    }
                                    if (!isTotal) {
                                        if (response.body().getVersionValid() == false) {
                                            versionDialog(context);
                                        } else {
                                            Error(context, response.body().getMsg() + "");
                                        }
                                    }
                                }
                            }
                        } else {
                            if (mApiCallBack != null) {
                                mApiCallBack.onError(ERROR_OTHER);
                            }
                            apiErrorHandle(context, response.code(), response.message());
                        }
                    } catch (Exception e) {
                        if (loader != null) {
                            if (loader.isShowing()) {
                                loader.dismiss();
                            }
                        }
                        if (mApiCallBack != null) {
                            mApiCallBack.onError(ERROR_OTHER);
                        }
                    }

                }

                @Override
                public void onFailure(Call<AppUserListResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing()) {
                            loader.dismiss();
                        }
                    }
                    if (!isTotal) {
                        if (t instanceof UnknownHostException || t instanceof IOException) {
                            NetworkError(context);
                            if (mApiCallBack != null) {
                                mApiCallBack.onError(ERROR_NETWORK+"");
                            }
                        }
                        else if (t instanceof SocketTimeoutException || t instanceof TimeoutException) {
                            ErrorWithTitle(context, "TIME OUT ERROR", t.getMessage() + "");
                            if (mApiCallBack != null) {
                                mApiCallBack.onError(ERROR_OTHER);
                            }
                        } else {
                            if (mApiCallBack != null) {
                                mApiCallBack.onError(ERROR_OTHER);
                            }
                            if (t.getMessage() != null && !t.getMessage().isEmpty()) {
                                ErrorWithTitle(context, "FATAL ERROR", t.getMessage() + "");
                            } else {
                                Error(context, context.getResources().getString(R.string.some_thing_error));
                            }
                        }

                    }

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            if (mApiCallBack != null) {
                mApiCallBack.onError(ERROR_OTHER);
            }
        }
    }

    public void GetCallMeUserReq(final Activity mActivity, String mobNo, final CustomLoader loader) {
        try {
            loader.show();
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            String LoginResponse = getLoginPref(mActivity);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);

            Call<BasicResponse> call = git.GetCallMeUserReq(new CallBackRequest(mobNo,
                    LoginDataResponse.getData().getUserID(), LoginDataResponse.getData().getLoginTypeID(),
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(mActivity),
                    "", BuildConfig.VERSION_NAME, getSerialNo(mActivity), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));

            call.enqueue(new Callback<BasicResponse>() {

                @Override
                public void onResponse(Call<BasicResponse> call, retrofit2.Response<BasicResponse> response) {

                    if (loader.isShowing())
                        loader.dismiss();
                    BasicResponse mBasicResponse = response.body();
                    if (mBasicResponse != null) {
                        if (mBasicResponse.getStatuscode() == 1) {
                            Successful(mActivity, mBasicResponse.getMsg() + "");

                        } else if (response.body().getStatuscode() == -1) {

                            Processing(mActivity, mBasicResponse.getMsg() + "");
                        } else {
                            Processing(mActivity, mBasicResponse.getMsg() + "");
                        }

                    } else {

                        Error(mActivity, mActivity.getResources().getString(R.string.some_thing_error));
                    }
                }

                @Override
                public void onFailure(Call<BasicResponse> call, Throwable t) {


                    try {
                        if (loader.isShowing()) {
                            loader.dismiss();
                        }
                        if (t.getMessage() != null && !t.getMessage().isEmpty()) {

                            NetworkError(mActivity, mActivity.getResources().getString(R.string.err_msg_network_title), mActivity.getResources().getString(R.string.err_msg_network));

                        } else {

                            Error(mActivity, mActivity.getResources().getString(R.string.some_thing_error));
                        }
                    } catch (IllegalStateException ise) {
                        loader.dismiss();

                        Error(mActivity, mActivity.getResources().getString(R.string.some_thing_error));
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();

            Error(mActivity, mActivity.getResources().getString(R.string.some_thing_error));
        }

    }

    public void EnableDisableRealApi(final Activity context, boolean isRealApi, final CustomLoader loader, final ApiCallBack mApiCallBack) {
        try {
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<BasicResponse> call = git.ChangeRealAPIStatus(new RealApiChangeRequest(isRealApi,
                    LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getLoginTypeID() + "",
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));

            call.enqueue(new Callback<BasicResponse>() {

                @Override
                public void onResponse(Call<BasicResponse> call, retrofit2.Response<BasicResponse> response) {
                    if (loader != null && loader.isShowing()) {
                        loader.dismiss();
                    }

                    if (response.body() != null) {
                        if (response.body().getStatuscode() == 1) {
                            if (mApiCallBack != null) {
                                mApiCallBack.onSucess(response.body());
                            }
                        } else {
                            if (response.body().getVersionValid() == false) {
                                versionDialog(context);
                            } else {
                                Error(context, response.body().getMsg() + "");
                            }
                        }

                    }
                }

                @Override
                public void onFailure(Call<BasicResponse> call, Throwable t) {

                    if (loader != null && loader.isShowing()) {
                        loader.dismiss();
                    }
                    if (t.getMessage().contains("No address associated with hostname")) {
                        Error(context, context.getResources().getString(R.string.network_error));
                    } else {
                        Error(context, t.getMessage());
                    }
                }
            });

        } catch (Exception e) {
            if (loader != null && loader.isShowing()) {
                loader.dismiss();
            }
            e.printStackTrace();
        }

    }

    public void updateBank(final Activity context, UpdateBankRequest updateBankRequest, final CustomLoader loader, final ApiCallBack mApiCallBack) {
        try {

            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<BasicResponse> call = git.AppUpdateBank(updateBankRequest);

            call.enqueue(new Callback<BasicResponse>() {

                @Override
                public void onResponse(Call<BasicResponse> call, retrofit2.Response<BasicResponse> response) {
                    if (loader != null && loader.isShowing()) {
                        loader.dismiss();
                    }

                    if (response.body() != null) {
                        if (response.body().getStatuscode() == 1) {
                            if (mApiCallBack != null) {
                                mApiCallBack.onSucess(response.body());
                            }
                        } else {
                            if (response.body().getVersionValid() == false) {
                                versionDialog(context);
                            } else {
                                Error(context, response.body().getMsg() + "");
                            }
                        }

                    }
                }

                @Override
                public void onFailure(Call<BasicResponse> call, Throwable t) {

                    if (loader != null && loader.isShowing()) {
                        loader.dismiss();
                    }
                    if (t.getMessage().contains("No address associated with hostname")) {
                        Error(context, context.getResources().getString(R.string.network_error));
                    } else {
                        Error(context, t.getMessage());
                    }
                }
            });

        } catch (Exception e) {
            if (loader != null && loader.isShowing()) {
                loader.dismiss();
            }
            e.printStackTrace();
        }

    }

    public void UploadProfilePic(final Activity context, File selectedFile, final CustomLoader loader, LoginResponse LoginDataResponse, final ApiCallBack mApiCallBack) {
        try {

            BasicRequest mBasicRequest = new BasicRequest(
                    LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getLoginTypeID(),
                    ApplicationConstant.INSTANCE.APP_ID, getIMEI(context), "", BuildConfig.VERSION_NAME, getSerialNo(context),
                    LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession());

            String req = new Gson().toJson(mBasicRequest);
            // Parsing any Media type file
            MultipartBody.Part fileToUpload = null;
            if (selectedFile != null) {
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), selectedFile);
                if (selectedFile != null) {
                    fileToUpload = MultipartBody.Part.createFormData("file", selectedFile.getName(), requestBody);
                }
            }
            RequestBody requestStr = RequestBody.create(MediaType.parse("text/plain"), req);
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<BasicResponse> call = git.UploadProfile(fileToUpload, requestStr);
            call.enqueue(new Callback<BasicResponse>() {

                @Override
                public void onResponse(Call<BasicResponse> call, retrofit2.Response<BasicResponse> response) {
                    try {
                        if (loader != null && loader.isShowing()) {
                            loader.dismiss();
                        }

                        if (response.body() != null) {
                            if (response.body().getStatuscode() == 1) {
                                // setFundreqToList(context, new Gson().toJson(response.body()).toString());
                                Successful(context, response.body().getMsg() + "");

                                if (mApiCallBack != null) {
                                    mApiCallBack.onSucess(response.body());
                                }

                            } else if (response.body().getStatuscode() == -1) {
                                if (response.body().getVersionValid() == false) {
                                    versionDialog(context);
                                } else {
                                    Error(context, response.body().getMsg() + "");
                                }
                            } else {
                                Error(context, response.body().getMsg() + "");
                            }

                        } else {
                            Error(context, context.getResources().getString(R.string.some_thing_error));
                        }
                    } catch (Exception e) {
                        if (loader != null && loader.isShowing()) {
                            loader.dismiss();
                        }
                        Error(context, e.getMessage() + "");
                    }
                }

                @Override
                public void onFailure(Call<BasicResponse> call, Throwable t) {

                    try {
                        if (loader != null && loader.isShowing()) {
                            loader.dismiss();
                        }
                        NetworkError(context, context.getResources().getString(R.string.err_msg_network_title), context.getResources().getString(R.string.err_msg_network));
                        /*  Error(context, context.getResources().getString(R.string.err_something_went_wrong) + "");*/
                    } catch (IllegalStateException ise) {
                        Error(context, ise.getMessage());
                        if (loader != null && loader.isShowing()) {
                            loader.dismiss();
                        }
                    }
                }
            });

        } catch (Exception e) {

            if (loader != null && loader.isShowing()) {
                loader.dismiss();
            }
            Error(context, e.getMessage() + "");
            e.printStackTrace();
        }
    }

    public void WTRLogReport(final Activity context, Integer topRows, Integer criteria, String criteriaText, Integer status, String fromDate, String toDate, Integer dateType, String transactionID, final CustomLoader loader) {
        try {
            String LoginResponse = UtilMethods.INSTANCE.getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            RefundLogRequest mRefundLogRequest = new RefundLogRequest("0", topRows, criteria,
                    criteriaText, status, fromDate, toDate, dateType, transactionID, LoginDataResponse.getData().getUserID(), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession(),
                    ApplicationConstant.INSTANCE.APP_ID,
                    UtilMethods.INSTANCE.getIMEI(context),
                    "", BuildConfig.VERSION_NAME, UtilMethods.INSTANCE.getSerialNo(context), LoginDataResponse.getData().getLoginTypeID());
            /* String str =new Gson().toJson(mFundDCReportRequest);*/
            Call<AppUserListResponse> call = git.WTRLog(mRefundLogRequest);
            call.enqueue(new Callback<AppUserListResponse>() {
                @Override
                public void onResponse(Call<AppUserListResponse> call, final retrofit2.Response<AppUserListResponse> response) {

                    try {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                        if (response.body() != null && response.body().getStatuscode() != null) {
                            if (response.body().getStatuscode() == 1 && response.body().getRefundLog() != null && response.body().getRefundLog().size() > 0) {
                                ActivityActivityMessage activityActivityMessage =
                                        new ActivityActivityMessage("Refund_Log", "" + new Gson().toJson(response.body()));
                                GlobalBus.getBus().post(activityActivityMessage);
                            } else if (response.body().getStatuscode() == -1) {
                                if (response.body().getVersionValid() != null && response.body().getVersionValid() == false) {
                                    versionDialog(context);
                                } else {
                                    UtilMethods.INSTANCE.Error(context, response.body().getMsg() + "");
                                }
                            } else {
                                UtilMethods.INSTANCE.Error(context, "Report not found.");
                            }
                        }
                    } catch (Exception e) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                        UtilMethods.INSTANCE.Error(context, e.getMessage());
                    }

                }

                @Override
                public void onFailure(Call<AppUserListResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }

                    displayingOnFailuireMessage(context, t,null);

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            UtilMethods.INSTANCE.Error(context, e.getMessage());
        }
    }

    public void GenerateBeneficiaryOTP(final Activity context, final String MobileNumber,final ApiCallBack apiCallBack, final CustomLoader loader) {
        try {
            String LoginResponse = UtilMethods.INSTANCE.getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<RechargeReportResponse> call = git.GenerateBenficiaryOTP(new GetSenderRequest(new Senderobject(MobileNumber),
                    LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getLoginTypeID() + "", ApplicationConstant.INSTANCE.APP_ID,
                    UtilMethods.INSTANCE.getIMEI(context),
                    "", BuildConfig.VERSION_NAME, UtilMethods.INSTANCE.getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));

            call.enqueue(new Callback<RechargeReportResponse>() {

                @Override
                public void onResponse(Call<RechargeReportResponse> call, retrofit2.Response<RechargeReportResponse> response) {
                    if (loader!=null && loader.isShowing())
                        loader.dismiss();

                    if (response.body() != null && response.body().getStatuscode() != null) {
                        if (response.body().getStatuscode().equalsIgnoreCase("1")) {


                            if (apiCallBack != null) {
                                apiCallBack.onSucess(response.body());

                            }
                        } else if (response.body().getStatuscode().equalsIgnoreCase("-1")) {
                            if (response.body().getIsVersionValid() != null && response.body().getIsVersionValid().equalsIgnoreCase("false")) {
                                versionDialog(context);
                            } else {
                                UtilMethods.INSTANCE.Error(context, response.body().getMsg() + "");
                            }
                        }

                    }
                }

                @Override
                public void onFailure(Call<RechargeReportResponse> call, Throwable t) {
                    if (loader!=null && loader.isShowing())
                        loader.dismiss();
                    displayingOnFailuireMessage(context,t,null);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            if (loader!=null && loader.isShowing())
                loader.dismiss();
        }

    }

    public void ValidateBeneficiary(final Activity context, String MobileNumber,String accounNo,String otp,final ApiCallBack apiCallBack,final CustomLoader loader) {
        try {
            String LoginResponse = UtilMethods.INSTANCE.getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
            String senderno=prefs.getString(ApplicationConstant.INSTANCE.senderNumberPref,null);//
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<RechargeReportResponse> call = git.ValidateBeneficiary(new ValiSenderRequest(new Senderobject(senderno),MobileNumber,accounNo,otp, LoginDataResponse.getData().getUserID(),LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession(), ApplicationConstant.INSTANCE.APP_ID, UtilMethods.INSTANCE.getIMEI(context),"",BuildConfig.VERSION_NAME,UtilMethods.INSTANCE.getSerialNo(context)+"",LoginDataResponse.getData().getLoginTypeID()+""));

            call.enqueue(new Callback<RechargeReportResponse>() {

                @Override
                public void onResponse(Call<RechargeReportResponse> call, retrofit2.Response<RechargeReportResponse> response) {

                    if (loader!=null && loader.isShowing())
                        loader.dismiss();

                    if (response.body() != null && response.body().getStatuscode() != null) {
                        if (response.body().getStatuscode().equalsIgnoreCase("1")) {
                            if (apiCallBack != null) {
                                apiCallBack.onSucess(response.body());
                            }


                        } else if (response.body().getStatuscode().equalsIgnoreCase("-1")) {
                            if (response.body().getIsVersionValid() != null && response.body().getIsVersionValid().equalsIgnoreCase("false")) {
                                versionDialog(context);
                            } else {
                                UtilMethods.INSTANCE.Error(context, response.body().getMsg() + "");
                            }
                        }

                    }
                }

                @Override
                public void onFailure(Call<RechargeReportResponse> call, Throwable t) {
                    /// Log.e("response", "error ");
                    if (loader!=null && loader.isShowing())
                        loader.dismiss();
                    displayingOnFailuireMessage(context,t,null);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            if (loader!=null && loader.isShowing())
                loader.dismiss();
        }

    }

      /*------- DTh Subscription-----------------*/

    public void GetDthPackage(final Activity context, String oid, CustomLoader loader, final ApiCallBack mApiCallBack) {
        try {
            loader.show();
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<GetDthPackageResponse> call = git.GetDTHPackage(new GetDthPackageRequest(oid,
                    LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getLoginTypeID() + "",
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));

            call.enqueue(new Callback<GetDthPackageResponse>() {

                @Override
                public void onResponse(Call<GetDthPackageResponse> call, retrofit2.Response<GetDthPackageResponse> response) {
                    loader.dismiss();
                    if (response.body() != null && response.body().getStatuscode() == 1) {
                        if (response.body().getDthPackage() != null && response.body().getDthPackage().size() > 0) {
                            if (mApiCallBack != null) {
                                mApiCallBack.onSucess(response.body());
                            }

                        } else {
                            Error(context, "Package Not Found.");
                        }

                    } else {
                        if (response.body() != null && response.body().getMsg() != null) {
                            Error(context, response.body().getMsg() + "");
                        } else {
                            Error(context, context.getResources().getString(R.string.some_thing_error) + "");
                        }
                    }
                }

                @Override
                public void onFailure(Call<GetDthPackageResponse> call, Throwable t) {
                    loader.dismiss();
                    Error(context, t.getMessage() + "");
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            loader.dismiss();
            Error(context, e.getMessage() + "");
        }

    }

    public void GetDthChannel(final Activity context, String pid, String oid, CustomLoader loader, final ApiCallBack mApiCallBack) {
        try {
            loader.show();
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<GetDthPackageResponse> call = git.DTHChannelByPackageID(new GetDthPackageChannelRequest(pid, oid,
                    LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getLoginTypeID() + "",
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));

            call.enqueue(new Callback<GetDthPackageResponse>() {

                @Override
                public void onResponse(Call<GetDthPackageResponse> call, retrofit2.Response<GetDthPackageResponse> response) {
                    loader.dismiss();
                    if (response.body() != null && response.body().getStatuscode() == 1) {
                        if (response.body().getDthChannels() != null && response.body().getDthChannels().size() > 0) {
                            if (mApiCallBack != null) {
                                mApiCallBack.onSucess(response.body());
                            }

                        } else {
                            Error(context, "Channel Not Found.");
                        }

                    } else {
                        if (response.body() != null && response.body().getMsg() != null) {
                            Error(context, response.body().getMsg() + "");
                        } else {
                            Error(context, context.getResources().getString(R.string.some_thing_error) + "");
                        }
                    }
                }

                @Override
                public void onFailure(Call<GetDthPackageResponse> call, Throwable t) {
                    loader.dismiss();
                    Error(context, t.getMessage() + "");
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            loader.dismiss();
            Error(context, e.getMessage() + "");
        }

    }

    public void PincodeArea(final Activity context, String pincode, final CustomLoader loader, ApiCallBack mApiCallBack) {
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            Call<PincodeAreaResponse> call = git.GetPincodeArea(new PincodeAreaRequest(pincode,
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getLoginTypeID(),
                    LoginDataResponse.getData().getUserID(), LoginDataResponse.getData().getSessionID(),
                    LoginDataResponse.getData().getSession())
            );
            call.enqueue(new Callback<PincodeAreaResponse>() {
                @Override
                public void onResponse(Call<PincodeAreaResponse> call, final Response<PincodeAreaResponse> response) {
                    try {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                        if (response.body() != null) {

                            if (response.body().getStatuscode() == 1) {
                                if (response.body().getAreas() != null && response.body().getAreas().size() > 0) {
                                    if (mApiCallBack != null) {
                                        mApiCallBack.onSucess(response.body());
                                    }
                                } else {
                                    Error(context, "Area not available or may be pincode doesn't exist.");
                                }

                            } else {
                                if (!response.body().isVersionValid()) {
                                    versionDialog(context);
                                } else {
                                    Error(context, response.body().getMsg() + "");
                                }
                            }

                        }
                    } catch (Exception e) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                    }

                }

                @Override
                public void onFailure(Call<PincodeAreaResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }
                    try {
                        if (t.getMessage() != null && !t.getMessage().isEmpty()) {

                            NetworkError(context, context.getResources().getString(R.string.err_msg_network_title), context.getResources().getString(R.string.err_msg_network));
                        } else {
                            Error(context, context.getResources().getString(R.string.some_thing_error));
                        }
                    } catch (IllegalStateException ise) {
                        Error(context, ise.getMessage());

                    }


                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DTHSubscription(final Activity context, boolean isReal, int pid, String surname, String gender, int areaID, final String opName, String customer, String address, String pincode,
                                String customerNo, String GeoCode, String securityKey, DthPackage mthPackage, final CustomLoader loader,final  ApiCallBack apiCallBack) {
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            Call<RechargeCResponse> call = git.DTHSubscription(new DTHSubscriptionRequest(
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getLoginTypeID(), pid,
                    customer, customerNo, address, pincode,
                    LoginDataResponse.getData().getUserID(), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession(), GeoCode,
                    securityKey, isReal,surname,gender,areaID)
            );
            call.enqueue(new Callback<RechargeCResponse>() {
                @Override
                public void onResponse(Call<RechargeCResponse> call, final Response<RechargeCResponse> response) {
                    try {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                        if (response.body() != null) {

                            if (response.body().getStatuscode().equalsIgnoreCase("1") || response.body().getStatuscode().equalsIgnoreCase("2")) {
                                if(apiCallBack!=null){
                                    apiCallBack.onSucess(response.body());
                                }
                                Successful(context, response.body().getMsg()+"");

                            } else if (response.body().getStatuscode().equalsIgnoreCase("2")) {
                                Successful(context, response.body().getMsg()+"");
                                if(apiCallBack!=null){
                                    apiCallBack.onSucess(response.body());
                                }
                            } else if (response.body().getStatuscode().equalsIgnoreCase("3")) {
                                Failed(context, response.body().getMsg()+"");
                            } else if (response.body().getStatuscode().equalsIgnoreCase("-1")) {
                                if (response.body().getIsVersionValid() != null && response.body().getIsVersionValid().equalsIgnoreCase("false")) {
                                    versionDialog(context);
                                } else {
                                    Error(context, response.body().getMsg() + "");
                                }
                            }
                            Balancecheck(context, loader, null);
                        }
                    } catch (Exception e) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                    }

                }

                @Override
                public void onFailure(Call<RechargeCResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }
                    try {
                        if (t.getMessage() != null && !t.getMessage().isEmpty()) {

                            if (t.getMessage().contains("No address associated with hostname")) {
                                NetworkError(context, context.getResources().getString(R.string.err_msg_network_title),
                                        context.getResources().getString(R.string.err_msg_network));
                            } else {
                                Processing(context, "Recharge request Accepted");
                            }

                        } else {
                            Processing(context, "Recharge request Accepted");

                        }
                    } catch (IllegalStateException ise) {
                        Error(context, ise.getMessage());

                    }

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DthSubscriptionReport(final Activity context, String opTypeId, String topValue,
                                      int status,int bookingStatus, String fromDate, String toDate, String transactionID, String accountNo, String childMobileNumnber, String isExport, boolean IsRecent, final CustomLoader loader, LoginResponse LoginDataResponse, final ApiCallBack mApiCallBack) {
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<DthSubscriptionReportResponse> call = git.DTHSubscriptionReport(new DthSubscriptionReportRequest(IsRecent, opTypeId, "0",
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context),
                    topValue, status,bookingStatus, fromDate, toDate, transactionID, accountNo, childMobileNumnber, isExport,
                    LoginDataResponse.getData().getUserID()
                    , LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession(),
                    LoginDataResponse.getData().getLoginTypeID()));
            call.enqueue(new Callback<DthSubscriptionReportResponse>() {
                @Override
                public void onResponse(Call<DthSubscriptionReportResponse> call, final Response<DthSubscriptionReportResponse> response) {
                    try {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                        if (response.body() != null && response.body().getStatuscode() != null) {
                            if (response.body().getStatuscode().equalsIgnoreCase("1")) {
                                if (mApiCallBack != null) {
                                    mApiCallBack.onSucess(response.body());
                                }

                            } else if (response.body().getStatuscode().equalsIgnoreCase("-1")) {
                                if (response.body().getIsVersionValid() != null && response.body().getIsVersionValid().equalsIgnoreCase("false")) {
                                    versionDialog(context);
                                } else {
                                    Error(context, response.body().getMsg() + "");
                                }
                            }
                        }
                    } catch (Exception e) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                    }

                }

                @Override
                public void onFailure(Call<DthSubscriptionReportResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }


                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            if (loader != null) {
                if (loader.isShowing())
                    loader.dismiss();
            }
        }
    }
    /*------------------------DTH Subs End----------------------------*/


    public void GeUserCommissionRate(Activity mContext, int uid, CustomLoader loader, LoginResponse LoginDataResponse, ApiCallBack mApiCallBack) {
        try {
            loader.show();
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<BasicResponse> call = git.GeUserCommissionRate(new BasicRequest(uid,
                    LoginDataResponse.getData().getUserID(), LoginDataResponse.getData().getLoginTypeID(),
                    ApplicationConstant.INSTANCE.APP_ID, UtilMethods.INSTANCE.getIMEI(mContext), "", BuildConfig.VERSION_NAME, UtilMethods.INSTANCE.getSerialNo(mContext),
                    LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));

            call.enqueue(new Callback<BasicResponse>() {

                @Override
                public void onResponse(Call<BasicResponse> call, retrofit2.Response<BasicResponse> response) {

                    try {
                        if (loader != null) {
                            if (loader.isShowing()) {
                                loader.dismiss();
                            }
                        }
                        if (response.isSuccessful()) {
                            BasicResponse mBasicResponse = response.body();
                            if (mBasicResponse != null) {
                                if (mBasicResponse.getStatuscode() == 1) {

                                    if (mApiCallBack != null) {
                                        mApiCallBack.onSucess(mBasicResponse);
                                    }
                                } else {

                                    Error(mContext, mBasicResponse.getMsg() + "");
                                }

                            } else {

                                Error(mContext, mContext.getResources().getString(R.string.some_thing_error));
                            }
                        } else {
                            apiErrorHandle(mContext, response.code(), response.message());
                        }
                    } catch (Exception e) {
                        if (loader != null) {
                            if (loader.isShowing()) {
                                loader.dismiss();
                            }
                        }
                        Error(mContext, e.getMessage() + "");
                    }

                }

                @Override
                public void onFailure(Call<BasicResponse> call, Throwable t) {


                    try {
                        if (loader != null) {
                            if (loader.isShowing()) {
                                loader.dismiss();
                            }
                        }
                        apiFailureError(mContext, t);
                    } catch (IllegalStateException ise) {
                        if (loader != null) {
                            if (loader.isShowing()) {
                                loader.dismiss();
                            }
                        }

                        Error(mContext, mContext.getResources().getString(R.string.some_thing_error));
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            if (loader != null) {
                if (loader.isShowing()) {
                    loader.dismiss();
                }
            }
            Error(mContext, mContext.getResources().getString(R.string.some_thing_error));
        }

    }



    public void IncentiveDetail(final Activity context, String opTypeId, final CustomLoader loader, final ApiCallBack mApiCallBack) {
        try {
            String LoginResponse = getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<AppUserListResponse> call = git.IncentiveDetail(new IncentiveDetailRequest(opTypeId,
                    LoginDataResponse.getData().getUserID(), LoginDataResponse.getData().getLoginTypeID(),
                    ApplicationConstant.INSTANCE.APP_ID,
                    getIMEI(context),
                    "", BuildConfig.VERSION_NAME, getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));
            call.enqueue(new Callback<AppUserListResponse>() {
                @Override
                public void onResponse(Call<AppUserListResponse> call, final retrofit2.Response<AppUserListResponse> response) {
                    try {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                        if (response.body() != null) {
                            if (response.body().getStatuscode() == 1) {
                                if (response.body().getIncentiveDetails() != null && response.body().getIncentiveDetails().size() > 0) {
                                    if (mApiCallBack != null) {
                                        mApiCallBack.onSucess(response.body());
                                    }
                                } else {
                                    Error(context, "Data Not Found");
                                }
                            }
                         /*   else if (response.body().getStatuscode() == -1) {
                                if (response.body().isVersionValid() == false) {
                                    versionDialog(context);
                                }
                                else {
                                    Error(context, response.body().getMsg() + "");
                                }
                            }*/
                        } else {
                            Error(context, context.getResources().getString(R.string.some_thing_error));
                        }
                    } catch (Exception e) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                        Error(context, e.getMessage() + "");

                    }

                }

                @Override
                public void onFailure(Call<AppUserListResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }

                    apiFailureError(context,t);

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            if (loader != null) {
                if (loader.isShowing())
                    loader.dismiss();
            }
            Error(context, e.getMessage() + "");
        }
    }

    public void AccStmtAndCollFilterFosClick(final Activity context, String topRows,
                                             String fromDate, String toDate,
                                             String utype,
                                             final CustomLoader loader, LoginResponse mLoginDataResponse, int areaid, final ApiCallBack mApiCallBack) {
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<FosAccStmtAndCollReportResponse> call = git.AccStmtAndColl(new FosAccStmtAndCollReportRequest(ApplicationConstant.INSTANCE.APP_ID, fromDate, "", UtilMethods.INSTANCE.getSerialNo(context), mLoginDataResponse.getData().getSession(), mLoginDataResponse.getData().getSessionID(), toDate, topRows, BuildConfig.VERSION_NAME, utype, areaid, mLoginDataResponse.getData().getUserID(), mLoginDataResponse.getData().getLoginTypeID(), UtilMethods.INSTANCE.getIMEI(context)
            ));
            call.enqueue(new Callback<FosAccStmtAndCollReportResponse>() {

                @Override
                public void onResponse(Call<FosAccStmtAndCollReportResponse> call, final retrofit2.Response<FosAccStmtAndCollReportResponse> response) {

                    try {
                        if (loader != null) {
                            if (loader.isShowing()) {
                                loader.dismiss();
                            }
                        }
                        if (response.isSuccessful()) {


                            if (response.body() != null) {
                                if (response.body().getStatuscode() == 1) {

                                    if (mApiCallBack != null) {
                                        mApiCallBack.onSucess(response.body());
                                    }

                                } else {

                                    if (!response.body().getIsVersionValid()) {
                                        versionDialog(context);
                                    } else {
                                        Error(context, response.body().getMsg() + "");
                                    }
                                }
                            }
                        } else {

                            apiErrorHandle(context, response.code(), response.message());
                        }
                    } catch (Exception e) {
                        if (loader != null) {
                            if (loader.isShowing()) {
                                loader.dismiss();
                            }
                        }
                        Error(context, e.getMessage());
                    }

                }

                @Override
                public void onFailure(Call<FosAccStmtAndCollReportResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing()) {
                            loader.dismiss();
                        }
                    }
                    apiFailureError(context,t);
                }

            });

        } catch (Exception e) {
            Error(context, e.getMessage());
            e.printStackTrace();
        }
    }

    public void GetArealist(final Activity context, final CustomLoader loader, LoginResponse LoginDataResponse, final ApiCallBack mApiCallBack) {
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<AppGetAMResponse> call = git.AppGetAM(new AppGetAMRequest(ApplicationConstant.INSTANCE.APP_ID, UtilMethods.INSTANCE.getIMEI(context),
                    "", UtilMethods.INSTANCE.getSerialNo(context), LoginDataResponse.getData().getSession(),
                    LoginDataResponse.getData().getSessionID(), BuildConfig.VERSION_NAME, LoginDataResponse.getData().getLoginTypeID(), LoginDataResponse.getData().getUserID()
            ));
            call.enqueue(new Callback<AppGetAMResponse>() {
                @Override
                public void onResponse(Call<AppGetAMResponse> call, final retrofit2.Response<AppGetAMResponse> response) {
                    try {

                        if (loader != null) {
                            if (loader.isShowing()) {
                                loader.dismiss();
                            }
                        }
                        if (response.isSuccessful()) {
                            if (response.body() != null) {

                                if (response.body().getStatuscode() == 1) {

                                    UtilMethods.INSTANCE.setAreaListPref(context, new Gson().toJson(response.body()));

                                    if (mApiCallBack != null) {
                                        mApiCallBack.onSucess(response.body());
                                    }


                                } else {
                                    if (!response.body().getIsVersionValid()) {
                                        versionDialog(context);
                                    } else {
                                        Error(context, response.body().getMsg() + "");
                                    }

                                }
                            }
                        } else {

                            apiErrorHandle(context, response.code(), response.message());
                        }
                    } catch (Exception e) {
                        if (loader != null) {
                            if (loader.isShowing()) {
                                loader.dismiss();
                            }
                        }

                    }


                }

                @Override
                public void onFailure(Call<AppGetAMResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing()) {
                            loader.dismiss();
                        }
                    }
                    apiFailureError(context,t);
                }


            });

        } catch (Exception e) {
            e.printStackTrace();
            Error(context, e.getMessage());
            if (loader != null) {
                if (loader.isShowing()) {
                    loader.dismiss();
                }
            }

        }
    }


    public void ASPayCollect(Activity mContext, int uid, String remark, String amount,
                             final String userName, CustomLoader loader, final String collectionMode, String bankUtr, String bankName, LoginResponse mLoginDataResponse, final ApiCallBack mFundTransferCallBAck) {

        try {
            loader.show();
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<AppUserListResponse> call = git.ASPayCollect(new ASPayCollectRequest(ApplicationConstant.INSTANCE.APP_ID, UtilMethods.INSTANCE.getIMEI(mContext), "", UtilMethods.INSTANCE.getSerialNo(mContext), mLoginDataResponse.getData().getSession(), mLoginDataResponse.getData().getSessionID(), BuildConfig.VERSION_NAME
                    , mLoginDataResponse.getData().getLoginTypeID(), mLoginDataResponse.getData().getUserID()
                    , uid, collectionMode, amount, remark, bankName, bankUtr));

            call.enqueue(new Callback<AppUserListResponse>() {

                @Override
                public void onResponse(Call<AppUserListResponse> call, retrofit2.Response<AppUserListResponse> response) {


                    try {
                        if (loader != null) {
                            if (loader.isShowing()) {
                                loader.dismiss();
                            }
                        }


                        if (response.isSuccessful()) {
                            AppUserListResponse data = response.body();
                            if (data != null) {
                                if (data.getStatuscode() == 1) {

                                    if (mFundTransferCallBAck != null) {
                                        mFundTransferCallBAck.onSucess(data);
                                    }
                                    Successful(mContext, data.getMsg().replace("{User}", userName));

                                } else {
                                    Error(mContext, data.getMsg().replace("{User}", userName));
                                }

                            } else {
                                Error(mContext, mContext.getResources().getString(R.string.some_thing_error));
                            }


                        } else {
                            apiErrorHandle(mContext, response.code(), response.message());
                        }

                    } catch (Exception e) {
                        if (loader != null) {
                            if (loader.isShowing()) {
                                loader.dismiss();
                            }
                        }
                        Error(mContext, e.getMessage() + "");
                    }

                }

                @Override
                public void onFailure(Call<AppUserListResponse> call, Throwable t) {

                    try {
                        if (loader != null) {
                            if (loader.isShowing()) {
                                loader.dismiss();
                            }
                        }
                        apiFailureError(mContext, t);
                    } catch (IllegalStateException ise) {
                        if (loader != null) {
                            if (loader.isShowing()) {
                                loader.dismiss();
                            }
                        }
                        Error(mContext, ise.getMessage() + "");
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            if (loader != null) {
                if (loader.isShowing()) {
//                    loader.dismiss();
                }
            }
        }
    }

    public void GetASCollectBank(final Activity context, final CustomLoader loader, LoginResponse LoginDataResponse, final ApiCallBack mApiCallBack) {
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<BankListResponse> call = git.GetASCollectBank(new BalanceRequest(LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getLoginTypeID(),
                    ApplicationConstant.INSTANCE.APP_ID, UtilMethods.INSTANCE.getIMEI(context), "", BuildConfig.VERSION_NAME, UtilMethods.INSTANCE.getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));
            call.enqueue(new Callback<BankListResponse>() {
                @Override
                public void onResponse(Call<BankListResponse> call, final retrofit2.Response<BankListResponse> response) {
                    try {

                        if (loader != null) {
                            if (loader.isShowing()) {
                                loader.dismiss();
                            }
                        }
                        if (response.isSuccessful()) {
                            if (response.body() != null) {

                                if (response.body().getStatuscode().equalsIgnoreCase("1")) {
                                    if (response.body().getBanks() != null && response.body().getBanks().size() > 0) {
                                        UtilMethods.INSTANCE.setFosBankList(context, new Gson().toJson(response.body()));
                                        if (mApiCallBack != null) {
                                            mApiCallBack.onSucess(response.body());
                                        }
                                    } else {
                                        Error(context, "Bank not found");

                                    }

                                } else {
                                    if (response.body().getIsVersionValid()!=null && response.body().getIsVersionValid().equalsIgnoreCase("false")) {
                                        versionDialog(context);
                                    } else {
                                        Error(context, response.body().getMsg() + "");
                                    }


                                }
                            }
                        } else {

                            apiErrorHandle(context, response.code(), response.message());
                        }
                    } catch (Exception e) {
                        if (loader != null) {
                            if (loader.isShowing()) {
                                loader.dismiss();
                            }
                        }

                    }


                }

                @Override
                public void onFailure(Call<BankListResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing()) {
                            loader.dismiss();
                        }
                    }
                    apiFailureError(context,t);

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            Error(context, e.getMessage());
            if (loader != null) {
                if (loader.isShowing()) {
                    loader.dismiss();
                }
            }

        }
    }


    public void fundTransferApi(final Activity context,boolean isMarkCredit, String securityKey, boolean oType, int uid, String remark, int walletType, String amount, final String userName, final AlertDialog alertDialogFundTransfer, final CustomLoader loader, final FundTransferCallBAck mFundTransferCallBAck) {
        try {
            loader.show();
            String LoginResponse = UtilMethods.INSTANCE.getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<AppUserListResponse> call = git.AppFundTransfer(new FundTransferRequest(isMarkCredit,securityKey,oType, uid, remark, walletType, 0, amount, LoginDataResponse.getData().getUserID(), LoginDataResponse.getData().getLoginTypeID(),
                    ApplicationConstant.INSTANCE.APP_ID,
                    UtilMethods.INSTANCE.getIMEI(context),
                    "", BuildConfig.VERSION_NAME, UtilMethods.INSTANCE.getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));
            call.enqueue(new Callback<AppUserListResponse>() {

                @Override
                public void onResponse(Call<AppUserListResponse> call, retrofit2.Response<AppUserListResponse> response) {

                    if (loader.isShowing())
                        loader.dismiss();
                    AppUserListResponse data = response.body();
                    if (data != null) {
                        if (data.getStatuscode() == 1) {
                            if (mFundTransferCallBAck != null) {
                                mFundTransferCallBAck.onSucessFund();
                            }
                            alertDialogFundTransfer.dismiss();
                            UtilMethods.INSTANCE.Successful(context, data.getMsg().replace("{User}", userName));
                        } else if (response.body().getStatuscode() == -1) {

                            UtilMethods.INSTANCE.Error(context, data.getMsg().replace("{User}", userName));
                        } else if (response.body().getStatuscode() == 0) {

                            UtilMethods.INSTANCE.Error(context, data.getMsg().replace("{User}", userName));
                        } else {

                            UtilMethods.INSTANCE.Error(context, data.getMsg().replace("{User}", userName));
                        }

                    } else {

                        UtilMethods.INSTANCE.Error(context, context.getResources().getString(R.string.some_thing_error));
                    }
                }

                @Override
                public void onFailure(Call<AppUserListResponse> call, Throwable t) {
                    Log.e("response", "error ");

                    try {
                        if (loader.isShowing()) {
                            loader.dismiss();
                        }
                        if (t.getMessage() != null && !t.getMessage().isEmpty()) {

                            if (t.getMessage().contains("No address associated with hostname")) {

                                UtilMethods.INSTANCE.Error(context, context.getResources().getString(R.string.err_msg_network));


                            } else {

                                UtilMethods.INSTANCE.Error(context, t.getMessage());


                            }

                        } else {

                            UtilMethods.INSTANCE.Error(context, context.getResources().getString(R.string.some_thing_error));
                        }
                    } catch (IllegalStateException ise) {
                        loader.dismiss();

                        UtilMethods.INSTANCE.Error(context, context.getResources().getString(R.string.some_thing_error));
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();

            UtilMethods.INSTANCE.Error(context, context.getResources().getString(R.string.some_thing_error));
        }

    }

    public interface FundTransferCallBAck {
        void onSucessFund();

        void onSucessEdit();
    }




    public String getAreaListPref(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        return prefs.getString(ApplicationConstant.INSTANCE.areaListPref, "");

    }

    public void setAreaListPref(Context context, String value) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.areaListPref, value);
        editor.commit();

    }

    public void setFosBankList(Activity context, String bankList) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.bankListFOSPref, bankList);
        editor.commit();
    }

    public String getFosBankList(Activity context) {

        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        return prefs.getString(ApplicationConstant.INSTANCE.bankListFOSPref, null);

    }



    public void setAppLogoURl(final Activity context, String appUrl){
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.appLogoUrlPref, appUrl);
        editor.commit();
    }
    public String getAppLogoUrl(Activity activity){
        SharedPreferences prefs = activity.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        return prefs.getString(ApplicationConstant.INSTANCE.appLogoUrlPref,null);
    }

    public void setEmailVerifiedPref(Context context, boolean value) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(ApplicationConstant.INSTANCE.isEmailVerifiedPref, value);
        editor.commit();

    }

    public boolean getEmailVerifiedPref(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        return prefs.getBoolean(ApplicationConstant.INSTANCE.isEmailVerifiedPref, false);

    }

    public void setSocialLinkSavedPref(Context context, boolean value) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(ApplicationConstant.INSTANCE.isSocialLinkSavedPref, value);
        editor.commit();

    }

    public boolean getSocialLinkSavedPref(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        return prefs.getBoolean(ApplicationConstant.INSTANCE.isSocialLinkSavedPref, false);

    }

    public void setSocialorEmailDialogTimePref(Context context, long value) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong(ApplicationConstant.INSTANCE.SocialorEmailDialogTimePref, value);
        editor.commit();

    }

    public long getSocialorEmailDialogTimePref(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        return prefs.getLong(ApplicationConstant.INSTANCE.SocialorEmailDialogTimePref, 0);

    }

    public void CheckFlagsEmail(Activity mContext, CustomLoader loader,
                                LoginResponse LoginDataResponse) {
        try {

            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<BasicResponse> call = git.CheckFlagsEmail(new BasicRequest(LoginDataResponse.getData().getUserID(),
                    LoginDataResponse.getData().getLoginTypeID(),
                    ApplicationConstant.INSTANCE.APP_ID, UtilMethods.INSTANCE.getIMEI(mContext), "", BuildConfig.VERSION_NAME, UtilMethods.INSTANCE.getSerialNo(mContext),
                    LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));

            call.enqueue(new Callback<BasicResponse>() {

                @Override
                public void onResponse(Call<BasicResponse> call, retrofit2.Response<BasicResponse> response) {
                    if (response.isSuccessful()) {
                        try {

                            BasicResponse mBasicResponse = response.body();
                            if (mBasicResponse != null) {
                                if (mBasicResponse.getStatuscode() == 1) {
                                    UtilMethods.INSTANCE.setSocialorEmailDialogTimePref(mContext, System.currentTimeMillis());
                                    if (!mBasicResponse.isEmailVerified() || !mBasicResponse.isSocialAlert()) {
                                        emailVerifySocialLinkDialog(mContext, mBasicResponse.isEmailVerified(), mBasicResponse.isSocialAlert(), loader, LoginDataResponse);
                                    } else {
                                        UtilMethods.INSTANCE.setEmailVerifiedPref(mContext, true);
                                        UtilMethods.INSTANCE.setSocialLinkSavedPref(mContext, true);
                                    }
                                } else {
                                    if (response.body().getVersionValid()) {
                                        if (mBasicResponse.getStatuscode() == 0) {
                                            UtilMethods.INSTANCE.setEmailVerifiedPref(mContext, true);
                                            UtilMethods.INSTANCE.setSocialLinkSavedPref(mContext, true);
                                            UtilMethods.INSTANCE.setSocialorEmailDialogTimePref(mContext, System.currentTimeMillis());
                                        }
                                    }

                                }

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        apiErrorHandle(mContext, response.code(), response.message());
                    }
                }

                @Override
                public void onFailure(Call<BasicResponse> call, Throwable t) {
                    t.printStackTrace();

                }
            });

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public void VerifyEmail(Activity mContext, CustomLoader loader,
                            LoginResponse LoginDataResponse, ApiCallBack mApiCallBack) {
        try {
            if (loader != null) {
                loader.show();
            }
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<BasicResponse> call = git.SendEmailVerification(new BasicRequest(LoginDataResponse.getData().getUserID(),
                    LoginDataResponse.getData().getLoginTypeID(),
                    ApplicationConstant.INSTANCE.APP_ID, UtilMethods.INSTANCE.getIMEI(mContext), "", BuildConfig.VERSION_NAME, UtilMethods.INSTANCE.getSerialNo(mContext),
                    LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));

            call.enqueue(new Callback<BasicResponse>() {

                @Override
                public void onResponse(Call<BasicResponse> call, retrofit2.Response<BasicResponse> response) {

                    try {
                        if (loader != null) {
                            if (loader.isShowing()) {
                                loader.dismiss();
                            }
                        }
                        if (response.isSuccessful()) {
                            BasicResponse mBasicResponse = response.body();
                            if (mBasicResponse != null) {
                                if (mBasicResponse.getStatuscode() == 1) {
                                    UtilMethods.INSTANCE.setSocialorEmailDialogTimePref(mContext, System.currentTimeMillis());
                                    UtilMethods.INSTANCE.setEmailVerifiedPref(mContext, true);
                                    if (mApiCallBack != null) {
                                        mApiCallBack.onSucess(mBasicResponse);
                                    }
                                } else {
                                    if (!response.body().getVersionValid()) {
                                        versionDialog(mContext);
                                    } else {
                                        Error(mContext, mBasicResponse.getMsg() + "");
                                    }

                                }

                            }
                        } else {
                            apiErrorHandle(mContext, response.code(), response.message());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (loader != null) {
                            if (loader.isShowing()) {
                                loader.dismiss();
                            }
                        }
                        Error(mContext, e.getMessage() + "");
                    }


                }

                @Override
                public void onFailure(Call<BasicResponse> call, Throwable t) {
                    t.printStackTrace();

                    try {
                        if (loader != null) {
                            if (loader.isShowing()) {
                                loader.dismiss();
                            }
                        }
                        apiFailureError(mContext, t);
                    } catch (IllegalStateException ise) {
                        if (loader != null) {
                            if (loader.isShowing()) {
                                loader.dismiss();
                            }
                        }

                        Error(mContext, ise.getMessage() + "");
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            if (loader != null) {
                if (loader.isShowing()) {
                    loader.dismiss();
                }
            }
            Error(mContext, e.getMessage() + "");
        }

    }

    public void submitSocialDetails(Activity mContext, String whatsappNum, String telegramNum, String hangoutId, CustomLoader loader, LoginResponse LoginDataResponse, ApiCallBack mApiCallBack) {
        try {
            if (loader != null) {
                loader.show();
            }
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<BasicResponse> call = git.SaveSocialAlertSetting(new SubmitSocialDetailsRequest(whatsappNum, telegramNum, hangoutId,
                    LoginDataResponse.getData().getUserID(),
                    LoginDataResponse.getData().getLoginTypeID(),
                    ApplicationConstant.INSTANCE.APP_ID, UtilMethods.INSTANCE.getIMEI(mContext), "", BuildConfig.VERSION_NAME, UtilMethods.INSTANCE.getSerialNo(mContext),
                    LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));

            call.enqueue(new Callback<BasicResponse>() {

                @Override
                public void onResponse(Call<BasicResponse> call, retrofit2.Response<BasicResponse> response) {


                    try {
                        if (loader != null) {
                            if (loader.isShowing()) {
                                loader.dismiss();
                            }
                        }
                        if (response.isSuccessful()) {
                            BasicResponse mBasicResponse = response.body();
                            if (mBasicResponse != null) {
                                if (mBasicResponse.getStatuscode() == 1) {
                                    UtilMethods.INSTANCE.setSocialorEmailDialogTimePref(mContext, System.currentTimeMillis());
                                    UtilMethods.INSTANCE.setSocialLinkSavedPref(mContext, true);
                                    if (mApiCallBack != null) {
                                        mApiCallBack.onSucess(mBasicResponse);
                                    }
                                } else {
                                    if (!response.body().getVersionValid()) {
                                        versionDialog(mContext);
                                    } else {
                                        Error(mContext, mBasicResponse.getMsg() + "");
                                    }

                                }

                            }
                        } else {
                            apiErrorHandle(mContext, response.code(), response.message());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (loader != null) {
                            if (loader.isShowing()) {
                                loader.dismiss();
                            }
                        }
                        Error(mContext, e.getMessage() + "");
                    }

                }

                @Override
                public void onFailure(Call<BasicResponse> call, Throwable t) {
                    t.printStackTrace();

                    try {
                        if (loader != null) {
                            if (loader.isShowing()) {
                                loader.dismiss();
                            }
                        }
                        apiFailureError(mContext, t);
                    } catch (IllegalStateException ise) {
                        if (loader != null) {
                            if (loader.isShowing()) {
                                loader.dismiss();
                            }
                        }

                        Error(mContext, ise.getMessage() + "");
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            if (loader != null) {
                if (loader.isShowing()) {
                    loader.dismiss();
                }
            }
            Error(mContext, e.getMessage() + "");
        }

    }

    private void emailVerifySocialLinkDialog(Activity context, final boolean isEmailVerified, final boolean isSocialAlert, CustomLoader loader, LoginResponse loginDataResponse) {

        if (socialAlertBottomSheetDialog != null && socialAlertBottomSheetDialog.isShowing()) {
            return;
        }
        socialAlertBottomSheetDialog = new BottomSheetDialog(context);
        socialAlertBottomSheetDialog.setContentView(R.layout.dialog_email_verify_social_link_update);

        socialAlertBottomSheetDialog.setCancelable(false);

        ImageView closeBtn = socialAlertBottomSheetDialog.findViewById(R.id.closeBtn);
        TextView title = socialAlertBottomSheetDialog.findViewById(R.id.title);
        LinearLayout verifyEmailView = socialAlertBottomSheetDialog.findViewById(R.id.verifyEmailView);
        LinearLayout socialView = socialAlertBottomSheetDialog.findViewById(R.id.socialView);
        LinearLayout socialInputView = socialAlertBottomSheetDialog.findViewById(R.id.socialInputView);
        TextView verifyEmailTxt = socialAlertBottomSheetDialog.findViewById(R.id.verifyEmailTxt);
        TextView verifyEmailBtn = socialAlertBottomSheetDialog.findViewById(R.id.verifyEmailBtn);
        View line = socialAlertBottomSheetDialog.findViewById(R.id.line);
        TextView socialSaveTxt = socialAlertBottomSheetDialog.findViewById(R.id.socialSaveTxt);
        TextView whatsappTitle = socialAlertBottomSheetDialog.findViewById(R.id.whatsappTitle);
        EditText whatsappNumberEt = socialAlertBottomSheetDialog.findViewById(R.id.whatsappNumberEt);
        TextView telegramTitle = socialAlertBottomSheetDialog.findViewById(R.id.telegramTitle);
        EditText telegramNumberEt = socialAlertBottomSheetDialog.findViewById(R.id.telegramNumberEt);
        TextView hangoutTitle = socialAlertBottomSheetDialog.findViewById(R.id.hangoutTitle);
        EditText hangoutEt = socialAlertBottomSheetDialog.findViewById(R.id.hangoutEt);
        TextView submitBtn = socialAlertBottomSheetDialog.findViewById(R.id.submitBtn);

        if (!isEmailVerified && isSocialAlert) {
            title.setText("Verify Email Id");
        } else if (isEmailVerified && !isSocialAlert) {
            title.setText("Update Social Link");
        } else {
            title.setText("Verify Email Id And Update Social Link");
        }
        if (isEmailVerified) {
            line.setVisibility(View.GONE);
            verifyEmailView.setVisibility(View.GONE);
        }
        if (isSocialAlert) {
            line.setVisibility(View.GONE);
            socialView.setVisibility(View.GONE);
        } else {
            hangoutEt.setText(loginDataResponse.getData().getEmailID() + "");
            whatsappNumberEt.setText(loginDataResponse.getData().getMobileNo() + "");
        }
        closeBtn.setOnClickListener(v -> socialAlertBottomSheetDialog.dismiss());
        verifyEmailBtn.setOnClickListener(v -> {
            if (UtilMethods.INSTANCE.isNetworkAvialable(context)) {
                VerifyEmail(context, loader, loginDataResponse, object -> {
                    BasicResponse mBasicResponse = (BasicResponse) object;
                    verifyEmailTxt.setText(mBasicResponse.getMsg() + "");
                    verifyEmailTxt.setTextColor(context.getResources().getColor(R.color.green));
                    verifyEmailBtn.setVisibility(View.GONE);

                    if (socialView.getVisibility() == View.GONE || socialInputView.getVisibility() == View.GONE) {
                        socialAlertBottomSheetDialog.setCancelable(true);
                    }
                });
            } else {
                UtilMethods.INSTANCE.NetworkError(context);
            }

        });

        submitBtn.setOnClickListener(v -> {
            if (whatsappNumberEt.getText().toString().trim().isEmpty() || whatsappNumberEt.getText().toString().trim().length() != 10) {
                whatsappNumberEt.setError("Please enter valid Whatsapp Number");
                whatsappNumberEt.requestFocus();
                return;
            } else if (telegramNumberEt.getText().toString().trim().isEmpty() || telegramNumberEt.getText().toString().trim().length() != 10) {
                telegramNumberEt.setError("Please enter valid Telegram Number");
                telegramNumberEt.requestFocus();
                return;
            } else if (hangoutEt.getText().toString().trim().isEmpty() || !hangoutEt.getText().toString().trim().contains("@")
                    || !hangoutEt.getText().toString().trim().contains(".")) {
                hangoutEt.setError("Please enter valid Hangout Email Id");
                hangoutEt.requestFocus();
                return;
            }

            if (UtilMethods.INSTANCE.isNetworkAvialable(context)) {
                submitSocialDetails(context, whatsappNumberEt.getText().toString().trim(), telegramNumberEt.getText().toString().trim(), hangoutEt.getText().toString().trim(), loader, loginDataResponse, object -> {
                    BasicResponse mBasicResponse = (BasicResponse) object;
                    socialSaveTxt.setText(mBasicResponse.getMsg() + "");
                    socialSaveTxt.setTextColor(context.getResources().getColor(R.color.green));
                    socialSaveTxt.setVisibility(View.VISIBLE);
                    socialInputView.setVisibility(View.GONE);

                    if (verifyEmailView.getVisibility() == View.GONE || verifyEmailBtn.getVisibility() == View.GONE) {
                        socialAlertBottomSheetDialog.setCancelable(true);
                    }
                });
            } else {
                UtilMethods.INSTANCE.NetworkError(context);
            }
        });


        socialAlertBottomSheetDialog.show();
    }


    public void GetAccountOpenlist(final Activity context, int opTypeId, final CustomLoader loader,
                                   final ApiCallBack mApiCallBack) {
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);

            String LoginResponse = UtilMethods.INSTANCE.getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);

            Call<AccountOpenListResponse> call = git.GetAccountOpeningList(new AccountOpenListRequest(opTypeId,
                    LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getLoginTypeID() + "",
                    ApplicationConstant.INSTANCE.APP_ID,
                    UtilMethods.INSTANCE.getIMEI(context),
                    "", BuildConfig.VERSION_NAME, UtilMethods.INSTANCE.getSerialNo(context),
                    LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));
            call.enqueue(new Callback<AccountOpenListResponse>() {
                @Override
                public void onResponse(Call<AccountOpenListResponse> call, final retrofit2.Response<AccountOpenListResponse> response) {

                    try {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                        if (response.isSuccessful()) {
                            if (response.body() != null) {

                                if (response.body().getStatuscode() == 1) {
                                    if (response.body().getAccountOpeningDeatils() != null &&
                                            response.body().getAccountOpeningDeatils().size() > 0) {
                                        UtilMethods.INSTANCE.setAccountOpenList(context, new Gson().toJson(response.body()));
                                        if (mApiCallBack != null) {
                                            mApiCallBack.onSucess(response.body());
                                        }
                                    }else {
                                        Error(context, "Record not found");
                                    }
                                } else {
                                    if (!response.body().isVersionValid()) {
                                        versionDialog(context);
                                    } else {
                                        Error(context, response.body().getMsg() + "");
                                    }
                                }
                            }
                        } else {
                            apiErrorHandle(context, response.code(), response.message());
                        }
                    } catch (Exception e) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                    }

                }

                @Override
                public void onFailure(Call<AccountOpenListResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }
                    try {
                        apiFailureError(context, t);
                    } catch (IllegalStateException ise) {
                        Error(context, ise.getMessage());

                    }

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void GetAEPSBanklist(final Activity context, final CustomLoader loader, final ApiCallBack mApiCallBack) {
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            String LoginResponse = UtilMethods.INSTANCE.getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            Call<BankListResponse> call = git.GetAEPSBanks(new BalanceRequest(LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getLoginTypeID() + "",
                    ApplicationConstant.INSTANCE.APP_ID,
                    UtilMethods.INSTANCE.getIMEI(context),
                    "", BuildConfig.VERSION_NAME, UtilMethods.INSTANCE.getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));
            call.enqueue(new Callback<BankListResponse>() {
                @Override
                public void onResponse(Call<BankListResponse> call, final retrofit2.Response<BankListResponse> response) {

                    try {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                        if (response.isSuccessful()) {
                            if (response.body() != null && response.body().getStatuscode() != null) {

                                if (response.body().getStatuscode().equalsIgnoreCase("1")) {
                                    UtilMethods.INSTANCE.setAEPSBankList(context, new Gson().toJson(response.body()));
                                    if (mApiCallBack != null) {
                                        mApiCallBack.onSucess(response.body());
                                    }
                                } else if (response.body().getStatuscode().equalsIgnoreCase("-1")) {
                                    if (response.body().getIsVersionValid() != null && response.body().getIsVersionValid().equalsIgnoreCase("false")) {
                                        versionDialog(context);
                                    } else {
                                        Error(context, response.body().getMsg() + "");
                                    }
                                }
                            }
                        } else {
                            apiErrorHandle(context, response.code(), response.message());
                        }
                    } catch (Exception e) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                    }

                }

                @Override
                public void onFailure(Call<BankListResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }
                    try {
                        apiFailureError(context, t);
                    } catch (IllegalStateException ise) {
                        Error(context, ise.getMessage());

                    }

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DepositNow(final Activity context, String lati, String longi, String reff1, String reff2, String reff3, String otp, String aadhar, String amount, int bankIIn, int interfaceType,
                           final CustomLoader loader, LoginResponse LoginDataResponse, final ApiCallBack mApiCallBack) {
        try {
            loader.show();
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);

            Call<GenerateDepositOTPResponse> call = git.DepositNow(new GenerateDepositOTPRequest(lati, longi, reff1, reff2, reff3, otp, aadhar, amount, interfaceType, bankIIn,
                    LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getLoginTypeID() + "", ApplicationConstant.INSTANCE.APP_ID, UtilMethods.INSTANCE.getIMEI(context), "", BuildConfig.VERSION_NAME, UtilMethods.INSTANCE.getSerialNo(context),
                    LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));
            call.enqueue(new Callback<GenerateDepositOTPResponse>() {
                @Override
                public void onResponse(Call<GenerateDepositOTPResponse> call, final retrofit2.Response<GenerateDepositOTPResponse> response) {

                    try {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                        if (response.isSuccessful()) {
                            if (response.body() != null) {

                                if (response.body().getStatuscode() == 1) {

                                    if (mApiCallBack != null) {
                                        mApiCallBack.onSucess(response.body());
                                    }
                                } else {
                                    if (!response.body().isVersionValid()) {
                                        versionDialog(context);
                                    } else {
                                        Error(context, response.body().getMsg() + "");
                                    }
                                }
                            }
                        } else {
                            apiErrorHandle(context, response.code(), response.message());
                        }
                    } catch (Exception e) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                    }

                }

                @Override
                public void onFailure(Call<GenerateDepositOTPResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }
                    try {
                        apiFailureError(context, t);
                    } catch (IllegalStateException ise) {
                        Error(context, ise.getMessage());

                    }

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void GetWithdrawlAEPS(final Activity context, PidData mPidData, String aadhar, String amount, int bankIIn, int interfaceType, final CustomLoader loader, final ApiCallBack mApiCallBack) {
        try {
            loader.show();
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            String LoginResponse = UtilMethods.INSTANCE.getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            Call<GetAEPSResponse> call = git.AEPSWithdrawal(new GetAepsRequest(mPidData, aadhar, amount, interfaceType, bankIIn, LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getLoginTypeID() + "", ApplicationConstant.INSTANCE.APP_ID,
                    UtilMethods.INSTANCE.getIMEI(context),
                    "", BuildConfig.VERSION_NAME, UtilMethods.INSTANCE.getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));
            call.enqueue(new Callback<GetAEPSResponse>() {
                @Override
                public void onResponse(Call<GetAEPSResponse> call, final retrofit2.Response<GetAEPSResponse> response) {

                    try {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                        if (response.isSuccessful()) {
                            if (response.body() != null) {

                                if (response.body().getStatuscode() == 1) {
                                    if (mApiCallBack != null) {
                                        mApiCallBack.onSucess(response.body());
                                    }
                                } else {
                                    if (!response.body().isVersionValid()) {
                                        versionDialog(context);
                                    } else {
                                        Error(context, response.body().getMsg() + "");
                                    }
                                }
                            }
                        } else {
                            apiErrorHandle(context, response.code(), response.message());
                        }
                    } catch (Exception e) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                    }

                }

                @Override
                public void onFailure(Call<GetAEPSResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }
                    try {
                        apiFailureError(context, t);
                    } catch (IllegalStateException ise) {
                        Error(context, ise.getMessage());

                    }

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void GetBalanceAEPS(final Activity context, PidData mPidData, String aadhar, int bankIIn, int interfaceType, final CustomLoader loader, final ApiCallBack mApiCallBack) {
        try {
            loader.show();
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            String LoginResponse = UtilMethods.INSTANCE.getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            Call<GetAEPSResponse> call = git.GetBalanceAEPS(new GetAepsRequest(mPidData, aadhar, interfaceType, bankIIn, LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getLoginTypeID() + "", ApplicationConstant.INSTANCE.APP_ID,
                    UtilMethods.INSTANCE.getIMEI(context),
                    "", BuildConfig.VERSION_NAME, UtilMethods.INSTANCE.getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));
            call.enqueue(new Callback<GetAEPSResponse>() {
                @Override
                public void onResponse(Call<GetAEPSResponse> call, final retrofit2.Response<GetAEPSResponse> response) {

                    try {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                        if (response.isSuccessful()) {
                            if (response.body() != null) {

                                if (response.body().getStatuscode() == 1) {
                                    if (mApiCallBack != null) {
                                        mApiCallBack.onSucess(response.body());
                                    }
                                } else {
                                    if (!response.body().isVersionValid()) {
                                        versionDialog(context);
                                    } else {
                                        Error(context, response.body().getMsg() + "");
                                    }
                                }
                            }
                        } else {
                            apiErrorHandle(context, response.code(), response.message());
                        }
                    } catch (Exception e) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                    }

                }

                @Override
                public void onFailure(Call<GetAEPSResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }
                    try {
                        apiFailureError(context, t);
                    } catch (IllegalStateException ise) {
                        Error(context, ise.getMessage());

                    }

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void GetMINIStatementAEPS(final Activity context, PidData mPidData, String aadhar, int bankIIn, String bankName, int interfaceType, final CustomLoader loader, final ApiCallBack mApiCallBack) {
        try {
            loader.show();
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            String LoginResponse = UtilMethods.INSTANCE.getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            Call<GetAEPSResponse> call = git.BankMiniStatement(new GetAepsRequest(mPidData, aadhar, interfaceType, bankIIn, bankName, LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getLoginTypeID() + "",
                    ApplicationConstant.INSTANCE.APP_ID,
                    UtilMethods.INSTANCE.getIMEI(context),
                    "", BuildConfig.VERSION_NAME, UtilMethods.INSTANCE.getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));
            call.enqueue(new Callback<GetAEPSResponse>() {
                @Override
                public void onResponse(Call<GetAEPSResponse> call, final retrofit2.Response<GetAEPSResponse> response) {

                    try {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                        if (response.isSuccessful()) {
                            if (response.body() != null) {

                                if (response.body().getStatuscode() == 1) {
                                    if (response.body().getStatements() != null && response.body().getStatements().size() > 0) {
                                        if (mApiCallBack != null) {
                                            mApiCallBack.onSucess(response.body());
                                        }
                                    } else {
                                        Error(context, "Mini Statements not available.");
                                    }
                                } else {
                                    if (!response.body().isVersionValid()) {
                                        versionDialog(context);
                                    } else {
                                        Error(context, response.body().getMsg() + "");
                                    }
                                }
                            }
                        } else {
                            apiErrorHandle(context, response.code(), response.message());
                        }
                    } catch (Exception e) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                    }

                }

                @Override
                public void onFailure(Call<GetAEPSResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }
                    try {
                        apiFailureError(context, t);
                    } catch (IllegalStateException ise) {
                        Error(context, ise.getMessage());

                    }

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void GetAadharPay(final Activity context, String lati, String longi, PidData mPidData, String aadhar, String amount, int bankIIn, int interfaceType, final CustomLoader loader, final ApiCallBack mApiCallBack) {
        try {
            loader.show();
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            String LoginResponse = UtilMethods.INSTANCE.getLoginPref(context);
            LoginResponse LoginDataResponse = new Gson().fromJson(LoginResponse, LoginResponse.class);
            Call<GetAEPSResponse> call = git.Aadharpay(new GetAepsRequest(/*lati, longi, */mPidData, aadhar, amount, interfaceType, bankIIn, LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getLoginTypeID() + "", ApplicationConstant.INSTANCE.APP_ID, UtilMethods.INSTANCE.getIMEI(context), "", BuildConfig.VERSION_NAME, UtilMethods.INSTANCE.getSerialNo(context), LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));
            call.enqueue(new Callback<GetAEPSResponse>() {
                @Override
                public void onResponse(Call<GetAEPSResponse> call, final retrofit2.Response<GetAEPSResponse> response) {

                    try {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                        if (response.isSuccessful()) {
                            if (response.body() != null) {

                                if (response.body().getStatuscode() == 1) {
                                    if (mApiCallBack != null) {
                                        mApiCallBack.onSucess(response.body());
                                    }
                                } else {
                                    if (!response.body().isVersionValid()) {
                                        versionDialog(context);
                                    } else {
                                        Error(context, response.body().getMsg() + "");
                                    }
                                }
                            }
                        } else {
                            apiErrorHandle(context, response.code(), response.message());
                        }
                    } catch (Exception e) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                    }

                }

                @Override
                public void onFailure(Call<GetAEPSResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }
                    try {
                        apiFailureError(context, t);
                    } catch (IllegalStateException ise) {
                        Error(context, ise.getMessage());

                    }

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void GenerateDepositOTP(final Activity context, String lati, String longi, String aadhar, String amount, int bankIIn, int interfaceType, final CustomLoader loader, LoginResponse LoginDataResponse, final ApiCallBack mApiCallBack) {
        try {
            loader.show();
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);

            Call<GenerateDepositOTPResponse> call = git.GenerateDepositOTP(new GenerateDepositOTPRequest(lati, longi, aadhar, amount, interfaceType, bankIIn,
                    LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getLoginTypeID() + "",
                    ApplicationConstant.INSTANCE.APP_ID, UtilMethods.INSTANCE.getIMEI(context), "", BuildConfig.VERSION_NAME, UtilMethods.INSTANCE.getSerialNo(context),
                    LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));
            call.enqueue(new Callback<GenerateDepositOTPResponse>() {
                @Override
                public void onResponse(Call<GenerateDepositOTPResponse> call, final retrofit2.Response<GenerateDepositOTPResponse> response) {

                    try {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                        if (response.isSuccessful()) {
                            if (response.body() != null) {

                                if (response.body().getStatuscode() == 1) {

                                    if (mApiCallBack != null) {
                                        mApiCallBack.onSucess(response.body());
                                    }
                                } else {
                                    if (!response.body().isVersionValid()) {
                                        versionDialog(context);
                                    } else {
                                        Error(context, response.body().getMsg() + "");
                                    }
                                }
                            }
                        } else {
                            apiErrorHandle(context, response.code(), response.message());
                        }
                    } catch (Exception e) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                    }

                }

                @Override
                public void onFailure(Call<GenerateDepositOTPResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }
                    try {
                        apiFailureError(context, t);
                    } catch (IllegalStateException ise) {
                        Error(context, ise.getMessage());

                    }

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void VerifyDepositOTP(final Activity context, String lati, String longi, String reff1, String reff2, String reff3, String otp, String aadhar, String amount, int bankIIn, int interfaceType,
                                 final CustomLoader loader, LoginResponse LoginDataResponse, final ApiCallBack mApiCallBack) {
        try {
            loader.show();
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<GenerateDepositOTPResponse> call = git.VerifyDepositOTP(new GenerateDepositOTPRequest(lati, longi, reff1, reff2, reff3, otp, aadhar, amount, interfaceType, bankIIn,
                    LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getLoginTypeID() + "",
                    ApplicationConstant.INSTANCE.APP_ID, UtilMethods.INSTANCE.getIMEI(context), "", BuildConfig.VERSION_NAME, UtilMethods.INSTANCE.getSerialNo(context),
                    LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession()));
            call.enqueue(new Callback<GenerateDepositOTPResponse>() {
                @Override
                public void onResponse(Call<GenerateDepositOTPResponse> call, final retrofit2.Response<GenerateDepositOTPResponse> response) {

                    try {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                        if (response.isSuccessful()) {
                            if (response.body() != null) {

                                if (response.body().getStatuscode() == 1) {

                                    if (mApiCallBack != null) {
                                        mApiCallBack.onSucess(response.body());
                                    }
                                } else {
                                    if (!response.body().isVersionValid()) {
                                        versionDialog(context);
                                    } else {
                                        Error(context, response.body().getMsg() + "");
                                    }
                                }
                            }
                        } else {
                            apiErrorHandle(context, response.code(), response.message());
                        }
                    } catch (Exception e) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                    }

                }

                @Override
                public void onFailure(Call<GenerateDepositOTPResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }
                    try {
                        apiFailureError(context, t);
                    } catch (IllegalStateException ise) {
                        Error(context, ise.getMessage());

                    }

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setAccountOpenList(Activity context, String data) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.accountOpenListPref, data);
        editor.commit();

    }

    public String getAccountOpenList(Activity context) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        return prefs.getString(ApplicationConstant.INSTANCE.accountOpenListPref, "");
    }

    public void setAEPSBankList(Activity context, String bankList) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.bankAEPSListPref, bankList);
        editor.commit();
    }

    public void AccStmtRport(final Activity context,String mobile, String topRows,
                             String fromDate, String toDate,
                             String utype,
                             final CustomLoader loader, LoginResponse mLoginDataResponse, int areaid, final ApiCallBack mApiCallBack) {
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<FosAccStmtAndCollReportResponse> call = git.GetASSumm(new FosAccStmtAndCollReportRequest(mobile,ApplicationConstant.INSTANCE.APP_ID,
                    fromDate, ""
                    , getSerialNo(context), mLoginDataResponse.getData().getSession(), mLoginDataResponse.getData().getSessionID(), toDate, topRows
                    , BuildConfig.VERSION_NAME, utype, areaid,
                    mLoginDataResponse.getData().getUserID(), mLoginDataResponse.getData().getLoginTypeID(), getIMEI(context)
            ));
            call.enqueue(new Callback<FosAccStmtAndCollReportResponse>() {

                @Override
                public void onResponse(Call<FosAccStmtAndCollReportResponse> call, final retrofit2.Response<FosAccStmtAndCollReportResponse> response) {

                    try {
                        if (loader != null) {
                            if (loader.isShowing()) {
                                loader.dismiss();
                            }
                        }
                        if (response.isSuccessful()) {


                            if (response.body() != null) {
                                if (response.body().getStatuscode() == 1) {
                                    // setAscReportPref(context, new Gson().toJson( response.body() ) );
                                    if (mApiCallBack != null) {
                                        mApiCallBack.onSucess(response.body());
                                    }

                                } else {

                                    if (!response.body().getIsVersionValid()) {
                                        versionDialog(context);
                                    } else {
                                        Error(context, response.body().getMsg() + "");
                                    }
                                }
                            }
                        } else {

                            apiErrorHandle(context, response.code(), response.message());
                        }
                    } catch (Exception e) {
                        if (loader != null) {
                            if (loader.isShowing()) {
                                loader.dismiss();
                            }
                        }
                        Error(context, e.getMessage());
                    }

                }

                @Override
                public void onFailure(Call<FosAccStmtAndCollReportResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing()) {
                            loader.dismiss();
                        }
                    }
                    try {
                        if (t instanceof UnknownHostException || t instanceof IOException) {
                            NetworkError(context);

                        } else if (t instanceof SocketTimeoutException || t instanceof TimeoutException) {
                            ErrorWithTitle(context, "TIME OUT ERROR", t.getMessage() + "");

                        } else {

                            if (t.getMessage() != null && !t.getMessage().isEmpty()) {
                                ErrorWithTitle(context, "FATAL ERROR", t.getMessage() + "");
                            } else {
                                Error(context, context.getResources().getString(R.string.some_thing_error));
                            }
                        }

                    } catch (IllegalStateException ise) {

                        Error(context, ise.getMessage());

                    }
                }

            });

        } catch (Exception e) {
            Error(context, e.getMessage());
            e.printStackTrace();
        }
    }




    public void doUPIPayment(final Activity context, final CustomLoader loader, UPIPaymentReq upiPaymentReq, ApiCallBack apiCallBack) {
        try {

            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<RechargeReportResponse> call = git.doUPIPayment(upiPaymentReq);

            call.enqueue(new Callback<RechargeReportResponse>() {

                @Override
                public void onResponse(Call<RechargeReportResponse> call, retrofit2.Response<RechargeReportResponse> response) {

                    if (loader!=null && loader.isShowing())
                        loader.dismiss();
                    if (response.isSuccessful()) {
                        if (response.body() != null && response.body().getStatuscode() != null) {

                            if (response.body().getStatuscode().equalsIgnoreCase("1") || response.body().getStatuscode().equalsIgnoreCase("2")) {

                                Successful(context,response.body().getMsg());

                                if(apiCallBack!=null)
                                    apiCallBack.onSucess(response.body());

                            } else if (response.body().getStatuscode().equalsIgnoreCase("-1")) {
                                if (response.body().getIsVersionValid() != null && response.body().getIsVersionValid().equalsIgnoreCase("false")) {
                                    versionDialog(context);
                                } else {
                                    Error(context, response.body().getMsg() + "");
                                }
                            } else if (response.body().getStatuscode().equalsIgnoreCase("3")) {
                                Error(context, response.body().getMsg() + "");
                            }

                        }
                    } else {
                        apiErrorHandle(context, response.code(), response.message());
                    }
                }

                @Override
                public void onFailure(Call<RechargeReportResponse> call, Throwable t) {

                    if (loader!=null && loader.isShowing())
                        loader.dismiss();

                    try {
                        apiFailureError(context, t);
                    } catch (IllegalStateException ise) {
                        Error(context, ise.getMessage());

                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            if (loader!=null && loader.isShowing())
                loader.dismiss();
        }

    }

    public void MoveToBankReport(final Activity context, int status, int topRow, int oid,
                                 String fromDate, String toDate, String transactionID, String childMobileNo,
                                 final CustomLoader loader,  final ApiCallBackTwoMethod mApiCallBack) {
        try {
            LoginResponse LoginDataResponse=new Gson().fromJson(UtilMethods.INSTANCE.getLoginPref(context),LoginResponse.class);
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<MoveToBankReportResponse> call = git.MoveToBankReport(new MoveToBankReportRequest(
                    topRow, status, oid, fromDate, toDate, transactionID,
                    childMobileNo,
                    LoginDataResponse.getData().getUserID() + "", LoginDataResponse.getData().getSessionID(), LoginDataResponse.getData().getSession(),
                    ApplicationConstant.INSTANCE.APP_ID, UtilMethods.INSTANCE.getIMEI(context), "", BuildConfig.VERSION_NAME, UtilMethods.INSTANCE.getSerialNo(context),
                    LoginDataResponse.getData().getLoginTypeID()));


            call.enqueue(new Callback<MoveToBankReportResponse>() {

                @Override
                public void onResponse(Call<MoveToBankReportResponse> call, retrofit2.Response<MoveToBankReportResponse> response) {

                    try {
                        if (loader != null) {
                            if (loader.isShowing()) {
                                loader.dismiss();
                            }
                        }
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                if (response.body().getStatuscode()==1) {
                                    if (mApiCallBack != null) {
                                        mApiCallBack.onSucess(response.body());
                                    }
                                } else if (response.body().getStatuscode()==3) {
                                    if (mApiCallBack != null) {
                                        mApiCallBack.onError(ERROR_OTHER);
                                    }
                                    Error(context, response.body().getMsg() + "");
                                } else {
                                    if (mApiCallBack != null) {
                                        mApiCallBack.onError(ERROR_OTHER);
                                    }
                                    if (!response.body().isVersionValid()) {
                                        versionDialog(context);
                                    } else {
                                        Error(context, response.body().getMsg() + "");
                                    }
                                }

                            }
                        } else {
                            if (mApiCallBack != null) {
                                mApiCallBack.onError(ERROR_OTHER);
                            }
                            apiErrorHandle(context, response.code(), response.message());
                        }
                    } catch (Exception e) {
                        if (loader != null) {
                            if (loader.isShowing()) {
                                loader.dismiss();
                            }
                        }
                        if (mApiCallBack != null) {
                            mApiCallBack.onError(ERROR_OTHER);
                        }
                        Error(context, e.getMessage() + "");
                    }


                }

                @Override
                public void onFailure(Call<MoveToBankReportResponse> call, Throwable t) {

                    if (loader != null) {
                        if (loader.isShowing()) {
                            loader.dismiss();
                        }
                    }

                    apiFailureErrorWithCallBack(context,t,mApiCallBack);

                }
            });

        } catch (Exception e) {
            if (mApiCallBack != null) {
                mApiCallBack.onError(ERROR_OTHER);
            }
            if (loader != null) {
                if (loader.isShowing()) {
                    loader.dismiss();
                }
            }
            e.printStackTrace();
            Error(context, e.getMessage());
        }

    }

    /*----------------API End-----------*/

    public void openOtpDepositDialog(final Activity context, String mobileNum, final DialogOTPCallBack mDialogCallBack) {

        if (dialogOTP != null && dialogOTP.isShowing()) {
            return;
        }
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.verifyotp, null);

        TextView numberTv = view.findViewById(R.id.number);
        EditText edMobileOtp = view.findViewById(R.id.ed_mobile_otp);
        final View btLogin = view.findViewById(R.id.okButton);
        final View cancelButton = view.findViewById(R.id.cancelButton);
        final TextView timerTv = view.findViewById(R.id.timer);
        final Button resendBtn = view.findViewById(R.id.resendButton);


        if (mobileNum != null && !mobileNum.isEmpty()) {
            numberTv.setVisibility(View.VISIBLE);
            numberTv.setText(mobileNum.replace(mobileNum.substring(0, 7), "XXXXXXX"));
        }
        dialogOTP = new Dialog(context,R.style.Theme_AppCompat_Dialog_Alert);
        dialogOTP.setCancelable(false);
        dialogOTP.setContentView(view);
        dialogOTP.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogOTP.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        setTimer(timerTv, resendBtn);

        cancelButton.setOnClickListener(v -> dialogOTP.dismiss());


        btLogin.setOnClickListener(v -> {
            if (edMobileOtp.getText().length() != 6) {
                edMobileOtp.setError("Enter a Valid OTP");
                edMobileOtp.requestFocus();
            } else {
                if (mDialogCallBack != null) {
                    mDialogCallBack.onPositiveClick(edMobileOtp, edMobileOtp.getText().toString(), timerTv, resendBtn, dialogOTP);
                }
            }
        });

        resendBtn.setOnClickListener(v -> {

            if (mDialogCallBack != null) {
                mDialogCallBack.onResetCallback(edMobileOtp, edMobileOtp.getText().toString(), timerTv, resendBtn, dialogOTP);
            }

        });

        dialogOTP.setOnDismissListener(dialog1 -> {
            if (countDownTimer != null) {
                countDownTimer.cancel();
                countDownTimer = null;
            }
        });

        dialogOTP.show();
    }


    public interface DialogOTPCallBack {
        void onPositiveClick(EditText edMobileOtp, String otpValue, TextView timerTv, View resendCodeTv, Dialog mDialog);

        void onResetCallback(EditText edMobileOtp, String otpValue, TextView timerTv, View resendCodeTv, Dialog mDialog);
    }

    public void setTimer(final TextView timer, final View resendcode) {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        timer.setText("Resend OTP - 00:00");
        countDownTimer = new CountDownTimer(30000, 1000) { // adjust the milli seconds here

            public void onTick(long millisUntilFinished) {
                timer.setText("Resend OTP - " + String.format("%02d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            public void onFinish() {
                timer.setText("");
                resendcode.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void displayingOnFailuireMessage(Activity context, Throwable t,ApiCallBackTwoMethod mApiCallBack) {
        if (t instanceof UnknownHostException || t instanceof IOException) {
            NetworkError(context);
            if (mApiCallBack != null) {
                mApiCallBack.onError(ERROR_NETWORK+"");
            }
        } else if (t instanceof SocketTimeoutException || t instanceof TimeoutException) {
            ErrorWithTitle(context, "TIME OUT ERROR", t.getMessage() + "");
            if (mApiCallBack != null) {
                mApiCallBack.onError(ERROR_OTHER);
            }
        } else {
            if (mApiCallBack != null) {
                mApiCallBack.onError(ERROR_OTHER);
            }
            if (t.getMessage() != null && !t.getMessage().isEmpty()) {
                ErrorWithTitle(context, "FATAL ERROR", t.getMessage() + "");
            } else {
                Error(context, context.getResources().getString(R.string.some_thing_error));
            }
        }
    }

    public void apiErrorHandle(Activity context, int code, String msg) {
        if (code == 401) {
            ErrorWithTitle(context, "UNAUTHENTICATED " + code, msg + "");
        } else if (code == 404) {
            ErrorWithTitle(context, "API ERROR " + code, msg + "");
        } else if (code >= 400 && code < 500) {
            ErrorWithTitle(context, "CLIENT ERROR " + code, msg + "");
        } else if (code >= 500 && code < 600) {

            ErrorWithTitle(context, "SERVER ERROR " + code, msg + "");
        } else {
            ErrorWithTitle(context, "FATAL/UNKNOWN ERROR " + code, msg + "");
        }
    }

    public void apiFailureError(Activity context, Throwable t) {
        if (t instanceof UnknownHostException || t instanceof IOException) {
            NetworkError(context);
        }
        else if (t instanceof SocketTimeoutException || t instanceof TimeoutException) {
            ErrorWithTitle(context, "TIME OUT ERROR", t.getMessage() + "");
        } else {
            if (t.getMessage() != null && !t.getMessage().isEmpty()) {
                ErrorWithTitle(context, "FATAL ERROR", t.getMessage() + "");
            } else {
                Error(context, context.getResources().getString(R.string.some_thing_error));
            }
        }
    }

    private void apiFailureErrorWithCallBack(Activity context, Throwable t, ApiCallBackTwoMethod mApiCallBack) {
        try {
            if (t instanceof UnknownHostException || t instanceof IOException) {
                UtilMethods.INSTANCE.NetworkError(context);
                if (mApiCallBack != null) {
                    mApiCallBack.onError(ERROR_NETWORK);
                }
            } else if (t instanceof SocketTimeoutException || t instanceof TimeoutException) {
                ErrorWithTitle(context, "TIME OUT ERROR", t.getMessage() + "");
                if (mApiCallBack != null) {
                    mApiCallBack.onError(ERROR_OTHER);
                }
            } else {
                if (mApiCallBack != null) {
                    mApiCallBack.onError(ERROR_OTHER);
                }
                if (t.getMessage() != null && !t.getMessage().isEmpty()) {
                    ErrorWithTitle(context, "FATAL ERROR", t.getMessage() + "");
                } else {
                    Error(context, context.getResources().getString(R.string.some_thing_error));
                }
            }

        }
        catch (IllegalStateException ise) {
            if (mApiCallBack != null) {
                mApiCallBack.onError(ERROR_OTHER);
            }
            Error(context, ise.getMessage());

        }

    }


    public String formatedDate2(String dateStr) {
        if (dateStr != null && !dateStr.isEmpty()) {
            String formateDate = null;
            SimpleDateFormat inputFormat;

            inputFormat = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");


            SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy hh:mm aa");
            try {
                Date date = inputFormat.parse(dateStr);
                formateDate = outputFormat.format(date);
                System.out.println(date);
            } catch (ParseException e) {
                e.printStackTrace();
                return dateStr;
            }

            return formateDate;
        }
        return dateStr;
    }

    public boolean showCallOnBoardingMsgs(Activity context, OnboardingResponse mOnboardingResponse, CustomAlertDialog customPassDialog) {
        if (mOnboardingResponse.isShowMsg() && mOnboardingResponse.getIsDown() && mOnboardingResponse.getMsg() != null) {
            customPassDialog.showMessage(context.getResources().getString(R.string.SERVICEDOWN), mOnboardingResponse.getMsg() + "", R.drawable.servicedown8, 0);
            return false;
        } else if (mOnboardingResponse.isShowMsg() && mOnboardingResponse.getIsWaiting() && mOnboardingResponse.getMsg() != null) {
            customPassDialog.showMessage(context.getResources().getString(R.string.UNDERSCREENING), mOnboardingResponse.getMsg() + "", R.drawable.underscreaning7, 0);
            return false;
        } else if (mOnboardingResponse.isShowMsg() && mOnboardingResponse.getIsUnathorized() && mOnboardingResponse.getMsg() != null) {
            customPassDialog.showMessage(context.getResources().getString(R.string.UNAUTHORISED), mOnboardingResponse.getMsg() + "", R.drawable.unauthorized6, 0);
            return false;
        } else if (mOnboardingResponse.isShowMsg() && mOnboardingResponse.getIsIncomplete() && mOnboardingResponse.getMsg() != null) {
            customPassDialog.showMessage(context.getResources().getString(R.string.INCOMPLETE), mOnboardingResponse.getMsg() + "", R.drawable.incomplete5, 1);
            return false;
        } else if (mOnboardingResponse.isShowMsg() && mOnboardingResponse.getIsRejected() & mOnboardingResponse.getMsg() != null) {
            customPassDialog.showMessage(context.getResources().getString(R.string.REJECT), mOnboardingResponse.getMsg() + "", R.drawable.reject4, 1);
            return false;
        } else if (mOnboardingResponse.isRedirection()) {
            if (mOnboardingResponse.getGiurl() != null && !mOnboardingResponse.getGiurl().isEmpty()) {
                Intent intent = new Intent(context, InsurenceWebview.class);
                intent.putExtra("url", mOnboardingResponse.getGiurl());
                context.startActivity(intent);
                return true;
            } else if (mOnboardingResponse.isShowMsg() && mOnboardingResponse.isRedirection() & mOnboardingResponse.getMsg() != null && !mOnboardingResponse.getMsg().isEmpty()) {
                customPassDialog.showMessage(context.getResources().getString(R.string.Redirection), mOnboardingResponse.getMsg() + "", R.drawable.incomplete5, 1);
                return false;
            }
        }
        return true;
    }

    public void dthSubscriptionConfirmDialog(Activity context,
                                             final boolean isPinPass,
                                              String logo, String number, String operator,
                                              String amount,String packageName, String name, String address, final     DialogCallBack mDialogCallBack) {
        if (alertDialogMobile != null && alertDialogMobile.isShowing()) {
            return;
        }
        androidx.appcompat.app.AlertDialog.Builder dialogBuilder;
        dialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(context);

        alertDialogMobile = dialogBuilder.create();
        alertDialogMobile.setCancelable(true);

        alertDialogMobile.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        LayoutInflater inflater = context.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_dth_subscription_confiorm, null);
        alertDialogMobile.setView(dialogView);
        TextView packageTv= dialogView.findViewById(R.id.packagetv);
        packageTv.setText(packageName+"");
        AppCompatTextView amountTv = dialogView.findViewById(R.id.amount);
        amountTv.setText(context.getResources().getString(R.string.rupiya) + " " + amount);
        final TextInputLayout til_pinPass = dialogView.findViewById(R.id.til_pinPass);
        final EditText pinPassEt = dialogView.findViewById(R.id.pinPassEt);
        if (isPinPass) {
            til_pinPass.setVisibility(View.VISIBLE);
        } else {
            til_pinPass.setVisibility(View.GONE);
        }
        AppCompatTextView operatorTv = dialogView.findViewById(R.id.operator);
        operatorTv.setText(operator);
        AppCompatTextView numberTv = dialogView.findViewById(R.id.number);
        numberTv.setText(number);
        AppCompatTextView nameTv = dialogView.findViewById(R.id.name);
        nameTv.setText(name);
        AppCompatTextView addressTv = dialogView.findViewById(R.id.address);
        addressTv.setText(address);
        AppCompatTextView cancelTv = dialogView.findViewById(R.id.cancel);
        AppCompatTextView okTv = dialogView.findViewById(R.id.ok);
        AppCompatImageView logoIv = dialogView.findViewById(R.id.logo);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(context)
                .load(logo)
                .apply(RequestOptions.circleCropTransform())
                .apply(RequestOptions.placeholderOf(R.mipmap.ic_launcher))
                .into(logoIv);



        okTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPinPass && pinPassEt.getText().toString().isEmpty()) {
                    pinPassEt.setError("Field can't be empty");
                    pinPassEt.requestFocus();
                    return;
                }
                alertDialogMobile.dismiss();
                if (mDialogCallBack != null) {
                    mDialogCallBack.onPositiveClick(pinPassEt.getText().toString());
                }
            }
        });

        cancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogMobile.dismiss();
                if (mDialogCallBack != null) {
                    mDialogCallBack.onCancelClick();
                }
            }
        });


        alertDialogMobile.show();

    }



    public void RealTimeCommission(final Activity context, final CustomLoader loader, final ApiCallBack mApiCallBack) {
        try {
            LoginResponse LoginDataResponse=new Gson().fromJson(UtilMethods.INSTANCE.getLoginPref(context),LoginResponse.class);
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<RSlabRangDetailResponse> call = git.RealTimeCommission(
                    new BasicRequest(LoginDataResponse.getData().getUserID(),
                            LoginDataResponse.getData().getLoginTypeID(),
                            ApplicationConstant.INSTANCE.APP_ID,
                            UtilMethods.INSTANCE.getIMEI(context),
                            "",
                            BuildConfig.VERSION_NAME,
                            UtilMethods.INSTANCE.getSerialNo(context),
                            LoginDataResponse.getData().getSessionID(),
                            LoginDataResponse.getData().getSession()));


            call.enqueue(new Callback<RSlabRangDetailResponse>() {

                @Override
                public void onResponse(Call<RSlabRangDetailResponse> call, retrofit2.Response<RSlabRangDetailResponse> response) {

                    try {
                        if (loader != null) {
                            if (loader.isShowing()) {
                                loader.dismiss();
                            }
                        }
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                if (response.body().getStatuscode()==1) {
                                    if (mApiCallBack != null) {
                                        mApiCallBack.onSucess(response.body());
                                    }
                                } else if (response.body().getStatuscode()==3) {

                                    Error(context, response.body().getMsg() + "");
                                } else {

                                    if (!response.body().isVersionValid()) {
                                        versionDialog(context);
                                    } else {
                                        Error(context, response.body().getMsg() + "");
                                    }
                                }

                            }
                        } else {

                            apiErrorHandle(context, response.code(), response.message());
                        }
                    } catch (Exception e) {
                        if (loader != null) {
                            if (loader.isShowing()) {
                                loader.dismiss();
                            }
                        }
                        Error(context, e.getMessage() + "");
                    }


                }

                @Override
                public void onFailure(Call<RSlabRangDetailResponse> call, Throwable t) {

                    if (loader != null) {
                        if (loader.isShowing()) {
                            loader.dismiss();
                        }
                    }

                    apiFailureError(context,t);


                }
            });

        } catch (Exception e) {
            if (loader != null) {
                if (loader.isShowing()) {
                    loader.dismiss();
                }
            }
            e.printStackTrace();
            Error(context, e.getMessage());
        }

    }

    //Account Settlements---

    public void SettlementAccountBankApi(final Activity mActivity,final  LoginResponse mLoginDataResponse,final CustomLoader loader,final ApiResponseCallBack mResponseCallBack){
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<SettlementAccountResponse> call = git.GetSettlementAccount(new BasicRequest(
                    mLoginDataResponse.getData().getUserID() + "",
                    mLoginDataResponse.getData().getLoginTypeID(),
                    ApplicationConstant.INSTANCE.APP_ID,
                    UtilMethods.INSTANCE.getDeviceId(mActivity),
                    UtilMethods.INSTANCE.getFCMRegKey(mActivity),
                    BuildConfig.VERSION_NAME,
                    UtilMethods.INSTANCE.getSerialNo(mActivity),
                    mLoginDataResponse.getData().getSessionID(),
                    mLoginDataResponse.getData().getSession()));
            call.enqueue(new Callback<SettlementAccountResponse>() {
                @Override
                public void onResponse(Call<SettlementAccountResponse> call, final retrofit2.Response<SettlementAccountResponse> response) {

                    try {

                        if (loader != null) {
                            if (loader.isShowing()) {
                                loader.dismiss();
                            }
                        }
                        if (response.isSuccessful()) {
                            if (response.body() != null) {

                                if (response.body().getStatuscode() == 1) {
                                    if (response.body().getDataList() != null && response.body().getDataList().size() > 0) {

                                        if(mResponseCallBack!=null)
                                            mResponseCallBack.onSuccess(response.body());

                                    } else {
                                        UtilMethods.INSTANCE.Error(mActivity, "Account not found");
                                    }
                                } else {
                                    if (!response.body().isVersionValid()) {
                                        UtilMethods.INSTANCE.versionDialog(mActivity);
                                    } else {
                                        UtilMethods.INSTANCE.Error(mActivity, response.body().getMsg() + "");
                                    }
                                    if(mResponseCallBack!=null)
                                        mResponseCallBack.onError(response.code());
                                }
                            }
                        } else {
                            apiErrorHandle(mActivity, response.code(), response.message());
                            if(mResponseCallBack!=null)
                                mResponseCallBack.onError(response.code());
                        }
                    } catch (Exception e) {
                        if (loader != null) {
                            if (loader.isShowing()) {
                                loader.dismiss();
                            }
                            UtilMethods.INSTANCE. Error(mActivity, e.getMessage());
                        }

                    }


                }

                @Override
                public void onFailure(Call<SettlementAccountResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing()) {
                            loader.dismiss();
                        }
                    }
                    apiFailureError(mActivity,t);
                    if(mResponseCallBack!=null)
                        mResponseCallBack.onError(ERROR_OTHER);
                }
            });

        }
        catch (Exception e) {
            e.printStackTrace();
            if (loader != null) {
                if (loader.isShowing()) {
                    loader.dismiss();
                }
            }
            UtilMethods.INSTANCE. Error(mActivity, e.getMessage());

        }
    }

    public void UpdateSettlementAccountApi(final Activity mActivity,final  LoginResponse mLoginDataResponse,String AccountName,String AccountNumber,String selectedBank,String IFSC,int bankId,int updatedId,final CustomLoader loader,final ApiResponseCallBack mResponseCallBack){
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<BasicResponse> call = git.UpdateSettlementAccount(new UpdateSettlementAccountRequest(
                    mLoginDataResponse.getData().getUserID(),
                    mLoginDataResponse.getData().getLoginTypeID(),
                    ApplicationConstant.INSTANCE.APP_ID,
                    UtilMethods.INSTANCE.getDeviceId(mActivity),
                    UtilMethods.INSTANCE.getFCMRegKey(mActivity),
                    BuildConfig.VERSION_NAME,
                    UtilMethods.INSTANCE.getSerialNo(mActivity),
                    mLoginDataResponse.getData().getSessionID(),
                    mLoginDataResponse.getData().getSession(),
                    AccountName,
                    AccountNumber,
                    bankId,
                    selectedBank,
                    updatedId,
                    IFSC));
            call.enqueue(new Callback<BasicResponse>() {
                @Override
                public void onResponse(Call<BasicResponse> call, final retrofit2.Response<BasicResponse> response) {

                    try {

                        if (loader != null) {
                            if (loader.isShowing()) {
                                loader.dismiss();
                            }
                        }
                        if (response.isSuccessful()) {
                            if (response.body() != null ) {

                                if (response.body().getStatuscode() == 1) {
                                    if(mResponseCallBack!=null)
                                        mResponseCallBack.onSuccess(response.body());

                                } else {
                                    if (!response.body().isVersionValid()) {
                                        UtilMethods.INSTANCE.versionDialog(mActivity);
                                    } else {
                                        UtilMethods.INSTANCE.Error(mActivity, response.body().getMsg() + "");
                                    }
                                    if(mResponseCallBack!=null)
                                        mResponseCallBack.onError(response.code());
                                }
                            }
                        } else {
                            apiErrorHandle(mActivity, response.code(), response.message());
                            if(mResponseCallBack!=null)
                                mResponseCallBack.onError(response.code());
                        }
                    } catch (Exception e) {
                        if (loader != null) {
                            if (loader.isShowing()) {
                                loader.dismiss();
                            }
                            UtilMethods.INSTANCE. Error(mActivity, e.getMessage());
                        }

                    }


                }

                @Override
                public void onFailure(Call<BasicResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing()) {
                            loader.dismiss();
                        }
                    }
                    apiFailureError(mActivity,t);
                    if(mResponseCallBack!=null)
                        mResponseCallBack.onError(ERROR_OTHER);
                }
            });

        }
        catch (Exception e) {
            e.printStackTrace();
            if (loader != null) {
                if (loader.isShowing()) {
                    loader.dismiss();
                }
            }
            UtilMethods.INSTANCE. Error(mActivity, e.getMessage());

        }
    }

    public void VerifySettlementAccountApi(final Activity mActivity,final  LoginResponse mLoginDataResponse,int updatedId,final CustomLoader loader,final ApiResponseCallBack mResponseCallBack){
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<BasicResponse> call = git.VerifySettlementAccountOfUser(new UpdateSettlementAccountRequest(
                    mLoginDataResponse.getData().getUserID(),
                    mLoginDataResponse.getData().getLoginTypeID(),
                    ApplicationConstant.INSTANCE.APP_ID,
                    UtilMethods.INSTANCE.getDeviceId(mActivity),
                    UtilMethods.INSTANCE.getFCMRegKey(mActivity),
                    BuildConfig.VERSION_NAME,
                    UtilMethods.INSTANCE.getSerialNo(mActivity),
                    mLoginDataResponse.getData().getSessionID(),
                    mLoginDataResponse.getData().getSession(),
                    updatedId));
            call.enqueue(new Callback<BasicResponse>() {
                @Override
                public void onResponse(Call<BasicResponse> call, final retrofit2.Response<BasicResponse> response) {

                    try {

                        if (loader != null) {
                            if (loader.isShowing()) {
                                loader.dismiss();
                            }
                        }
                        if (response.isSuccessful()) {
                            if (response.body() != null ) {

                                if (response.body().getStatuscode() == 1) {
                                    if(mResponseCallBack!=null)
                                        mResponseCallBack.onSuccess(response.body());

                                } else {
                                    if (!response.body().isVersionValid()) {
                                        UtilMethods.INSTANCE.versionDialog(mActivity);
                                    } else {
                                        UtilMethods.INSTANCE.Error(mActivity, response.body().getMsg() + "");
                                    }
                                    if(mResponseCallBack!=null)
                                        mResponseCallBack.onError(response.code());
                                }
                            }
                        } else {
                            apiErrorHandle(mActivity, response.code(), response.message());
                            if(mResponseCallBack!=null)
                                mResponseCallBack.onError(response.code());
                        }
                    } catch (Exception e) {
                        if (loader != null) {
                            if (loader.isShowing()) {
                                loader.dismiss();
                            }
                            UtilMethods.INSTANCE. Error(mActivity, e.getMessage());
                        }

                    }


                }

                @Override
                public void onFailure(Call<BasicResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing()) {
                            loader.dismiss();
                        }
                    }
                    apiFailureError(mActivity,t);
                    if(mResponseCallBack!=null)
                        mResponseCallBack.onError(ERROR_OTHER);
                }
            });

        }
        catch (Exception e) {
            e.printStackTrace();
            if (loader != null) {
                if (loader.isShowing()) {
                    loader.dismiss();
                }
            }
            UtilMethods.INSTANCE. Error(mActivity, e.getMessage());

        }
    }

    public void UpdateUTRSettlementAccountApi(final Activity mActivity,final  LoginResponse mLoginDataResponse,int updatedId,String utrTxt,final CustomLoader loader,final ApiResponseCallBack mResponseCallBack){
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<BasicResponse> call = git.UpdateUTRByUser(new UpdateSettlementAccountRequest(
                    mLoginDataResponse.getData().getUserID(),
                    mLoginDataResponse.getData().getLoginTypeID(),
                    ApplicationConstant.INSTANCE.APP_ID,
                    UtilMethods.INSTANCE.getDeviceId(mActivity),
                    UtilMethods.INSTANCE.getFCMRegKey(mActivity),
                    BuildConfig.VERSION_NAME,
                    UtilMethods.INSTANCE.getSerialNo(mActivity),
                    mLoginDataResponse.getData().getSessionID(),
                    mLoginDataResponse.getData().getSession(),
                    updatedId,
                    utrTxt));
            call.enqueue(new Callback<BasicResponse>() {
                @Override
                public void onResponse(Call<BasicResponse> call, final retrofit2.Response<BasicResponse> response) {

                    try {

                        if (loader != null) {
                            if (loader.isShowing()) {
                                loader.dismiss();
                            }
                        }
                        if (response.isSuccessful()) {
                            if (response.body() != null ) {

                                if (response.body().getStatuscode() == 1) {
                                    if(mResponseCallBack!=null)
                                        mResponseCallBack.onSuccess(response.body());

                                } else {
                                    if (!response.body().isVersionValid()) {
                                        UtilMethods.INSTANCE.versionDialog(mActivity);
                                    } else {
                                        UtilMethods.INSTANCE.Error(mActivity, response.body().getMsg() + "");
                                    }
                                    if(mResponseCallBack!=null)
                                        mResponseCallBack.onError(response.code());
                                }
                            }
                        } else {
                            apiErrorHandle(mActivity, response.code(), response.message());
                            if(mResponseCallBack!=null)
                                mResponseCallBack.onError(response.code());
                        }
                    } catch (Exception e) {
                        if (loader != null) {
                            if (loader.isShowing()) {
                                loader.dismiss();
                            }
                            UtilMethods.INSTANCE. Error(mActivity, e.getMessage());
                        }

                    }


                }

                @Override
                public void onFailure(Call<BasicResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing()) {
                            loader.dismiss();
                        }
                    }
                    apiFailureError(mActivity,t);
                    if(mResponseCallBack!=null)
                        mResponseCallBack.onError(ERROR_OTHER);
                }
            });

        }
        catch (Exception e) {
            e.printStackTrace();
            if (loader != null) {
                if (loader.isShowing()) {
                    loader.dismiss();
                }
            }
            UtilMethods.INSTANCE. Error(mActivity, e.getMessage());

        }
    }

    public void SetDefaultSettlementAccountApi(final Activity mActivity,final  LoginResponse mLoginDataResponse,int updatedId,final CustomLoader loader,final ApiResponseCallBack mResponseCallBack){
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<BasicResponse> call = git.ToggleDefaultSettlementAcount(new UpdateSettlementAccountRequest(
                    mLoginDataResponse.getData().getUserID(),
                    mLoginDataResponse.getData().getLoginTypeID(),
                    ApplicationConstant.INSTANCE.APP_ID,
                    UtilMethods.INSTANCE.getDeviceId(mActivity),
                    UtilMethods.INSTANCE.getFCMRegKey(mActivity),
                    BuildConfig.VERSION_NAME,
                    UtilMethods.INSTANCE.getSerialNo(mActivity),
                    mLoginDataResponse.getData().getSessionID(),
                    mLoginDataResponse.getData().getSession(),
                    updatedId));
            call.enqueue(new Callback<BasicResponse>() {
                @Override
                public void onResponse(Call<BasicResponse> call, final retrofit2.Response<BasicResponse> response) {

                    try {

                        if (loader != null) {
                            if (loader.isShowing()) {
                                loader.dismiss();
                            }
                        }
                        if (response.isSuccessful()) {
                            if (response.body() != null ) {

                                if (response.body().getStatuscode() == 1) {
                                    if(mResponseCallBack!=null)
                                        mResponseCallBack.onSuccess(response.body());

                                } else {
                                    if (!response.body().isVersionValid()) {
                                        UtilMethods.INSTANCE.versionDialog(mActivity);
                                    } else {
                                        UtilMethods.INSTANCE.Error(mActivity, response.body().getMsg() + "");
                                    }
                                    if(mResponseCallBack!=null)
                                        mResponseCallBack.onError(response.code());
                                }
                            }
                        } else {
                            apiErrorHandle(mActivity, response.code(), response.message());
                            if(mResponseCallBack!=null)
                                mResponseCallBack.onError(response.code());
                        }
                    } catch (Exception e) {
                        if (loader != null) {
                            if (loader.isShowing()) {
                                loader.dismiss();
                            }
                            UtilMethods.INSTANCE. Error(mActivity, e.getMessage());
                        }

                    }


                }

                @Override
                public void onFailure(Call<BasicResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing()) {
                            loader.dismiss();
                        }
                    }
                    apiFailureError(mActivity,t);
                    if(mResponseCallBack!=null)
                        mResponseCallBack.onError(ERROR_OTHER);
                }
            });

        }
        catch (Exception e) {
            e.printStackTrace();
            if (loader != null) {
                if (loader.isShowing()) {
                    loader.dismiss();
                }
            }
            UtilMethods.INSTANCE. Error(mActivity, e.getMessage());

        }
    }

    public void DeleteSettlementAccountApi(final Activity mActivity,final  LoginResponse mLoginDataResponse,int updatedId,final CustomLoader loader,final ApiResponseCallBack mResponseCallBack){
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<BasicResponse> call = git.DeleteSettlementAcount(new UpdateSettlementAccountRequest(
                    mLoginDataResponse.getData().getUserID(),
                    mLoginDataResponse.getData().getLoginTypeID(),
                    ApplicationConstant.INSTANCE.APP_ID,
                    UtilMethods.INSTANCE.getDeviceId(mActivity),
                    UtilMethods.INSTANCE.getFCMRegKey(mActivity),
                    BuildConfig.VERSION_NAME,
                    UtilMethods.INSTANCE.getSerialNo(mActivity),
                    mLoginDataResponse.getData().getSessionID(),
                    mLoginDataResponse.getData().getSession(),
                    updatedId));
            call.enqueue(new Callback<BasicResponse>() {
                @Override
                public void onResponse(Call<BasicResponse> call, final retrofit2.Response<BasicResponse> response) {

                    try {

                        if (loader != null) {
                            if (loader.isShowing()) {
                                loader.dismiss();
                            }
                        }
                        if (response.isSuccessful()) {
                            if (response.body() != null ) {

                                if (response.body().getStatuscode() == 1) {
                                    if(mResponseCallBack!=null)
                                        mResponseCallBack.onSuccess(response.body());

                                } else {
                                    if (!response.body().isVersionValid()) {
                                        UtilMethods.INSTANCE.versionDialog(mActivity);
                                    } else {
                                        UtilMethods.INSTANCE.Error(mActivity, response.body().getMsg() + "");
                                    }
                                    if(mResponseCallBack!=null)
                                        mResponseCallBack.onError(response.code());
                                }
                            }
                        } else {
                            apiErrorHandle(mActivity, response.code(), response.message());
                            if(mResponseCallBack!=null)
                                mResponseCallBack.onError(response.code());
                        }
                    } catch (Exception e) {
                        if (loader != null) {
                            if (loader.isShowing()) {
                                loader.dismiss();
                            }
                            UtilMethods.INSTANCE. Error(mActivity, e.getMessage());
                        }

                    }


                }

                @Override
                public void onFailure(Call<BasicResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing()) {
                            loader.dismiss();
                        }
                    }
                    apiFailureError(mActivity,t);
                    if(mResponseCallBack!=null)
                        mResponseCallBack.onError(ERROR_OTHER);
                }
            });

        }
        catch (Exception e) {
            e.printStackTrace();
            if (loader != null) {
                if (loader.isShowing()) {
                    loader.dismiss();
                }
            }
            UtilMethods.INSTANCE. Error(mActivity, e.getMessage());

        }
    }

    public String getTransactionRemark(Context mContext,int oid){
        String accountName="";
        if(UtilMethods.INSTANCE.getNumberList(mContext)!=null){
            NumberListResponse numberListResponse= new Gson().fromJson(UtilMethods.INSTANCE.getNumberList(mContext), NumberListResponse.class);
            if(numberListResponse!=null && numberListResponse.getData().getOperators()!=null){
                for (OperatorList operatorList: numberListResponse.getData().getOperators()){
                    if(operatorList.getOid()==oid){
                        accountName=operatorList.getAccountName();
                        break;
                    }
                }
            }
        }
        return accountName;
    }

    public void setAppLogoIconUI(Activity activity, ImageView logoIv) {
        RequestOptions requestOptions=new RequestOptions();
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        requestOptions.skipMemoryCache(true);
        requestOptions.centerInside();
        requestOptions.placeholder(R.drawable.rnd_logo);
        requestOptions.error(R.drawable.rnd_logo);

        if (UtilMethods.INSTANCE.getAppLogoUrl(activity) != null && !UtilMethods.INSTANCE.getAppLogoUrl(activity).isEmpty()) {
            Glide.with(activity).
                    load(UtilMethods.INSTANCE.getAppLogoUrl(activity))
                    .apply(requestOptions)
                    .into(logoIv);
        } else {
            int wid = UtilMethods.INSTANCE.getWIDPref(activity);
            if (wid > 0) {
                Glide.with(activity)
                        .load(ApplicationConstant.INSTANCE.baseIconUrl + wid + "/logo.png")
                        .apply(requestOptions)
                        .into(logoIv);
            }
        }


    }

    public Bitmap setAppLogoAsBitMapIconUI(Activity activity) {
        requestOptions=new RequestOptions();
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        requestOptions.skipMemoryCache(true);
        requestOptions.centerInside();
        requestOptions.placeholder(R.drawable.rnd_placeholder);
        requestOptions.error(R.drawable.rnd_placeholder);

        Glide.with(activity).asBitmap().load(UtilMethods.INSTANCE.getAppLogoUrl(activity)).into(new CustomTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {

            }
            @Override
            public void onLoadCleared(Drawable placeholder) {
            }
        });
        return  null;
    }

    public String formatedShareContent(String value) {

        if(value.contains("*")) {
            Pattern p = Pattern.compile("\\*([^\\*]*)\\*");
            Matcher m = p.matcher(value);
            while (m.find()) {
                value = value.replace("*" + m.group(1) + "*", "<b>" + m.group(1) + "</b>");

            }
        }
        if(value.contains("_")) {
            Pattern p = Pattern.compile("\\_([^\\_]*)\\_");
            Matcher m = p.matcher(value);
            while (m.find()) {
                value = value.replace("_" + m.group(1) + "_", "<i>" + m.group(1) + "</i>");

            }
        }

        if(value.contains("~")) {
            Pattern p = Pattern.compile("\\~([^\\~]*)\\~");
            Matcher m = p.matcher(value);
            while (m.find()) {
                value = value.replace("~" + m.group(1) + "~", "<s>" + m.group(1) + "</s>");

            }
        }
        if(value.contains("```")) {
            Pattern p = Pattern.compile("\\```([^\\```]*)\\```");
            Matcher m = p.matcher(value);
            while (m.find()) {
                value = value.replace("```" + m.group(1) + "```", "<tt>" + m.group(1) + "</tt>");

            }
        }
        return value;
    }

    public interface ApiResponseCallBack {
        void onSuccess(Object object);

        void onError(int error);
    }

}
