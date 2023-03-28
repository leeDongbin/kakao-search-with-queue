package biz.kakao.search.domain.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PopularSearchDto {

    private String searchWord;
    private int searchCount;

    @Builder
    public PopularSearchDto(String searchWord, int searchCount) {
        this.searchWord = searchWord;
        this.searchCount = searchCount;
    }

}
