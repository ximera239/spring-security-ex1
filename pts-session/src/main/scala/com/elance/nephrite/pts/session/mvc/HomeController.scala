package com.elance.nephrite.pts.session.mvc

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMethod, RequestMapping}

/**
 * Created by Evgeny Zhoga on 20.02.15.
 * based on http://spring.io/blog/2011/01/04/green-beans-getting-started-with-spring-mvc/
 */
@Controller
class HomeController {
  @RequestMapping(
    value = Array("/"),
    method = Array(RequestMethod.GET, RequestMethod.HEAD)
  )
  def home() = "home"
}
