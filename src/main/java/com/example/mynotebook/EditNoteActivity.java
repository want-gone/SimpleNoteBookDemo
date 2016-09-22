package com.example.mynotebook;

import java.util.Calendar;

import com.example.bean.Note;
import com.example.db.NoteDAO;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditNoteActivity extends BaseActivity{
	
	private EditText etTitle,etContent;
	private Note note;
	private NoteDAO dao;
	private Calendar calendar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.avtivity_show_note);
		Intent intent = getIntent();
		note = (Note) intent.getSerializableExtra("note");
		dao = new NoteDAO(helper);
		initView();
		
	}
	
	public void click(View view) {
	        String title = etTitle.getText().toString();
	        String content = etContent.getText().toString();
	        note.setTitle(title);
	        note.setContent(content);
	        Intent intent2 = new Intent(EditNoteActivity.this, MainActivity.class);
		switch (view.getId()) {
		case R.id.btn_show_update:
			dao.update(note);
			Toast.makeText(getApplicationContext(), "修改成功！", Toast.LENGTH_LONG).show();
			break;

		case R.id.btn_show_delete:
			dao.del(note.getDate());
			Toast.makeText(getApplicationContext(), "删除成功！", Toast.LENGTH_LONG).show();
			break;
		}
		this.finish();
		startActivity(intent2);
	}

	private void initView() {
		etTitle = (EditText) findViewById(R.id.et_show_title);
		etContent = (EditText) findViewById(R.id.et_show_content);
		etTitle.setText(note.getTitle().toString());
		etContent.setText(note.getContent().toString());
	}
}
