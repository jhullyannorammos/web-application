package br.com.application.converter;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.application.domain.SoftwareLicense;

public class SoftwareLicenseToJSON {
	
	public static JSONArray SoftwareLicensesToJSON(List<SoftwareLicense> softwareLicenses) throws JSONException, Exception {
		JSONArray softwareLicensesJSON = new JSONArray();
		for(SoftwareLicense softwareLicense: softwareLicenses) {
			softwareLicensesJSON.put(SoftwareLicenseToJSON(softwareLicense));
		}
		return softwareLicensesJSON;
	}
	
	public static JSONObject SoftwareLicenseToJSON(SoftwareLicense softwareLicense) {
		JSONObject softwareLicenseJSON = new JSONObject();
		softwareLicenseJSON.put("codigo", softwareLicense.getPatrimonio().getPatrimonio());
		softwareLicenseJSON.put("codigo", softwareLicense.getSistema());
		softwareLicenseJSON.put("codigo", softwareLicense.getKey());
		softwareLicenseJSON.put("codigo", softwareLicense.getId());
		return softwareLicenseJSON;
	} 

}
