package com.smhrd.js;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class BattleAdapter extends BaseAdapter {

    private ArrayList<BattleDTO> list = new ArrayList<>();

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    public void addItem(String team_name){
        BattleDTO dto = new BattleDTO(team_name);
        list.add(dto);
    }


}
