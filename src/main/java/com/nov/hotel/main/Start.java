package com.nov.hotel.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Locale;

public class Start {

	public static Locale locale = new Locale("uk");

	public static final ApplicationContext APPLICATION_CONTEXT = new ClassPathXmlApplicationContext("/spring/app-context.xml");

	public static void main(String[] args) {

		Locale.setDefault(locale);
	}
}
