package com.calarix.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PreAuthorize("hasRole('ADMIN','INVITADO')")
    @GetMapping("users")
    public ModelAndView list(){
        List<User> list = userRepository.findAll();
        return new ModelAndView("users", "users", list);
    }
    @GetMapping("users/new")
    public String newUser(){
        return "nwUser";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("users/new")
    public String newUser(@Valid User user){
        userRepository.save(user);
        return "redirect:users";
    }

    @PreAuthorize("hasRole('ADMIN','INVITADO')")
    @GetMapping("users/show")
    public ModelAndView showUser(@RequestParam("id") Long id){
        Optional<User> userOptional = userRepository.findById(id);
        return new ModelAndView("showUser", "user", userOptional.get());
    }

}
