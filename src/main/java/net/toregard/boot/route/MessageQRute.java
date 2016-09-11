package net.toregard.boot.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MessageQRute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("jms:queue:ToreQueues").
                routeId("ToreGardQ").
                process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        Object body = exchange.getIn().getBody();
                        System.out.print("*******************************");
                        System.out.println(body);
                    }
                });
    }
}

