package br.com.application.servlet.view;

import br.com.application.domain.Modelo;
import br.com.application.enumerator.EstoqueEnum;
import br.com.application.persistence.jpa.EstoquePersistence;
import br.com.application.persistence.jpa.ModeloPersistence;
import br.com.application.domain.Estoque;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/Estoques" })
public class EstoqueView extends HttpServlet {

	private HashMap<String, Object> js = new HashMap<String, Object>();
	private Gson gson = null;
	private  String jsonArray;
	
	private ModeloPersistence modeloPersistence;
	private Modelo modelo;
	
	private EstoquePersistence estoquePersistence;
	private List<Estoque> estoques;
	private Estoque estoque;
	
	private JSONArray estoquesJSON;
	private JSONObject estoqueJSON;


	public EstoqueView() {
		super();
	}

    
    private void findAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			estoquePersistence = new EstoquePersistence();
			estoques = new ArrayList<Estoque>();
			estoques = estoquePersistence.findAll();
			
			estoquesJSON = new JSONArray();
			
			for(Estoque estoque : estoques) {
				estoqueJSON = new JSONObject();
				estoqueJSON.put("quantidade", estoque.getQuantidade());
				estoqueJSON.put("tipo", estoque.getEstoque().toString());
				estoqueJSON.put("modelo", estoque.getModelo());
				estoquesJSON.put(estoqueJSON);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(500);
			response.getWriter().print(gson.toJson(e.getMessage()));
		}finally {
			response.setStatus(200);
			response.getWriter().print(estoquesJSON);
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
			Logger.getLogger(ProdutoView.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception ex) {
			Logger.getLogger(ProdutoView.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}

}
