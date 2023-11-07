package com.codingdojo.samplehunters.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


//repositories
import com.codingdojo.samplehunters.repositories.SampleRepository;
import com.codingdojo.samplehunters.repositories.UserRepository;
//models
import com.codingdojo.samplehunters.models.Sample;
import com.codingdojo.samplehunters.models.User;
import com.codingdojo.samplehunters.models.Like;
import com.codingdojo.samplehunters.models.LoginUser;
//services
import com.codingdojo.samplehunters.services.SampleService;
import com.codingdojo.samplehunters.services.UserService;
import com.codingdojo.samplehunters.services.LikeService;

@Controller
public class SampleHunterControllers {

    @Autowired
    private UserService userService;

    @Autowired
    private SampleService sampleService;

    @Autowired
    private LikeService likeService;

    @GetMapping("/")
    public String index(Model model){
        return "index.jsp";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser,
                            BindingResult result, Model model, HttpSession session){
        User registeredUser = userService.register(newUser, result);

        if(registeredUser == null){
            model.addAttribute("newLogin", new LoginUser());
            return "login.jsp";
        }
        session.setAttribute("user_id", registeredUser.getId());
        return "redirect:/home";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin,
                        BindingResult result, Model model, HttpSession session){
        
        User loggedInUser = userService.login(newLogin, result);

        if (loggedInUser == null){
            model.addAttribute("newUser", new User());
            return "login.jsp";
        }
        session.setAttribute("user_id", loggedInUser.getId());
        return "redirect:/home";
    }

    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(HttpSession session, Model model) {
        model.addAttribute("newLogin", new LoginUser());
        model.addAttribute("newUser", new User());
        return "login.jsp";
    }

    //================================================================================================

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("user_id");
    
        if (userId != null) {
            User user = userService.findById(userId);
            model.addAttribute("userName", user.getUserName());
        }
    
        List<Sample> allSamples = sampleService.getAllSamples();
    
        for (Sample sample : allSamples) {
            int likesCount = sampleService.getLikesCount(sample.getId());
            sample.setLikesCount(likesCount);
        }
    
        model.addAttribute("allSamples", allSamples);
    
        return "home.jsp";
    }
    

    @GetMapping("/addSample")
    public String addSample(HttpSession session, Model model) {
        if (session.getAttribute("user_id") == null) {
            return "redirect:/login";
        }
        Long userId = (Long) session.getAttribute("user_id");
        User user = userService.findById(userId);
        model.addAttribute("user", user);
        model.addAttribute("newSample", new Sample());
        return "addSample.jsp";
    }
    
    @PostMapping("/addSample")
    public String addSample(@Valid @ModelAttribute("newSample") Sample newSample,
                            BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            Long userId = (Long) session.getAttribute("user_id");
            User user = userService.findById(userId);
            model.addAttribute("user", user);
            return "addSample.jsp";
        }
    
        Long userId = (Long) session.getAttribute("user_id");
        newSample.setUser(userService.findById(userId));
        sampleService.createSample(newSample);
    
        Long sampleId = newSample.getId();
    
        return "redirect:/samples/" + sampleId;
    }


    @GetMapping("/samples/{id}")
    public String showSample(@PathVariable("id") Long id, Model model, HttpSession session) {
        Sample sample = sampleService.getSingleSample(id);
        
        if (session.getAttribute("user_id") != null) {
            Long userId = (Long) session.getAttribute("user_id");
            boolean hasUserLikedSample = likeService.hasUserLikedSample(userId, id);
            model.addAttribute("hasUserLikedSample", hasUserLikedSample);
        }
        int likesCount = sampleService.getLikesCount(id);
        model.addAttribute("sample", sample);
        model.addAttribute("likesCount", likesCount);
        return "showSample.jsp";
    }

    @GetMapping("/samples/{id}/edit")
    public String editSample(@PathVariable("id") Long id, Model model) {
        Sample sample = sampleService.getSingleSample(id);
        model.addAttribute("sample", sample);
        return "editSample.jsp";
    }
    
    @PostMapping("/samples/{id}/edit")
    public String updateSample(@Valid @ModelAttribute("sample") Sample sample, BindingResult result, HttpSession session) {
        if (result.hasErrors()) {
            return "editSample.jsp";
        }
        
        Long userId = (Long) session.getAttribute("user_id");
        User user = userService.findById(userId);
        sample.setUser(user);
        sampleService.updateSample(sample);
        
        return "redirect:/samples/" + sample.getId();
    }
    

    @GetMapping("/samples/{id}/delete")
    public String deleteSample(@PathVariable("id") Long id){
        sampleService.deleteSample(id);
        return "redirect:/home";
    }

    //stretch
    @GetMapping("/nextSample/{currentSampleId}")
    public String scrollToNextSample(@PathVariable("currentSampleId") Long currentSampleId, Model model, HttpSession session) {
        Sample currentSample = sampleService.getSingleSample(currentSampleId);
        Sample nextSample = sampleService.getNextSample(currentSample);
        
        if (nextSample == null) {
            return "redirect:/home"; // or show an appropriate message
        } else {
            model.addAttribute("sample", nextSample);
            return "showSample.jsp";
        }
    }
    
    @GetMapping("/previousSample/{currentSampleId}")
    public String scrollToPreviousSample(@PathVariable("currentSampleId") Long currentSampleId, Model model, HttpSession session) {
        Sample currentSample = sampleService.getSingleSample(currentSampleId);
        Sample previousSample = sampleService.getPreviousSample(currentSample);
    
        if (previousSample == null) {
            return "redirect:/home"; // or show an appropriate message
        } else {
            model.addAttribute("sample", previousSample);
            return "showSample.jsp";
        }
    }

    @PostMapping("/like/{sampleId}")
    public String likeSample(@PathVariable("sampleId") Long sampleId, HttpSession session) {
        if (session.getAttribute("user_id") == null) {
            return "redirect:/login";
        }
        Long userId = (Long) session.getAttribute("user_id");
        likeService.likeSample(sampleId, userId);
        sampleService.updateSampleLikesCount(sampleId);
        return "redirect:/samples/" + sampleId;
    }

}
