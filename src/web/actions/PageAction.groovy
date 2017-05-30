package web.actions

import groovy.util.logging.Slf4j
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 * Created by admin on 2017/5/27.
 */


@Slf4j
@Grab("thymeleaf-spring4")
@Controller
@RequestMapping('/page')
class PageAction  extends BaseAction{
    @RequestMapping('/index')
    String index() {

//        def cl = this.class.classLoader
//        while (cl) {
//            println cl
//            cl = cl.parent
//        }

        println getClass().getResource("/")
        return 'index'
    }

    @RequestMapping('/cookbook')
    String toCookBook(){

        // 尼马， 少提交了代码，调试了半天，浪费时间呀。
//        println getClass().getResource("/")
//        println "############################################"
//        def cl = this.class.classLoader
//        while (cl) {
//            println cl
//            cl = cl.parent
//        }

        log.info  "go to CookBook page"
        return 'cookbook'
    }
}
