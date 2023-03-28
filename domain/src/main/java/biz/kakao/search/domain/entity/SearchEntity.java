package biz.kakao.search.domain.entity;

import biz.kakao.search.domain.common.BaseEntity;
import lombok.*;
import javax.persistence.*;

@Getter
@Entity
@ToString
@Table(name = "search")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SearchEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "search_word")
    private String searchWord;

    @Column(name = "is_read")
    private boolean isRead;

    @Builder
    public SearchEntity(String searchWord,
                        boolean isRead) {
        this.searchWord = searchWord;
        this.isRead = isRead;
    }

    public static SearchEntity createdBy(String searchWord,
                                         boolean isRead) {
        return SearchEntity.builder()
                .searchWord(searchWord)
                .isRead(isRead)
                .build();
    }

    public void readUpdateTrue() {
        this.isRead = true;
    }

}
