package beans;

import java.util.Date;

public class Course {

	private int course_id = -1;
	private Date date;
	private int duration; // in seconds
	private int score;
	private String user;
	private int quiz;
	
	public Course(int course_id, Date date, int duration, int score, String user, int quiz) {
		super();
		this.course_id = course_id;
		this.date = date;
		this.duration = duration;
		this.score = score;
		this.user = user;
		this.quiz = quiz;
	}

	public Course() {
		
	}

	public int getCourse_id() {
		return course_id;
	}

	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getQuiz() {
		return quiz;
	}

	public void setQuiz(int quiz) {
		this.quiz = quiz;
	}

}
