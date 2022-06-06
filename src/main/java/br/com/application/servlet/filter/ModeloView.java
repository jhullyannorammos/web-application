package br.com.application.servlet.filter;

import java.io.IOException;
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

import br.com.application.domain.Modelo;
import br.com.application.persistence.jpa.FornecedorPersistence;
import br.com.application.persistence.jpa.ModeloPersistence;
import br.com.application.domain.Fornecedor;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/ModelosFilter/"})
public class ModeloView extends HttpServlet {

	HashMap<String, Object> js = new HashMap<String, Object>();
	Gson gson = null;
	String jsonArray;

	ModeloPersistence modeloPersistence;
	List<Modelo> modelos;
	Modelo modelo;
	
	FornecedorPersistence fornecedorPersistence;
	List<Fornecedor> fornecedores;
	Fornecedor fornecedor;
	
	JSONArray modelosJSON;
	JSONObject modeloJSON;

	public ModeloView() {
		super();
	}
   
	private void findAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			modeloPersistence = new ModeloPersistence();
			modelosJSON = new JSONArray();
			for(Modelo modelo: modeloPersistence.findAll()) {
				modeloJSON = new JSONObject();
				modeloJSON.put("fornecedor", modelo.getFornecedor().getFornecedor());
				modeloJSON.put("lancamento", modelo.getLancamento());
				modeloJSON.put("modelo", modelo.getModelo());
				modeloJSON.put("codigo", modelo.getId());
				modelosJSON.put(modeloJSON);
			}
		} catch (IOException e) {
			e.printStackTrace();
			response.setStatus(500);
			response.getWriter().print(gson.toJson(e.getMessage()));
		}finally {
			response.setStatus(200);
			response.getWriter().print(modelosJSON);
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
