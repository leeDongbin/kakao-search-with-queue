package biz.kakao.external.client.kakao.api;

public abstract class AbstractKakaoSearchClient implements SearchApi {

    public static final String QUERY_NAME = "query";
    public static final String SORT_NAME = "sort";
    public static final String PAGE_NAME = "page";
    public static final String SIZE_NAME = "size";
    public static final int DEFAULT_PAGE = 1;
    public static int DEFAULT_SIZE = 10;

}
