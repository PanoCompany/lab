package assignment.controller;

import assignment.entity.User;
import assignment.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/manager/user")
    public String listUser(Model model){
        model.addAttribute("users", userService.getAll());
        return "manager/user-list";
    }

    @GetMapping("/manager/user/add")
    public String formAddUser(Model model){
        model.addAttribute("user", new User());
        return "manager/user-add";
    }

    @PostMapping("/manager/user/add")
    public String createUser(User user){
        user.setPassword("123456"); //default password
        userService.save(user);
        return "redirect:/manager/user";
    }

    @PostMapping("/manager/user/update")
    public String updateUser(User user){
        userService.update( user);
        return "redirect:/manager/user";
    }

    @GetMapping("/manager/user/edit/{id}")
    public String editUser(@PathVariable long id, Model model){
        User userEdit = userService.getById(id);

        model.addAttribute("user", userEdit);
        return "manager/user-edit";
    }


    @GetMapping("/manager/user/delete/{id}")
    public String deleteUser(@PathVariable long id){
        userService.deleteById(id);
        return "redirect:/manager/user";
    }

}
