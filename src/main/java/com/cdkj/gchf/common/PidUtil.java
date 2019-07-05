package com.cdkj.gchf.common;

import com.sun.jna.Platform;

import java.lang.reflect.Field;

/**
 * @author : silver
 * @since : 2019-07-03 11:11
 */
public class PidUtil {
    public static long getPid(Process process){
        long pid = -1;
        Field field = null;
        if (Platform.isWindows()) {
            try {
                field = process.getClass().getDeclaredField("handle");
                field.setAccessible(true);
                pid = Kernel32.INSTANCE.GetProcessId((Long) field.get(process));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (Platform.isLinux() || Platform.isAIX() || Platform.isMac()) {
            try {
                Class<?> clazz = Class.forName("java.lang.UNIXProcess");
                field = clazz.getDeclaredField("pid");
                field.setAccessible(true);
                pid = (Integer) field.get(process);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return pid;
    }
}
