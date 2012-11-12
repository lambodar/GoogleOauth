package com.adaptavant.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
@SuppressWarnings("serial")
public class GetDetailServlet extends HttpServlet{
	private String firstname;
	private String name;
	private String gender;
	@SuppressWarnings("unused")
	private Object username;
	private String email;
	private String profileImage;
	private String family_Name;

	public void service(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		HttpSession session=request.getSession(true);
		if(session!=null){
		String code = request.getParameter("code");
		@SuppressWarnings("unused")
		HttpTransport HTTP_TRANSPORT = new NetHttpTransport();//used inside 
		System.out.println("code is here :: " + code);
		//GoogleAuthorizationCodeFlow 
		//response.sendRedirect("");
		//URL url=new URL("");
		//HttpClient client = new DefaultHttpClient();
		HttpTransport transport=new com.google.api.client.http.javanet.NetHttpTransport();
		String CLIENT_ID="xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
		String CLIENT_SECRET="xxxxxxxxxxxxxxxxxxxxxxxxx";
		String authorizationCode=code;
		String CALLBACK_URL="http://xxxxxxxxxxxxxxxxxxxxxxxxx.com/getDetail";
		System.out.println("before jsonfactory");
		//JsonFactory JSON_FACTORY = new JacksonFactory();
		JacksonFactory JSON_FACTORY = new JacksonFactory();
		System.out.println("after jsonfactory");
		//GoogleAuthorizationCodeGrant codeGrant = new GoogleAuthorizationCodeGrant(transport,JSON_FACTORY, CLIENT_ID, CLIENT_SECRET, authorizationCode, CALLBACK_URL);
		//AccessTokenResponse authResponse = new GoogleAuthorizationCodeGrant(httpTransport, jsonFactory,clientId, clientSecret, code, redirectUrl).execute();
		GoogleAuthorizationCodeTokenRequest tokenRequest=new GoogleAuthorizationCodeTokenRequest(transport,JSON_FACTORY, CLIENT_ID, CLIENT_SECRET, authorizationCode, CALLBACK_URL);
		GoogleTokenResponse tokenResponse=tokenRequest.execute();
		//GoogleIdToken idToken=tokenResponse.parseIdToken();
		//new AuthorizationCodeTokenRequest();
		String original_token=tokenResponse.getAccessToken();
		System.out.println("AccessToken:"+original_token);
		GenericUrl googleApiGetWay=new GenericUrl("https://www.googleapis.com/oauth2/v1/userinfo?access_token="+original_token);
		//googleApiGetWay.appendRawPath("https://www.googleapis.com/oauth2/v1/userinfo?access_token="+original_token);
		GoogleCredential cradential=new GoogleCredential();
		HttpRequestFactory userrequest = new NetHttpTransport().createRequestFactory(cradential);
		HttpRequest googlerequest = userrequest.buildGetRequest(googleApiGetWay);
		HttpResponse userDetailsUrl = googlerequest.execute();
		BufferedReader userDetails = new BufferedReader(new InputStreamReader(userDetailsUrl.getContent()));
		String parsingString="";
		System.out.println("==========Google user details===============");
		for (String line = userDetails.readLine(); line != null; line = userDetails.readLine()) {
		      System.out.println(line);
		      parsingString+=line;
		    }
		    ObjectMapper mapper = new ObjectMapper();
		     mapper.configure(org.codehaus.jackson.JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		      if(parsingString != null && !("".equalsIgnoreCase(parsingString))){
		    	  JsonNode jnode=mapper.readValue(parsingString,JsonNode.class);
		    	  if(jnode.get("email") != null)	
		    		  email = jnode.get("email").toString().replaceAll("\"", "");
		    	  if(jnode.get("given_name") != null)
		    		  firstname = jnode.get("given_name").toString().replaceAll("\"", "");
		    	  if(jnode.get("name") != null)
		    		  name = jnode.get("name").toString().replaceAll("\"", "");
		    	  if(jnode.get("family_name") != null)
		    		  family_Name = jnode.get("family_name").toString().replaceAll("\"", "");
		    	  if(jnode.get("picture") != null)
		    		  profileImage =  jnode.get("picture").toString().replaceAll("\"", ""); 
		    	  if(jnode.get("gender") != null)
		    		  gender=  jnode.get("gender").toString().replaceAll("\"", "");
		    	  username=email.substring(0,email.indexOf("@"));
		      }
		      	session.setAttribute("username", username);
		      	session.setAttribute("family", family_Name);
		      	session.setAttribute("img", profileImage);
		      	session.setAttribute("gender", gender);
		      	session.setAttribute("email", email);
		      	session.setAttribute("name", name);
		      	session.setAttribute("firstname", firstname);
		      	
				System.out.println("username : "+username);
				System.out.println("Name::"+name);
				System.out.println("email::"+email);
				System.out.println("FirstName::"+firstname);
				System.out.println("fam::"+family_Name);
				System.out.println("img::"+profileImage);
				System.out.println("Gender::"+gender);
				response.sendRedirect("/views/usercheck.jsp");
		}//session
	}
}
