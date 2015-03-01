package com.elance.nephrite.pts.security.session

import javax.servlet.http.{HttpSession, Cookie, HttpServletResponse, HttpServletRequest}

import com.elance.nephrite.pts.util.Logger
import org.springframework.beans.factory.annotation.{Autowired, Value}
import org.springframework.context.annotation.PropertySource
import org.springframework.security.core.context.{SecurityContextHolder, SecurityContext}
import org.springframework.security.web.context.{HttpSessionSecurityContextRepository, HttpRequestResponseHolder, SecurityContextRepository}
import org.springframework.stereotype.Service

import scala.collection.JavaConversions._

/**
 * Created by Evgeny Zhoga on 27.02.15.
 * Based on data in http://basrikahveci.com/configuring-spring-security-to-persist-the-securitycontext-to-places-other-then-session-between-requests/
 */
@Service
class MemcachedSecurityContextRepository extends HttpSessionSecurityContextRepository with Logger {
  @Value(value = "${session.cookie.name:SessionID}")
  private var COOKIE_NAME: String = _

  @Autowired
  private var delegate: HttpSessionSecurityContextRepository = _

  override def loadContext(requestResponseHolder: HttpRequestResponseHolder): SecurityContext = {
    info(s">>>>>>>>>>> loadContext called")
    val context = delegate.loadContext(requestResponseHolder)
    context
  }

  override def containsContext(request: HttpServletRequest): Boolean = {
    info(s">>>>>>>>>>> containsContext called")
    delegate.containsContext(request)
  }

  override def saveContext(context: SecurityContext, request: HttpServletRequest, response: HttpServletResponse): Unit = {
    info(s">>>>>>>>>>> saveContext called")
    Option(context.getAuthentication).
      map(a => info(
        s"""Authentication:
           |    ${a.getAuthorities.toList},
           |    ${a.getCredentials}
           |    ${a.getDetails}
           |    ${a.getPrincipal}
           |    """.stripMargin))

    delegate.saveContext(context, request, response)
  }
}
