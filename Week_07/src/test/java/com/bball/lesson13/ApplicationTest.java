package com.bball.lesson13;

import com.bball.lesson13.service.ITOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {
    @Autowired
    private ITOrderService orderService;

    @Test
    public void testBatch(){
        orderService.batchInsertOrder();
    }
}
