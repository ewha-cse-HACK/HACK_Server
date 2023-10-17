package com.hack.hack_server.Dalle.Service;
import com.amazonaws.services.s3.AmazonS3;
import com.hack.hack_server.Authentication.PrincipalDetails;
import com.hack.hack_server.Community.Post.Dto.PostListResponseDto;
import com.hack.hack_server.Community.Post.Dto.PostResponseDto;
import com.hack.hack_server.Dalle.Dto.*;
import com.hack.hack_server.Entity.JournalComment;
import com.hack.hack_server.Entity.Journal;
import com.hack.hack_server.Entity.Post;
import com.hack.hack_server.Entity.User;
import com.hack.hack_server.Repository.JournalCommentRepository;
import com.hack.hack_server.Repository.JournalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URL;

@Service
@RequiredArgsConstructor
public class JournalService {
    private final JournalRepository journalRepository;
    private final JournalCommentRepository journalCommentRepository;
    private final AmazonS3 s3Client;

    public ResponseEntity<?> getResponse(Long journal_id, PrincipalDetails principalDetails){

        User user = principalDetails.getUser();

        //그림일기 이미지 S3에서 조회
        URL url = s3Client.getUrl("hack-s3bucket/그림일기", Long.toString(journal_id));
        String imageUrl = ""+url;

        //journal_id로 조회!
        Journal journal = journalRepository.findById(journal_id)
                .orElseThrow(()-> new IllegalArgumentException("journal_id 오류: " + journal_id));

//        if (journal.getUser().getEmail() != user.getEmail())
//        {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }


        //comment 추가
        JournalComment comment = journalCommentRepository.findByJournal_Id(journal_id)
                .orElse(null);


        //comment가 null일 경우 예외처리
        if (comment != null) {
            JournalResponseDto journalResponseDto = JournalResponseDto.builder()
                    .id(journal.getId())
                    .createdTime(journal.getCreatedTime())
                    .content(journal.getContent())
                    .imageUrl(imageUrl)
                    .journalCommentResponseDto(new JournalCommentResponseDto(comment)) //comment
                    .build();
            return new ResponseEntity<>(journalResponseDto, HttpStatus.OK);

        }

            JournalResponseDto journalResponseDto = JournalResponseDto.builder()
                    .id(journal.getId())
                    .createdTime(journal.getCreatedTime())
                    .content(journal.getContent())
                    .imageUrl(imageUrl)
                    .build();

            return new ResponseEntity<>(journalResponseDto, HttpStatus.OK);

    }


    public HttpStatus addComment(Long journal_id, PrincipalDetails principalDetails, JournalCommentDto journalCommentDto) {
        User user = principalDetails.getUser();

        //journal_id로 조회!
        Journal journal = journalRepository.findById(journal_id)
                .orElseThrow(()-> new IllegalArgumentException("journal_id 오류: " + journal_id));

//        //* 해당되는 유저가 아니면 에러 반환!.. 이 로직 디버깅해야함 *
//        if (journal.getUser() != user)
//        {
//            return HttpStatus.BAD_REQUEST;
//        }

        //그림일기 댓글(comment) 객체 생성
        JournalComment comment = JournalComment.builder()
                .content(journalCommentDto.getContent())
                .user(user)
                .journal(journal)
                .build();

        //댓글 DB에 저장
        journalCommentRepository.save(comment);

        return HttpStatus.CREATED;
    }

    //그림일기 목록 조회
    @Transactional(readOnly = true)
    public JournalListResponseDto findAllJournal(Long pet_id, Pageable pageable, PrincipalDetails principalDetails){
        User user = principalDetails.getUser();

        Page<Journal> journals = journalRepository.findAllByPet_Id(pageable, pet_id);
        Page<JournalListDto> journalListDtos = journals.map(JournalListDto::new);
        JournalListResponseDto responseDto = JournalListResponseDto.builder()
                .journalList(journalListDtos.getContent())
                .currentPage(journalListDtos.getNumber() + 1)
                .totalPage(journalListDtos.getTotalPages())
                .build();
        return responseDto;
    }

}