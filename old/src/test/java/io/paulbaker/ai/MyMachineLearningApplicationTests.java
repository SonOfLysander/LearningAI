package io.paulbaker.ai;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

//@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MyMachineLearningApplication.class)
public class MyMachineLearningApplicationTests extends AbstractTestNGSpringContextTests{

	@Test
	public void contextLoads() {
	}

}
