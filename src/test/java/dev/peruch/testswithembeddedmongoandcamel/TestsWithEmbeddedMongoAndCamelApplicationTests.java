package dev.peruch.testswithembeddedmongoandcamel;

import com.mmnaseri.utils.spring.data.dsl.factory.RepositoryFactoryBuilder;
import dev.peruch.testswithembeddedmongoandcamel.entity.User;
import dev.peruch.testswithembeddedmongoandcamel.repository.BaseRepository;
import dev.peruch.testswithembeddedmongoandcamel.route.SaveRoute;
import junit.framework.Assert;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
public class TestsWithEmbeddedMongoAndCamelApplicationTests extends CamelTestSupport {

	private BaseRepository baseRepository;

	@Override
	protected RoutesBuilder createRouteBuilder() throws Exception {
		baseRepository = RepositoryFactoryBuilder
				.builder()
				.mock(BaseRepository.class);


		return new SaveRoute()
					.withBaseRepository(baseRepository);
	}

	@Test
	public void savePersonThenCheckMongoIfRecordIsThere() {
		baseRepository.save(getJohnSnow());
		List<User> response = baseRepository.findAll();

		Assert.assertEquals(1, response.size());
	}

	@Test
	public void testingRoute(){
		String expected = "User saved!";
		String camelResponse = (String) template.requestBody("direct:saveUser", getJohnSnow());
		Assert.assertEquals(expected, camelResponse);
	}

	public User getJohnSnow(){
		return new User()
				.withName("Joao")
				.withLastName("Neves");
	}
}
