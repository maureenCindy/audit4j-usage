package zw.co.zaratech.dev.samples.audit4jusage.services;

import org.audit4j.core.annotation.Audit;
import org.audit4j.core.annotation.AuditField;
import org.springframework.stereotype.Service;

/**
 * @author maureen on 13/5/2019
 */
@Service
public class HelloService {

    @Audit(action = "HelloService.hello")
    public String hello(@AuditField(field="loggedInUsername") String name) {
        return "Welcome "  + name;
    }
}


