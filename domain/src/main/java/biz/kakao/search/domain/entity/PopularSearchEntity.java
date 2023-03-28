package biz.kakao.search.domain.entity;

import biz.kakao.search.domain.common.BaseEntity;
import lombok.*;
import javax.persistence.*;

@Getter
@Entity
@ToString
@Table(name = "popular_search")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PopularSearchEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "search_word")
    private String searchWord;

    @Column(name = "search_count")
    private int searchCount;

    @Builder
    public PopularSearchEntity(String searchWord,
                               int searchCount) {
        this.searchWord = searchWord;
        this.searchCount = searchCount;
    }

    public static PopularSearchEntity createdBy(String searchWord,
                                                int searchCount) {
        return PopularSearchEntity.builder()
                .searchWord(searchWord)
                .searchCount(searchCount)
                .build();
    }

    public void update(String searchWord) {
        this.searchWord = searchWord;
        this.searchCount += 1;
    }


}
