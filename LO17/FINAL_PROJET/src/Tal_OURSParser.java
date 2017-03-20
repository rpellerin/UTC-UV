// $ANTLR 3.5.2 /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g 2016-06-13 14:06:37

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class Tal_OURSParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "ANNEE", "DATES", "ET", "JOUR", 
		"MOIS", "MOTS", "OU", "RUBRIQUES", "SELECT", "VAR", "WS"
	};
	public static final int EOF=-1;
	public static final int ANNEE=4;
	public static final int DATES=5;
	public static final int ET=6;
	public static final int JOUR=7;
	public static final int MOIS=8;
	public static final int MOTS=9;
	public static final int OU=10;
	public static final int RUBRIQUES=11;
	public static final int SELECT=12;
	public static final int VAR=13;
	public static final int WS=14;

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public Tal_OURSParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public Tal_OURSParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	@Override public String[] getTokenNames() { return Tal_OURSParser.tokenNames; }
	@Override public String getGrammarFileName() { return "/home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g"; }



	// $ANTLR start "listerequetes"
	// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:22:1: listerequetes returns [String sql = \"\"] : r= requete ;
	public final String listerequetes() throws RecognitionException {
		String sql =  "";


		Arbre r =null;

		Arbre lr_arbre;
		try {
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:22:64: (r= requete )
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:23:2: r= requete
			{
			pushFollow(FOLLOW_requete_in_listerequetes198);
			r=requete();
			state._fsp--;


					lr_arbre = r;
					sql = lr_arbre.sortArbre();
				
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return sql;
	}
	// $ANTLR end "listerequetes"



	// $ANTLR start "requete"
	// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:29:1: requete returns [Arbre req_arbre = new Arbre(\"\")] : ( ( SELECT )+ ) cols= columns ( MOTS mots= contenir )? ( ( ( RUBRIQUES rubs= rubriques ) ( MOTS mots= contenir )? ( DATES dats= dates ( MOTS mots= contenir )? )? ) | ( ( DATES dats= dates ) ( MOTS mots= contenir )? ( RUBRIQUES rubs= rubriques ( MOTS mots= contenir )? )? ) ) ;
	public final Arbre requete() throws RecognitionException {
		Arbre req_arbre =  new Arbre("");


		Arbre cols =null;
		Arbre mots =null;
		Arbre rubs =null;
		Arbre dats =null;

		Arbre ps_arbre;
		try {
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:29:75: ( ( ( SELECT )+ ) cols= columns ( MOTS mots= contenir )? ( ( ( RUBRIQUES rubs= rubriques ) ( MOTS mots= contenir )? ( DATES dats= dates ( MOTS mots= contenir )? )? ) | ( ( DATES dats= dates ) ( MOTS mots= contenir )? ( RUBRIQUES rubs= rubriques ( MOTS mots= contenir )? )? ) ) )
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:30:2: ( ( SELECT )+ ) cols= columns ( MOTS mots= contenir )? ( ( ( RUBRIQUES rubs= rubriques ) ( MOTS mots= contenir )? ( DATES dats= dates ( MOTS mots= contenir )? )? ) | ( ( DATES dats= dates ) ( MOTS mots= contenir )? ( RUBRIQUES rubs= rubriques ( MOTS mots= contenir )? )? ) )
			{
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:30:2: ( ( SELECT )+ )
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:30:3: ( SELECT )+
			{
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:30:3: ( SELECT )+
			int cnt1=0;
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( (LA1_0==SELECT) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:30:3: SELECT
					{
					match(input,SELECT,FOLLOW_SELECT_in_requete221); 
					}
					break;

				default :
					if ( cnt1 >= 1 ) break loop1;
					EarlyExitException eee = new EarlyExitException(1, input);
					throw eee;
				}
				cnt1++;
			}

			}


					req_arbre.ajouteFils(new Arbre("","select distinct"));
				
			pushFollow(FOLLOW_columns_in_requete233);
			cols=columns();
			state._fsp--;


					ps_arbre = cols;
					req_arbre.ajouteFils(ps_arbre);
					req_arbre.ajouteFils(new Arbre("","from titretext te, date d where d.fichier = te.fichier and "));
				
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:38:2: ( MOTS mots= contenir )?
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( (LA2_0==MOTS) ) {
				alt2=1;
			}
			switch (alt2) {
				case 1 :
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:38:3: MOTS mots= contenir
					{
					match(input,MOTS,FOLLOW_MOTS_in_requete239); 
					pushFollow(FOLLOW_contenir_in_requete245);
					mots=contenir();
					state._fsp--;


							req_arbre.ajouteFils(new Arbre("","!("));
							ps_arbre = mots;
							req_arbre.ajouteFils(ps_arbre);
							req_arbre.ajouteFils(new Arbre("","!)"));
						
					}
					break;

			}

			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:44:2: ( ( ( RUBRIQUES rubs= rubriques ) ( MOTS mots= contenir )? ( DATES dats= dates ( MOTS mots= contenir )? )? ) | ( ( DATES dats= dates ) ( MOTS mots= contenir )? ( RUBRIQUES rubs= rubriques ( MOTS mots= contenir )? )? ) )
			int alt9=2;
			int LA9_0 = input.LA(1);
			if ( (LA9_0==RUBRIQUES) ) {
				alt9=1;
			}
			else if ( (LA9_0==DATES) ) {
				alt9=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 9, 0, input);
				throw nvae;
			}

			switch (alt9) {
				case 1 :
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:45:2: ( ( RUBRIQUES rubs= rubriques ) ( MOTS mots= contenir )? ( DATES dats= dates ( MOTS mots= contenir )? )? )
					{
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:45:2: ( ( RUBRIQUES rubs= rubriques ) ( MOTS mots= contenir )? ( DATES dats= dates ( MOTS mots= contenir )? )? )
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:46:2: ( RUBRIQUES rubs= rubriques ) ( MOTS mots= contenir )? ( DATES dats= dates ( MOTS mots= contenir )? )?
					{
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:46:2: ( RUBRIQUES rubs= rubriques )
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:46:3: RUBRIQUES rubs= rubriques
					{
					match(input,RUBRIQUES,FOLLOW_RUBRIQUES_in_requete259); 
					pushFollow(FOLLOW_rubriques_in_requete265);
					rubs=rubriques();
					state._fsp--;


							req_arbre.ajouteFils(new Arbre("","and !("));
							ps_arbre = rubs;
							req_arbre.ajouteFils(ps_arbre);
							req_arbre.ajouteFils(new Arbre("","!)"));
						
					}

					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:52:2: ( MOTS mots= contenir )?
					int alt3=2;
					int LA3_0 = input.LA(1);
					if ( (LA3_0==MOTS) ) {
						alt3=1;
					}
					switch (alt3) {
						case 1 :
							// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:52:3: MOTS mots= contenir
							{
							match(input,MOTS,FOLLOW_MOTS_in_requete272); 
							pushFollow(FOLLOW_contenir_in_requete278);
							mots=contenir();
							state._fsp--;


									req_arbre.ajouteFils(new Arbre("","and !("));
									ps_arbre = mots;
									req_arbre.ajouteFils(ps_arbre);
									req_arbre.ajouteFils(new Arbre("","!)"));
								
							}
							break;

					}

					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:58:2: ( DATES dats= dates ( MOTS mots= contenir )? )?
					int alt5=2;
					int LA5_0 = input.LA(1);
					if ( (LA5_0==DATES) ) {
						alt5=1;
					}
					switch (alt5) {
						case 1 :
							// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:58:3: DATES dats= dates ( MOTS mots= contenir )?
							{
							match(input,DATES,FOLLOW_DATES_in_requete286); 
							pushFollow(FOLLOW_dates_in_requete292);
							dats=dates();
							state._fsp--;


									req_arbre.ajouteFils(new Arbre("","and !("));
									ps_arbre = dats;
									req_arbre.ajouteFils(ps_arbre);
									req_arbre.ajouteFils(new Arbre("","!)"));
								
							// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:64:2: ( MOTS mots= contenir )?
							int alt4=2;
							int LA4_0 = input.LA(1);
							if ( (LA4_0==MOTS) ) {
								alt4=1;
							}
							switch (alt4) {
								case 1 :
									// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:64:3: MOTS mots= contenir
									{
									match(input,MOTS,FOLLOW_MOTS_in_requete298); 
									pushFollow(FOLLOW_contenir_in_requete304);
									mots=contenir();
									state._fsp--;


											req_arbre.ajouteFils(new Arbre("","and !("));
											ps_arbre = mots;
											req_arbre.ajouteFils(ps_arbre);
											req_arbre.ajouteFils(new Arbre("","!)"));
										
									}
									break;

							}

							}
							break;

					}

					}

					}
					break;
				case 2 :
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:73:2: ( ( DATES dats= dates ) ( MOTS mots= contenir )? ( RUBRIQUES rubs= rubriques ( MOTS mots= contenir )? )? )
					{
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:73:2: ( ( DATES dats= dates ) ( MOTS mots= contenir )? ( RUBRIQUES rubs= rubriques ( MOTS mots= contenir )? )? )
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:74:2: ( DATES dats= dates ) ( MOTS mots= contenir )? ( RUBRIQUES rubs= rubriques ( MOTS mots= contenir )? )?
					{
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:74:2: ( DATES dats= dates )
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:74:3: DATES dats= dates
					{
					match(input,DATES,FOLLOW_DATES_in_requete325); 
					pushFollow(FOLLOW_dates_in_requete331);
					dats=dates();
					state._fsp--;


							req_arbre.ajouteFils(new Arbre("","and !("));
							ps_arbre = dats;
							req_arbre.ajouteFils(ps_arbre);
							req_arbre.ajouteFils(new Arbre("","!)"));
						
					}

					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:80:2: ( MOTS mots= contenir )?
					int alt6=2;
					int LA6_0 = input.LA(1);
					if ( (LA6_0==MOTS) ) {
						alt6=1;
					}
					switch (alt6) {
						case 1 :
							// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:80:3: MOTS mots= contenir
							{
							match(input,MOTS,FOLLOW_MOTS_in_requete338); 
							pushFollow(FOLLOW_contenir_in_requete344);
							mots=contenir();
							state._fsp--;


									req_arbre.ajouteFils(new Arbre("","and !("));
									ps_arbre = mots;
									req_arbre.ajouteFils(ps_arbre);
									req_arbre.ajouteFils(new Arbre("","!)"));
								
							}
							break;

					}

					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:86:2: ( RUBRIQUES rubs= rubriques ( MOTS mots= contenir )? )?
					int alt8=2;
					int LA8_0 = input.LA(1);
					if ( (LA8_0==RUBRIQUES) ) {
						alt8=1;
					}
					switch (alt8) {
						case 1 :
							// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:86:3: RUBRIQUES rubs= rubriques ( MOTS mots= contenir )?
							{
							match(input,RUBRIQUES,FOLLOW_RUBRIQUES_in_requete352); 
							pushFollow(FOLLOW_rubriques_in_requete358);
							rubs=rubriques();
							state._fsp--;


									req_arbre.ajouteFils(new Arbre("","and !("));
									ps_arbre = rubs;
									req_arbre.ajouteFils(ps_arbre);
									req_arbre.ajouteFils(new Arbre("","!)"));
								
							// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:92:2: ( MOTS mots= contenir )?
							int alt7=2;
							int LA7_0 = input.LA(1);
							if ( (LA7_0==MOTS) ) {
								alt7=1;
							}
							switch (alt7) {
								case 1 :
									// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:92:3: MOTS mots= contenir
									{
									match(input,MOTS,FOLLOW_MOTS_in_requete364); 
									pushFollow(FOLLOW_contenir_in_requete370);
									mots=contenir();
									state._fsp--;


											req_arbre.ajouteFils(new Arbre("","and !("));
											ps_arbre = mots;
											req_arbre.ajouteFils(ps_arbre);
											req_arbre.ajouteFils(new Arbre("","!)"));
										
									}
									break;

							}

							}
							break;

					}

					}

					}
					break;

			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return req_arbre;
	}
	// $ANTLR end "requete"



	// $ANTLR start "columns"
	// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:103:1: columns returns [Arbre columns_arbre = new Arbre(\"\")] : col1= column (c= ( ET ) col2= column )* ;
	public final Arbre columns() throws RecognitionException {
		Arbre columns_arbre =  new Arbre("");


		Token c=null;
		Arbre col1 =null;
		Arbre col2 =null;

		Arbre col1_arbre, col2_arbre;
		try {
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:103:93: (col1= column (c= ( ET ) col2= column )* )
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:104:2: col1= column (c= ( ET ) col2= column )*
			{
			pushFollow(FOLLOW_column_in_columns408);
			col1=column();
			state._fsp--;


					col1_arbre = col1;
					columns_arbre.ajouteFils(col1_arbre);
				
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:108:2: (c= ( ET ) col2= column )*
			loop10:
			while (true) {
				int alt10=2;
				int LA10_0 = input.LA(1);
				if ( (LA10_0==ET) ) {
					alt10=1;
				}

				switch (alt10) {
				case 1 :
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:108:3: c= ( ET ) col2= column
					{
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:108:7: ( ET )
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:108:8: ET
					{
					c=(Token)match(input,ET,FOLLOW_ET_in_columns419); 
					}


							columns_arbre.ajouteFils(new Arbre("", ","));
						
					pushFollow(FOLLOW_column_in_columns431);
					col2=column();
					state._fsp--;


							col2_arbre = col2;
							columns_arbre.ajouteFils(col2_arbre);
						
					}
					break;

				default :
					break loop10;
				}
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return columns_arbre;
	}
	// $ANTLR end "columns"



	// $ANTLR start "column"
	// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:118:1: column returns [Arbre column = new Arbre(\"\")] : c= ( VAR ) ;
	public final Arbre column() throws RecognitionException {
		Arbre column =  new Arbre("");


		Token c=null;

		try {
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:118:47: (c= ( VAR ) )
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:119:2: c= ( VAR )
			{
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:119:6: ( VAR )
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:119:7: VAR
			{
			c=(Token)match(input,VAR,FOLLOW_VAR_in_column455); 
			}


					String s = null;
					switch(c.getText()) {
					case "fichier":
						s = "te.fichier as fichier";
						break;
					case "rubrique":
						s = "te.rubrique as rubrique";
						break;
					case "numero":
						s = "te.numero as numero";
						break;
					default:
						s = c.getText();
					}
					column.ajouteFils(new Arbre("", s));
				
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return column;
	}
	// $ANTLR end "column"



	// $ANTLR start "rubriques"
	// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:138:1: rubriques returns [Arbre rubriques_arbre = new Arbre(\"\")] : rub1= rubrique (c= ( OU ) rub2= rubrique )* ;
	public final Arbre rubriques() throws RecognitionException {
		Arbre rubriques_arbre =  new Arbre("");


		Token c=null;
		Arbre rub1 =null;
		Arbre rub2 =null;

		Arbre rub1_arbre, rub2_arbre;
		try {
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:138:97: (rub1= rubrique (c= ( OU ) rub2= rubrique )* )
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:139:2: rub1= rubrique (c= ( OU ) rub2= rubrique )*
			{
			pushFollow(FOLLOW_rubrique_in_rubriques483);
			rub1=rubrique();
			state._fsp--;


					rub1_arbre = rub1;
					rubriques_arbre.ajouteFils(rub1_arbre);
				
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:143:2: (c= ( OU ) rub2= rubrique )*
			loop11:
			while (true) {
				int alt11=2;
				int LA11_0 = input.LA(1);
				if ( (LA11_0==OU) ) {
					alt11=1;
				}

				switch (alt11) {
				case 1 :
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:143:3: c= ( OU ) rub2= rubrique
					{
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:143:7: ( OU )
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:143:8: OU
					{
					c=(Token)match(input,OU,FOLLOW_OU_in_rubriques494); 
					}


							rubriques_arbre.ajouteFils(new Arbre("", "OR"));
						
					pushFollow(FOLLOW_rubrique_in_rubriques506);
					rub2=rubrique();
					state._fsp--;


							rub2_arbre = rub2;
							rubriques_arbre.ajouteFils(rub2_arbre);
						
					}
					break;

				default :
					break loop11;
				}
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return rubriques_arbre;
	}
	// $ANTLR end "rubriques"



	// $ANTLR start "rubrique"
	// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:153:1: rubrique returns [Arbre rubrique = new Arbre(\"\")] : c= VAR ;
	public final Arbre rubrique() throws RecognitionException {
		Arbre rubrique =  new Arbre("");


		Token c=null;

		try {
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:153:51: (c= VAR )
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:154:2: c= VAR
			{
			c=(Token)match(input,VAR,FOLLOW_VAR_in_rubrique529); 

					rubrique.ajouteFils(new Arbre("rubrique = ", "'"+c.getText()+"'"));
				
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return rubrique;
	}
	// $ANTLR end "rubrique"



	// $ANTLR start "dates"
	// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:159:1: dates returns [Arbre dates_arbre = new Arbre(\"\")] : dat1= date (c= ( OU ) ( DATES )? dat2= date )* ;
	public final Arbre dates() throws RecognitionException {
		Arbre dates_arbre =  new Arbre("");


		Token c=null;
		Arbre dat1 =null;
		Arbre dat2 =null;

		Arbre date1_arbre, date2_arbre;
		try {
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:159:91: (dat1= date (c= ( OU ) ( DATES )? dat2= date )* )
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:160:2: dat1= date (c= ( OU ) ( DATES )? dat2= date )*
			{
			pushFollow(FOLLOW_date_in_dates556);
			dat1=date();
			state._fsp--;


					date1_arbre = dat1;
					dates_arbre.ajouteFils(date1_arbre);
				
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:164:2: (c= ( OU ) ( DATES )? dat2= date )*
			loop13:
			while (true) {
				int alt13=2;
				int LA13_0 = input.LA(1);
				if ( (LA13_0==OU) ) {
					alt13=1;
				}

				switch (alt13) {
				case 1 :
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:164:3: c= ( OU ) ( DATES )? dat2= date
					{
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:164:7: ( OU )
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:164:8: OU
					{
					c=(Token)match(input,OU,FOLLOW_OU_in_dates567); 
					}


							dates_arbre.ajouteFils(new Arbre("", "OR"));
						
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:167:2: ( DATES )?
					int alt12=2;
					int LA12_0 = input.LA(1);
					if ( (LA12_0==DATES) ) {
						alt12=1;
					}
					switch (alt12) {
						case 1 :
							// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:167:2: DATES
							{
							match(input,DATES,FOLLOW_DATES_in_dates574); 
							}
							break;

					}

					pushFollow(FOLLOW_date_in_dates583);
					dat2=date();
					state._fsp--;


							date2_arbre = dat2;
							dates_arbre.ajouteFils(date2_arbre);
						
					}
					break;

				default :
					break loop13;
				}
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return dates_arbre;
	}
	// $ANTLR end "dates"



	// $ANTLR start "date"
	// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:174:1: date returns [Arbre date = new Arbre(\"\")] : ( ( (jour= JOUR ) (mois= MOIS )? (annee= ANNEE )? ) | ( (mois= MOIS ) (annee= ANNEE )? ) | (annee= ANNEE ) );
	public final Arbre date() throws RecognitionException {
		Arbre date =  new Arbre("");


		Token jour=null;
		Token mois=null;
		Token annee=null;

		try {
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:174:42: ( ( (jour= JOUR ) (mois= MOIS )? (annee= ANNEE )? ) | ( (mois= MOIS ) (annee= ANNEE )? ) | (annee= ANNEE ) )
			int alt17=3;
			switch ( input.LA(1) ) {
			case JOUR:
				{
				alt17=1;
				}
				break;
			case MOIS:
				{
				alt17=2;
				}
				break;
			case ANNEE:
				{
				alt17=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 17, 0, input);
				throw nvae;
			}
			switch (alt17) {
				case 1 :
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:175:20: ( (jour= JOUR ) (mois= MOIS )? (annee= ANNEE )? )
					{
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:175:20: ( (jour= JOUR ) (mois= MOIS )? (annee= ANNEE )? )
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:176:4: (jour= JOUR ) (mois= MOIS )? (annee= ANNEE )?
					{
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:176:4: (jour= JOUR )
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:177:6: jour= JOUR
					{
					jour=(Token)match(input,JOUR,FOLLOW_JOUR_in_date634); 

										date.ajouteFils(new Arbre("jour =", "'"+jour.getText()+"'"));
									
					}

					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:182:4: (mois= MOIS )?
					int alt14=2;
					int LA14_0 = input.LA(1);
					if ( (LA14_0==MOIS) ) {
						alt14=1;
					}
					switch (alt14) {
						case 1 :
							// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:183:6: mois= MOIS
							{
							mois=(Token)match(input,MOIS,FOLLOW_MOIS_in_date661); 

												date.ajouteFils(new Arbre("and mois =", "'"+mois.getText().replaceAll("^0(\\d{2})+","$1")+"'"));
											
							}
							break;

					}

					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:188:4: (annee= ANNEE )?
					int alt15=2;
					int LA15_0 = input.LA(1);
					if ( (LA15_0==ANNEE) ) {
						alt15=1;
					}
					switch (alt15) {
						case 1 :
							// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:189:6: annee= ANNEE
							{
							annee=(Token)match(input,ANNEE,FOLLOW_ANNEE_in_date689); 

												date.ajouteFils(new Arbre("and annee =", "'"+annee.getText()+"'"));
											
							}
							break;

					}

					}

					}
					break;
				case 2 :
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:196:21: ( (mois= MOIS ) (annee= ANNEE )? )
					{
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:196:21: ( (mois= MOIS ) (annee= ANNEE )? )
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:197:4: (mois= MOIS ) (annee= ANNEE )?
					{
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:197:4: (mois= MOIS )
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:198:6: mois= MOIS
					{
					mois=(Token)match(input,MOIS,FOLLOW_MOIS_in_date777); 

										date.ajouteFils(new Arbre("mois =", "'"+mois.getText().replaceAll("^0(\\d{2})+","$1")+"'"));
									
					}

					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:203:4: (annee= ANNEE )?
					int alt16=2;
					int LA16_0 = input.LA(1);
					if ( (LA16_0==ANNEE) ) {
						alt16=1;
					}
					switch (alt16) {
						case 1 :
							// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:204:6: annee= ANNEE
							{
							annee=(Token)match(input,ANNEE,FOLLOW_ANNEE_in_date804); 

												date.ajouteFils(new Arbre("and annee =", "'"+annee.getText()+"'"));
											
							}
							break;

					}

					}

					}
					break;
				case 3 :
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:211:4: (annee= ANNEE )
					{
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:211:4: (annee= ANNEE )
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:212:6: annee= ANNEE
					{
					annee=(Token)match(input,ANNEE,FOLLOW_ANNEE_in_date870); 

										date.ajouteFils(new Arbre("annee =", "'"+annee.getText()+"'"));
									
					}

					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return date;
	}
	// $ANTLR end "date"



	// $ANTLR start "contenir"
	// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:219:1: contenir returns [Arbre les_pars_arbre = new Arbre(\"\")] : par1= mot ( (c= ( OU ) par2= mot ) | (c= ( ET ) par3= VAR ) )* ;
	public final Arbre contenir() throws RecognitionException {
		Arbre les_pars_arbre =  new Arbre("");


		Token c=null;
		Token par3=null;
		Arbre par1 =null;
		Arbre par2 =null;

		Arbre par1_arbre, par2_arbre;
		try {
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:220:40: (par1= mot ( (c= ( OU ) par2= mot ) | (c= ( ET ) par3= VAR ) )* )
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:221:3: par1= mot ( (c= ( OU ) par2= mot ) | (c= ( ET ) par3= VAR ) )*
			{
			pushFollow(FOLLOW_mot_in_contenir907);
			par1=mot();
			state._fsp--;


							par1_arbre = par1;
							les_pars_arbre.ajouteFils(par1_arbre);
						
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:226:3: ( (c= ( OU ) par2= mot ) | (c= ( ET ) par3= VAR ) )*
			loop18:
			while (true) {
				int alt18=3;
				int LA18_0 = input.LA(1);
				if ( (LA18_0==OU) ) {
					alt18=1;
				}
				else if ( (LA18_0==ET) ) {
					alt18=2;
				}

				switch (alt18) {
				case 1 :
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:226:4: (c= ( OU ) par2= mot )
					{
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:226:4: (c= ( OU ) par2= mot )
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:226:5: c= ( OU ) par2= mot
					{
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:226:9: ( OU )
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:226:10: OU
					{
					c=(Token)match(input,OU,FOLLOW_OU_in_contenir924); 
					}


									les_pars_arbre.ajouteFils(new Arbre("", "OR"));
								
					pushFollow(FOLLOW_mot_in_contenir940);
					par2=mot();
					state._fsp--;


									par2_arbre = par2;
									les_pars_arbre.ajouteFils(par2_arbre);
								
					}

					}
					break;
				case 2 :
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:237:3: (c= ( ET ) par3= VAR )
					{
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:237:3: (c= ( ET ) par3= VAR )
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:237:4: c= ( ET ) par3= VAR
					{
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:237:8: ( ET )
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:237:9: ET
					{
					c=(Token)match(input,ET,FOLLOW_ET_in_contenir963); 
					}


									les_pars_arbre.ajouteFils(new Arbre("", "!(!( INTERSECT "));
								
					par3=(Token)match(input,VAR,FOLLOW_VAR_in_contenir979); 

									les_pars_arbre.ajouteFils(new Arbre("", "'"+par3.getText()+"'"));
									les_pars_arbre.ajouteFils(new Arbre("", " !)!)"));
								
					}

					}
					break;

				default :
					break loop18;
				}
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return les_pars_arbre;
	}
	// $ANTLR end "contenir"



	// $ANTLR start "mot"
	// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:251:1: mot returns [Arbre lepar_arbre = new Arbre(\"\")] : (v= VAR ) ;
	public final Arbre mot() throws RecognitionException {
		Arbre lepar_arbre =  new Arbre("");


		Token v=null;

		try {
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:251:49: ( (v= VAR ) )
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:252:3: (v= VAR )
			{
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:252:3: (v= VAR )
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:252:4: v= VAR
			{
			v=(Token)match(input,VAR,FOLLOW_VAR_in_mot1014); 

						lepar_arbre.ajouteFils(new Arbre("mot =", "'"+v.getText()+"'"));
					
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return lepar_arbre;
	}
	// $ANTLR end "mot"

	// Delegated rules



	public static final BitSet FOLLOW_requete_in_listerequetes198 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SELECT_in_requete221 = new BitSet(new long[]{0x0000000000003000L});
	public static final BitSet FOLLOW_columns_in_requete233 = new BitSet(new long[]{0x0000000000000A20L});
	public static final BitSet FOLLOW_MOTS_in_requete239 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_contenir_in_requete245 = new BitSet(new long[]{0x0000000000000820L});
	public static final BitSet FOLLOW_RUBRIQUES_in_requete259 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_rubriques_in_requete265 = new BitSet(new long[]{0x0000000000000222L});
	public static final BitSet FOLLOW_MOTS_in_requete272 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_contenir_in_requete278 = new BitSet(new long[]{0x0000000000000022L});
	public static final BitSet FOLLOW_DATES_in_requete286 = new BitSet(new long[]{0x0000000000000190L});
	public static final BitSet FOLLOW_dates_in_requete292 = new BitSet(new long[]{0x0000000000000202L});
	public static final BitSet FOLLOW_MOTS_in_requete298 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_contenir_in_requete304 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DATES_in_requete325 = new BitSet(new long[]{0x0000000000000190L});
	public static final BitSet FOLLOW_dates_in_requete331 = new BitSet(new long[]{0x0000000000000A02L});
	public static final BitSet FOLLOW_MOTS_in_requete338 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_contenir_in_requete344 = new BitSet(new long[]{0x0000000000000802L});
	public static final BitSet FOLLOW_RUBRIQUES_in_requete352 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_rubriques_in_requete358 = new BitSet(new long[]{0x0000000000000202L});
	public static final BitSet FOLLOW_MOTS_in_requete364 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_contenir_in_requete370 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_column_in_columns408 = new BitSet(new long[]{0x0000000000000042L});
	public static final BitSet FOLLOW_ET_in_columns419 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_column_in_columns431 = new BitSet(new long[]{0x0000000000000042L});
	public static final BitSet FOLLOW_VAR_in_column455 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_rubrique_in_rubriques483 = new BitSet(new long[]{0x0000000000000402L});
	public static final BitSet FOLLOW_OU_in_rubriques494 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_rubrique_in_rubriques506 = new BitSet(new long[]{0x0000000000000402L});
	public static final BitSet FOLLOW_VAR_in_rubrique529 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_date_in_dates556 = new BitSet(new long[]{0x0000000000000402L});
	public static final BitSet FOLLOW_OU_in_dates567 = new BitSet(new long[]{0x00000000000001B0L});
	public static final BitSet FOLLOW_DATES_in_dates574 = new BitSet(new long[]{0x0000000000000190L});
	public static final BitSet FOLLOW_date_in_dates583 = new BitSet(new long[]{0x0000000000000402L});
	public static final BitSet FOLLOW_JOUR_in_date634 = new BitSet(new long[]{0x0000000000000112L});
	public static final BitSet FOLLOW_MOIS_in_date661 = new BitSet(new long[]{0x0000000000000012L});
	public static final BitSet FOLLOW_ANNEE_in_date689 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_MOIS_in_date777 = new BitSet(new long[]{0x0000000000000012L});
	public static final BitSet FOLLOW_ANNEE_in_date804 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ANNEE_in_date870 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_mot_in_contenir907 = new BitSet(new long[]{0x0000000000000442L});
	public static final BitSet FOLLOW_OU_in_contenir924 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_mot_in_contenir940 = new BitSet(new long[]{0x0000000000000442L});
	public static final BitSet FOLLOW_ET_in_contenir963 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_VAR_in_contenir979 = new BitSet(new long[]{0x0000000000000442L});
	public static final BitSet FOLLOW_VAR_in_mot1014 = new BitSet(new long[]{0x0000000000000002L});
}
