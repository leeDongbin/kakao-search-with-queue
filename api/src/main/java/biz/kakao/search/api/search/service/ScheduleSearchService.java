package biz.kakao.search.api.search.service;

import biz.kakao.search.domain.entity.SearchEntity;
import biz.kakao.search.domain.service.PopularSearchDomainService;
import biz.kakao.search.domain.service.SearchDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ScheduleSearchService {

    private final SearchDomainService searchDomainService;
    private final PopularSearchDomainService popularSearchDomainService;

    @Transactional
    @Scheduled(fixedDelay = 10000L)
    public void createPopularSearch() {
        List<SearchEntity> searchEntities = searchDomainService.findSearch();
        if(!CollectionUtils.isEmpty(searchEntities)) {
            List<String> searchWords = searchEntities.stream().map(SearchEntity::getSearchWord).collect(Collectors.toList());
            popularSearchDomainService.savePopularSearch(searchWords);
            searchDomainService.readUpdateTrue(searchEntities);
        }
    }

}
