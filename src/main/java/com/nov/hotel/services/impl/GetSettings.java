package com.nov.hotel.services.impl;

import com.nov.hotel.entities.Settings;

import java.time.LocalTime;
import java.util.Locale;

public class GetSettings {

    public static Settings get(){
        Settings settings = new Settings();
        settings.setName("Назва готелю");
        settings.setFullName("Повна назва готелю");
        settings.setStartTime(LocalTime.of(12,0));
        settings.setEndTime(LocalTime.of(12,0));
        settings.setLocale(new Locale("uk"));
        return settings;
    }
}
