package br.com.application.servlet.filter;

import br.com.application.domain.Modelo;
import br.com.application.domain.Produto;
import br.com.application.persistence.jpa.FornecedorPersistence;
import br.com.application.persistence.jpa.ModeloPersistence;
import br.com.application.persistence.jpa.ProdutoPersistence;
import br.com.application.domain.Fornecedor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/ProdutoFilter/" })
public class ProdutosView extends HttpServlet {

	HashMap<String, Object> js = new HashMap<String, Object>();
	String jsonArray;
	Gson gson = null;
	
	List<Produto> produtos;
	ProdutoPersistence produtoPersistence;
	Produto produto;
	
	List<Modelo> modelos;
	ModeloPersistence modeloPersistence;
	Modelo modelo;
	
	List<Fornecedor> fornecedors;
	FornecedorPersistence fornecedorPersistence;
	Fornecedor fornecedor;
	
	private JSONObject produtoJSON;
	private JSONArray produtosJSON;

	public ProdutosView() {
		super();
	}

	
	private void findAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			produtoPersistence = new ProdutoPersistence();		
			produtosJSON = new JSONArray();
			for(Produto produto : produtoPersistence.findAll()) {
				produtoJSON = new JSONObject();
				produtoJSON.put("fornecedor", produto.getModelo().getFornecedor().getFornecedor());
				produtoJSON.put("abrangencia", produto.getModelo().getFornecedor().getAbrangencia());
				produtoJSON.put("modelo", produto.getModelo().getModelo());
				produtoJSON.put("produto", produto.getProduto());
				produtoJSON.put("valor", produto.getValor());
				produtoJSON.put("codigo", produto.getId());
				produtosJSON.put(produtoJSON);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(500);
			response.getWriter().print(e.getMessage());
		}finally {
			response.setStatus(200);
			response.getWriter().print(new Gson().toJson(produtosJSON));
		}	
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, Exception {
		gson = new GsonBuilder().setPrettyPrinting().create();
		response.setContentType("application/json");
		int op = Integer.parseInt(request.getParameter("actionServlet"));
		switch (op) {
		case 400:
			findAll(request, response);
			break;
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception ex) {
			Logger.getLogger(ProdutosView.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception ex) {
			Logger.getLogger(ProdutosView.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}
}
