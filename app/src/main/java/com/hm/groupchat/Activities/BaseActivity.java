package com.hm.groupchat.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hm.groupchat.CustomViews.ProgressLoader;

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
