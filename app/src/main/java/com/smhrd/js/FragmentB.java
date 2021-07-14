package com.smhrd.js;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class FragmentB extends Fragment {

    private MainActivity mainActivity;

    public void onAttacth(Context context){
        super.onAttach(context);
        mainActivity = (MainActivity)getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View fragment = inflater.inflate(R.layout.activity_main_page, container, false);

        Button btn_creat_team_go = fragment.findViewById(R.id.btn_creat_team_go);
        Button btn_recruiting_go = fragment.findViewById(R.id.btn_recruiting_go);
        Button btn_freechat_go = fragment.findViewById(R.id.btn_freechat_go);
        Button btn_QAchat_go = fragment.findViewById(R.id.btn_QAchat_go);
        Button btn_rank_go = fragment.findViewById(R.id.btn_rank_go);

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
}