package com.olcow.tsso.utils;

import org.springframework.stereotype.Component;

@Component
public class RegularUtils {

    /**
     * 判断用户名是否只包含字母数字_-@
     * @param username 要判断的东西
     * @return 是否匹配
     */
    public boolean usernameTest(String username){
        return username != null && username.matches("^[0-9a-zA-Z_\\-@]{4,12}$");
    }

    /**
     * 判断当前password是否合法
     * @param password 密码
     * @return 是否匹配
     */
    public boolean passwordTest(String password){
        return password != null && password.matches("^[0-9a-zA-Z~!@#$%^&*()_+|}{?><,./';:=\\[\\]\\\\`-]{6,18}$");
    }

    /**
     * 判断当前Email是否合法
     * @param email 邮箱
     * @return 是否匹配
     */
    public boolean emailTest(String email){
        return email != null && email.matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
    }
}
