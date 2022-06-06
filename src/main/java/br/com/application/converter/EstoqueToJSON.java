package br.com.application.converter;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.application.domain.Estoque;

public class EstoqueToJSON {
	
	public static JSONArray EstoquesToJSON(List<Estoque> estoques) throws JSONException, Exception {
		JSONArray estoqueJSON = new JSONArray();
		for(Estoque estoque: estoques) {
			estoqueJSON.put(EstoqueToJSON.EstoqueToJSON(estoque));
		}
		return estoqueJSON;
	}
	
	public static JSONObject EstoqueToJSON(Estoque estoque) throws JSONException, Exception {
		JSONObject estoqueJSON = new JSONObject();
		estoqueJSON.put("quantidade", estoque.getMercadorias().size());
		estoqueJSON.put("estoque", estoque.getEstoque());
		estoqueJSON.put("modelo", estoque.getModelo());
		estoqueJSON.put("codigo", estoque.getId());
		return estoqueJSON;
	}

}
