package com.skypaps.clover.core.ui;

import android.os.Build;
import android.os.Bundle;


import com.skypaps.clover.core.manager.PreferenceManager;
import com.skypaps.clover.core.manager.SessionManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static com.skypaps.clover.core.ui.AbstractActivity.PERMISSION_REQUEST_CODE;


public abstract class AbstractFragment extends Fragment {

    @Inject
    protected PreferenceManager preferenceManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getCloverActivity().getApplicationComponent().inject(this);
    }

    @Inject
    protected SessionManager sessionManager;


    public AbstractActivity getCloverActivity() {
        return (AbstractActivity) super.getActivity();
    }

    public void showProgressDialog() {
        if (isAdded()) {
            getCloverActivity().showProgressDialog();
        }
    }

    public void showProgressDialog(String message) {
        if (isAdded()) {
            getCloverActivity().showProgressDialog(message);
        }
    }

    public void hideProgressDialog() {
        if (isAdded()) {
            getCloverActivity().hideProgressDialog();
        }
    }

    public void showSuccessDialog(String message){
        if (isAdded()) {
            getCloverActivity().showSuccessDialog(message);
        }
    }

    protected List<String> getMissingPermissions() {
        return new ArrayList<>();
    }

    protected void askPermission(List<String> missingPermissions) {
        if (Build.VERSION.SDK_INT >= 23 && missingPermissions.size() > 0) {

            List<String> requestPermissions = new ArrayList<>(missingPermissions);

            if (requestPermissions.size() > 0) {
                requestPermissions(requestPermissions.toArray(new String[0]), PERMISSION_REQUEST_CODE);
            }
        }
    }

    public void showError(String message) {
        if (isAdded()) {
            getCloverActivity().showError(message);
        }
    }


}
