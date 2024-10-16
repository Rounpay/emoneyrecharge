package com.fintech.emoneyrechargeonlinenew.DTHSubscription.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fintech.emoneyrechargeonlinenew.DTHSubscription.Adapter.DthPackageAdapter;
import com.fintech.emoneyrechargeonlinenew.DTHSubscription.dto.DthPackage;
import com.fintech.emoneyrechargeonlinenew.R;

import java.util.ArrayList;


public class DthPackageActivity extends AppCompatActivity {

    DthPackageAdapter mDthPackageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dth_package);
        Toolbar toolBar = findViewById(R.id.toolbar);
        toolBar.setTitle(getIntent().getStringExtra("Title"));
        toolBar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolBar);
        toolBar.setNavigationIcon(R.drawable.ic_arrow_back_icon);

        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DthPackageActivity.this.onBackPressed();
            }
        });
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<DthPackage> mGetDthPackageResponse = (ArrayList<DthPackage>) getIntent().getSerializableExtra("PACKAGE_DATA_ARRAY");
        recyclerView.setAdapter(new DthPackageAdapter(mGetDthPackageResponse, this));
    }

    public void ViewChannel(DthPackage operator) {

        startActivity(new Intent(this, DthChannelActivity.class)
                .putExtra("DTH_PACKAGE", operator)
                .setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP));
    }

    public void SelectedPackage(DthPackage operator) {

        Intent intent = new Intent();
        intent.putExtra("Package", operator);
        setResult(RESULT_OK, intent);
        finish();
    }
}
