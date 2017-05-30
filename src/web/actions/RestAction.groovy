package web.actions

import groovy.sql.Sql
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestParam
import web.domain.Chapter
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


    // cookbook  list, add, update , delete
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
            def insertIds=sql.executeInsert("INSERT INTO CookBook (title, author, year) VALUES (:title, :author, :year)", book)
            book.id = insertIds[0][0]
            log.info "add new cookbook ${book}"
            return book
        } catch (e) {
            log.warn "failed to insert new cookbook ${book}, caused by  ${e.message}"
            this.response.setStatus(400)
            return "operation failed"
        }
    }


    @RequestMapping('/cookbook/update')
    updateCookBook(CookBook book) {
        log.info "try to update cookbook ${book}"
        try {
            int ret= sql.executeUpdate("UPDATE CookBook SET  title = :title, author=:author, year=:year where id=:id", book)
            log.info "update ${ret} record: ${book}"
            return book
        } catch (e) {
            log.warn "failed to insert new cookbook ${book}, caused by  ${e.message}"
            this.response.setStatus(400)
            return "operation failed"
        }
    }

    @RequestMapping('/cookbook/delete')
    deleteCookBook(CookBook book) {
        log.info "try to delete from cookbook ${book}"
        try {
            sql.execute("DELETE FROM cookbook where id=:id", book)
            log.info "delete record: ${book}"
            return book
        } catch (e) {
            log.warn "failed to insert new cookbook ${book}, caused by  ${e.message}"
            this.response.setStatus(400)
            return "operation failed"
        }
    }


    // Chapter table  add, update, deleter
    @RequestMapping('/chapter/list')
    getChapters(@RequestParam('id') Integer book_id) {
        log.info "get json list chapter ${book_id}"
        def list = []
        if(book_id==null){
            return list
        }
        sql.eachRow("SELECT * FROM chapter where book_id=:id", [id:book_id]) {
            Chapter chapter = new Chapter(id: it.id, title: it.title, book_id:it.book_id)
            list << chapter
        }
        return list
    }


    @RequestMapping('/chapter/insert')
    insertChapter(Chapter chapter) {
        log.info "try to insert new chapter ${chapter}"
        try {
            def insertIds=sql.executeInsert("INSERT INTO chapter (title, book_id) VALUES (:title, :book_id)", chapter)
            chapter.id = insertIds[0][0]
            log.info "add new chapter ${chapter}"
            return chapter
        } catch (e) {
            log.warn "failed to insert new chapter ${chapter}, caused by  ${e.message}"
            this.response.setStatus(400)
            return "operation failed"
        }
    }



    @RequestMapping('/chapter/update')
    updateChapter(Chapter chapter) {
        log.info "try to update chapter ${chapter}"
        try {
            int ret= sql.executeUpdate("UPDATE chapter SET  title = :title where id=:id", chapter)
            log.info "update ${ret} record: ${chapter}"
            return chapter
        } catch (e) {
            log.warn "failed to insert new chapter ${chapter}, caused by  ${e.message}"
            this.response.setStatus(400)
            return "operation failed"
        }
    }


    @RequestMapping('/chapter/delete')
    deleteChapter(Chapter chapter) {
        log.info "try to delete from chapter ${chapter}"
        try {
            sql.execute("DELETE FROM chapter where id=:id", chapter)
            log.info "delete record: ${chapter}"
            return chapter
        } catch (e) {
            log.warn "failed to insert new chapter ${chapter}, caused by  ${e.message}"
            this.response.setStatus(400)
            return "operation failed"
        }
    }


}
