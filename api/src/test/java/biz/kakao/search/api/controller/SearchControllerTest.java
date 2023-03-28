package biz.kakao.search.api.controller;

import biz.kakao.common.code.ApiResponseCode;
import biz.kakao.search.api.ApiApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles({"local"})
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = {ApiApplication.class})
@AutoConfigureMockMvc
public class SearchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testApiGetSearchBlogQueryAndSortRecencySuccess() throws  Exception{
        MultiValueMap params = new LinkedMultiValueMap();
        params.add("query","이효리");
        params.add("sort","RECENCY");

        ResultActions resultActions = this.mockMvc.perform(get("/v1/searches/blogs")
                .params(params)
                .contentType(MediaType.APPLICATION_JSON));

        resultActions.andDo(print());
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.code", is(ApiResponseCode.SUCCESS.getCode())));
    }

    @Test
    public void testApiGetSearchBlogQueryAndSortAccuracySuccess() throws  Exception{
        MultiValueMap params = new LinkedMultiValueMap();
        params.add("query","아이유");
        params.add("sort","ACCURACY");

        ResultActions resultActions = this.mockMvc.perform(get("/v1/searches/blogs")
                .params(params)
                .contentType(MediaType.APPLICATION_JSON));

        resultActions.andDo(print());
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.code", is(ApiResponseCode.SUCCESS.getCode())));
    }

    @Test
    public void testApiGetSearchBlogQuerySuccess() throws  Exception{
        MultiValueMap params = new LinkedMultiValueMap();
        params.add("query","현빈");

        ResultActions resultActions = this.mockMvc.perform(get("/v1/searches/blogs")
                .params(params)
                .contentType(MediaType.APPLICATION_JSON));

        resultActions.andDo(print());
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.code", is(ApiResponseCode.SUCCESS.getCode())));
    }

    @Test
    public void testApiGetSearchBlogQueryBadRequestErrorFail() throws  Exception{
        MultiValueMap params = new LinkedMultiValueMap();

        ResultActions resultActions = this.mockMvc.perform(get("/v1/searches/blogs")
                .params(params)
                .contentType(MediaType.APPLICATION_JSON));

        resultActions.andDo(print());
        resultActions.andExpect(status().is4xxClientError());
        resultActions.andExpect(jsonPath("$.code", is(ApiResponseCode.BAD_REQUEST_ERROR.getCode())));
    }

    @Test
    public void testApiGetSearchPopularsSuccess() throws  Exception{
        ResultActions resultActions = this.mockMvc.perform(get("/v1/searches/populars")
                .contentType(MediaType.APPLICATION_JSON));

        resultActions.andDo(print());
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.code", is(ApiResponseCode.SUCCESS.getCode())));
    }

}
