package com.masahiro.util;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * 动态日志输出
 * 
 * @author Masahiro
 *
 */
public class Log4jUtils {
	private static Log4jUtils instance = null;
	private static Logger logger = null;
	public static final int DEBUG = 0;
	public static final int INFO = 1;
	public static final int ERROR = 2;
	private static Properties p = null;

	static {
		StackTraceElement[] stack = new Throwable().getStackTrace();
		logger = Logger.getLogger(stack[1].getClassName());
	}

	Log4jUtils() {

	}

	/**
	 * 加载指定log4j的配置文件
	 * 
	 * @param propertyPath
	 *            配置文件路径(例:propertyPath = "/log4jxxx.properties")
	 */
	Log4jUtils(String propertyPath) {
		p = new Properties();
		try {
			p.load(Log4jUtils.class.getResourceAsStream(propertyPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初始化日志
	 * 
	 * @param propertyPath
	 * @return
	 */
	public static Log4jUtils init(String propertyPath) {
		synchronized (Log4jUtils.class) {
			if (instance == null) {
				instance = new Log4jUtils(propertyPath);
			}
		}
		return instance;
	}

	/**
	 * 动态设置日志输出等级 Log4jUtils.ERROR Log4jUtils.DEBUG Log4jUtils.INFO
	 * 
	 * @param level
	 * 
	 */
	public static void setLoggerLevel(int level) {
		switch (level) {
		case 0:
			p.setProperty("log4j.rootLogger", "DEBUG, console, file");
			break;
		case 1:
			p.setProperty("log4j.rootLogger", "INFO, console, file");
			break;
		case 2:
			p.setProperty("log4j.rootLogger", "ERROR, console, file");
			break;
		default:
			p.setProperty("log4j.rootLogger", "INFO, console, file");
			break;
		}
		PropertyConfigurator.configure(p);
	}

	public static void debug(String msg) {
		logger.log(Log4jUtils.class.getName(), Level.DEBUG, msg, null);
	}

	public static void info(String msg) {
		logger.log(Log4jUtils.class.getName(), Level.INFO, msg, null);
	}

	public static void error(String msg) {
		logger.log(Log4jUtils.class.getName(), Level.ERROR, msg, null);
	}
}
