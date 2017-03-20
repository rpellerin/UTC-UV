package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bean.Annonce;
import utils.ErrorJSON;
import webservice.ClientAPIProxy;

@SuppressWarnings("serial")
public class HomeController extends HttpServlet {
	
	public final static String RESPONSE_ADS        = "ads";
	public final static String RESPONSE_ERROR      = "message_error";
	
	private final Gson json = new Gson();
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = utils.Requests.getRequestValue(request, "name");
		String category = utils.Requests.getRequestValue(request, "category");
		String street = utils.Requests.getRequestValue(request, "street");
		String city = utils.Requests.getRequestValue(request, "city");
		String postcode = utils.Requests.getRequestValue(request, "postcode");
		
		
		ClientAPIProxy ap = new ClientAPIProxy();
		String res = ap.getByParams(name, category, street, city, postcode);
		if (res.contains("[")) {
			System.out.println(res);
			Annonce[] ads = json.fromJson(res, Annonce[].class);
			request.setAttribute(RESPONSE_ADS, ads);
		}
		else {
			ErrorJSON err = json.fromJson(res, ErrorJSON.class);
			request.setAttribute(RESPONSE_ERROR, err.getError());
		}
		
		request.setAttribute("name", name);
		request.setAttribute("category", category);
		request.setAttribute("street", street);
		request.setAttribute("city", city);
		request.setAttribute("postcode", postcode);
		this.getServletContext().getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request, response);
	}
}
