package com.javayh.omni.ai.chat.chat.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.javayh.omni.ai.chat.chat.ChatRequestDto;
import com.javayh.omni.ai.chat.chat.service.ChatAIService;

import reactor.core.publisher.Flux;

/**
 * <p>
 *
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2025-07-15
 */
@RestController
@RequestMapping(value = "/chat")
public class ChatAIController {

    @Autowired
    private ChatAIService chatAIService;

    @PostMapping(value = "/ask")
    public String ask(@RequestBody ChatRequestDto chatRequestDto) {
        return chatAIService.ask(chatRequestDto);
    }

    @PostMapping(value = "/ask/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> askStream(@RequestBody ChatRequestDto chatRequestDto) {
        return chatAIService.askStream(chatRequestDto);
    }


}
