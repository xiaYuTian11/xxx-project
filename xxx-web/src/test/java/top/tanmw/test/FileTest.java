package top.tanmw.test;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Objects;

import static com.efficient.file.constant.FileConstant.POINT;

/**
 * @author TMW
 * @since 2023/5/11 16:56
 */
public class FileTest {

    @Test
    public void test01() {
        String name;
        String fileName = "tanmw.txt";

        int lastIndexOf = fileName.lastIndexOf("-");
        int fileNum = 1;
        String nameDefault = fileName.substring(0, fileName.lastIndexOf(POINT)) + "-" + fileNum;
        if (lastIndexOf > 0) {
            String subStr = fileName.substring(lastIndexOf + 1, fileName.lastIndexOf(POINT));
            if (NumberUtil.isNumber(subStr)) {
                fileNum = NumberUtil.binaryToInt(subStr) + 1;
                name = fileName.substring(0, lastIndexOf) + "-" + fileNum;
            } else {
                name = nameDefault;
            }
        } else {
            name = nameDefault;
        }
        fileName = name + Objects.requireNonNull(fileName).substring(fileName.lastIndexOf(POINT));
        System.out.println(fileName);
    }

    @Test
    public void test02(){
        System.out.println(DateUtil.format(new Date(), "/yyyy/MM/dd/"));
    }
}
