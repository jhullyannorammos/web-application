package br.com.application.converter;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.application.domain.Patrimonio;

public class PatrimonioToJSon {
	
	public static JSONArray PatrimoniosToJSON(List<Patrimonio> patrimonios) {
		JSONArray patrimonioJSON = new JSONArray();
		for(Patrimonio patrimonio : patrimonios) {
			patrimonioJSON.put(PatrimonioToJSON(patrimonio));
		}
		return patrimonioJSON;
	}
	
	public static JSONObject PatrimonioToJSON(Patrimonio patrimonio) {
		JSONObject patrimonioJSON = new JSONObject();
		patrimonioJSON.put("codigo", patrimonio.getSoftwareLicense().getId());
		patrimonioJSON.put("codigo", patrimonio.getMercadoria().getId());
		patrimonioJSON.put("codigo", patrimonio.getPatrimonio());
		patrimonioJSON.put("codigo", patrimonio.getId());
		return patrimonioJSON;
	} 

}
