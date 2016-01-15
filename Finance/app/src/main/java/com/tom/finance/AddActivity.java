package com.tom.finance;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {
    private EditText edDate;
    private EditText edInfo;
    private EditText edAmount;
    private MyDBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        findViews();
        helper = MyDBHelper.getInstance(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    private void findViews() {
        edDate = (EditText) findViewById(R.id.ed_date);
        edInfo = (EditText) findViewById(R.id.ed_info);
        edAmount = (EditText) findViewById(R.id.ed_amount);
    }

    public void add(View v){
        String cdate = edDate.getText().toString();
        String info = edInfo.getText().toString();
        int amount = Integer.parseInt(edAmount.getText().toString());
        ContentValues values = new ContentValues();
        values.put("cdate", cdate);
        values.put("info", info);
        values.put("amount", amount);
        long id = helper.getWritableDatabase().insert("exp", null, values);
        Log.d("ADD", id+"");
    }
}
