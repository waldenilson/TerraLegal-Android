package br.gov.incra.sicop;

import java.util.ArrayList;
import java.util.List;

import br.gov.incra.sicop.abstractactivity.IActivity;
import br.gov.incra.sicop.controller.GlobalController;
import br.gov.incra.sicop.list.ListViewImageAdapter;
import android.os.Bundle;
import android.app.Activity;
import android.app.Application;
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
import android.widget.EditText;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ConsultaProcessoActivity extends Activity implements IActivity{

    private ProgressDialog pd;
    private EditText nome,cadastro_pessoa;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consulta_processo);
		getWindow().setLayout(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		init();

	}

	private void init(){
		nome = (EditText) findViewById(R.id.et_nome);
		cadastro_pessoa = (EditText) findViewById(R.id.et_cadastro_pessoa);
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
	
	public void bt_pesquisar(View v)
	{

		if( nome.getText().toString().length() > 2 || cadastro_pessoa.getText().toString().length() > 2 )
		{
			( (GlobalController) getApplication()).setNomePesquisa( nome.getText().toString() );
			( (GlobalController) getApplication()).setCadastroPesquisa( cadastro_pessoa.getText().toString() );
			
			Intent data = new Intent(this, ProcessosActivity.class);
			startActivityForResult(data,1);										
		}
		else
			Toast.makeText(this, "Informe pelo menos 3 caracteres.", Toast.LENGTH_LONG).show();
			
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

}
