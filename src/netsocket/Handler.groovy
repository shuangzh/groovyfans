package netsocket

/**
 * Created by admin on 2017/5/24.
 */
interface Handler {
    Response handle(Request request)
}