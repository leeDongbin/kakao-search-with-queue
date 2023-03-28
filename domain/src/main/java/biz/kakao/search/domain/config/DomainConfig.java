package biz.kakao.search.domain.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan("biz.kakao.search.domain.common")
@ComponentScan("biz.kakao.search.domain.entity")
@ComponentScan("biz.kakao.search.domain.service")
@ComponentScan("biz.kakao.search.domain.repository")
@EnableJpaRepositories(basePackages = {"biz.kakao.search.domain.repository"})
@EntityScan(basePackages = {"biz.kakao.search.domain.entity"})
public class DomainConfig {
}
