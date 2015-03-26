package bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table( name = "r_inscriptionRegate" )
public class InscriptionRegate {

    @Embeddable 
    public static class Id implements Serializable {
        // composantes de la clé composite 
        // pointe sur une Regate 
        @Column(name = "regate")
        private int regateId;  

        // pointe sur un Bateau
        @Column(name = "bateau")
        private int bateauId; 
 
        public Id() {
        	
        } 

        public int getRegateId() {
			return regateId;
		}
		public void setRegateId(int regateId) {
			this.regateId = regateId;
		}
		
		public int getBateauId() {
			return bateauId;
		}
		public void setBateauId(int bateauId) {
			this.bateauId = bateauId;
		} 
       
        // toString 
        public String toString() {
            return String.format("[%d,%d]", getRegateId(), getBateauId());
        } 
    } 
	
    
    

    // champs de la classe Personne_Activite 
    // clé composite 
    @EmbeddedId 
    private Id id = new Id();  

    // relation principale PersonneActivite (many) -> Personne (one) 
    // implémentée par la clé étrangère : personneId (PersonneActivite (many) -> Personne (one) 
    // personneId est en même temps élément de la clé primaire composite 
    // JPA ne doit pas gérer cette clé étrangère (insertable = false, updatable = false) car c'est fait par l'application elle-même dans son constructeur 
    @ManyToOne 
    @JoinColumn(name = "regate", insertable = false, updatable = false)
    @JsonBackReference("refIR.regate")
    private Regate irRegate; 

 
    // relation principale PersonneActivite -> Activite 
    // implémentée par la clé étrangère : activiteId (PersonneActivite (many) -> Activite (one) 
    // activiteId est en même temps élément de la clé primaire composite 
    // JPA ne doit pas gérer cette clé étrangère (insertable = false, updatable = false) car c'est fait par l'application elle-même dans son constructeur 
    @ManyToOne() 
    @JoinColumn(name = "bateau", insertable = false, updatable = false)
    @JsonBackReference("refIR.bateau")
    private Bateau irBateau; 

 

    // constructeurs 
    public InscriptionRegate() {  

    } 

 

    public InscriptionRegate(Regate r, Bateau b) { 
        // les clés étrangères sont fixées par l'application 
        getId().setRegateId(r.getId()); 
        getId().setBateauId(b.getId()); 

        // associations bidirectionnelles 
        this.setIrRegate(r); 
        this.setIrBateau(b); 

        r.getBateauxInscrits().add(this); 
        b.getRegates().add(this); 

    } 


	public Id getId() {
		return id;
	}
	public void setId(Id id) {
		this.id = id;
	}

	public Regate getIrRegate() {
		return irRegate;
	}
	public void setIrRegate(Regate irRegate) {
		this.irRegate = irRegate;
	}

	public Bateau getIrBateau() {
		return irBateau;
	}
	public void setIrBateau(Bateau irBateau) {
		this.irBateau = irBateau;
	} 


    // toString 
    public String toString() { 
        return String.format("[%s,%s,%s]", getId(), getIrRegate().getNom(), getIrBateau().getNom()); 

    }

}
