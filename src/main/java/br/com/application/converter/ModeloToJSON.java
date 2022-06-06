package br.com.application.converter;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.application.domain.Modelo;

public class ModeloToJSON {
	
	public static JSONArray ModelosToJSON(List<Modelo> modelos) {
		JSONArray modelosJSON = new JSONArray();
		for(Modelo modelo : modelos) {
			modelosJSON.put(ModeloToJSON(modelo));
		}
		return modelosJSON;
	}
	
	public static JSONObject ModeloToJSON(Modelo modelo) {
		JSONObject modeloJSON = new JSONObject();
		modeloJSON.put("codigo", modelo.getId());
		return modeloJSON;
	} 

}
