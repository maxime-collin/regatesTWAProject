package bean;

import java.io.Serializable;

import javax.persistence.ManyToOne;

public class InscriptionCourseId implements Serializable{

	private static final long serialVersionUID = 8869492896533150280L;

	private Course course;
	private Bateau bateau;
	
	@ManyToOne
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
	@ManyToOne
	public Bateau getBateau() {
		return bateau;
	}
	public void setBateau(Bateau bateau) {
		this.bateau = bateau;
	}
	
	
}
