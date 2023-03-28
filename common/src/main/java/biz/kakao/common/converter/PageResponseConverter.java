package biz.kakao.common.converter;

import biz.kakao.common.response.PageResponse;
import org.springframework.data.domain.Page;

public class PageResponseConverter {

    public static <T> PageResponse<T> convert(Page<T> page) {
        PageResponse<T> response = new PageResponse<>();
        response.setPageNumber(page.getNumber() + 1);
        response.setPageSize(page.getSize());
        response.setTotalPageNumber(page.getTotalPages());
        response.setTotalSize(page.getTotalElements());
        response.setList(page.getContent());
        return response;
    }

}
