package biz.kakao.search.api.search.service.rabbitMq;

import biz.kakao.common.dto.SearchDto;
import biz.kakao.search.domain.service.SearchDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Slf4j
@RequiredArgsConstructor
@Component
public class RabbitMqReceiver {

    private final SearchDomainService searchDomainService;

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(name="search", type = ExchangeTypes.TOPIC),
            value = @Queue(name = "search-word-queue"),
            key = "search-word-key"
    ))
    public void receiverSearchWord(SearchDto searchDto){
        log.info("receiver <-- {} ", searchDto.toString());
        searchDomainService.createSearchWord(searchDto.getSearchWord());
    }

}
