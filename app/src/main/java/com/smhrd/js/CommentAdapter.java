package com.smhrd.js;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CommentAdapter extends BaseAdapter {

    private ArrayList<CommentDTO> list = new ArrayList<CommentDTO>();

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
        CommentDTO dto = list.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (!dto.getComment().equals(" ")){

                convertView = inflater.inflate(R.layout.comment, parent, false);
                TextView comment_lol_name = convertView.findViewById(R.id.comment_lol_name);
                TextView comment_text = convertView.findViewById(R.id.comment_text);

                comment_lol_name.setText(dto.getLol_name());
                comment_text.setText(dto.getComment());

            }else{
                convertView = inflater.inflate(R.layout.comment2,parent,false);
            }

        return convertView;
    }

    public void addItem(String lol_name, String chat, String num){
        CommentDTO dto = new CommentDTO(lol_name,chat, num);
        list.add(dto);
    }
}
