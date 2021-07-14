package com.smhrd.js;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ranking extends AppCompatActivity {

    ImageView img_rank_team_rogo;
    TextView tv_rank_team_name, tv_rank_lol_name, tv_rank_team_score, tv_rank_team_rank;

    private RequestQueue queue;
    private StringRequest stringRequest;

    private RankAdapter adapter;
    private ListView ranklist;

    String n;



    private ArrayList<RankDTO> list = new ArrayList<RankDTO>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        img_rank_team_rogo=findViewById(R.id.img_rank_rogo);
        tv_rank_lol_name=findViewById(R.id.tv_rank_lol_name);
        tv_rank_team_name=findViewById(R.id.tv_rank_team_name);
        tv_rank_team_rank=findViewById(R.id.tv_rank_team_rank);
        tv_rank_team_score=findViewById(R.id.tv_rank_team_score);

        ranklist=findViewById(R.id.ranklist);

//        setImage(#/data/user/0/com.smhrd.js/1626073178238);

//        sendRequest();
        sendRequest1();


    }

//    public void sendRequest() {
//        adapter = new RankAdapter();
//        queue = Volley.newRequestQueue(this);
//        String url = "http://121.147.52.82:3100/team_ranking";
//
//        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            private ArrayList<RankDTO> list = new ArrayList<RankDTO>();
//
//            @Override
//            public void onResponse(String response) {
//                //Server로부터 데이터를 받아온 곳
//                Log.v("idiid",response);
//
//                String member = PreferenceManager.getString(getApplicationContext(),"login");
//
//
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                    JSONObject jsonObject1 = new JSONObject(member);
//                    String name = jsonObject1.getString("lol_name");
//                    tv_rank_lol_name.setText(name);
//                    n=name;
//
//                    JSONArray jsonArray = jsonObject.getJSONArray("team_name");
//                    JSONArray jsonArray1 = jsonObject.getJSONArray("team_score");
//                    String my_team ;
//                    if((jsonObject1.optString("team_name","")).equals("")){
//                        my_team = "";
//                    }else{
//                        my_team = jsonObject1.getString("team_name");
//                    }
//
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        int rank = (i+1);
//                        String team_name = jsonArray.getString(i);
//                        String team_score = jsonArray1.getString(i);
//                        if(my_team.equals(jsonArray.getString(i))){
//                            tv_rank_team_name.setText(my_team);
//                            tv_rank_team_score.setText(jsonArray1.getString(i));
//                            tv_rank_team_rank.setText(i+1);
//                        }else{
//                            tv_rank_team_score.setText("");
//                            tv_rank_team_rank.setText("");
//                            tv_rank_team_name.setText("속해있는 팀이 없습니다");
//                        }
//
//
//                        adapter.addItem(rank,team_name,team_score);
//
//                    }
////                    adapter.notifyDataSetChanged();
//                    ranklist.setAdapter(adapter);
//                    ranklist.setSelection(adapter.getCount() - 1);
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//
//
//
//            }
//
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
//                    String lol_name = jsonObject.getString("member_lol_name");
//                    parmas.put("lol_name",lol_name);
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//
//
//
//                return parmas;
//
//            }
//        };
//
//        queue.add(stringRequest);
//
//
//    }

    public void sendRequest1() {
        adapter = new RankAdapter();
        queue = Volley.newRequestQueue(this);
        String url = "http://121.147.52.82:3100/team_name_temp";

        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            private ArrayList<RankDTO> list = new ArrayList<RankDTO>();

            @Override
            public void onResponse(String response) {
                //Server로부터 데이터를 받아온 곳
                Log.v("idiid",response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String uri = jsonObject.getString("team_img_logo");
                    Log.v("Uri",uri);
                    setImage(Uri.parse(uri));


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
                    String team_name = jsonObject.getString("member_lol_name");
                    parmas.put("team_name",team_name);

                } catch (JSONException e) {
                    e.printStackTrace();
                }




                return parmas;

            }
        };

        queue.add(stringRequest);


    }

    private void setImage(Uri uri) {
        try{
            InputStream in = getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(in);
            img_rank_team_rogo.setImageBitmap(bitmap);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
}