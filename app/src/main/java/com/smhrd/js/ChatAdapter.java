package com.smhrd.js;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ChatAdapter extends BaseAdapter {

    private ArrayList<ChatDTO> list = new ArrayList<ChatDTO>();

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
        ChatDTO dto = list.get(position);

       String member = PreferenceManager.getString(parent.getContext(), "login");
        try {
            JSONObject jsonObject = new JSONObject(member);
            String myId = jsonObject.getString("lol_name");
            if (convertView == null) { //xml가져온게 비어있으면
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                if(dto.getId().equals(myId)){
                    convertView = inflater.inflate(R.layout.chatting2, parent, false);
                }else {
                    convertView = inflater.inflate(R.layout.chatting, parent, false);
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        TextView tv_chat_id = convertView.findViewById(R.id.tv_chat_id);
        TextView tv_chat_chat = convertView.findViewById(R.id.tv_chat_chat);

        tv_chat_id.setText(dto.getId());
        tv_chat_chat.setText(dto.getChat());

        return convertView;
    }

    public void addItem(String id, String chat){
        ChatDTO dto = new ChatDTO(id,chat);
        list.add(dto);
    }

}