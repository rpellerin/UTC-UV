package app;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import io.InputFile;
import lexicon.Lemmas;
import lexicon.StopList;
import utils.ConsoleOutputBuffer;

/**
 * Class used once on webapp startup and on webapp destroy
 * @author Romain Pellerin
 */
public final class App implements ServletContextListener {

	public static final boolean DEBUG = false;
	
	public static StopList stoplist;
	public static Lemmas lemmas;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		try {
			/**
			 * Reading the arguments and creating the associated files
			 */
			InputFile stoplist1File = new InputFile(sce.getServletContext().getRealPath("/WEB-INF/OUR_FILES/stoplist.txt"));
			ArrayList<String> stoplist1FileContent = stoplist1File.read();
		
			InputFile lemma1File = new InputFile(sce.getServletContext().getRealPath("/WEB-INF/OUR_FILES/lemmes.txt"));
			ArrayList<String> lemma1FileContent = lemma1File.read();
			
			InputFile stoplist2File = new InputFile(sce.getServletContext().getRealPath("/WEB-INF/OUR_FILES/stoplist_OURS.txt"));
			ArrayList<String> stoplist2FileContent = stoplist2File.read();
			
			InputFile lemma2File = new InputFile(sce.getServletContext().getRealPath("/WEB-INF/OUR_FILES/lemmes_OURS.txt"));
			ArrayList<String> lemma2FileContent = lemma2File.read();
			

			/**
			 * Instantiating objects
			 */
			stoplist = new StopList(stoplist1FileContent);
			lemmas   = new Lemmas(lemma1FileContent);
			stoplist.addWords(stoplist2FileContent);
			lemmas.addWords(lemma2FileContent);
			
		} catch (IOException e) {
			ConsoleOutputBuffer.getInstance().addErrorLine("Errow while reading files.");
			ConsoleOutputBuffer.getInstance().addStackTrace(e.getStackTrace());
			ConsoleOutputBuffer.getInstance().printBufferOnStandardOutput();
		} catch (Exception e) {
			ConsoleOutputBuffer.getInstance().addErrorLine("Error while instantiation the stoplist and lemma.");
			ConsoleOutputBuffer.getInstance().addStackTrace(e.getStackTrace());
			ConsoleOutputBuffer.getInstance().printBufferOnStandardOutput();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}
}
