package com.example.mynotebook;

import java.util.ArrayList;
import java.util.List;

import com.example.adapter.NoteEditAdapter;
import com.example.adapter.NoteEditAdapter.ViewHolder;
import com.example.bean.Note;
import com.example.db.NoteDAO;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class EditActivity extends BaseActivity {
	private Button checkedAll,checkedNoting,checkedDel;
	private ListView lv;
	private NoteEditAdapter adapter;
	private List<Note> data;
	private NoteDAO dao;
	 private int checkNum;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
		
		initView();
		dao = new NoteDAO(helper);
		lv = (ListView) this.findViewById(R.id.lv_show_notes);
		
		dao = new NoteDAO(helper);
		data = new ArrayList<Note>();
		data.clear();
		List<Note> notes = dao.queryAll();
		data.addAll(notes);
		adapter = new NoteEditAdapter(this, data);
		adapter.notifyDataSetChanged();
		lv.setAdapter(adapter);
		
		 // 全选按钮的回调接口
		checkedAll.setOnClickListener(new OnClickListener() {

			@Override
            public void onClick(View v) {
                // 遍历list的长度，将MyAdapter中的map值全部设为true
                for (int i = 0; i < data.size(); i++) {
                    NoteEditAdapter.getIsSelected().put(i, true);
                }
                // 数量设为list的长度
                checkNum = data.size();
                // 刷新listview
                dataChanged();
            }
        });

        // 反选按钮的回调接口
        checkedNoting.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // 遍历list的长度，将已选的设为未选，未选的设为已选
                for (int i = 0; i < data.size(); i++) {
                    if (NoteEditAdapter.getIsSelected().get(i) == true) {
                    	NoteEditAdapter.getIsSelected().put(i, false);
                        checkNum--;
                    } else {
                    	NoteEditAdapter.getIsSelected().put(i, true);
                        checkNum++;
                    }
                }
                // 刷新listview和TextView的显示
                dataChanged();
            }
        });
        
        checkedDel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				for (int i = 0; i < data.size(); i++) {
                    if (NoteEditAdapter.getIsSelected().get(i) == true) {
                    	Note note = data.get(i);
                        dao.del(note.getDate());
                    }
                }
				Toast.makeText(getApplicationContext(), "删除成功！", Toast.LENGTH_LONG).show();
			}
		} );
        
        // 绑定listView的监听器
        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                    long arg3) {

                // 取得ViewHolder对象，这样就省去了通过层层的findViewById去实例化我们需要的cb实例的步骤
            	ViewHolder holder = (ViewHolder) arg1.getTag();
                // 改变CheckBox的状态
                holder.checkBox.toggle();
                // 将CheckBox的选中状况记录下来
                NoteEditAdapter.getIsSelected().put(arg2, holder.checkBox.isChecked()); 
                // 调整选定条目
                if (holder.checkBox.isChecked() == true) {
                    checkNum++;
                } else {
                    checkNum--;
                }        
            }
        });
    }
	
	    // 刷新listview的显示
	    private void dataChanged() {
	        // 通知listView刷新
	    	adapter.notifyDataSetChanged();
	    }
	
		private void initView() {
			checkedAll = (Button) findViewById(R.id.btn_checkall);
			checkedNoting = (Button) findViewById(R.id.btn_checknothing);
			checkedDel = (Button) findViewById(R.id.btn_del);
		}
		
		public void click(View view) {
			Intent intent = new Intent(EditActivity.this, MainActivity.class);
			EditActivity.this.finish();
			startActivity(intent);
		}
}
