package net.toregard.boot.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.ValueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MyRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file:C:/logs").routeId("ToreGard").
               //setBody().simple("ref:getHallo");
                       process(new Processor() {
                   public void process(Exchange exchange) throws Exception {
                       Object body = exchange.getIn().getBody();
                       // do some business logic with the input body
                   }
               });

        //to("log:bar");
    }


    @Bean
    String getHallo(){
        String a="";
        return "hallo der";
    }
}
