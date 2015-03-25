package control;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import bean.Bateau;
import bean.Course;
import bean.Regate;

@Controller
public class RegateController {
	
	@Resource
	Dao dao;

	@RequestMapping(value="/listerRegates",method=RequestMethod.GET)
	public @ResponseBody List <Regate> listerRegate() {
		System.out.println("listerRegates");
		return dao.listerRegates();
	}
	
	@RequestMapping(value="/listerRegateCourses", params= "id", method=RequestMethod.GET)
	public @ResponseBody List <Course> listerRegateCourses(@RequestParam("id") Integer id) {
		System.out.println("listerRegates");
		return dao.listerRegateCourses(id);
	}
	
	@RequestMapping(value="/listerRegateBateaux", params= "id", method=RequestMethod.GET)
	public @ResponseBody List <Bateau> listerRegateBateaux(@RequestParam("id") Integer id) {
		System.out.println("listerRegates");
		//return dao.listerRegateBateaux(id);
		return null;
	}

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
	
	@RequestMapping(value="/enregistrerRegate",method=RequestMethod.GET)
	public String showForm(){
		return "enregistrerRegate";
	}
	
	@RequestMapping(value="/enregistrerRegate",method=RequestMethod.POST)
	public @ResponseBody Resultat enregistrerRegate(
			@RequestBody @Valid Regate regate, BindingResult bres) {
		System.out.println("enregistrerRegate nom = "+regate.getNom());
		Resultat res = convertBindingResult(bres);
	
		Regate r = new Regate();
		r.setNom(regate.getNom());
		r.setDescription(regate.getDescription());
		r.setNiveau(regate.getNiveau());
		r.setType(regate.getType());
		r.setDateDebut(regate.getDateDebut());
		r.setHeureDebut(regate.getHeureDebut());
		r.setDateFin(regate.getDateFin());
		r.setHeureFin(regate.getHeureFin());
		
		if(res.getRes().equals("SUCCESS")) {
			System.out.println("res sucess");
			dao.enregistrerRegate(r);
		}
		return res;
	}
	
	@RequestMapping(value="/supprimerRegate",method=RequestMethod.POST)
	public @ResponseBody Resultat supprimerRegate(
			@RequestBody @Valid Regate regate, BindingResult bres) {
		
		System.out.println("supprimerRegate nom = "+regate.getNom());
		Resultat res = convertBindingResult(bres);
			
		if(res.getRes().equals("SUCCESS")) {
			System.out.println("res sucess");
			dao.supprimerRegate(regate);
		}
		return res;
	}
	
	
	@RequestMapping(value="/supprimerBateauToRegate",method=RequestMethod.POST)
	public @ResponseBody Resultat supprimerBateauToRegate(
			@RequestParam("regate") Regate regate, @RequestParam("bateau") Bateau bateau, BindingResult bres) {
		System.out.println("supprimerBateauToRegate nom = "+bateau.getNom());
		Resultat res = convertBindingResult(bres);
			
		if(res.getRes().equals("SUCCESS")) {
			System.out.println("res sucess");
			dao.supprimerBateauToRegate(regate, bateau);
		}
		return res;
	}
	
	 @RequestMapping(value="/modifierRegate", params= "id", method=RequestMethod.GET)
	 public @ResponseBody Regate returnRegate(@RequestParam("id") Integer id){
		 System.out.println("modifierRegate id = " + id);
		 Regate regate = dao.returnRegate(id);
		 return regate;
	 }
	 	 
	 @RequestMapping(value="/modifierRegate",method=RequestMethod.POST)
		public @ResponseBody Resultat modifierRegate(
				@RequestBody @Valid Regate regate, BindingResult bres) {
			System.out.println("modifierRegate nom = "+regate.getNom());
			Resultat res = convertBindingResult(bres);
			
			if(res.getRes().equals("SUCCESS")) {
				System.out.println("res sucess");
				dao.modifierRegate(regate);
			}
			return res;
		}

}