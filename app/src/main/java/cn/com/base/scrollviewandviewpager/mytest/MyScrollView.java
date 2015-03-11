package cn.com.base.scrollviewandviewpager.mytest;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

/**
 * Created by caipengcheng on 2015/2/28.
 */
public class MyScrollView extends ScrollView {

    /**
     * 透明View
     */
    private View transparentView;
    /**
     * 上面图片布局
     */
    private View topLayout;

    public MyScrollView(Context context) {
        this(context,null);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void setTransparentView(View transparentView) {
        this.transparentView = transparentView;
    }
    public void setTopLayout(View topLayout) {
        this.topLayout = topLayout;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (topLayout==null|| transparentView == null) {
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
