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
@Table( name = "r_course" )
public class Course {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer id;
	
	@Temporal(TemporalType.DATE)
	public Date dateDebut;
	
	@Temporal(TemporalType.TIME)
	public Date heureDebut;
	
	@Temporal(TemporalType.DATE)
	public Date dateFin;
	
	@Temporal(TemporalType.TIME)
	public Date heureFin;

	public double coefficient;
	public int regate;
	
	public Course(){
		
	}

	@Column(name = "id")
	public Integer getId() {
		return id;
	}

	@Column(name = "datedebut")
	public Date getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	@Column(name = "heureDebut")
	public Date getHeureDebut() {
		return heureDebut;
	}
	public void setHeureDebut(Date heureDebut) {
		this.heureDebut = heureDebut;
	}

	@Column(name = "dateFin")
	public Date getDateFin() {
		return dateFin;
	}
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	@Column(name = "heureFin")
	public Date getHeureFin() {
		return heureFin;
	}
	public void setHeureFin(Date heureFin) {
		this.heureFin = heureFin;
	}

	@Column(name = "coefficient")
	public double getCoefficient() {
		return coefficient;
	}
	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}

}
