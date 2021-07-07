package com.smhrd.js;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Table extends AppCompatActivity {

    private TextView tv_board, tv_board_title;
    final int[] selectedBoard = {0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        tv_board = findViewById(R.id.tv_board);
        tv_board_title = findViewById(R.id.tv_board_title);

        tv_board.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] boards = new String[]{"자유게시판","Q&A게시판","팀생성 권한 요청 게시판","구인 게시판"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(Table.this);
                dialog .setTitle("게시판을 선택하세요")
                        .setSingleChoiceItems(boards, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                selectedBoard[0]=which;
                            }
                        })
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(selectedBoard[0]==0){
                                    tv_board_title.setText("자유게시판");
                                }else if(selectedBoard[0]==1){
                                    tv_board_title.setText("Q&A게시판");
                                }else if(selectedBoard[0]==2){
                                    tv_board_title.setText("팀생성 권한 요청 게시판");
                                }else{
                                    tv_board_title.setText("구인 게시판");
                                }
                                Toast.makeText(Table.this,boards[selectedBoard[0]],Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNeutralButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(Table.this,"취소 버튼을 눌렀습니다",Toast.LENGTH_SHORT).show();
                            }
                        });
                dialog.create();
                dialog.show();


            }
        });
    }
}