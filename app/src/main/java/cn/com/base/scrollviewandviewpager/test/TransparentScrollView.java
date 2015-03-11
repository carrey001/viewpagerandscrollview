package cn.com.base.scrollviewandviewpager.test;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

/**
 * Created by shangbluce on 15/2/5.
 */
public class TransparentScrollView extends ScrollView {

    /**
     * 透明View
     */
    private View transparentView;
    /**
     * 上面图片布局
     */
    private View topLayout;
    //是否滚动到底部
    private boolean isScrollToBottom = false;

    private ViewPager autoScrollViewPager;


    public TransparentScrollView(Context context) {
        this(context, null);
    }

    public TransparentScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TransparentScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setTopLayout(View topLayout) {
        this.topLayout = topLayout;
    }



    public void setAutoScrollViewPager(ViewPager autoScrollViewPager) {
        this.autoScrollViewPager = autoScrollViewPager;
    }

    public void setTransparentView(View transparentView) {
        this.transparentView = transparentView;
    }

    public View getTransparentView() {
        return transparentView;
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
//        if (isScrollToBottom && scrollOnBottom != null) {
//            if (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_CANCEL) {
//                scrollOnBottom.scrollToBotton(getScrollY() - getHeight());
//            }
//        }

        return super.onTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (t + getHeight() >= computeVerticalScrollRange()) {//滚动到底部
            isScrollToBottom = true;
        } else {
            isScrollToBottom = false;
        }

        if (topLayout == null || transparentView == null) {
            return;
        }

        int height = topLayout.getHeight();
        int scrollY = (int) getScrollY();
        int result = height - scrollY;
        result = result < 0 ? 0 : result;
        ViewGroup.LayoutParams layoutParams = transparentView.getLayoutParams();
        layoutParams.height = result;
        transparentView.setLayoutParams(layoutParams);

        if (scrollY > height)
            return;

        int halfScrollY = (int) (Math.ceil(scrollY) / 2);
        float percent = 0;
        percent = scrollY / (float) height;
        percent = percent > 0.8f ? 0.8f : percent;
        percent = percent < 0 ? 0 : percent;
        // Log.e("sss", ",height:" + height + ",scrollY:" + scrollY + "," + result + ",percent=" + percent);
        if (scrollY > 0) {
            topLayout.scrollTo(0, halfScrollY);

        }

        transparentView.setBackgroundColor(Color.argb((int) (percent * 255), 0, 0, 0));
    }


}
