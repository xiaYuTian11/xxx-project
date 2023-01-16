package com.zenith.xxx.service.base;

import com.efficient.auth.api.AuthService;
import com.efficient.auth.model.dto.LoginInfo;
import com.efficient.auth.model.entity.UserCheck;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author TMW
 * @since 2023/1/16 16:57
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public UserCheck getUserInfo(LoginInfo loginInfo) {
        return new UserCheck();
    }

    @Override
    public List<String> getUserOperationList(String s) {
        return new ArrayList<>();
    }

    @Override
    public List<String> getUserPermissionList(String s) {
        return new ArrayList<>();
    }

    @Override
    public Object getUserExtendInfo(String s) {
        return new UserCheck();
    }

    @Override
    public boolean unLockUser(String s) {
        return false;
    }

    @Override
    public boolean lockUser(String s, Date date) {
        return false;
    }
}
