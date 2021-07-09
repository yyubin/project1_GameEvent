package com.smhrd.js;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.json.JSONException;
import org.json.JSONObject;

public class fragA extends Fragment {

    private TextView member_name, member_lol_name, member_phone, member_email;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View fragment = inflater.inflate(R.layout.frag_a, container, false);

        member_name=fragment.findViewById(R.id.member_name);
        member_lol_name=fragment.findViewById(R.id.member_lol_name);
        member_email=fragment.findViewById(R.id.member_email);
        member_phone=fragment.findViewById(R.id.member_phone);

        member_email.setText("a");




        String member = PreferenceManager.getString(getContext(),"login");
        try {

            JSONObject jsonObject = new JSONObject(member);

            Log.v("json", String.valueOf(jsonObject));

            String name = jsonObject.getString("name");
            String lol_name = jsonObject.getString("lol_name");
            String phone = jsonObject.getString("tel");
            String email = jsonObject.getString("email");

            member_lol_name.setText(lol_name);
            member_name.setText(name);
            member_phone.setText(phone);
            member_email.setText(email);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return fragment;
    }
}
