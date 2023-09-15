
package com.example.noCountry.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.Data;
@Entity
@Data
public class User implements UserDetails {
    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;
    @Column(unique = true)
    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<AuthoritiesRoles> authorities;
    
    private String firstname;
    private String lastname;
    private Integer contactNum;
    @Enumerated(EnumType.STRING)
    private Role role;
    private Location location;
    private String country; 

    public User() {
        super();
        this.authorities = new HashSet<AuthoritiesRoles>();
    }

    public User(UUID id, String username, String password, String firstname, String lastname, Integer contactNum, Role role, Location location, String country) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.contactNum = contactNum;
        this.role = role;
        this.location = location;
        this.country = country;
    }

    public User(String username, String password, Set<AuthoritiesRoles> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }
    
    

    public User(String username, String password, String firstname, String lastname,
            Integer contactNum, Role role, Location location, String country) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.contactNum = contactNum;
        this.role = role;
        this.location = location;
        this.country = country;
    }
    
    
    
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    public Integer getContactNum() {
        return contactNum;
    }

    public void setContactNum(Integer contactNum) {
        this.contactNum = contactNum;
    }
    
    private String getFirstname(){
        return firstname;
    }
    
    private void setFirstname(String firstname){
        this.firstname = firstname;
    }
    
    private String getLastname(){
        return lastname;
    }
    
    private void setLastname(String lastname){
        this.lastname = lastname;
    }
    
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }
    
    public void setAuthorities(Set<AuthoritiesRoles> authorities){
        this.authorities = authorities;
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
