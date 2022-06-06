package br.com.application.converter;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.application.domain.Mercadoria;
import br.com.application.domain.Movimentacao;

public class MovimentacaoToJSON {
	
	public static JSONArray MovimentacaoToJSON(List<Movimentacao> movimentacaos) throws JSONException, Exception {
		JSONArray movimentcaosJSON = new JSONArray();
		for(Movimentacao movimentacao : movimentacaos) {
			movimentcaosJSON.put(MovimentacaoToJSON(movimentacao));
		}
		return movimentcaosJSON;
	}
	
	public static JSONObject MovimentacaoToJSON(Movimentacao movimentacao) throws JSONException, Exception {
		JSONObject movimentacaoJSON = new JSONObject();
		movimentacaoJSON.put("codigo", MercadoriaToJSON.MercadoriasToJSON(movimentacao.getMercadorias()));
		movimentacaoJSON.put("codigo", movimentacao.getMovimentacao().getId());
		movimentacaoJSON.put("codigo", movimentacao.getColaborador().getId());
		movimentacaoJSON.put("codigo", movimentacao.getPeriodo());
		movimentacaoJSON.put("codigo", movimentacao.getId());
		return movimentacaoJSON;
	} 

}
