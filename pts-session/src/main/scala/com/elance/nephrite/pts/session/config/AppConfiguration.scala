package com.elance.nephrite.pts.session.config

import org.springframework.context.annotation.{Bean, ComponentScan, Configuration, PropertySource}
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.view.InternalResourceViewResolver

/**
 * Usefull: http://www.luckyryan.com/2013/02/07/migrate-spring-mvc-servlet-xml-to-java-config/
 * Created by Evgeny Zhoga on 20.02.15.
 */
@Configuration
@PropertySource(value = Array("classpath:security.properties"), ignoreResourceNotFound = true)
@ComponentScan(Array("com.elance.nephrite.pts.session.mvc"))
@EnableWebMvc
class AppConfiguration {
  @Bean
  def viewResolver =
    new InternalResourceViewResolver() {
      setPrefix("WEB-INF/views/")
      setSuffix(".jsp")
    }
}
