package com.cos.instagram.service;

import com.cos.instagram.domain.subscribe.Subscribe;
import com.cos.instagram.domain.subscribe.SubscribeRepository;
import com.cos.instagram.handler.ex.CustomApiException;
import com.cos.instagram.web.dto.subscribe.SubscribeDto;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;
    private final EntityManager em;

    @Transactional(readOnly = true)
    public List<SubscribeDto> 구독리스트(int principalId, int pageUserId) {

        StringBuffer sb = new StringBuffer();
        sb.append("select u.id, u.username, u.profileImageUrl, ");
        sb.append("if( (select 1 from subscribe where fromUserId = ? and toUserId = u.id), 1, 0) subscribeState, ");
        sb.append("if(u.id = ?, 1, 0) equalUserState ");
        sb.append("from user u inner join subscribe s ");
        sb.append("on u.id = s.toUserId ");
        sb.append("where s.fromUserId = ? ");

        Query query = em.createNativeQuery(sb.toString())
                .setParameter(1, principalId)
                .setParameter(2, principalId)
                .setParameter(3, pageUserId);

        JpaResultMapper result = new JpaResultMapper();
        List<SubscribeDto> subscribeDtos = result.list(query, SubscribeDto.class);
        return subscribeDtos;
    }

    @Transactional
    public void 구독하기(int fromUserId, int toUserId) {
        try {
            subscribeRepository.mSubscribe(fromUserId, toUserId);
        } catch (Exception e) {
            throw new CustomApiException("이미 구독을 하였습니다.");
        }
    }

    @Transactional
    public void 구독취소하기(int fromUserId, int toUserId) {
        subscribeRepository.mUnSubscribe(fromUserId, toUserId);
    }
}
