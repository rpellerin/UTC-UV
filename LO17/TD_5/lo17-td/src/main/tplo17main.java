package main;


import java.io.FileInputStream;

import org.apache.jena.larq.IndexBuilderString;
import org.apache.jena.larq.IndexLARQ;
import org.apache.jena.larq.LARQ;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class tplo17main {
	public static String ontoN3Filename = "documents/lo17-ontology.n3";
	public static String kb = "documents/kblo17.owl";
	public static String LO17_PREFIX = "lo17";
	public static Logger logger = Logger.getLogger("com.hp");

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BasicConfigurator.configure();
		logger.setLevel(Level.INFO);
		//q1();
		//q2();
		//q3();
		q4();

	}
	// Réponse à la question 1
		public static void q1() {
			Model model = model = ModelFactory.createDefaultModel();
			try {
				// Crée un model
				model.read(new FileInputStream(ontoN3Filename),null,"TURTLE");
				// Crée une query (modifier le fichier)
				// Remplacer les ???? dans le fichier requête
				Query q1 = readFileQuery("queries/concept.sparql");
				System.out.println("===== Concepts =====");
				// Exécute la query
				runQuery(q1, model);
				
				Query q2 = readFileQuery("queries/relations.sparql");
				System.out.println("===== Relations =====");
				// Exécute la query
				runQuery(q2, model);
				
				Query q3 = readFileQuery("queries/attributs.sparql");
				System.out.println("===== Attributs =====");
				// Exécute la query
				runQuery(q3, model);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	// Réponse à la question 2
	public static void q2() {
		Model model = null;
		model = ModelFactory.createDefaultModel();
		try {
			// Crée un model
			model.read(new FileInputStream(kb), "RDF/XML");
			// Remplacer les ???? dans le fichier requête
			Query q1 = readFileQuery("queries/titres.sparql");

			System.out.println("===== Titres =====");
			// Exécute la query
			runQuery(q1, model);
			
			Query q2 = readFileQuery("queries/rubriques.sparql");

			System.out.println("===== Rubriques =====");
			// Exécute la query
			runQuery(q2, model);
			
			Query q3 = readFileQuery("queries/titres_cnrs.sparql");

			System.out.println("===== Titres contenant CNRS =====");
			// Exécute la query
			runQuery(q3, model);
			
			Query q4 = readFileQuery("queries/rub_2011.sparql");

			System.out.println("===== Rubriques 2011 =====");
			// Exécute la query
			runQuery(q4, model);
			
			Query q5 = readFileQuery("queries/bulletins_img.sparql");

			System.out.println("===== Bulletins images =====");
			// Exécute la query
			runQuery(q5, model);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	// Réponse à la question 3
		public static void q3() {
			Model model = null;
			model = ModelFactory.createDefaultModel();
			try {
				// Crée un model
				model.read(new FileInputStream(kb), "RDF/XML");
				
				// Compléter la requête
				Query q1 = readFileQuery("queries/contacts.sparql");

				System.out.println("===== Contacts =====");
				// Exécute la query
				runQuery(q1, model);
				
				Query q2 = readFileQuery("queries/rubrique_most_used.sparql");

				System.out.println("===== Rubriques most used =====");
				// Exécute la query
				runQuery(q2, model);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		// Réponse à la question 4
		public static void q4() {
			Model model = null;
			model = ModelFactory.createDefaultModel();
			try {
				// Crée un model
				model.read(new FileInputStream(kb), "RDF/XML");
				IndexLARQ wholeIndex = getWholeStringIndex(model); 
				// Remplacer les ???? dans le fichier requête
				Query q1 = readFileQuery("queries/capteurs.sparql");

				System.out.println("===== Capteurs =====");
				// Exécute la query sur le modèle en utilisant l'index
				queryIndex(q1,wholeIndex,model);
				
				Query q2 = readFileQuery("queries/infra.sparql");

				System.out.println("===== Infra internet =====");
				// Exécute la query sur le modèle en utilisant l'index
				queryIndex(q2,wholeIndex,model);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				// Crée un model
				model.read(new FileInputStream(kb), "RDF/XML");
				IndexLARQ wholeIndex = getWholeStringIndex(model); 
				// Remplacer les ???? dans le fichier requête
				Query q3 = readFileQuery("queries/creation.sparql");

				System.out.println("===== Création =====");
				// Exécute la query sur le modèle en utilisant l'index
				  queryIndex(q3,wholeIndex,model);
				// à continuer
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				// Crée un model
				model.read(new FileInputStream(kb), "RDF/XML");
				IndexLARQ wholeIndex = getWholeStringIndex(model); 
				// Remplacer les ???? dans le fichier requête
				Query q4 = readFileQuery("queries/chaire.sparql");

				System.out.println("===== Chaire =====");
				// Exécute la query sur le modèle en utilisant l'index
				  queryIndex(q4,wholeIndex,model);
				// à continuer
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	/**
	 * Retourne une query à partir d'un nom de fichier
	 * @param filename
	 * @return
	 */
	public static Query readFileQuery(String filename) {
		return QueryFactory.read(filename);
	}

	/**
	 * Retourne une query à partir de la chaîne de la query
	 * @param queryString
	 * @return
	 */
	public static Query readStringQuery(String queryString) {
		return QueryFactory.create(queryString);
	}

	/**
	 * Exécute une query sur un model. L'affichage se fait dans la console
	 * @param q
	 * @param model
	 */
	public static void runQuery(Query q, Model model) {
		QueryExecution queryExecution = QueryExecutionFactory.create(q, model);
		ResultSet r = queryExecution.execSelect();
		ResultSetFormatter.out(System.out, r);
		queryExecution.close();
	}

	/**
	 * Retourne l'index des noeuds littéraux de toute la base
	 * @param model
	 * @return
	 */
	public static IndexLARQ getWholeStringIndex(Model model) {
		IndexBuilderString larqBuilder = new IndexBuilderString();
		larqBuilder.indexStatements(model.listStatements());
		larqBuilder.closeWriter();
		model.unregister(larqBuilder);
		return larqBuilder.getIndex();
	}

	/**
	 * Exécute une query nécessitant un index LARQ.
	 * La sortie se fait dans la console
	 * @param q
	 * @param index
	 * @param model
	 */
	public static void queryIndex(Query q, IndexLARQ index, Model model) {
		QueryExecution queryExecution = QueryExecutionFactory.create(q, model);
		LARQ.setDefaultIndex(queryExecution.getContext(), index);
		ResultSet r = queryExecution.execSelect();
		ResultSetFormatter.out(System.out, r);
		queryExecution.close();
	}
}
