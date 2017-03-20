package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bean.Annonce;
import bean.Categorie;
import utils.ErrorJSON;
import webservice.AdminAPIProxy;

@SuppressWarnings("serial")
public class AdsController extends HttpServlet {
	
	public final static String RESPONSE_ADS        = "ads";
	public final static String RESPONSE_CATEGORIES = "categories";
	public final static String RESPONSE_ERROR      = "message_error";
	public final static String RESPONSE_AD         = "ad";
	
	private final Gson json = new Gson();
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminAPIProxy ap = new AdminAPIProxy();
		
		String res = null, res2 = null;
		
		String edit = utils.Requests.getRequestValue(request, "edit");
		String delete = utils.Requests.getRequestValue(request, "delete");
		
		if (delete != null && !delete.trim().isEmpty()) {
			res = ap.deleteAd(Integer.parseInt(delete));
			if (res.equals("true")) {
				response.sendRedirect(request.getContextPath()+"/ads");
				return;
			}
			else {
				ErrorJSON err = json.fromJson(res, ErrorJSON.class);
				request.setAttribute(RESPONSE_ERROR, err.getError());
			}
		} else if (edit != null && !edit.trim().isEmpty()) {
			
			res = ap.getAd(Integer.parseInt(edit));
			
			if (res.contains("annonce_id")) {
				Annonce c = json.fromJson(res, Annonce.class);
				
				res2 = ap.getAllCategorie();
				Categorie[] cat = json.fromJson(res2, Categorie[].class);
				request.setAttribute(RESPONSE_CATEGORIES, cat);				
				request.setAttribute(RESPONSE_AD, c);
				this.getServletContext().getRequestDispatcher("/WEB-INF/pages/adsedit.jsp").forward(request, response);
				return;
			}
			else {
				ErrorJSON err = json.fromJson(res, ErrorJSON.class);
				request.setAttribute(RESPONSE_ERROR, err.getError());
			}
		}
		
		res = ap.getAllAd();
		res2 = ap.getAllCategorie();
		Annonce[] data = json.fromJson(res, Annonce[].class);
		Categorie[] cat = json.fromJson(res2, Categorie[].class);
		
		request.setAttribute(RESPONSE_ADS, data);
		request.setAttribute(RESPONSE_CATEGORIES, cat);
		this.getServletContext().getRequestDispatcher("/WEB-INF/pages/adsmanagement.jsp").forward(request, response);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = utils.Requests.getRequestValue(request, "name");
		String category = utils.Requests.getRequestValue(request, "category");
		String street = utils.Requests.getRequestValue(request, "street");
		String city = utils.Requests.getRequestValue(request, "city");
		String postcode = utils.Requests.getRequestValue(request, "postcode");
		String telephone = utils.Requests.getRequestValue(request, "telephone");
		
		String edit = utils.Requests.getRequestValue(request, "edit");
		
		String res = null;
		
		if (name != null && !name.trim().isEmpty() &&
				category != null && !category.trim().isEmpty() &&
				street != null && !street.trim().isEmpty() &&
				city != null && !city.trim().isEmpty() &&
				postcode != null && !postcode.trim().isEmpty() &&
				telephone != null && !telephone.trim().isEmpty()) {
			
			AdminAPIProxy ap = new AdminAPIProxy();
			
			if(edit != null && !edit.trim().isEmpty()) {
				res = ap.updateAd(Integer.parseInt(edit), name, Integer.parseInt(category), street, city, postcode, telephone);
				if (res.equals("true")) {
					response.sendRedirect(request.getContextPath()+"/ads");
					return;
				}
				else {
					ErrorJSON err = json.fromJson(res, ErrorJSON.class);
					request.setAttribute(RESPONSE_ERROR, err.getError());
				}
			}
			else {
				res = ap.createAd(name, Integer.parseInt(category), street, city, postcode, telephone);
				if (!res.contains("\"annonce_id\"")) {
					ErrorJSON err = json.fromJson(res, ErrorJSON.class);
					request.setAttribute(RESPONSE_ERROR, err.getError());
				}
			}
		}
		doGet(request, response);
	}
}
