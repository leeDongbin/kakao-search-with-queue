package biz.kakao.search.api.service;

import biz.kakao.search.api.search.service.GatewayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertNotNull;

@ActiveProfiles({"local"})
@RunWith(SpringRunner.class)
@SpringBootTest
public class GatewayServiceTest {

    @Autowired
    private GatewayService gatewayService;

    @Test
    public void testCreateSearchErrorFail(){
        final String searWord = "원빈";

        Object object = gatewayService.createSearch("원빈");

        assertNotNull(object);
    }

}
