package com.smhrd.js;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class fragB extends Fragment {

    private TextView fragb_tear_1, fragb_tear_2, fragb_tear_3, fragb_tear_4, fragb_tear_5,
            fragb_tv_1, fragb_tv_2, fragb_tv_3, fragb_tv_4, fragb_tv_5, my_team_name;

    private RequestQueue queue;
    private StringRequest stringRequest;
    private String member_lol_name1,member_lol_name2,member_lol_name3,member_lol_name4,member_lol_name5, team_name;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View fragment = inflater.inflate(R.layout.frag_b, container, false);
        fragb_tear_1 = fragment.findViewById(R.id.fragb_tear_1);
        fragb_tear_2 = fragment.findViewById(R.id.fragb_tear_2);
        fragb_tear_3 = fragment.findViewById(R.id.fragb_tear_3);
        fragb_tear_4 = fragment.findViewById(R.id.fragb_tear_4);
        fragb_tear_5 = fragment.findViewById(R.id.fragb_tear_5);

        fragb_tv_1 = fragment.findViewById(R.id.fragb_tv_1);
        fragb_tv_2 = fragment.findViewById(R.id.fragb_tv_2);
        fragb_tv_3 = fragment.findViewById(R.id.fragb_tv_3);
        fragb_tv_4 = fragment.findViewById(R.id.fragb_tv_4);
        fragb_tv_5 = fragment.findViewById(R.id.fragb_tv_5);

        my_team_name = fragment.findViewById(R.id.my_team_name);


        sendRequest();

        return fragment;
    }

    public void sendRequest() {
        queue = Volley.newRequestQueue(getContext());

        String url = "http://59.0.234.45:3100/Search";
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String check = jsonObject.getString("check");

                    if(check.equals("ok")){
                        member_lol_name1 = jsonObject.getString("member_lol_name1");
                        member_lol_name2 = jsonObject.getString("member_lol_name2");
                        member_lol_name3 = jsonObject.getString("member_lol_name3");
                        member_lol_name4 = jsonObject.getString("member_lol_name4");
                        member_lol_name5 = jsonObject.getString("member_lol_name5");
                        team_name = jsonObject.getString("team_name");
                        MyThread m = new MyThread();
                        m.start();
                    }else{

                        Toast.makeText(getContext(), "팀정보가 완벽하지 않습니다.", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Server 통신시 Error 발생하면 오는 곳
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parmas = new HashMap<String, String>();
                //Server로 데이터 보낼 시 넣어주는 곳

                String member = PreferenceManager.getString(getContext(),"login");
                try {
                    JSONObject jsonObject = new JSONObject(member);

                    String team_name = jsonObject.getString("team_name");
                    parmas.put("team_name", team_name);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return parmas;

            }
        };

        queue.add(stringRequest);


    }

    public class MyThread extends Thread{

        String url1 = "https://www.op.gg/summoner/userName="+member_lol_name1;//member_nickname
        String url2 = "https://www.op.gg/summoner/userName="+member_lol_name2;//member_nickname
        String url3 = "https://www.op.gg/summoner/userName="+member_lol_name3;//member_nickname
        String url4 = "https://www.op.gg/summoner/userName="+member_lol_name4;//member_nickname
        String url5 = "https://www.op.gg/summoner/userName="+member_lol_name5;//member_nickname
        @Override
        public void run() {
            Document doc = null;
            try {
                doc = Jsoup.connect(url1).get();
                Element el1 = doc.select(".TierRank").first();
                doc = Jsoup.connect(url2).get();
                Element el2 = doc.select(".TierRank").first();
                doc = Jsoup.connect(url3).get();
                Element el3 = doc.select(".TierRank").first();
                doc = Jsoup.connect(url4).get();
                Element el4 = doc.select(".TierRank").first();
                doc = Jsoup.connect(url5).get();
                Element el5 = doc.select(".TierRank").first();

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        fragb_tear_1.setText(el1.text());
                        fragb_tear_2.setText(el2.text());
                        fragb_tear_3.setText(el3.text());
                        fragb_tear_4.setText(el4.text());
                        fragb_tear_5.setText(el5.text());
                        fragb_tv_1.setText(member_lol_name1);
                        fragb_tv_2.setText(member_lol_name2);
                        fragb_tv_3.setText(member_lol_name3);
                        fragb_tv_4.setText(member_lol_name4);
                        fragb_tv_5.setText(member_lol_name5);
                        my_team_name.setText(team_name);
                    }
                });


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


}

