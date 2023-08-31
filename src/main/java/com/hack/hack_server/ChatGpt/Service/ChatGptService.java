package com.hack.hack_server.ChatGpt.Service;

import com.hack.hack_server.Authentication.PrincipalDetails;
import com.hack.hack_server.ChatGpt.Dto.*;
import com.hack.hack_server.ChatGpt.ChatGptConfig;
import com.hack.hack_server.Entity.Characters;
import com.hack.hack_server.Entity.CharactersMapping;
import com.hack.hack_server.Entity.Pet;
import com.hack.hack_server.Entity.User;
import com.hack.hack_server.Repository.CharactersMappingRepository;
import com.hack.hack_server.Repository.CharactersRepository;
import com.hack.hack_server.Repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatGptService {
    private static final RestTemplate restTemplate = new RestTemplate();
    private final PetRepository petRepository;
    private final CharactersRepository charactersRepository;
    private final CharactersMappingRepository mappingRepository;

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
        List<CharactersMapping> characterList = mappingRepository.findByPet_Id(petId);
        Characters charOne = charactersRepository.findById(characterList.get(0).getId()).get();
        Characters charTwo = charactersRepository.findById(characterList.get(1).getId()).get();

        List<MessageRequestDto> messages = new ArrayList<>();

        /*페르소나 설정*/
        messages.add(MessageRequestDto.builder()
                        .role(ChatGptConfig.SYSTEM_ROLE)
                        .content("You are a dead pet. Your master is missing you. Give comfort and warm words to your master. Use informal language")
                        .build());

        messages.add(MessageRequestDto.builder()
                        .role(ChatGptConfig.SYSTEM_ROLE)
                        .content("1.Your name: " + pet.getName() + "2. Owner's name: " + pet.getOwnerName() + "3.Tone " + charOne.getType() + "하고" + charTwo.getType() + "하게")
                        .build());

        messages.add(MessageRequestDto.builder()
                .role(ChatGptConfig.SYSTEM_ROLE)
                .content("First, call your master according to the given owner's name. Second, answer reflecting given tone. Third, answer me in a friendly tone. Finally, answer from Pet's point of view. 한국어로 대답해")
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


    //[기능: 일기 훔쳐보기에서의 '일기' 생성]
    public ChatGptAnswerResponseDto generateJournal(Long petId, PrincipalDetails principalDetails){
        Pet pet = petRepository.findById(petId)
                .orElseThrow(()-> new IllegalArgumentException("pet_id 오류: " + petId));
        List<CharactersMapping> characterList = mappingRepository.findByPet_Id(petId);
        System.out.print(characterList);
        Characters charOne = charactersRepository.findById(characterList.get(0).getCharacter().getId()).get();
        Characters charTwo = charactersRepository.findById(characterList.get(1).getCharacter().getId()).get();

        List<MessageRequestDto> messages = new ArrayList<>();

        /*페르소나 반영*/
        messages.add(MessageRequestDto.builder()
                .role(ChatGptConfig.SYSTEM_ROLE)
                .content("You are a pet. Please write a paragraph of journal of the day. Use informal language and use Korean. Use these characteristics when writing a journal." +
                        "The tone of the journal: "+ charOne.getType() + "하고" + charTwo.getType() + "하게" +
                        "1. Your favorite place:" + pet.getFavoritePlace() + "2. Your favorite play:" + pet.getFavoritePlay() + "3. Your habit:" + pet.getHabit()
                        + "4. Your routine:" + pet.getRoutine() + "5. Your favorite snack:" + pet.getFavoriteSnack() + "6. Your favorite time:" + pet.getFavoriteTime())
                .build());
        //이미지 생성시 종까지 명시하면 좋겠당


        ChatGptResponseDto responseDto =  this.getResponse(this.buildHttpEntity(new ChatGptRequestDto(
                ChatGptConfig.MODEL,
                messages,
                ChatGptConfig.MAX_TOKEN + 200,
                ChatGptConfig.TEMPERATURE,
                ChatGptConfig.TOP_P)));
        //max_token + 200 = 500

        //* * * 일기 내용 일기훔쳐보기에 저장 * * * (일기 Entity)

        return new ChatGptAnswerResponseDto(responseDto.getChoices().get(0).getMessage().getContent());
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