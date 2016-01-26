package com.example.dongja94.samplefragmentbackstack;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Fragment[] list = { new OneFragment(), new TwoFragment(), new ThreeFragment()};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new BaseFragment())
                    .commit();
        }

        Button btn = (Button)findViewById(R.id.btn_prev);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = getSupportFragmentManager().getBackStackEntryCount();
                if (count > 0) {
                    getSupportFragmentManager().popBackStack();
                }
            }
        });

        btn = (Button)findViewById(R.id.btn_next);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = getSupportFragmentManager().getBackStackEntryCount();
                if (count < list.length) {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.setCustomAnimations(R.anim.slide_left_in, R.anim.rotate_out, R.anim.slide_left_in, R.anim.rotate_out);
                    ft.replace(R.id.container, list[count]);
                    ft.addToBackStack("index:" + count);
                    ft.commit();
                } else {
                    getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }
            }
        });
    }

}
