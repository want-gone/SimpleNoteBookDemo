package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import com.example.bean.Note;
import com.example.mynotebook.R;

public class NoteEditAdapter extends BaseAdapter {
	

    private List<Note>   data;

    private LayoutInflater inflater;

    private static HashMap<Integer,Boolean> isSelected;
    
    public NoteEditAdapter(Context context,List<Note> data) {
    	this.data = data;
        inflater=LayoutInflater.from(context);
        isSelected = new HashMap<Integer, Boolean>();
        
        initDate();
    }
    
 // 初始化isSelected的数据
    private void initDate(){
        for(int i=0; i<data.size();i++) {
            getIsSelected().put(i,false);
        }
    }


    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }

    @Override
    public Object getItem(int position) {
        return data!=null?data.get(position):null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder=null;
        if(convertView==null){
            holder=new ViewHolder();
            convertView=inflater.inflate(R.layout.item_listview_editnotes,null);
            holder.tvTitle= (TextView) convertView.findViewById(R.id.tv_title_edit);
            holder.checkBox  = (CheckBox) convertView.findViewById(R.id.cb_edit);
            convertView.setTag(holder);
        }else
        {
            holder= (ViewHolder) convertView.getTag();
        }

        holder.tvTitle.setText(data.get(position).getTitle()+"");
        holder.checkBox.setChecked(getIsSelected().get(position));
        return convertView;
    }

    public static HashMap<Integer,Boolean> getIsSelected() {
        return isSelected;
    }

    public static void setIsSelected(HashMap<Integer,Boolean> isSelected) {
        NoteEditAdapter.isSelected = isSelected;
    }
    
    public static class ViewHolder{
        public TextView tvTitle;
        public CheckBox checkBox;
    }
}
