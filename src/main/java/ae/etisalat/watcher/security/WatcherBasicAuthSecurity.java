package ae.etisalat.watcher.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class WatcherBasicAuthSecurity extends WebSecurityConfigurerAdapter{
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.csrf().disable();
		http.authorizeRequests((requests) -> {
			requests.antMatchers(HttpMethod.OPTIONS,"/**").permitAll();
			requests.anyRequest().authenticated();
		});
		//http.formLogin();
		http.httpBasic();
	}

}
