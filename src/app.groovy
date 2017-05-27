import netsocket.Packet
import netsocket.Request

/**
 * Created by admin on 2017/5/25.
 */

Packet packet = new Packet(new Request(code: 1000, msg: 'this is packet test'))
Request req = packet.toTarget()
println req