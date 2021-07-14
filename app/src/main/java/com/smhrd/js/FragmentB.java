package com.smhrd.js;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class FragmentB extends Fragment {

    private Thread t;
    private ImageView arena_img;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View fragment = inflater.inflate(R.layout.activity_main_page, container, false);

        Button btn_creat_team_go = fragment.findViewById(R.id.btn_creat_team_go);
        Button btn_recruiting_go = fragment.findViewById(R.id.btn_recruiting_go);
        Button btn_freechat_go = fragment.findViewById(R.id.btn_freechat_go);
        Button btn_QAchat_go = fragment.findViewById(R.id.btn_QAchat_go);
        Button btn_rank_go = fragment.findViewById(R.id.btn_rank_go);
        arena_img = fragment.findViewById(R.id.arena_img);

        t = new Thread(new MyThread());
        t.start();

        arena_img.setOnClickListener(new MyListener());

        btn_rank_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ranking.class);
                startActivity(intent);
            }
        });

        btn_creat_team_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CreateTeam.class);
                startActivity(intent);
            }
        });

        btn_recruiting_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Recruting_chat.class);
                startActivity(intent);
            }
        });

        btn_freechat_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),free_board.class);
                startActivity(intent);
            }
        });

        btn_QAchat_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), QuestionAnswer.class);
                startActivity(intent);
            }
        });

        return fragment;
    }

    class MyListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getContext(), battle_attend.class);
            startActivity(intent);

        }

    }





    public class MyThread implements Runnable {
        int cnt = 0;
        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()) {//is가 붙어있으면 true or false
                    Thread.sleep(1000);
                    cnt++;
                    Message message = myHandler.obtainMessage();
                    message.arg1 = cnt;
                    myHandler.sendMessage(message);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(msg.arg1%3==0){
                arena_img.setImageResource(R.drawable.arena1);
            }else if(msg.arg1%3==1){
                arena_img.setImageResource(R.drawable.arena2);
            }else if(msg.arg1%3==2){
                arena_img.setImageResource(R.drawable.arena3);
            }
        }
    };



}