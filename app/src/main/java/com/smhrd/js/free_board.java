package com.smhrd.js;

import android.os.Bundle;
import android.util.Log;
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

public class free_board extends AppCompatActivity {


    private RequestQueue queue; // 정보를 통해 요청, 실질적으로 움직임, StringRequest
    private StringRequest stringRequest; // 주문하는 문서의 내용

    private BoardAdapter adapter;
    private ListView boardlist;
    private ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_board);



        boardlist=findViewById(R.id.board_list);

        sendRequest();


    }

    public void sendRequest() { // get방식 post방식 get방식은 url공유가능
        adapter = new BoardAdapter();
        queue = Volley.newRequestQueue(this);
        String url = "http://121.147.52.82:3100/free_board_all";
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


                    for (int i = 0; i < jsonArray.length(); i++) {
                        String board_num = jsonArray4.getString(i);
                        String title = jsonArray.getString(i);
                        String name = jsonArray1.getString(i);
                        String time = jsonArray2.getString(i);
                        String num = numArray.getString(i);
                        String text = jsonArray3.getString(i);

                        adapter.addItem(board_num,name,title,text,time,num);

                    }
//                    adapter.notifyDataSetChanged();
                    boardlist.setAdapter(adapter);
                    boardlist.setSelection(adapter.getCount() - 1);

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

}