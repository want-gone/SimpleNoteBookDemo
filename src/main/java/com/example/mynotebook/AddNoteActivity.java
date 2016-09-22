package com.example.mynotebook;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.example.bean.Note;
import com.example.db.NoteDAO;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddNoteActivity extends BaseActivity{
	private EditText etTitle,etContent;
	private Calendar calendar;
	private NoteDAO dao;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addnote);
		dao = new NoteDAO(helper);
		initView();
	}
	private void initView() {
		etTitle = (EditText) findViewById(R.id.et_title);
		etContent = (EditText) findViewById(R.id.et_content);
	}
	public void click(View view) {
		Note note = null;
		String title = etTitle.getText().toString();
		String content = etContent.getText().toString();
		String date = getDate();
		if ("".equals(title) || "".equals(content)) {
			Toast.makeText(getApplicationContext(), "标题或者内容不能为空！", 0).show();
		}else {
			note = new Note(title, content, date);
			dao.add(note);
			Toast.makeText(getApplicationContext(), "保存成功！", 0).show();
			Intent intent = new Intent(AddNoteActivity.this, MainActivity.class);
			this.finish();
			startActivity(intent);
		}
	}
	private String getDate() {
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyy年MM月dd日 HH:mm:ss ");
		Date curDate = new Date(System.currentTimeMillis());//获取当前时间
		String str = formatter.format(curDate);
		return str;
	}
}
