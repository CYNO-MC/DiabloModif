package com.cyno.diablo.util;

import com.cyno.diablo.Diablo;

public class Debug {
    public static void Log(String message){
        Diablo.LOGGER.debug(message);
    }

    public static void Log(double message){
        Diablo.LOGGER.debug(message);
    }

    public static void Log(boolean condition){
        Diablo.LOGGER.debug(condition ? "true" : "false");
    }

    public static  void Separate(){
        Diablo.LOGGER.debug("#############################################################################################################");
    }

    public static void SeparateDanger(){
        Diablo.LOGGER.error("/!\\#############################################################################################################/!\\");
    }

    public static void Danger(String msg){
        Diablo.LOGGER.error(msg);
    }

    public static void Danger(double message){
        Diablo.LOGGER.error(message);
    }

    public static void Danger(boolean condition){
        Diablo.LOGGER.error(condition ? "true" : "false");
    }
}
