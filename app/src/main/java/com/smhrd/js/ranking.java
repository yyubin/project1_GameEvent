package com.smhrd.js;

import android.content.Context;
import android.icu.number.CompactNotation;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
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

public class ranking extends AppCompatActivity {

    ImageView img_rank_team_rogo;
    TextView tv_rank_team_name, tv_rank_lol_name, tv_rank_team_score, tv_rank_team_rank;

    private RequestQueue queue;
    private StringRequest stringRequest;


    private ListView ranklist;
    private RankAdapter adapter;
    private RankAdapter2 adapter2;
    private ArrayList<RankDTO> list = new ArrayList<RankDTO>();

    private int a,ad;
    String myteam, pac;

    public Context context_ranking;

    private NotificationListenerService.Ranking ranking;
    private MainPage mainPage;
    public ranking ranking_class;

    private String rank_1_team, rank_2_team, rank_3_team, rank_1_score,rank_2_score,rank_3_score;

    public ranking() {
        this.ranking = ranking;
    }

    public ranking(String rank_1_team, String rank_2_team, String rank_3_team, String rank_1_score, String rank_2_score, String rank_3_score) {
        this.rank_1_team = rank_1_team;
        this.rank_2_team = rank_2_team;
        this.rank_3_team = rank_3_team;
        this.rank_1_score = rank_1_score;
        this.rank_2_score = rank_2_score;
        this.rank_3_score = rank_3_score;
    }

    public String getRank_1_team() {
        return rank_1_team;
    }

    public void setRank_1_team(String rank_1_team) {
        this.rank_1_team = rank_1_team;
    }

    public String getRank_2_team() {
        return rank_2_team;
    }

    public void setRank_2_team(String rank_2_team) {
        this.rank_2_team = rank_2_team;
    }

    public String getRank_3_team() {
        return rank_3_team;
    }

    public void setRank_3_team(String rank_3_team) {
        this.rank_3_team = rank_3_team;
    }

    public String getRank_1_score() {

        return rank_1_score;
    }

    public void setRank_1_score(String rank_1_score) {
        this.rank_1_score = rank_1_score;
    }

    public String getRank_2_score() {
        return rank_2_score;
    }

    public void setRank_2_score(String rank_2_score) {
        this.rank_2_score = rank_2_score;
    }

    public String getRank_3_score() {
        return rank_3_score;
    }

