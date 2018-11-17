package com.hm.groupchat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BaseActivity extends AppCompatActivity {

    private ProgressLoader mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    protected void displayLoadingIndicator(String message) {

        mLoadingIndicator = new ProgressLoader(this, message);
        mLoadingIndicator.show();
    }

    protected void hideLoadingIndicator() {

        if(mLoadingIndicator != null && mLoadingIndicator.isShowing())
            mLoadingIndicator.dismiss();
    }
}
