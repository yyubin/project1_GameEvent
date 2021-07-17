package com.smhrd.js;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class boradsuc extends AppCompatActivity {

    private TextView tv_free_title, tv_free_lol_name, tv_free_text;
    private EditText edt_free_comment;
    private Button btn_free_submit;

    private String num,num2;
    private RequestQueue queue;
    private StringRequest stringRequest;

    private CommentAdapter adapter;
    private ListView comment_list;

    private ArrayList<CommentDTO> list = new ArrayList<CommentDTO>();
    private ArrayList<String> count = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boradsuc);

        tv_free_lol_name=findViewById(R.id.tv_re_lol_name);
        tv_free_text=findViewById(R.id.tv_free_text);
        tv_free_title=findViewById(R.id.tv_free_title);

        comment_list=findViewById(R.id.comment_list);
        edt_free_comment=findViewById(R.id.edt_free_comment);
        btn_free_submit=findViewById(R.id.btn_free_submit);

        Intent intent = getIntent();
        num=intent.getStringExtra("board_text_num");
        num2=intent.getStringExtra("board_num");
        String name = intent.getStringExtra("name");
        tv_free_lol_name.setText(name);
        Log.v("num",num);

        sendRequest();

        btn_free_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendRequest1();


            }
        });
    }

    public void sendRequest() {
        adapter=new CommentAdapter();
        queue = Volley.newRequestQueue(this);
        String url = "http://121.147.52.82:3100/free_board_comment_write_all";
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Server로부터 데이터를 받아온 곳
                Log.v("idiid",response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("글제목");
                    tv_free_title.setText(jsonArray.getString(0));
                    JSONArray jsonArray1 = jsonObject.getJSONArray("글내용");
                    tv_free_text.setText(jsonArray1.getString(0));
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

                comment_list.setAdapter(adapter);
                comment_list.setSelection(adapter.getCount() - 1);

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

    public void sendRequest1() {
        adapter=new CommentAdapter();
        queue = Volley.newRequestQueue(this);
        String url = "http://121.147.52.82:3100/free_board_comment_write";
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Server로부터 데이터를 받아온 곳
                Log.v("idiid",response);

                sendRequest();

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

                String name;

                String member = PreferenceManager.getString(getApplicationContext(),"login");
                try {
                    JSONObject jsonObject = new JSONObject(member);
                    name=jsonObject.getString("lol_name");
                    parmas.put("member_lol_name",name);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String comment = edt_free_comment.getText().toString();

                parmas.put("comment_text",comment);
                parmas.put("board_text_num",String.valueOf(Integer.parseInt(num)));

                sendRequest();
                return parmas;



            }
        };

        queue.add(stringRequest);


    }
}