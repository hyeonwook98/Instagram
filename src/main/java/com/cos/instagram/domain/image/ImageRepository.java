package com.cos.instagram.domain.image;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    @Query(value = "SELECT * FROM image WHERE userId IN (SELECT toUserId FROM subscribe where fromUserId = :principalId) order by id desc", nativeQuery = true)
    List<Image> mStory(int principalId);
}
