package com.sukh.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.eightbitlab.bottomnavigationbar.BottomBarItem;
import com.eightbitlab.bottomnavigationbar.BottomNavigationBar;
import com.sukh.demo.baseSlide.BaseSlideMenuActivity;
import com.sukh.demo.fragment.HomeFragment;

import me.tangke.slidemenu.SlideMenu;

public class MainActivity extends BaseSlideMenuActivity {

    FrameLayout container ;
    BottomNavigationBar navigation_bar ;
    SlideMenu mSlideMenu ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSlideRole(R.layout.activity_main);
        setSlideRole(R.layout.menu);

        mSlideMenu = getSlideMenu();

        mSlideMenu.setPrimaryShadowWidth(30);
        // mSlideMenu.setSlideMode(SlideMenu.MODE_SLIDE_WINDOW);
        mSlideMenu.setEdgeSlideEnable(true);

        findViewById() ;

        initNavigation() ;

        loadFragment(new HomeFragment() , true , "HomeFragment") ;



    }

    private void findViewById() {

        container = findViewById(R.id.container) ;
        navigation_bar = findViewById(R.id.navigation_bar) ;


    }


    void  initNavigation(){


        BottomBarItem item1 = new BottomBarItem(R.drawable.ic_launcher_background ,R.string.home ) ;
        BottomBarItem item2 = new BottomBarItem(R.drawable.ic_launcher_background , R.string.tab1);
        BottomBarItem item3 = new BottomBarItem(R.drawable.ic_launcher_background , R.string.tab2);
        BottomBarItem item4 = new BottomBarItem(R.drawable.ic_launcher_background , R.string.tab3) ;


        navigation_bar.addTab(item1);
        navigation_bar.addTab(item2);
        navigation_bar.addTab(item3);
        navigation_bar.addTab(item4);
        // navigation_bar.addTab(item5);



        navigation_bar.setOnReselectListener(new BottomNavigationBar.OnReselectListener() {
            @Override
            public void onReselect(int position) {

                Toast.makeText(MainActivity.this, "Comming soon...", Toast.LENGTH_SHORT).show();





            }
        });

        navigation_bar.setOnSelectListener(new BottomNavigationBar.OnSelectListener() {
            @Override
            public void onSelect(int position) {
                Log.e("selectewd position",position+"");


                Toast.makeText(MainActivity.this, "Comming soon...", Toast.LENGTH_SHORT).show();

            }
        });


    }




    public void loadFragment(Fragment fragment ,  boolean backstak , String tag) {


        String backStateName = fragment.getClass().getName();
        FragmentManager fm = getSupportFragmentManager();
        boolean fragmentPopped = fm.popBackStackImmediate(backStateName, 0);
        if (!fragmentPopped) { //fragment not in back stack, create it.
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.container, fragment , tag);
            ft.addToBackStack(backStateName);

            ft.commit();
        }
    }



}
