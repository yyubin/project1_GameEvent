package com.smhrd.js;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

//액티비티에서 프레그먼트a를 실행하는 순간 연동됨.
public class FragmentA extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View fragment = inflater.inflate(R.layout.activity_join, container, false);

        return fragment;
    }
}
