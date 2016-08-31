package com.lf.tempcore.tempModule.tempRemotComm;

import android.util.Log;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by longf on 2016/8/26.
 */
public enum TempRemotAPIConnecter {
    INSTANCE;
    private Retrofit retrofit;
    private OkHttpClient client;
    private TempUserInfoAccessable userInfoAccessable = TempUserInfoAccessableImpl.getInstance();

    public void init(TempRemotAPIConfiguration configuration) {
        if (configuration == null) {
            throw new IllegalArgumentException("ERROR_INIT_CONFIGURATION_WITH_NULL");
        }
        if (client == null) {
            Log.i("TempRemotAPIConnecter","init");
            client = new OkHttpClient();
            // 用户验证
          /*  Interceptor interceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
//                Request newRequest = chain.request().newBuilder().addHeader("User-Agent", buildUserIdentify()).build();
                    Request newRequest = chain.request().newBuilder().build();
//                Request request = chain.request();
//                long t1 = System.nanoTime();
//                Debug.info(String.format("Sending request %s on %s%n%s",
//                        request.url(), chain.connection(), request.headers()));
//
//                Response response = chain.proceed(request);
//
//                long t2 = System.nanoTime();
//                Debug.info(String.format("Received response for %s in %.1fms%n%s",
//                        response.request().url(), (t2 - t1) / 1e6d, response.headers()));
                    return chain.proceed(newRequest);
                }
            };*/
//            client.interceptors().add(interceptor);

            retrofit = new Retrofit.Builder().baseUrl(configuration.getBaseUri()).
//        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).
        client(client).addConverterFactory(GsonConverterFactory.create()).
//        addCallAdapterFactory(RxJavaCallAdapterFactory.create()).
                            build();

//        requestBody = new MultipartBuilder().
//                type(MultipartBuilder.FORM).
//                addPart(
//                    Headers.of("Content-Disposition", "form-data; name=\"mycustomfile.png\""),
//                    RequestBody.create(MEDIA_TYPE_PNG, file))
//            .build();
        }

    }

    public OkHttpClient getClient() {
        return client;
    }


    public Retrofit getRetrofit() {
        return retrofit;
    }


    /**
     * 构造用户认证的头信息
     * @return
     */
    private  String buildUserIdentify() {
        final String localUserIdentify = userInfoAccessable.getLocalUserIdentity();
        final Long userId = userInfoAccessable.getUserId();
        final String username = userInfoAccessable.getUsername();
        final String encryptPwd = userInfoAccessable.getEncryptPassword();
//        if (StringUtils.isNotBlank(localUserIdentify)) {
//            return localUserIdentify;
//        } else {
//        Debug.info("构造用户认证的头信息="+username + "|" + userId + "|" + encryptPwd + "|" + localUserIdentify);
        return username + "|" + userId + "|" + encryptPwd + "|" + localUserIdentify;
//        }

    }
    public  <T> void  executeRemotAPI(Call<T> call, retrofit2.Callback<T> callback){
        call.enqueue(callback);
    }
    /*public  <API,T> void  executeRemotAPI(Class<API> clazz,Call<T> call, retrofit2.Callback<T> callback){
        call.enqueue(callback);
    }*/
    /**
     * 构造远程接口对象
     *
     * @param clazz 接口的interface class
     * @param <API>
     * @return
     */
    public  <API> API createRemoteApi(Class<API> clazz) {
        return retrofit.create(clazz);
    }
   /* public <T> void executeMethod(Observable<T> observable) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Subscriber<T>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(T o) {
//                        Toast.makeText(context.this, o., Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public  <T> void executeMethod(Observable<T> observable, final OnCallBack<T> callback) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Subscriber<T>() {
                    @Override
                    public void onCompleted() {
                        if (callback != null) {
                            callback.onCompleted();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if (callback != null) {
                            callback.onError(e);
                        }
                    }

                    @Override
                    public void onNext(T o) {
                        if (callback != null) {
                            callback.onSucceed(o);
                        }
                    }
                });
    }

    public interface OnCallBack<T> {
        void onSucceed(T data);

        void onCompleted();

        void onError(Throwable e);


    }
    interface OnProgressCallBack<T> extends OnCallBack<T>{
        void onLoading(long total, long current, boolean isUploading);
    }*/
}
