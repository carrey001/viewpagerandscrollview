package cn.com.base.scrollviewandviewpager.test;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;


public class TransparentView extends FrameLayout {

    private ViewGroup topView;
    private ViewGroup scrollView;

    private ClickEventListener clickEventListener;

    public TransparentView(Context context) {
        this(context, null);
    }

    public TransparentView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TransparentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setTopView(ViewGroup topView) {
        this.topView = topView;
    }

    public void setScrollView(ViewGroup scrollView) {
        this.scrollView = scrollView;
    }


    public void setClickEventListener(ClickEventListener clickEventListener) {
        this.clickEventListener = clickEventListener;
    }
    /**
     * 滚动状态3种:  -1上下滚动；0还未区分；1左右滚动
     */
    private int scrollType = 0;
    private float downX, downY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //Log.e("sss","TransparentView（0000000000） dispatchTouchEvent scrollType="+scrollType+" action="+ev.getAction()+" top="+(topView == null));
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            scrollType = 0;
            downX = ev.getX();
            downY = ev.getY();
        } else if (scrollType == 0 && ev.getAction() == MotionEvent.ACTION_MOVE) {
            float distanceX = Math.abs(ev.getX() - downX);
            float distanceY = Math.abs(ev.getY() - downY);
            if (distanceX > distanceY) {
                scrollType = 1;
            } else if (distanceX < distanceY) {
                scrollType = -1;
            }
        }


        switch (scrollType) {
            case 0:
                if(topView != null){
                    topView.dispatchTouchEvent(ev);
                }
                if(scrollView != null){
                    scrollView.dispatchTouchEvent(ev);
                }
                break;
            case -1:
                if(scrollView != null){
                    scrollView.dispatchTouchEvent(ev);
                }
                break;
            case 1:
                if(topView != null){
                    topView.dispatchTouchEvent(ev);
                }
                break;
        }
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
      //  Log.e("sss","TransparentView （1111111111111）onInterceptTouchEvent scrollType="+scrollType+" action="+ev.getAction()+" top="+(topView == null));
        switch (scrollType) {
            case 0:
                if(topView != null){
                    topView.dispatchTouchEvent(ev);
                }
                if(scrollView != null){
                    scrollView.dispatchTouchEvent(ev);
                }
                break;
            case -1:
                if(scrollView != null){
                    scrollView.dispatchTouchEvent(ev);
                }
                break;
            case 1:
                if(topView != null){
                    topView.dispatchTouchEvent(ev);
                }
                break;
        }
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(clickEventListener!=null){
            clickEventListener.click(ev);
        }
        switch (scrollType) {
            case 0:
                if(topView != null){
                    topView.dispatchTouchEvent(ev);
                }
                if(scrollView != null){
                   scrollView.dispatchTouchEvent(ev);
                }
                break;
            case -1:
                if(scrollView != null){
                    scrollView.dispatchTouchEvent(ev);
                }
                break;
            case 1:
                if(topView != null){
                    topView.dispatchTouchEvent(ev);
                }
                break;
        }
        return true;
    }
}
