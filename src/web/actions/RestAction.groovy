package web.actions

import groovy.sql.Sql
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import web.domain.CookBook

/**
 * Created by admin on 2017/5/27.
 */

@Slf4j
@RestController
@RequestMapping('/rest')
class RestAction extends BaseAction {

    @Autowired
    Sql sql

    @RequestMapping('/home')
    String home() {
        println getClass().getResource("/")
        return "this is rest Action"
    }

    @RequestMapping('/cookbook/list')
    getCookBooks() {
        log.info 'get json cookbook list'
        def list = []
        sql.eachRow("SELECT  * FROM CookBook") {
            CookBook book = new CookBook(id: it.id, title: it.title, author: it.author, year: it.year)
            list << book
        }
        return list
    }

    @RequestMapping('/cookbook/insert')
    insertCookBook(CookBook book) {
        log.info "try to insert new cookbook ${book}"
        try {
            sql.execute("INSERT INTO CookBook VALUES (:id, :title, :author, :year)", book)
            log.info "add new cookbook ${book}"
            return book
        } catch (e) {
            log.warn "failed to insert new cookbook ${book}, caused by  ${e.message}"
            this.response.setStatus(400)
            return "operation failed"
        }
    }
}
