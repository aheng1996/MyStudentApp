package com.example.mystudentapp.base;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.mystudentapp.view.SpotsDialog;


/**
 * WaterEnvironmentDecisionMakingSystem
 *
 * @author sjw
 * @date 2018/6/27.
 */
public class BaseFragment extends Fragment {
    public static final String ARG_PARAM1 = "param1";
    public static final String ARG_PARAM2 = "param2";
    public static final String ARG_PARAM3 = "param3";

    private String mParam1;
    private String mParam2;

    public OnFragmentInteractionListener mListener;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void showToast(String msg) {
        if (msg == null) {
            return;
        }
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    public interface OnFragmentInteractionListener {


    }

    public void gotoActivity(Class clazz) {
        Intent intent = new Intent(getActivity(), clazz);
        startActivity(intent);
    }

    private SpotsDialog spotsDialog;

    public void showLandingDialog(String msg) {
        if (spotsDialog == null) {
            spotsDialog = (SpotsDialog) new SpotsDialog.Builder()
                    .setContext(getActivity()).build();
        }
        spotsDialog.setMessage(msg);
        spotsDialog.show();
    }

    public void hideDialog() {
        if (spotsDialog != null && spotsDialog.isShowing()) {
            spotsDialog.dismiss();
        }

    }

    public String getBeginTime() {
        return "";
    }

    public String getEndTime() {
        return "";
    }

    public String getStationCode(){
        return "";
    }


}
