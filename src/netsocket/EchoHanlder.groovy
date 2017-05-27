package netsocket

/**
 * Created by admin on 2017/5/25.
 */
class EchoHanlder  implements Handler{

    @Override
    Response handle(Request request) {
        Response resp = new Response(request)
        resp.result = 0
        return  resp
    }

}
