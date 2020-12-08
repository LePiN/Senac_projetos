package com.r8store.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.r8store.model.dao.CityDAO;
import com.r8store.model.dao.StateDAO;
import com.r8store.model.entity.City;
import com.r8store.model.entity.State;


public class HttpAddress {
	
	public static JSONObject returnAddress(String cep) {
		try(CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
			HttpPost request = new HttpPost("https://viacep.com.br/ws/" + cep + "/json/");
            request.addHeader("content-type", "application/json");
            HttpResponse result = httpClient.execute(request);
            String output = EntityUtils.toString(result.getEntity(), "UTF-8");
						
			JSONObject objectJson = new JSONObject(output);
			if (objectJson != null) {
				verifyDatabase(objectJson);
			}
			return objectJson;
    	} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static void verifyDatabase(JSONObject obj) {
		StateDAO stDAO = new StateDAO();
		CityDAO cDAO = new CityDAO();
		
		try {
			State state = stDAO.findStateByInitial(obj.getString("uf").toUpperCase());
			if (state != null) {
				City city = cDAO.findCityByStateAndName(state.getId(), obj.getString("localidade").toUpperCase());
				System.out.println(city);
				if (city == null) {
					city = new City();
					city.setName(obj.getString("localidade").toUpperCase());
					city.setState(state);
					cDAO.merge(city);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}

