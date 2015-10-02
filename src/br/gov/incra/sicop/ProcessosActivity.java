package br.gov.incra.sicop;

import java.util.ArrayList;
import java.util.List;

import br.gov.incra.sicop.abstractactivity.IActivity;
import br.gov.incra.sicop.controller.GlobalController;
import br.gov.incra.sicop.list.ListViewColorAdapter;
import br.gov.incra.sicop.list.ListViewConfigAdapter;
import br.gov.incra.sicop.list.ListViewDetailColorAdapter;
import br.gov.incra.sicop.list.ListViewImageAdapter;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
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
	private List<Integer> ids,cor;
	private List<String> nomes,localizacao,gleba;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_processos);
		getWindow().setLayout(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		init();
		
		try
		{

			SQLiteDatabase sql = ((GlobalController) getApplication()).getDatabase();
			if ( sql.isOpen() )
			{

				
					String s = "SELECT * FROM processo WHERE nome LIKE '%"+ ((GlobalController)getApplication()).getNomePesquisa() +"%' AND cadastro_pessoa LIKE '%"+((GlobalController)getApplication()).getCadastroPesquisa()+"%' ";
					
					Cursor resultSet = sql.rawQuery(s, null);
					if(resultSet.getCount()>0)
					{

						ids = new ArrayList<Integer>();
						nomes = new ArrayList<String>();
						localizacao = new ArrayList<String>();
						gleba = new ArrayList<String>();
						cor = new ArrayList<Integer>();

						resultSet.moveToFirst();
						while(resultSet.isAfterLast() == false )
						{
							ids.add(resultSet.getInt(0));
							if(resultSet.getString(7).contains("Anexo."))
								nomes.add( resultSet.getString(3)+" (Anexo)" );
							else
								nomes.add( resultSet.getString(3) );
							
							gleba.add( resultSet.getString(9)+" / "+resultSet.getString(8) );
							localizacao.add( resultSet.getString(5) );
							if(resultSet.getString(6).equals("PROCESSO RURAL"))
								cor.add( Color.rgb(102, 255, 102) );
							else if(resultSet.getString(6).equals("CLAUSULAS RESOLUTIVAS"))
								cor.add( Color.rgb(102, 255, 255) );
							else
								cor.add( Color.rgb(255, 153, 51) );	
							resultSet.moveToNext();
						}
						ListViewDetailColorAdapter lvdc = new ListViewDetailColorAdapter(this, nomes, localizacao, gleba, cor);
						lv_processos.setAdapter(lvdc);
						lv_processos.setTextFilterEnabled(true);
						lv_processos.setOnItemClickListener(this);	
					}
					else
					{
						Toast.makeText(this, "Não encontrou dados na consulta.", Toast.LENGTH_LONG).show();
						finish();
					}
					
			}
			else
			{
				Toast.makeText(this, "Arquivo dos dados incorreto. Informe ao desenvolvedor.", Toast.LENGTH_LONG).show();
				finish();
			}

		}
		catch(Exception e)
		{
			Toast.makeText(this, "Não acessou / encontrou o arquivo dos dados. Informe ao desenvolvedor.", Toast.LENGTH_LONG).show();
			finish();
		}

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
		((GlobalController)getApplication()).setIdPesquisa( ids.get(arg2) );
		Intent data = new Intent(this, ProcessoActivity.class);
		startActivityForResult(data,1);						
	}

}
