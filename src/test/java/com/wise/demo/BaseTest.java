package com.wise.demo;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wise.demo.Application;

/**
 * 单元测试 基类
 * @author lingyuwang
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)  
@SpringBootTest(classes = Application.class) 
public class BaseTest {

}
