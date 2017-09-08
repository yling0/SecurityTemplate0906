package me.yling.securitytemplate.controllers;

import me.yling.securitytemplate.models.Role;
import me.yling.securitytemplate.models.User;
import me.yling.securitytemplate.repositories.RoleRepo;
import me.yling.securitytemplate.repositories.UserRepo;
import me.yling.securitytemplate.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    UserRepo userRepo;

    @RequestMapping("/")
    public String index ()
    {
        return "index";
    }

    @RequestMapping("/addroles")
    public @ResponseBody String addRoles()
    {
        Role userrole = new Role();
        userrole.setRole("USER");
        roleRepo.save(userrole);

        Role adminrole = new Role();
        adminrole.setRole("ADMIN");
        roleRepo.save(adminrole);

        return "Roles added";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationPage (Model model)
    {
        model.addAttribute("user", new User());
        return "registration";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistrationPage (@Valid @ModelAttribute("user") User user, BindingResult result, Model model)
    {
        model.addAttribute("user", user);

        if (result.hasErrors())
        {
            return "registration";
        } else
        {
            userService.saveUser(user);
            model.addAttribute("message", "User Account Successfully Created.");
        }

        return "index";
    }

    @RequestMapping(value = "/registeradmin", method = RequestMethod.GET)
    public String showRegistrationadminPage (Model model)
    {
        model.addAttribute("user", new User());
        return "registrationadmin";
    }

    @RequestMapping(value = "/registeradmin", method = RequestMethod.POST)
    public String processRegistrationadminPage (@Valid @ModelAttribute("user") User user, BindingResult result, Model model)
    {
        model.addAttribute("user", user);

        if (result.hasErrors())
        {
            return "registrationadmin";
        } else
        {
            userService.saveAdmin(user);
            model.addAttribute("message", "User Account Successfully Created.");
        }

        return "index";
    }

    @RequestMapping("/login")
    public String login()
    {
        return "login";
    }

    @RequestMapping("/welcome")
    public String secure()
    {
        return "welcome";
    }


}
