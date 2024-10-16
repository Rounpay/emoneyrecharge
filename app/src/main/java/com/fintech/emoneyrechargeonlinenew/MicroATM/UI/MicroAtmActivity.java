package com.fintech.emoneyrechargeonlinenew.MicroATM.UI;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.ImageViewCompat;
import androidx.core.widget.TextViewCompat;

import com.fingpay.microatmsdk.HistoryScreen;
import com.fingpay.microatmsdk.MicroAtmLoginScreen;
import com.fingpay.microatmsdk.utils.Constants;
import com.fintech.emoneyrechargeonlinenew.AEPS.FingPay.UI.EKYCProcessActivity;
import com.fintech.emoneyrechargeonlinenew.Auth.dto.LoginResponse;
import com.fintech.emoneyrechargeonlinenew.BuildConfig;
import com.fintech.emoneyrechargeonlinenew.Fragments.SdkDetail;
import com.fintech.emoneyrechargeonlinenew.MicroATM.dto.InitiateMiniATMReq;
import com.fintech.emoneyrechargeonlinenew.MicroATM.dto.InitiateMiniBankATMRes;
import com.fintech.emoneyrechargeonlinenew.MicroATM.dto.UpdateMiniBankStatusReq;
import com.fintech.emoneyrechargeonlinenew.R;
import com.fintech.emoneyrechargeonlinenew.Util.ApiClient;
import com.fintech.emoneyrechargeonlinenew.Util.ApplicationConstant;
import com.fintech.emoneyrechargeonlinenew.Util.DropdownDialog.DropDownDialog;
import com.fintech.emoneyrechargeonlinenew.Util.EndPointInterface;
import com.fintech.emoneyrechargeonlinenew.Util.GetLocation;
import com.fintech.emoneyrechargeonlinenew.Util.UtilMethods;
import com.fintech.emoneyrechargeonlinenew.usefull.CustomLoader;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.roundpay.emoneylib.Utils.Utility;

import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;


public class MicroAtmActivity extends AppCompatActivity {

    private CustomLoader loader;
    private EditText amountEt, remarksEt;
    TextView btnTxt;
    private View fingPayBtn, historyBtn, amountView, remarkView;
    View txnTypeChooserView;

    private static final int CODE = 123;
    TextView transactionType;
    public String superMerchentId = "266";
    private DropDownDialog mDropDownDialog;
    private String merchantId, password, mobile;
    private String intentSdkType, intentOid;
    private LoginResponse mLoginDataResponse;
    private SdkDetail intentSdkDetail;
    private GetLocation mGetLocation;
    private String tid;

