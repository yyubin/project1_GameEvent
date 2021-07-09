package com.smhrd.js;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class FragmentA extends Fragment {

    private ImageView img_mypage_me,img_mypage_group;

    private int user = R.drawable.user;
    private int user1 = R.drawable.user1;
    private int group = R.drawable.group;
    private int group1 = R.drawable.group2;
    private View view_mypage_me,view_mypage_group;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View fragment = inflater.inflate(R.layout.activity_mypage, container, false);
        getChildFragmentManager().beginTransaction().replace(R.id.child_fragment, new fragA()).commit();

        img_mypage_group=fragment.findViewById(R.id.img_mypage_group);
        img_mypage_me=fragment.findViewById(R.id.img_mypage_me);
        view_mypage_me=fragment.findViewById(R.id.view_mypage_me);
        view_mypage_group=fragment.findViewById(R.id.view_mypage_group);

        img_mypage_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getChildFragmentManager().beginTransaction().replace(R.id.child_fragment, new fragB()).commit();
                img_mypage_group.setImageResource(group1);
                img_mypage_me.setImageResource(user);
                view_mypage_group.setBackgroundColor(Color.BLACK);
                view_mypage_me.setBackgroundColor(Color.parseColor("#C3BEBE"));
            }
        });

        img_mypage_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getChildFragmentManager().beginTransaction().replace(R.id.child_fragment, new fragA()).commit();
                img_mypage_group.setImageResource(group);
                img_mypage_me.setImageResource(user1);
                view_mypage_group.setBackgroundColor(Color.parseColor("#C3BEBE"));
                view_mypage_me.setBackgroundColor(Color.BLACK);
            }
        });

        return fragment;
    }


}
