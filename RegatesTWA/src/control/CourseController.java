package control;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import bean.Course;

@Controller
public class CourseController {
	
	@Resource
	Dao dao;

	public Resultat convertBindingResult(BindingResult bres) {
		Resultat res = new Resultat();
		if(!bres.hasErrors()){
			res.setRes("SUCCESS");
		}else{
			res.setRes("FAIL");
		}
		for (FieldError fe : bres.getFieldErrors()) {
			System.out.println(fe.getField()+" "+fe.getDefaultMessage());
			res.getErr().put(fe.getField(), fe.getDefaultMessage());
		}
		return res;
	}
	
	@RequestMapping(value="/enregistrerCourse",method=RequestMethod.GET)
	public String showForm(){
		return "enregistrerCourse";
	}
	
	@RequestMapping(value="{regateId}/enregistrerCourse",method=RequestMethod.POST)
	public @ResponseBody Resultat enregistrerUser(
			@RequestBody @Valid Course course, @PathVariable("regateId") int regateId, BindingResult bres) {
		System.out.println("enregistrerCourse is = "+course.getNumero());
		Resultat res = convertBindingResult(bres);
	
		Course c = new Course();
		c.setNumero(course.getNumero());
		c.setDateDebut(course.getDateDebut());
		c.setHeureDebut(course.getHeureDebut());
		c.setDateFin(course.getDateFin());
		c.setHeureFin(course.getHeureFin());
		c.setCoefficient(course.getCoefficient());
		
		if(res.getRes().equals("SUCCESS")) {
			System.out.println("res sucess");
			dao.enregistrerCourse(course, regateId);
		}
		return res;
	}
	
	@RequestMapping(value="/supprimerCourse",method=RequestMethod.POST)
	public @ResponseBody Resultat supprimerCourse(
			@RequestBody @Valid Course course, BindingResult bres) {
		System.out.println("supprimerCourse id = "+course.getId());
		Resultat res = convertBindingResult(bres);
			
		if(res.getRes().equals("SUCCESS")) {
			System.out.println("res sucess");
			dao.supprimerCourse(course);
		}
		return res;
	}
	
	 @RequestMapping(value="/modifierCourse", params= "idCourse", method=RequestMethod.GET)
	 public @ResponseBody Course returnCourse(@RequestParam("idCourse") Integer idCourse){
		 System.out.println("modifierCourse id = " + idCourse);
		 Course course = dao.returnCourse(idCourse);
		 return course;
	 }
	 	 
	 @RequestMapping(value="/modifierCourse",method=RequestMethod.POST)
		public @ResponseBody Resultat modifierCourse(
				@RequestBody @Valid Course course, BindingResult bres) {
			System.out.println("modifierCourse id = "+course.getId());
			Resultat res = convertBindingResult(bres);
					
			if(res.getRes().equals("SUCCESS")) {
				System.out.println("res sucess");
				dao.modifiercourse(course);
			}
			return res;
		}

}