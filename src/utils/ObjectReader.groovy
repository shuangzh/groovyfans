package utils

/**
 * Created by admin on 2017/5/25.
 */
class ObjectReader extends ObjectInputStream{
    ClassLoader loader

    ObjectReader(InputStream inputStream, ClassLoader loader){
        super(inputStream)
        this.loader = loader

    }

    protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException{
        if (loader == null) {
            return super.resolveClass(desc)
        }else {
            return  Class.forName(desc.getName(), true, loader)
        }
    }

}
