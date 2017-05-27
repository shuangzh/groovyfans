package netsocket

import groovy.transform.ToString

/**
 * Created by admin on 2017/5/24.
 */

@ToString(includeSuper = true)
class Response  extends Request implements Serializable{
    int result
    Response(Request request){
        this.code = request.code
        this.msg = request.msg
        this.data = request.data
    }
}
