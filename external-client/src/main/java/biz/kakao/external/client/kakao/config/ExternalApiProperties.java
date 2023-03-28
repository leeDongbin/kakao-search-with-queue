package biz.kakao.external.client.kakao.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "search.kakao-search-client")
public class ExternalApiProperties {

    private String rootUri;
    private String findBlogUriPath;
    private String authorName;
    private String authorValue;
    private long connectTimeout;
    private long readTimeout;

}
