package com.smhrd.js;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class pw_find extends AppCompatActivity {
    private RequestQueue queue;
    private StringRequest stringRequest;

    private EditText edt_join_id3, edt_join_email3, edt_join_phone3;
    private Button btn_pw_find;
    private String find_pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pw_find);

        edt_join_id3=findViewById(R.id.edt_join_name2);
        edt_join_email3=findViewById(R.id.edt_join_email2);
        edt_join_phone3=findViewById(R.id.edt_join_phone2);

        btn_pw_find=findViewById(R.id.btn_id_find);

        btn_pw_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest();

            }
        });


    }

    public void sendRequest() {
        queue = Volley.newRequestQueue(this);
        String url = "http://121.147.52.82:3100/find_pw";
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Server로부터 데이터를 받아온 곳

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    find_pw = jsonObject.getString("member_pw");
                    String check = jsonObject.getString("check");
                    if(check.equals("ok")){
                        AlertDialog.Builder builder = new AlertDialog.Builder(pw_find.this);

                        builder.setTitle("아이디 찾기").setMessage("요청하신 비밀번호 : "+find_pw);

                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int id)
                            {
                                Intent intent = new Intent(getApplicationContext(),Login.class);
                                startActivity(intent);
                            }
                        });


                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }else{
                        Toast.makeText(pw_find.this,"입력하신 정보가 존재하지 않습니다",Toast.LENGTH_SHORT).show();
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
                parmas.put("member_email", edt_join_email3.getText().toString());
                parmas.put("member_id", edt_join_id3.getText().toString());
                parmas.put("member_phone", edt_join_phone3.getText().toString());

                return parmas;

            }
        };

        queue.add(stringRequest);


    }
}