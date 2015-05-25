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

public class PecaActivity extends Activity implements IActivity {

    private ProgressDialog pd;
    
    private TextView contrato, nome, localizacao, entrega, cadastro_pessoa, area, perimetro, observacao,gleba,municipio;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_peca);
		getWindow().setLayout(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		init();

		SQLiteDatabase sql = ((GlobalController) getApplication()).getDatabase();
		if ( sql.isOpen() )
		{
			try
			{
				Cursor resultSet = sql.rawQuery("SELECT * FROM pecatecnica WHERE id = "+ ((GlobalController)getApplication()).getIdPesquisa() +"", null);
				resultSet.moveToFirst();
	
				nome.setText( nome.getText().toString()+" "+resultSet.getString(3) );
				String cpf = resultSet.getString(4);
				String cpf_mask = "";
				for(int x=0;x<cpf.length();x++){
					if(x == 0 || x == 1  || x == 2 || x == cpf.length()-1 || x == cpf.length()-2)
						cpf_mask += "*";
					else
						cpf_mask += ""+cpf.charAt(x);
				}
				
//				satubinha, pio XII, vitorino freire, olho dagua das cunhas, esperantinopolis
				
				cadastro_pessoa.setText( cadastro_pessoa.getText().toString()+" "+cpf_mask );				
				localizacao.setText( localizacao.getText().toString()+" "+resultSet.getString(5) );
				gleba.setText( gleba.getText().toString()+" "+resultSet.getString(8) );
				municipio.setText( municipio.getText().toString()+" "+resultSet.getString(9) );

				contrato.setText( contrato.getText().toString()+" "+resultSet.getString(1) );
				entrega.setText( entrega.getText().toString()+" "+resultSet.getString(2) );
				area.setText( area.getText().toString()+" "+resultSet.getString(6)+" ha" );
				if(resultSet.getString(7).equals("None"))
					perimetro.setText( perimetro.getText().toString()+" " );
				else
					perimetro.setText( perimetro.getText().toString()+" "+resultSet.getString(7) );
				observacao.setText( observacao.getText().toString()+" "+resultSet.getString(10) );
				
			}
			catch(Exception e){
				Toast.makeText(this, "erro: "+e.getMessage()+". Informe ao Desenvolvedor.", Toast.LENGTH_LONG).show();
				finish();
			}
		}
		else
		{
			Toast.makeText(this, "Não carregou / acessou o arquivo dos dados", Toast.LENGTH_LONG).show();
			finish();
		}

	}

	private void init()
	{
		nome = (TextView) findViewById(R.id.tv_peca_nome);
		localizacao = (TextView) findViewById(R.id.tv_peca_localizacao);
		cadastro_pessoa = (TextView) findViewById(R.id.tv_peca_cadastro_pessoa);
		gleba = (TextView) findViewById(R.id.tv_peca_gleba);
		municipio = (TextView) findViewById(R.id.tv_peca_municipio);
		contrato = (TextView) findViewById(R.id.tv_peca_contrato);
		entrega = (TextView) findViewById(R.id.tv_peca_entrega);
		observacao = (TextView) findViewById(R.id.tv_peca_observacao);
		area = (TextView) findViewById(R.id.tv_peca_area);
		perimetro = (TextView) findViewById(R.id.tv_peca_perimetro);
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
