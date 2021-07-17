package com.smhrd.js;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class BoardAdapter extends BaseAdapter {

    private ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext(); //xml불러오기
        BoardDTO dto = list.get(position);// DTO dto = list.get(position);

        if (convertView == null) { //xml가져온게 비어있으면
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.board, parent, false);
        }

        TextView tv_board_list_title = convertView.findViewById(R.id.tv_board_list_title);
//        TextView tv_board_list_num = convertView.findViewById(R.id.tv_board_list_num);
        TextView tv_board_list_text = convertView.findViewById(R.id.tv_board_list_text);
        TextView tv_board_list_time = convertView.findViewById(R.id.tv_board_list_time);
        TextView tv_board_list_name = convertView.findViewById(R.id.tv_board_list_name);

        tv_board_list_name.setText(dto.getName());
        tv_board_list_time.setText(dto.getTime());
//        tv_board_list_num.setText(dto.getNum());
        tv_board_list_title.setText(dto.getTitle());
        tv_board_list_text.setText(dto.getText());

        LinearLayout board_layout = (LinearLayout)convertView.findViewById(R.id.board_layout);
        board_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(dto.getBoard_num().equals("1")){
                    Intent intent = new Intent(parent.getContext(),boradsuc.class);
                    intent.putExtra("board_text_num",dto.getNum());
                    intent.putExtra("board_num",dto.getBoard_num());
                    intent.putExtra("name",dto.getName());

                    context.startActivity(intent);
                    Log.v("aa","인텐트성공");
                }else if(dto.getBoard_num().equals("2")){
                    Intent intent = new Intent(parent.getContext(),QuestionAnswer.class);
                }else if(dto.getBoard_num().equals("3")){
                    Intent intent = new Intent(parent.getContext(),Recruting_chat.class);
                }else{
                    Intent intent = new Intent(parent.getContext(),TeamCreateAttend.class);
                }



            }
        });



        return convertView;
    }

    public void addItem(String board_num,String name, String title, String text, String time, String num){
        BoardDTO dto = new BoardDTO(board_num,name,title,text,time,num);
        list.add(dto);
    }
}
