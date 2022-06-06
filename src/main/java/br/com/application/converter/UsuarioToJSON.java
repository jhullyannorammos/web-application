package br.com.application.converter;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.application.domain.Usuario;

public class UsuarioToJSON {
	
	public static JSONArray UsuariosToJSON(List<Usuario> usuarios) throws JSONException, Exception {
		JSONArray departamentosJSON = new JSONArray();
		for(Usuario Usuario: usuarios) {
			departamentosJSON.put(UsuarioToJSON(Usuario));
		}
		return departamentosJSON;
	}
	
	private static JSONObject UsuarioToJSON(Usuario usuario) {
		JSONObject usuarioJSON = new JSONObject();
		usuarioJSON.put("codigo", usuario.getPassword());
		usuarioJSON.put("codigo", usuario.getUsername());
		usuarioJSON.put("codigo", usuario.getStatus());
		usuarioJSON.put("codigo", usuario.getPerfil());
		usuarioJSON.put("codigo", usuario.getEmail());
		usuarioJSON.put("codigo", usuario.getId());
		return usuarioJSON;
	} 

}
