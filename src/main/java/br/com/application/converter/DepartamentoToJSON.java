package br.com.application.converter;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.application.domain.Departamento;

public class DepartamentoToJSON {
	
	public static JSONArray DepartamentosToJSON(List<Departamento> departamentos) throws JSONException, Exception {
		JSONArray departamentosJSON = new JSONArray();
		for(Departamento departments: departamentos) {
			departamentosJSON.put(DepartamentoToJSON(departments));
		}
		return departamentosJSON;
	}
	
	@SuppressWarnings("unused")
	public static JSONObject DepartamentoToJSON(Departamento departamento) throws JSONException, Exception {
		JSONObject departamentoJSON = new JSONObject();
		departamentoJSON.put("quantidade", departamento.getColaboradores().size());
		departamentoJSON.put("departamento", departamento.getDepartamento());
		departamentoJSON.put("hierarquia", departamento.getHierarquia());
		departamentoJSON.put("localidade", departamento.getLocalidade());
		departamentoJSON.put("sigla", departamento.getSigla());
		departamentoJSON.put("codigo", departamento.getId());
		return departamentoJSON;
	}

}
