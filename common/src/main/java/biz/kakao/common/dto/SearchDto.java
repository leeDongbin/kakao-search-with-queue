package biz.kakao.common.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SearchDto {

    private String searchWord;

    @Builder
    public SearchDto(String searchWord) {
        this.searchWord = searchWord;
    }

}
