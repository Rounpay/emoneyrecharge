package com.fintech.emoneyrechargeonlinenew.Activities.DthPlan.UI;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fintech.emoneyrechargeonlinenew.Activities.DthPlan.dto.DthPlanInfoAllResponse;
import com.fintech.emoneyrechargeonlinenew.Activities.DthPlan.dto.PDetails;
import com.fintech.emoneyrechargeonlinenew.Activities.DthPlan.dto.PlanInfoPlan;
import com.fintech.emoneyrechargeonlinenew.Activities.DthPlan.dto.PlanInfoRPData;
import com.fintech.emoneyrechargeonlinenew.Activities.DthPlan.dto.PlanInfoRecords;
import com.fintech.emoneyrechargeonlinenew.Activities.DthPlan.dto.PlanRPResponse;
import com.fintech.emoneyrechargeonlinenew.R;

import java.util.ArrayList;
import java.util.List;


public class DthViewPlanFragment extends Fragment {

    RecyclerView recycler_view;
    TextView noData;
    ArrayList<PlanInfoPlan> operatorDetailshow = new ArrayList<PlanInfoPlan>();
    ArrayList<PlanRPResponse> operatorDetailRPshow = new ArrayList<>();
    ArrayList<PlanInfoPlan> operatorDetailPAshow = new ArrayList<>();
    List<PDetails> operatorDetailRNshow=new ArrayList<>();
    PlanInfoRecords response;
    PlanInfoRPData responseRP;
    PlanInfoRecords responsePA;
    PlanInfoRecords responseMyPlan;
    DthPlanInfoAllResponse mRNResponse;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.recharge_plan_fragment, container, false);

        try {
            String re_type = getArguments().getString("type");
            response = (PlanInfoRecords) getArguments().getSerializable("response");
            responseRP = (PlanInfoRPData) getArguments().getSerializable("responseRP");
            responsePA = (PlanInfoRecords) getArguments().getSerializable("responsePA");
            responseMyPlan = (PlanInfoRecords) getArguments().getSerializable("responseMyPlan");
            mRNResponse = (DthPlanInfoAllResponse) getArguments().getSerializable("responseRN");
            recycler_view = v.findViewById(R.id.recycler_view);
            noData = v.findViewById(R.id.noData);

            if (response != null) {
                if (re_type.equalsIgnoreCase("Plan")) {
                    operatorDetailshow.addAll(response.getPlan());
                } else {
                    operatorDetailshow.addAll(response.getAddOnPack());
                }
            }
            if (responseRP != null) {
                if (responseRP.getResponse() != null && responseRP.getResponse().size() > 0) {
                    for (int i = 0; i < responseRP.getResponse().size(); i++) {
                        if (re_type.equalsIgnoreCase(responseRP.getResponse().get(i).getRechargeType())) {
                            operatorDetailRPshow.add(responseRP.getResponse().get(i));
                        }
                    }

                }
                /*if (re_type.equalsIgnoreCase("Plan")) {
                    operatorDetailRPshow.addAll(responseRP.getResponse());
                }*/
            }
            if (responsePA != null) {
                if (re_type.equalsIgnoreCase("Plan")) {
                    operatorDetailPAshow.addAll(responsePA.getPlan());
                } else {
                    operatorDetailPAshow.addAll(responsePA.getAddOnPack());
                }
            }

            if (responseMyPlan != null) {
                if (re_type.equalsIgnoreCase("Plan")) {
                    operatorDetailPAshow.addAll(responseMyPlan.getPlan());
                } else {
                    operatorDetailPAshow.addAll(responseMyPlan.getAddOnPack());
                }
            }
            if(mRNResponse!=null){
              /*  if (mRNResponse.getDataList() != null && mRNResponse.getDataList().size() > 0) {
                    for (int i = 0; i < mRNResponse.getDataList().size(); i++) {
                        if (re_type.equalsIgnoreCase(mRNResponse.getDataList().get(i).getpType())) {
                            List<PDetails> pDetials=mRNResponse.getDataList().get(i).getpDetials();
                            operatorDetailRNshow.addAll(pDetials);
                        }
                    }

                }*/
            }
            if (operatorDetailshow != null && operatorDetailshow.size() > 0) {
                noData.setVisibility(View.GONE);
                DthPlanListAdapter mAdapter = new DthPlanListAdapter(operatorDetailshow, getActivity());

                recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
                recycler_view.setAdapter(mAdapter);

            } else if (operatorDetailRPshow != null && operatorDetailRPshow.size() > 0) {
                noData.setVisibility(View.GONE);
                DthPlanListRPAdapter mAdapter = new DthPlanListRPAdapter(operatorDetailRPshow, getActivity());

                recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
                recycler_view.setAdapter(mAdapter);

            }
            else if (operatorDetailPAshow != null && operatorDetailPAshow.size() > 0) {
                noData.setVisibility(View.GONE);
                DthPlanListAdapter mAdapter = new DthPlanListAdapter(operatorDetailPAshow, getActivity());

                recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
                recycler_view.setAdapter(mAdapter);

            }else if (operatorDetailRNshow != null && operatorDetailRNshow.size() > 0) {
                noData.setVisibility(View.GONE);
                DthPlanListRNAdapter mAdapter = new DthPlanListRNAdapter(operatorDetailRNshow, getActivity());
                recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
                recycler_view.setAdapter(mAdapter);

            }else {
                noData.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return v;
    }


}
