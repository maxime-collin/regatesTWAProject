package bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table( name = "r_course" )
public class Course implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private Integer numero;

	
	@Temporal(TemporalType.DATE)
	private Date dateDebut;
	
	@Temporal(TemporalType.TIME)
	private Date heureDebut;
	
	@Temporal(TemporalType.DATE)
	private Date dateFin;
	
	@Temporal(TemporalType.TIME)
	private Date heureFin;

	private double coefficient;
	
	@OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="regate")
	private Regate regate;

	
	public Course(){
		
	}

	@Column(name = "id")
	public Integer getId() {
		return id;
	}

	@Column(name = "numero")
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
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

	public Regate getRegate() {
		return regate;
	}
	public void setRegate(Regate regate) {
		this.regate = regate;
	}

}
