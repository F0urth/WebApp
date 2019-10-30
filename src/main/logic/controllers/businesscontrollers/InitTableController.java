package main.logic.controllers.businesscontrollers;

import main.logic.services.ResponseParser;
import main.logic.services.WebReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * Annotation add we thinking about reflection type verification
 *
 * @author F0urth
 */
@BusinessController
public class InitTableController {

    private WebReader reader;
    private ResponseParser parser;

    public InitTableController(String url) {
        this.reader = new WebReader(url);
        this.parser = new ResponseParser();
    }

    public ModelAndView createTable() {
        ModelAndView mav = new ModelAndView();
        final ResponseParser.FullParseData fullParseData = parser.parsNBPTableAResponse(reader.read());
        mav.addObject("result", fullParseData);
        mav.setViewName("viewresults");
        return mav;
    }
}
