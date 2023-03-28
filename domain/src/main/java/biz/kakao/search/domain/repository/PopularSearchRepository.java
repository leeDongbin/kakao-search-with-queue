package biz.kakao.search.domain.repository;

import biz.kakao.search.domain.entity.PopularSearchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface PopularSearchRepository extends JpaRepository<PopularSearchEntity, Long> {

    Optional<PopularSearchEntity> findBySearchWord(String searchWord);

    List<PopularSearchEntity> findTop10ByOrderBySearchCountDesc();

}
