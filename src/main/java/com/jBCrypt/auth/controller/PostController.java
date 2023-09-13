package com.jBCrypt.auth.controller;

import com.jBCrypt.auth.Repositories.PostRepo;
import com.jBCrypt.auth.Repositories.SiteUserRepo;
import com.jBCrypt.auth.model.PostModel;
import com.jBCrypt.auth.model.SiteUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class PostController {

    @Autowired
    PostRepo postRepo;

    @Autowired
    SiteUserRepo siteUserRepo;

    @GetMapping("/post")
    public String posts(Model postM, HttpServletRequest request){
        HttpSession session = request.getSession();
        String username = session.getAttribute("username").toString();

        postM.addAttribute("username", username);

        List<PostModel> posts = postRepo.findAll();
        postM.addAttribute("posts", posts);

        return "post.html";
    }

//    @GetMapping("/post")
//    public String userPosts(Model userPostM){
//        List<PostModel> posts = postRepo.findAll();
////        System.out.println(posts.toString());
//        userPostM.addAttribute("posts", posts);
//        return "";
//    }

    @PostMapping("/post/createPosts")
    public RedirectView createPosts(String textContent, HttpServletRequest request){
        HttpSession session = request.getSession();
        String username = session.getAttribute("username").toString();

        SiteUserModel user = siteUserRepo.findByUsername(username);

        PostModel post = new PostModel(user, textContent);
        postRepo.save(post);

        return new RedirectView("/post");
    }

}
