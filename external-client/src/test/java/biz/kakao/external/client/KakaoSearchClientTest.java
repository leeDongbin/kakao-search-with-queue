package biz.kakao.external.client;

import biz.kakao.external.client.kakao.api.KakaoSearchClient;
import biz.kakao.external.client.kakao.api.SearchApi;
import biz.kakao.external.client.kakao.code.Sort;
import biz.kakao.external.client.kakao.config.ExternalApiConfig;
import biz.kakao.external.client.kakao.dto.KakaoApiResponse;
import biz.kakao.external.client.kakao.exception.ApiStatusFailException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ActiveProfiles({"local"})
@SpringBootTest(classes = {ExternalApiConfig.class, KakaoSearchClient.class})
@TestPropertySource(properties = { "spring.config.location=classpath:application-external-client.yml" })
public class KakaoSearchClientTest {

    @Autowired
    private SearchApi kakaoSearchClient;

    @Test
    public void testDefaultSearchBlogQueryAndSortRecencySuccess() {
        String query = "이효리";
        Sort sort = Sort.RECENCY;

        KakaoApiResponse response = kakaoSearchClient.searchBlog(query, sort);
        final int SIZE = response.getDocuments().size();

        assertNotNull(response);
        assertEquals(10, SIZE);
    }

    @Test
    public void testDefaultSearchBlogQueryAndSortAccuracySuccess() {
        String query = "권상우";
        Sort sort = Sort.ACCURACY;

        KakaoApiResponse response = kakaoSearchClient.searchBlog(query, sort);
        final int SIZE = response.getDocuments().size();

        assertNotNull(response);
        assertEquals(10, SIZE);
    }

    @Test
    public void testDefaultSearchBlogQueryAndSortNullSuccess() {
        String query = "권상우";
        Sort sort = null;

        KakaoApiResponse response = kakaoSearchClient.searchBlog(query, sort);
        final int SIZE = response.getDocuments().size();

        assertNotNull(response);
        assertEquals(10, SIZE);
    }

    @Test
    public void testSearchBlogQueryAndSortRecencySuccess() {
        String query = "원빈";
        Sort sort = Sort.RECENCY;

        KakaoApiResponse response = kakaoSearchClient.searchBlog(query, sort, 1, 30);
        final int SIZE = response.getDocuments().size();

        assertNotNull(response);
        assertEquals(30, SIZE);
    }


    @Test
    public void testSearchBlogQueryAndSortAccuracySuccess() {
        String query = "권상우";
        Sort sort = Sort.ACCURACY;

        KakaoApiResponse response = kakaoSearchClient.searchBlog(query, sort, 1, 20);
        final int SIZE = response.getDocuments().size();

        assertNotNull(response);
        assertEquals(20, SIZE);
    }

    @Test(expected = ApiStatusFailException.class)
    public void testDefaultSearchBlogRequireQueryEmptyFail() {
        String query = null;
        Sort sort = Sort.RECENCY;

        KakaoApiResponse response = kakaoSearchClient.searchBlog(null, sort);

        assertNotNull(response);
    }

}
