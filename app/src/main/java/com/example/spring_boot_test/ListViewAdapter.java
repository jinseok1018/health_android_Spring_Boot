package com.example.spring_boot_test;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.spring_boot_test.data.User;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<User> listItems = new ArrayList<User>();


    public ListViewAdapter(Context context){
        this.mContext = context;
    }
    @Override
    public int getCount() {
        return listItems.size();
    }
    @Override
    public Object getItem(int i) {
        return listItems.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // item.xml 레이아웃을 inflate해서 참조획득
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }
        // item.xml 의 참조 획득
        TextView txt_id = (TextView)convertView.findViewById(R.id.txt_id);
        TextView txt_content = (TextView)convertView.findViewById(R.id.txt_content);
        User user = listItems.get(position);

        // 가져온 데이터를 텍스트뷰에 입력
        txt_id.setText(user.getUserid());
        txt_content.setText("Height : " + user.getHeight() +" Sex : "+ (user.isSex()?"Men":"Women")  );

        return convertView;
    }
    public void addItem(String userid, boolean sex, int height){
        User user = new User();
        user.setUserid(userid);
        user.setSex(sex);
        user.setHeight(height);
        listItems.add(user);
    }
}


