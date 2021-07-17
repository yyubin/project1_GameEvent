package com.smhrd.js;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class Recruting_chat extends AppCompatActivity {

    private TextView tv_re_lol_name, tv_re_text;

    private String num,num2;
    private RequestQueue queue;
    private StringRequest stringRequest;

    private CommentAdapter adapter;
    private ListView recruit_comment_list;

    private ArrayList<CommentDTO> list = new ArrayList<CommentDTO>();
    private ArrayList<String> count = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruting_chat);

        recruit_comment_list=findViewById(R.id.recruit_comment_list);

        tv_re_lol_name=findViewById(R.id.tv_re_lol_name);
        tv_re_text=findViewById(R.id.tv_re_text);

        Intent intent = getIntent();
//        intent.putExtra("board_text_num",dto.getNum());
//        intent.putExtra("board_num",dto.getBoard_num());
//        intent.putExtra("name",dto.getName());

        tv_re_text.setText(intent.getStringExtra("text"));
        tv_re_lol_name.setText(intent.getStringExtra("name"));
        num=intent.getStringExtra("board_text_num");
        num2=intent.getStringExtra("board_num");

        sendRequest();

    }

    public void sendRequest() {
        adapter=new CommentAdapter();
        queue = Volley.newRequestQueue(this);
        String url = "http://121.147.52.82:3100/recruit_board_comment_write_all";
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Server로부터 데이터를 받아온 곳
                Log.v("idiida",response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray2 = jsonObject.getJSONArray("댓글작성자");
                    JSONArray jsonArray3 = jsonObject.getJSONArray("댓글내용");


                    for(int i=0; i<jsonArray2.length(); i++){
                        String lol_name = jsonArray2.getString(i);
                        String comment = jsonArray3.getString(i);


                        adapter.addItem(lol_name,comment,i+"");
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                recruit_comment_list.setAdapter(adapter);
                recruit_comment_list.setSelection(adapter.getCount() - 1);

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

                parmas.put("board_text_num",String.valueOf(Integer.parseInt(num)));
                parmas.put("board_num",String.valueOf(Integer.parseInt(num2)));


                return parmas;

            }
        };

        queue.add(stringRequest);


    }
}