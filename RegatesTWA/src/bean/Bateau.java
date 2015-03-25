package bean;

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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table( name = "r_bateau" )
public class Bateau {
	
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
	
	@ManyToMany(cascade={CascadeType.PERSIST})
    @JoinTable(name="r_equipage",joinColumns = @JoinColumn(name = "bateau"), inverseJoinColumns = @JoinColumn(name = "equipier"))
    @JsonManagedReference
	private Set<User> equipage = new HashSet<User>();
	
    @ManyToMany(mappedBy="bateaux")
    @JsonBackReference
	private Set<Regate> regates = new HashSet<Regate>();
	
	
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

	public Set<User> getEquipage() {
		return equipage;
	}
	public void setEquipage(Set<User> equipage) {
		this.equipage = equipage;
	}

	public Set<Regate> getRegates() {
		return regates;
	}
	public void setRegates(Set<Regate> regates) {
		this.regates = regates;
	}
}
