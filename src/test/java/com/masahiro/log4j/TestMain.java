package com.masahiro.log4j;

import com.masahiro.util.Log4jUtils;

public class TestMain {
	public static void main(String[] args) {
		// 加载log4j配置文件
		Log4jUtils.init("/log4j1.properties");
		// 设置日志输出级别
		Log4jUtils.setLoggerLevel(Log4jUtils.INFO);
		// 日志输出demo
		Log4jUtils.debug("this is debug");
		Log4jUtils.info("this is info");
		Log4jUtils.error("this is error");
	}

}
