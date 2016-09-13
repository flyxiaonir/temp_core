package com.lf.core;

import android.os.Bundle;
import android.view.View;

import com.lf.tempcore.tempActivity.TempActivity;

/**
 * Created by KY on 2016/9/13.
 */
public class ActProgressTest  extends TempActivity{
    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.act_progress_test_layout);
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void bindValues() {
        showProgressDialog(false);

    }

    @Override
    protected void OnViewClicked(View v) {

    }
}
