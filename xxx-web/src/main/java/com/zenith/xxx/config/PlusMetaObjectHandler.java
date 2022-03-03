package com.zenith.xxx.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * mybatis-plus 通用字段默认填充值
 *
 * @author TMW
 * @date 2021/2/24 10:22
 */
@Component
@Slf4j
public class PlusMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "createUser", String.class, "");
        this.strictInsertFill(metaObject, "isDelete", Integer.class, 0);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateUser", String.class, "");
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
    }

}


