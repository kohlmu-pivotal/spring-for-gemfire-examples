package dev.gemfire.spring.session.beanscopes.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import dev.gemfire.spring.session.beanscopes.beans.RequestScopedProxyBean;
import dev.gemfire.spring.session.beanscopes.beans.SessionScopedProxyBean;

import java.io.PrintWriter;
import java.io.StringWriter;

import static dev.gemfire.spring.session.beanscopes.Application.INDEX_TEMPLATE_VIEW_NAME;
import static dev.gemfire.spring.session.beanscopes.Application.PING_RESPONSE;

@Controller
public class SessionCountController {
    @Autowired
    private RequestScopedProxyBean requestBean;

    @Autowired
    private SessionScopedProxyBean sessionBean;

    @ExceptionHandler
    @ResponseBody
    public String errorHandler(Throwable error) {
        StringWriter writer = new StringWriter();
        error.printStackTrace(new PrintWriter(writer));
        return writer.toString();
    }

    @GetMapping(path = "/ping")
    @ResponseBody
    public String ping() {
        return PING_RESPONSE;
    }

    @GetMapping(path = "/counts")
    public String requestAndSessionInstanceCount(HttpServletRequest request, HttpSession session, Model model) { // <6>

        model.addAttribute("sessionId", session.getId());
        model.addAttribute("sessionCount", this.sessionBean.getCount());
        model.addAttribute("requestCount", this.requestBean.getCount());

        return INDEX_TEMPLATE_VIEW_NAME;
    }
}
