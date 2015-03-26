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

@Entity
@Table( name = "r_equipage" )
public class Equipage {

    @Embeddable 
    public static class Id implements Serializable {
        // composantes de la clé composite 
        // pointe sur une Regate 
        @Column(name = "bateau")
        private int bateauId;  

        // pointe sur un Bateau
        @Column(name = "equipier")
        private String equipierId; 
 
        public Id() {
        	
        } 

        public String getEquipierId() {
			return equipierId;
		}
		public void setEquipierId(String equipierId) {
			this.equipierId = equipierId;
		}
		
		public int getBateauId() {
			return bateauId;
		}
		public void setBateauId(int bateauId) {
			this.bateauId = bateauId;
		} 
       
        // toString 
        public String toString() {
            return String.format("[%s,%d]", getEquipierId(), getBateauId());
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
    @JoinColumn(name = "equipier", insertable = false, updatable = false)
    @JsonBackReference("refE.user")
    private User eEquipier; 

 
    // relation principale PersonneActivite -> Activite 
    // implémentée par la clé étrangère : activiteId (PersonneActivite (many) -> Activite (one) 
    // activiteId est en même temps élément de la clé primaire composite 
    // JPA ne doit pas gérer cette clé étrangère (insertable = false, updatable = false) car c'est fait par l'application elle-même dans son constructeur 
    @ManyToOne() 
    @JoinColumn(name = "bateau", insertable = false, updatable = false)
    @JsonBackReference("refE.bateau")
    private Bateau eBateau; 

 

    // constructeurs 
    public Equipage() {  

    } 

 

    public Equipage(User e, Bateau b) { 
        // les clés étrangères sont fixées par l'application 
        getId().setEquipierId(e.getNumeroLicence()); 
        getId().setBateauId(b.getId()); 

        // associations bidirectionnelles 
        this.setEEquipier(e);
        this.setEBateau(b); 

        e.getBateaux().add(this); 
        b.getEquipage().add(this); 

    } 


	public Id getId() {
		return id;
	}
	public void setId(Id id) {
		this.id = id;
	}

	public User getEEquipier() {
		return eEquipier;
	}
	public void setEEquipier(User eEquipier) {
		this.eEquipier = eEquipier;
	}

	public Bateau getEBateau() {
		return eBateau;
	}
	public void setEBateau(Bateau eBateau) {
		this.eBateau = eBateau;
	} 


    // toString 
    public String toString() { 
        return String.format("[%s,%s,%s]", getId(), getEEquipier().getNom(), getEBateau().getNom()); 

    }

}
