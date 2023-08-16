package com.hack.hack_server.Dalle;

import com.hack.hack_server.Dalle.Dto.CreateImageRequest;
import com.hack.hack_server.Dalle.Dto.ImageResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.reactivex.Single;

import java.time.LocalDate;

public interface OpenAiApi {
    @POST("/v1/images/generations")
    Single ImageResult createImage(@BODY CreateImageRequest request);


}