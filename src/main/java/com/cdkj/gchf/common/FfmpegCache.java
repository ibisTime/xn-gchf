package com.cdkj.gchf.common;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/**
 * @author : silver
 * @since : 2019-07-03 10:33
 */
@Component
public class FfmpegCache {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Set<FfmpegInfo> ffmpegInfoSet = new HashSet<>();

    public FfmpegInfo checkFfmpegCommand(String command){
        FfmpegInfo ffmpegInfo = null;

        if(CollectionUtils.isNotEmpty(ffmpegInfoSet)){
            for(FfmpegInfo info : ffmpegInfoSet){
                if(info.getCommand().equals(command)){
                    ffmpegInfo = info;
                    break;
                }
            }
        }

        return ffmpegInfo;
    }

    public void storeFfmpegCommand(String userId, Long pid, String command){
        ffmpegInfoSet.add(new FfmpegInfo(userId, pid, command));
    }

    public void storeFfmpegCommand(String userId, String command){
        for(FfmpegInfo ffmpegInfo : ffmpegInfoSet){
            if(ffmpegInfo.getCommand().equals(command)){
                ffmpegInfo.getUserIdSet().add(userId);
            }
        }
    }

    public void releaseFfmpegResource(String userId){
        if(CollectionUtils.isEmpty(ffmpegInfoSet)){
            return;
        }

        Iterator<FfmpegInfo> infos = ffmpegInfoSet.iterator();

        while(infos.hasNext()){
            FfmpegInfo ffmpegInfo = infos.next();

            if(ffmpegInfo.getUserIdSet().contains(userId)){
                ffmpegInfo.getUserIdSet().remove(userId);

                if(CollectionUtils.isEmpty(ffmpegInfo.getUserIdSet())){
                    try {
                        infos.remove();
                        Runtime.getRuntime().exec("kill -9 " + ffmpegInfo.getPid());
                        logger.info("释放ffmpeg资源:{"+ffmpegInfo.getPid()+"}成功");
                    } catch (IOException e) {
                        logger.error("释放ffmpeg资源:{0}失败", ffmpegInfo.getPid(), e);
                    }
                }
            }
        }
    }

    @Scheduled(cron = "0 0/2 * * * ?")
    public void cleanFfmpegCommand(){

        if(CollectionUtils.isEmpty(ffmpegInfoSet)){
            return;
        }

        Iterator<FfmpegInfo> infos = ffmpegInfoSet.iterator();
        String outputString = null;

        try {
            String command = "ps -ef";
            Process process = Runtime.getRuntime().exec(command);
            outputString = parseString(process.getInputStream());
        }catch (Exception e){
            e.printStackTrace();
        }

        while(infos.hasNext()){
            FfmpegInfo ffmpegInfo = infos.next();

            if(StringUtils.isNotBlank(outputString) && !outputString.contains(ffmpegInfo.getPid().toString())){
                infos.remove();
            }

        }

    }

    public void handleOutputStream(Process process){
        //由于JVM只提供有限缓存空间，当外部程序（子进程）的输出流超出了这个有限空间而父进程
        //又不读出这些数据，子进程会被阻塞waitFor()永远都不会返回，就会造成死锁
        new PrintStream(process.getErrorStream()).start();
        new PrintStream(process.getInputStream()).start();
    }

    private String parseString(InputStream inputStream){
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        StringBuffer b=new StringBuffer();

        try{
            while ((line=br.readLine())!=null) {
                b.append(line+"\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return b.toString();
    }

}

class FfmpegInfo{

    //获取流的用户
    private Set<String> userIdSet;

    //进程PID
    private Long pid;

    //命令行
    private String command;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FfmpegInfo that = (FfmpegInfo) o;
        return Objects.equals(pid, that.pid) &&
                Objects.equals(command, that.command);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pid, command);
    }

    Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    Set<String> getUserIdSet() {
        return userIdSet;
    }

    public void setUserIdSet(Set<String> userIdSet) {
        this.userIdSet = userIdSet;
    }

    FfmpegInfo(String userId, Long pid, String command) {
        this.userIdSet = new HashSet<>();
        this.userIdSet.add(userId);
        this.pid = pid;
        this.command = command;
    }
}

class PrintStream extends Thread {
    java.io.InputStream __is = null;

    public PrintStream(java.io.InputStream is) {
        __is = is;
    }

    @Override
    public void run() {
        try {
            while (this != null) {
                int _ch = __is.read();
                if (_ch != -1) {
                    System.out.print((char) _ch);
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}