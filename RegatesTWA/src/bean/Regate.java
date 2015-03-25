package bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table( name = "r_regate" )
public class Regate {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "nom")
	private String nom;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "niveau")
	private String niveau;
	
	@Column(name = "type")
	private String type;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dateDebut")
	private Date dateDebut;
	
	@Temporal(TemporalType.TIME)
	@Column(name = "heureDebut")
	private Date heureDebut;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dateFin")
	private Date dateFin;
	
	@Temporal(TemporalType.TIME)
	@Column(name = "heureFin")
	private Date heureFin;
	
    @ManyToMany(cascade={CascadeType.PERSIST})
    @JoinTable(name="r_inscriptionRegate",joinColumns = @JoinColumn(name = "regate"), inverseJoinColumns = @JoinColumn(name = "bateau"))
    @JsonManagedReference
	private Set<Bateau> bateaux = new HashSet<Bateau>();
	
	
	public Regate(){
		
	}
	
	
	public Integer getId() {
		return id;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String n) {
		nom = n;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getNiveau() {
		return niveau;
	}
	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public Date getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getHeureDebut() {
		return heureDebut;
	}
	public void setHeureDebut(Date heureDebut) {
		this.heureDebut = heureDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public Date getHeureFin() {
		return heureFin;
	}
	public void setHeureFin(Date heureFin) {
		this.heureFin = heureFin;
	}

	public Set<Bateau> getBateaux() {
		return bateaux;
	}
	public void setBateaux(Set<Bateau> bateaux) {
		this.bateaux = bateaux;
	}

}
