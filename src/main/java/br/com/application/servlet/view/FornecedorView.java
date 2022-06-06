package br.com.application.servlet.view;

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

import br.com.application.domain.Fornecedor;
import br.com.application.enumerator.AbrangenciaEnum;
import br.com.application.persistence.jpa.FornecedorPersistence;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/Fornecedores" })
public class FornecedorView extends HttpServlet {

	HashMap<String, Object> responseModel;
	Gson gson = null;
	
	JSONArray fornecedoresJSON;
	JSONObject fornecedorJSON;

	List<Fornecedor> fornecedores;
	FornecedorPersistence fornecedorPersistence;
	Fornecedor fornecedor;

	public FornecedorView() {
		super();
		responseModel = new HashMap<String, Object>();
	}
	
	
	private void findAll(HttpServletResponse response) throws Exception {
		try {
			fornecedorPersistence = new FornecedorPersistence();
			fornecedoresJSON = new JSONArray();
			for(Fornecedor fornecedor: fornecedorPersistence.findAll()) {
				fornecedorJSON = new JSONObject();
				fornecedorJSON.put("abrangencia", fornecedor.getAbrangencia());
				fornecedorJSON.put("fornecedor", fornecedor.getFornecedor());
				fornecedorJSON.put("codigo", fornecedor.getId());
				fornecedoresJSON.put(fornecedorJSON);
			}
		} catch (IOException e) {
			e.printStackTrace();
			response.setStatus(500);
			response.getWriter().print(gson.toJson(e.getMessage()));
		}finally {
			response.setStatus(200);
			response.getWriter().print(fornecedoresJSON);
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
