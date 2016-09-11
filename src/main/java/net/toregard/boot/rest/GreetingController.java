package net.toregard.boot.rest;

import net.toregard.boot.domain.Greeting;
import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Endpoint;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
    private static final String template = "Hello, %s!";
    private final  AtomicLong counter = new AtomicLong();

    @Autowired
    CamelContext camelContext;

    @Autowired
    private ProducerTemplate producer;

    @Autowired
    private ConsumerTemplate consumer;

    @Autowired
    ConfigurableApplicationContext context;

    @RequestMapping(path = "/greeting" ,method= RequestMethod.GET)
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        //producer.sendBody("direct:foo", "Hello World");
        //String body = consumer.receiveBody("direct:foo", String.class);

        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
        jmsTemplate.convertAndSend("ToreQueues", new Greeting(counter.incrementAndGet(),name));
        return new Greeting(counter.incrementAndGet(),name);
                //String.format(template, body));
    }
}
