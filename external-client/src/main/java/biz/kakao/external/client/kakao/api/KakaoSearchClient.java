package biz.kakao.external.client.kakao.api;

import biz.kakao.external.client.kakao.code.Sort;
import biz.kakao.external.client.kakao.config.ExternalApiProperties;
import biz.kakao.external.client.kakao.dto.KakaoApiResponse;
import biz.kakao.external.client.kakao.exception.ApiStatusFailException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Component("kakaoSearchClient")
public class KakaoSearchClient extends AbstractKakaoSearchClient {

    private final RestTemplate kakaoSearchApiRestTemplate;
    private final ExternalApiProperties externalApiProperties;

    @Override
    public KakaoApiResponse searchBlog(String query,
                                       Sort sort,
                                       int page,
                                       int size) {
        String uri = getSearchBlogQueryUri(query, sort, page, size);
        ResponseEntity<KakaoApiResponse> response = exchange(uri, getHeaders());

        if(response.getStatusCode() != HttpStatus.OK) { throw new ApiStatusFailException(); }
        return response.getBody();
    }

    @Override
    public KakaoApiResponse searchBlog(String query, Sort sort) {
        String uri = getSearchBlogQueryUri(query, sort, DEFAULT_PAGE, DEFAULT_SIZE);
        ResponseEntity<KakaoApiResponse> response = exchange(uri, getHeaders());

        if(response.getStatusCode() != HttpStatus.OK) { throw new ApiStatusFailException(); }
        return response.getBody();
    }

    private HttpEntity getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(externalApiProperties.getAuthorName(), externalApiProperties.getAuthorValue());
        return new HttpEntity<>(headers);
    }

    private String getSearchBlogQueryUri(String query,
                                         Sort sort,
                                         Integer page,
                                         Integer size) {
        String sortName = Objects.nonNull(sort) ? sort.getName() : null;
        return UriComponentsBuilder.fromUriString(externalApiProperties.getFindBlogUriPath())
                .queryParam(QUERY_NAME, query)
                .queryParam(SORT_NAME, sortName)
                .queryParam(PAGE_NAME, page)
                .queryParam(SIZE_NAME, size)
                .build()
                .toString();
    }

    private ResponseEntity<KakaoApiResponse> exchange(String uri,
                                                      HttpEntity httpEntity) {
        try {
            return kakaoSearchApiRestTemplate.exchange(uri, HttpMethod.GET, httpEntity, KakaoApiResponse.class);
        }catch(Exception e) {
            throw new ApiStatusFailException(e.getMessage());
        }
    }

}
