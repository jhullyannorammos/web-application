package br.com.application.servlet.filter;

import java.io.IOException;
import java.util.HashMap;
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

import br.com.application.domain.Mercadoria;
import br.com.application.domain.Modelo;
import br.com.application.domain.Produto;
import br.com.application.domain.embeddable.Configuracao;
import br.com.application.persistence.jpa.DepartamentoPersistence;
import br.com.application.persistence.jpa.EstoquePersistence;
import br.com.application.persistence.jpa.MercadoriaPersistence;
import br.com.application.persistence.jpa.ModeloPersistence;
import br.com.application.persistence.jpa.ProdutoPersistence;
import br.com.application.domain.Departamento;
import br.com.application.domain.Estoque;
import br.com.application.util.DateUtil;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/MercadoriasFilter/" })
public class MercadoriaView extends HttpServlet {
	
	JSONArray mercadoriasJSON;
	JSONObject mercadoriaJSON;
	
	EstoquePersistence EstoquePersistence;
	Estoque Estoque;
	
	ProdutoPersistence produtoPersistence;
	Produto produto;
	
	MercadoriaPersistence mercadoriaPersistence;
	Mercadoria mercadoria;
	
	DepartamentoPersistence departamentoPersistence;
	Departamento departamento;
	
	Configuracao configuracao;
	
	HashMap<String, Object> js = new HashMap<String, Object>();
	Gson gson = null;
	
	private void findAll(HttpServletResponse response) throws Exception {
		try {
			mercadoriaPersistence = new MercadoriaPersistence();
			mercadoriasJSON = new JSONArray();
			for(Mercadoria mercadoria : mercadoriaPersistence.findAll()) {
				mercadoriaJSON = new JSONObject();
				mercadoriaJSON.put("serial number", mercadoria.getSerial());
				mercadoriaJSON.put("fornecedor", mercadoria.getProduto().getModelo().getFornecedor().getFornecedor().toString());
				mercadoriaJSON.put("modelo", mercadoria.getProduto().getModelo().getModelo());
				mercadoriaJSON.put("produto", mercadoria.getProduto().getProduto());
				mercadoriaJSON.put("valor", mercadoria.getProduto().getValor());
				mercadoriaJSON.put("codigo", mercadoria.getId());
				mercadoriasJSON.put(mercadoriaJSON);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(500);
			response.getWriter().print(gson.toJson(e.getMessage()));
		}finally {
			response.setStatus(200);
			response.getWriter().print(mercadoriasJSON);
		}

	}
	
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, Exception {
		gson = new GsonBuilder().setPrettyPrinting().create();
		response.setContentType("application/json");
		int op = Integer.parseInt(request.getParameter("actionServlet"));
		switch (op) {
		case 400:
			findAll(response);
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
