package br.gov.incra.sicop.controller.abstractcontroller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import br.gov.incra.sicop.controller.GlobalController;

@SuppressWarnings("serial")
public class RestController extends AbstractRestController implements Serializable {

	private static List<Object> tratarWS(String res)
	{
		List<Object> retorno = new ArrayList<Object>();
		try
		{
			List<JSONObject> l = new ArrayList<JSONObject>();
			JSONArray ja = new JSONArray(res);
			for(int x=0; x<ja.length();x++)
				l.add(ja.getJSONObject(x));
			retorno.add("99");
			retorno.add(l);
			return retorno;
		}
		catch(Exception e)
		{
			retorno.add(res);
			retorno.add(null);
			return retorno;
		}
	}
	
	
	public static List<Object> login(String key, String nome, String senha){
		return tratarWS( wsRest("login/?key="+key+"&username="+nome+"&password="+GlobalController.md5(senha)) );
	}

}