    public void setRank_3_score(String rank_3_score) {
        this.rank_3_score = rank_3_score;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        pac = this.getPackageName();
        img_rank_team_rogo=findViewById(R.id.img_rank_rogo);
        tv_rank_lol_name=findViewById(R.id.tv_rank_lol_name);
        tv_rank_team_name=findViewById(R.id.tv_rank_team_name);
        tv_rank_team_rank=findViewById(R.id.tv_rank_team_rank);
        tv_rank_team_score=findViewById(R.id.tv_rank_team_score);

        ranklist=findViewById(R.id.ranklist);



        sendRequest();
//        sendRequest1();
//        try {
//
//            File files = new File("/data/user/0/com.smhrd.js/1626073178238");
//
//            if(files.exists()==true) {
//
//                Uri uri1 = Uri.parse("/data/user/0/com.smhrd.js/1626073178238");
//                img_rank_team_rogo.setImageURI(uri1);
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }


    }

    public void sendRequest() {
        adapter = new RankAdapter();
        adapter2 = new RankAdapter2();
        queue = Volley.newRequestQueue(this);
        String url = "http://121.147.52.82:3100/team_ranking";

        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            private ArrayList<RankDTO> list = new ArrayList<RankDTO>();

            @Override
            public void onResponse(String response) {
                //Server로부터 데이터를 받아온 곳
                Log.v("idiid",response);


                String member = PreferenceManager.getString(getApplicationContext(),"login");

                try {
                    JSONObject jsonObject2 = new JSONObject(member);


                    myteam=jsonObject2.getString("team_name");
                    Log.v("nyteam",myteam);
                    tv_rank_team_name.setText(myteam);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                try {
                    JSONObject jsonObject = new JSONObject(response); //서버
                    JSONObject jsonObject1 = new JSONObject(member); //pre
                    Log.v("member",member);
                    String name = jsonObject1.getString("lol_name");
                    tv_rank_lol_name.setText(name);

                    JSONArray jsonArray = jsonObject.getJSONArray("team_name");
                    JSONArray jsonArray1 = jsonObject.getJSONArray("team_score");
                    JSONArray jsonArray2 = jsonObject.getJSONArray("team_img_logo");



                    int rank;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        rank = (i+1);
                        String team_name = jsonArray.getString(i);
                        String team_score = jsonArray1.getString(i);


                        adapter.notifyDataSetChanged();
                        adapter2.notifyDataSetChanged();
                        adapter.addItem(rank,team_name,team_score);
                        adapter2.addItem(rank,team_name,team_score);
                        Log.v("1위",rank+"");

                        ranklist.setAdapter(adapter);
                        ranklist.setSelection(adapter.getCount() - 1);

                        Log.v("aaaaaaaaaaaaa",myteam);
                        Log.v("aaaaaaaaaaaaaaaa",team_name);

                        if(myteam.equals(jsonArray.getString(i)+"")) {
                            a=i;
                            String img1 = "@drawable/logo"+jsonArray2.getString(i);
                            ad = getResources ().getIdentifier(img1,"drawable",pac);
                            img_rank_team_rogo.setImageResource(ad);
                            Log.v("num",a+"");

                            tv_rank_team_score.setText(jsonArray1.getString(a));
                            tv_rank_team_rank.setText(a+1+"");
                        }

                    }


                    MainPage mainPage = new MainPage();

                    setRank_1_team(jsonArray.getString(0));
                    Log.v("asdfasdf",getRank_1_team());

                    setRank_1_score(jsonArray1.getString(0));
                    setRank_2_team(jsonArray.getString(1));
                    setRank_2_score(jsonArray1.getString(1));
                    setRank_3_team(jsonArray.getString(2));
                    setRank_3_score(jsonArray1.getString(2));


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

                String member = PreferenceManager.getString(getApplicationContext(),"login");
                try {
                    JSONObject jsonObject = new JSONObject(member);
                    String lol_name = jsonObject.getString("lol_name");
                    parmas.put("lol_name",lol_name);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return parmas;

            }
        };

        queue.add(stringRequest);


    }

//    public void sendRequest1() {
//        adapter = new RankAdapter();
//        queue = Volley.newRequestQueue(this);
//        String url = "http://121.147.52.82:3100/team_name_temp";
//
//
//        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//
//            @Override
//            public void onResponse(String response) {
//                //Server로부터 데이터를 받아온 곳
//                Log.v("idiid",response);
//
//                    try {
//                        JSONObject jsonObject = new JSONObject(response);
//                        String uri = jsonObject.getString("team_img_logo");
//                        Log.v("Uri",uri);
//
//                        try {
//                            File files = new File(uri);
//                            if(files.exists()==true) {
//                                Uri uri1 = Uri.parse(uri);
//                                img_rank_team_rogo.setImageURI(uri1);
//                            }
//                        }catch (Exception e){
//                            e.printStackTrace();
//                        }
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                //Server 통신시 Error 발생하면 오는 곳
//                error.printStackTrace();
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                //Server로 데이터 보낼 시 넣어주는 곳
//                Map<String, String> parmas = new HashMap<String, String>();
//
//                String member = PreferenceManager.getString(getApplicationContext(),"login");
//                try {
//                    JSONObject jsonObject = new JSONObject(member);
//                    String member_lol_name = jsonObject.getString("lol_name");
//                    parmas.put("member_lol_name",member_lol_name);
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//                return parmas;
//
//            }
//        };
//
//        queue.add(stringRequest);
//
//    }



}