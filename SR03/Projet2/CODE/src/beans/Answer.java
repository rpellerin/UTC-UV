package beans;

public class Answer {

	private int answer_id;
	private String answer;
	private int _order;
	private boolean isCorrect;
	private boolean isActive;
	private int question;
	
	public Answer(int answer_id, String answer, int _order, boolean isCorrect, boolean isActive, int question) {
		super();
		this.answer_id = answer_id;
		this.answer = answer;
		this._order = _order;
		this.isCorrect = isCorrect;
		this.isActive = isActive;
		this.question = question;
	}

	public int getAnswer_id() {
		return answer_id;
	}

	public void setAnswer_id(int answer_id) {
		this.answer_id = answer_id;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int get_order() {
		return _order;
	}

	public void set_order(int _order) {
		this._order = _order;
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getQuestion() {
		return question;
	}

	public void setQuestion(int question) {
		this.question = question;
	}

	public Answer() {
		
	}

	@Override
	public String toString() {
		return "Answer [answer_id=" + answer_id + ", answer=" + answer + ", _order=" + _order + ", isCorrect="
				+ isCorrect + ", isActive=" + isActive + ", question=" + question + "]";
	}


}
