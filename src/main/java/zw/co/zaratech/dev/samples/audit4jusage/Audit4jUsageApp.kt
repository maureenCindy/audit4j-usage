package zw.co.zaratech.dev.samples.audit4jusage

/**
 * @author maureen on 12/5/2019
 */

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class Audit4jUsageApp {


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val context = SpringApplication.run(Audit4jUsageApp::class.java)
        }
    }

}
