package br.com.application.servlet.filter;

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

import br.com.application.domain.Colaborador;
import br.com.application.domain.Departamento;
import br.com.application.persistence.jpa.DepartamentoPersistence;
import br.com.application.util.DateUtil;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/DepartamentosFilter/" })
public class DepartamentoView extends HttpServlet {

	private HashMap<String, Object> map = new HashMap<String, Object>();
	private RequestDispatcher requestDispatcher;
	private JSONObject departamentoJSON;
	private JSONArray departamentosJSON;
	private JSONObject colaboradorJSON;
	private JSONArray colaboradoresJSON;
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
			departamentosJSON = new JSONArray();
			for(Departamento departments: departamentoPersistence.findAll()) {
				departamentoJSON = new JSONObject();
				departamentoJSON.put("quantidade", departments.getColaboradores().size());
				departamentoJSON.put("departamento", departments.getDepartamento());
				departamentoJSON.put("hierarquia", departments.getHierarquia());
				departamentoJSON.put("localidade", departments.getLocalidade());
				departamentoJSON.put("sigla", departments.getSigla());
				departamentoJSON.put("codigo", departments.getId());
				departamentosJSON.put(departamentoJSON);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(500);
			response.getWriter().print(gson.toJson(e.getMessage()));
		} finally {
			response.setStatus(200);
			response.getWriter().print(departamentosJSON);
		}
	}
    
    private void findBy(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	map.put("idd", request.getParameter("idd").trim());
		try {
			departamentoPersistence = new DepartamentoPersistence();
			departamento = new Departamento();
			departamentosJSON = new JSONArray();
			colaboradoresJSON = new JSONArray();
			departamentoJSON = new JSONObject();
			departamento = departamentoPersistence.findBy(Long.parseLong(map.get("idd").toString()));
			for(Colaborador colaborador: departamento.getColaboradores()) {
					colaboradorJSON = new JSONObject();
					colaboradorJSON.put("datanascimento", DateUtil.converterCalendarToString(colaborador.getDataNascimento()));
					colaboradorJSON.put("nome", colaborador.getNomeCompleto());
					colaboradorJSON.put("remuneracao", colaborador.getRemuneracao());
					colaboradorJSON.put("cargo", colaborador.getCargo());
					colaboradorJSON.put("cnh", colaborador.getCnh());
					colaboradorJSON.put("cpf", colaborador.getCpf());
					colaboradorJSON.put("rg", colaborador.getRg());
					colaboradorJSON.put("codigo", colaborador.getId());
					colaboradoresJSON.put(colaboradorJSON);
			}
			departamentoJSON.put("quantidade", departamento.getColaboradores().size());
			departamentoJSON.put("colaboradores", colaboradoresJSON);
			departamentoJSON.put("departamento", departamento.getDepartamento());
			departamentoJSON.put("hierarquia", departamento.getHierarquia());
			departamentoJSON.put("localidade", departamento.getLocalidade());
			departamentoJSON.put("sigla", departamento.getSigla());
			departamentoJSON.put("codigo", departamento.getId());
			departamentosJSON.put(departamentoJSON);
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(500);
			response.getWriter().print(gson.toJson(e.getMessage()));
		}finally {
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
		case 600:
			findBy(request, response);
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
