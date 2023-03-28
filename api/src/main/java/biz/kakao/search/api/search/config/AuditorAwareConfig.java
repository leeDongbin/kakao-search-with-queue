package biz.kakao.search.api.search.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class AuditorAwareConfig {

    @Bean
    public AuditorAware<Long> auditorAware() {
        return new AuditorAware<>() {
            @Override
            public Optional<Long> getCurrentAuditor() {
                Long accessUserNumber = 1L;
                return Optional.ofNullable(accessUserNumber);
            }
        };
    }

}
