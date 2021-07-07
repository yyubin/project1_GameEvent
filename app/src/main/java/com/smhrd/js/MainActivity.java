package com.smhrd.js;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView navi;

    private FragmentA fragmentA;
    private FragmentB fragmentB;
    private FragmentC fragmentC;

    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navi = findViewById(R.id.navi);

        fragmentA = new FragmentA();
        fragmentB = new FragmentB();
        fragmentC = new FragmentC();

        getSupportFragmentManager().beginTransaction().replace(R.id.frag, fragmentA).commit();
        //fragment를 총괄 관리해주는 매니저

        navi.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int selectItem = item.getItemId();
                if (selectItem==R.id.page1){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frag, fragmentA).commit();
                }else if (selectItem==R.id.page2){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frag, fragmentB).commit();
                }else if (selectItem==R.id.page3){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frag, fragmentC).commit();
                }
                return false;
            }
        });
    }

    public void setBundle(Bundle bundle){
        this.bundle = bundle;
    }
    public Bundle getBundle(){
        return bundle;
    }
}