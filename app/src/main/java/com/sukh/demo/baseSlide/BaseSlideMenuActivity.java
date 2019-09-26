package com.sukh.demo.baseSlide;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.sukh.demo.R;


import me.tangke.slidemenu.SlideMenu;

/**
 * Created by sutanurath on 24/09/17.
 */

public class BaseSlideMenuActivity extends FragmentActivity {
    private SlideMenu mSlideMenu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slidemenu);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        mSlideMenu = findViewById(R.id.slideMenu);
    }

    public void setSlideRole(int res) {
        if (null == mSlideMenu) {
            return;
        }

        getLayoutInflater().inflate(res, mSlideMenu, true);
    }

    public SlideMenu getSlideMenu() {
        return mSlideMenu;
    }
}