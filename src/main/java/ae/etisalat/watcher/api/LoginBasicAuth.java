package ae.etisalat.watcher.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ae.etisalat.watcher.model.AuthenticationBean;

@CrossOrigin
@RestController
@RequestMapping(path = "/user")
public class LoginBasicAuth {
	
	@GetMapping(path = "/login")
	public AuthenticationBean loginUser(){
		return new AuthenticationBean("You are authenticated");
	}
	
}
