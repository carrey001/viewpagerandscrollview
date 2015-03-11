//package cn.com.base.scrollviewandviewpager;
//
//import android.content.Context;
//import android.os.Build;
//import android.util.AttributeSet;
//import android.view.GestureDetector;
//import android.view.MotionEvent;
//import android.view.VelocityTracker;
//import android.widget.ScrollView;
//import android.widget.Scroller;
//
//import com.lidroid.xutils.util.LogUtils;
//import com.yiwang.adapter.ImagePagerAdapter;
//import com.yiwang.widget.autoscrollviewpager.AutoScrollViewPager;
//
///**
// * Created by caipengcheng on 2015/2/2.
// */
//public class ProductAutoScrollViewPager extends AutoScrollViewPager {
//    //private OnTouchListener touchListener = new OnTouchListener() {
//    float oldY, oldX;
//    float curY, curX;
//    private Scroller mScroller;
//    /**
//     * 用于计算手滑动的速度
//     */
//    private VelocityTracker mVelocityTracker;
//    private int mMinimumVelocity;
//    private int mMaximumVelocity;
//    private ScrollView scrollContent;
//    /**
//     * 用于计算手滑动的速度
//     */
//    private VelocityTracker vt;
//    private float lastY = 0.0f;
//    private float lastX = 0.0f;
//    private float distanceX, distanceY;
//    private boolean isOut = false;
//    /**
//     * 上下滑动
//     */
//    private boolean scrollVertical = false;
//    private boolean actionDown = false;
//    private GestureDetector gestureDetector;
//
//    public ProductAutoScrollViewPager(Context paramContext) {
//        super(paramContext);
//        init(paramContext);
//    }
//
//    public ProductAutoScrollViewPager(Context paramContext, AttributeSet paramAttributeSet) {
//        super(paramContext, paramAttributeSet);
//        init(paramContext);
//    }
//
//    public void setScrollViewContainer(ScrollView viewContainer) {
//        this.scrollContent = viewContainer;
//    }
//
//    private void init(Context context) {
//        //mScroller = new Scroller(context);
//        if (Build.VERSION.SDK_INT >= 9) {
//            setOverScrollMode(OVER_SCROLL_NEVER);
//        }
//        gestureDetector = new GestureDetector(getContext(), new MySimpleGestureDetector());
//        //final ViewConfiguration configuration = ViewConfiguration.get(context);
//        //mMinimumVelocity = configuration.getScaledMinimumFlingVelocity();
//        //mMaximumVelocity = configuration.getScaledMaximumFlingVelocity();
//    }
//
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent event) {
//        gestureDetector.onTouchEvent(event);
//        ImagePagerAdapter imagePagerAdapter = (ImagePagerAdapter) getAdapter();
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                imagePagerAdapter.setClickable(true);
//                break;
//            case MotionEvent.ACTION_CANCEL:
//                imagePagerAdapter.setClickable(true);
//                break;
//            case MotionEvent.ACTION_MOVE:
////                imagePagerAdapter.setClickable(false);
////                LogUtils.i("set clickable :" + false);
//                break;
//        }
//        return super.dispatchTouchEvent(event);
////        Log.e("sss", "actionDown:" + actionDown + ",scrollVertical = " + scrollVertical + ",action:" + event.getAction());
////        initVelocityTrackerIfNotExists();
////        switch (event.getAction()) {
////            case MotionEvent.ACTION_DOWN:
////                scrollVertical = false;
////                actionDown = true;
////                mScroller.isFinished();
////                initOrResetVelocityTracker();
////                mVelocityTracker.addMovement(event);
////                curY = oldY = event.getY();
////                curX = oldX = event.getX();
////                break;
////            case MotionEvent.ACTION_MOVE:
////                initVelocityTrackerIfNotExists();
////                mVelocityTracker.addMovement(event);
////                curY = event.getY();
////                curX = event.getX();
////                float absX = Math.abs(curX - oldX);
////                if (actionDown && absX > 0) {
////
////                    if (Math.abs(curX - oldX) >= Math.abs(curY - oldY)) {
////                        scrollVertical = false;
////                    } else {
////                        scrollVertical = true;
////                    }
////                    actionDown = false;
////                }
////                if (scrollVertical && scrollContent != null) {
////                    int scrollY = (int) (curY - oldY);
////                    scrollContent.scrollBy(0, -scrollY);
////                }
////                oldY = curY;
////
////                break;
////            case MotionEvent.ACTION_CANCEL:
////            case MotionEvent.ACTION_UP:
////
////                mVelocityTracker.computeCurrentVelocity(500, mMaximumVelocity);
////                // 获取Y方向的速度
////                float yVelocity = mVelocityTracker.getYVelocity();
////                if (scrollVertical) {
////                    scrollContent.fling((int) -yVelocity);
////                }
////                actionDown = false;
////                scrollVertical = false;
////                break;
////            default:
////                actionDown = false;
////                break;
////        }
////        if (scrollVertical) {
////            return false;
////        } else {
////            return super.dispatchTouchEvent(event);
////        }
//    }
//
//    private void initOrResetVelocityTracker() {
//        if (mVelocityTracker == null) {
//            mVelocityTracker = VelocityTracker.obtain();
//        } else {
//            mVelocityTracker.clear();
//        }
//    }
//
//    private void initVelocityTrackerIfNotExists() {
//        if (mVelocityTracker == null) {
//            mVelocityTracker = VelocityTracker.obtain();
//        }
//    }
//
//    private void recycleVelocityTracker() {
//        if (mVelocityTracker != null) {
//            mVelocityTracker.recycle();
//            mVelocityTracker = null;
//        }
//    }
//
//    class MySimpleGestureDetector extends GestureDetector.SimpleOnGestureListener {
//        private int lastDistance;
//
//        @Override
//        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//            if (Math.abs(distanceX) < Math.abs(distanceY)) {
//                LogUtils.i("distanceY: " + distanceY + "  realY: " + (int) Math.ceil(distanceY));
//                if (lastDistance != 0) {
//                    distanceY += lastDistance;
//                }
//                lastDistance = (int) Math.ceil(distanceY);
//                if (scrollContent != null) {
//                //    scrollContent.smoothScrollBy(0, (int) Math.ceil(distanceY));
//                }
//                ImagePagerAdapter imagePagerAdapter = (ImagePagerAdapter) getAdapter();
//                imagePagerAdapter.setClickable(false);
//                LogUtils.i("set clickable :" + false);
//            }
////            return Math.abs(distanceX) < Math.abs(distanceY);
//            return true;
//        }
//
//        @Override
//        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//            if (scrollContent != null) {
//              //  scrollContent.fling((int) -velocityY);
//            }
//            return true;
//        }
//
//        @Override
//        public boolean onDown(MotionEvent e) {
//            lastDistance = 0;
//            return super.onDown(e);
//        }
//    }
//}
