package com.fintech.emoneyrechargeonlinenew.Activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;
import com.fintech.emoneyrechargeonlinenew.Api.Response.DmtReportObject;
import com.fintech.emoneyrechargeonlinenew.Api.Response.RechargeReportResponse;
import com.fintech.emoneyrechargeonlinenew.Auth.dto.LoginResponse;
import com.fintech.emoneyrechargeonlinenew.R;
import com.fintech.emoneyrechargeonlinenew.Util.ActivityActivityMessage;
import com.fintech.emoneyrechargeonlinenew.Util.ApplicationConstant;
import com.fintech.emoneyrechargeonlinenew.Util.CustomFilterDialogUtils.CustomFilterDialog;
import com.fintech.emoneyrechargeonlinenew.Util.GlobalBus;
import com.fintech.emoneyrechargeonlinenew.Util.UtilMethods;
import com.fintech.emoneyrechargeonlinenew.usefull.CustomLoader;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


public class DMRReport extends AppCompatActivity {


    RecyclerView recycler_view;
    DMRReportAdapter mAdapter;
    String response = "";
    LinearLayoutManager mLayoutManager;
    ArrayList<DmtReportObject> transactionsObjects = new ArrayList<>();
    RechargeReportResponse transactions = new RechargeReportResponse();

    CustomLoader loader;
    private String filterChildNumber="";
    private Toolbar toolbar;
    private CustomFilterDialog mCustomFilterDialog;
    private String filterFromDate = "", filterToDate = "";
    private String filterMobileNo = "";
    String filterTransactionId, filterChildMobileNo="", filterAccountNo;
    private int filterTopValue = 50;
    private String filterDateType = "";
    private String filterStatus = "", filteWalletType = "";
    private String filterChooseCriteria = "", filterEnterCriteria = "";
    private int filterStatusId, filteDateTypeId;
    private int filterChooseCriteriaId;
    private LoginResponse LoginPrefResponse;
    private boolean isRecent;
    private int filteWalletTypeId = 1, filterModeTypeId = 1;
    private String filterModeValue = "";
    int flag = 0;
    String today;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dmr_report_screen);
        mCustomFilterDialog = new CustomFilterDialog(this);
        loader = new CustomLoader(this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        recycler_view = findViewById(R.id.recycler_view);
        SharedPreferences myPrefs = getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, MODE_PRIVATE);
        String getLoginPref = myPrefs.getString(ApplicationConstant.INSTANCE.LoginPref, "");
        LoginPrefResponse = new Gson().fromJson(getLoginPref, LoginResponse.class);
        //filterChildMobileNo = LoginPrefResponse.getData().getMobileNo() + "";
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setTitle("DMT Report");
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        final Calendar myCalendar = Calendar.getInstance();
        String myFormat = "dd-MMM-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        today = sdf.format(myCalendar.getTime());
        filterFromDate = today;
        filterToDate = today;


        isRecent = true;
        HitApi();
    }

    private void HitApi() {
        if (UtilMethods.INSTANCE.isNetworkAvialable(this)) {

            loader.show();
            loader.setCancelable(false);
            loader.setCanceledOnTouchOutside(false);

            UtilMethods.INSTANCE.DMTReport(this, filterStatusId, "0", filterFromDate, filterToDate, filterTransactionId, filterAccountNo, filterChildMobileNo, filterTopValue,loader);

        } else {
            UtilMethods.INSTANCE.NetworkError(this, getResources().getString(R.string.err_msg_network_title),
                    getResources().getString(R.string.err_msg_network));
        }
    }

    public void dataParse(String response) {
        Gson gson = new Gson();
        transactions = gson.fromJson(response, RechargeReportResponse.class);
        transactionsObjects = transactions.getDmtReport();
        if (transactionsObjects.size() > 0) {
            mAdapter = new DMRReportAdapter(transactionsObjects, DMRReport.this);
            mLayoutManager = new LinearLayoutManager(this.getApplicationContext());
            recycler_view.setLayoutManager(mLayoutManager);
            recycler_view.setItemAnimator(new DefaultItemAnimator());
            recycler_view.setAdapter(mAdapter);
        } else {
            if(flag==1)
            UtilMethods.INSTANCE.Error(this, "No Record Found ! between \n " + filterFromDate + " to " + filterToDate);
            else
                UtilMethods.INSTANCE.Error(this, "No Record Found ! on \n " + today);

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_filter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_filter) {
            filterShow();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void filterShow() {
        mCustomFilterDialog.openAEPSRechargeDMRFilter("DMT", filterFromDate, filterToDate, filterChildNumber,filterAccountNo,filterTransactionId, filterTopValue,new CustomFilterDialog.AEPSDMRAndRechargeFilterCallBack() {
            @Override
            public void onSubmitClick(String fromDate, String toDate, String childNo, String accountNo, String transactionID, int topRows) {

                filterFromDate = fromDate;
                filterToDate = toDate;
                filterChildNumber = childNo;
                filterAccountNo = accountNo;
                filterTransactionId = transactionID;
                filterTopValue = topRows;
                flag = 1;
                isRecent=false;
                HitApi();
            }
        });
/*
        mCustomFilterDialog.openDisputeFilter("DMT", LoginPrefResponse.getData().getRoleID(),
                filterFromDate, filterToDate, filterMobileNo, filterTransactionId, filterChildMobileNo,
                filterAccountNo, filterTopValue, filterStatus, filterDateType, filterModeValue, filterChooseCriteriaId, filterChooseCriteria, filterEnterCriteria, filteWalletType, new CustomFilterDialog.LedgerFilterCallBack() {
                    @Override
                    public void onSubmitClick(String fromDate, String toDate, String mobileNo, String transactionId, String childMobileNo, String accountNo, int topValue, int statusId, String statusValue, int dateTypeId, String dateTypeValue, int modeTypeId, String modeValue, int chooseCriteriaId, String chooseCriteriaValue, String enterCriteriaValue, int walletTypeId, String walletType) {
                        filterFromDate = fromDate;
                        filterToDate = toDate;
                        filterMobileNo = mobileNo;
                        filterTransactionId = transactionId;
                        filterChildMobileNo = childMobileNo;
                        filterAccountNo = accountNo;
                        filterTopValue = topValue;
                        filterStatusId = statusId;
                        filterStatus = statusValue;
                        filteDateTypeId = dateTypeId;
                        filterDateType = dateTypeValue;
                        filterChooseCriteriaId = chooseCriteriaId;
                        filterChooseCriteria = chooseCriteriaValue;
                        filterEnterCriteria = enterCriteriaValue;
                        filteWalletTypeId = walletTypeId;
                        filteWalletType = walletType;
                        filterModeValue = modeValue;
                        filterModeTypeId = modeTypeId;
                        isRecent = false;
                        HitApi();
                    }
                });*/
    }


    @Subscribe
    public void onActivityActivityMessage(ActivityActivityMessage activityFragmentMessage) {
        if (activityFragmentMessage.getMessage().equalsIgnoreCase("DMRTransaction")) {
            response = activityFragmentMessage.getFrom();
            dataParse(response);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            GlobalBus.getBus().register(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Unregister the registered event.
        GlobalBus.getBus().unregister(this);
    }
}
