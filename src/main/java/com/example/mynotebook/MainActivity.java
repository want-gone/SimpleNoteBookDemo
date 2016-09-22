package com.example.mynotebook;

import java.util.ArrayList;
import java.util.List;

import com.example.adapter.NoteAdapter;
import com.example.bean.Note;
import com.example.db.NoteDAO;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends BaseActivity {
	private ListView lv;
	private NoteAdapter adapter;
	private List<Note> data;
	private NoteDAO dao;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		dao = new NoteDAO(helper);
		
		lv = (ListView) this.findViewById(R.id.lv_show_notes);
		
		dao = new NoteDAO(helper);
		data = new ArrayList<Note>();
		List<Note> notes = dao.queryAll();
		data.clear();
		data.addAll(notes);
		adapter = new NoteAdapter(this, data);
		adapter.notifyDataSetChanged();
		lv.setAdapter(adapter);
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Note note = data.get(position);
				Intent intent3 = new Intent(MainActivity.this, EditNoteActivity.class);
				intent3.putExtra("note", note);
				MainActivity.this.finish();
				startActivity(intent3);
			}
		});
	}
	public void click(View view) {
		switch (view.getId()) {
		case R.id.btn_edit:
			Intent intent = new Intent(this, EditActivity.class);
			this.finish();
			startActivity(intent);
			break;
		case R.id.btn_add:
			Intent intent2 = new Intent(this, AddNoteActivity.class);
			this.finish();
			startActivity(intent2);
			break;
		}
	}
}
