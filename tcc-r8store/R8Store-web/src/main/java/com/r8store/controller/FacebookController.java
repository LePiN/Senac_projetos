package com.r8store.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.brickred.socialauth.SocialAuthConfig;
import org.brickred.socialauth.SocialAuthManager;
import org.brickred.socialauth.util.SocialAuthUtil;
import org.json.JSONException;
import org.json.JSONObject;

import com.r8store.enums.Enum_Gender;
import com.r8store.enums.Enum_Type_User;

import com.r8store.model.dao.PersonDAO;
import com.r8store.model.entity.Person;
import com.r8store.model.entity.User;

@Named
@SessionScoped
public class FacebookController extends Controller implements Serializable {

	private static final long serialVersionUID = 3658300628580536494L;
	
	private SocialAuthManager socialManager;

	private final String mainURL = "http://localhost:8081/R8Store/dashboard.xhtml";	
	private final String provider = "facebook";
	private String accessToken = "";
	
	public static final String FB_APP_ID = "190233288406047";
	public static final String FB_APP_SECRET = "dc490d404c52f4dafb9fc5017df8db9e";
	public static final String REDIRECT_URI = "http://localhost:8081/R8Store/redirectFacebook.xhtml";
	public static final String FIELDS = "first_name,name,gender,email";
	
	@Inject
	private PersonDAO pDAO;
	
	@Inject
	private UserController userController;

	public void connect() {
		Properties prop = System.getProperties();
		prop.put("graph.facebook.com.consumer_key", FB_APP_ID);
		prop.put("graph.facebook.com.consumer_secret", FB_APP_SECRET);
		prop.put("graph.facebook.com.custom_permissions", "public_profile,email,manage_pages");

		SocialAuthConfig socialConfig = SocialAuthConfig.getDefault();
		try {
			socialConfig.load(prop);
			socialManager = new SocialAuthManager();
			socialManager.setSocialAuthConfig(socialConfig);
			String URLRetorno = socialManager.getAuthenticationUrl(provider, REDIRECT_URI);			
			FacesContext.getCurrentInstance().getExternalContext().redirect(URLRetorno);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getUser(ComponentSystemEvent event) throws Exception {		
		ExternalContext ex = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ex.getRequest();

		Map<String, String> parametros = SocialAuthUtil.getRequestParametersMap(request);

		if (socialManager != null) {
			this.accessToken = getAccessToken(parametros.get("code"));

			String graph = getFBGraph();
			Map<String, String> fbProfileData = getGraphData(graph);
			
			if (fbProfileData.get("email") != null) {
				String email = fbProfileData.get("email");
				Person p = pDAO.findByEmail(email);
				User u = new User();
				
				if (p == null) {
					p = new Person();
					p.setId(0l);
					p.setName(fbProfileData.get("name"));					
					String gender = fbProfileData.get("gender");					
					if (gender.equals("male")) {
						p.setGender(Enum_Gender.MASCULINO);
					} else {
						p.setGender(Enum_Gender.FEMININO);
					}
					p.setType(Enum_Type_User.PERSON);
					p.setEmail(email);
										
					u.setPerson(p);	
					pDAO.save(p);
				}
				
				this.userController.setUsuarioLogado(u);
				this.userController.setMeta(p);
			} else {
				//EMAIL DO FACEBOOK NÃO FOI VERIFICADO.
			}															
			
		}

		this.redirect(mainURL);
		return;
	}
	
	public String getFBGraphUrl(String code) {
		String fbGraphUrl = "";
		try {
			fbGraphUrl = "https://graph.facebook.com/oauth/access_token?"
					+ "client_id=" + FB_APP_ID + "&redirect_uri="
					+ URLEncoder.encode(REDIRECT_URI, "UTF-8")
					+ "&client_secret=" + FB_APP_SECRET + "&code=" + code;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return fbGraphUrl;
	}

	public String getAccessToken(String code) {
		if ("".equals(accessToken)) {
			URL fbGraphURL;
			try {
				fbGraphURL = new URL(getFBGraphUrl(code));
			} catch (MalformedURLException e) {
				e.printStackTrace();
				throw new RuntimeException("Invalid code received " + e);
			}
			URLConnection fbConnection;
			StringBuffer b = null;
			try {
				fbConnection = fbGraphURL.openConnection();
				BufferedReader in;
				in = new BufferedReader(new InputStreamReader(
						fbConnection.getInputStream()));
				String inputLine;
				b = new StringBuffer();
				while ((inputLine = in.readLine()) != null)
					b.append(inputLine + "\n");
				in.close();
				accessToken = b.toString();
				
				JSONObject json = new JSONObject(this.accessToken);
				this.accessToken = json.getString("access_token");
			} catch (IOException | JSONException e) {
				e.printStackTrace();
				throw new RuntimeException("Unable to connect with Facebook "
						+ e);
			}
			
			if (accessToken.startsWith("{")) {
				throw new RuntimeException("ERROR: Access Token Invalid: "
						+ accessToken);
			}
		}
		System.out.println("Token de acesso: " + this.accessToken);
		return accessToken;
	}
	
	public String getFBGraph() {
		String graph = null;
		try {

			String g = "https://graph.facebook.com/me?access_token=" + accessToken + "&fields=" + FIELDS;
			URL u = new URL(g);
			URLConnection c = u.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					c.getInputStream()));
			String inputLine;
			StringBuffer b = new StringBuffer();
			while ((inputLine = in.readLine()) != null)
				b.append(inputLine + "\n");
			in.close();
			graph = b.toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR in getting FB graph data. " + e);
		}
		return graph;
	}

	public Map getGraphData(String fbGraph) {
		Map fbProfile = new HashMap();
		try {
			JSONObject json = new JSONObject(fbGraph);
			fbProfile.put("id", json.getString("id"));
			if (json.has("first_name"))
				fbProfile.put("first_name", json.getString("first_name"));
			if (json.has("name"))
				fbProfile.put("name", json.getString("name"));
			if (json.has("email"))
				fbProfile.put("email", json.getString("email"));
			if (json.has("gender"))
				fbProfile.put("gender", json.getString("gender"));
		} catch (JSONException e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR in parsing FB graph data. " + e);
		}
		return fbProfile;
	}

}