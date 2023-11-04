package com.codingdojo.samplehunters.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.codingdojo.samplehunters.models.LoginUser;
//services
import com.codingdojo.samplehunters.services.SampleService;
import com.codingdojo.samplehunters.services.UserService;

@Controller
public class SampleHunterControllers {

    @Autowired
    private UserService userService;

    @Autowired
    private SampleService sampleService;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "index.jsp";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser,
                            BindingResult result, Model model, HttpSession session){
        User registeredUser = userService.register(newUser, result);

        if(registeredUser == null){
            model.addAttribute("newLogin", new LoginUser());
            return "index.jsp";
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
            return "index.jsp";
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
        return "index.jsp";
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
        model.addAttribute("allSamples", allSamples);
    
        return "home.jsp"; 
    }

    @GetMapping("/addSample")
    public String addSample(HttpSession session, Model model) {
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
    public String showSample(@PathVariable("id") Long id, Model model){
        Sample sample = sampleService.getSingleSample(id);
        model.addAttribute("sample", sample);
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
        return "redirect:/home";
    }
    

    @GetMapping("/samples/{id}/delete")
    public String deleteSample(@PathVariable("id") Long id){
        sampleService.deleteSample(id);
        return "redirect:/home";
    }

    
}
