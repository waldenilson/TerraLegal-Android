package br.gov.incra.sicop;

import java.util.ArrayList;
import java.util.List;

import br.gov.incra.sicop.abstractactivity.IActivity;
import br.gov.incra.sicop.controller.GlobalController;
import br.gov.incra.sicop.list.ListViewImageAdapter;
import android.opengl.Visibility;
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
    
    private TextView tipo, nome, localizacao, numero, cadastro_pessoa, subnome,endereco,contato,gleba,municipio, classificacao;
    private TextView pecas,pecas_dados, pendencias, pendencias_dados, anexos, anexos_dados, movimentacoes, movimentacoes_dados;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_processo);
		getWindow().setLayout(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		init();

		SQLiteDatabase sql = ((GlobalController) getApplication()).getDatabase();
		if ( sql.isOpen() )
		{
			Cursor resultSet = sql.rawQuery("SELECT * FROM processo WHERE id = "+ ((GlobalController)getApplication()).getIdPesquisa() +"", null);
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
			if(resultSet.getString(6).equals("PROCESSO RURAL"))
				subnome.setText( "Cônjuge: "+resultSet.getString(4) );
			else if(resultSet.getString(6).equals("REGULARIZACAO URBANA"))
				subnome.setText( "Povoado: "+resultSet.getString(4) );
			else
				subnome.setText( "Interessado: "+resultSet.getString(4) );
			
			if(!resultSet.getString(7).equals("Anexo"))
				classificacao.setVisibility(View.INVISIBLE);
			
			//DADOS AUXILIARES DO ANDAMENTO DO PROCESSO
			//PEÇAS
			if(resultSet.getString(15).equals(""))
				pecas_dados.setText("Nenhum dado de peça técnica.");
			else
			{
				String[] objpecas = resultSet.getString(15).split("FIMREG");
				for( int x = 0; x < objpecas.length;x++ )
					pecas_dados.setText( pecas_dados.getText().toString() + objpecas[x]+"\n" );
			}
			//ANEXOS
			if(resultSet.getString(14).equals(""))
				pecas_dados.setText("Nenhum anexo.");
			else
			{
				String[] objanexos = resultSet.getString(14).split("FIMREG");
				for( int x = 0; x < objanexos.length;x++ )
					anexos_dados.setText( anexos_dados.getText().toString() + objanexos[x]+"\n" );
			}
			//MOVIMENTACOES
			if(resultSet.getString(13).equals(""))
				pecas_dados.setText("Nenhuma movimentação.");
			else
			{
				String[] objmovs = resultSet.getString(13).split("FIMREG");
				for( int x = 0; x < objmovs.length;x++ )
					movimentacoes_dados.setText( movimentacoes_dados.getText().toString() + objmovs[x]+"\n" );
			}
			//PENDENCIAS
			if(resultSet.getString(12).equals(""))
				pecas_dados.setText("Nenhuma pendência.");
			else
			{
				String[] objpen = resultSet.getString(12).split("FIMREG");
				for( int x = 0; x < objpen.length;x++ )
					pendencias_dados.setText( pendencias_dados.getText().toString() + objpen[x]+"\n" );
			}
			
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
		classificacao = (TextView) findViewById(R.id.tv_anexo);
		pecas = (TextView) findViewById(R.id.tv_pecas);
		pecas_dados = (TextView) findViewById(R.id.tv_pecas_dados);
		pendencias = (TextView) findViewById(R.id.tv_pendencias);
		pendencias_dados = (TextView) findViewById(R.id.tv_pendencias_dados);
		movimentacoes = (TextView) findViewById(R.id.tv_movimentacoes);
		movimentacoes_dados = (TextView) findViewById(R.id.tv_movimentacoes_dados);
		anexos = (TextView) findViewById(R.id.tv_anexos);
		anexos_dados = (TextView) findViewById(R.id.tv_anexos_dados);
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
