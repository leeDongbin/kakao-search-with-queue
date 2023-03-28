package biz.kakao.external.client.kakao.api;

import biz.kakao.external.client.kakao.code.Sort;
import biz.kakao.external.client.kakao.dto.KakaoApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface SearchApi {

    KakaoApiResponse searchBlog(String query,
                                Sort sort,
                                int page,
                                int size);

    KakaoApiResponse searchBlog(String query,
                                Sort sort);

}
