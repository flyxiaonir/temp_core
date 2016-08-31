package com.lf.tempcore.tempEnum;

/**
 * Created by longf on 2016/7/22.
 */
public enum TempErrorCode {
    ERROR_FAILED(0),
    ERROR_SUCCEED(200),
    ERROR_NET_DISCONTECTED(-1),
    ERROR_404(404),
    ERROR_500(500);
    private int code;
    TempErrorCode(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }
    public TempErrorCode getTempErrorCode(int code){
        switch (code) {
            case 200:
                return ERROR_SUCCEED;
            case 404 :
                return ERROR_404;
            case 500 :
                return ERROR_500;
            case -1 :
                return ERROR_NET_DISCONTECTED;
            case 0 :
                return ERROR_FAILED;

        }
        return null;
    }
}
