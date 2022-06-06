package br.com.application.converter;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.application.domain.Produto;

public class ProdutoToJSON {
	
	public static JSONArray ProdutosToJSON(List<Produto> produtos) throws JSONException, Exception {
		JSONArray produtosJSON = new JSONArray();
		for(Produto produto : produtos) {
			produtosJSON.put(ProdutoToJSON(produto));
		}
		return produtosJSON;
	}
	
	public static JSONObject ProdutoToJSON(Produto produto) {
		JSONObject produtoJSON = new JSONObject();
		produtoJSON.put("codigo", produto.getModelo().getFornecedor().getFornecedor());
		produtoJSON.put("codigo", produto.getModelo().getLancamento());
		produtoJSON.put("codigo", produto.getModelo().getModelo());
		produtoJSON.put("codigo", produto.getProduto());
		produtoJSON.put("codigo", produto.getValor());
		produtoJSON.put("codigo", produto.getId());
		return produtoJSON;
	} 

}
