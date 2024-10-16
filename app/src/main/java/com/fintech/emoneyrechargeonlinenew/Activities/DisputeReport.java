package com.fintech.emoneyrechargeonlinenew.Activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.fintech.emoneyrechargeonlinenew.Activities.Adapter.DisputeReportAdapter;
import com.fintech.emoneyrechargeonlinenew.Api.Object.RefundLog;
import com.fintech.emoneyrechargeonlinenew.Api.Response.AppUserListResponse;
import com.fintech.emoneyrechargeonlinenew.R;
import com.fintech.emoneyrechargeonlinenew.Util.ActivityActivityMessage;
import com.fintech.emoneyrechargeonlinenew.Util.CustomFilterDialogUtils.CustomFilterDialog;
import com.fintech.emoneyrechargeonlinenew.Util.GlobalBus;
import com.fintech.emoneyrechargeonlinenew.Util.UtilMethods;
import com.fintech.emoneyrechargeonlinenew.usefull.CustomLoader;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class DisputeReport extends AppCompatActivity implements View.OnClickListener {

    ProgressDialog mProgressDialog = null;
    RecyclerView recycler_view;
    DisputeReportAdapter mAdapter;
    String response = "";
    LinearLayoutManager mLayoutManager;
    List<RefundLog> transactionsObjects = new ArrayList<>();
    AppUserListResponse transactions = new AppUserListResponse();
    // Declare CustomLoader.....
    CustomLoader loader;
    RelativeLayout searchLayout;
    EditText number;
    //  CardView cardContainer;
    EditText childNumber;
    String rechargeMobileNumber = "";
    String childMobileNumber = "";
    EditText search_all;
    private Toolbar toolbar;
    private CustomFilterDialog mCustomFilterDialog;
    private String filterFromDate = "", filterToDate = "";
    private String filterMobileNo = "",filterMobileNoNew="";
    private int filterTopValue = 50;
    private String filterDateType = "";
    private String filterStatus = "",filteWalletType="",filterWalletType = "";
    private String filterChooseCriteria = "", filterEnterCriteria = "";
    private int filterStatusId, filteDateTypeId;
    private int filterChooseCriteriaId, filterWalletTypeId = 1;
    private String filterTransactionId, filterChildMobileNo, filterAccountNo;
    private int filteWalletTypeId=1,filterModeTypeId=1;
    private String filterModeValue;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recent_recharge_report);

        mCustomFilterDialog = new CustomFilterDialog(this);
        loader = new CustomLoader(this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        SimpleDateFormat sdfToday = new SimpleDateFormat("dd MMM yyyy", Locale.US);
        filterFromDate = sdfToday.format(new Date());
        filterToDate = filterFromDate;
        recycler_view = findViewById(R.id.recycler_view);
        mProgressDialog = new ProgressDialog(DisputeReport.this);
        search_all = findViewById(R.id.search_all);
        findViewById(R.id.clearIcon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search_all.setText("");
            }
        });
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setTitle("Dispute Report");
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        HitApi();
    }

    private void HitApi() {
        if (UtilMethods.INSTANCE.isNetworkAvialable(this)) {

            loader.show();
            loader.setCancelable(false);
            loader.setCanceledOnTouchOutside(false);

            UtilMethods.INSTANCE.RefundLog(this, filterTopValue, filterChooseCriteriaId, filterEnterCriteria, filterStatusId, filterFromDate, filterToDate, filteDateTypeId, filterMobileNoNew, loader);

        } else {
            UtilMethods.INSTANCE.NetworkError(this, getResources().getString(R.string.err_msg_network_title),
                    getResources().getString(R.string.err_msg_network));
        }
    }


    public void dataParse(String response) {
        Gson gson = new Gson();
        transactions = gson.fromJson(response, AppUserListResponse.class);
        transactionsObjects = transactions.getRefundLog();

        mAdapter = new DisputeReportAdapter(transactionsObjects, DisputeReport.this);
        mLayoutManager = new LinearLayoutManager(this.getApplicationContext());
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setAdapter(mAdapter);
        search_all.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mAdapter.filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
       /* search_all.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // TODO Auto-generated method stub

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // TODO Auto-generated method stub
                mAdapter.filter(newText);
                return false;
            }
        });*/
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * Called when start the activity
     */
    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            GlobalBus.getBus().register(this);
        }
    }

    /**
     * Called when leaving the activity
     */
    @Override
    public void onPause() {

        super.onPause();
    }

    /**
     * Called when returning to the activity
     */
    @Override
    public void onResume() {
        super.onResume();

    }

    /**
     * Called before the activity is destroyed
     */
    @Override
    public void onDestroy() {
        GlobalBus.getBus().unregister(this);
        super.onDestroy();
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

            filterDisput();
           /* if (visibleFlag) {
                visibleFlag = false;
                searchContainer.setVisibility(View.GONE);
            } else {
                visibleFlag = true;
                searchContainer.setVisibility(View.VISIBLE);
            }*/

            /*mCustomFilterDialog.openDisputeFilter("Dispute", "",
                    filterFromDate, filterToDate, filterMobileNo, filterTransactionId, filterChildMobileNo,

                    filterAccountNo, filterTopValue, filterStatus, filterDateType, filterModeValue, filterChooseCriteriaId, filterChooseCriteria, filterEnterCriteria, filteWalletType, new CustomFilterDialog.LedgerFilterCallBack() {
                        @Override
                        public void onSubmitClick(String fromDate, String toDate, String mobileNo, String transactionId, String childMobileNo, String accountNo, int topValue, int statusId, String statusValue, int dateTypeId, String dateTypeValue,int modeTypeId,String modeValue, int chooseCriteriaId, String chooseCriteriaValue, String enterCriteriaValue, int walletTypeId, String walletType) {
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
                            filterModeValue= modeValue;
                            filterModeTypeId=modeTypeId;
                            HitApi();
                        }
                    });*/


            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void filterDisput() {

        mCustomFilterDialog.openDisputeFilter("Dispute", filterFromDate, filterToDate, filterMobileNo, filterTopValue, filterStatus, filterDateType, filterChooseCriteriaId, filterChooseCriteria, filterEnterCriteria, filterWalletType,filterMobileNoNew, new CustomFilterDialog.LedgerFilterCallBack() {
            @Override
            public void onSubmitClick(String fromDate, String toDate, String mobileNo, int topValue, int statusId, String statusValue, int dateTypeId, String dateTypeValue, int chooseCriteriaId, String chooseCriteriaValue, String enterCriteriaValue, int walletTypeId, String walletType,String mobileNoNew) {
                filterFromDate = fromDate;
                filterToDate = toDate;
                filterMobileNo = mobileNo;
                filterTopValue = topValue;
                filterStatusId = statusId;
                filterStatus = statusValue;
                filteDateTypeId = dateTypeId;
                filterDateType = dateTypeValue;
                filterChooseCriteriaId = chooseCriteriaId;
                filterChooseCriteria = chooseCriteriaValue;
                filterEnterCriteria = enterCriteriaValue;
                filterWalletType = walletType;
                filterWalletTypeId = walletTypeId;
                HitApi();
            }
        });
    }

    @Subscribe
    public void onActivityActivityMessage(ActivityActivityMessage activityFragmentMessage) {
        if (activityFragmentMessage.getMessage().equalsIgnoreCase("Refund_Log")) {

            response = activityFragmentMessage.getFrom();
            dataParse(response);


        }
    }

    /**
     * public int validateForm() {
     * <p>
     * int flag = 0;
     * <p>
     * if (number.getText() != null && number.getText().toString().trim().length() > 1 &&
     * !(number.getText().toString().trim().length() < 10) &&
     * (number.getText().toString().trim().charAt(0) == '7' ||
     * number.getText().toString().trim().charAt(0) == '8' ||
     * number.getText().toString().trim().charAt(0) == '9')
     * ) {
     * rechargeMobileNumber = number.getText().toString().trim();
     * } else {
     * //  number.setError(getResources().getString(R.string.mobilenumber_error));
     * number.requestFocus();
     * flag++;
     * }
     * <p>
     * if (childNumber.getText() != null && childNumber.getText().toString().trim().length() > 1 &&
     * !(childNumber.getText().toString().trim().length() < 10) &&
     * (childNumber.getText().toString().trim().charAt(0) == '7' ||
     * childNumber.getText().toString().trim().charAt(0) == '8' ||
     * childNumber.getText().toString().trim().charAt(0) == '9')
     * ) {
     * childMobileNumber = childNumber.getText().toString().trim();
     * } else {
     * //  childNumber.setError(getResources().getString(R.string.mobilenumber_error));
     * childNumber.requestFocus();
     * flag++;
     * }
     * <p>
     * return flag;
     * }
     */

    public void specificReportList(String response) {
        searchLayout.setVisibility(View.VISIBLE);
        recycler_view.setVisibility(View.VISIBLE);
        dataParse(response);
    }
}