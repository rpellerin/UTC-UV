package beans;

public class Quiz {

	private int quiz_id;
	private String subject;
	private boolean isActive;
	private String creator; // TODO change to User?
	
	public Quiz() {
		
	}
	
	public Quiz(int quiz_id, String subject, boolean isActive, String creator) {
		this.quiz_id = quiz_id;
		this.subject = subject;
		this.isActive = isActive;
		this.creator = creator;
	}

	public int getQuiz_id() {
		return quiz_id;
	}

	public void setQuiz_id(int quiz_id) {
		this.quiz_id = quiz_id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

}
