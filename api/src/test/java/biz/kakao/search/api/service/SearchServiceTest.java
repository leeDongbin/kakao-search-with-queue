package biz.kakao.search.api.service;

import biz.kakao.common.response.PageResponse;
import biz.kakao.common.support.PagingOption;
import biz.kakao.external.client.kakao.code.Sort;
import biz.kakao.external.client.kakao.dto.Documents;
import biz.kakao.external.client.kakao.exception.ApiStatusFailException;
import biz.kakao.search.api.search.dto.KakaoBlogSearchOption;
import biz.kakao.search.api.search.service.SearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

@ActiveProfiles({"local"})
@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchServiceTest {

    @Autowired
    private SearchService searchService;

    @Test
    public void testSearchBlogQueryAndSortSuccess(){
        final String searWord = "원빈";
        KakaoBlogSearchOption searchOption = new KakaoBlogSearchOption();
        searchOption.setQuery(searWord);
        searchOption.setSort(Sort.ACCURACY);
        PagingOption pagingOption = new PagingOption(10, 1);

        PageResponse<Documents> documentsPageResponse = searchService.searchBlogList(searchOption, pagingOption);
        final int SIZE = documentsPageResponse.getList().size();

        assertNotNull(documentsPageResponse);
        assertEquals(10, SIZE);
    }

    @Test
    public void testSearchBlogQuerySuccess(){
        final String searWord = "로또";
        KakaoBlogSearchOption searchOption = new KakaoBlogSearchOption();
        searchOption.setQuery(searWord);
        PagingOption pagingOption = new PagingOption(20, 1);

        PageResponse<Documents> documentsPageResponse = searchService.searchBlogList(searchOption, pagingOption);
        final int SIZE = documentsPageResponse.getList().size();

        assertNotNull(documentsPageResponse);
        assertEquals(20, SIZE);
    }

    @Test(expected = ApiStatusFailException.class)
    public void testSearchBlogQueryFail(){
        final String searWord = null;
        KakaoBlogSearchOption searchOption = new KakaoBlogSearchOption();
        searchOption.setQuery(searWord);
        PagingOption pagingOption = new PagingOption(10, 1);

        PageResponse<Documents> documentsPageResponse = searchService.searchBlogList(searchOption, pagingOption);

        assertNotNull(documentsPageResponse);
    }

}
