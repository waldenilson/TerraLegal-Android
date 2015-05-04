package br.gov.incra.sicop;

import java.util.ArrayList;
import java.util.List;

import br.gov.incra.sicop.abstractactivity.IActivity;
import br.gov.incra.sicop.controller.GlobalController;
import br.gov.incra.sicop.list.ListViewConfigAdapter;
import br.gov.incra.sicop.list.ListViewImageAdapter;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ProcessosActivity extends Activity implements IActivity, OnItemClickListener{

    private ProgressDialog pd;
    private ListView lv_processos;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_processos);
		getWindow().setLayout(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		init();
		
		List<String> nomes = new ArrayList<String>();

		SQLiteDatabase sql = ((GlobalController) getApplication()).getDatabase();
		if ( sql.isOpen() )
		{
			Cursor resultSet = sql.rawQuery("SELECT * FROM processo WHERE nome ILIKE '%"+ ((GlobalController)getApplication()).getNomePesquisa() +"%' ", null);
			resultSet.moveToFirst();
			
			while(resultSet.isAfterLast() == false)
			{
				nomes.add( resultSet.getString(2) );
				resultSet.moveToNext();
			}
		}
		else
		{
			Toast.makeText(this, "Não acessou a base de dados", Toast.LENGTH_LONG).show();
			finish();
		}

		ListViewConfigAdapter lv = new ListViewConfigAdapter(this, nomes);
		lv_processos.setAdapter(lv);
		lv_processos.setTextFilterEnabled(true);
		lv_processos.setOnItemClickListener(this);	

	}

	private void init(){
		lv_processos = (ListView) findViewById(R.id.lv_processos);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void click_bt_bar_back(View v) {
		// TODO Auto-generated method stub
		finish();
	}


	@Override
	public void abrirDialogProcessamento() {
		// TODO Auto-generated method stub
		pd = new ProgressDialog(this);
		pd.setTitle("");
		pd.setMessage(getString(R.string.ale_buscando_dados));
		pd.setCancelable(true);
		pd.setIndeterminate(true);
		pd.show();		
	}

	@Override
	public void fecharDialogProcessamento() {
		// TODO Auto-generated method stub
		if(pd != null)
			if(pd.isShowing())
				pd.dismiss();		
	}

	@Override
	public void finish() 
	{
		setResult(1, getIntent());
		super.finish();
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}

}
