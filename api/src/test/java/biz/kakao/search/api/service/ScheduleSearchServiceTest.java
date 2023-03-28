package biz.kakao.search.api.service;

import biz.kakao.search.api.search.service.ScheduleSearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertTrue;

@ActiveProfiles({"local"})
@RunWith(SpringRunner.class)
@SpringBootTest
public class ScheduleSearchServiceTest {

    @Autowired
    private ScheduleSearchService scheduleSearchService;

    @Test
    public void testCreateSearchErrorFail(){
        final boolean isTrue = true;

        scheduleSearchService.createPopularSearch();

        assertTrue(isTrue);
    }

}
