package com.fintech.emoneyrechargeonlinenew.Activities.DthPlan.UI;

import android.content.Intent;
import android.os.Bundle;

import com.fintech.emoneyrechargeonlinenew.Activities.DthPlan.dto.DthPlanInfoAllResponse;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.fintech.emoneyrechargeonlinenew.Activities.DthPlan.dto.PlanInfoRPData;
import com.fintech.emoneyrechargeonlinenew.Activities.DthPlan.dto.PlanInfoRecords;
import com.fintech.emoneyrechargeonlinenew.R;

import java.util.ArrayList;
import java.util.List;

public class DthPlanInfoActivity extends AppCompatActivity {

    PlanInfoRecords responsePlan = new PlanInfoRecords();
    PlanInfoRecords responsePAPlan = new PlanInfoRecords();
    PlanInfoRPData responseRPPlan = new PlanInfoRPData();
    ArrayList<String> rechargeType = new ArrayList<>();
    PlanInfoRecords responseMyPlan = new PlanInfoRecords();
    DthPlanInfoAllResponse dthPlanInfoAllResponse;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dth_plan_info);

        responsePlan = (PlanInfoRecords) getIntent().getSerializableExtra("response");
        responseRPPlan = (PlanInfoRPData) getIntent().getSerializableExtra("responseRP");
        responsePAPlan= (PlanInfoRecords) getIntent().getSerializableExtra("responsePA");
        responseMyPlan = (PlanInfoRecords) getIntent().getSerializableExtra("responseMyPlan");
        dthPlanInfoAllResponse = (DthPlanInfoAllResponse) getIntent().getSerializableExtra("responsePlanUpdated");
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Plans");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tabLayout = findViewById(R.id.tabs);
        tabLayout.setVisibility(View.GONE);
        viewPager = findViewById(R.id.viewpager);

        dataParse();
    }


    private void dataParse() {
        if (responsePlan != null) {
            if (responsePlan.getPlan() != null && responsePlan.getPlan().size() > 0) {
                rechargeType.add("Plan");
            }
            if (responsePlan.getAddOnPack() != null && responsePlan.getAddOnPack().size() > 0) {
                rechargeType.add("Add On Pack");
            }
        }
        else if (responseRPPlan != null) {

            if (responseRPPlan.getResponse() != null && responseRPPlan.getResponse().size() > 0) {
                for (int i = 0; i < responseRPPlan.getResponse().size(); i++) {
                    if (!rechargeType.contains(responseRPPlan.getResponse().get(i).getRechargeType())) {
                        rechargeType.add(responseRPPlan.getResponse().get(i).getRechargeType());
                    }
                }

            }
        }else if (responsePAPlan != null) {
            if (responsePAPlan.getPlan() != null && responsePAPlan.getPlan().size() > 0) {
                rechargeType.add("Plan");
            }
            if (responsePAPlan.getAddOnPack() != null && responsePAPlan.getAddOnPack().size() > 0) {
                rechargeType.add("Add On Pack");
            }
        }
        else if(responseMyPlan!=null){
            if (responseMyPlan.getPlan() != null && responseMyPlan.getPlan().size() > 0) {
                rechargeType.add("Plan");
            }
            if (responseMyPlan.getAddOnPack() != null && responseMyPlan.getAddOnPack().size() > 0) {
                rechargeType.add("Add On Pack");
            }
        }else if(dthPlanInfoAllResponse !=null){
           /* List<PlanInfoData> planRNList= dthPlanInfoAllResponse.getDataList();
            if(planRNList!=null && planRNList.size()>0){
                for ( PlanInfoData mPlanInfoData:planRNList){
                    rechargeType.add(mPlanInfoData.getpType());
                }

            }*/
        }
        setupViewPager(viewPager);
        tabLayout.setVisibility(View.VISIBLE);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        for (int i = 0; i < rechargeType.size(); i++) {
            DthViewPlanFragment mFragment = new DthViewPlanFragment();
            Bundle arg = new Bundle();
            arg.putString("type", rechargeType.get(i));
            arg.putSerializable("response", responsePlan);
            arg.putSerializable("responseRP", responseRPPlan);
            arg.putSerializable("responsePA", responsePAPlan);
            arg.putSerializable("responseMyPlan", responseMyPlan);
            arg.putSerializable("responseRN", dthPlanInfoAllResponse);
            mFragment.setArguments(arg);
            adapter.addFragment(mFragment, "" + rechargeType.get(i));
        }
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(rechargeType.size());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.browseplan_menu, menu);
        final MenuItem settingsItem = menu.findItem(R.id.operatorIcon);
        this.invalidateOptionsMenu();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }

    public void ItemClick(String amount, String desc) {
        Intent clickIntent = new Intent();
        clickIntent.putExtra("Amount", amount);
        clickIntent.putExtra("desc", desc);
        setResult(RESULT_OK, clickIntent);
        finish();
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
