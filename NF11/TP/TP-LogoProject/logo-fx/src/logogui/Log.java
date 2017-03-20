package logogui;

import javafx.scene.control.TextArea;


public class Log {
	private static Log instance;
	private TextArea logZone;
	public static Log getInstance() {
		if (instance == null)
			instance = new Log();
		return instance;
	}
	public void setLogZone(TextArea zone) {
		logZone = zone;
	}
	public static void append(String s) {
		getInstance().getLogZone().appendText(s);
	}
	public static void appendnl(String s) {
		getInstance().getLogZone().appendText(s + "\n");
	}
	public TextArea getLogZone() {
		return logZone;
	}
}
