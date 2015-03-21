package bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table( name = "r_inscriptionCourse" )
public class InscriptionCourse {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer id;
	
	public int course;
	public int classement;
	
	@Temporal(TemporalType.DATE)
	public Date dateArrivee;
	
	@Temporal(TemporalType.TIME)
	public Date heureArrivee;


	public InscriptionCourse(){
		
	}

	@Column(name = "id")
	public Integer getId() {
		return id;
	}

	@Column(name = "course")
	public int getCourse() {
		return course;
	}
	public void setCourse(int course) {
		this.course = course;
	}
		
	@Column(name = "dateArrivee")
	public Date getDateArrivee() {
		return dateArrivee;
	}
	public void setDateArrivee(Date dateArrivee) {
		this.dateArrivee = dateArrivee;
	}
	
	@Column(name = "heureArrivee")
	public Date getHeureArrivee() {
		return heureArrivee;
	}
	public void setHeureArrivee(Date heureArrivee) {
		this.heureArrivee = heureArrivee;
	}
	
	@Column(name = "classement")
	public int getClassement() {
		return classement;
	}
	public void setClassement(int classement) {
		this.classement = classement;
	}

}
