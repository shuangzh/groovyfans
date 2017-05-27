package netsocket

import groovy.util.logging.Slf4j
import utils.ObjectReader

/**
 * Created by admin on 2017/5/24.
 */

@Grab('ch.qos.logback:logback-classic:1.2.3')
@Slf4j
class Server {
    int port
    private ServerSocket serverSocket

    def handlers = [:]

    def registerHanlder(int code, Handler handler) {
        handlers[code] = handler
    }

    def start() {
        log.info "server start, listern on ${port}"
        this.registerHanlder(9000, new EchoHanlder())

        serverSocket = new ServerSocket(port)
        for (; ;) {
            serverSocket.accept(true) { Socket socket ->
                def reqhost = socket.getInetAddress().getHostAddress()
                log.info "connection come from ${reqhost}"
                socket.withStreams { InputStream input, OutputStream output ->
                    try {

                        ObjectReader or= new ObjectReader(input, getClass().getClassLoader())
                        ObjectOutputStream oos = new ObjectOutputStream(output)

                        for (; ;) {
                            Packet reqPacket = or.readObject()
                            Request request = reqPacket.toTarget()
                            Response response
                            if (handlers[request.code]) {
                                Handler h = handlers[request.code]
                                log.info "find hanlder ${h} to handle ${request.code} request"
                                response = handlers[request.code].handle(request)
                            } else {
                                log.info "there is no handler for ${request.code} request"
                                response = new Response(code: request.code, msg: "there is no hanlder for ${request.code} request", result: -1)
                            }
                            log.info "return to ${reqhost}; ${request} --- ${response}"
                            oos.writeObject(new Packet(response))
                            oos.flush()
                        }
                    } catch (e) {
//                        e.printStackTrace()
                        log.info "service thread is terminated, caused by ${e.getClass().getName()} with ${e.message}"
                    }
                }
            }
        }
    }


}
