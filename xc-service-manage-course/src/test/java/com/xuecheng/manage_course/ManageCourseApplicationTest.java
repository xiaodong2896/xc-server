package com.xuecheng.manage_course;

import com.xuecheng.manage_course.service.CategoryService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Administrator
 * @version 1.0
 * @create 2018-09-12 18:11
 **/
@SpringBootTest(classes = ManageCourseApplicationTest.class)
@RunWith(SpringRunner.class)
public class ManageCourseApplicationTest {


    @Autowired
    CategoryService categoryService;


}
