package com.javayh.omni.ai.chat.chat.api;


import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ChatController {
    @GetMapping("/chat")
    public String chat(Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        return "chat"; // chat.html
    }

    @GetMapping({"/", "/login"})
    public String login() {
        return "login";
    }
}
