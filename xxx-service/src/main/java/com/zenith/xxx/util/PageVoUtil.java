package com.zenith.xxx.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * @author TMW
 * @date 2021/2/25 15:45
 */
public class PageVoUtil<T> extends Page<T> {

    private static final long serialVersionUID = 1802275390272573156L;

    public static <T> Page<T> toVo(Page<?> page, List<T> list) {
        final Page<T> newPage = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        newPage.setRecords(list);
        return newPage;
    }
}
