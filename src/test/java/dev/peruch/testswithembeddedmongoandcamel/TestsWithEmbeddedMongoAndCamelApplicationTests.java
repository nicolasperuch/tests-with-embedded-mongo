package dev.peruch.testswithembeddedmongoandcamel;

import com.mmnaseri.utils.spring.data.dsl.factory.RepositoryFactoryBuilder;
import dev.peruch.testswithembeddedmongoandcamel.entity.User;
import dev.peruch.testswithembeddedmongoandcamel.repository.BaseRepository;
import junit.framework.Assert;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
public class TestsWithEmbeddedMongoAndCamelApplicationTests extends CamelTestSupport{

	@Test
	public void savePersonThenCheckMongoIfRecordIsThere() {

		final BaseRepository baseRepository = RepositoryFactoryBuilder
													.builder()
													.mock(BaseRepository.class);

		baseRepository.save(new User()
								.withName("Joao")
								.withLastName("Neves"));
		List<User> response = baseRepository.findAll();

		Assert.assertEquals(1, response.size());
	}
}
