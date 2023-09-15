package com.example.noCountry.Services;

import com.example.noCountry.Config.SecurityConfig;
import com.example.noCountry.DTO.UserDTO;
import com.example.noCountry.Entity.AuthoritiesRoles;
import com.example.noCountry.Entity.User;
import com.example.noCountry.Repository.UserRepository;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private SecurityConfig securityConfig;

    public List<User> viewAll(){
        return userRepository.findAll();
    }
    
    public boolean validateFields(UserDTO validateUser) throws IllegalArgumentException, Exception{
        
        Class<?> classPublication = validateUser.getClass();
        Field[] publicationFields =  classPublication.getDeclaredFields();
        for (Field auxField : publicationFields){
            auxField.setAccessible(true);
            try {
                if (auxField.get(validateUser) == null){
                    return false;
                }
            } catch (IllegalAccessException | IllegalArgumentException e){
                throw e;
            }
        }
        return true;
    }
    
    public User initializateUser(UserDTO userDTO) throws Exception{
        if (validateFields(userDTO)){
            User newUser = new User(
                    userDTO.getEmail(),
                    userDTO.getPassword(),
                    userDTO.getFirstname(),
                    userDTO.getLastname(),
                    userDTO.getContactNum(),
                    userDTO.getRole(),
                    userDTO.getLocation(),
                    userDTO.getCountry()
            );
            
            return newUser;
        }
        return null;
    }

    
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("username not found"));
    }
}
