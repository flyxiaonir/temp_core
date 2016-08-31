package com.lf.tempcore.tempViews;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2016/5/9.
 */
public class TempSideSlipViewPager extends ViewPager{
    private boolean isPagingEnabled = true;
    private boolean scrollEnable=true;

    public TempSideSlipViewPager(Context context) {
        super(context);
    }

    public TempSideSlipViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



//    @Override
//    public void scrollTo(int x, int y) {
//        if (scrollEnable){
//            super.scrollTo(x, y);
//        }
//
//    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return this.isPagingEnabled && super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return this.isPagingEnabled && super.onInterceptTouchEvent(event);
    }

    public void setPagingEnabled(boolean b) {
        this.isPagingEnabled = b;
    }

//    public void setPagingEnabled(boolean b) {
//        this.isPagingEnabled = b;
//    }

    public boolean isScrollEnable() {
        return scrollEnable;
    }

//    public void setScrollEnable(boolean scrollEnable) {
//        this.scrollEnable = scrollEnable;
//    }
}
