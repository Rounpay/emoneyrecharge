package com.fintech.emoneyrechargeonlinenew.Activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fintech.emoneyrechargeonlinenew.Activities.Adapter.AchievedTargetAdapter;
import com.fintech.emoneyrechargeonlinenew.Api.Object.TargetAchieved;
import com.fintech.emoneyrechargeonlinenew.Api.Response.AppUserListResponse;
import com.fintech.emoneyrechargeonlinenew.Auth.dto.LoginResponse;
import com.fintech.emoneyrechargeonlinenew.R;
import com.fintech.emoneyrechargeonlinenew.Util.UtilMethods;
import com.fintech.emoneyrechargeonlinenew.usefull.CustomLoader;
import com.google.gson.Gson;


import java.util.List;

public class AchievedTargetActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CustomLoader loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achieved_target);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setTitle("Target Details");
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        loader = new CustomLoader(this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        hitApi();
    }


    void hitApi() {
        if (UtilMethods.INSTANCE.isNetworkAvialable(this)) {
            loader.show();
            loader.setCancelable(false);
            loader.setCanceledOnTouchOutside(false);
            UtilMethods.INSTANCE.UserAchieveTarget(this, false, loader, new Gson().fromJson(UtilMethods.INSTANCE.getLoginPref(this), LoginResponse.class),new UtilMethods.ApiCallBackTwoMethod() {
                @Override
                public void onSucess(Object object) {
                    AppUserListResponse mAppUserListResponse = (AppUserListResponse) object;
                    if (mAppUserListResponse != null) {
                        List<TargetAchieved> transactionsObjects = mAppUserListResponse.getTargetAchieveds();
                        if (transactionsObjects != null && transactionsObjects.size() > 0) {
                            AchievedTargetAdapter mAdapter = new AchievedTargetAdapter(transactionsObjects, AchievedTargetActivity.this);
                            recyclerView.setAdapter(mAdapter);

                        } else {
                            UtilMethods.INSTANCE.Error(AchievedTargetActivity.this, "Data not found.");
                        }
                    }
                }

                @Override
                public void onError(Object object) {

                }


            });


        } else {
            UtilMethods.INSTANCE.NetworkError(this, getResources().getString(R.string.err_msg_network_title),
                    getResources().getString(R.string.err_msg_network));
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}