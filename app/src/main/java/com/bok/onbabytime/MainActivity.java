package com.bok.onbabytime; // 실제 패키지 이름과 일치하는지 확인

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout; // 필요시
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView; // 하단 내비게이션 바가 있다면

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private BottomNavigationView bottomNavigationView; // 하단 내비게이션 바가 있다면

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab); // MainActivity의 레이아웃 파일로 설정

        // 1. Toolbar 설정
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); // 이 액티비티의 액션 바로 설정

        // 2. DrawerLayout 참조
        drawerLayout = findViewById(R.id.drawer_layout);

        // 3. NavigationView 참조 및 리스너 설정
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this); // 이 액티비티에서 메뉴 항목 클릭 처리

        // 4. ActionBarDrawerToggle 설정 (햄버거 아이콘과 서랍 연동)
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, // string.xml에 정의할 문자열
                R.string.navigation_drawer_close // string.xml에 정의할 문자열
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState(); // 햄버거 아이콘 상태 동기화

        // TODO: 기존 MainActivity의 하단 내비게이션 바 및 프래그먼트 관련 초기화 코드 여기에 추가
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            // 기존 bottom_nav_menu.xml의 ID에 따라 프래그먼트 전환 로직 구현
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) { // bottom_nav_menu.xml의 ID
                // 홈 프래그먼트 로드
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                return true;
            } else if (itemId == R.id.navigation_dashboard) { // bottom_nav_menu.xml의 ID
                // 대시보드 프래그먼트 로드 (가정)
                // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DashboardFragment()).commit();
                return true;
            }
            return false;
        });

        // 앱 시작 시 기본적으로 홈 프래그먼트 로드
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home); // 서랍 메뉴에서 홈 항목 선택 표시
        }
    }

    // 뒤로 가기 버튼 눌렀을 때 서랍이 열려있으면 닫기
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    // NavigationView (서랍 메뉴) 항목 클릭 처리
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // 홈 프래그먼트 로드
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        } else if (id == R.id.nav_feedings) {
            // 수유 기록 프래그먼트 로드 (새로운 프래그먼트가 필요)
            // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FeedingsFragment()).commit();
            // TODO: FeedingsFragment.java 등 새로운 프래그먼트 생성 필요
        } else if (id == R.id.nav_diapers) {
            // 기저귀 기록 프래그먼트 로드 (새로운 프래그먼트가 필요)
            // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DiapersFragment()).commit();
        } else if (id == R.id.nav_sleeps) {
            // 수면 기록 프래그먼트 로드 (새로운 프래그먼트가 필요)
            // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SleepsFragment()).commit();
        } else if (id == R.id.nav_settings) {
            // 설정 화면 또는 프래그먼트 로드
        } else if (id == R.id.nav_about) {
            // 정보 화면 또는 프래그먼트 로드
        }

        drawerLayout.closeDrawer(GravityCompat.START); // 메뉴 클릭 후 서랍 닫기
        return true;
    }
}