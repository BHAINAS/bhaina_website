package com.bhainas.backend.Controller;
import com.bhainas.backend.Service.UserService;
import com.bhainas.backend.model.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/login")
    public String loginCheck() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken)
            return "login";
        return "redirect:/logged_in" ;
    }
    @GetMapping("/")
    public String indexPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken)
            return "index";
        return "redirect:/logged_in" ;
    }
    @GetMapping(value = "/signup")
    public String signInPage() {
        return "signup";
    }
    @GetMapping(value = "/contactus")
    public String contactUsPage() {
        return "contactus";
    }
    
    @GetMapping(value = "/logged_in")
    public String loggedInPage() {
        return "logged_in";
    }
    
    @PostMapping(value = "/signup")
    public String register(@RequestParam String firstName, @RequestParam String lastName,@RequestParam String email,@RequestParam String password) {
        UserData ud = new UserData();
        ud.setFirstName(firstName);
        ud.setLastName(lastName);
        ud.setPassword(password);
        ud.setEmail(email);
        userService.registerUser(ud);
        return "login";
    }

}

