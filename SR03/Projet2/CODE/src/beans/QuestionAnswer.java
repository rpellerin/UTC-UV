package beans;

public class QuestionAnswer {

	private int course;
	private int question;
	private int answer;

	public QuestionAnswer(int course, int question, int answer) {
		this.course = course;
		this.question = question;
		this.answer = answer;
	}

	public QuestionAnswer() {
		
	}

	public int getCourse() {
		return course;
	}

	public void setCourse(int course) {
		this.course = course;
	}

	public int getQuestion() {
		return question;
	}

	public void setQuestion(int question) {
		this.question = question;
	}

	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}



}
