package com.youngindians.Classes;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.youngindians.Fragments.HomeFragment;
import com.youngindians.Fragments.MemberFragment;
import com.youngindians.Fragments.dummy.DummyContent;
import com.youngindians.Models.User;
import com.youngindians.R;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity implements
        HomeFragment.OnHomeFragmentInteractionListener,
        MemberFragment.OnListFragmentInteractionListener {

    private TextView mTextMessage;
    private int prevStatusColor = 0;
    private int currentStatusColor = 0;

    private Toolbar toolbar;

    private ArrayList<Fragment> fragmentArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        createFragments();

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        final BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                currentStatusColor = bottomBar.getTabWithId(tabId).getBarColorWhenSelected();
                int position = bottomBar.findPositionForTabWithId(tabId);
                Log.d("frag", position+"");
                //String hexColor = String.format("#%06X", (0xFFFFFF & currentStatusColor));
                //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(hexColor)));
                changeActionBarColor(prevStatusColor, currentStatusColor);
                setFragment(position);
                Log.d("Color", bottomBar.getCurrentTab().getBarColorWhenSelected()+"");
                prevStatusColor = bottomBar.getTabWithId(tabId).getBarColorWhenSelected();

            }
        });

    }

    private void setFragment(int position) {
        Fragment f = fragmentArrayList.get(position);
        android.support.v4.app.FragmentTransaction transaction;
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, f);
        transaction.commit();
    }

    private void createFragments() {
        fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(HomeFragment.newInstance(1));
        fragmentArrayList.add(HomeFragment.newInstance(2));
        fragmentArrayList.add(MemberFragment.newInstance(1));
    }

    private void changeActionBarColor(int prevStatusColor, int currentStatusColor) {
        Integer colorFrom = prevStatusColor;
        Integer colorTo = currentStatusColor;
        Integer colorStatusFrom = prevStatusColor;
        Integer colorStatusTo = currentStatusColor;
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        ValueAnimator colorStatusAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorStatusFrom, colorStatusTo);

        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                toolbar.setBackgroundColor((Integer) animator.getAnimatedValue());
            }
        });

        colorStatusAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                Dashboard.this.getWindow().setStatusBarColor((Integer) animator.getAnimatedValue());
            }
        });

        colorAnimation.setDuration(300);
        colorAnimation.setStartDelay(0);
        colorAnimation.start();
        colorStatusAnimation.setDuration(300);
        colorStatusAnimation.setStartDelay(0);
        colorStatusAnimation.start();
    }

    @Override
    public void onHomeFragmentInteraction() {

    }

    @Override
    public void onListFragmentInteraction(User user) {

    }
}
