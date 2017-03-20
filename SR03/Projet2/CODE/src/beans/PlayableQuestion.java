package beans;

import java.util.ArrayList;

public class PlayableQuestion extends Question {

	private ArrayList<Answer> answers = new ArrayList<Answer>();
	
	public PlayableQuestion() {
		super();
	}

	public PlayableQuestion(int question_id, String question, int _order, boolean isActive, int quiz) {
		super(question_id, question, _order, isActive, quiz);
	}
	
	public ArrayList<Answer> getAnswers() {
		return answers;
	}
	
	public void addAnswer(Answer x) {
		this.answers.add(x);
	}
}
