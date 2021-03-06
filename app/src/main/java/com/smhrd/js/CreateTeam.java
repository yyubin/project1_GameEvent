package com.smhrd.js;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

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

public class CreateTeam extends AppCompatActivity {

    private EditText edt_create_team_name, edt_create_team_text;
    private Button btn_img_up, btn_create_submit;

    private ImageView img;
    private String img_path;
    private int ad;
    String choice;

    private RequestQueue queue;
    private StringRequest stringRequest;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_team);

        edt_create_team_name = findViewById(R.id.edt_create_team_name);
        edt_create_team_text = findViewById(R.id.edt_create_team_text);
        btn_img_up = findViewById(R.id.btn_img_up);
        btn_create_submit = findViewById(R.id.btn_create_submit);
        img = findViewById(R.id.img);


        btn_img_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//
//                startActivityForResult(intent, 1);
//                Intent intent = new Intent(getApplicationContext(),.class);
//                startActivity(intent);

                Intent intent = new Intent(getApplicationContext(),imgChoice.class);
                startActivityForResult(intent,1);


            }
        });

        btn_create_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest();

            }
        });



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode != Activity.RESULT_OK) {
                return;
            }
            choice = data.getStringExtra("img");
            String pac = this.getPackageName();
            Log.v("aaaa",choice+"");
            String img1 = "@drawable/logo"+choice;

           ad = getResources ().getIdentifier(img1,"drawable",pac);
            img.setImageResource(ad);
        }
    }

    // ????????? ???????????? image View??? ????????????.
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 1) {
//            if (resultCode == RESULT_OK) {
//
//                Uri uri = null;
//                if (data != null) {
//                    uri = data.getData();
//                }
//                if (uri != null) {
//                    img.setImageURI(uri);
//                    // ?????? ?????? ?????? ??? ?????? ????????? ??????
//                    img_path = createCopyAndReturnRealPath(CreateTeam.this.getApplicationContext(), uri);
//                    // ??????????????? ?????? ????????? ??? ???  ?????? Dialog
//                    new AlertDialog.Builder(this).setMessage(uri.toString()+"\n"+img_path).create().show();
//                    Log.v("path",uri.toString()+"\n"+img_path);
//
//                }
//            }
//        }
//    }


    public void sendRequest() {

        queue = Volley.newRequestQueue(this);
        String url = "http://121.147.52.82:3100/leader_team_name_register_temp";

        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String team_name = jsonObject.getString("team_name");
                    String img = jsonObject.getString("team_img_logo");
                    String intro =jsonObject.getString("team_introduce");

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Server ????????? Error ???????????? ?????? ???
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Server??? ????????? ?????? ??? ???????????? ???
                Map<String, String> parmas = new HashMap<String, String>();
                String member = PreferenceManager.getString(getApplicationContext(),"login");
                try {
                    JSONObject jsonObject = new JSONObject(member);
                    parmas.put("member_lol_name",jsonObject.getString("lol_name"));
                    parmas.put("member_code",jsonObject.getString("member_code"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                parmas.put("team_name",edt_create_team_name.getText().toString());
                parmas.put("team_introduce",edt_create_team_text.getText().toString());
                parmas.put("team_img_logo",choice);

                return parmas;

            }
        };

        queue.add(stringRequest);


    }


//    // ???????????? ????????? ??? ????????? ?????????
//    @Nullable
//    public String createCopyAndReturnRealPath(@NonNull Context context, @NonNull Uri uri) {
//        final ContentResolver contentResolver = context.getContentResolver();
//
//        if (contentResolver == null)
//            return null;
//
//
//        // ?????? ????????? ??????
//        String filePath = context.getApplicationInfo().dataDir + File.separator
//                + System.currentTimeMillis();
//
//
//        File file = new File(filePath);
//        try {
//            // ??????????????? ?????? uri ??? ??????  ???????????? ????????? ???????????? ?????? ?????????.
//
//            InputStream inputStream = contentResolver.openInputStream(uri);
//            if (inputStream == null)
//                return null;
//            // ????????? ???????????? ?????? ??????????????? file ?????????  ???????????? ????????? ????????????.
//
//            OutputStream outputStream = new FileOutputStream(file);
//            byte[] buf = new byte[1024];
//            int len;
//            while ((len = inputStream.read(buf)) > 0)
//                outputStream.write(buf, 0, len);
//            outputStream.close();
//
//            inputStream.close();
//
//
//        } catch (IOException ignore) {
//            return null;
//        }
//
//        return file.getAbsolutePath();
//    }



}
