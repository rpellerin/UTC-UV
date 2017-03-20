// Generated from grammars/PreLogo.g4 by ANTLR 4.5

  package prelogoparsing;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PreLogoParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, INT=5, ID=6, WS=7, COULEUR=8;
	public static final int
		RULE_prestat = 0, RULE_impt = 1, RULE_commande = 2;
	public static final String[] ruleNames = {
		"prestat", "impt", "commande"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'import'", "'('", "')'", "'nb = '"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, "INT", "ID", "WS", "COULEUR"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "PreLogo.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PreLogoParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class PrestatContext extends ParserRuleContext {
		public List<ImptContext> impt() {
			return getRuleContexts(ImptContext.class);
		}
		public ImptContext impt(int i) {
			return getRuleContext(ImptContext.class,i);
		}
		public List<CommandeContext> commande() {
			return getRuleContexts(CommandeContext.class);
		}
		public CommandeContext commande(int i) {
			return getRuleContext(CommandeContext.class,i);
		}
		public PrestatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prestat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PreLogoListener ) ((PreLogoListener)listener).enterPrestat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PreLogoListener ) ((PreLogoListener)listener).exitPrestat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PreLogoVisitor ) return ((PreLogoVisitor<? extends T>)visitor).visitPrestat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrestatContext prestat() throws RecognitionException {
		PrestatContext _localctx = new PrestatContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prestat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(7); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(6);
				impt();
				}
				}
				setState(9); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__0 );
			setState(12); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(11);
				commande();
				}
				}
				setState(14); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ImptContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(PreLogoParser.ID, 0); }
		public ImptContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_impt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PreLogoListener ) ((PreLogoListener)listener).enterImpt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PreLogoListener ) ((PreLogoListener)listener).exitImpt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PreLogoVisitor ) return ((PreLogoVisitor<? extends T>)visitor).visitImpt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImptContext impt() throws RecognitionException {
		ImptContext _localctx = new ImptContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_impt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(16);
			match(T__0);
			setState(17);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CommandeContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(PreLogoParser.ID, 0); }
		public List<TerminalNode> INT() { return getTokens(PreLogoParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(PreLogoParser.INT, i);
		}
		public TerminalNode COULEUR() { return getToken(PreLogoParser.COULEUR, 0); }
		public CommandeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_commande; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PreLogoListener ) ((PreLogoListener)listener).enterCommande(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PreLogoListener ) ((PreLogoListener)listener).exitCommande(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PreLogoVisitor ) return ((PreLogoVisitor<? extends T>)visitor).visitCommande(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CommandeContext commande() throws RecognitionException {
		CommandeContext _localctx = new CommandeContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_commande);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(19);
			match(ID);
			setState(20);
			match(T__1);
			setState(21);
			match(INT);
			setState(22);
			match(INT);
			setState(24);
			_la = _input.LA(1);
			if (_la==COULEUR) {
				{
				setState(23);
				match(COULEUR);
				}
			}

			setState(26);
			match(T__2);
			setState(29);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(27);
				match(T__3);
				setState(28);
				match(INT);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\n\"\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\3\2\6\2\n\n\2\r\2\16\2\13\3\2\6\2\17\n\2\r\2\16\2\20\3\3\3"+
		"\3\3\3\3\4\3\4\3\4\3\4\3\4\5\4\33\n\4\3\4\3\4\3\4\5\4 \n\4\3\4\2\2\5\2"+
		"\4\6\2\2\"\2\t\3\2\2\2\4\22\3\2\2\2\6\25\3\2\2\2\b\n\5\4\3\2\t\b\3\2\2"+
		"\2\n\13\3\2\2\2\13\t\3\2\2\2\13\f\3\2\2\2\f\16\3\2\2\2\r\17\5\6\4\2\16"+
		"\r\3\2\2\2\17\20\3\2\2\2\20\16\3\2\2\2\20\21\3\2\2\2\21\3\3\2\2\2\22\23"+
		"\7\3\2\2\23\24\7\b\2\2\24\5\3\2\2\2\25\26\7\b\2\2\26\27\7\4\2\2\27\30"+
		"\7\7\2\2\30\32\7\7\2\2\31\33\7\n\2\2\32\31\3\2\2\2\32\33\3\2\2\2\33\34"+
		"\3\2\2\2\34\37\7\5\2\2\35\36\7\6\2\2\36 \7\7\2\2\37\35\3\2\2\2\37 \3\2"+
		"\2\2 \7\3\2\2\2\6\13\20\32\37";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}