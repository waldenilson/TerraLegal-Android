package br.gov.incra.sicop;

import java.util.ArrayList;
import java.util.List;

import br.com.csl.alunouniasselvi.R;
import br.com.csl.alunouniasselvi.list.ListViewImageAdapter;
import br.gov.incra.sicop.abstractactivity.IActivity;
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
		
		lista.add( getString(R.string.lb_simulador_notas) ); desc.add( getString( R.string.lb_desc_simulador_notas) );//simulador
		img.add( getResources().getDrawable(R.drawable.calculator) );

		ListViewImageAdapter lv = new ListViewImageAdapter(this, lista, desc, img);
		lvmenu.setAdapter(lv);
		lvmenu.setTextFilterEnabled(true);
		lvmenu.setOnItemClickListener(this);	

	}

	private void init(){

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


	public void click_bt_bar_info(View v) {
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
