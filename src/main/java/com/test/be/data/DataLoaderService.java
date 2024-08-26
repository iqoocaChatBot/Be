package com.test.be.data;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DataLoaderService {

    @Value("classpath:/src/main/resources/static/아이쿠카 고객응대 메뉴얼.pdf")
    private Resource resource;

    private VectorStore vectorStore;

    public void load() {
        PagePdfDocumentReader pdfReader = new PagePdfDocumentReader(resource);

        TokenTextSplitter tokenTextSplitter = new TokenTextSplitter();
        vectorStore.accept(tokenTextSplitter.apply(pdfReader.get()));
    }


}
