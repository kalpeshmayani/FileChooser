package com.kpinfotech.adapter;

import java.util.ArrayList;

import com.kpinfotech.filechooser.R;
import com.kpinfotech.interfaces.Interface_File;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Adapter_File extends BaseAdapter {
	
	Activity activity;
	
	LayoutInflater myInflater;
	ArrayList<String> fileName = new ArrayList<String>();
	ArrayList<String> filePath = new ArrayList<String>();
	
	Interface_File i_File;
	
	public Adapter_File(Activity activity, ArrayList<String> fileName, ArrayList<String> filePath) {
		this.myInflater =  LayoutInflater.from(activity);
		this.i_File = (Interface_File) activity;
		this.activity = activity;
		this.fileName = fileName;
		this.filePath = filePath;
	}

	final class viewHolder
	{
		TextView tvdata;
		TextView ivcattachicon;
	}

	@Override
	public int getCount() {	return fileName.size();	}

	@Override
	public Object getItem(int position)	{	return fileName.get(position);	}

	@Override
	public long getItemId(int position)	{	return position;	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		
		final viewHolder holder = new viewHolder();
		convertView = myInflater.inflate(R.layout.adapter_attachmentlist, null);
		
		holder.tvdata = (TextView)convertView.findViewById(R.id.tvfilename);
		holder.ivcattachicon = (TextView)convertView.findViewById(R.id.ivcattachicon);
		
		holder.tvdata.setSelected(true);
		holder.tvdata.setText(fileName.get(position));
		
		holder.ivcattachicon.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				i_File.removeFile(position);
			}
		});
		
		return convertView;
	}

}