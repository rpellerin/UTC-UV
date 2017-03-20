import java.io.IOException;
import java.io.StringReader;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.antlr.runtime.ANTLRReaderStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

import app.App;
import sql.Database;
import sql.SQLFormatter;
import utils.ConsoleOutputBuffer;
import utils.StringSanitizer;
import sql.Database.Tuple;


public class HomeController extends HttpServlet {

	private static final long serialVersionUID = -5529044398132534888L; // Avoid warning
	
	// Form
	public static final String REQUEST_REQUEST      = "request";
	// Results
	public static final String RESPONSE_HEADERS     = "results_headers";
	public static final String RESPONSE_RESULTS     = "results";
	public static final String RESPONSE_STACKTRACE  = "stacktrace";
	// Error
	public static final String RESPONSE_ERROR       = "message_error";
	// Request submitted by user
	public static final String RESPONSE_REQUEST     = "request";
	// Possible suggestions regarding the user's request
	public static final String RESPONSE_SUGGESTIONS = "suggestions";
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestUser = utils.Request.getRequestValue(request, REQUEST_REQUEST);
		if (requestUser != null && !requestUser.trim().isEmpty()) {
			doPost(request, response);
		}
		else {
			this.getServletContext().getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request, response);
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestUser = utils.Request.getRequestValue(request, REQUEST_REQUEST);
		request.setAttribute(RESPONSE_REQUEST, requestUser);
		
		if (requestUser != null && !requestUser.trim().isEmpty()) {
			ConsoleOutputBuffer.getInstance().clear();
			
			/**
			 * Prompting request
			 */
			//STDin s = new STDin(System.in);
			//String prompt = s.toLowerCase().toString();
			String prompt = requestUser.trim();
			prompt = StringSanitizer.sanitize(prompt);
			
			prompt = App.stoplist.removeWordsFromStopList(prompt.toLowerCase().toString());
			prompt = StringSanitizer.removePunctuation(prompt);
			prompt = StringSanitizer.sanitize(prompt);
			ConsoleOutputBuffer.getInstance().addLine("REQUEST WITHOUT USELESS WORDS: "+prompt);
			
			
			prompt = App.lemmas.replaceWordsWithLemmas(prompt);
			ConsoleOutputBuffer.getInstance().addLine("REQUEST WITH LEMMAS: "+prompt);
			
			prompt = SQLFormatter.preFormat(prompt);
			//prompt = StringSanitizer.sanitize(prompt);
			ConsoleOutputBuffer.getInstance().addLine("REQUEST PREFORMATTED: "+prompt);
			
			/**
			 * SQL
			 */
			Tal_OURSLexer lexer = new Tal_OURSLexer(new ANTLRReaderStream(new StringReader(prompt)));
	        CommonTokenStream tokens2 = new CommonTokenStream(lexer);
	        Tal_OURSParser parser = new Tal_OURSParser(tokens2);
			try {
				String req = parser.listerequetes();
				ConsoleOutputBuffer.getInstance().addLine("SQL REQUEST FROM GRAMMAR: "+req);
				
				req = SQLFormatter.postFormat(req);
				req = StringSanitizer.sanitize(req);
				
				ConsoleOutputBuffer.getInstance().addLine("SQL REQUEST FROM GRAMMAR POST FORMATTED: "+req);
				ConsoleOutputBuffer.getInstance().printBufferOnStandardOutput();
				
				Tuple<ArrayList<String>,ArrayList<ArrayList<String>>> res = Database.doRequest(req, null);
				
				ArrayList<String> headers = res.headers;
				ArrayList<ArrayList<String>> values = res.array;
				
				ArrayList<String> suggestions = App.lemmas.getSuggestions();
				request.setAttribute(RESPONSE_SUGGESTIONS, suggestions.size() <= 1 ? null : suggestions);
				request.setAttribute(RESPONSE_HEADERS, headers);
				request.setAttribute(RESPONSE_RESULTS, values);
				
			} catch (RecognitionException e) {
				e.printStackTrace();
			} catch(SQLException ex) {
				ConsoleOutputBuffer.getInstance().addErrorLine("==> SQLException: ");
				while (ex != null) {
					ConsoleOutputBuffer.getInstance().addErrorLine("Message:   " + ex.getMessage ());
					ConsoleOutputBuffer.getInstance().addErrorLine("SQLState:  " + ex.getSQLState ());
					ConsoleOutputBuffer.getInstance().addErrorLine("ErrorCode: " + ex.getErrorCode ());
					ex = ex.getNextException();
					ConsoleOutputBuffer.getInstance().addErrorLine("");
				}
			}
		
		}
		else {
			ConsoleOutputBuffer.getInstance().addLine("Empty request received");
		}
		request.setAttribute(RESPONSE_STACKTRACE, ConsoleOutputBuffer.getInstance().getBuffer());
		this.getServletContext().getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request, response);
	}
}

