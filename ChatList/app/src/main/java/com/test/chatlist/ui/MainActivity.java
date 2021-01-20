package com.test.chatlist.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.test.chatlist.R;
import com.test.chatlist.adapter.BaseAdapter;
import com.test.chatlist.adapter.GroupAdapter;
import com.test.chatlist.constant.BusinessConstant;
import com.test.chatlist.model.Group;

import java.util.ArrayList;
import java.util.List;

/*
 * 将 ViewHolder 和 Adapter 封装成 BaseViewHolder 和 BaseAdapter（使用泛型）
 * */
public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 0x123;
    private RecyclerView rv;
    private GroupAdapter groupAdapter;
    private List<Group> groupList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        rv = findViewById(R.id.rv_list);
    }

    private void initData() {
        groupAdapter = new GroupAdapter(this, groupList, R.layout.item_group);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider_inset));
        rv.addItemDecoration(itemDecoration);
        rv.setAdapter(groupAdapter);
        setTitle(R.string.title_main);
    }

    private void initEvent() {
        groupAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                String info = new Gson().toJson(groupList.get(position));
                intent.putExtra(BusinessConstant.GROUP_INFO, info);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.group_opt_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                return true;
            case R.id.clear:
                groupList.clear();
                groupAdapter.notifyDataSetChanged();
                return true;
            case R.id.exit:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE:
                String groupInfo = data.getStringExtra(BusinessConstant.GROUP_INFO);
                if (!TextUtils.isEmpty(groupInfo)) {
                    Group group = new Gson().fromJson(groupInfo, Group.class);
                    groupList.add(group);
                    groupAdapter.notifyDataSetChanged();
                }
                break;
        }

    }
}