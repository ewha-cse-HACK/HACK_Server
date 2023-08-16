package com.hack.hack_server.Dalle.Service;

import com.hack.hack_server.Dalle.Dto.CreateImageRequest;
import com.hack.hack_server.Dalle.Service.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AIService {

    private OpenAiService openAiService;

    public String generatePicture(String prompt) {
        CreateImageRequest createImageRequest = CreateImageRequest.builder()
                .prompt(prompt)
                .size("512x512")
                .n(1)
                .build();

        String url = openAiService.createImage(createImageRequest).getData().get(0).getUrl();
        return url;
    }
}