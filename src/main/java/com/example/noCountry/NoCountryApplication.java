package com.example.noCountry;

import com.example.noCountry.Entity.AuthoritiesRoles;
import com.example.noCountry.Entity.User;
import com.example.noCountry.Repository.AuthoritiesRepository;
import com.example.noCountry.Repository.UserRepository;
import java.util.HashSet;
import java.util.Set;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class NoCountryApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoCountryApplication.class, args);
	}
        
        @Bean
        public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
                                        .allowedOrigins("http://localhost:4200")
                                        .allowedHeaders("*")
                                        .allowedMethods("GET", "POST", "PUT", "DELETE");
			}
		};
	}
        
        @Bean
        CommandLineRunner run(AuthoritiesRepository authoritiesRepository, UserRepository userRepository, PasswordEncoder passwordEncoder){
            return args -> {
                if (authoritiesRepository.findByAuthority("ADMIN").isPresent())return;
                AuthoritiesRoles adminRole = authoritiesRepository.save(new AuthoritiesRoles("ADMIN"));
                authoritiesRepository.save(new AuthoritiesRoles("USER"));
                
                Set<AuthoritiesRoles> roles = new HashSet<>();
                roles.add(adminRole);
                User admin = new User("admin", passwordEncoder.encode("password"), roles);
                userRepository.save(admin);
            };
        }
}
