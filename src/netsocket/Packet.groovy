package netsocket

import utils.ObjectReader

/**
 * Created by admin on 2017/5/24.
 */
class Packet implements Serializable{
    byte[] bytes

    Packet(target){
        ByteArrayOutputStream bos = new ByteArrayOutputStream()
        ObjectOutputStream oos = new ObjectOutputStream(bos)
        oos.writeObject(target)
        oos.flush()
        this.bytes = bos.toByteArray()
        oos.close()
        bos.close()
    }

    def toTarget(){
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes)
        ObjectReader ois = new ObjectReader(bis, this.getClass().getClassLoader())
        def target = ois.readObject()
        ois.close()
        bis.close()
        return  target
    }



}
