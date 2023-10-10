package com.hack.hack_server.Dalle.Service;
import com.amazonaws.services.s3.AmazonS3;
import com.hack.hack_server.Authentication.PrincipalDetails;
import com.hack.hack_server.Community.Post.Dto.PostDetailResponseDto;
import com.hack.hack_server.Dalle.Dto.JournalResponseDto;
import com.hack.hack_server.Entity.Journal;
import com.hack.hack_server.Entity.User;
import com.hack.hack_server.Repository.JournalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JournalService {
    private final JournalRepository journalRepository;
    private final AmazonS3 s3Client;

    public ResponseEntity<?> getResponse(Long journal_id, PrincipalDetails principalDetails){

        User user = principalDetails.getUser();

        //그림일기 이미지 S3에서 조회
        URL url = s3Client.getUrl("hack-s3bucket", Long.toString(journal_id));
        String imageUrl = ""+url;

        //journal_id로 조회!
        Journal journal = journalRepository.findById(journal_id)
                .orElseThrow(()-> new IllegalArgumentException("journal_id 오류: " + journal_id));

//        if (journal.getUser().getEmail() != user.getEmail())
//        {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }

        JournalResponseDto journalResponseDto = JournalResponseDto.builder()
                .createdTime(journal.getCreatedTime())
                .content(journal.getContent())
                .imageUrl(imageUrl)
                .build();
        return new ResponseEntity<>(journalResponseDto, HttpStatus.OK);
    }


}