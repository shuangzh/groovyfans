import netsocket.Client
import netsocket.Request
import netsocket.Response

/**
 * Created by admin on 2017/5/25.
 */
@Grapes(
        @Grab("org.codehaus.groovy.modules.http-builder:http-builder:0.7.1")
)
Client client=new Client(host: 'localhost', port: 9090)
client.connect()
Response resp = client.request(new Request(code: 9000, msg: "this is request message"))
print resp
client.close()
