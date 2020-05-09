package com.calarix.security.repository;

import com.calarix.security.domain.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

    public Rol findByName(String name);
}
