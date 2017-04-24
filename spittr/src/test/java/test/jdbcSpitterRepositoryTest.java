package test;

import static org.junit.Assert.*;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import spittr.config.RootConfig;
import spittr.repository.SpitterRepository;
import spittr.repository.SpittleRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=RootConfig.class)
public class jdbcSpitterRepositoryTest {
	
	@Autowired
	private SpitterRepository spitterRepository;
	@Autowired
	private SpittleRepository spittleRepository;

	@Test
	public void test() {
		assertNotNull(spitterRepository);
		assertNotNull(spittleRepository);

//		System.out.println(spittle);
		System.out.println(spitterRepository.findByUsername("jreey"));
		
	}

}
