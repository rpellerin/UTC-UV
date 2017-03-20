package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bean.Categorie;
import utils.ErrorJSON;
import webservice.AdminAPIProxy;

@SuppressWarnings("serial")
public class CategoriesController extends HttpServlet {
	
	public final static String RESPONSE_CATEGORIES = "categories";
	public final static String RESPONSE_ERROR      = "message_error";
	public final static String RESPONSE_NAME       = "name";
	
	private final Gson json = new Gson();
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminAPIProxy ap = new AdminAPIProxy();
		
		String res = null;
		
		String edit = utils.Requests.getRequestValue(request, "edit");
		String delete = utils.Requests.getRequestValue(request, "delete");
		
		if (delete != null && !delete.trim().isEmpty()) {
			res = ap.deleteCategorie(Integer.parseInt(delete));
			if (res.equals("true")) {
				response.sendRedirect(request.getContextPath()+"/categories");
				return;
			}
			else {
				ErrorJSON err = json.fromJson(res, ErrorJSON.class);
				request.setAttribute(RESPONSE_ERROR, err.getError());
			}
		} else if (edit != null && !edit.trim().isEmpty()) {
			
			res = ap.getCategory(Integer.parseInt(edit));
			
			if (res.contains("categorie_id")) {
				Categorie c = json.fromJson(res, Categorie.class);
				request.setAttribute(RESPONSE_NAME, c.getNom());
				this.getServletContext().getRequestDispatcher("/WEB-INF/pages/categoriesedit.jsp").forward(request, response);
				return;
			}
			else {
				ErrorJSON err = json.fromJson(res, ErrorJSON.class);
				request.setAttribute(RESPONSE_ERROR, err.getError());
			}
		}
		
		res = ap.getAllCategorie();
		Categorie[] data = json.fromJson(res, Categorie[].class);
		
		request.setAttribute(RESPONSE_CATEGORIES, data);
		this.getServletContext().getRequestDispatcher("/WEB-INF/pages/categoriesmanagement.jsp").forward(request, response);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = utils.Requests.getRequestValue(request, "name");
		String edit = utils.Requests.getRequestValue(request, "edit");
		
		String res = null;
		
		if (name != null && !name.trim().isEmpty()) {
			AdminAPIProxy ap = new AdminAPIProxy();
			
			if(edit != null && !edit.trim().isEmpty()) {
				res = ap.updateCategorie(Integer.parseInt(edit), name);
				if (res.equals("true")) {
					response.sendRedirect(request.getContextPath()+"/categories");
					return;
				}
				else {
					ErrorJSON err = json.fromJson(res, ErrorJSON.class);
					request.setAttribute(RESPONSE_ERROR, err.getError());
				}
			}
			else {
				res = ap.createCategory(name);
				if (!res.contains("\"categorie_id\"")) {
					ErrorJSON err = json.fromJson(res, ErrorJSON.class);
					request.setAttribute(RESPONSE_ERROR, err.getError());
				}
			}
		}
		doGet(request, response);
	}
}
