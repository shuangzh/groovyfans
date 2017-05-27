package web

import groovy.sql.Sql
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Created by admin on 2017/5/27.
 */


@Grab('groovy-all')
@Grab('org.hsqldb:hsqldb:2.3.4')
@Configuration
class BeansConfig {

    @Bean
    Sql getSQL(){
        def dbSettings = [
                url: 'jdbc:hsqldb:hsql://localhost/cookingdb',
                driver: 'org.hsqldb.jdbcDriver',
                user: 'sa',
                password: ''
        ]
        Sql.newInstance(dbSettings)
    }
}
