package com.hack.hack_server.Repository;

import com.hack.hack_server.Entity.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostImageRepository extends JpaRepository<PostImage, Long> {
}
