package biz.kakao.search.api.search.service;

import biz.kakao.common.converter.PageResponseConverter;
import biz.kakao.common.converter.PageableConverter;
import biz.kakao.common.response.PageResponse;
import biz.kakao.common.support.PagingOption;
import biz.kakao.external.client.kakao.api.SearchApi;
import biz.kakao.external.client.kakao.dto.Documents;
import biz.kakao.external.client.kakao.dto.KakaoApiResponse;
import biz.kakao.search.api.search.dto.KakaoBlogSearchOption;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SearchService {

    private final SearchApi kakaoSearchClient;

    public PageResponse<Documents> searchBlogList(KakaoBlogSearchOption searchOption,
                                                  PagingOption pagingOption) {
        KakaoApiResponse kakaoApiResponse = kakaoSearchClient.searchBlog(searchOption.getQuery(), searchOption.getSort(),
                pagingOption.getPageNumber(), pagingOption.getPageSize());
        List<Documents> documents = kakaoApiResponse.getDocuments();
        long totalCount = kakaoApiResponse.getMeta().getTotalCount();

        Page<Documents> searchBlogPage = new PageImpl<>(documents, PageableConverter.convert(pagingOption),totalCount);

        return PageResponseConverter.convert(searchBlogPage);
    }

}
