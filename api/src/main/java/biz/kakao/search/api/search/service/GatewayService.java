package biz.kakao.search.api.search.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RequiredArgsConstructor
@Service
public class GatewayService {

    private final RestTemplate restTemplate;

    @Value("${api.root-uri:}")
    private String rootUri;

    @Value("${api.create-search-uri-path:}")
    private String createSearchUriPath;

    public void createSearchAsync(String searchWord) {
        CompletableFuture.supplyAsync(() -> createSearch(searchWord));
    }

    public Object createSearch(String searchWord) {
        StringBuilder uri = new StringBuilder();
        uri.append(rootUri);
        uri.append(createSearchUriPath);

        Map<String, String> params = new HashMap<>();
        params.put("searchWord", searchWord);

        try {
            HttpEntity httpEntity = createHttpEntity(params);
            ResponseEntity<Object> responseEntity = restTemplate.exchange(uri.toString(), HttpMethod.POST, httpEntity, Object.class);

            return responseEntity.getBody();
        } catch (HttpClientErrorException e) {
            log.error(e.getMessage());
            return e.getMessage();
        } catch (Exception e) {
            log.error(e.getMessage());
            return e.getMessage();
        }
    }

    private <T> HttpEntity<T> createHttpEntity(T body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<T>(body, headers);
    }

}
