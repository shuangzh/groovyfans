package web.actions

import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.ModelAttribute

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession

/**
 * Created by admin on 2017/5/27.
 */

@Grab(group='org.springframework.boot', module='spring-boot-starter-web', version='1.5.3.RELEASE')
class BaseAction {
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;
    protected ModelMap modelMap;
    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap){
        this.request = request;
        this.response = response;
        this.session = request.getSession();
        this.modelMap = modelMap;
    }
}
