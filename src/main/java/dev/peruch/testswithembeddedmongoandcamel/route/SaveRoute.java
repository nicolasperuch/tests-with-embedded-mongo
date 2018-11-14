package dev.peruch.testswithembeddedmongoandcamel.route;

import dev.peruch.testswithembeddedmongoandcamel.entity.User;
import dev.peruch.testswithembeddedmongoandcamel.repository.BaseRepository;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveRoute extends RouteBuilder {

    @Autowired
    private BaseRepository baseRepository;

    @Override
    public void configure() throws Exception {


        from("direct:saveUser")
                .routeId("saveUser")
                    .process(this::saveUser)
                .end();
    }

    private void saveUser(Exchange exchange) {
        User user = (User) exchange.getIn().getBody();
        baseRepository.save(user);
    }
}
