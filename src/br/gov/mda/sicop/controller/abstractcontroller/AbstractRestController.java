package br.com.csl.base.controller.abstractcontroller;
import br.com.csl.base.webservice.WebService;

public abstract class AbstractRestController {
	
	
	protected static String wsRest(String rest)
	{		
		try
		{
			return WebService.
					consumirServicosRest(converterCaracteresURL(rest)).
					replace("&quot;", "\"");
		}
		catch(Exception e)
		{
			return "00";
		}
	}
	private static String converterCaracteresURL(String rest)
	{
		rest = rest.replace(" ", "%20");
		rest = rest.replace("\"", "%22");
		rest = rest.replace("[", "%5B");
		rest = rest.replace("]", "%5D");
		rest = rest.replace("{", "%7B");
		rest = rest.replace("}", "%7D");
		return rest;
	}
}
