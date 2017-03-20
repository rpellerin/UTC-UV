// $ANTLR 3.5.2 /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g 2016-06-13 14:06:38

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class Tal_OURSLexer extends Lexer {
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
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public Tal_OURSLexer() {} 
	public Tal_OURSLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public Tal_OURSLexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "/home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g"; }

	// $ANTLR start "SELECT"
	public final void mSELECT() throws RecognitionException {
		try {
			int _type = SELECT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:3:7: ( 'vouloir' )
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:3:8: 'vouloir'
			{
			match("vouloir"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SELECT"

	// $ANTLR start "ET"
	public final void mET() throws RecognitionException {
		try {
			int _type = ET;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:5:3: ( 'et' )
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:5:4: 'et'
			{
			match("et"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ET"

	// $ANTLR start "OU"
	public final void mOU() throws RecognitionException {
		try {
			int _type = OU;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:6:3: ( 'ou' )
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:6:4: 'ou'
			{
			match("ou"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "OU"

	// $ANTLR start "MOTS"
	public final void mMOTS() throws RecognitionException {
		try {
			int _type = MOTS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:8:5: ( 'contenir' )
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:8:6: 'contenir'
			{
			match("contenir"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MOTS"

	// $ANTLR start "RUBRIQUES"
	public final void mRUBRIQUES() throws RecognitionException {
		try {
			int _type = RUBRIQUES;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:10:10: ( 'rubrique' )
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:10:11: 'rubrique'
			{
			match("rubrique"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RUBRIQUES"

	// $ANTLR start "DATES"
	public final void mDATES() throws RecognitionException {
		try {
			int _type = DATES;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:12:6: ( 'date' )
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:12:7: 'date'
			{
			match("date"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DATES"

	// $ANTLR start "MOIS"
	public final void mMOIS() throws RecognitionException {
		try {
			int _type = MOIS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:13:5: ( '0' ( ( '0' ( '1' .. '9' ) ) | '10' | '11' | '12' ) )
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:13:6: '0' ( ( '0' ( '1' .. '9' ) ) | '10' | '11' | '12' )
			{
			match('0'); 
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:13:9: ( ( '0' ( '1' .. '9' ) ) | '10' | '11' | '12' )
			int alt1=4;
			int LA1_0 = input.LA(1);
			if ( (LA1_0=='0') ) {
				alt1=1;
			}
			else if ( (LA1_0=='1') ) {
				switch ( input.LA(2) ) {
				case '0':
					{
					alt1=2;
					}
					break;
				case '1':
					{
					alt1=3;
					}
					break;
				case '2':
					{
					alt1=4;
					}
					break;
				default:
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 1, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 1, 0, input);
				throw nvae;
			}

			switch (alt1) {
				case 1 :
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:13:10: ( '0' ( '1' .. '9' ) )
					{
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:13:10: ( '0' ( '1' .. '9' ) )
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:13:11: '0' ( '1' .. '9' )
					{
					match('0'); 
					if ( (input.LA(1) >= '1' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}

					}
					break;
				case 2 :
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:13:26: '10'
					{
					match("10"); 

					}
					break;
				case 3 :
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:13:31: '11'
					{
					match("11"); 

					}
					break;
				case 4 :
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:13:36: '12'
					{
					match("12"); 

					}
					break;

			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MOIS"

	// $ANTLR start "ANNEE"
	public final void mANNEE() throws RecognitionException {
		try {
			int _type = ANNEE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:14:6: ( ( '1' | '2' ) '0' .. '9' '0' .. '9' '0' .. '9' )
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:14:7: ( '1' | '2' ) '0' .. '9' '0' .. '9' '0' .. '9'
			{
			if ( (input.LA(1) >= '1' && input.LA(1) <= '2') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			matchRange('0','9'); 
			matchRange('0','9'); 
			matchRange('0','9'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ANNEE"

	// $ANTLR start "JOUR"
	public final void mJOUR() throws RecognitionException {
		try {
			int _type = JOUR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:15:5: ( ( '0' .. '9' ) | ( ( '1' | '2' ) '0' .. '9' ) | '30' | '31' )
			int alt2=4;
			switch ( input.LA(1) ) {
			case '1':
			case '2':
				{
				int LA2_1 = input.LA(2);
				if ( ((LA2_1 >= '0' && LA2_1 <= '9')) ) {
					alt2=2;
				}

				else {
					alt2=1;
				}

				}
				break;
			case '3':
				{
				switch ( input.LA(2) ) {
				case '0':
					{
					alt2=3;
					}
					break;
				case '1':
					{
					alt2=4;
					}
					break;
				default:
					alt2=1;
				}
				}
				break;
			case '0':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
				{
				alt2=1;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 2, 0, input);
				throw nvae;
			}
			switch (alt2) {
				case 1 :
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:15:6: ( '0' .. '9' )
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;
				case 2 :
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:15:17: ( ( '1' | '2' ) '0' .. '9' )
					{
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:15:17: ( ( '1' | '2' ) '0' .. '9' )
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:15:18: ( '1' | '2' ) '0' .. '9'
					{
					if ( (input.LA(1) >= '1' && input.LA(1) <= '2') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					matchRange('0','9'); 
					}

					}
					break;
				case 3 :
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:15:37: '30'
					{
					match("30"); 

					}
					break;
				case 4 :
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:15:42: '31'
					{
					match("31"); 

					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "JOUR"

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:17:3: ( ( ' ' | '\\t' | '\\r' | 'je' | 'qui' | 'dont' ) | '\\n' )
			int alt4=2;
			int LA4_0 = input.LA(1);
			if ( (LA4_0=='\t'||LA4_0=='\r'||LA4_0==' '||LA4_0=='d'||LA4_0=='j'||LA4_0=='q') ) {
				alt4=1;
			}
			else if ( (LA4_0=='\n') ) {
				alt4=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 4, 0, input);
				throw nvae;
			}

			switch (alt4) {
				case 1 :
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:17:4: ( ' ' | '\\t' | '\\r' | 'je' | 'qui' | 'dont' )
					{
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:17:4: ( ' ' | '\\t' | '\\r' | 'je' | 'qui' | 'dont' )
					int alt3=6;
					switch ( input.LA(1) ) {
					case ' ':
						{
						alt3=1;
						}
						break;
					case '\t':
						{
						alt3=2;
						}
						break;
					case '\r':
						{
						alt3=3;
						}
						break;
					case 'j':
						{
						alt3=4;
						}
						break;
					case 'q':
						{
						alt3=5;
						}
						break;
					case 'd':
						{
						alt3=6;
						}
						break;
					default:
						NoViableAltException nvae =
							new NoViableAltException("", 3, 0, input);
						throw nvae;
					}
					switch (alt3) {
						case 1 :
							// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:17:5: ' '
							{
							match(' '); 
							}
							break;
						case 2 :
							// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:17:10: '\\t'
							{
							match('\t'); 
							}
							break;
						case 3 :
							// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:17:17: '\\r'
							{
							match('\r'); 
							}
							break;
						case 4 :
							// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:17:24: 'je'
							{
							match("je"); 

							}
							break;
						case 5 :
							// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:17:31: 'qui'
							{
							match("qui"); 

							}
							break;
						case 6 :
							// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:17:39: 'dont'
							{
							match("dont"); 

							}
							break;

					}

					skip();
					}
					break;
				case 2 :
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:17:59: '\\n'
					{
					match('\n'); 
					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WS"

	// $ANTLR start "VAR"
	public final void mVAR() throws RecognitionException {
		try {
			int _type = VAR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:19:4: ( ( 'A' .. 'Z' | 'a' .. 'z' | '\\u00a0' .. '\\u00ff' ) ( ( 'a' .. 'z' ) | ( '0' .. '9' ) | '-' | ( '\\u00a0' .. '\\u00ff' ) )+ )
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:19:5: ( 'A' .. 'Z' | 'a' .. 'z' | '\\u00a0' .. '\\u00ff' ) ( ( 'a' .. 'z' ) | ( '0' .. '9' ) | '-' | ( '\\u00a0' .. '\\u00ff' ) )+
			{
			if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z')||(input.LA(1) >= '\u00A0' && input.LA(1) <= '\u00FF') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:19:45: ( ( 'a' .. 'z' ) | ( '0' .. '9' ) | '-' | ( '\\u00a0' .. '\\u00ff' ) )+
			int cnt5=0;
			loop5:
			while (true) {
				int alt5=2;
				int LA5_0 = input.LA(1);
				if ( (LA5_0=='-'||(LA5_0 >= '0' && LA5_0 <= '9')||(LA5_0 >= 'a' && LA5_0 <= 'z')||(LA5_0 >= '\u00A0' && LA5_0 <= '\u00FF')) ) {
					alt5=1;
				}

				switch (alt5) {
				case 1 :
					// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:
					{
					if ( input.LA(1)=='-'||(input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'a' && input.LA(1) <= 'z')||(input.LA(1) >= '\u00A0' && input.LA(1) <= '\u00FF') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt5 >= 1 ) break loop5;
					EarlyExitException eee = new EarlyExitException(5, input);
					throw eee;
				}
				cnt5++;
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "VAR"

	@Override
	public void mTokens() throws RecognitionException {
		// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:1:8: ( SELECT | ET | OU | MOTS | RUBRIQUES | DATES | MOIS | ANNEE | JOUR | WS | VAR )
		int alt6=11;
		switch ( input.LA(1) ) {
		case 'v':
			{
			int LA6_1 = input.LA(2);
			if ( (LA6_1=='o') ) {
				int LA6_14 = input.LA(3);
				if ( (LA6_14=='u') ) {
					int LA6_25 = input.LA(4);
					if ( (LA6_25=='l') ) {
						int LA6_34 = input.LA(5);
						if ( (LA6_34=='o') ) {
							int LA6_39 = input.LA(6);
							if ( (LA6_39=='i') ) {
								int LA6_43 = input.LA(7);
								if ( (LA6_43=='r') ) {
									int LA6_46 = input.LA(8);
									if ( (LA6_46=='-'||(LA6_46 >= '0' && LA6_46 <= '9')||(LA6_46 >= 'a' && LA6_46 <= 'z')||(LA6_46 >= '\u00A0' && LA6_46 <= '\u00FF')) ) {
										alt6=11;
									}

									else {
										alt6=1;
									}

								}

								else {
									alt6=11;
								}

							}

							else {
								alt6=11;
							}

						}

						else {
							alt6=11;
						}

					}

					else {
						alt6=11;
					}

				}

				else {
					alt6=11;
				}

			}
			else if ( (LA6_1=='-'||(LA6_1 >= '0' && LA6_1 <= '9')||(LA6_1 >= 'a' && LA6_1 <= 'n')||(LA6_1 >= 'p' && LA6_1 <= 'z')||(LA6_1 >= '\u00A0' && LA6_1 <= '\u00FF')) ) {
				alt6=11;
			}

			else {
				int nvaeMark = input.mark();
				try {
					input.consume();
					NoViableAltException nvae =
						new NoViableAltException("", 6, 1, input);
					throw nvae;
				} finally {
					input.rewind(nvaeMark);
				}
			}

			}
			break;
		case 'e':
			{
			int LA6_2 = input.LA(2);
			if ( (LA6_2=='t') ) {
				int LA6_15 = input.LA(3);
				if ( (LA6_15=='-'||(LA6_15 >= '0' && LA6_15 <= '9')||(LA6_15 >= 'a' && LA6_15 <= 'z')||(LA6_15 >= '\u00A0' && LA6_15 <= '\u00FF')) ) {
					alt6=11;
				}

				else {
					alt6=2;
				}

			}
			else if ( (LA6_2=='-'||(LA6_2 >= '0' && LA6_2 <= '9')||(LA6_2 >= 'a' && LA6_2 <= 's')||(LA6_2 >= 'u' && LA6_2 <= 'z')||(LA6_2 >= '\u00A0' && LA6_2 <= '\u00FF')) ) {
				alt6=11;
			}

			else {
				int nvaeMark = input.mark();
				try {
					input.consume();
					NoViableAltException nvae =
						new NoViableAltException("", 6, 2, input);
					throw nvae;
				} finally {
					input.rewind(nvaeMark);
				}
			}

			}
			break;
		case 'o':
			{
			int LA6_3 = input.LA(2);
			if ( (LA6_3=='u') ) {
				int LA6_16 = input.LA(3);
				if ( (LA6_16=='-'||(LA6_16 >= '0' && LA6_16 <= '9')||(LA6_16 >= 'a' && LA6_16 <= 'z')||(LA6_16 >= '\u00A0' && LA6_16 <= '\u00FF')) ) {
					alt6=11;
				}

				else {
					alt6=3;
				}

			}
			else if ( (LA6_3=='-'||(LA6_3 >= '0' && LA6_3 <= '9')||(LA6_3 >= 'a' && LA6_3 <= 't')||(LA6_3 >= 'v' && LA6_3 <= 'z')||(LA6_3 >= '\u00A0' && LA6_3 <= '\u00FF')) ) {
				alt6=11;
			}

			else {
				int nvaeMark = input.mark();
				try {
					input.consume();
					NoViableAltException nvae =
						new NoViableAltException("", 6, 3, input);
					throw nvae;
				} finally {
					input.rewind(nvaeMark);
				}
			}

			}
			break;
		case 'c':
			{
			int LA6_4 = input.LA(2);
			if ( (LA6_4=='o') ) {
				int LA6_17 = input.LA(3);
				if ( (LA6_17=='n') ) {
					int LA6_28 = input.LA(4);
					if ( (LA6_28=='t') ) {
						int LA6_35 = input.LA(5);
						if ( (LA6_35=='e') ) {
							int LA6_40 = input.LA(6);
							if ( (LA6_40=='n') ) {
								int LA6_44 = input.LA(7);
								if ( (LA6_44=='i') ) {
									int LA6_47 = input.LA(8);
									if ( (LA6_47=='r') ) {
										int LA6_50 = input.LA(9);
										if ( (LA6_50=='-'||(LA6_50 >= '0' && LA6_50 <= '9')||(LA6_50 >= 'a' && LA6_50 <= 'z')||(LA6_50 >= '\u00A0' && LA6_50 <= '\u00FF')) ) {
											alt6=11;
										}

										else {
											alt6=4;
										}

									}

									else {
										alt6=11;
									}

								}

								else {
									alt6=11;
								}

							}

							else {
								alt6=11;
							}

						}

						else {
							alt6=11;
						}

					}

					else {
						alt6=11;
					}

				}

				else {
					alt6=11;
				}

			}
			else if ( (LA6_4=='-'||(LA6_4 >= '0' && LA6_4 <= '9')||(LA6_4 >= 'a' && LA6_4 <= 'n')||(LA6_4 >= 'p' && LA6_4 <= 'z')||(LA6_4 >= '\u00A0' && LA6_4 <= '\u00FF')) ) {
				alt6=11;
			}

			else {
				int nvaeMark = input.mark();
				try {
					input.consume();
					NoViableAltException nvae =
						new NoViableAltException("", 6, 4, input);
					throw nvae;
				} finally {
					input.rewind(nvaeMark);
				}
			}

			}
			break;
		case 'r':
			{
			int LA6_5 = input.LA(2);
			if ( (LA6_5=='u') ) {
				int LA6_18 = input.LA(3);
				if ( (LA6_18=='b') ) {
					int LA6_29 = input.LA(4);
					if ( (LA6_29=='r') ) {
						int LA6_36 = input.LA(5);
						if ( (LA6_36=='i') ) {
							int LA6_41 = input.LA(6);
							if ( (LA6_41=='q') ) {
								int LA6_45 = input.LA(7);
								if ( (LA6_45=='u') ) {
									int LA6_48 = input.LA(8);
									if ( (LA6_48=='e') ) {
										int LA6_51 = input.LA(9);
										if ( (LA6_51=='-'||(LA6_51 >= '0' && LA6_51 <= '9')||(LA6_51 >= 'a' && LA6_51 <= 'z')||(LA6_51 >= '\u00A0' && LA6_51 <= '\u00FF')) ) {
											alt6=11;
										}

										else {
											alt6=5;
										}

									}

									else {
										alt6=11;
									}

								}

								else {
									alt6=11;
								}

							}

							else {
								alt6=11;
							}

						}

						else {
							alt6=11;
						}

					}

					else {
						alt6=11;
					}

				}

				else {
					alt6=11;
				}

			}
			else if ( (LA6_5=='-'||(LA6_5 >= '0' && LA6_5 <= '9')||(LA6_5 >= 'a' && LA6_5 <= 't')||(LA6_5 >= 'v' && LA6_5 <= 'z')||(LA6_5 >= '\u00A0' && LA6_5 <= '\u00FF')) ) {
				alt6=11;
			}

			else {
				int nvaeMark = input.mark();
				try {
					input.consume();
					NoViableAltException nvae =
						new NoViableAltException("", 6, 5, input);
					throw nvae;
				} finally {
					input.rewind(nvaeMark);
				}
			}

			}
			break;
		case 'd':
			{
			switch ( input.LA(2) ) {
			case 'a':
				{
				int LA6_19 = input.LA(3);
				if ( (LA6_19=='t') ) {
					int LA6_30 = input.LA(4);
					if ( (LA6_30=='e') ) {
						int LA6_37 = input.LA(5);
						if ( (LA6_37=='-'||(LA6_37 >= '0' && LA6_37 <= '9')||(LA6_37 >= 'a' && LA6_37 <= 'z')||(LA6_37 >= '\u00A0' && LA6_37 <= '\u00FF')) ) {
							alt6=11;
						}

						else {
							alt6=6;
						}

					}

					else {
						alt6=11;
					}

				}

				else {
					alt6=11;
				}

				}
				break;
			case 'o':
				{
				int LA6_20 = input.LA(3);
				if ( (LA6_20=='n') ) {
					int LA6_31 = input.LA(4);
					if ( (LA6_31=='t') ) {
						int LA6_38 = input.LA(5);
						if ( (LA6_38=='-'||(LA6_38 >= '0' && LA6_38 <= '9')||(LA6_38 >= 'a' && LA6_38 <= 'z')||(LA6_38 >= '\u00A0' && LA6_38 <= '\u00FF')) ) {
							alt6=11;
						}

						else {
							alt6=10;
						}

					}

					else {
						alt6=11;
					}

				}

				else {
					alt6=11;
				}

				}
				break;
			case '-':
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
			case 'b':
			case 'c':
			case 'd':
			case 'e':
			case 'f':
			case 'g':
			case 'h':
			case 'i':
			case 'j':
			case 'k':
			case 'l':
			case 'm':
			case 'n':
			case 'p':
			case 'q':
			case 'r':
			case 's':
			case 't':
			case 'u':
			case 'v':
			case 'w':
			case 'x':
			case 'y':
			case 'z':
			case '\u00A0':
			case '\u00A1':
			case '\u00A2':
			case '\u00A3':
			case '\u00A4':
			case '\u00A5':
			case '\u00A6':
			case '\u00A7':
			case '\u00A8':
			case '\u00A9':
			case '\u00AA':
			case '\u00AB':
			case '\u00AC':
			case '\u00AD':
			case '\u00AE':
			case '\u00AF':
			case '\u00B0':
			case '\u00B1':
			case '\u00B2':
			case '\u00B3':
			case '\u00B4':
			case '\u00B5':
			case '\u00B6':
			case '\u00B7':
			case '\u00B8':
			case '\u00B9':
			case '\u00BA':
			case '\u00BB':
			case '\u00BC':
			case '\u00BD':
			case '\u00BE':
			case '\u00BF':
			case '\u00C0':
			case '\u00C1':
			case '\u00C2':
			case '\u00C3':
			case '\u00C4':
			case '\u00C5':
			case '\u00C6':
			case '\u00C7':
			case '\u00C8':
			case '\u00C9':
			case '\u00CA':
			case '\u00CB':
			case '\u00CC':
			case '\u00CD':
			case '\u00CE':
			case '\u00CF':
			case '\u00D0':
			case '\u00D1':
			case '\u00D2':
			case '\u00D3':
			case '\u00D4':
			case '\u00D5':
			case '\u00D6':
			case '\u00D7':
			case '\u00D8':
			case '\u00D9':
			case '\u00DA':
			case '\u00DB':
			case '\u00DC':
			case '\u00DD':
			case '\u00DE':
			case '\u00DF':
			case '\u00E0':
			case '\u00E1':
			case '\u00E2':
			case '\u00E3':
			case '\u00E4':
			case '\u00E5':
			case '\u00E6':
			case '\u00E7':
			case '\u00E8':
			case '\u00E9':
			case '\u00EA':
			case '\u00EB':
			case '\u00EC':
			case '\u00ED':
			case '\u00EE':
			case '\u00EF':
			case '\u00F0':
			case '\u00F1':
			case '\u00F2':
			case '\u00F3':
			case '\u00F4':
			case '\u00F5':
			case '\u00F6':
			case '\u00F7':
			case '\u00F8':
			case '\u00F9':
			case '\u00FA':
			case '\u00FB':
			case '\u00FC':
			case '\u00FD':
			case '\u00FE':
			case '\u00FF':
				{
				alt6=11;
				}
				break;
			default:
				int nvaeMark = input.mark();
				try {
					input.consume();
					NoViableAltException nvae =
						new NoViableAltException("", 6, 6, input);
					throw nvae;
				} finally {
					input.rewind(nvaeMark);
				}
			}
			}
			break;
		case '0':
			{
			int LA6_7 = input.LA(2);
			if ( ((LA6_7 >= '0' && LA6_7 <= '1')) ) {
				alt6=7;
			}

			else {
				alt6=9;
			}

			}
			break;
		case '1':
		case '2':
			{
			int LA6_8 = input.LA(2);
			if ( ((LA6_8 >= '0' && LA6_8 <= '9')) ) {
				int LA6_22 = input.LA(3);
				if ( ((LA6_22 >= '0' && LA6_22 <= '9')) ) {
					alt6=8;
				}

				else {
					alt6=9;
				}

			}

			else {
				alt6=9;
			}

			}
			break;
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
		case '8':
		case '9':
			{
			alt6=9;
			}
			break;
		case '\t':
		case '\n':
		case '\r':
		case ' ':
			{
			alt6=10;
			}
			break;
		case 'j':
			{
			int LA6_11 = input.LA(2);
			if ( (LA6_11=='e') ) {
				int LA6_23 = input.LA(3);
				if ( (LA6_23=='-'||(LA6_23 >= '0' && LA6_23 <= '9')||(LA6_23 >= 'a' && LA6_23 <= 'z')||(LA6_23 >= '\u00A0' && LA6_23 <= '\u00FF')) ) {
					alt6=11;
				}

				else {
					alt6=10;
				}

			}
			else if ( (LA6_11=='-'||(LA6_11 >= '0' && LA6_11 <= '9')||(LA6_11 >= 'a' && LA6_11 <= 'd')||(LA6_11 >= 'f' && LA6_11 <= 'z')||(LA6_11 >= '\u00A0' && LA6_11 <= '\u00FF')) ) {
				alt6=11;
			}

			else {
				int nvaeMark = input.mark();
				try {
					input.consume();
					NoViableAltException nvae =
						new NoViableAltException("", 6, 11, input);
					throw nvae;
				} finally {
					input.rewind(nvaeMark);
				}
			}

			}
			break;
		case 'q':
			{
			int LA6_12 = input.LA(2);
			if ( (LA6_12=='u') ) {
				int LA6_24 = input.LA(3);
				if ( (LA6_24=='i') ) {
					int LA6_33 = input.LA(4);
					if ( (LA6_33=='-'||(LA6_33 >= '0' && LA6_33 <= '9')||(LA6_33 >= 'a' && LA6_33 <= 'z')||(LA6_33 >= '\u00A0' && LA6_33 <= '\u00FF')) ) {
						alt6=11;
					}

					else {
						alt6=10;
					}

				}

				else {
					alt6=11;
				}

			}
			else if ( (LA6_12=='-'||(LA6_12 >= '0' && LA6_12 <= '9')||(LA6_12 >= 'a' && LA6_12 <= 't')||(LA6_12 >= 'v' && LA6_12 <= 'z')||(LA6_12 >= '\u00A0' && LA6_12 <= '\u00FF')) ) {
				alt6=11;
			}

			else {
				int nvaeMark = input.mark();
				try {
					input.consume();
					NoViableAltException nvae =
						new NoViableAltException("", 6, 12, input);
					throw nvae;
				} finally {
					input.rewind(nvaeMark);
				}
			}

			}
			break;
		case 'A':
		case 'B':
		case 'C':
		case 'D':
		case 'E':
		case 'F':
		case 'G':
		case 'H':
		case 'I':
		case 'J':
		case 'K':
		case 'L':
		case 'M':
		case 'N':
		case 'O':
		case 'P':
		case 'Q':
		case 'R':
		case 'S':
		case 'T':
		case 'U':
		case 'V':
		case 'W':
		case 'X':
		case 'Y':
		case 'Z':
		case 'a':
		case 'b':
		case 'f':
		case 'g':
		case 'h':
		case 'i':
		case 'k':
		case 'l':
		case 'm':
		case 'n':
		case 'p':
		case 's':
		case 't':
		case 'u':
		case 'w':
		case 'x':
		case 'y':
		case 'z':
		case '\u00A0':
		case '\u00A1':
		case '\u00A2':
		case '\u00A3':
		case '\u00A4':
		case '\u00A5':
		case '\u00A6':
		case '\u00A7':
		case '\u00A8':
		case '\u00A9':
		case '\u00AA':
		case '\u00AB':
		case '\u00AC':
		case '\u00AD':
		case '\u00AE':
		case '\u00AF':
		case '\u00B0':
		case '\u00B1':
		case '\u00B2':
		case '\u00B3':
		case '\u00B4':
		case '\u00B5':
		case '\u00B6':
		case '\u00B7':
		case '\u00B8':
		case '\u00B9':
		case '\u00BA':
		case '\u00BB':
		case '\u00BC':
		case '\u00BD':
		case '\u00BE':
		case '\u00BF':
		case '\u00C0':
		case '\u00C1':
		case '\u00C2':
		case '\u00C3':
		case '\u00C4':
		case '\u00C5':
		case '\u00C6':
		case '\u00C7':
		case '\u00C8':
		case '\u00C9':
		case '\u00CA':
		case '\u00CB':
		case '\u00CC':
		case '\u00CD':
		case '\u00CE':
		case '\u00CF':
		case '\u00D0':
		case '\u00D1':
		case '\u00D2':
		case '\u00D3':
		case '\u00D4':
		case '\u00D5':
		case '\u00D6':
		case '\u00D7':
		case '\u00D8':
		case '\u00D9':
		case '\u00DA':
		case '\u00DB':
		case '\u00DC':
		case '\u00DD':
		case '\u00DE':
		case '\u00DF':
		case '\u00E0':
		case '\u00E1':
		case '\u00E2':
		case '\u00E3':
		case '\u00E4':
		case '\u00E5':
		case '\u00E6':
		case '\u00E7':
		case '\u00E8':
		case '\u00E9':
		case '\u00EA':
		case '\u00EB':
		case '\u00EC':
		case '\u00ED':
		case '\u00EE':
		case '\u00EF':
		case '\u00F0':
		case '\u00F1':
		case '\u00F2':
		case '\u00F3':
		case '\u00F4':
		case '\u00F5':
		case '\u00F6':
		case '\u00F7':
		case '\u00F8':
		case '\u00F9':
		case '\u00FA':
		case '\u00FB':
		case '\u00FC':
		case '\u00FD':
		case '\u00FE':
		case '\u00FF':
			{
			alt6=11;
			}
			break;
		default:
			NoViableAltException nvae =
				new NoViableAltException("", 6, 0, input);
			throw nvae;
		}
		switch (alt6) {
			case 1 :
				// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:1:10: SELECT
				{
				mSELECT(); 

				}
				break;
			case 2 :
				// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:1:17: ET
				{
				mET(); 

				}
				break;
			case 3 :
				// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:1:20: OU
				{
				mOU(); 

				}
				break;
			case 4 :
				// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:1:23: MOTS
				{
				mMOTS(); 

				}
				break;
			case 5 :
				// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:1:28: RUBRIQUES
				{
				mRUBRIQUES(); 

				}
				break;
			case 6 :
				// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:1:38: DATES
				{
				mDATES(); 

				}
				break;
			case 7 :
				// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:1:44: MOIS
				{
				mMOIS(); 

				}
				break;
			case 8 :
				// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:1:49: ANNEE
				{
				mANNEE(); 

				}
				break;
			case 9 :
				// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:1:55: JOUR
				{
				mJOUR(); 

				}
				break;
			case 10 :
				// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:1:60: WS
				{
				mWS(); 

				}
				break;
			case 11 :
				// /home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g:1:63: VAR
				{
				mVAR(); 

				}
				break;

		}
	}



}
