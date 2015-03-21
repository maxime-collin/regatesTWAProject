package control;

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

@Controller
public class BateauController {
	
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
	
	@RequestMapping(value="/enregistrerBateau",method=RequestMethod.GET)
	public String showForm(){
		return "enregistrerBateau";
	}
	
	@RequestMapping(value="/enregistrerBateau", method=RequestMethod.POST)
	public @ResponseBody Resultat enregistrerBateau(
			@RequestBody @Valid Bateau bateau, BindingResult bres) {
		System.out.println("enregistrerBateau nom = "+bateau.getNom());
		Resultat res = convertBindingResult(bres);
			
		Bateau b = new Bateau();
		b.setNumero(bateau.getNumero());
		b.setNom(bateau.getNom());
		b.setNationalite(bateau.getNationalite());
		b.setType(bateau.getType());
		b.setCapitaine(bateau.getCapitaine());
		
		if(res.getRes().equals("SUCCESS")) {
			System.out.println("res sucess");
			dao.enregistrerBateau(b);
		}
		return res;
	}
	
	@RequestMapping(value="/supprimerBateau",method=RequestMethod.POST)
	public @ResponseBody Resultat supprimerUser(
			@RequestBody @Valid Bateau bateau, BindingResult bres) {
		System.out.println("supprimerBateau nom = "+bateau.getNom());
		Resultat res = convertBindingResult(bres);
	
		Bateau b = new Bateau();
		b.setNumero(bateau.getNumero());
		b.setNom(bateau.getNom());
		b.setNationalite(bateau.getNationalite());
		b.setType(bateau.getType());
		b.setCapitaine(bateau.getCapitaine());
		
		if(res.getRes().equals("SUCCESS")) {
			System.out.println("res sucess");
			dao.supprimerBateau(b);
		}
		return res;
	}
		
	 @RequestMapping(value="/modifierBateau", params= "id", method=RequestMethod.GET)
	 public @ResponseBody Bateau returnBateau(@RequestParam("id") Integer id){
		 System.out.println("modifierBateau id = " + id);
		 Bateau bateau = dao.returnBateau(id);
		 return bateau;
	 }
	 	 
	 @RequestMapping(value="/modifierBateau",method=RequestMethod.POST)
		public @ResponseBody Resultat modifierBateau(
				@RequestBody @Valid Bateau bateau, BindingResult bres) {
			System.out.println("modifierBateau nom = "+bateau.getNom());
			Resultat res = convertBindingResult(bres);
		
			Bateau b = new Bateau();
			b.setNumero(bateau.getNumero());
			b.setNom(bateau.getNom());
			b.setNationalite(bateau.getNationalite());
			b.setType(bateau.getType());
			b.setCapitaine(bateau.getCapitaine());
			
			if(res.getRes().equals("SUCCESS")) {
				System.out.println("res sucess");
				dao.modifierBateau(b);
			}
			return res;
		}

}