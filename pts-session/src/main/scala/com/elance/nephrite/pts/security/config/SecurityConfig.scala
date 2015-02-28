package com.elance.nephrite.pts.security.config

import com.elance.nephrite.pts.security.session.MemcachedSecurityContextRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration}
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.{EnableWebSecurity, WebSecurityConfigurerAdapter}
import org.springframework.security.web.context.HttpSessionSecurityContextRepository

/**
 * Created by Evgeny Zhoga on 27.02.15.
 */
@Configuration
@EnableWebSecurity
@ComponentScan(Array("com.elance.nephrite.pts.security.session"))
class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Bean
  def securityContextRepository(): HttpSessionSecurityContextRepository =
    new HttpSessionSecurityContextRepository() {
      setAllowSessionCreation(false)
    }

  override def configure(auth: AuthenticationManagerBuilder):Unit = {
    auth.
      inMemoryAuthentication().
        withUser("admin").password("admin").roles("ADMIN")
  }

  @Bean
  override def authenticationManagerBean = super.authenticationManagerBean()

  @Autowired
  var memcachedSecurityContextRepository: MemcachedSecurityContextRepository = _

  override def configure(http: HttpSecurity): Unit = {
    http.
      csrf().disable().
      authorizeRequests().
        antMatchers("/home").access("hasRole('ROLE_ADMIN')").
        anyRequest().anonymous().and().
      securityContext().securityContextRepository(memcachedSecurityContextRepository).and().
      formLogin().
        loginPage("/login").failureUrl("/denied").defaultSuccessUrl("/home").and().
      logout().invalidateHttpSession(true).logoutSuccessUrl("/login").and().
      sessionManagement().invalidSessionUrl("/login").maximumSessions(1).expiredUrl("/login")

  }
}