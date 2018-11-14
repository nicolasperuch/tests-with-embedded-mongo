package dev.peruch.testswithembeddedmongoandcamel;

import dev.peruch.testswithembeddedmongoandcamel.entity.User;
import dev.peruch.testswithembeddedmongoandcamel.repository.BaseRepository;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestsWithEmbeddedMongoAndCamelApplicationTests {

	@Autowired
	private BaseRepository baseRepository;

	@Test
	public void savePersonThenCheckMongoIfRecordIsThere() {
		baseRepository.save(new User()
								.withName("Joao")
								.withLastName("Neves"));
		List<User> response = baseRepository.findAll();

		Assert.assertEquals(1, response.size());
	}
}
