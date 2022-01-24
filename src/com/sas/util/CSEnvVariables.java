package com.sas.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Pradeep K
 *
 */
public class CSEnvVariables {
	static InputStream inStream = null;
	static Properties configuration = null;

	static InputStream methodsInStream = null;
	static Properties methodConfig = null;

	public static String getProperty(String propertyName) {
		if (null == configuration) {
			try {
				ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
				inStream = classLoader.getResourceAsStream("props.ini");
				configuration = new Properties();
				configuration.load(inStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return configuration.getProperty(propertyName);
	}

	/**
	 * Thomson Reuters method names
	 * 
	 * @param propertyName
	 * @return
	 */
	public static String getMethodNames(String propertyName) {
		if (null == methodConfig) {
			try {
				ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
				methodsInStream = classLoader.getResourceAsStream("methods.ini");
				methodConfig = new Properties();
				methodConfig.load(methodsInStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return methodConfig.getProperty(propertyName);
	}
}
