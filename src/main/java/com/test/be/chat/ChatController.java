package com.test.be.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @PostMapping("/chat")
    public String chat(@RequestBody String message) {
       return chatClient.prompt()
               .system("내가 올려 놓은 엑셀파일을 참조해서 너가 상담사가 된 것처럼 나에게 올바른 해답 3가지를 줘")
               .user(message)
               .call()
               .content();
    }
}
