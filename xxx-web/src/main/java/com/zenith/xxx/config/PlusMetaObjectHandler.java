package com.zenith.xxx.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.sjr.common.entity.UserTicket;
import com.sjr.common.util.RequestHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

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
        UserTicket currUser = RequestHolder.getCurrUser();
        if (Objects.nonNull(currUser)) {
            this.strictInsertFill(metaObject, "createUser", String.class, currUser.getUsername());
        } else {
            this.strictInsertFill(metaObject, "createUser", String.class, "");
        }
        this.strictInsertFill(metaObject, "isDelete", Integer.class, 0);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        UserTicket currUser = RequestHolder.getCurrUser();
        if (Objects.nonNull(currUser)) {
            this.strictUpdateFill(metaObject, "updateUser", String.class, currUser.getUsername());
        } else {
            this.strictUpdateFill(metaObject, "updateUser", String.class, "");
        }
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
    }

}


