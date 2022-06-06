package br.com.application.converter;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.application.domain.Mercadoria;
import br.com.application.domain.Usuario;

public class MercadoriaToJSON {
	
	public static JSONArray MercadoriasToJSON(List<Mercadoria> mercadorias) throws JSONException, Exception {
		JSONArray mercadoriasJSON = new JSONArray();
		for(Mercadoria mercadoria: mercadorias) {
			mercadoriasJSON.put(MercadoriaToJSON(mercadoria));
		}
		return mercadoriasJSON;
	}
	
	public static JSONObject MercadoriaToJSON(Mercadoria mercadoria) {
		JSONObject mercadoriaJSON = new JSONObject();
		mercadoriaJSON.put("codigo", mercadoria.getId());
		return mercadoriaJSON;
	} 

}
