package com.example.spring_boot_test.ListViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.spring_boot_test.R;
import com.example.spring_boot_test.data.UserDto;
import com.example.spring_boot_test.data.UserHealthDto;

import java.util.ArrayList;

public class MyListViewAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<UserHealthDto> listItems = new ArrayList<UserHealthDto>();

    public MyListViewAdapter(Context context){
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
            convertView = inflater.inflate(R.layout.mylist_item, parent, false);
        }
        // item.xml 의 참조 획득
        TextView txt_date = (TextView)convertView.findViewById(R.id.txt_date);
        TextView tv_content1 = (TextView)convertView.findViewById(R.id.tv_content1);
        TextView tv_content2 = (TextView)convertView.findViewById(R.id.tv_content2);

        UserHealthDto user = listItems.get(position);

        // 가져온 데이터를 텍스트뷰에 입력
        txt_date.setText(user.getDate());
        tv_content1.setText("weight : " + user.getWeight() +" body_fat : " + user.getBody_fat() +" body_muscle : " + user.getBody_muscle() );
        tv_content2.setText("menu_planner : " + user.getMenu_planner() +" exercise_method : " + user.getExercise_method());
        return convertView;
    }
    public void addItem(String date, float weight, float body_fat, float body_muscle, String menu_planner, String exercise_method){
        UserHealthDto user = new UserHealthDto();
        user.setDate(date);
        user.setWeight(weight);
        user.setBody_fat(body_fat);
        user.setBody_muscle(body_muscle);
        user.setMenu_planner(menu_planner);
        user.setExercise_method(exercise_method);
        listItems.add(user);
    }
}


