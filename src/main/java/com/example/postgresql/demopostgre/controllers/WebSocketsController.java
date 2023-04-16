package com.example.postgresql.demopostgre.controllers;

import com.example.postgresql.demopostgre.beans.Ville;
import com.example.postgresql.demopostgre.websocketbeans.InputMessage;
import com.example.postgresql.demopostgre.websocketbeans.OutputMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.web.server.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class WebSocketsController {

    @GetMapping(value="/testwebsoc")
    public String testwebsoc(){
        return "testwebsocket";
    }


    @GetMapping("/csrf")
    public @ResponseBody
    String getCsrfToken(HttpServletRequest request) {
        CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        return csrf.getToken();
    }

}
