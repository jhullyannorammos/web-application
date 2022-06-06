package br.com.application.servlet.model;

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
@WebServlet(urlPatterns = { "/Departamento" })
public class DepartamentoModel extends HttpServlet {

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

	public DepartamentoModel() {
		super();
	}

	private void save(HttpServletRequest request, HttpServletResponse response) throws Exception {

		map.put("dpt", request.getParameter("dpt").trim());
		map.put("sgl", request.getParameter("sgl").trim());
		map.put("hrq", request.getParameter("hrq").trim());
		map.put("cdd", request.getParameter("cdd").trim());

		try {

			departamentoPersistence = new DepartamentoPersistence();
			departamento = new Departamento();
			departamento.setDepartamento(map.get("dpt").toString());
			departamento.setLocalidade(map.get("cdd").toString());
			departamento.setHierarquia(map.get("hrq").toString());
			departamento.setSigla(map.get("sgl").toString());
			departamentoPersistence.save(departamento);

		} catch (ConstraintViolationException e) {
			e.printStackTrace();
			response.setStatus(500);
			response.getWriter().print(gson.toJson(e.getMessage()));
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(500);
			response.getWriter().print(gson.toJson(e.getMessage()));
		}finally {
			response.setStatus(200);
			response.getWriter().print(gson.toJson(departamento.getDepartamento()));
		}
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws Exception {

		map.put("idd", request.getParameter("idd").trim());
		map.put("dpt", request.getParameter("dpt").trim());
		map.put("sgl", request.getParameter("sgl").trim());
		map.put("hrq", request.getParameter("hrp").trim());
		map.put("cdd", request.getParameter("cdd").trim());
		map.put("fdc", request.getParameter("fdc").trim());

		try {

			departamentoPersistence = new DepartamentoPersistence();
			departamento = departamentoPersistence.findBy(Long.parseLong(map.get("dpt").toString()));
			departamento.setLocalidade(map.get("cdd").toString() + "-" + map.get("fdc").toString());
			departamento.setHierarquia(map.get("hrq").toString());
			departamento.setDepartamento(map.get("dpt").toString());
			departamento.setSigla(map.get("sgl").toString());
			departamentoPersistence.merge(departamento);

		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(500);
			response.getWriter().print(gson.toJson(e.getMessage()));
		} finally {
			response.setStatus(200);
			response.getWriter().print(departamento.getDepartamento());
		}

	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {

		map.put("idd", request.getParameter("idd").trim());
		try {
			departamentoPersistence = new DepartamentoPersistence();
			departamento = departamentoPersistence.findBy(Long.parseLong(map.get("dpt").toString())); 
			departamentoPersistence.delete(departamento);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(500);
			response.getWriter().print(gson.toJson(e.getMessage()));
		}finally {
			response.setStatus(200);
			response.getWriter().print(gson.toJson(departamento.getDepartamento()));
		}
	}
    
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, Exception {
		gson = new GsonBuilder().setPrettyPrinting().create();
		response.setContentType("application/json");
		int op = Integer.parseInt(request.getParameter("actionServlet"));
		switch (op) {
		case 100:
			save(request, response);
			break;
		case 200:
			update(request, response);
			break;
		case 300:
			delete(request, response);
			break;
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception ex) {
			Logger.getLogger(ProdutosModel.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception ex) {
			Logger.getLogger(ProdutosModel.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}

}
