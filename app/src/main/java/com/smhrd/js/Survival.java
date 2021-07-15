package com.smhrd.js;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Survival extends AppCompatActivity {

    private RequestQueue queue;
    private StringRequest stringRequest;

    private TextView tv_team;

    int arr_32[] = new int[]{R.id.tv_sv_32_1, R.id.tv_sv_32_2, R.id.tv_sv_32_3, R.id.tv_sv_32_4,
            R.id.tv_sv_32_5, R.id.tv_sv_32_6,R.id.tv_sv_32_7,R.id.tv_sv_32_8};
    int arr_16[] = new int[]{R.id.tv_sv_16_1,
            R.id.tv_sv_16_2, R.id.tv_sv_16_3,R.id.tv_sv_16_4};
    int arr_8[] = new int[]{R.id.tv_sv_8_1,R.id.tv_sv_8_2};
    int arr_4[]=new int[]{R.id.tv_sv_4_1};
    TextView arr1[] = new TextView[arr_32.length];
    TextView arr2[] = new TextView[arr_16.length];
    TextView arr3[] = new TextView[arr_8.length];
    TextView arr4[] = new TextView[arr_4.length];

    String team_16[] = new String[16];

    ArrayList<String> arr_tor = new ArrayList<>();
    ArrayList<String> arr_team1 = new ArrayList<>();
    ArrayList<Integer> arr_count = new ArrayList<Integer>();

    private BattleAdapter adapter;
    private ArrayList<BattleDTO> list = new ArrayList<BattleDTO>();

    private TextView tv_time_1, tv_time_2, tv_time_3, tv_time_4;
    private TextView tv_date_1, tv_date_2, tv_date_3, tv_date_4;
    private Button btn_a, btn_b, btn_c, btn_d;

    int end;
    int tornament[];

    String[] team_16_name = {"포도리","떡잎방범대","꿀렁꿀렁","반짝이들","주둥이","풍선팀","산기슭","주주클럽","돌머리","쿵쿵따","헬로월드","망뚱이","동물친구","꿈별이들","왕짠돌","검은조직"};
    String[] team_8_name = {};
    String[] team_4_name = {};
    String[] team_2_name = {};
    String[] team_1_name = {};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survival);

        tv_team = findViewById(R.id.tv_line);

        for(int i = 0; i < arr_32.length; i++){
            arr1[i]=findViewById(arr_32[i]);
        };
        for(int i=0; i<arr_16.length;i++){
            arr2[i]=findViewById(arr_16[i]);
        };
        for(int i=0; i< arr_8.length;i++){
            arr3[i]=findViewById(arr_8[i]);
            Log.v("aaaaa",arr3[i]+" "+arr_8[i]);
        };
//        arr3[0]=findViewById(R.id.tv_sv_8_1);
//
//        arr3[1]=findViewById(arr_8[1]);
        for(int i=0; i< arr_4.length; i++){
            arr4[i]=findViewById(arr_4[i]);
        };


        tv_time_1 = findViewById(R.id.tv_time_1);
        tv_time_2 = findViewById(R.id.tv_time_1);
        tv_time_3 = findViewById(R.id.tv_time_2);
        tv_time_4 = findViewById(R.id.tv_time_3);

        tv_date_1 = findViewById(R.id.tv_date_1);
        tv_date_2 = findViewById(R.id.tv_date_1);
        tv_date_3 = findViewById(R.id.tv_date_2);
        tv_date_4 = findViewById(R.id.tv_date_3);

        btn_a = findViewById(R.id.btn_a);
        btn_b = findViewById(R.id.btn_b);
        btn_c = findViewById(R.id.btn_c);
        btn_d = findViewById(R.id.btn_d);

        sendRequest();
        sendRequest1();

        btn_a.callOnClick();

        btn_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a=0;
                for(int i=0; i< arr_32.length; i++){
                    arr1[i].setText(arr_team1.get(arr_count.get(i))+"");
                    Log.v("check1",arr1[i].getText().toString());

                }
                for(int j=0; j<team_16_name.length; j++){
                    for(int i=0; i< arr1.length; i++){
                        if(arr1[i].getText().toString().equals(team_16_name[j])){
                            arr2[a].setText(team_16_name[j]);
                            a++;
                        }
                    }

                }

