package com.kpinfotech.activity;

import java.io.File;
import java.util.ArrayList;

import com.kpinfotech.adapter.Adapter_File;
import com.kpinfotech.filechooser.R;
import com.kpinfotech.fileselector.FileSelectionActivity;
import com.kpinfotech.interfaces.Interface_File;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity implements OnClickListener, Interface_File {
	
	Activity activity;
	
	Button btnselect;
	ListView lvlist;
	
	int FILES = 100;
	private static final String FILES_TO_UPLOAD = "upload";
	
	ArrayList<String> fileName = new ArrayList<String>();
	ArrayList<String> filePath = new ArrayList<String>();
	
	Adapter_File a_File;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		init();
	}

	private void init() {
		activity = (Activity) MainActivity.this;
		
		btnselect = (Button) findViewById(R.id.btnselect);
		lvlist = (ListView) findViewById(R.id.lvlist);
		
		a_File = new Adapter_File(activity, fileName, filePath);
		lvlist.setAdapter(a_File);
		a_File.notifyDataSetChanged();
		
		btnselect.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		
		case R.id.btnselect:
			Intent fileIntent = new Intent(activity, FileSelectionActivity.class);
            activity.startActivityForResult(fileIntent, FILES);
			break;
			
		default:
			break;
        }
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if(requestCode == FILES && resultCode == RESULT_OK) {

			ArrayList<File> files = (ArrayList<File>) data.getSerializableExtra(FILES_TO_UPLOAD); //file array list
            
			for (File file : files) {
				fileName.add(file.getName());
				filePath.add(file.getAbsolutePath());
			}
			
			a_File.notifyDataSetChanged();
		}
	}

	@Override
	public void removeFile(int position) {
		fileName.remove(position);
		filePath.remove(position);
		
		a_File.notifyDataSetChanged();
	}

}