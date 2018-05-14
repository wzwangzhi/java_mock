import com.wangzhi.test.mock.kit.HttpRequest;
import sun.misc.BASE64Decoder;

import java.io.IOException;

public class Test {

    @org.junit.Test
    public void main() {
        String encode = HttpRequest.Base64.encode(Math.random() + Math.random() + Math.random() + Math.random() + Math.random() + "userId=" + 1);
        System.out.println(encode);
        String encode1 = HttpRequest.Base64.encode(Math.random() + Math.random() + Math.random() + Math.random() + Math.random() + "userId=" + 2);
        System.out.println(encode1);
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] bytes = new byte[0];
        try {
            bytes = decoder.decodeBuffer(encode);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(new String(bytes).split("=")[1]);
    }
}
