package com.example.mynotebook;

import android.app.Activity;
import android.os.Bundle;
import com.example.db.DBHelper;

public class BaseActivity extends Activity {


    protected DBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper=new DBHelper(this);
    }
}

