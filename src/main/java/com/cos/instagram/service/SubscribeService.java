package com.cos.instagram.service;

import com.cos.instagram.domain.subscribe.Subscribe;
import com.cos.instagram.domain.subscribe.SubscribeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;

    @Transactional
    public void 구독하기(int fromUserId, int toUserId) {
        subscribeRepository.mSubscribe(fromUserId, toUserId);
    }

    @Transactional
    public void 구독취소하기(int fromUserId, int toUserId) {
        subscribeRepository.mUnSubscribe(fromUserId, toUserId);
    }
}
