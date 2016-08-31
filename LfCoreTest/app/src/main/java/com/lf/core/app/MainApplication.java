package com.lf.core.app;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.lf.tempcore.tempApplication.TempApplication;
import com.lf.tempcore.tempModule.tempDebuger.Debug;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemotAPIConfiguration;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemotAPIConnecter;

/**
 * Created by longf on 2016/8/31.
 */
public class MainApplication extends TempApplication {
    private final String TAG = MainApplication.class.getSimpleName();
    @Override
    public void onCreate() {
        initAPI("http://192.168.0.7:8090/edumap/");
        super.onCreate();
    }
    private void initAPI(String baseUri){
//        getPackageManager().getApplicationInfo()
        ApplicationInfo info = null;
        try {
             info =getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            Debug.info(TAG,"temp_base_uri="+info.metaData.getString("temp_base_uri"));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
       /* if (info!=null){

            info.metaData.getString("temp_base_uri");
        }else{
            Debug.info(TAG,"ApplicationInfo is null");
        }*/
        Debug.info(TAG,"初始化网络引擎");
        TempRemotAPIConfiguration.TempRemptAPIBuilder builder = new TempRemotAPIConfiguration.TempRemptAPIBuilder(baseUri);
        TempRemotAPIConfiguration configuration = new TempRemotAPIConfiguration(builder);
        TempRemotAPIConnecter.INSTANCE.init(configuration);
    }
}
