package zw.co.zaratech.dev.samples.audit4jusage.configuration.audit

import org.audit4j.core.handler.ConsoleAuditHandler
import org.audit4j.core.handler.Handler
import org.audit4j.core.handler.file.FileAuditHandler
import org.audit4j.core.layout.CustomizableLayout
import org.audit4j.core.layout.Layout
import org.audit4j.handler.db.DatabaseAuditHandler
import org.audit4j.integration.spring.AuditAspect
import org.audit4j.integration.spring.SpringAudit4jConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy
import zw.co.zaratech.dev.samples.audit4jusage.customs.MyMetaData
import java.util.*

/**
 * @author maureen on 12/5/2019
 */
@Configuration
@EnableAspectJAutoProxy
open class Audit4jConfiguration {

    @Bean
    open fun layout(): Layout {
        var layout = CustomizableLayout();
        layout.setTemplate("${'$'}{eventDate}|${'$'}{uuid}|actor=${'$'}{actor}|${'$'}{action}|origin=${'$'}{origin} " +
                "=> ${'$'}{foreach fields field}${'$'}{field.name} ${'$'}{field.type}:${'$'}{field.value}, ${'$'}{end}");
        return layout;
    }

    @Bean
    open fun metaData(): MyMetaData {
        return MyMetaData()
    }

    @Bean
    open fun  databaseHandler():DatabaseAuditHandler{
        val databaseHandler = DatabaseAuditHandler ()
        databaseHandler.embedded = "false"
        databaseHandler.db_user="root"
        databaseHandler.db_password="P@ssw0rd#"
        databaseHandler.db_url="jdbc:mysql://localhost:3306/audit4j?useSSL=FALSE"
        databaseHandler.db_driver="com.mysql.cj.jdbc.Driver"
        //databaseHandler.db_connection_type="pooled"
        return databaseHandler;
    }

    @Bean
    open fun fileAuditHandler(): FileAuditHandler {
        return FileAuditHandler()
    }

    @Bean
    open fun consoleAuditHandler(): ConsoleAuditHandler {
        return ConsoleAuditHandler()
    }


    @Bean
    open fun auditConfig(): SpringAudit4jConfig {
        val audit4jConfig = SpringAudit4jConfig()
        val handlers = ArrayList<Handler<*>>()
        handlers.add(databaseHandler())
        handlers.add(consoleAuditHandler())
        handlers.add(fileAuditHandler())
        audit4jConfig.setHandlers(handlers)
        audit4jConfig.setLayout(layout())
        audit4jConfig.setMetaData(metaData())
        val props = HashMap<String, String>()
        props["log.file.location"] = "/Users/maureen/Downloads/audit4jusage/src/main/resources/audit"
        audit4jConfig.setProperties(props)
        return audit4jConfig

    }

    @Bean
    open fun auditAspect(): AuditAspect {
        return AuditAspect()
    }



}

private operator fun String.invoke(b: Boolean) {

}
