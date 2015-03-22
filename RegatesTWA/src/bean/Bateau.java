package bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table( name = "r_bateau" )
public class Bateau {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String numero;
	private String nom;	
	private String type;
	private String nationalite;
	
	@OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="capitaine")
	private User capitaine;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pkE.bateau", cascade=CascadeType.ALL)
	private List<Equipage> equipage = new ArrayList<Equipage>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pkIR.bateau", cascade=CascadeType.ALL)
	private List<InscriptionRegate> inscrRegates = new ArrayList<InscriptionRegate>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pkIC.bateau", cascade=CascadeType.ALL)
	private List<InscriptionCourse> inscrCourses = new ArrayList<InscriptionCourse>();
	
	public Bateau(){
		
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

	@Column(name = "numero")
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}

	public User getCapitaine() {
		return capitaine;
	}
	public void setCapitaine(User capitaine) {
		this.capitaine = capitaine;
	}

	@Column(name = "type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "nationalite")
	public String getNationalite() {
		return nationalite;
	}
	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}

	public List<Equipage> getEquipage() {
		return equipage;
	}
	public void setEquipage(List<Equipage> equipage) {
		this.equipage = equipage;
	}

	public List<InscriptionRegate> getInscrRegates() {
		return inscrRegates;
	}
	public void setInscrRegates(List<InscriptionRegate> inscrRegates) {
		this.inscrRegates = inscrRegates;
	}

	public List<InscriptionCourse> getInscrCourses() {
		return inscrCourses;
	}
	public void setInscrCourses(List<InscriptionCourse> inscrCourses) {
		this.inscrCourses = inscrCourses;
	}

}
