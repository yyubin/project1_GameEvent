package com.smhrd.js;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RankAdapter extends BaseAdapter {

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
            if (convertView == null) { //xml가져온게 비어있으면

                if(dto.getRank()<=3){
                    convertView = inflater.inflate(R.layout.rank1, parent, false);
                }else {
                    convertView = inflater.inflate(R.layout.rank2, parent, false);
                }
            }

        TextView tv_rank_rank = convertView.findViewById(R.id.tv_rank_rank);
        TextView tv_rank_name = convertView.findViewById(R.id.tv_rank_name);
        TextView tv_rank_score = convertView.findViewById(R.id.tv_rank_score);

        tv_rank_rank.setText(dto.getRank()+"위");
        tv_rank_name.setText(dto.getTeam_name());
        tv_rank_score.setText(dto.getTeam_score());

        return convertView;
    }

    public void addItem(int rank, String team_name, String team_score){
        RankDTO dto = new RankDTO(rank,team_name,team_score);
        list.add(dto);
    }
}
