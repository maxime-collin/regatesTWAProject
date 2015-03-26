package control;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import bean.User;

@Controller
public class UserController {
	
	@Resource
	Dao dao;

	@RequestMapping(value="/listerUsers",method=RequestMethod.GET)
	public @ResponseBody List <User> listerUser() {
		System.out.println("listerUsers");
		return dao.listerUsers();
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
	
	@RequestMapping(value="/enregistrerUser",method=RequestMethod.GET)
	public String showForm(){
		return "enregistrerUser";
	}
	
	@RequestMapping(value="/enregistrerUser",method=RequestMethod.POST)
	public @ResponseBody Resultat enregistrerUser(
			@RequestBody @Valid User user, BindingResult bres) {
		System.out.println("enregistrerUser nom = "+user.getNom());
		Resultat res = convertBindingResult(bres);
	
		User u = new User();
		u.setNom(user.getNom());
		u.setPrenom(user.getPrenom());
		u.setIdentifiant(user.getIdentifiant());
		u.setMotDePasse(user.getMotDePasse());
		u.setNumeroLicence(user.getNumeroLicence());
		u.setUrl(user.getUrl());
		
		if(res.getRes().equals("SUCCESS")) {
			System.out.println("res sucess");
			dao.enregistrerUser(u);
		}
		return res;
	}
	
	@RequestMapping(value="/supprimerUser",method=RequestMethod.POST)
	public @ResponseBody Resultat supprimerUser(
			@RequestBody @Valid User user, BindingResult bres) {
		System.out.println("supprimerUser nom = "+user.getNom());
		Resultat res = convertBindingResult(bres);
	
		User u = new User();
		u.setNom(user.getNom());
		u.setPrenom(user.getPrenom());
		u.setIdentifiant(user.getIdentifiant());
		u.setMotDePasse(user.getMotDePasse());
		u.setNumeroLicence(user.getNumeroLicence());
		u.setUrl(user.getUrl());
		u.setAdmin(user.getAdmin());
		
		if(res.getRes().equals("SUCCESS")) {
			System.out.println("res sucess");
			dao.supprimerUser(u);
		}
		return res;
	}
	
	 @RequestMapping(value="/modifierUser", params= "id", method=RequestMethod.GET)
	 public @ResponseBody User returnUser(@RequestParam("id") Integer id){
		 System.out.println("modifierUser id = " + id);
		 User user = dao.returnUser(id);
		 return user;
	 }
	 	 
	 @RequestMapping(value="/modifierUser",method=RequestMethod.POST)
		public @ResponseBody Resultat modifierUser(
				@RequestBody @Valid User user, BindingResult bres) {
			System.out.println("modifierUser nom = "+user.getNom());
			Resultat res = convertBindingResult(bres);
		
			User u = new User();
			u.setNom(user.getNom());
			u.setPrenom(user.getPrenom());
			u.setIdentifiant(user.getIdentifiant());
			u.setMotDePasse(user.getMotDePasse());
			u.setNumeroLicence(user.getNumeroLicence());
			u.setUrl(user.getUrl());
			u.setAdmin(user.getAdmin());
			
			if(res.getRes().equals("SUCCESS")) {
				System.out.println("res sucess");
				dao.modifierUser(u);
			}
			return res;
		}

	 @RequestMapping(value="/connectUser",method=RequestMethod.POST)
		public @ResponseBody Resultat connectUser(
				@RequestBody @Valid Object connection, BindingResult bres) {
		 		
		 	JSONObject json = (JSONObject) connection;
		 	String identifiant = (String) json.get("identifiant");
		 	System.out.println("connectUser id "+identifiant);
		 	String password = (String) json.get("password");
		 	
			Resultat res = convertBindingResult(bres);
			
			if(res.getRes().equals("SUCCESS")) {
				System.out.println("res sucess");
				dao.connectUser(identifiant, password);
			}
			return res;
		}
}