package netsocket

import groovy.util.logging.Slf4j
import utils.ObjectReader

/**
 * Created by admin on 2017/5/25.
 */

@Grab('ch.qos.logback:logback-classic:1.2.3')
@Slf4j
class Client {
    String host
    int port
    Socket socket

    ObjectInputStream input
    ObjectOutputStream output

    def connect() {
        this.socket = new Socket(host, port)
        this.output = new ObjectOutputStream(socket.getOutputStream())
        this.input = new ObjectReader(socket.getInputStream(), getClass().getClassLoader())
    }

    def close(){
        this.output.close()
        this.input.close()
        this.socket.close()
    }

    Response request(Request request){
        log.info "send request to ${host}:${port}; content ${request}"
        Packet reqPacket = new Packet(request)
        this.output.writeObject(reqPacket)
        this.output.flush()
        Packet respPacket = this.input.readObject()
        Response response= respPacket.toTarget()
        log.info "receive response from ${host}:${port};content ${response}"
        return  response
    }

}
