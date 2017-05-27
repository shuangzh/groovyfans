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
        println getClass().getResource("/")
        return 'index'
    }

    @RequestMapping('/cookbook')
    String toCookBook(){
        log.info  "go to CookBook page"
        return 'cookbook'
    }
}
