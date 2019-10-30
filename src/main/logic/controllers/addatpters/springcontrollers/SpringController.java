package main.logic.controllers.addatpters.springcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author F0urth
 */
@Controller
public class SpringController {

    @RequestMapping("check")
    public ModelAndView generateTable() {
        ModelAndView mav = new ModelAndView();

        return mav;
    }
}
