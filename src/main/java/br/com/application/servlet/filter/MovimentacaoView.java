package br.com.application.servlet.filter;

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

import br.com.application.domain.Colaborador;
import br.com.application.domain.Mercadoria;
import br.com.application.domain.Movimentacao;
import br.com.application.enumerator.MovimentacaoEnum;
import br.com.application.persistence.jpa.ColaboradorPersistence;
import br.com.application.persistence.jpa.MercadoriaPersistence;
import br.com.application.persistence.jpa.MovimentacaoPersistence;
import br.com.application.util.DateUtil;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/MovimentacaoFilter/" })
public class MovimentacaoView extends HttpServlet {

	private JSONArray movimentacaosJSON;
	private JSONObject movimentacaoJSON;
	
	JSONArray mercadoriasJSON;
	JSONObject mercadoriaJSON;
	
	private ColaboradorPersistence colaboradorPersistence;
	private Colaborador colaborador;
	
	private MovimentacaoPersistence movimentacaoPersistence;
	private Movimentacao movimentacao;
	
	private MercadoriaPersistence mercadoriaPersistence;
	private List<Mercadoria> mercadorias;
	private Mercadoria mercadoria;
	
	private HashMap<String, Object> js = new HashMap<String, Object>();
	private Gson gson = null;


	public MovimentacaoView() {
		super();
	}
	
	private void findAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			movimentacaoPersistence = new MovimentacaoPersistence();
			movimentacaosJSON = new JSONArray(); 
			for(Movimentacao movimentacao : movimentacaoPersistence.findAll()) {
				movimentacaoJSON = new JSONObject();
				movimentacaoJSON.put("movimentacao", movimentacao.getMovimentacao());
				movimentacaoJSON.put("periodo", movimentacao.getPeriodo());
				movimentacaoJSON.put("codigo", movimentacao.getId());
				//movimentacaoJSON.put("cargo", movimentacao.getColaborador().getCargo());
				//movimentacaoJSON.put("matricula", movimentacao.getColaborador().getMatricula());
				mercadoriasJSON = new JSONArray();
				for(Mercadoria mercadoria : movimentacao.getMercadorias()) {
					mercadoriaJSON = new JSONObject();
					mercadoriaJSON.put("patrimonio", mercadoria.getPatrimonio().getPatrimonio());
					mercadoriaJSON.put("produto", mercadoria.getProduto().getProduto());
					mercadoriaJSON.put("serial", mercadoria.getSerial());
					mercadoriasJSON.put(mercadoriaJSON);
				}
				movimentacaoJSON.put("mercadorias", mercadoriasJSON);
				movimentacaosJSON.put(movimentacaoJSON);
			}
			
		}catch (Exception exception) {
			exception.printStackTrace();
			response.setStatus(500);
			response.getWriter().print(gson.toJson(exception.getMessage()));
		}finally {
			response.setStatus(200);
			response.getWriter().print(movimentacaosJSON);
		}
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException, Exception {
		gson = new GsonBuilder().setPrettyPrinting().create();
		response.setContentType("application/json");
		int op = Integer.parseInt(request.getParameter("actionServlet"));
		switch (op) {
		case 300:
			findAll(request, response);
			break;
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
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
