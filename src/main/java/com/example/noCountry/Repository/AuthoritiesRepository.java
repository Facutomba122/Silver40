package com.example.noCountry.Repository;

import com.example.noCountry.Entity.AuthoritiesRoles;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthoritiesRepository extends JpaRepository<AuthoritiesRoles, UUID> {
    Optional<AuthoritiesRoles> findByAuthority(String authority);
}
