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

import br.com.application.converter.CidadeConverterToJSON;
import br.com.application.domain.Cidade;
import br.com.application.persistence.jpa.CidadePersistence;


@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/Cidades" })
public class CidadeView extends HttpServlet {

	private CidadePersistence cidadePersistence;
	List<Cidade> cidades;
	JSONArray cidadesJSON;
	private Gson gson = null;

	private void findAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			cidadePersistence = new CidadePersistence();
			cidades = cidadePersistence.findAll();
			cidadesJSON = new JSONArray();
			cidadesJSON = CidadeConverterToJSON.cidadesToJSON(cidades);
		} catch (Exception exception) {
			exception.printStackTrace();
			response.setStatus(500);
			response.getWriter().print(exception.getMessage().toString());
		} finally {
			response.setStatus(200);
			response.getWriter().print(cidadesJSON);
		}
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException, Exception {
		gson = new GsonBuilder().setPrettyPrinting().create();
		response.setContentType("application/json");
		int actionServlet = Integer.parseInt(request.getParameter("actionServlet"));
		switch (actionServlet) {
		case 400:
			findAll(request, response);
			break;
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception ex) {
			Logger.getLogger(ProdutoView.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
