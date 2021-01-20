package com.test.chatlist.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.test.chatlist.R;
import com.test.chatlist.constant.BusinessConstant;
import com.test.chatlist.model.Group;

import java.util.Random;

public class AddActivity extends AppCompatActivity {

    private EditText nameET,creatorET;
    private Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
        initEvent();
    }

    private void initView(){
        nameET = findViewById(R.id.et_name);
        creatorET = findViewById(R.id.et_creator);
        submitBtn = findViewById(R.id.btn_submit);
        setTitle(R.string.title_add);
    }

    private void initEvent(){
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameET.getText().toString();
                String creator = creatorET.getText().toString();
                if(TextUtils.isEmpty(name)){
                    Toast.makeText(AddActivity.this,R.string.toast_name_not_allow_empty,Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(creator)){
                    Toast.makeText(AddActivity.this,R.string.toast_creator_not_allow_empty,Toast.LENGTH_SHORT).show();
                    return;
                }

                Random random = new Random();
                Group group = new Group(name,creator,BusinessConstant.ICON_LIST[random.nextInt(4)]);
                Intent intent = new Intent(AddActivity.this,MainActivity.class);
                Gson gson = new Gson();
                intent.putExtra(BusinessConstant.GROUP_INFO,gson.toJson(group));
                setResult(MainActivity.REQUEST_CODE,intent);
                Toast.makeText(AddActivity.this,R.string.toast_create_success,Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AddActivity.this,MainActivity.class);
        intent.putExtra(BusinessConstant.GROUP_INFO,"");
        setResult(MainActivity.REQUEST_CODE,intent);
        super.onBackPressed();
    }
}