//                for(int i=0; i< arr_16.length;i++){
//                    if(arr_tor.get(i).equals("16")){
//                        Log.v("aaaaaaaaaaa",arr1[i].getText().toString());
//                        arr2[i].setText( arr1[i].getText().toString());
//                    }else{
//                        arr1[i].setBackgroundColor(Color.parseColor("Black"));
//                    }
//                }
//                for (int i=0; i< arr_8.length; i++){
//                    if(arr_tor.get(i).equals("8")){
//                        arr3[i].setText(arr2[i].getText().toString());
//                    }else{
//                        arr2[i].setBackgroundColor(Color.parseColor("Black"));
//                    }
//                }
//                for(int i=0; i<arr_4.length; i++){
//                    if(arr_tor.get(i).equals("4")){
//                        arr4[i].setText(arr3[i].getText().toString());
//                    }else{
//                        arr3[i].setBackgroundColor(Color.parseColor("Black"));
//                    }
//                }

            }
        });

        btn_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i=0; i<arr_32.length; i++){
                    arr1[i].setText(arr_team1.get(arr_count.get(i+8))+"");
                    Log.v("check2",arr1[i].getText().toString());

                }
                int a = 0;
                for(int j=0; j<team_16_name.length; j++){
                    for(int i=0; i< arr1.length; i++){
                        if(arr1[i].getText().toString().equals(team_16_name[j])){
                            arr2[a].setText(team_16_name[j]);
                            a++;
                        }
                    }

                }
            }
        });

        btn_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0; i<arr_32.length; i++){
                    arr1[i].setText(arr_team1.get(arr_count.get(i+16))+"");
                    Log.v("check3",arr1[i].getText().toString());
                }
                int a = 0;
                for(int j=0; j<team_16_name.length; j++){
                    for(int i=0; i< arr1.length; i++){
                        if(arr1[i].getText().toString().equals(team_16_name[j])){
                            arr2[a].setText(team_16_name[j]);
                            a++;
                        }
                    }

                }

            }
        });

        btn_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0; i<arr_32.length; i++){
                    arr1[i].setText(arr_team1.get(arr_count.get(i+24))+"");
                    Log.v("check4",arr1[i].getText().toString());


                }
                int a = 0;
                for(int j=0; j<team_16_name.length; j++){
                    for(int i=0; i< arr1.length; i++){
                        if(arr1[i].getText().toString().equals(team_16_name[j])){
                            arr2[a].setText(team_16_name[j]);
                            a++;
                        }
                    }

                }
            }
        });



    }

    public void sendRequest() {
        adapter = new BattleAdapter();
        queue = Volley.newRequestQueue(this);
        String url = "http://121.147.52.82:3100/battle_tournament";

        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            private ArrayList<RankDTO> list = new ArrayList<RankDTO>();

            @Override
            public void onResponse(String response) {
                //Server로부터 데이터를 받아온 곳
                Log.v("idiid",response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Random random = new Random();
                    JSONArray jsonArray_team1 = jsonObject.getJSONArray("team_name");
                    JSONArray jsonArray_tor = jsonObject.getJSONArray("battle_tournament");
                    JSONArray jsonArray_bat_name = jsonObject.getJSONArray("battle_name");
                    if(jsonArray_bat_name.getString(1).equals("브론즈리그")){
                        random.setSeed(12);
                    }
                    for(int i=0; i<jsonArray_tor.length(); i++){
                        arr_tor.add(jsonArray_tor.getString(i)+"");
                    }

                    int a;
                    for(int i=0; i<jsonArray_team1.length(); i++) {
                        arr_team1.add(jsonArray_team1.getString(i));
                    }
                    Log.v("jsonteam",jsonArray_team1.length()+"");
                    for(int i=0; i<arr_team1.size();i++){

                        a=random.nextInt(arr_team1.size());
                        arr_count.add(a);

                        for(int j=0; j<i;j++){
                            if(arr_count.get(i)==arr_count.get(j)){
                                arr_count.remove(i);
                                i--;
                            }
                        }
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
                //Server로 데이터 보낼 시 넣어주는 곳
                Map<String, String> parmas = new HashMap<String, String>();

                parmas.put("battle_name","브론즈리그");
                for(int i=0; i<team_16_name.length; i++){
                    parmas.put("team_name",team_16_name[i]+"");
                }

                return parmas;

            }
        };
        queue.add(stringRequest);
    }

    public void sendRequest1() {
        adapter = new BattleAdapter();
        queue = Volley.newRequestQueue(this);
        String url = "http://121.147.52.82:3100/battle_tournament_update";

        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            private ArrayList<RankDTO> list = new ArrayList<RankDTO>();

            @Override
            public void onResponse(String response) {
                //Server로부터 데이터를 받아온 곳

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
                //Server로 데이터 보낼 시 넣어주는 곳
                Map<String, String> parmas = new HashMap<String, String>();

                for(int i=0; i<team_16_name.length; i++){
                    parmas.put("battle_name","브론즈리그");

                    parmas.put("team_name",team_16_name[i]+"");

                    return parmas;
                }

                return parmas;

            }
        };
        queue.add(stringRequest);
    }


}