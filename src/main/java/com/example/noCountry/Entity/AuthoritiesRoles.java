package com.example.noCountry.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.UUID;
import org.springframework.security.core.GrantedAuthority;

@Entity
public class AuthoritiesRoles implements GrantedAuthority{

    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;
    
    private String authority;

    public AuthoritiesRoles() {
    }

    public AuthoritiesRoles(String authority) {
        this.authority = authority;
    }

    public AuthoritiesRoles(UUID id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    
    @Override
    public String getAuthority() {
        return authority;
    }
    
    public void setAuthority(String authorities){
        this.authority = authorities; 
    }
    
}
