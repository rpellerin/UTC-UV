package beans;

public class Question {

	private int question_id;
	private String question;
	private int _order;
	private boolean isActive;
	private int quiz;
	
	public Question() {
		
	}

	public Question(int question_id, String question, int _order, boolean isActive, int quiz) {
		this.question_id = question_id;
		this.question = question;
		this._order = _order;
		this.isActive = isActive;
		this.quiz = quiz;
	}

	public int getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public int get_order() {
		return _order;
	}

	public void set_order(int _order) {
		this._order = _order;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getQuiz() {
		return quiz;
	}

	public void setQuiz(int quiz) {
		this.quiz = quiz;
	}


}
