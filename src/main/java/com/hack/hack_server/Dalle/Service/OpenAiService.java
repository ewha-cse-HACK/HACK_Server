package com.hack.hack_server.Dalle.Service;

import com.hack.hack_server.Dalle.Dto.CreateImageRequest;
import com.hack.hack_server.Dalle.OpenAiApi;

import com.hack.hack_server.Dalle.Dto.ImageResult;

public class OpenAiService {

    private static final String BASE_URL = "https://api.openai.com/";

    private final OpenAiApi api;

    public OpenAiService(final OpenAiApi api) {
        this.api = api;
    }

    public ImageResult createImage(CreateImageRequest request) {
        return api.createImage(request);
    }
}