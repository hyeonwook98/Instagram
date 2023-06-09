package com.cos.instagram.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SubscribeRepository extends JpaRepository<Subscribe, Integer> {

    @Modifying
    @Query(value = "INSERT INTO subscribe(fromUserId, toUserId, createDate) VALUES(:fromUserId,:toUserId,now())",
            nativeQuery = true)
    void mSubscribe(int fromUserId, int toUserId);

    @Modifying
    @Query(value = "DELETE FROM subscribe WHERE fromUserId = :fromUserId AND toUserId = :toUserId",
            nativeQuery = true)
    void mUnSubscribe(int fromUserId, int toUserId);

    @Query(value = "SELECT count(*) FROM subscribe WHERE fromUserId = :principalId AND toUserId = :userId", nativeQuery = true)
    int mSubscribeState(int principalId, int userId);

    @Query(value = "SELECT count(*) FROM subscribe WHERE fromUserId = :userId", nativeQuery = true)
    int mSubscribeCount(int userId);
}
