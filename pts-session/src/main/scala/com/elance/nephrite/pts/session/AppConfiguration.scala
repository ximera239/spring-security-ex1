package com.elance.nephrite.pts.session

import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.web.servlet.view.InternalResourceViewResolver

/**
 * Created by Evgeny Zhoga on 20.02.15.
 */
@Configuration
class AppConfiguration {
  @Bean
  def viewResolver =
    new InternalResourceViewResolver() {
      setPrefix("WEB-INF/views/")
      setSuffix(".jsp")
    }
}
