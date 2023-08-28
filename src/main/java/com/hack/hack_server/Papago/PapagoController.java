
package com.hack.hack_server.Papago;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/papago")
@RequiredArgsConstructor
public class PapagoController {

    private final NaverTransService naverTransService;

    @PostMapping("/translate")
    public String translate(@RequestBody String prompt) {
        return naverTransService.getTransSentence(prompt);
    }

}