package biz.kakao.search.domain.service;

import biz.kakao.search.domain.entity.SearchEntity;
import biz.kakao.search.domain.repository.SearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SearchDomainService {

    private final SearchRepository searchRepository;

    @Transactional
    public void createSearchWord(String searchWord) {
        SearchEntity searchEntity = SearchEntity.createdBy(searchWord, false);
        searchRepository.save(searchEntity);
    }

    public List<SearchEntity> findSearch() {
        return searchRepository.findByIsRead(false);
    }

    @Transactional
    public void readUpdateTrue(List<SearchEntity> searchEntities) {
        searchEntities.forEach(searchEntity -> {
            searchEntity.readUpdateTrue();
        });
    }

}
