package com.smhrd.js;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

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

public class chatting extends AppCompatActivity {

    private EditText edt_chat;
    private Button btn_chat_submit;

    private RequestQueue queue; // 정보를 통해 요청, 실질적으로 움직임, StringRequest
    private StringRequest stringRequest; // 주문하는 문서의 내용

    private ChatAdapter adapter;
    private ListView chatlist;
    private String lol_name;
    private ArrayList<ChatDTO> list = new ArrayList<ChatDTO>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

//
//        MyThread myThread = new MyThread();
//        myThread.start();
//.ListView listView1 = (ListView) findViewById(R.id.listView1)

        chatlist = (ListView) findViewById(R.id.chatlist);

        edt_chat = findViewById(R.id.edt_chat);
        btn_chat_submit = findViewById(R.id.btn_chat_submit);

        String member = PreferenceManager.getString(getApplicationContext(), "login");
        try {
            JSONObject jsonObject = new JSONObject(member);
            lol_name = jsonObject.getString("lol_name");
            Log.v("lol_name", lol_name);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        sendRequest();

        btn_chat_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest();



            }
        });
    }


    public void sendRequest() { // get방식 post방식 get방식은 url공유가능
        adapter = new ChatAdapter();
        queue = Volley.newRequestQueue(this);
        String url = "http://121.147.52.82:3100/chatting";
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            private ArrayList<ChatDTO> list = new ArrayList<ChatDTO>();

            @Override
            public void onResponse(String response) {

                Log.v("resultValue", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("member_lol_name");
                    JSONArray jsonArray1 = jsonObject.getJSONArray("chatting_text");
                    Log.v("lol_name", jsonArray + "");
                    Log.v("chat", jsonArray1 + "");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        String id = jsonArray.getString(i);
                        String chat = jsonArray1.getString(i);

                        adapter.addItem(id, chat);

                    }
//                    adapter.notifyDataSetChanged();
                    chatlist.setAdapter(adapter);
                    chatlist.setSelection(adapter.getCount() - 1);

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

                try { // try catch는 string타입 아닌경우를 대비함
                    String utf8String = new String(response.data, "UTF-8");
                    return Response.success(utf8String, HttpHeaderParser.parseCacheHeaders(response)); //인코딩하는 코드
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                return super.parseNetworkResponse(response);
                //데이터가져오는 행위 = 파싱 데이터=파스 (여기는 인코딩하는곳) response method가기전에 먼저오는곳

            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                String member = PreferenceManager.getString(getApplicationContext(), "login");
                try {
                    JSONObject jsonObject = new JSONObject(member);
                    String lol_name = jsonObject.getString("lol_name");
                    params.put("member_lol_name", lol_name);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                params.put("chatting_text", edt_chat.getText().toString());
                Log.v("aaaaaaaaa",edt_chat.getText().toString());


                return params;
                // 네트워크끼리 주고받는 것 parameter 데이터 보내는 곳
                //Server로 데이터를 보낼 시 넣어주는 곳
            }
        };

        //requestqueue가 요청하는 것
        queue.add(stringRequest);


    }


//    public void chatSelect() { // get방식 post방식 get방식은 url공유가능
//        adapter = new ChatAdapter();
//        queue = Volley.newRequestQueue(this);
//        String url = "http://121.147.52.82:3100/chatting";
//        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            private ArrayList<ChatDTO> list = new ArrayList<ChatDTO>();
//
//            @Override
//            public void onResponse(String response) {
//
//                Log.v("resultValue", response);
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                    JSONArray jsonArray = jsonObject.getJSONArray("member_lol_name");
//                    JSONArray jsonArray1 = jsonObject.getJSONArray("chatting_text");
//                    Log.v("lol_name", jsonArray + "");
//                    Log.v("chat", jsonArray1 + "");
//
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        String id = jsonArray.getString(i);
//                        String chat = jsonArray1.getString(i);
//
//                        adapter.addItem(id, chat);
//
//                    }
//                    chatlist.setAdapter(adapter);
//                    chatlist.setSelection(adapter.getCount() - 1);
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//                error.printStackTrace();
//            }
//        }) {
//
//            @Override
//            protected Response<String> parseNetworkResponse(NetworkResponse response) {
//
//                try { // try catch는 string타입 아닌경우를 대비함
//                    String utf8String = new String(response.data, "UTF-8");
//                    return Response.success(utf8String, HttpHeaderParser.parseCacheHeaders(response)); //인코딩하는 코드
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
//                return super.parseNetworkResponse(response);
//
//            }
//
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<String, String>();
//
//                return params;
//            }
//        };
//
//        queue.add(stringRequest);
//
//
//    }

//
//    public class MyThread extends Thread{
//        @Override
//        public void run() {
//
//            while (true){
//                chatSelect();
//                try {
//                    Thread.sleep(400);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//
//
//        }
//    }

}


