package com.smhrd.js;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

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

public class fragA extends Fragment {

    private TextView member_name, member_lol_name, member_phone, member_email;
    private Button btn_mypage_logout, btn_mypage_delete;

    private RequestQueue queue;
    private StringRequest stringRequest;
    final String[] input_pw = {""};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View fragment = inflater.inflate(R.layout.frag_a, container, false);

        member_name=fragment.findViewById(R.id.member_name);
        member_lol_name=fragment.findViewById(R.id.my_team_name);
        member_email=fragment.findViewById(R.id.member_email);
        member_phone=fragment.findViewById(R.id.member_phone);

        btn_mypage_logout=fragment.findViewById(R.id.btn_mypage_logout);
        btn_mypage_delete=fragment.findViewById(R.id.btn_mypage_delete);

        btn_mypage_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceManager.setString(getContext(),"logout", "");
                Intent intent = new Intent(getContext(),Login.class);
                startActivity(intent);

            }
        });

        btn_mypage_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setTitle("회원 탈퇴");
                alert.setMessage("비밀번호를 입력해주세요.");
                final EditText pw = new EditText(getContext());

                InputFilter[] FilterArray = new InputFilter[1];
                FilterArray[0] = new InputFilter.LengthFilter(18); //글자수 제한
                pw.setFilters(FilterArray);
                alert.setView(pw);
                alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String delete_pw = pw.getText().toString();
                        pw.setText(delete_pw);
                        input_pw[0]=pw.getText().toString();
                        Log.v("pwpw",input_pw[0]);
                        SharedPreferences pref = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();

                        AlertDialog.Builder alert1 = new AlertDialog.Builder(getContext());
                        editor.putString("delete_pw", delete_pw);
                        editor.commit();

                        alert1.setMessage("정말 탈퇴하시겠습니까?");
                        alert1.setPositiveButton("예", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sendRequest();
                            }
                        });
                        alert1.setNeutralButton("아니요", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }

                        });
                        alert1.show();
                    }
                });

                alert.setNegativeButton("취소",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) { //취소 버튼을 클ㅣ
                    }
                });
                alert.show();




            }
        });




        String member = PreferenceManager.getString(getContext(),"login");
        try {

            JSONObject jsonObject = new JSONObject(member);

            Log.v("json", String.valueOf(jsonObject));

            String name = jsonObject.getString("name");
            String lol_name = jsonObject.getString("lol_name");
            String phone = jsonObject.getString("tel");
            String email = jsonObject.getString("email");

            member_lol_name.setText(lol_name);
            member_name.setText(name);
            member_phone.setText(phone);
            member_email.setText(email);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return fragment;
    }

    public void sendRequest() {
        queue = Volley.newRequestQueue(getContext());

        String url = "http://121.147.52.82:3100/Delete";
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String check = jsonObject.getString("check");
                    if(check.equals("ok")){
                        Intent intent = new Intent(getContext(),Login.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getContext(), "입력하신 비밀번호가 맞지 않습니다.", Toast.LENGTH_SHORT).show();
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
                Map<String, String> parmas = new HashMap<String, String>();
                //Server로 데이터 보낼 시 넣어주는 곳

                String member = PreferenceManager.getString(getContext(),"login");
                try {
                    JSONObject jsonObject = new JSONObject(member);
                    String id = jsonObject.getString("id");
                    String lol_name = jsonObject.getString("lol_name");
                    parmas.put("member_id",id);
                    parmas.put("member_lol_name",lol_name);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                parmas.put("member_pw", input_pw[0]);


                return parmas;

            }
        };

        queue.add(stringRequest);


    }


}
