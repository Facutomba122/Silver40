package com.example.noCountry.Services;

import com.example.noCountry.Config.SecurityConfig;
import com.example.noCountry.Entity.AuthoritiesRoles;
import com.example.noCountry.Entity.User;
import com.example.noCountry.Repository.AuthoritiesRepository;
import com.example.noCountry.Repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AuthenticationService {
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthoritiesRepository authRepository;
    @Autowired
    private SecurityConfig security;
    
    public User registerUser(String username, String password){
        String encodedPassword = security.passwordEncoder().encode(password);
        AuthoritiesRoles userRole = authRepository.findByAuthority("USER").get();
        
        Set<AuthoritiesRoles> authorities = new HashSet<>();
        authorities.add(userRole);
        
        return userRepository.save(new User(username, encodedPassword, authorities));
    }
}
