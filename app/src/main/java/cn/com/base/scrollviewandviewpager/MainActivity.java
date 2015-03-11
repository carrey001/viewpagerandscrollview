package cn.com.base.scrollviewandviewpager;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import cn.com.base.scrollviewandviewpager.test.AutoScrollViewPager;
import cn.com.base.scrollviewandviewpager.test.ClickEventListener;
import cn.com.base.scrollviewandviewpager.test.TransparentScrollView;
import cn.com.base.scrollviewandviewpager.test.TransparentView;


public class MainActivity extends ActionBarActivity {

    private TransparentScrollView transparentScrollView;
    private TransparentView transparentView;
    private AutoScrollViewPager autoScrollViewPager;

    private View view;

    private int[] list ={ R.drawable.p1,R.drawable.p2,R.drawable.p3,R.drawable.p4};

    private ViewPager viewPager ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        autoScrollViewPager  = (AutoScrollViewPager) findViewById(R.id.viewpager);

        transparentScrollView = (TransparentScrollView) findViewById(R.id.scrollview);
        transparentView = (TransparentView) findViewById(R.id.eventtransparentview);
        view = findViewById(R.id.top_transport_view);
        transparentView.setScrollView(transparentScrollView);
        transparentView.setTopView(autoScrollViewPager);
        transparentScrollView.setTransparentView(transparentView);
        View topView = findViewById(R.id.view);
        transparentScrollView.setTopLayout(topView);
        transparentScrollView.setAutoScrollViewPager(autoScrollViewPager);
        transparentView.setClickEventListener(new ClickEventListener() {
            @Override
            public void click(MotionEvent ev) {
                autoScrollViewPager.onTouchEvent(ev);
            }
        });
//        ViewGroup.LayoutParams layoutParams = topView.getLayoutParams();
//
//        transparentView.getLayoutParams().height=layoutParams.height;
//        view.getLayoutParams().height=layoutParams.height;

        autoScrollViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return list.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object o) {
                return view==o;
            }

            @Override
            public int getItemPosition(Object object) {
                return super.getItemPosition(object);
            }

            @Override
            public Object instantiateItem(ViewGroup container, final int position) {

                ImageView view = new ImageView(MainActivity.this);
                view.setBackgroundResource(list[position]);
                container.addView(view);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this,"第"+position+"个被点击了",0).show();
                    }
                });
                return view;

            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });


    }

}
