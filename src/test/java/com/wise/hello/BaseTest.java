package com.wise.hello;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 单元测试 基类
 * @author lingyuwang
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)  
@SpringBootTest(classes = Application.class) 
public class BaseTest {

}
