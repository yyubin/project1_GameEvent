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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class join extends AppCompatActivity {

    private EditText edt_join_id, edt_join_pw, edt_join_name, edt_join_phone, edt_join_email, edt_join_nickname;
    private Button btn_join_create;

    private RequestQueue queue;
    private StringRequest stringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        edt_join_id = findViewById(R.id.edt_join_id);
        edt_join_pw = findViewById(R.id.edt_join_pw);
        edt_join_name = findViewById(R.id.edt_join_name);
        edt_join_phone = findViewById(R.id.edt_join_phone);
        edt_join_email = findViewById(R.id.edt_join_email);
        edt_join_nickname = findViewById(R.id.edt_join_nickname);

        btn_join_create = findViewById(R.id.btn_join_create);

        btn_join_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest();
            }
        });
    }

    public void sendRequest() {
        queue = Volley.newRequestQueue(this);
        String url = "http://59.0.234.45:3100/Join";
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Server로부터 데이터를 받아온 곳
                Log.v("resultValue", response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String value = jsonObject.getString("check");
                    Log.v("resultValue", value);
                    if (value.equals("ok")) {
                        Intent intent = new Intent(getApplicationContext(), join_success.class);
                        startActivity(intent);
                    } else if (value.equals("fail")) {
                        Toast.makeText(getApplicationContext(), "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show();

                        edt_join_id.setText("");
                        edt_join_pw.setText("");
                        edt_join_name.setText("");
                        edt_join_phone.setText("");
                        edt_join_email.setText("");
                        edt_join_nickname.setText("");

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
                parmas.put("member_id", edt_join_id.getText().toString());
                parmas.put("member_pw", edt_join_pw.getText().toString());
                parmas.put("member_name", edt_join_name.getText().toString());
                parmas.put("member_email", edt_join_email.getText().toString());
                parmas.put("member_phone", edt_join_phone.getText().toString());
                parmas.put("member_lol_name", edt_join_nickname.getText().toString());
                return parmas;

            }
        };

        queue.add(stringRequest);

    }
}