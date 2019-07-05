package com.cdkj.gchf.common;


import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * @author : silver
 * @since : 2019-07-03 10:57
 */
public interface Kernel32 extends Library {
  public static Kernel32 INSTANCE = (Kernel32) Native.loadLibrary("kernel32", Kernel32.class);

  public long GetProcessId(Long hProcess);
}
