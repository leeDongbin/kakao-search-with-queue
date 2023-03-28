package biz.kakao.search.api.search.service.rabbitMq;

import biz.kakao.common.dto.SearchDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class RabbitMqSender {

    private final RabbitTemplate rabbitTemplate;

    public void sendSearchWord(SearchDto searchDto) {
        log.info("sendSearchWord --> "+searchDto.toString());
        rabbitTemplate.convertAndSend("search", "search-word-key", searchDto);
    }

}
