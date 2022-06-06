package br.com.application.converter;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.application.domain.Cidade;
import br.com.application.domain.Colaborador;
import br.com.application.util.DateUtil;

public class ColaboradorToJSON {
	
	public static JSONArray ColaboradoresToJSON(List<Colaborador> colaboradores) throws JSONException, Exception {
		JSONArray colaboradoresJSON = new JSONArray();
		for(Colaborador colaborador: colaboradores) {
			colaboradoresJSON.put(colaboradorToJSON(colaborador));
		}
		return colaboradoresJSON;
	}
	
	@SuppressWarnings("unused")
	public static JSONObject colaboradorToJSON(Colaborador colaborador) throws JSONException, Exception {
		JSONObject colaboradorJSON = new JSONObject();
		colaboradorJSON.put("datanascimento", DateUtil.converterCalendarToString(colaborador.getDataNascimento()));
		colaboradorJSON.put("departamento", colaborador.getDepartamento().getDepartamento());
		colaboradorJSON.put("hierarquia", colaborador.getDepartamento().getHierarquia());
		colaboradorJSON.put("sigla", colaborador.getDepartamento().getSigla());
		colaboradorJSON.put("nome", colaborador.getNomeCompleto());
		colaboradorJSON.put("remuneracao", colaborador.getRemuneracao());
		colaboradorJSON.put("cargo", colaborador.getCargo());
		colaboradorJSON.put("cnh", colaborador.getCnh());
		colaboradorJSON.put("cpf", colaborador.getCpf());
		colaboradorJSON.put("rg", colaborador.getRg());
		colaboradorJSON.put("codigo", colaborador.getId());
		return colaboradorJSON;
	}

}
