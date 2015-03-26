package bean;

import java.io.Serializable;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table( name = "r_bateau" )
public class Bateau implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2180705953648956919L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "numero")
	private String numero;
	
	@Column(name = "nom")
	private String nom;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "nationalite")
	private String nationalite;
	
    @OneToMany(mappedBy = "eBateau", cascade = { CascadeType.REMOVE }) 
    @JsonManagedReference("refE.bateau")
	private Set<Equipage> equipage = new HashSet<Equipage>();
	
    @OneToMany(mappedBy = "irBateau", cascade = { CascadeType.REMOVE }) 
    @JsonManagedReference("refIR.bateau")
	private Set<InscriptionRegate> regates = new HashSet<InscriptionRegate>();
	
	
	public Bateau(){
		
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

	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getNationalite() {
		return nationalite;
	}
	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}

	public Set<Equipage> getEquipage() {
		return equipage;
	}
	public void setEquipage(Set<Equipage> equipage) {
		this.equipage = equipage;
	}

	public Set<InscriptionRegate> getRegates() {
		return regates;
	}
	public void setRegates(Set<InscriptionRegate> regates) {
		this.regates = regates;
	}
}
