package br.com.application.servlet.view;

import org.hibernate.exception.ConstraintViolationException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.application.converter.DepartamentoToJSON;
import br.com.application.domain.Colaborador;
import br.com.application.domain.Departamento;
import br.com.application.persistence.jpa.DepartamentoPersistence;
import br.com.application.util.DateUtil;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/Departamentos" })
public class DepartamentoView extends HttpServlet {

	private HashMap<String, Object> map = new HashMap<String, Object>();
	private RequestDispatcher requestDispatcher;
	private JSONArray departamentosJSON;
	private Gson gson = null;
	
	private String responseSucess = "transação realizada com sucesso"; 
	private DepartamentoPersistence departamentoPersistence;
	private List<Departamento> departamentos;
	private Departamento departamento;

	public DepartamentoView() {
		super();
	}
	
    private void findAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			departamentoPersistence = new DepartamentoPersistence();
			departamentos = new ArrayList<>();
			departamentos = departamentoPersistence.findAll();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			request.setAttribute("departamentos", departamentos);
			requestDispatcher = request.getRequestDispatcher("?pg=94");
			requestDispatcher.forward(request, response);
		}
	}
    
    
    private void findAll(HttpServletResponse response) throws Exception {
		try {
			
			departamentoPersistence = new DepartamentoPersistence();
			departamentos = new ArrayList<Departamento>();
			departamentosJSON = new JSONArray();
			
			departamentos = departamentoPersistence.findAll();
			departamentosJSON = DepartamentoToJSON.DepartamentosToJSON(departamentos);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(500);
			response.getWriter().print(gson.toJson(e.getMessage()));
		} finally {
			response.setStatus(200);
			response.getWriter().print(departamentosJSON);
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
		case 500:
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
