package com.elance.nephrite.pts.session.mvc

import java.text.DateFormat
import java.util.{Date, Locale}

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.{PathVariable, RequestMethod, RequestMapping}

/**
 * Created by Evgeny Zhoga on 20.02.15.
 * based on http://spring.io/blog/2011/01/04/green-beans-getting-started-with-spring-mvc/
 */
@Controller
class HomeController {
  @RequestMapping(value = Array("/home"),method = Array(RequestMethod.GET, RequestMethod.HEAD))
  def home() = "home"

  @RequestMapping(value = Array("/login"),method = Array(RequestMethod.GET, RequestMethod.HEAD))
  def login() = "login"

  @RequestMapping(value = Array("/logout"),method = Array(RequestMethod.GET, RequestMethod.HEAD))
  def logout() = "logout"

  @RequestMapping(value = Array("/denied"),method = Array(RequestMethod.GET, RequestMethod.HEAD))
  def denied() = "denied"

  @RequestMapping(value = Array("/emp/get/{id}"), method = Array(RequestMethod.GET))
  def emp(locale: Locale, model: Model, @PathVariable("id") id: Int) = {
    val date = new Date()
    val dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale)

    val formattedDate = dateFormat.format(date)

    model.addAttribute("serverTime", formattedDate )
    model.addAttribute("id", id)
    model.addAttribute("name", "Pankaj")

    "employee"
  }
}
