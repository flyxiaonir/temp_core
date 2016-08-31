package com.lf.tempcore.tempModule.tempMVPCommI;

import com.lf.tempcore.tempEnum.TempErrorCode;
import com.lf.tempcore.tempEnum.TempNetType;

/**
 * Created by longf on 2016/4/25.
 */
public interface TempViewI {
    TempNetType checkNetWork();
    void setTitle(String title);
    void showPro();
    void dismissPro();
    void toast(String message);
    void showConntectError();
    void onError(TempErrorCode code,String message);
}
