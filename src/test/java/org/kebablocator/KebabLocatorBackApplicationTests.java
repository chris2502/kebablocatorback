package org.kebablocator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.slf4j.LoggerFactory.getLogger;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = KebabLocatorBackApplication.class)
public class KebabLocatorBackApplicationTests {
	private static final org.slf4j.Logger LOGGER = getLogger(KebabLocatorBackApplicationTests.class);
	@Test
	public void contextLoads() {
		LOGGER.info("POTATE TIME is "+System.currentTimeMillis(), "");
	}

}
