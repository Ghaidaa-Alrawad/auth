package com.jBCrypt.auth.controller;

import com.jBCrypt.auth.Repositories.SiteUserRepo;
import com.jBCrypt.auth.model.SiteUserModel;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AuthenticationController {

    @Autowired
    SiteUserRepo siteUserRepo;

    @GetMapping("/login")
    public String getLogInPage() {
        return "/login.html";
    }

    @GetMapping("/signup")
    public String getSingUpPage(){
        return "/signup.html";
    }

    @PostMapping("/signup")
    public RedirectView signUp(String username, String password){
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
        SiteUserModel siteUser = new SiteUserModel(username, hashedPassword);

        siteUserRepo.save(siteUser);

        return new RedirectView("/login");
    }

    @PostMapping("/login")
    public RedirectView logIn(String username, String password, HttpServletRequest request){
        SiteUserModel userFromDB = siteUserRepo.findByUsername(username);

        if ((userFromDB == null)){
            return new RedirectView("/login");
        }

         if(userFromDB.getUsername().equals(username) && (BCrypt.checkpw(password, userFromDB.getPassword()))){
             HttpSession session = request.getSession();
             session.setAttribute("username",userFromDB.getUsername());
             return new RedirectView("/post");
         }
        return new RedirectView("/login");
    }

    @PostMapping("/logout")
    public RedirectView logOut(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return new RedirectView("/login");
    }

}
