package com.hack.hack_server.Repository;

import com.hack.hack_server.Entity.Journal;
import com.hack.hack_server.Entity.JournalComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JournalCommentRepository extends JpaRepository<JournalComment, Long> {

    @Query(nativeQuery = true, value = "select * from journal_comment where journal_id=:journal_id")
    Optional<JournalComment> findByJournal_Id(@Param("journal_id") Long journalId);

}
