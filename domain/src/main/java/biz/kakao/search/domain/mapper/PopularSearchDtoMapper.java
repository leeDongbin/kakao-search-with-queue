package biz.kakao.search.domain.mapper;

import biz.kakao.search.domain.dto.PopularSearchDto;
import biz.kakao.search.domain.entity.PopularSearchEntity;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PopularSearchDtoMapper {

    public static List<PopularSearchDto> map(List<PopularSearchEntity> popularSearchEntities) {
        if (Objects.isNull(popularSearchEntities)) return Arrays.asList(new PopularSearchDto());

        return popularSearchEntities.stream().map(entity -> build(entity)).collect(Collectors.toList());
    }

    private static PopularSearchDto build(PopularSearchEntity popularSearchEntity) {
        return PopularSearchDto.builder()
                .searchWord(popularSearchEntity.getSearchWord())
                .searchCount(popularSearchEntity.getSearchCount())
                .build();
    }

}
