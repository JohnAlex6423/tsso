package com.olcow.tsso;

import com.olcow.tsso.service.LoginService;
import com.olcow.tsso.service.UserService;
import com.olcow.tsso.until.JWTUntil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TssoApplicationTests {

	@Resource
	LoginService loginService;

	@Resource
	JWTUntil jwtUntil;

	@Resource UserService userService;

	@Test
	public void contextLoads() {
	}

}
