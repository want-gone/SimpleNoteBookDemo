package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;
import com.example.bean.Note;
import com.example.mynotebook.R;

public class NoteAdapter extends BaseAdapter {
	

    private List<Note>   data;

    private LayoutInflater inflater;

    public NoteAdapter(Context context,List<Note> data) {
    	this.data = data;
        inflater=LayoutInflater.from(context);
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
            convertView=inflater.inflate(R.layout.item_listview_notes,null);
            holder.tvTitle= (TextView) convertView.findViewById(R.id.tv_title);
            holder.tvDate= (TextView) convertView.findViewById(R.id.tv_date);
            convertView.setTag(holder);
        }else
        {
            holder= (ViewHolder) convertView.getTag();
        }

        holder.tvTitle.setText(data.get(position).getTitle()+"");
        holder.tvDate.setText(data.get(position).getDate()+"");
        return convertView;
    }

    public static class ViewHolder{
        TextView tvTitle;
        TextView tvDate;
    }
}
