package com.lf.tempcore.tempModule.tempMVPCommI.tempPullableComms;

/**
 * Created by longf on 2016/5/6.
 */
public interface TempPullableViewI<DATA>{
    void onInit(DATA data);
    void onRefresh(DATA data);
    void onLoadmore(DATA data);
    void refreshStatus(boolean succeed);
    void loadMoreStatus(boolean succeed);
    void showPullableProgressDialog();
    void dismissPullableProgressDialog();
}
