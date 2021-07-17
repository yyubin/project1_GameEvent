package com.smhrd.js;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
            fragb_tv_1, fragb_tv_2, fragb_tv_3, fragb_tv_4, fragb_tv_5, my_team_name,
            tv_score;

    private RequestQueue queue;
    private StringRequest stringRequest;
    private String member_lol_name1,member_lol_name2,member_lol_name3,member_lol_name4,member_lol_name5, team_name;

    int sum1 = 0;
    int sum2 = 0;
    int sum3 = 0;
    int sum4 = 0;
    int sum5 = 0;
    int sum = 0;
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

        tv_score = fragment.findViewById(R.id.tv_score);

        my_team_name = fragment.findViewById(R.id.my_team_name);

        sendRequest();

        Intent intent = new Intent(getContext(), battle_attend.class);
        intent.putExtra("score", sum);

        return fragment;
    }

    public void sendRequest() {
        queue = Volley.newRequestQueue(getContext());

        String url = "http://121.147.52.82:3100/Search";
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

                if(el1.text().equals("Diamond 1") || el1.text().equals("Diamond 2")){
                    sum1 = 36;
                }else if(el1.text().equals("Diamond 3") || el1.text().equals("Diamond 4") || el1.text().equals("Platinum 1") || el1.text().equals("Platinum 2")){
                    sum1 = 26;
                }else if(el1.text().equals("Platinum 3") || el1.text().equals("Platinum 4") || el1.text().equals("Gold 1") || el1.text().equals("Gold 2")){
                    sum1 = 22;
                }else if(el1.text().equals("Gold 3") || el1.text().equals("Gold 4") || el1.text().equals("Silver 1") || el1.text().equals("Silver 2")){
                    sum1 = 17;
                }else if(el1.text().equals("silver 3") || el1.text().equals("silver 4") || el1.text().equals("Bronze 1") || el1.text().equals("Bronze 2")){
                    sum1 = 10;
                }else if(el1.text().equals("Bronze 3") || el1.text().equals("Bronze 4") || el1.text().equals("Iron 1") || el1.text().equals("Iron 2")){
                    sum1 = 1;
                }else if(el1.text().equals("Unranked")){
                    sum1 = 0;
                }
                if(el2.text().equals("Diamond 1") || el2.text().equals("Diamond 2")){
                    sum2 = 36;
                }else if(el2.text().equals("Diamond 3") || el2.text().equals("Diamond 4") || el2.text().equals("Platinum 1") || el2.text().equals("Platinum 2")){
                    sum2 = 26;
                }else if(el2.text().equals("Platinum 3") || el2.text().equals("Platinum 4") || el2.text().equals("Gold 1") || el2.text().equals("Gold 2")){
                    sum2 = 22;
                }else if(el2.text().equals("Gold 3") || el2.text().equals("Gold 4") || el2.text().equals("Silver 1") || el2.text().equals("Silver 2")){
                    sum2 = 17;
                }else if(el2.text().equals("silver 3") || el2.text().equals("silver 4") || el2.text().equals("Bronze 1") || el2.text().equals("Bronze 2")){
                    sum2 = 10;
                }else if(el2.text().equals("Bronze 3") || el2.text().equals("Bronze 4") || el2.text().equals("Iron 1") || el2.text().equals("Iron 2")){
                    sum2 = 1;
                }else if(el2.text().equals("Unranked")){
                    sum2 = 0;
                }
                if(el3.text().equals("Diamond 1") || el3.text().equals("Diamond 2")){
                    sum3 = 36;
                }else if(el3.text().equals("Diamond 3") || el3.text().equals("Diamond 4") || el3.text().equals("Platinum 1") || el3.text().equals("Platinum 2")){
                    sum3 = 26;
                }else if(el3.text().equals("Platinum 3") || el3.text().equals("Platinum 4") || el3.text().equals("Gold 1") || el3.text().equals("Gold 2")){
                    sum3 = 22;
                }else if(el3.text().equals("Gold 3") || el3.text().equals("Gold 4") || el3.text().equals("Silver 1") || el3.text().equals("Silver 2")){
                    sum3 = 17;
                }else if(el3.text().equals("silver 3") || el3.text().equals("silver 4") || el3.text().equals("Bronze 1") || el3.text().equals("Bronze 2")){
                    sum3 = 10;
                }else if(el3.text().equals("Bronze 3") || el3.text().equals("Bronze 4") || el3.text().equals("Iron 1") || el3.text().equals("Iron 2")){
                    sum3 = 1;
                }else if(el3.text().equals("Unranked")){
                    sum3 = 0;
                }
                if(el4.text().equals("Diamond 1") || el4.text().equals("Diamond 2")){
                    sum4 = 36;
                }else if(el4.text().equals("Diamond 3") || el4.text().equals("Diamond 4") || el4.text().equals("Platinum 1") || el4.text().equals("Platinum 2")){
                    sum4 = 26;
                }else if(el4.text().equals("Platinum 3") || el4.text().equals("Platinum 4") || el4.text().equals("Gold 1") || el4.text().equals("Gold 2")){
                    sum4 = 22;
                }else if(el4.text().equals("Gold 3") || el4.text().equals("Gold 4") || el4.text().equals("Silver 1") || el4.text().equals("Silver 2")){
                    sum4 = 17;
                }else if(el4.text().equals("silver 3") || el4.text().equals("silver 4") || el4.text().equals("Bronze 1") || el4.text().equals("Bronze 2")){
                    sum4 = 10;
                }else if(el4.text().equals("Bronze 3") || el4.text().equals("Bronze 4") || el4.text().equals("Iron 1") || el4.text().equals("Iron 2")){
                    sum4 = 1;
                }else if(el4.text().equals("Unranked")){
                    sum4 = 0;
                }
                if(el5.text().equals("Diamond 1") || el5.text().equals("Diamond 2")){
                    sum5 = 36;
                }else if(el5.text().equals("Diamond 3") || el5.text().equals("Diamond 4") || el5.text().equals("Platinum 1") || el5.text().equals("Platinum 2")){
                    sum5 = 26;
                }else if(el5.text().equals("Platinum 3") || el5.text().equals("Platinum 4") || el5.text().equals("Gold 1") || el5.text().equals("Gold 2")){
                    sum5 = 22;
                }else if(el5.text().equals("Gold 3") || el5.text().equals("Gold 4") || el5.text().equals("Silver 1") || el5.text().equals("Silver 2")){
                    sum5 = 17;
                }else if(el5.text().equals("silver 3") || el5.text().equals("silver 4") || el5.text().equals("Bronze 1") || el5.text().equals("Bronze 2")){
                    sum5 = 10;
                }else if(el5.text().equals("Bronze 3") || el5.text().equals("Bronze 4") || el5.text().equals("Iron 1") || el5.text().equals("Iron 2")){
                    sum5 = 1;
                }else if(el5.text().equals("Unranked")){
                    sum5 = 0;
                }
                sum = sum1+sum2+sum3+sum4+sum5;
                Log.v("e1값", el1.text());
                Log.v("e2값", el2.text());
                Log.v("e3값", el3.text());
                Log.v("e4값", el4.text());
                Log.v("e5값", el5.text());

                Log.v("sum1값", sum1+"");
                Log.v("sum2값", sum2+"");
                Log.v("sum3값", sum3+"");
                Log.v("sum4값", sum4+"");
                Log.v("sum5값", sum5+"");
                Log.v("sum값", sum+"");

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

                        tv_score.setText(sum+"");
                    }
                });


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


}

