package com.test.chatlist.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.test.chatlist.R;
import com.test.chatlist.constant.BusinessConstant;
import com.test.chatlist.model.Group;

public class DetailActivity extends AppCompatActivity {

    private TextView nameTV,creatorTV;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();
        initData();
    }

    private void initView(){
        nameTV = findViewById(R.id.tv_name);
        creatorTV = findViewById(R.id.tv_creator);
        imageView = findViewById(R.id.iv_icon);
        setTitle(R.string.title_detail);
    }

    private void initData(){
        String info = getIntent().getStringExtra(BusinessConstant.GROUP_INFO);
        if(!TextUtils.isEmpty(info)){
            Group group = new Gson().fromJson(info,Group.class);
            if(group!=null){
                nameTV.setText(group.getName());
                creatorTV.setText(group.getCreator());
                imageView.setImageResource(group.getIconId());
            }
        }
    }
}