package com.hack.hack_server.ChatGpt.Service;

import com.hack.hack_server.Authentication.PrincipalDetails;
import com.hack.hack_server.ChatGpt.Dto.*;
import com.hack.hack_server.ChatGpt.ChatGptConfig;
import com.hack.hack_server.Entity.*;
import com.hack.hack_server.Repository.JournalRepository;
import com.hack.hack_server.Repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatGptService {
    private static final RestTemplate restTemplate = new RestTemplate();
    private final PetRepository petRepository;
    private final JournalRepository journalRepository;

    @Value("${api-key.chat-gpt}")
    private String apiKey;

    public HttpEntity<ChatGptRequestDto> buildHttpEntity(ChatGptRequestDto requestDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(ChatGptConfig.MEDIA_TYPE));
        headers.add(ChatGptConfig.AUTHORIZATION, ChatGptConfig.BEARER + apiKey);
        return new HttpEntity<>(requestDto, headers);
    }

    public ChatGptResponseDto getResponse(HttpEntity<ChatGptRequestDto> chatGptRequestDtoHttpEntity) {
        ResponseEntity<ChatGptResponseDto> responseEntity = restTemplate.postForEntity(
                ChatGptConfig.URL,
                chatGptRequestDtoHttpEntity,
                ChatGptResponseDto.class);
        return responseEntity.getBody();
    }


    public ChatGptAnswerResponseDto askQuestion(Long petId, PrincipalDetails principalDetails, QuestionRequestDto requestDto) {
        User owner = principalDetails.getUser();
        Pet pet = petRepository.findById(petId)
                .orElseThrow(()-> new IllegalArgumentException("pet_id 오류: " + petId));

        List<MessageRequestDto> messages = new ArrayList<>();

        /*페르소나 설정*/
        messages.add(MessageRequestDto.builder()
                        .role(ChatGptConfig.SYSTEM_ROLE)
                        .content("You are a dead pet. Your master is missing you. Give comfort and warm words to your master. Use informal language"
                                + "Make a Korean sentence that ends with a period.")
                        .build());

        messages.add(MessageRequestDto.builder()
                        .role(ChatGptConfig.SYSTEM_ROLE)
                        .content("1.Your name: " + pet.getName() + "2. Owner's name: " + pet.getOwnerName() + "3.Tone " + pet.getCharacOne() + "하고" + pet.getCharacTwo() + "하게")
                        .build());

        messages.add(MessageRequestDto.builder()
                .role(ChatGptConfig.SYSTEM_ROLE)
                .content("First, call your master according to the given owner's name. " +
                        "Second, answer reflecting given tone. " +
                        "Third, answer me in a friendly tone. Don't use honorifics in Korean." +
                        "Fourth, don't tell me sad things. " +
                        "Finally, answer from Pet's point of view. " + "Make a Korean sentence that ends with a period")
                .build());


        /*메시지 전달*/
        messages.add(MessageRequestDto.builder()
                        .role(ChatGptConfig.USER_ROLE)
                        .content(requestDto.getQuestion())
                        .build());

        ChatGptResponseDto responseDto =  this.getResponse(this.buildHttpEntity(new ChatGptRequestDto(
                                ChatGptConfig.MODEL,
                                messages,
                                ChatGptConfig.MAX_TOKEN,
                                ChatGptConfig.TEMPERATURE,
                                ChatGptConfig.TOP_P)));

        return new ChatGptAnswerResponseDto(responseDto.getChoices().get(0).getMessage().getContent());

    }


    // main 기능 #2: 일기 훔쳐보기 -> 일기(chatGPT 생성)
    public DalleAnswerResponseDto generateJournal(Long petId, PrincipalDetails principalDetails){
        User user = principalDetails.getUser();
        Pet pet = petRepository.findById(petId)
                .orElseThrow(()-> new IllegalArgumentException("pet_id 오류: " + petId));

        List<MessageRequestDto> messages = new ArrayList<>();

        /*페르소나 반영*/
        messages.add(MessageRequestDto.builder()
                .role(ChatGptConfig.SYSTEM_ROLE)
                .content("You are a pet. Please write 1 sentence of journal of the day. Use informal language and use Korean. Use these characteristics when writing a journal. Answer in a complete sentence." +
                        "The tone of the journal: "+ pet.getCharacOne() + "하고" + pet.getCharacTwo() + "하게" +
                        "1. Your favorite place:" + pet.getFavoritePlace() + "2. Your favorite play:" + pet.getFavoritePlay() + "3. Your habit:" + pet.getHabit()
                        + "4. Your routine:" + pet.getRoutine() + "5. Your favorite snack:" + pet.getFavoriteSnack() + "6. Your favorite time:" + pet.getFavoriteTime())
                .build());
        //이미지 생성시 종까지 명시하면 좋겠당

        messages.add(MessageRequestDto.builder()
                .role(ChatGptConfig.SYSTEM_ROLE)
                .content("1.Your name: " + pet.getName() + "2. Owner's name: " + pet.getOwnerName())
                .build());

        messages.add(MessageRequestDto.builder()
                .role(ChatGptConfig.SYSTEM_ROLE)
                .content("Call your master according to the given owner's name.")
                .build());

        ChatGptResponseDto responseDto =  this.getResponse(this.buildHttpEntity(new ChatGptRequestDto(
                ChatGptConfig.MODEL,
                messages,
                ChatGptConfig.MAX_TOKEN,
                ChatGptConfig.TEMPERATURE,
                ChatGptConfig.TOP_P)));
        //max_token: 300


        //* * * GPT가 생성한 일기 내용 저장 * * *
        /*일기 객체 생성*/
        Journal journal = Journal.builder()
                .user(user)
                .pet(pet)
                .content(responseDto.getChoices().get(0).getMessage().getContent())
                .build();
        journalRepository.save(journal);

        Long journalId = journal.getId();

        String response = responseDto.getChoices().get(0).getMessage().getContent();

        //DALL-E api 호출시 페르소나 반영
        String petFur;
        String prompt;
        if (pet.getFurColor() == null){
            prompt = "나는" + pet.getKind() + "종류의" + pet.getSpecies() + "야. 내 행동을 그려줘." + response;

        }
        else
        {
            petFur = pet.getFurColor();
            prompt = "나는" + pet.getKind() + "종류의" + petFur + "털을 가진" + pet.getSpecies() + "야. 내 행동을 그려줘." + response;

        }
        return new DalleAnswerResponseDto(prompt, journalId);


        //        return new DalleAnswerResponseDto(responseDto.getChoices().get(0).getMessage().getContent(), journalId);
    }


    public int generateImage(Long petId, PrincipalDetails principalDetails, String image, Long jourId){
//        Journal journal = journalRepository.findByPetAndOwnerId(petId, principalDetails.getUser().getId()); //ERROR: not unique result
        Journal journal = journalRepository.findById(jourId)
                .orElseThrow(()-> new IllegalArgumentException("journal Id 오류"));
        journalRepository.updateImage(journal.getId(), image); //그림일기 생성 후 저장
        return 1;
    }


    //일기를 한 문장으로 요약해서 DALL-E에게 전달!
    public ChatGptAnswerResponseDto shortJorunal(String koJournal){

        List<MessageRequestDto> messages = new ArrayList<>();

        /*페르소나 설정*/
        messages.add(MessageRequestDto.builder()
                .role(ChatGptConfig.USER_ROLE)
                .content("다음 글을 1개의 문장으로 요약해줘: " + koJournal)
                .build());


        ChatGptResponseDto responseDto =  this.getResponse(this.buildHttpEntity(new ChatGptRequestDto(
                ChatGptConfig.MODEL,
                messages,
                ChatGptConfig.MAX_TOKEN,
                ChatGptConfig.TEMPERATURE,
                ChatGptConfig.TOP_P)));

        return new ChatGptAnswerResponseDto(responseDto.getChoices().get(0).getMessage().getContent());
    }

}