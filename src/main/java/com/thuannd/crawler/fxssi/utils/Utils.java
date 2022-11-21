package com.thuannd.crawler.fxssi.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Slf4j
public class Utils {

    public static LocalDateTime fromMillisecond(long millisSecond) {
        try {
            return Instant.ofEpochMilli(millisSecond).atZone(ZoneId.systemDefault()).toLocalDateTime();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return null;
        }
    }

    public static long fromLocalDateTime(LocalDateTime localDateTime){
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

}
