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
public class MainController {
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
	}
	
	@GetMapping({"/", "/home"})
	public String home() {
		return "home";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/app")
	public String welcome(Map<String, Object> model, UserInput userInput) {
		model.put("serviceModels", this.serviceModels);
		model.put("deploymentModels", this.deploymentModels);
		return "index";
	}

	@PostMapping("/app")
	public String handleUserInputs(Map<String, Object> model, @Valid UserInput userInput, BindingResult bindingResult) {
		if(userInput.getServiceType().equalsIgnoreCase("saas")&&userInput.getDeploymentType().equalsIgnoreCase("public")) {
			return "redirect:"+mediafire;
		}
		if(userInput.getServiceType().equalsIgnoreCase("saas")&&userInput.getDeploymentType().equalsIgnoreCase("private")) {
			return "redirect:"+googleDrive;
		}
		if(userInput.getServiceType().equalsIgnoreCase("paas")) {
			return "redirect:"+heroku;
		}
		if(userInput.getServiceType().equalsIgnoreCase("iaas")) {
			return "redirect:"+openStack;
		}
		return "redirect:/app";
	}
}
