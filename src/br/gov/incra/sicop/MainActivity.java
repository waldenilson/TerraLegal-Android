package br.gov.incra.sicop;

import java.util.ArrayList;
import java.util.List;

import br.gov.incra.sicop.abstractactivity.IActivity;
import br.gov.incra.sicop.list.ListViewImageAdapter;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity implements IActivity, OnItemClickListener {

    private ProgressDialog pd;
    private ListView lvmenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getWindow().setLayout(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		init();
		List<String> lista = new ArrayList<String>();
		List<String> desc = new ArrayList<String>();
		List<Drawable> img = new ArrayList<Drawable>();
		
		lista.add( getString(R.string.menu_processos) ); desc.add( getString( R.string.menu_desc_processos) );//processos
		img.add( getResources().getDrawable(R.drawable.processo) );

		lista.add( getString(R.string.menu_pecas) ); desc.add( getString( R.string.menu_desc_pecas) );//pecas
		img.add( getResources().getDrawable(R.drawable.peca) );

		lista.add( getString(R.string.menu_livro) ); desc.add( getString( R.string.menu_desc_livro) );//livro fundiario
		img.add( getResources().getDrawable(R.drawable.livro) );

		lista.add( getString(R.string.menu_leis) ); desc.add( getString( R.string.menu_desc_leis) );//legislacao
		img.add( getResources().getDrawable(R.drawable.legislacao) );

		lista.add( getString(R.string.menu_info) ); desc.add( getString( R.string.menu_desc_info) );//informacoes
		img.add( getResources().getDrawable(R.drawable.info) );

		ListViewImageAdapter lv = new ListViewImageAdapter(this, lista, desc, img);
		lvmenu.setAdapter(lv);
		lvmenu.setTextFilterEnabled(true);
		lvmenu.setOnItemClickListener(this);	

	}

	private void init(){
		lvmenu = (ListView) findViewById(R.id.lv_menu);
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

	public void login(View v){
		Intent data = new Intent(this, LoginActivity.class);
		startActivityForResult(data,1);				
	}
	
	public void bt_sair(View v){
		finish();
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}
}
