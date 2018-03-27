package com.android.sam.universal_interface;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import struct.FunctionManager;
import struct.FunctionWithParamNoResult;

/**
 * Created by Sam on 2018-03-27.
 * E-mail:729717222@qq.com
 */

public class MainActivity extends AppCompatActivity {
    public static final String FUNC_SHOW_TOAST = "show_toast";

    private final static String TAG = "main";

    private List<Fragment> fragmentList = new ArrayList<>();
    private ViewPager viewPager;
    private FragmentPagerAdapter adapter;

    private Button btnUpdate1;
    private Button btnUpdate2;
    private Button btnUpdate3;
    private Button btnUpdate4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnUpdate1 = findViewById(R.id.btn_update1);
        btnUpdate2 = findViewById(R.id.btn_update2);
        btnUpdate3 = findViewById(R.id.btn_update3);
        btnUpdate4 = findViewById(R.id.btn_update4);

        btnUpdate1.setOnClickListener(clickListener);
        btnUpdate2.setOnClickListener(clickListener);
        btnUpdate3.setOnClickListener(clickListener);
        btnUpdate4.setOnClickListener(clickListener);

        fragmentList.add(new Fragment1());
        fragmentList.add(new Fragment2());
        fragmentList.add(new Fragment3());
        fragmentList.add(new Fragment4());

        adapter = new FragmentPagerAdapter(getSupportFragmentManager(),
                fragmentList);

        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(adapter);

        FunctionManager.getInstance().addFunction(showToast);
    }

    //activity invoke fragment method.
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_update1:
                    FunctionManager.getInstance().invokeFunction(Fragment1.INTERFACE_UPDATE_CONTENT,
                            "MainActivity call fragment1: "+new Date().toLocaleString());
                    break;

                case R.id.btn_update2:
                    FunctionManager.getInstance().invokeFunction(Fragment2.INTERFACE_UPDATE_CONTENT,
                            "MainActivity call fragment2: "+new Date().toLocaleString());
                    break;

                case R.id.btn_update3:
                    FunctionManager.getInstance().invokeFunction(Fragment3.INTERFACE_UPDATE_CONTENT,
                            "MainActivity call fragment3: "+new Date().toLocaleString());
                    break;

                case R.id.btn_update4:
                    FunctionManager.getInstance().invokeFunction(Fragment4.INTERFACE_UPDATE_CONTENT,
                            "MainActivity call fragment4: "+new Date().toLocaleString());
                    break;
                default: break;
            }
        }
    };

    @Override
    protected void onPause() {
        super.onPause();

        if (isFinishing()){
            FunctionManager.getInstance().removeAllFunctions();
        }
    }

    //fragment invoke activity's method.
    private FunctionWithParamNoResult<String> showToast = new FunctionWithParamNoResult<String>(FUNC_SHOW_TOAST) {
        @Override
        public void function(String s) {
            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
        }
    };
}
