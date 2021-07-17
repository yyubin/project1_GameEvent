package com.smhrd.js;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FragmentB extends Fragment {

    private Thread t;
    private ImageView arena_img;
    private ArrayList<RankDTO> list = new ArrayList<RankDTO>();

    public Context context_main;

    private TextView tv_rank_1_score, tv_rank_2_score, tv_rank_3_score, main_rank_1_name, main_rank_2_name, main_rank_3_name,
            tv_main_free_title,tv_main_free_text, tv_main_recruit, tv_main_recruit_text,tv_main_qa,tv_main_qa_text;
    private ranking ranking;

    private MainPage mainPage;

    private ImageView img_main_recruit;

    private TextView tv_board_go;


    private RequestQueue queue;
    private StringRequest stringRequest;

    public void MainPage(){
        this.mainPage=mainPage;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View fragment = inflater.inflate(R.layout.activity_main_page, container, false);

        Button btn_creat_team_go = fragment.findViewById(R.id.btn_creat_team_go);
        Button btn_recruiting_go = fragment.findViewById(R.id.btn_recruiting_go);
        Button btn_freechat_go = fragment.findViewById(R.id.btn_freechat_go);
        Button btn_QAchat_go = fragment.findViewById(R.id.btn_QAchat_go);
        Button btn_rank_go = fragment.findViewById(R.id.btn_rank_go);

        tv_main_qa=fragment.findViewById(R.id.tv_main_qa);
        tv_main_qa_text=fragment.findViewById(R.id.tv_main_qa_text);

//        img_main_recruit=fragment.findViewById(R.id.img_main_recurit);

        tv_rank_1_score=fragment.findViewById(R.id.tv_rank_1_score);
        tv_rank_2_score=fragment.findViewById(R.id.tv_rank_2_score);
        tv_rank_3_score=fragment.findViewById(R.id.tv_rank_3_score);

        main_rank_1_name=fragment.findViewById(R.id.main_rank_1_name);
        main_rank_2_name=fragment.findViewById(R.id.main_rank_2_name);
        main_rank_3_name=fragment.findViewById(R.id.main_rank_3_name);

        tv_main_free_text=fragment.findViewById(R.id.tv_main_free_text);
        tv_main_free_title=fragment.findViewById(R.id.tv_main_free_title);

        tv_main_recruit=fragment.findViewById(R.id.tv_main_recurit);
        tv_main_recruit_text=fragment.findViewById(R.id.tv_main_recurit_text);

        tv_board_go=fragment.findViewById(R.id.tv_board_go);

        tv_board_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Table.class);
                startActivity(intent);
            }
        });


        arena_img = fragment.findViewById(R.id.arena_img);

        t = new Thread(new MyThread());
        t.start();

        arena_img.setOnClickListener(new MyListener());

        ranking=new ranking();
        sendRequest();
        sendRequest1();
        sendRequest2();
        sendRequest3();

        btn_rank_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ranking.class);
                startActivity(intent);
            }
        });

        btn_creat_team_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CreateTeam.class);
                startActivity(intent);
            }
        });

        btn_recruiting_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), free_board.class);
                intent.putExtra("number",2+"");
                startActivity(intent);
            }
        });

        btn_freechat_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),free_board.class);
                intent.putExtra("number",1+"");
                startActivity(intent);
            }
        });

        btn_QAchat_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), free_board.class);
                intent.putExtra("number",3+"");
                startActivity(intent);
            }
        });

        return fragment;
    }

    class MyListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getContext(), battle_attend.class);
            startActivity(intent);

        }

    }

    public void sendRequest() {

        queue = Volley.newRequestQueue(getContext());
        String url = "http://121.147.52.82:3100/team_ranking";

        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            private ArrayList<RankDTO> list = new ArrayList<RankDTO>();

            @Override
            public void onResponse(String response) {
                //Server로부터 데이터를 받아온 곳
                Log.v("idiid", response);


                try {
                    JSONObject jsonObject = new JSONObject(response); //서버


                    JSONArray jsonArray = jsonObject.getJSONArray("team_name");
                    JSONArray jsonArray1 = jsonObject.getJSONArray("team_score");
                    JSONArray jsonArray2 = jsonObject.getJSONArray("team_img_logo");


                    tv_rank_1_score.setText(jsonArray1.getString(0));
                    main_rank_1_name.setText(jsonArray.getString(0));

                    tv_rank_2_score.setText(jsonArray1.getString(1));
                    main_rank_2_name.setText(jsonArray.getString(1));

                    tv_rank_3_score.setText(jsonArray1.getString(2));
                    main_rank_3_name.setText(jsonArray.getString(2));

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

                String member = PreferenceManager.getString(getContext(),"login");
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

    public void sendRequest1() { // get방식 post방식 get방식은 url공유가능

        queue = Volley.newRequestQueue(getContext());
        String url = "http://121.147.52.82:3100/free_board_all";
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            private ArrayList<ChatDTO> list = new ArrayList<ChatDTO>();

            @Override
            public void onResponse(String response) {

                Log.v("resultValue", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray jsonArray = jsonObject.getJSONArray("board_title");
                    JSONArray jsonArray3 = jsonObject.getJSONArray("board_text");
                    tv_main_free_text.setText(jsonArray3.getString(0));
                    tv_main_free_title.setText(jsonArray.getString(0));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // 에러 감지하는 곳
                //Server 통신시 Error발생 하면 오는 곳
                error.printStackTrace();
            }
        }) {

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {

                try {
                    String utf8String = new String(response.data, "UTF-8");
                    return Response.success(utf8String, HttpHeaderParser.parseCacheHeaders(response)); //인코딩하는 코드
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                return super.parseNetworkResponse(response);

            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                return params;

            }
        };
        queue.add(stringRequest);
    }

    public void sendRequest2() { // get방식 post방식 get방식은 url공유가능

        queue = Volley.newRequestQueue(getContext());
        String url = "http://121.147.52.82:3100/recruit_board_all";
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            private ArrayList<ChatDTO> list = new ArrayList<ChatDTO>();

            @Override
            public void onResponse(String response) {

                Log.v("resultValue", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray numArray = jsonObject.getJSONArray("board_text_num");
                    JSONArray jsonArray = jsonObject.getJSONArray("board_title");
                    JSONArray jsonArray1 = jsonObject.getJSONArray("member_lol_name");
                    JSONArray jsonArray2 = jsonObject.getJSONArray("board_time");
                    JSONArray jsonArray3 = jsonObject.getJSONArray("board_text");
                    JSONArray jsonArray4 = jsonObject.getJSONArray("board_num");


                    tv_main_recruit.setText(jsonArray.getString(0));
                    tv_main_recruit_text.setText(jsonArray3.getString(0));

                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // 에러 감지하는 곳
                //Server 통신시 Error발생 하면 오는 곳
                error.printStackTrace();
            }
        }) {

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {

                try {
                    String utf8String = new String(response.data, "UTF-8");
                    return Response.success(utf8String, HttpHeaderParser.parseCacheHeaders(response)); //인코딩하는 코드
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                return super.parseNetworkResponse(response);


            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();




                return params;

            }
        };


        queue.add(stringRequest);


    }

    public void sendRequest3() { // get방식 post방식 get방식은 url공유가능

        queue = Volley.newRequestQueue(getContext());
        String url = "http://121.147.52.82:3100/qa_board_all";
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            private ArrayList<ChatDTO> list = new ArrayList<ChatDTO>();

            @Override
            public void onResponse(String response) {

                Log.v("resultValue", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray numArray = jsonObject.getJSONArray("board_text_num");
                    JSONArray jsonArray = jsonObject.getJSONArray("board_title");
                    JSONArray jsonArray1 = jsonObject.getJSONArray("member_lol_name");
                    JSONArray jsonArray2 = jsonObject.getJSONArray("board_time");
                    JSONArray jsonArray3 = jsonObject.getJSONArray("board_text");
                    JSONArray jsonArray4 = jsonObject.getJSONArray("board_num");
                    Log.v("asdfadsfasdf",jsonArray4.getString(0));

                    tv_main_qa.setText(jsonArray.getString(0));
                    tv_main_qa_text.setText(jsonArray3.getString(0));




                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // 에러 감지하는 곳
                //Server 통신시 Error발생 하면 오는 곳
                error.printStackTrace();
            }
        }) {

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {

                try {
                    String utf8String = new String(response.data, "UTF-8");
                    return Response.success(utf8String, HttpHeaderParser.parseCacheHeaders(response)); //인코딩하는 코드
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                return super.parseNetworkResponse(response);


            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();




                return params;

            }
        };


        queue.add(stringRequest);


    }




    public class MyThread implements Runnable {
        int cnt = 0;
        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()) {//is가 붙어있으면 true or false
                    Thread.sleep(1000);
                    cnt++;
                    Message message = myHandler.obtainMessage();
                    message.arg1 = cnt;
                    myHandler.sendMessage(message);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(msg.arg1%6==0 || msg.arg1%6==1){
                arena_img.setImageResource(R.drawable.arena1);
            }else if(msg.arg1%6==2 || msg.arg1%6==3){
                arena_img.setImageResource(R.drawable.arena2);
            }else if(msg.arg1%6==4 || msg.arg1%6==4){
                arena_img.setImageResource(R.drawable.arena3);
            }
        }
    };



}