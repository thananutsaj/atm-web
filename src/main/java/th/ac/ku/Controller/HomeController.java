package th.ac.ku.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/home")
    public String getHomepage(Model model){
        model.addAttribute("greeting","Hello Stranger");
        return "home" ;// คืนค่าไฟล์ home . html
    }
}
