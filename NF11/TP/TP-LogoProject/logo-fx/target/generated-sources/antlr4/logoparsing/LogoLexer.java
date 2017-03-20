// Generated from Logo.g4 by ANTLR 4.4

  package logoparsing;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LogoLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__17=1, T__16=2, T__15=3, T__14=4, T__13=5, T__12=6, T__11=7, T__10=8, 
		T__9=9, T__8=10, T__7=11, T__6=12, T__5=13, T__4=14, T__3=15, T__2=16, 
		T__1=17, T__0=18, INT=19, WS=20;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'", "'\\u000F'", "'\\u0010'", "'\\u0011'", "'\\u0012'", 
		"'\\u0013'", "'\\u0014'"
	};
	public static final String[] ruleNames = {
		"T__17", "T__16", "T__15", "T__14", "T__13", "T__12", "T__11", "T__10", 
		"T__9", "T__8", "T__7", "T__6", "T__5", "T__4", "T__3", "T__2", "T__1", 
		"T__0", "INT", "WS"
	};


	public LogoLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Logo.g4"; }

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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\26q\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5"+
		"\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3"+
		"\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17"+
		"\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\24\3\24\3\24\7\24"+
		"d\n\24\f\24\16\24g\13\24\5\24i\n\24\3\25\6\25l\n\25\r\25\16\25m\3\25\3"+
		"\25\2\2\26\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33"+
		"\17\35\20\37\21!\22#\23%\24\'\25)\26\3\2\5\3\2\63;\3\2\62;\5\2\13\f\17"+
		"\17\"\"s\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2"+
		"\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2"+
		"\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\3+\3\2\2\2\5-\3\2\2\2"+
		"\7\60\3\2\2\2\t\63\3\2\2\2\13\67\3\2\2\2\r:\3\2\2\2\17A\3\2\2\2\21D\3"+
		"\2\2\2\23F\3\2\2\2\25H\3\2\2\2\27M\3\2\2\2\31P\3\2\2\2\33R\3\2\2\2\35"+
		"T\3\2\2\2\37V\3\2\2\2!X\3\2\2\2#[\3\2\2\2%^\3\2\2\2\'h\3\2\2\2)k\3\2\2"+
		"\2+,\7\61\2\2,\4\3\2\2\2-.\7t\2\2./\7g\2\2/\6\3\2\2\2\60\61\7v\2\2\61"+
		"\62\7i\2\2\62\b\3\2\2\2\63\64\7h\2\2\64\65\7e\2\2\65\66\7e\2\2\66\n\3"+
		"\2\2\2\678\7n\2\289\7e\2\29\f\3\2\2\2:;\7j\2\2;<\7c\2\2<=\7u\2\2=>\7c"+
		"\2\2>?\7t\2\2?@\7f\2\2@\16\3\2\2\2AB\7c\2\2BC\7x\2\2C\20\3\2\2\2DE\7]"+
		"\2\2E\22\3\2\2\2FG\7_\2\2G\24\3\2\2\2HI\7h\2\2IJ\7r\2\2JK\7q\2\2KL\7u"+
		"\2\2L\26\3\2\2\2MN\7d\2\2NO\7e\2\2O\30\3\2\2\2PQ\7*\2\2Q\32\3\2\2\2RS"+
		"\7+\2\2S\34\3\2\2\2TU\7,\2\2U\36\3\2\2\2VW\7-\2\2W \3\2\2\2XY\7v\2\2Y"+
		"Z\7f\2\2Z\"\3\2\2\2[\\\7x\2\2\\]\7g\2\2]$\3\2\2\2^_\7/\2\2_&\3\2\2\2`"+
		"i\7\62\2\2ae\t\2\2\2bd\t\3\2\2cb\3\2\2\2dg\3\2\2\2ec\3\2\2\2ef\3\2\2\2"+
		"fi\3\2\2\2ge\3\2\2\2h`\3\2\2\2ha\3\2\2\2i(\3\2\2\2jl\t\4\2\2kj\3\2\2\2"+
		"lm\3\2\2\2mk\3\2\2\2mn\3\2\2\2no\3\2\2\2op\b\25\2\2p*\3\2\2\2\6\2ehm\3"+
		"\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}