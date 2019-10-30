package main.logic.controllers.adapters.springcontrollers;

import main.logic.controllers.businesscontrollers.InitTableController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author F0urth
 */
@Controller
public class SpringController {

    private InitTableController logicController;

    @Autowired
    public SpringController(String url) {
        this.logicController = new InitTableController(url);
    }

    @RequestMapping("check")
    public ModelAndView generateTable() {
        return logicController.createTable();
    }
}
