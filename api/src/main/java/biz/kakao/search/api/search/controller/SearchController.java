package biz.kakao.search.api.search.controller;

import biz.kakao.common.dto.SearchDto;
import biz.kakao.common.generator.ApiResponseGenerator;
import biz.kakao.common.response.ApiResponse;
import biz.kakao.common.response.PageResponse;
import biz.kakao.common.support.PagingOption;
import biz.kakao.external.client.kakao.dto.Documents;
import biz.kakao.search.api.search.dto.KakaoBlogSearchOption;
import biz.kakao.search.api.search.service.GatewayService;
import biz.kakao.search.api.search.service.rabbitMq.RabbitMqSender;
import biz.kakao.search.api.search.service.SearchService;
import biz.kakao.search.domain.dto.PopularSearchDto;
import biz.kakao.search.domain.service.PopularSearchDomainService;
import biz.kakao.search.domain.service.SearchDomainService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/searches")
public class SearchController {

    private final SearchService searchService;
    private final SearchDomainService searchDomainService;
    private final PopularSearchDomainService popularSearchDomainService;
    private final GatewayService gatewayService;
    private final RabbitMqSender rabbitMqSender;

    @ApiOperation(tags = "블로그 검색 API", value = "블로그 검색 정보", notes = "블로그 검색을 합니다.")
    @GetMapping(value = "/blogs")
    public ResponseEntity<ApiResponse<PageResponse<Documents>>> searchBlogList(@Valid @ModelAttribute KakaoBlogSearchOption searchOption,
                                                                               @ModelAttribute PagingOption pagingOption){
        PageResponse<Documents> pageResponse = searchService.searchBlogList(searchOption, pagingOption);
        SearchDto searchDto = new SearchDto(searchOption.getQuery());
        rabbitMqSender.sendSearchWord(searchDto);
        ApiResponse<PageResponse<Documents>> apiResponse = ApiResponseGenerator.success(pageResponse);

        return ResponseEntity.ok(apiResponse);
    }

    @ApiOperation(tags = "검색 등록 API", value = "검색 등록", notes = "검색을 등록합니다.")
    @PostMapping
    public ResponseEntity<ApiResponse<Map>> createSearchWord(@ApiParam(value = "검색어", required = true) @Valid @RequestBody SearchDto request){
        searchDomainService.createSearchWord(request.getSearchWord());
        ApiResponse<Map> apiResponse = ApiResponseGenerator.success(new HashMap<>());
        return ResponseEntity.ok(apiResponse);
    }

    @ApiOperation(tags = "인기 검색 API", value = "블로그 검색 정보", notes = "블로그 검색을 합니다.")
    @GetMapping(value = "/populars")
    public ResponseEntity<ApiResponse<List<PopularSearchDto>>> popularSearchBlogList(){
        List<PopularSearchDto> popularSearches = popularSearchDomainService.findPopularSearch();
        ApiResponse<List<PopularSearchDto>> apiResponse = ApiResponseGenerator.success(popularSearches);

        return ResponseEntity.ok(apiResponse);
    }

}
