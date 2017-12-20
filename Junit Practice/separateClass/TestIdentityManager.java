package com.pingidentity.mtfuji.tests;

import org.junit.runners.Suite;
import org.junit.runner.RunWith;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	TestParameterizedIdentityManager.class,
	TestSingleRunIdentityManager.class,
	TestParameterizedNameIdentityManager.class,
	TestParameterizedDateIdentityManager.class
		})


public class TestIdentityManager {
 
}
