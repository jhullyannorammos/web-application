package br.com.application.converter;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.application.domain.Cidade;

public class CidadeConverterToJSON {

	public static JSONArray cidadesToJSON(List<Cidade> cidades) {
		JSONArray cidadesJSON = new JSONArray();
		for(Cidade cidade: cidades){
			cidadesJSON.put(cidadeToJSON(cidade));
		}
		return cidadesJSON;
	}
	
	public static JSONObject cidadeToJSON(Cidade cidade) {
		JSONObject cidadeJSON = new JSONObject();
		cidadeJSON.put("federacao", cidade.getFederacao());
		cidadeJSON.put("cidade", cidade.getCidade());
		cidadeJSON.put("capital", cidade.isCapital());
		cidadeJSON.put("sigla", cidade.getSigla());
		cidadeJSON.put("codigo", cidade.getId());
		return cidadeJSON;
	}

}
