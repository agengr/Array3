package com.wilis.array3;

import android.app.TabActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class array3 extends TabActivity {
	List<almag> model=new ArrayList<almag>();
	almagAdapter adapter=null;
	EditText nama=null;
	EditText alamat=null;
	RadioGroup jekel=null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		nama=(EditText)findViewById(R.id.nama);
		alamat=(EditText)findViewById(R.id.alamat);
		jekel=(RadioGroup)findViewById(R.id.jekel);
		
		Button save=(Button)findViewById(R.id.save);
		
		save.setOnClickListener(onSave);
		
		ListView list=(ListView)findViewById(R.id.almag);
		
		adapter=new almagAdapter();
		list.setAdapter(adapter);
		
		TabHost.TabSpec spec=getTabHost().newTabSpec("tag1");
		
		spec.setContent(R.id.almag);
		spec.setIndicator("List", getResources()
																.getDrawable(R.drawable.list));
		getTabHost().addTab(spec);
		
		spec=getTabHost().newTabSpec("tag2");
		spec.setContent(R.id.details);
		spec.setIndicator("Details", getResources()
																	.getDrawable(R.drawable.alamat));
		getTabHost().addTab(spec);
		
		getTabHost().setCurrentTab(0);
		
		list.setOnItemClickListener(onListClick);
	}
	
	private View.OnClickListener onSave=new View.OnClickListener() {
		public void onClick(View v) {
			almag r=new almag();
			r.setNama(nama.getText().toString());
			r.setAlamat(alamat.getText().toString());
			
			switch (jekel.getCheckedRadioButtonId()) {
				case R.id.pria:
					r.setJekel("Pria");
					break;
					
				case R.id.perempuan:
					r.setJekel("Perempuan");
					break;
					
				
			}
			
			adapter.add(r);
		}
	};
	
	private AdapterView.OnItemClickListener onListClick=new AdapterView.OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent,
														 View view, int position,
														 long id) {
			almag r=model.get(position);
			
			nama.setText(r.getNama());
			alamat.setText(r.getAlamat());
			
			if (r.getJekel().equals("Pria")) {
				jekel.check(R.id.pria);
			}
			else if (r.getJekel().equals("Perempuan")) {
				jekel.check(R.id.perempuan);
			}
			
			
			getTabHost().setCurrentTab(1);
		}
	};
	
	class almagAdapter extends ArrayAdapter<almag> {
		almagAdapter() {
			super(array3.this, R.layout.row, model);
		}
		
		public View getView(int position, View convertView,
												ViewGroup parent) {
			View row=convertView;
			almagHolder holder=null;
			
			if (row==null) {													
				LayoutInflater inflater=getLayoutInflater();
				
				row=inflater.inflate(R.layout.row, parent, false);
				holder=new almagHolder(row);
				row.setTag(holder);
			}
			else {
				holder=(almagHolder)row.getTag();
			}
			
			holder.populateFrom(model.get(position));
			
			return(row);
		}
	}
	
	static class almagHolder {
		private TextView nama=null;
		private TextView alamat=null;
		private ImageView icon=null;
		private View row=null;
		
		almagHolder(View row) {
			this.row=row;
			
			nama=(TextView)row.findViewById(R.id.title);
			alamat=(TextView)row.findViewById(R.id.alamat);
			icon=(ImageView)row.findViewById(R.id.icon);
		}
		
		void populateFrom(almag r) {
			nama.setText(r.getNama());
			alamat.setText(r.getAlamat());
	
			if (r.getJekel().equals("Pria")) {
				icon.setImageResource(R.drawable.pria);
			}
			else if (r.getJekel().equals("Perempuan")) {
				icon.setImageResource(R.drawable.perempuan);
			}
			
		}
	}
}
