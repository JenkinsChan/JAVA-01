import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Method;

public class HelloClassLoader extends ClassLoader{
    public static void main(String[] args) throws Exception{
        Class cls = new HelloClassLoader().findClass("Hello");
        Object obj = cls.newInstance();
        Method method = cls.getMethod("hello");
        method.invoke(obj);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try(InputStream inputStream = HelloClassLoader.class.getClassLoader().getResourceAsStream("Hello.xlass")) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] tempBytes = new byte[1];
            while (inputStream.read(tempBytes) != -1){
                tempBytes[0] =  (byte) (255 - (int)tempBytes[0]);
                outputStream.write(tempBytes);
            }
            byte[] sourceBytes = outputStream.toByteArray();
            return defineClass(name, sourceBytes, 0, sourceBytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return super.findClass(name);
    }
}
