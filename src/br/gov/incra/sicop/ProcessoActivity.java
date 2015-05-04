package br.gov.incra.sicop;

import java.util.ArrayList;
import java.util.List;

import br.gov.incra.sicop.abstractactivity.IActivity;
import br.gov.incra.sicop.controller.GlobalController;
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
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ProcessoActivity extends Activity implements IActivity {

    private ProgressDialog pd;
    
    private TextView tipo, nome, localizacao, numero, cadastro_pessoa, subnome,endereco,contato,gleba,municipio;
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_processo);
		getWindow().setLayout(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		init();

		SQLiteDatabase sql = ((GlobalController) getApplication()).getDatabase();
		if ( sql.isOpen() )
		{
			Cursor resultSet = sql.rawQuery("SELECT * FROM processo WHERE id = "+ ((GlobalController)getApplication()).getIdPesquisa() +"% ", null);
			resultSet.moveToFirst();

			tipo.setText( tipo.getText().toString()+" "+resultSet.getString(6) );
			numero.setText( numero.getText().toString()+" "+resultSet.getString(1) );
			nome.setText( nome.getText().toString()+" "+resultSet.getString(3) );
			cadastro_pessoa.setText( cadastro_pessoa.getText().toString()+" "+resultSet.getString(2) );
			localizacao.setText( localizacao.getText().toString()+" "+resultSet.getString(5) );
			
			gleba.setText( gleba.getText().toString()+" "+resultSet.getString(8) );
			endereco.setText( endereco.getText().toString()+" "+resultSet.getString(10) );
			contato.setText( contato.getText().toString()+" "+resultSet.getString(11) );
			municipio.setText( municipio.getText().toString()+" "+resultSet.getString(9) );
			subnome.setText( subnome.getText().toString()+" "+resultSet.getString(4) );
			
		}
		else
		{
			Toast.makeText(this, "Não acessou a base de dados", Toast.LENGTH_LONG).show();
			finish();
		}

	}

	private void init()
	{
		tipo = (TextView) findViewById(R.id.tv_tipo);
		nome = (TextView) findViewById(R.id.tv_nome);
		localizacao = (TextView) findViewById(R.id.tv_localizacao);
		numero = (TextView) findViewById(R.id.tv_numero);
		cadastro_pessoa = (TextView) findViewById(R.id.tv_cadastro_pessoa);
		gleba = (TextView) findViewById(R.id.tv_gleba);
		municipio = (TextView) findViewById(R.id.tv_municipio);
		endereco = (TextView) findViewById(R.id.tv_endereco);
		contato = (TextView) findViewById(R.id.tv_contato);
		subnome = (TextView) findViewById(R.id.tv_subnome);
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

}
