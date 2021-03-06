package com.youtube.example;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Toast;

import com.youtube.engine.widget.MeowBottomNavigation;
import com.youtube.example.fragments.BerandaFragment;
import com.youtube.example.fragments.ExploreFragment;
import com.youtube.example.fragments.ChatFragment;
import com.youtube.example.fragments.NotificationFragment;
import com.youtube.example.fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    private final static int ID_HOME = 1;
    private final static int ID_EXPLORE = 2;
    private final static int ID_MESSAGE = 3;
    private final static int ID_NOTIFICATION = 4;
    private final static int ID_ACCOUNT = 5;
    private Toolbar mToolbar;
    private MeowBottomNavigation bottomNavigation;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
		mToolbar = (Toolbar)findViewById(R.id.toolbar);
		setSupportActionBar(mToolbar);

        bottomNavigation = findViewById(R.id.bottomNavigation);

        bottomNavigation.add(new MeowBottomNavigation.Model(ID_HOME, R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_EXPLORE, R.drawable.ic_explore));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_MESSAGE, R.drawable.ic_message));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_NOTIFICATION, R.drawable.ic_notification));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_ACCOUNT, R.drawable.ic_account));

        bottomNavigation.setCount(ID_NOTIFICATION, "115");

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                Toast.makeText(MainActivity.this, "clicked item : " + item.getId(), Toast.LENGTH_SHORT).show();
            }
        });

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Toast.makeText(MainActivity.this, "showing item : " + item.getId(), Toast.LENGTH_SHORT).show();

                String name;
                Fragment fragment;
                switch (item.getId()) {
                    case ID_HOME:
                        name = "HOME";
                        fragment = new BerandaFragment();
                        break;
                    case ID_EXPLORE:
                        name = "EXPLORE";
                        fragment = new ExploreFragment();
                        break;
                    case ID_MESSAGE:
                        name = "MESSAGE";
                        fragment = new ChatFragment();
                        break;
                    case ID_NOTIFICATION:
                        name = "NOTIFICATION";
                        fragment = new NotificationFragment();
                        break;
                    case ID_ACCOUNT:
                        name = "ACCOUNT";
                        fragment = new ProfileFragment();
                        break;
                    default:
                        name = "";
                        fragment = new NotificationFragment();
                }
                mToolbar.setSubtitle(name);
               // tvSelected.setText(getString(R.string.main_page_selected, name));
               showFragment(fragment);
            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                Toast.makeText(MainActivity.this, "reselected item : " + item.getId(), Toast.LENGTH_SHORT).show();
            }
        });

        bottomNavigation.setCount(ID_NOTIFICATION, "115");

        bottomNavigation.show(ID_NOTIFICATION,true);
  
    }


    public void showFragment(Fragment fragment)
    {
        getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.content_frame, fragment)
        .commit();
    }
}
