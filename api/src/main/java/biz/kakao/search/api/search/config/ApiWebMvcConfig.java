package biz.kakao.search.api.search.config;

import biz.kakao.external.client.kakao.config.ExternalApiConfig;
import biz.kakao.search.domain.config.DomainConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        DomainConfig.class,
        ExternalApiConfig.class
})
public class ApiWebMvcConfig {

}
