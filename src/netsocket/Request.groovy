package netsocket

import groovy.transform.ToString

/**
 * Created by admin on 2017/5/24.
 */

@ToString
class Request  implements  Serializable{
    int code
    String msg
    def data

}
