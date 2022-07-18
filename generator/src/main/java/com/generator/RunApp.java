package com.generator;

import top.tanmw.generator.Generator;

/**
 * @author TMW
 * @since 2022/7/9 19:35
 */
public class RunApp {
    public static void main(String[] args) throws Exception {
        Generator.run(RunApp.class.getResource("/generator.txt").getPath().toString());
    }
}
