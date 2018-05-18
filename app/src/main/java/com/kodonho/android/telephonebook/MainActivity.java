package com.kodonho.android.telephonebook;

import android.Manifest;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kodonho.android.telephonebook.adapter.PhoneAdapter;
import com.kodonho.android.telephonebook.domain.Phone;
import com.kodonho.android.telephonebook.domain.PhoneDAO;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    List<Phone> data;
    PhoneAdapter adapter;
    RecyclerView recyclerView;

    @Override
    public void init() {
        setContentView(R.layout.activity_main);
        loadData();
        setRecycler();
        setDataToAdapter();
    }

    private void loadData(){
        data = PhoneDAO.getPhoneList(this);
    }

    private  void setRecycler(){
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new PhoneAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setDataToAdapter(){
        adapter.setDataAndRefresh(data);
    }
}
