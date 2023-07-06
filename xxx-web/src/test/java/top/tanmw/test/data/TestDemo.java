package top.tanmw.test.data;

import cn.hutool.core.util.URLUtil;
import org.junit.jupiter.api.Test;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author TMW
 * @since 2023/7/5 14:03
 */
public class TestDemo {
    private static String key = "http://tanmw.top";

    @Test
    public void test01() throws Exception {
        // String param = "{\n" +
        //         "    \"module\":\"123\",\n" +
        //         "    \"userId\":\"1111\",\n" +
        //         "    \"userName\":\"谭明武\"\n" +
        //         "}";
        // String param = "{\n" +
        //         "        \"module\": \"123\",\n" +
        //         "        \"userId\": \"1111\",\n" +
        //         "        \"userName\": \"谭明武\"\n" +
        //         "    }";
        // String param = "1676440625731461121";
        String param = "id=1676848003836616706";
        String encrypt = TestDemo.encrypt(param.getBytes(StandardCharsets.UTF_8));
        System.out.println(encrypt);
        System.out.println(URLUtil.encode(encrypt));
        System.out.println(URLEncoder.encode(encrypt));
        System.out.println(URLEncoder.encode(encrypt));
        System.out.println(new String(TestDemo.decrypt("Yu5i8L0UAuo5rwMp+/DrhAAdqepI2HmZjod0/dazyTc=".getBytes(StandardCharsets.UTF_8))));
    }

    public static String encrypt(byte[] data) throws Exception {
        Cipher cipher = getCipher(key, 1);
        return Base64.getEncoder().encodeToString(cipher.doFinal(data));
        // return new String(cipher.doFinal(data));
    }

    public static byte[] decrypt(byte[] data) throws Exception {
        Cipher cipher = getCipher(key, 2);
        return cipher.doFinal(Base64.getDecoder().decode(data));
        // return cipher.doFinal(data);
    }

    public static Cipher getCipher(String key, int model) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(model, secretKeySpec);
        return cipher;
    }

    @Test
    public void test02() throws Exception {
        String str = "VhIYudqAPO9iVdDMj3+wEioRpySrqtMgRicGxZ+CxwwoNYgyU2vXwpUprCnLzPXZzPOVvffTpcTNj/dzx3rtJteLrdAuKy2ToO1I6RmGlCf+y6b9ZuT3S/7dyKwFV8jJ";
        System.out.println(new String(TestDemo.decrypt(str.getBytes(StandardCharsets.UTF_8))));
    }
}
