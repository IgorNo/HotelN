package com.nov.hotel.main;

import com.nov.hotel.entities.Settings;
import com.nov.hotel.services.impl.GetSettings;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Locale;

public class Start {

	public static final Settings SETTINGS = GetSettings.get();

	public static final ApplicationContext APPLICATION_CONTEXT = new ClassPathXmlApplicationContext("/spring/app-context.xml");

	public static void main(String[] args) {

		Locale.setDefault(SETTINGS.getLocale());
	}
}
