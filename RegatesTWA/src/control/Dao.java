package control;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.stereotype.Service;

import bean.Bateau;
import bean.Course;
import bean.Regate;
import bean.User;

@Service
public class Dao {
	
	//Dao dao = new Dao();
	
	EntityManagerFactory emf = null;
	EntityManager em = null;

	public Dao() {
		System.out.println("Dao xxxxx");
		emf = Persistence.createEntityManagerFactory("tphibernate");
		em = emf.createEntityManager();
	}
	
	public void enregistrerUser(User u) {
		System.out.println("dao enregistrerUser");
		System.out.println("dao user nom : "+u.getNom());
		EntityTransaction tx = em.getTransaction();
		if(!tx.isActive()){
			tx.begin();
		}
		em.persist(u);
		tx.commit();
		System.out.println("dao commit");
	}
	
	public List<User>listerUsers() {
		System.out.println("dao listerUsers");
		List <User> lst = em.createQuery("select u from User u").getResultList();
		return lst;
	}
	
	public void supprimerUser(User user) {
		EntityTransaction tx = em.getTransaction();
		if (!tx.isActive()) 
			tx.begin();
		User u = em.find(User.class, user.getNumeroLicence());
		em.remove(u);
		tx.commit();
	}
	
	public User returnUser(Integer id){
		System.out.println("dao returnUser");

		EntityTransaction tx = em.getTransaction();
		if (!tx.isActive()) 
			tx.begin();
		User u = em.find(User.class, id);
		return u;
	}

	public void modifierUser(User u){
		System.out.println("dao modifierUser");
		System.out.println("dao user nom : "+u.getNom());
		EntityTransaction tx = em.getTransaction();
		if(!tx.isActive()){
			tx.begin();
		}
		em.merge(u);
		tx.commit();
		System.out.println("dao commit");
	}

	public void enregistrerCourse(Course course, int regateId) {
		System.out.println("dao enregistrerCourse");
		System.out.println("dao course id : "+course.getNumero());
		EntityTransaction tx = em.getTransaction();
		if(!tx.isActive()){
			tx.begin();
		}
		Regate regate = returnRegate(regateId);
		course.setRegate(regate);
		em.persist(course);
		tx.commit();
		System.out.println("dao commit");		
	}

	public Course returnCourse(Integer idCourse) {
		System.out.println("dao returnCourse");

		EntityTransaction tx = em.getTransaction();
		if (!tx.isActive()) 
			tx.begin();
		Course c = em.find(Course.class, idCourse);
		return c;
	}

	public void supprimerCourse(Course c) {
		EntityTransaction tx = em.getTransaction();
		if (!tx.isActive()) 
			tx.begin();
		Course course = em.find(Course.class, c.getId());
		em.remove(course);
		tx.commit();
	}
	
	public void modifiercourse(Course c) {
		System.out.println("dao modifierCourse");
		System.out.println("dao course id : "+c.getId());
		EntityTransaction tx = em.getTransaction();
		if(!tx.isActive()){
			tx.begin();
		}
		em.merge(c);
		tx.commit();
		System.out.println("dao commit");
	}

	public void enregistrerBateau(Bateau b) {
		System.out.println("dao enregistrerBateau");
		System.out.println("dao bateau nom : "+b.getNom());
		EntityTransaction tx = em.getTransaction();
		if(!tx.isActive()){
			tx.begin();
		}
		em.persist(b);
		tx.commit();
		System.out.println("dao commit");	
	}

	public Bateau returnBateau(Integer id) {
		System.out.println("dao returnBateau");

		EntityTransaction tx = em.getTransaction();
		if (!tx.isActive()) 
			tx.begin();
		Bateau b = em.find(Bateau.class, id);
		return b;
	}

	public void modifierBateau(Bateau b) {
		System.out.println("dao modifierBateau");
		System.out.println("dao Bateau nom : "+b.getNom());
		EntityTransaction tx = em.getTransaction();
		if(!tx.isActive()){
			tx.begin();
		}
		em.merge(b);
		tx.commit();
		System.out.println("dao commit");
	}

	public void supprimerBateau(Bateau b) {
		EntityTransaction tx = em.getTransaction();
		if (!tx.isActive()) 
			tx.begin();
		Bateau bateau = em.find(Bateau.class, b.getId());
		em.remove(bateau);
		tx.commit();
	}

	public void enregistrerRegate(Regate r) {
		System.out.println("dao enregistrerRegate");
		System.out.println("dao regate nom : "+r.getNom());
		EntityTransaction tx = em.getTransaction();
		if(!tx.isActive()){
			tx.begin();
		}
		em.persist(r);
		tx.commit();
		System.out.println("dao commit");	
	}

	public void supprimerRegate(Regate r) {
		EntityTransaction tx = em.getTransaction();
		if (!tx.isActive()) 
			tx.begin();
		Regate regate = em.find(Regate.class, r.getId());
		em.remove(regate);
		tx.commit();
	}

	public Regate returnRegate(Integer id) {
		System.out.println("dao returnRegate");

		EntityTransaction tx = em.getTransaction();
		if (!tx.isActive()) 
			tx.begin();
		Regate r = em.find(Regate.class, id);
		System.out.println("DAO returnRegate -> size r.bateaux : " + r.getBateaux().size());
		for (Bateau bateau : r.getBateaux())
			System.out.println("DAO returnRegate -> bateau.nom : " + bateau.getNom());
		
		return r;
	}

	public void modifierRegate(Regate r) {
		System.out.println("dao modifierRegate");
		EntityTransaction tx = em.getTransaction();
		if(!tx.isActive()){
			tx.begin();
		}
		em.merge(r);
		tx.commit();
		System.out.println("dao commit");
	}

	public List<Regate> listerRegates() {
		System.out.println("dao listerRegates");
		List <Regate> lst = em.createQuery("select r from Regate r").getResultList();
		return lst;
	}

	public List<Course> listerRegateCourses(Integer id) {
		System.out.println("dao listerRegateCourses");
		List<Course> listC = em.createQuery("select c from Course c Where c.regate.id = " + id).getResultList();
				
		return listC;
	}

	/*public List<Bateau> listerRegateBateaux(Integer id) {
		System.out.println("dao listerRegateBateaux");
		Regate regate = returnRegate(id);
		List<Bateau> listB = new ArrayList<Bateau>();
		
		for (InscriptionRegate ir : regate.getInscrBateaux())
			listB.add(ir.getPkIR().getBateau());
		
		return listB;
	}*/

	public void supprimerBateauToRegate(Regate r, Bateau b) {
		// TODO Auto-generated method stub
		
	}
}