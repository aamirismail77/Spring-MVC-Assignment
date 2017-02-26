package com.bellinf.batch4.mvc;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/")
public class WelcomeController {
	

	@RequestMapping(method=RequestMethod.GET)
	public String printWelcome(ModelMap model){
		model.addAttribute("message", "Hello World, Siva!");
		return "welcome";
	}
	
	@RequestMapping(value="test" ,method=RequestMethod.GET)
	public String printTest(ModelMap model){
		model.addAttribute("message", "Hello World, Test!");
		return "welcome";
	}
	
	@RequestMapping(value="other/something-else" ,method=RequestMethod.GET)
	public String printSomethingElse(ModelMap model){
		model.addAttribute("message", "Hello World, SomethingElse!");
		model.addAttribute("NewAttribute", "Blah Blah");
		
		return "something";
	}
	
	
	

}
