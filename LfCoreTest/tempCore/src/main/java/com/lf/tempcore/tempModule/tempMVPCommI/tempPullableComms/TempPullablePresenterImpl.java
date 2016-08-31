package com.lf.tempcore.tempModule.tempMVPCommI.tempPullableComms;

import com.lf.tempcore.tempModule.tempRemotComm.TempRemotAPIConnecter;
import com.lf.tempcore.tempResponse.TempResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by longf on 2016/5/6.
 */
public abstract class TempPullablePresenterImpl<RESPONSE extends TempResponse> implements TempPullablePresenterI {
    private int currentPage=0;
    private int size=10;
    private final int INIT =0,REFRESH=1,LOADMORE=2;
    private TempPullableViewI<RESPONSE> mView;
    public TempPullablePresenterImpl(TempPullableViewI v){
        this.mView =v;
    }
    @Override
    public void requestInit() {
        currentPage=0;
        queryData(INIT);
    }

    @Override
    public void requestRefresh() {
        currentPage=0;
        queryData(REFRESH);
    }

    @Override
    public void requestLoadmore() {
        queryData(LOADMORE);
    }

    private void queryData(final int status){
        if (mView!=null) {
            mView.showPullableProgressDialog();
        }
        TempRemotAPIConnecter.INSTANCE.executeRemotAPI(createObservable(getQueryPage(), getSize(), getCurrentPage()), new Callback<RESPONSE>() {
            @Override
            public void onResponse(Call<RESPONSE> call, Response<RESPONSE> response) {
                if (mView!=null&&response.body().getFlag()==1) {
                    switch (status) {
                        case INIT:
                            currentPage++;
//                            setCurrentPage();
                            mView.onInit(response.body());
                            break;
                        case REFRESH:
                            currentPage++;
                            mView.refreshStatus(true);
                            mView.onRefresh(response.body());
                            break;
                        case LOADMORE:
                            currentPage++;
                            mView.loadMoreStatus(true);
                            mView.onLoadmore(response.body());
                            break;
                    }

                }
            }

            @Override
            public void onFailure(Call<RESPONSE> call, Throwable t) {

            }
        } /*new TempRemoteApiDao.OnCallBack<RESPONSE>() {
            @Override
            public void onSucceed(RESPONSE data) {
                if (mView!=null&&data.getFlag()==1) {
                    switch (status) {
                        case INIT:
                            currentPage++;
//                            setCurrentPage();
                            mView.onInit(data);
                            break;
                        case REFRESH:
                            currentPage++;
                            mView.refreshStatus(true);
                            mView.onRefresh(data);
                            break;
                        case LOADMORE:
                            currentPage++;
                            mView.loadMoreStatus(true);
                            mView.onLoadmore(data);
                            break;
                    }

                }
            }

            @Override
            public void onCompleted() {
                if (mView!=null) {
                    mView.dismissPullableProgressDialog();

                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView!=null) {
                    mView.dismissPullableProgressDialog();
//                    mView.showConnectError();
                    switch (status) {
                        case INIT:
                            break;
                        case REFRESH:
                            mView.refreshStatus(false);
                            break;
                        case LOADMORE:
                            mView.loadMoreStatus(false);
                            break;
                    }
                }
            }
        }*/);
    }
//    public abstract Observable<RESPONSE> createObservable(int queryPage, int querysize, int currentPage) ;
    public abstract Call<RESPONSE> createObservable(int queryPage, int querysize, int currentPage) ;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getQueryPage() {
        return currentPage+1;
    }
    public int getCurrentPage() {
        return this.currentPage ;
    }
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
