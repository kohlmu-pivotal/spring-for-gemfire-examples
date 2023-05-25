package sample.client.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sample.client.model.RequestScopedProxyBean;
import sample.client.model.SessionScopedProxyBean;

import java.io.PrintWriter;
import java.io.StringWriter;

import static sample.client.Application.INDEX_TEMPLATE_VIEW_NAME;
import static sample.client.Application.PING_RESPONSE;

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