    private String deviceId, deviceSerialNum;
    ArrayList<String> arrayListType = new ArrayList<>();
    int selectedTypePos = 0;
    private BottomSheetDialog mBottomSheetDialog;
    private String btnTextValue = "Continue to cash withdrawal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_micro_atm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Mini ATM");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_icon);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        mDropDownDialog = new DropDownDialog(this);
        intentSdkType = getIntent().getStringExtra("SDKType");
        intentOid = getIntent().getStringExtra("OID");
        intentSdkDetail = (SdkDetail) getIntent().getSerializableExtra("SDKDetails");
        if (intentSdkDetail != null) {
            merchantId = intentSdkDetail.getApiOutletID();
            password = intentSdkDetail.getApiOutletPassword();
            mobile = intentSdkDetail.getApiOutletMob();
            superMerchentId = intentSdkDetail.getApiPartnerID();
        }

        mLoginDataResponse = new Gson().fromJson(UtilMethods.INSTANCE.getLoginPref(this), LoginResponse.class);
        deviceId = UtilMethods.INSTANCE.getDeviceId(this);
        deviceSerialNum =UtilMethods.INSTANCE.getSerialNo(this);
        loader = new CustomLoader(this, android.R.style.Theme_Translucent_NoTitleBar);
        amountEt = findViewById(R.id.et_amount);
        transactionType = findViewById(R.id.transactionType);
        remarksEt = findViewById(R.id.et_remarks);
        txnTypeChooserView = findViewById(R.id.txnTypeChooserView);
        amountView = findViewById(R.id.amountView);
        remarkView = findViewById(R.id.remarkView);
        btnTxt = findViewById(R.id.btnTxt);
        fingPayBtn = findViewById(R.id.btn_fingpay);
        fingPayBtn.setOnClickListener(v -> launch());

        historyBtn = findViewById(R.id.btn_history);
        historyBtn.setOnClickListener(v -> history());

        mGetLocation = new GetLocation(this,loader);
        if (UtilMethods.INSTANCE.getLattitude == 0 || UtilMethods.INSTANCE.getLongitude == 0) {
            mGetLocation.startLocationUpdatesIfSettingEnable((lattitude, longitude) -> {
                UtilMethods.INSTANCE.getLattitude = lattitude;
                UtilMethods.INSTANCE.getLongitude = longitude;
            });
        }

        arrayListType.add("Cash Withdrawal");
        arrayListType.add("Cash Deposit");
        arrayListType.add("Balance Enquiry");
        arrayListType.add("Mini Statement");
       /* arrayListType.add("Card Activation");
        arrayListType.add("Reset Pin");
        arrayListType.add("Change Pin");*/


        amountEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    if ((selectedTypePos == 0 || selectedTypePos == 1)) {

                        btnTxt.setText(btnTextValue + " of " + Utility.INSTANCE.formatedAmountWithRupees(s.toString()));
                    } else {
                        btnTxt.setText(btnTextValue);
                    }
                } else {
                    btnTxt.setText(btnTextValue);
                }


            }
        });

        txnTypeChooserView.setOnClickListener(v ->
                mDropDownDialog.showDropDownPopup(v, selectedTypePos, arrayListType, (clickPosition, value, object) ->

                        {

                            if (selectedTypePos != clickPosition) {
                                transactionType.setText(value + "");
                                selectedTypePos = clickPosition;
                                btnTextValue = "Continue to " + value.toLowerCase();

                                if (selectedTypePos == 0 || selectedTypePos == 1) {
                                    String amt = "";
                                    if (amountEt.getText().length() > 0) {
                                        amt = " of " + Utility.INSTANCE.formatedAmountWithRupees(amountEt.getText().toString());
                                    }
                                    btnTxt.setText(btnTextValue + amt);
                                } else {
                                    btnTxt.setText(btnTextValue);
                                }


                                if (selectedTypePos == 0 || selectedTypePos == 1) {
                                    amountView.setVisibility(View.VISIBLE);
                                    remarkView.setVisibility(View.VISIBLE);
                                } else {
                                    amountView.setVisibility(View.GONE);
                                    remarkView.setVisibility(View.GONE);
                                }
                            }
                        }
                ));

        findViewById(R.id.btn_ekyc).setOnClickListener(v -> {
            startActivity(new Intent(this, EKYCProcessActivity.class)
                    .putExtra("SDKDetail", intentSdkDetail)
                    .setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP));
        });
    }


    void launch() {

        if (selectedTypePos == -1 || transactionType.getText().toString().isEmpty()) {
            NUL(findViewById(R.id.mainView), "Please select any type", Color.RED);

            return;
        } else if (selectedTypePos == 0 && amountEt.getText().toString().isEmpty()) {
            amountEt.setError("Please enter amount.");
            amountEt.requestFocus();
            return;

        } else if (selectedTypePos == 1 && amountEt.getText().toString().isEmpty()) {
            amountEt.setError("Please enter amount.");
            amountEt.requestFocus();
            return;

        }
        if (selectedTypePos == 0 || selectedTypePos == 1) {
            InitiateMiniBank(this, loader);
        } else {
            String tid = "fingpay" + String.valueOf(new Date().getTime());
            getLocation(tid);
        }
    }


    void getLocation(String tid) {
        if (UtilMethods.INSTANCE.getLattitude != 0 && UtilMethods.INSTANCE.getLongitude != 0) {

            fingpaySubmit(tid, UtilMethods.INSTANCE.getLattitude, UtilMethods.INSTANCE.getLongitude);
        } else {
            if (mGetLocation != null) {
                mGetLocation.startLocationUpdates((lattitude, longitude) -> {
                    UtilMethods.INSTANCE.getLattitude = lattitude;
                    UtilMethods.INSTANCE.getLongitude = longitude;
                    fingpaySubmit(tid, lattitude, longitude);
                });
            } else {
                mGetLocation = new GetLocation(this, loader);
                mGetLocation.startLocationUpdates((lattitude, longitude) -> {
                    UtilMethods.INSTANCE.getLattitude = lattitude;
                    UtilMethods.INSTANCE.getLongitude = longitude;
                    fingpaySubmit(tid, lattitude, longitude);
                });
            }
        }
    }

    void fingpaySubmit(String tid, double lattitude, double longitude) {

        String amount = amountEt.getText().toString().trim();
        String remarks = remarksEt.getText().toString().trim();

        if (Utility.INSTANCE.isValidString(merchantId)) {
            if (Utility.INSTANCE.isValidString(password)) {

                Intent intent = new Intent(this, MicroAtmLoginScreen.class);
                intent.putExtra(Constants.MERCHANT_USERID, merchantId);
                intent.putExtra(Constants.MERCHANT_PASSWORD, password);
                intent.putExtra(Constants.AMOUNT, amount);
                intent.putExtra(Constants.REMARKS, remarks);
                intent.putExtra(Constants.MOBILE_NUMBER, mobile);
                intent.putExtra(Constants.AMOUNT_EDITABLE, false);
                intent.putExtra(Constants.TXN_ID, tid + "");
                intent.putExtra(Constants.SUPER_MERCHANTID, superMerchentId);
                intent.putExtra(Constants.IMEI, deviceId);
                intent.putExtra(Constants.LATITUDE, lattitude);
                intent.putExtra(Constants.LONGITUDE, longitude);


                switch (selectedTypePos) {
                    case 0:
                        if (amountEt.getText().toString().isEmpty()) {
                            amountEt.setError("Please enter amount.");
                            amountEt.requestFocus();
                            return;
                        }
                        intent.putExtra(Constants.TYPE, Constants.CASH_WITHDRAWAL);
                        break;

                    case 1:
                        if (amountEt.getText().toString().isEmpty()) {
                            amountEt.setError("Please enter amount.");
                            amountEt.requestFocus();
                            return;
                        }
                        intent.putExtra(Constants.TYPE, Constants.CASH_DEPOSIT);
                        break;

                    case 2:
                        intent.putExtra(Constants.TYPE, Constants.BALANCE_ENQUIRY);
                        break;

                    case 3:
                        intent.putExtra(Constants.TYPE, Constants.MINI_STATEMENT);
                        break;
                    case 4:
                        intent.putExtra(Constants.TYPE, Constants.CARD_ACTIVATION);
                        break;
                    case 5:
                        intent.putExtra(Constants.TYPE, Constants.PIN_RESET);
                        break;

                    case 6:
                        intent.putExtra(Constants.TYPE, Constants.CHANGE_PIN);
                        break;
                }

                intent.putExtra(Constants.MICROATM_MANUFACTURER, Constants.MoreFun);
                startActivityForResult(intent, CODE);
            } else {
                NUL(findViewById(R.id.mainView), "Please use valid password", Color.RED);
            }
        } else {
            NUL(findViewById(R.id.mainView), "Please use valid merchant id", Color.RED);
        }
    }

    void history() {

        Intent intent = new Intent(this, HistoryScreen.class);
        intent.putExtra(Constants.MERCHANT_USERID, merchantId);
        intent.putExtra(Constants.MERCHANT_PASSWORD, password);
        intent.putExtra(Constants.SUPER_MERCHANTID, superMerchentId);
        intent.putExtra(Constants.IMEI, deviceId);
        startActivity(intent);


         /*//For Testing
        Intent intent = new Intent(this, MicroATMBalanceStatusActivity.class);
        Intent inte = new Intent();
        inte.putExtra(Constants.TRANS_STATUS, true);
        inte.putExtra(Constants.MESSAGE, "Hello Vishnu How are u");
       // inte.putExtra(Constants.TRANS_AMOUNT, 34656.0);
        inte.putExtra(Constants.BALANCE_AMOUNT, 3453.0);
        inte.putExtra(Constants.RRN, "djwid28e092");
        inte.putExtra(Constants.TRANS_TYPE, "Cash Withdrawal");
        inte.putExtra(Constants.TYPE, Constants.CASH_WITHDRAWAL);
        inte.putExtra(Constants.CARD_NUM, "6372472482992");
        inte.putExtra(Constants.BANK_NAME, "Punjab Bank");
        inte.putExtra(Constants.CARD_TYPE, "VISA");
        inte.putExtra(Constants.TERMINAL_ID, "73rhiwef");
        inte.putExtra(Constants.FP_TRANS_ID, "ue93urhf");
        //inte.putExtra(Constants.TRANS_ID, "e982dgwdfgow290e");
        int size = inte.getExtras().size();
        intent.putExtras(inte);
        startActivity(intent);*/
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    boolean status = data.getBooleanExtra(Constants.TRANS_STATUS, false);
                    String response = data.getStringExtra(Constants.MESSAGE);
                    double transAmount = data.getDoubleExtra(Constants.TRANS_AMOUNT, 0);
                    double balAmount = data.getDoubleExtra(Constants.BALANCE_AMOUNT, 0);
                    String bankRrn = data.getStringExtra(Constants.RRN);
                    String cardNum = getIntent().getStringExtra(Constants.CARD_NUM);
                    String bankName = getIntent().getStringExtra(Constants.BANK_NAME);
                    String transType = data.getStringExtra(Constants.TRANS_TYPE);
                    int type = data.getIntExtra(Constants.TYPE, Constants.CASH_WITHDRAWAL);
                    data.putExtra("APP_TID", tid);
                    if (Utility.INSTANCE.isValidString(response)) {
                        if (data.getExtras().size() > 7) {
                            if (type == Constants.BALANCE_ENQUIRY) {
                                Intent intent = new Intent(MicroAtmActivity.this, MicroATMBalanceStatusActivity.class);
                                intent.putExtras(data);
                                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                startActivity(intent);
                            } else {
                                Intent intent = new Intent(MicroAtmActivity.this, MicroATMStatusActivity.class);
                                intent.putExtras(data);
                                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                startActivity(intent);
                            }
                        } else {
                            showData(status, response, transAmount, balAmount, bankRrn, transType, type);
                        }
                        if (type == Constants.CASH_WITHDRAWAL || type == Constants.CASH_DEPOSIT) {
                            if (status) {
                                UpdateMiniBankStatus(MicroAtmActivity.this, cardNum, bankName, loader, bankRrn, "2", response);
                            } else {
                                UpdateMiniBankStatus(MicroAtmActivity.this, cardNum, bankName, loader, bankRrn, "3", response);
                            }
                        }
                    }
                } else {
                    NUL(findViewById(R.id.mainView), "Data not found", Color.RED);
                    if (selectedTypePos == 0 || selectedTypePos == 1) {
                        UpdateMiniBankStatus(MicroAtmActivity.this, "", "", loader, "", "3", "Data not found");
                    }
                }
            } else if (resultCode == RESULT_CANCELED) {
                if (selectedTypePos == 0 || selectedTypePos == 1) {
                    UpdateMiniBankStatus(MicroAtmActivity.this, "", "", loader, "", "3", "Canceled");
                }
                NUL(findViewById(R.id.mainView), "Canceled", Color.RED);

            }
        } else {
            if (mGetLocation != null) {
                mGetLocation.onActivityResult(requestCode, resultCode, data);
            }
        }
    }


    @Override
    protected void onPause() {
        if (mGetLocation != null) {
            mGetLocation.onPause();
        }
        super.onPause();
    }

    public void InitiateMiniBank(final Activity context, final CustomLoader loader) {
        try {
            loader.show();
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<InitiateMiniBankATMRes> call = git.initiateMiniBank(new InitiateMiniATMReq(UtilMethods.INSTANCE.getLattitude+"", UtilMethods.INSTANCE.getLongitude+"",intentSdkType, intentOid, amountEt.getText().toString().trim(), mLoginDataResponse.getData().getUserID() + "", mLoginDataResponse.getData().getLoginTypeID() + "", ApplicationConstant.INSTANCE.APP_ID, deviceId,
                    "", BuildConfig.VERSION_NAME, deviceSerialNum,
                    mLoginDataResponse.getData().getSessionID(), mLoginDataResponse.getData().getSession()));

            call.enqueue(new Callback<InitiateMiniBankATMRes>() {

                @Override
                public void onResponse(Call<InitiateMiniBankATMRes> call, retrofit2.Response<InitiateMiniBankATMRes> response) {

                    if (loader.isShowing())
                        loader.dismiss();
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            if (response.body().getStatuscode() == 1) {
                                tid = response.body().getTid() + "";
                                getLocation(tid);
                            } else {
                                if (!response.body().isVersionValid()) {
                                    UtilMethods.INSTANCE.versionDialog(context);
                                } else {
                                    UtilMethods.INSTANCE.Error(context, response.body().getMsg() + "");
                                }
                            }

                        }
                    } else {
                        UtilMethods.INSTANCE.apiErrorHandle(context, response.code(), response.message());
                    }
                }

                @Override
                public void onFailure(Call<InitiateMiniBankATMRes> call, Throwable t) {

                    if (loader.isShowing())
                        loader.dismiss();
                    UtilMethods.INSTANCE.apiFailureError(context, t);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void UpdateMiniBankStatus(final Activity context, String cardNum, String bankName, final CustomLoader loader, String vendorId, String apiStatus, String remark) {
        try {
            loader.show();
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<InitiateMiniBankATMRes> call = git.updateMiniBankStatus(new UpdateMiniBankStatusReq(
                    UtilMethods.INSTANCE.getLattitude+"",
                    UtilMethods.INSTANCE.getLongitude+"",cardNum, bankName, tid, vendorId, apiStatus, remark,
                    mLoginDataResponse.getData().getUserID() + "", mLoginDataResponse.getData().getLoginTypeID() + "", ApplicationConstant.INSTANCE.APP_ID,
                    deviceId,
                    "", BuildConfig.VERSION_NAME, deviceSerialNum,
                    mLoginDataResponse.getData().getSessionID(), mLoginDataResponse.getData().getSession()));

            call.enqueue(new Callback<InitiateMiniBankATMRes>() {

                @Override
                public void onResponse(Call<InitiateMiniBankATMRes> call, retrofit2.Response<InitiateMiniBankATMRes> response) {

                    if (loader.isShowing())
                        loader.dismiss();
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            if (response.body().getStatuscode() == 1) {

                            } else {
                                if (!response.body().isVersionValid()) {
                                    UtilMethods.INSTANCE.versionDialog(context);
                                } else {
                                    UtilMethods.INSTANCE.Error(context, response.body().getMsg() + "");
                                }
                            }

                        }
                    } else {
                        UtilMethods.INSTANCE.apiErrorHandle(context, response.code(), response.message());
                    }
                }

                @Override
                public void onFailure(Call<InitiateMiniBankATMRes> call, Throwable t) {

                    if (loader.isShowing())
                        loader.dismiss();
                    UtilMethods.INSTANCE.apiFailureError(context, t);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    void showData(boolean status, String response, double transAmount, double balAmount, String bankRrn, String transType, int type) {
        try {
            if (mBottomSheetDialog != null && mBottomSheetDialog.isShowing()) {
                return;
            }

            mBottomSheetDialog = new BottomSheetDialog(this);
            View dialogView = getLayoutInflater().inflate(R.layout.dialog_mini_atm_response, null);

            LinearLayout shareView = dialogView.findViewById(R.id.shareView);
            TextView typeTv = dialogView.findViewById(R.id.typeTv);
            ImageView closeIv = dialogView.findViewById(R.id.closeIv);
            ImageView statusIcon = dialogView.findViewById(R.id.statusIcon);
            TextView statusTv = dialogView.findViewById(R.id.statusTv);
            TextView statusMsg = dialogView.findViewById(R.id.statusMsg);
            LinearLayout tamtView = dialogView.findViewById(R.id.tamtView);
            TextView txnAmt = dialogView.findViewById(R.id.txnAmt);
            LinearLayout bAmtView = dialogView.findViewById(R.id.bAmtView);
            TextView balAmt = dialogView.findViewById(R.id.balAmt);
            LinearLayout rrnView = dialogView.findViewById(R.id.rrnView);
            TextView rrn = dialogView.findViewById(R.id.rrn);
            LinearLayout TypeView = dialogView.findViewById(R.id.TypeView);
            TextView txnType = dialogView.findViewById(R.id.txnType);
            Button closeBtn = dialogView.findViewById(R.id.closeBtn);
            Button shareBtn = dialogView.findViewById(R.id.shareBtn);
            Button repetBtn = dialogView.findViewById(R.id.repetBtn);


            String responseStr = "";

            if (status) {
                statusTv.setText("Success");
                statusIcon.setImageResource(R.drawable.ic_check_mark);
                ImageViewCompat.setImageTintList(statusIcon, AppCompatResources.getColorStateList(this, R.color.green));
                statusTv.setTextColor(getResources().getColor(R.color.green));

            } else {
                statusTv.setText("Failed");
                statusIcon.setImageResource(R.drawable.ic_cross_mark);
                ImageViewCompat.setImageTintList(statusIcon, AppCompatResources.getColorStateList(this, R.color.color_red));
                statusTv.setTextColor(getResources().getColor(R.color.color_red));

            }

            if (type == Constants.CASH_WITHDRAWAL) {
                typeTv.setText("Cash Withdrawl");
            } else if (type == Constants.CASH_DEPOSIT) {
                typeTv.setText("Cash Deposit");
            } else if (type == Constants.BALANCE_ENQUIRY) {
                typeTv.setText("Balance Enquiry");
            } else if (type == Constants.MINI_STATEMENT) {
                typeTv.setText("Mini Statement");
            } else if (type == Constants.CARD_ACTIVATION) {
                typeTv.setText("Card Activation");
            } else if (type == Constants.PIN_RESET) {
                typeTv.setText("Reset Pin");
            } else if (type == Constants.CHANGE_PIN) {
                typeTv.setText("Change Pin");
            }

            if (response != null && !response.isEmpty()) {
                statusMsg.setText(response);
                statusMsg.setVisibility(View.VISIBLE);
            } else {
                statusMsg.setVisibility(View.GONE);
            }

            if (type == Constants.BALANCE_ENQUIRY || type == Constants.MINI_STATEMENT) {
                if (transAmount != 0) {
                    txnAmt.setText(Utility.INSTANCE.formatedAmountWithRupees(transAmount + ""));
                    tamtView.setVisibility(View.VISIBLE);
                } else {
                    tamtView.setVisibility(View.GONE);
                }
                balAmt.setText(Utility.INSTANCE.formatedAmountWithRupees(balAmount + ""));
                bAmtView.setVisibility(View.VISIBLE);
            } else if (type == Constants.CASH_WITHDRAWAL || type == Constants.CASH_DEPOSIT) {
                txnAmt.setText(Utility.INSTANCE.formatedAmountWithRupees(transAmount + ""));
                tamtView.setVisibility(View.VISIBLE);

                if (balAmount != 0) {
                    balAmt.setText(Utility.INSTANCE.formatedAmountWithRupees(balAmount + ""));
                    bAmtView.setVisibility(View.VISIBLE);
                } else {
                    bAmtView.setVisibility(View.GONE);
                }
            } else {
                if (transAmount != 0) {
                    txnAmt.setText(Utility.INSTANCE.formatedAmountWithRupees(transAmount + ""));
                    tamtView.setVisibility(View.VISIBLE);
                } else {
                    tamtView.setVisibility(View.GONE);
                }

                if (balAmount != 0) {
                    balAmt.setText(Utility.INSTANCE.formatedAmountWithRupees(balAmount + ""));
                    bAmtView.setVisibility(View.VISIBLE);
                } else {
                    bAmtView.setVisibility(View.GONE);
                }
            }


            if (bankRrn != null && !bankRrn.isEmpty()) {
                rrn.setText(bankRrn);
                rrnView.setVisibility(View.VISIBLE);
            } else {
                rrnView.setVisibility(View.GONE);

            }

            if (transType != null && !transType.isEmpty()) {
                txnType.setText(transType);
                TypeView.setVisibility(View.VISIBLE);
            } else {
                TypeView.setVisibility(View.GONE);

            }

            closeIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mBottomSheetDialog.dismiss();
                }
            });
            mBottomSheetDialog.setContentView(dialogView);
            mBottomSheetDialog.show();

        } catch (IllegalStateException ise) {

        } catch (IllegalArgumentException iae) {

        } catch (Exception e) {

        }

    }

    public void NUL(View paramView, String paramString, int paramInt) {
        Snackbar localSnackbar = Snackbar.make(paramView, "" + paramString, Snackbar.LENGTH_LONG);
        View snackBarView = localSnackbar.getView();
        snackBarView.setBackgroundColor(paramInt);
        TextView mainTextView = snackBarView.findViewById(com.google.android.material.R.id.snackbar_text);
        mainTextView.setMaxLines(5);
        TextViewCompat.setTextAppearance(mainTextView, R.style.TextAppearance_AppCompat_Body2);
        localSnackbar.show();

    }
}
