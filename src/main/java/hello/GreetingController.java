package hello;

import Entity.User;
import Service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

    UserServiceImpl userService;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        String t = "dfgsdf";
        User user = new User("df");
        userService.addUser(user);
        model.addAttribute("name", name);
        model.addAttribute("t", t);
        return "greeting";
    }

}
