package beans;

import java.util.ArrayList;

public class PlayableQuiz extends Quiz {

	private ArrayList<PlayableQuestion> pq = new ArrayList<PlayableQuestion>();
	
	public PlayableQuiz() {
		super();
	}

	public PlayableQuiz(int quiz_id, String subject, boolean isActive, String creator) {
		super(quiz_id, subject, isActive, creator);
	}

	public ArrayList<PlayableQuestion> getQuestions() {
		return pq;
	}
	
	public void addQuestion(PlayableQuestion x) {
		this.pq.add(x);
	}
}
