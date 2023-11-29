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
        String param = "{\n" +
                "    \"pageNum\": 1,\n" +
                "    \"pageSize\": 10\n" +
                "}";
        // String param = "{\n" +
        //         "        \"module\": \"123\",\n" +
        //         "        \"userId\": \"1111\",\n" +
        //         "        \"userName\": \"谭明武\"\n" +
        //         "    }";
        // String param = "1676440625731461121";
        // String param = "id=1676848003836616706";
        String encrypt = TestDemo.encrypt(param.getBytes(StandardCharsets.UTF_8));
        System.out.println(encrypt);
        System.out.println(URLUtil.encode(encrypt));
        System.out.println(URLEncoder.encode(encrypt));
        System.out.println(URLEncoder.encode(encrypt));
        System.out.println(new String(TestDemo.decrypt("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".getBytes(StandardCharsets.UTF_8))));
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
