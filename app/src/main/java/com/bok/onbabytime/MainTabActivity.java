package com.bok.onbabytime;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainTabActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);

        BottomNavigationView nav = findViewById(R.id.bottom_navigation);
        nav.setOnItemSelectedListener(item -> {
            Fragment fragment;

            if (item.getItemId() == R.id.nav_home) {
                fragment = new HomeFragment();
            } else {
                fragment = new HomeFragment(); // 또는 나중에 다른 탭 추가
            }
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment).commit();
            return true;
        });

        nav.setSelectedItemId(R.id.nav_home);
    }
}
