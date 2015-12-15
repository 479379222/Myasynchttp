package com.example.jason.myhttpasynctest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MylistActivity extends AppCompatActivity {

    private ListView lstView;
    private String[] ChinaCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mylist);

        ChinaCity=new String[]{"北京","上海","天津"};

        lstView=(ListView)findViewById(R.id.lstView);
        lstView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ChinaCity));

        lstView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MylistActivity.this, ChinaCity[position], Toast.LENGTH_SHORT).show();
            }
        });
    }



}
