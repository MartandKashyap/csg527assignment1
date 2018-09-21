package com.mecs.csg527assignment1.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.mecs.csg527assignment1.model.UserInput;

@Controller
public class IndexController {
	// inject via application.properties
	@Value("${links.mediafire}")
	private String mediafire;
	@Value("${links.google}")
	private String googleDrive;
	@Value("${links.heroku}")
	private String heroku;
	@Value("${links.openstack}")
	private String openStack;
	
	private List<String> serviceModels;
	private List<String> deploymentModels;

	{
		serviceModels = new ArrayList<>();
		deploymentModels = new ArrayList<>();

		serviceModels.add("Saas");
		serviceModels.add("Paas");
		serviceModels.add("Iaas");

		deploymentModels.add("Public");
		deploymentModels.add("Private");
		
//		mediafire = "";
		/*deploymentModels.add("Community");
		deploymentModels.add("Hybrid");*/
	}

	@GetMapping("/")
	public String welcome(Map<String, Object> model, UserInput userInput) {
		model.put("serviceModels", this.serviceModels);
		model.put("deploymentModels", this.deploymentModels);
		return "index";
	}

	@PostMapping("/")
	public String handleUserInputs(Map<String, Object> model, @Valid UserInput userInput, BindingResult bindingResult) {
		if(userInput.getServiceType().equalsIgnoreCase("saas")&&userInput.getDeploymentType().equalsIgnoreCase("public")) {
//			model.put("link", mediafire);
			return "redirect:"+mediafire;
//			return "loading";
		}
		if(userInput.getServiceType().equalsIgnoreCase("saas")&&userInput.getDeploymentType().equalsIgnoreCase("private")) {
			return "redirect:"+googleDrive;
//			model.put("link", mediafire);
//			return "loading";
		}
		if(userInput.getServiceType().equalsIgnoreCase("paas")) {
			return "redirect:"+heroku;
//			model.put("link", mediafire);
//			return "loading";
		}
		if(userInput.getServiceType().equalsIgnoreCase("iaas")) {
			return "redirect:"+openStack;
//			model.put("link", mediafire);
//			return "loading";
		}
		return "redirect:/";
	}
}
