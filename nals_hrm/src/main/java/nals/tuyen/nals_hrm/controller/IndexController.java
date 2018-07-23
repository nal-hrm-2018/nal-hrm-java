package nals.tuyen.nals_hrm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

  @RequestMapping(value = "/show-login", method = RequestMethod.GET)
  public String login() {
    return "login";
  }

  @RequestMapping(value = "/403", method = RequestMethod.GET)
  public String accessDenied() {
    return "403";
  }
}

