package com.zenith.xxx.model.dto;

import com.efficient.data.security.annotation.Sensitive;
import com.efficient.data.security.constant.SensitiveType;
import lombok.Data;

/**
 * @author TMW
 * @since 2023/7/6 16:13
 */
@Data
public class UserTest {
    @Sensitive(rule = SensitiveType.USER_ID)
    private String id;
    @Sensitive(rule = SensitiveType.CHINESE_NAME)
    private String name;
    @Sensitive(rule = SensitiveType.ID_CARD)
    private String idCard;
    @Sensitive(rule = SensitiveType.FIXED_PHONE)
    private String fixedPhone;
    @Sensitive(rule = SensitiveType.MOBILE_PHONE)
    private String mobilePhone;
    @Sensitive(rule = SensitiveType.ADDRESS)
    private String address;
    @Sensitive(rule = SensitiveType.EMAIL)
    private String email;
    @Sensitive(rule = SensitiveType.PASSWORD)
    private String password;
    @Sensitive(rule = SensitiveType.CAR_LICENSE)
    private String carLicense;
    @Sensitive(rule = SensitiveType.BANK_CARD)
    private String bankCard;
}
