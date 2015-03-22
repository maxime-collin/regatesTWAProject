package bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table( name = "r_inscriptionCourse" )
@AssociationOverrides({	@AssociationOverride(name = "pkIC.course", joinColumns = @JoinColumn(name = "course")),
						@AssociationOverride(name = "pkIC.bateau", joinColumns = @JoinColumn(name = "bateau"))
})
public class InscriptionCourse implements Serializable {
	
	private static final long serialVersionUID = -1442756526149710606L;
	
	private InscriptionCourseId pkIC = new InscriptionCourseId();
	private int classement;
	
	@Temporal(TemporalType.DATE)
	private Date dateArrivee;
	
	@Temporal(TemporalType.TIME)
	private Date heureArrivee;


	public InscriptionCourse(){
		
	}


	@EmbeddedId
	public InscriptionCourseId getPkIC() {
		return pkIC;
	}
	public void setPkIC(InscriptionCourseId pkIC) {
		this.pkIC = pkIC;
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
