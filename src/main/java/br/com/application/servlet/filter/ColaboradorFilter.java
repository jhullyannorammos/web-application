package br.com.application.servlet.filter;

import java.io.IOException;
import java.io.PrintWriter;
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

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import br.com.application.domain.Departamento;
import br.com.application.persistence.jpa.ColaboradorPersistence;
import br.com.application.persistence.jpa.DepartamentoPersistence;
import br.com.application.domain.Colaborador;
import br.com.application.util.DateUtil;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/ColaboradorFilter/" })
public class ColaboradorFilter extends HttpServlet {

	HashMap<String, Object> js = new HashMap<String, Object>();
	RequestDispatcher dispatcher = null;
	private JSONObject colaboradorJSON;
	private JSONArray colaboradoresJSON;
	Gson gson = null;
	PrintWriter printWriter;
	
	private ColaboradorPersistence colaboradorPersistence;
	private List<Colaborador> colaboradors;
	private Colaborador colaborador;
	
	DepartamentoPersistence departamentoPersistence;
	Departamento departamento;
	
	private String responseSucess = "transação realizada com sucesso"; 

	public ColaboradorFilter() {
		super();
	}
	
    private void findBy(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	js.put("idc", request.getParameter("idc").trim());
		try {
			colaboradorPersistence = new ColaboradorPersistence();
			colaborador = colaboradorPersistence.findBy(Long.parseLong(js.get("idc").toString()));
			
			colaboradoresJSON = new JSONArray();
			for(Colaborador colaborador: colaboradors) {
				colaboradorJSON = new JSONObject();
				colaboradorJSON.put("datanascimento", DateUtil.converterCalendarToString(colaborador.getDataNascimento()));
				colaboradorJSON.put("departamento", colaborador.getDepartamento().getDepartamento());
				colaboradorJSON.put("hierarquia", colaborador.getDepartamento().getHierarquia());
				colaboradorJSON.put("sigla", colaborador.getDepartamento().getSigla());
				colaboradorJSON.put("nome", colaborador.getNomeCompleto());
				colaboradorJSON.put("remuneracao", colaborador.getRemuneracao());
				colaboradorJSON.put("cargo", colaborador.getCargo());
				colaboradorJSON.put("cnh", colaborador.getCnh());
				colaboradorJSON.put("cpf", colaborador.getCpf());
				colaboradorJSON.put("rg", colaborador.getRg());
				colaboradorJSON.put("codigo", colaborador.getId());
				colaboradoresJSON.put(colaboradorJSON);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(500);
			response.getWriter().print(gson.toJson(e.getMessage()));
		} finally {
			response.setStatus(200);
			response.getWriter().print(colaboradoresJSON);
		}
	}
	
	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, Exception {

		gson = new GsonBuilder().setPrettyPrinting().create();
		response.setContentType("application/json; charset=utf-8");
		int operator = Integer.parseInt(request.getParameter("actionServlet"));

		switch (operator) {
		case 400:
			findBy(request, response);
			break;
		}
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
	// + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception ex) {
			Logger.getLogger(ColaboradorFilter.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception ex) {
			Logger.getLogger(ColaboradorFilter.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * Handles the HTTP <code>PUT</code> method.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception ex) {
			Logger.getLogger(ColaboradorFilter.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
