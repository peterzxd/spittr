package spittr.web;

import java.io.IOException;

import javax.servlet.http.Part;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import spittr.domain.Spitter;
import spittr.repository.SpitterRepository;



@Controller
@RequestMapping("/spitter")
public class SpitterController {
	
	private SpitterRepository spitterRepository;
	
	
	public SpitterRepository getSpittleRepository() {
		return spitterRepository;
	}

	@Autowired
	public SpitterController(SpitterRepository spitterRepository) {
		super();
		this.spitterRepository = spitterRepository;
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String showRegistrationForm(Model model) {
		//璺宠浆鍒癡iew鍓嶏紝蹇呴』鍦∕odel涓斁缃竴涓拰CommandName鐩稿悓鐨勭被鍨�
		model.addAttribute("spitter",new Spitter()); //spitter鍙互閫氳繃绫诲瀷鎺ㄦ柇寰楀嚭锛屾墍浠ュ彲浠ョ渷鐣�
		return "registerForm";
	}
	
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String processRegistration(
			@Valid Spitter spitter, Errors errors) {//涓�鏃﹀彂鐢熼獙璇侀敊璇紝閿欒娑堟伅灏变細鏀惧叆errors
		if(errors.hasErrors()) {
			return "registerForm";
		}
		spitterRepository.save(spitter);
		
		return "redirect:/spitter/" + spitter.getUsername();
	} 
	

	
	@RequestMapping(value="/{username}", method=RequestMethod.GET) 
	public String showSpitterProfile(@PathVariable String username, Model model) {
		Spitter spitter = spitterRepository.findByUsername(username);
		model.addAttribute(spitter);
		return "profile";
	}
}












