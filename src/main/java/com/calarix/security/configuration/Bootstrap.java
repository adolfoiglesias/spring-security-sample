package com.calarix.security.configuration;

import com.calarix.security.domain.Rol;
import com.calarix.security.domain.User;
import com.calarix.security.repository.RolRepository;
import com.calarix.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

public class Bootstrap implements CommandLineRunner {

    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        initUser();
    }

    protected void initUser() {
        if (userRepository.count() != 0) {
            return;
        }
        Rol rol = new Rol();
        rol.name = Rol.ADMIN;
        rolRepository.save(rol);

        rol = new Rol();
        rol.name = Rol.INVITADO;
        rolRepository.save(rol);

        Rol rolAdmin = rolRepository.findByName(Rol.ADMIN);
        Rol rolOp = rolRepository.findByName(Rol.INVITADO);

        User u = new User();
        u.email = "admin";
        u.name = "ADMIN";
        u.password =  passwordEncoder.encode("admin");
        u.roles = new HashSet<>();
        u.roles.add(rolAdmin);
        userRepository.save(u);

        u = new User();
        u.email = "invItado";
        u.name = "INVITADO";
        u.password = passwordEncoder.encode("invitado");
        u.roles = new HashSet<>();
        u.roles.add(rolOp);
        userRepository.save(u);
    }

}
