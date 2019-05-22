package zw.co.zaratech.dev.samples.audit4jusage.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import zw.co.zaratech.dev.samples.audit4jusage.services.HelloService;

/**
 * @author maureen on 13/5/2019
 */
@RestController
public class GreetingsRestController {

    @Autowired
    HelloService helloService;

    @GetMapping(value = {"/", "/welcome"}, produces = "application/json")
    public String welcome() {
        String name= "anonymous";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            name= ((UserDetails) authentication.getPrincipal()).getUsername();
        }
        return helloService.hello(name);
    }
}
