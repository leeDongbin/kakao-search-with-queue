package biz.kakao.search.domain.service;

import biz.kakao.search.domain.dto.PopularSearchDto;
import biz.kakao.search.domain.entity.PopularSearchEntity;
import biz.kakao.search.domain.mapper.PopularSearchDtoMapper;
import biz.kakao.search.domain.repository.PopularSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PopularSearchDomainService {

    private final PopularSearchRepository popularSearchRepository;

    @Transactional
    public void savePopularSearch(List<String> searchWords) {
        searchWords.forEach(searchWord -> {
            Optional<PopularSearchEntity> getSearchWord =  popularSearchRepository.findBySearchWord(searchWord);
            if(getSearchWord.isPresent()) {
                getSearchWord.get().update(searchWord);
            }else {
                PopularSearchEntity popularSearchEntity = PopularSearchEntity.builder()
                        .searchWord(searchWord)
                        .searchCount(1)
                        .build();

                popularSearchRepository.save(popularSearchEntity);
            }
        });
    }

    public List<PopularSearchDto> findPopularSearch() {
        List<PopularSearchEntity> getPopularSearchEntity= popularSearchRepository.findTop10ByOrderBySearchCountDesc();
        return PopularSearchDtoMapper.map(getPopularSearchEntity);
    }

}
