package db

import groovy.grape.Grape

/**
 * Created by admin on 2017/5/25.
 */
@groovy.lang.Grapes([
                @GrabConfig(systemClassLoader=true),
                @Grab('org.hsqldb:hsqldb:2.3.4')]
)

import org.hsqldb.Server
class HsqldbServer {
    static dbSettings = [
            url: 'jdbc:hsqldb:hsql://localhost/cookingdb',
            driver: 'org.hsqldb.jdbcDriver',
            user: 'sa',
            password: ''
        ]

    static startServer() {
//        loadGrapes()
        Server server = new Server()
        def logFile = new File('db.log')
        server.setLogWriter(new PrintWriter(logFile))
        server.with {
            setDatabaseName(0, 'cookingdb')
            setDatabasePath(0, 'mem:cookingdb')
            start()
        }
        println "hsqldb server started ..."
        server
    }

    static loadGrapes(){
        ClassLoader classLoader = new groovy.lang.GroovyClassLoader()
        Map[] grapez = [[group : 'org.hsqldb', module : 'hsqldb', version : '2.3.0']]
        Grape.grab(classLoader: classLoader, grapez)
        println "Class: " + classLoader.loadClass('org.hsqldb.jdbcDriver')
    }

    static  main(args){
        startServer()
    }

}
