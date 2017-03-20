package utils;

import java.util.ArrayList;

public class ConsoleOutputBuffer {
	
	public static class Line {
		private String line;
		private boolean isErrorLine = false;
		
		public Line(String line, boolean isErrorLine) {
			this.line = line;
			this.isErrorLine = isErrorLine;
		}

		public String getLine() {
			return line;
		}

		public boolean isErrorLine() {
			return isErrorLine;
		}
	}
	
	
	private static ConsoleOutputBuffer singleton;
	private ArrayList<Line> buffer;
	
	private ConsoleOutputBuffer() {
		buffer = new ArrayList<Line>();
	}
	
	public synchronized static ConsoleOutputBuffer getInstance() {
		if (singleton == null) {
			singleton = new ConsoleOutputBuffer();
		}
		return singleton;
	}
	
	public synchronized ArrayList<Line> getBuffer() {
		return buffer;
	}
	
	public synchronized void addErrorLine(String line) {
		buffer.add(new Line(line, true));
	}
	
	public synchronized void addLine(String line) {
		buffer.add(new Line(line, false));
	}
	
	public synchronized void addStackTrace(StackTraceElement[] st) {
		for (StackTraceElement el : st) {
			buffer.add(new Line(el.toString(), true));
		}
	}
	
	public synchronized void printBufferOnStandardOutput() {
		for (Line e: buffer) {
			if (e.isErrorLine) {
				System.err.println(e.getLine());
			}
			else {
				System.out.println(e.getLine());
			}
		}
	}
	
	public synchronized void clear() {
		this.buffer.clear();
	}
}
