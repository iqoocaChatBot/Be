package com.test.be.chat;

import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.Embedding;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.embedding.EmbeddingRequest;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.ai.openai.OpenAiEmbeddingOptions;
import org.springframework.ai.vectorstore.ChromaVectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.EmptyStackException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmbeddingController {

    private final EmbeddingModel embeddingModel;
    private final ChromaVectorStore vectorStore;

    @Autowired
    public EmbeddingController(EmbeddingModel embeddingModel, ChromaVectorStore vectorStore) {
        this.embeddingModel = embeddingModel;
        this.vectorStore = vectorStore;
    }


    @PostMapping("/chat")
    public Embedding chat(@RequestBody String message) {
        EmbeddingResponse embeddingResponse = this.embeddingModel.embedForResponse(List.of(message));
        return embeddingResponse.getResult();
    }

    @PostMapping("/search")
    public List<Document> search(@RequestBody String message) {
        return vectorStore.similaritySearch(message);
    }
}
