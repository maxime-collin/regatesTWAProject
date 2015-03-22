package bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table( name = "r_regate" )
public class Regate {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String nom;
	private String description;
	private String niveau;
	private String type;
	
	@Temporal(TemporalType.DATE)
	private Date dateDebut;
	
	@Temporal(TemporalType.TIME)
	private Date heureDebut;
	
	@Temporal(TemporalType.DATE)
	private Date dateFin;
	
	@Temporal(TemporalType.TIME)
	private Date heureFin;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pkIR.regate", cascade=CascadeType.ALL)
	private List<InscriptionRegate> inscrBateaux = new ArrayList<InscriptionRegate>();
	
	
	public Regate(){
		
	}
	
	@Column(name = "id")
	public Integer getId() {
		return id;
	}
	
	@Column(name = "nom")
	public String getNom() {
		return nom;
	}
	public void setNom(String n) {
		nom = n;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "niveau")
	public String getNiveau() {
		return niveau;
	}
	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}

	@Column(name = "type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "dateDebut")
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

	public List<InscriptionRegate> getInscrBateaux() {
		return inscrBateaux;
	}
	public void setInscrBateaux(List<InscriptionRegate> inscrBateaux) {
		this.inscrBateaux = inscrBateaux;
	}

}
