package br.com.application.converter;

import java.util.Collection;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.application.domain.Fornecedor;

public class FornecedorToJSON {
	
	public static JSONArray FornecedoresToJSON(List<Fornecedor> fornecedores) throws JSONException, Exception {
		JSONArray fornecedoresJSON = new JSONArray();
		for(Fornecedor fornecedor : fornecedores) {
			fornecedoresJSON.put(FornecedorToJSON(fornecedor));
		}
		return fornecedoresJSON;
	}

	public static JSONObject FornecedorToJSON(Fornecedor fornecedor) {
		JSONObject fornecedorJSON = new JSONObject();
		fornecedorJSON.put("codigo", fornecedor.getModelos().size());
		fornecedorJSON.put("codigo", fornecedor.getAbrangencia());
		fornecedorJSON.put("codigo", fornecedor.getFornecedor());
		fornecedorJSON.put("codigo", fornecedor.getEndereco());
		fornecedorJSON.put("codigo", fornecedor.getTelefone());
		fornecedorJSON.put("codigo", fornecedor.getId());
		return fornecedorJSON;
	}

}
