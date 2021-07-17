package com.smhrd.js;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RankAdapter2 extends BaseAdapter {
    private ArrayList<RankDTO> list = new ArrayList<RankDTO>();

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return  list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext(); //xml불러오기
        RankDTO dto = list.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.activity_main_page, parent, false);





        return convertView;
    }

    public void addItem(int rank, String team_name, String team_score){
        RankDTO dto = new RankDTO(rank,team_name,team_score);
        list.add(dto);
    }
}
