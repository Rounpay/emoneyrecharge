package com.fintech.emoneyrechargeonlinenew.QRScan.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fintech.emoneyrechargeonlinenew.Auth.dto.LoginResponse;
import com.fintech.emoneyrechargeonlinenew.BuildConfig;
import com.fintech.emoneyrechargeonlinenew.QRScan.Adapter.VPAListAdapter;
import com.fintech.emoneyrechargeonlinenew.QRScan.dto.VPAListUPIPaymentRequest;
import com.fintech.emoneyrechargeonlinenew.QRScan.dto.VPAListUPIPaymentResponse;
import com.fintech.emoneyrechargeonlinenew.QRScan.dto.VpaListResponse;
import com.fintech.emoneyrechargeonlinenew.R;
import com.fintech.emoneyrechargeonlinenew.Util.ApiClient;
import com.fintech.emoneyrechargeonlinenew.Util.ApplicationConstant;
import com.fintech.emoneyrechargeonlinenew.Util.EndPointInterface;
import com.fintech.emoneyrechargeonlinenew.Util.Senderobject;
import com.fintech.emoneyrechargeonlinenew.Util.UtilMethods;
import com.fintech.emoneyrechargeonlinenew.usefull.CustomLoader;
import com.google.gson.Gson;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class VPAListActivity extends AppCompatActivity {

    private View vpaListContainerView;
    private EditText senderNumberEt;
    private EditText searchEt;
    private ImageView clearIcon;
    private Toolbar toolbar;
    private Button upiPayNewBtn;
    private RecyclerView vpaListRv;
    private VPAListAdapter mVPAListAdapter;
    private List<VpaListResponse> vpaList=new ArrayList<>();
    private CustomLoader loader;
    private final Integer VPA_LIST_INTENT_REQUEST_CODE=10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vpalist);
        getIds();
    }


    private void getIds() {
        loader = new CustomLoader(this, android.R.style.Theme_Translucent_NoTitleBar);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setTitle("UPI Payment");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        
        vpaListContainerView=findViewById(R.id.vpaListContainerView);
        senderNumberEt=findViewById(R.id.senderNumberEt);
        upiPayNewBtn=findViewById(R.id.upiPayNewBtn);
        searchEt=findViewById(R.id.searchEt);
        clearIcon=findViewById(R.id.clearIcon);
        vpaListRv=findViewById(R.id.vpaListRv);

        vpaListRv.setLayoutManager(new LinearLayoutManager(this));
        mVPAListAdapter=new VPAListAdapter(vpaList,this);
        vpaListRv.setAdapter(mVPAListAdapter);


        senderNumberEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()<10){
                    vpaListContainerView.setVisibility(View.GONE);
                    upiPayNewBtn.setVisibility(View.VISIBLE);
                }
                else if (s.length() == 10) {
                    loader.show();
                    loader.setCancelable(false);
                    hitVPAListApi();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        upiPayNewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(VPAListActivity.this, UPIPayActivity.class)
                        .putExtra("VPA","")
                        .putExtra("beneName","")
                        .putExtra("verified",false)
                        .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP),VPA_LIST_INTENT_REQUEST_CODE);
            }
        });

        clearIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchEt.setText("");
            }
        });


        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence newText, int start, int before, int count) {

                if (mVPAListAdapter != null) {
                    mVPAListAdapter.filter(newText.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void hitVPAListApi() {

        if (UtilMethods.INSTANCE.isNetworkAvialable(this)) {
            loader.show();
            loader.setCancelable(false);
            loader.setCanceledOnTouchOutside(false);

            try {
                LoginResponse mLoginResponse = new Gson().fromJson(UtilMethods.INSTANCE.getLoginPref(this), LoginResponse.class);
                EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
                Call<VPAListUPIPaymentResponse> call = git.GetVPAListUPIPayment(new VPAListUPIPaymentRequest(
                        new Senderobject(senderNumberEt.getText().toString()),
                        mLoginResponse.getData().getUserID(),
                        mLoginResponse.getData().getLoginTypeID(),
                        ApplicationConstant.INSTANCE.APP_ID,
                        UtilMethods.INSTANCE.getIMEI(this),
                        "", 
                        BuildConfig.VERSION_NAME,
                        UtilMethods.INSTANCE.getSerialNo(this)
                        , mLoginResponse.getData().getSessionID(),
                        mLoginResponse.getData().getSession()
                        ));
                call.enqueue(new Callback<VPAListUPIPaymentResponse>() {
                    @Override
                    public void onResponse(Call<VPAListUPIPaymentResponse> call, final retrofit2.Response<VPAListUPIPaymentResponse> response) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                        try {
                            if (response.isSuccessful() && response.body()!=null) {
                                if (response.body().getStatuscode()==1) {
                                    VPAListUPIPaymentResponse mDataResponse=response.body();
                                    if(mDataResponse.getVpaList()!=null && mDataResponse.getVpaList().size()>0){
                                        vpaListContainerView.setVisibility(View.VISIBLE);
                                        upiPayNewBtn.setVisibility(View.VISIBLE);
                                         vpaList.clear();
                                         vpaList.addAll(mDataResponse.getVpaList());
                                         mVPAListAdapter.notifyDataSetChanged();
                                    }else
                                    {
                                        upiPayNewBtn.setVisibility(View.VISIBLE);
                                        vpaListContainerView.setVisibility(View.GONE);
                                    }
                                } else if (response.body().getStatuscode()==-1) {
                                    if (!response.body().isVersionValid()) {
                                        UtilMethods.INSTANCE.versionDialog(VPAListActivity.this);
                                    } else {
                                        UtilMethods.INSTANCE.Error(VPAListActivity.this, response.body().getMsg() + "");
                                    }
                                    upiPayNewBtn.setVisibility(View.VISIBLE);
                                    vpaListContainerView.setVisibility(View.GONE);
                                }
                            }
                        } catch (Exception e) {
                            if (loader != null) {
                                if (loader.isShowing())
                                    loader.dismiss();
                            }
                            upiPayNewBtn.setVisibility(View.VISIBLE);
                            vpaListContainerView.setVisibility(View.GONE);
                        }

                    }

                    @Override
                    public void onFailure(Call<VPAListUPIPaymentResponse> call, Throwable t) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                        upiPayNewBtn.setVisibility(View.VISIBLE);
                        vpaListContainerView.setVisibility(View.GONE);
                        try {
                            if (t.getMessage() != null && !t.getMessage().isEmpty()) {

                                if (t.getMessage().contains("No address associated with hostname")) {
                                    UtilMethods.INSTANCE.NetworkError(VPAListActivity.this, VPAListActivity.this.getResources().getString(R.string.err_msg_network_title), getResources().getString(R.string.err_msg_network));
                                } else {
                                    UtilMethods.INSTANCE.Error(VPAListActivity.this, t.getMessage());

                                }

                            } else {
                                UtilMethods.INSTANCE.Error(VPAListActivity.this, VPAListActivity.this.getResources().getString(R.string.some_thing_error));

                            }
                        } catch (IllegalStateException ise) {
                            UtilMethods.INSTANCE.Error(VPAListActivity.this, ise.getMessage());

                        }

                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                upiPayNewBtn.setVisibility(View.VISIBLE);
                vpaListContainerView.setVisibility(View.GONE);
            }
        }
        else {
            UtilMethods.INSTANCE.NetworkError(this, getResources().getString(R.string.err_msg_network_title),
                    getResources().getString(R.string.err_msg_network));
            upiPayNewBtn.setVisibility(View.VISIBLE);
            vpaListContainerView.setVisibility(View.GONE);
        }
    }

    public void itemClick(VpaListResponse mVpaListResponse) {
        startActivityForResult(new Intent(VPAListActivity.this, UPIPayActivity.class)
                .putExtra("VPA",mVpaListResponse.getVpa())
                .putExtra("beneName",mVpaListResponse.getAccountHolder())
                .putExtra("verified",mVpaListResponse.isVerified())
                .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP),VPA_LIST_INTENT_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==VPA_LIST_INTENT_REQUEST_CODE && resultCode==RESULT_OK){
            hitVPAListApi();
        }
    }
}