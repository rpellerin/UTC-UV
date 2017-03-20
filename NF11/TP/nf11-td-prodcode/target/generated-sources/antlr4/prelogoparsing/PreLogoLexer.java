// Generated from PreLogo.g4 by ANTLR 4.4

  package prelogoparsing;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PreLogoLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__3=1, T__2=2, T__1=3, T__0=4, INT=5, ID=6, WS=7, COULEUR=8;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'"
	};
	public static final String[] ruleNames = {
		"T__3", "T__2", "T__1", "T__0", "INT", "ID", "WS", "COULEUR"
	};


	public PreLogoLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "PreLogo.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\nE\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\3\3\3\3"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\7\6("+
		"\n\6\f\6\16\6+\13\6\5\6-\n\6\3\7\6\7\60\n\7\r\7\16\7\61\3\b\6\b\65\n\b"+
		"\r\b\16\b\66\3\b\3\b\3\t\3\t\3\t\3\t\7\t?\n\t\f\t\16\tB\13\t\5\tD\n\t"+
		"\2\2\n\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\3\2\6\3\2\63;\3\2\62;\3\2c|"+
		"\5\2\13\f\17\17\"\"J\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2"+
		"\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\3\23\3\2\2\2\5\25\3"+
		"\2\2\2\7\27\3\2\2\2\t\35\3\2\2\2\13,\3\2\2\2\r/\3\2\2\2\17\64\3\2\2\2"+
		"\21:\3\2\2\2\23\24\7*\2\2\24\4\3\2\2\2\25\26\7+\2\2\26\6\3\2\2\2\27\30"+
		"\7p\2\2\30\31\7d\2\2\31\32\7\"\2\2\32\33\7?\2\2\33\34\7\"\2\2\34\b\3\2"+
		"\2\2\35\36\7k\2\2\36\37\7o\2\2\37 \7r\2\2 !\7q\2\2!\"\7t\2\2\"#\7v\2\2"+
		"#\n\3\2\2\2$-\7\62\2\2%)\t\2\2\2&(\t\3\2\2\'&\3\2\2\2(+\3\2\2\2)\'\3\2"+
		"\2\2)*\3\2\2\2*-\3\2\2\2+)\3\2\2\2,$\3\2\2\2,%\3\2\2\2-\f\3\2\2\2.\60"+
		"\t\4\2\2/.\3\2\2\2\60\61\3\2\2\2\61/\3\2\2\2\61\62\3\2\2\2\62\16\3\2\2"+
		"\2\63\65\t\5\2\2\64\63\3\2\2\2\65\66\3\2\2\2\66\64\3\2\2\2\66\67\3\2\2"+
		"\2\678\3\2\2\289\b\b\2\29\20\3\2\2\2:C\7%\2\2;D\7\62\2\2<@\t\2\2\2=?\t"+
		"\3\2\2>=\3\2\2\2?B\3\2\2\2@>\3\2\2\2@A\3\2\2\2AD\3\2\2\2B@\3\2\2\2C;\3"+
		"\2\2\2C<\3\2\2\2D\22\3\2\2\2\t\2),\61\66@C\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}