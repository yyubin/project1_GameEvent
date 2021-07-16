package com.smhrd.js;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    private EditText edt_login_id, edt_login_pw;
    private Button btn_login, btn_join_go, btn_find1,btn_find2;

    private RequestQueue queue;
    private StringRequest stringRequest;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt_login_id = findViewById(R.id.edt_login_id);
        edt_login_pw = findViewById(R.id.edt_login_pw);


        btn_login = findViewById(R.id.btn_login);
        btn_join_go = findViewById(R.id.btn_join_go);
        btn_find1=findViewById(R.id.btn_find1);
        btn_find2=findViewById(R.id.btn_find2);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("result","dd");
                sendRequest();

            }
        });

        btn_join_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
Intent intent = new Intent(getApplicationContext(), join.class);
startActivity(intent);
            }
        });

        btn_find1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),id_find.class);
                startActivity(intent);

            }
        });
        btn_find2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),pw_find.class);
                startActivity(intent);
            }
        });




    }

    public void sendRequest() {
        queue = Volley.newRequestQueue(this);
        String url = "http://121.147.52.82:3100/Login";
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Server로부터 데이터를 받아온 곳
                Log.v("resultValue", response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String value = jsonObject.getString("check");

                    Log.v("resultValue", value);
                    if (value.equals("t")) {
                        String id = jsonObject.getString("member_id");
                        String pw = jsonObject.getString("member_pw");
                        String name = jsonObject.getString("member_name");
                        String lol_name = jsonObject.getString("member_lol_name");
                        String phone = jsonObject.getString("member_phone");
                        String email = jsonObject.getString("member_email");
                        String team_name = jsonObject.getString("team_name");
                        String member_code = jsonObject.getString("member_code");

                        MemberDTO dto = new MemberDTO(id,pw,name,phone,lol_name,email, team_name,member_code);
                        Gson gson = new Gson();
                        String member = gson.toJson(dto);
                        PreferenceManager.setString(getApplicationContext(),"login", member);
                        Log.v("sss",member);
                        Toast.makeText(getApplicationContext(), "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show();


                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);



                    } else if (value.equals("f")) {
                        Toast.makeText(getApplicationContext(), "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show();


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
                parmas.put("member_id", edt_login_id.getText().toString());
                parmas.put("member_pw", edt_login_pw.getText().toString());

                return parmas;

            }
        };

        queue.add(stringRequest);


    }
}