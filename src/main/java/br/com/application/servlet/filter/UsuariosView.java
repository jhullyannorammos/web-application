package br.com.application.servlet.filter;

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
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.GsonBuilder;

import br.com.application.domain.Usuario;
import br.com.application.enumerator.UsuarioEnum;
import br.com.application.enumerator.UsuarioStatus;
import br.com.application.persistence.jpa.UsuarioPersistence;
import br.com.application.security.Criptografy;
import br.com.application.security.Logado;

import com.google.gson.Gson;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/UsuarioFilter/"})
public class UsuariosView extends HttpServlet {

	HashMap<String, Object> js = new HashMap<String, Object>();
	HttpServletRequest httpRequest = null;
	RequestDispatcher dispatcher = null;
	String url = "application?pg=156";
	
	private JSONObject usuarioJSON;
	private JSONArray usuariosJSON;

	HttpSession session = null;
	Gson gson = null;

	Logado logado;

	private UsuarioPersistence usuarioPersistence;
	private List<Usuario> usuarios;
	private Usuario usuario;

	public UsuariosView() {
		super();
	}

	
	private void findAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			usuarios = new ArrayList<Usuario>();
			usuarioPersistence = new UsuarioPersistence();
			usuariosJSON = new JSONArray();
			for(Usuario user : usuarioPersistence.findAll()) {
				usuarioJSON = new JSONObject();
				usuarioJSON.put("username", user.getUsername());
				usuarioJSON.put("password", user.getPassword());
				usuarioJSON.put("perfil", user.getPerfil());
				usuarioJSON.put("email", user.getEmail());
				usuarioJSON.put("codigo", user.getId());
				usuariosJSON.put(usuarioJSON);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(500);
			response.getWriter().print(e.getMessage().toString());
		}finally {
			response.setStatus(200);
			response.getWriter().print(usuariosJSON);
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
		response.setContentType("application/json");
		int op = Integer.parseInt(request.getParameter("actionServlet"));
		switch (op) {
		case 400:
			findAll(request, response);
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
			Logger.getLogger(UsuariosView.class.getName()).log(Level.SEVERE, null, ex);
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
			Logger.getLogger(UsuariosView.class.getName()).log(Level.SEVERE, null, ex);
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
