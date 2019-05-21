package zw.co.zaratech.dev.samples.audit4jusage.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import zw.co.zaratech.dev.samples.audit4jusage.services.HelloService;

/**
 * @author maureen on 13/5/2019
 */
@RestController
public class GreetingsRestController {

    @Autowired
    HelloService helloService;

    @GetMapping(value = "greetings/{name}/{surname}", produces = "application/json")
    public String welcome(@PathVariable String name, @PathVariable String surname) {
        return helloService.hello(name,surname);
    }


}
