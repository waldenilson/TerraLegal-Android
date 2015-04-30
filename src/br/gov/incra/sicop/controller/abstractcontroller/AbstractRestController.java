package br.gov.incra.sicop.controller.abstractcontroller;
import br.gov.incra.sicop.webservice.WebService;

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
