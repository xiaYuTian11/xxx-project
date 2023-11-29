package top.tanmw.test.file;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.NumberUtil;
import org.junit.jupiter.api.Test;

import static com.efficient.file.constant.FileConstant.HORIZONTAL_BAR;
import static com.efficient.file.constant.FileConstant.POINT;

/**
 * @author TMW
 * @since 2023/9/25 16:00
 */
public class FIleTest {

    @Test
    public void test() {
        String basePath = "F:\\work\\project\\zenith\\zenith-cppcc\\upload\\upload\\file\\word\\2023\\09\\25";
        String fileName = "重庆数字政协0921日报-2.docx";

        int pointIndexOf = fileName.lastIndexOf(POINT);
        String suffix = fileName.substring(pointIndexOf);
        int fileNum = 1;
        String nameDefault = fileName.substring(0, fileName.lastIndexOf(POINT)) + HORIZONTAL_BAR + 1 + suffix;
        String name = fileName;
        while (FileUtil.file(basePath, name).exists()) {
            int lastIndexOf = name.lastIndexOf(HORIZONTAL_BAR);
            if (lastIndexOf > 0) {
                String subStr = name.substring(lastIndexOf + 1, name.lastIndexOf(POINT));
                if (NumberUtil.isNumber(subStr)) {
                    fileNum = Integer.parseInt(subStr) + 1;
                    name = name.substring(0, lastIndexOf) + HORIZONTAL_BAR + fileNum + suffix;
                } else {
                    name = nameDefault;
                }
            } else {
                name = nameDefault;
            }
        }
        fileName = name;
        System.out.println(fileName);
    }
}
