package com.smhrd.js;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

public class id_find extends AppCompatActivity {

    private EditText edt_join_name2, edt_join_email2, edt_join_phone2;
    private RequestQueue queue;
    private StringRequest stringRequest;
    private Button btn_id_find;
    private String find_id;
    private int a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id_find);

        edt_join_name2=findViewById(R.id.edt_join_name2);
        edt_join_email2=findViewById(R.id.edt_join_email2);
        edt_join_phone2=findViewById(R.id.edt_join_phone2);
        btn_id_find=findViewById(R.id.btn_id_find);

        btn_id_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendRequest();

            }
        });


    }

    public void sendRequest() {
        queue = Volley.newRequestQueue(this);
        String url = "http://121.147.52.82:3100/find_id";
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Server로부터 데이터를 받아온 곳
                Log.v("idiid",response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    find_id = jsonObject.getString("member_id");
                    String check = jsonObject.getString("check");
                    if(check.equals("ok")){
                        AlertDialog.Builder builder = new AlertDialog.Builder(id_find.this);

                        builder.setTitle("아이디 찾기").setMessage("요청하신 아이디 : "+find_id);

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
                        Toast.makeText(id_find.this,"입력하신 정보가 존재하지 않습니다",Toast.LENGTH_SHORT).show();
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
                parmas.put("member_email", edt_join_email2.getText().toString());
                parmas.put("member_name", edt_join_name2.getText().toString());
                parmas.put("member_phone", edt_join_phone2.getText().toString());

                return parmas;

            }
        };

        queue.add(stringRequest);


    }
}