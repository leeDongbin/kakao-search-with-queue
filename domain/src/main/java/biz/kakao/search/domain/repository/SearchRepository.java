package biz.kakao.search.domain.repository;

import biz.kakao.search.domain.entity.SearchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SearchRepository extends JpaRepository<SearchEntity, Long> {

    List<SearchEntity> findByIsRead(boolean isRead);

}
