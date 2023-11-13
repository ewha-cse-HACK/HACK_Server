package com.hack.hack_server.Dalle.Service;

import com.theokanning.openai.image.CreateImageRequest;
import com.theokanning.openai.service.OpenAiService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AIService {

    @Resource(name = "getOpenAiService")
    private final OpenAiService openAiService;

    public String generatePicture(String prompt) {
        CreateImageRequest createImageRequest = CreateImageRequest.builder()
                .prompt(prompt)
                .size("1024x1024")
                .n(1)
                .model("dall-e-3")
                .responseFormat("b64_json") //b64_json 포맷으로의 반환을 위해 이 코드 추가!
                .build();

//        String url = openAiService.createImage(createImageRequest).getData().get(0).getUrl();

        //이미지 URL 대신 base64-encoded JSON을 리턴하는 것으로 변경!
        String b64 = openAiService.createImage(createImageRequest).getData().get(0).getB64Json();
        return b64;
    }
}