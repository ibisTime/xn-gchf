package com.cdkj.gchf.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import lombok.EqualsAndHashCode;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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

    public void storeFfmpegCommand(String userId, String uuid, Process process, String command) {
        ffmpegInfoSet.add(new FfmpegInfo(userId, uuid, process, command));
    }

    public void storeFfmpegCommand(String userId, String uuid, String command) {
        for(FfmpegInfo ffmpegInfo : ffmpegInfoSet){
            if(ffmpegInfo.getCommand().equals(command)){
                ffmpegInfo.getUserSet().add(new HlsUser(userId, uuid));
            }
        }
    }

    public void releaseFfmpegResource(String userId, String uuid) {
        if(CollectionUtils.isEmpty(ffmpegInfoSet)){
            return;
        }

        Iterator<FfmpegInfo> infos = ffmpegInfoSet.iterator();
        HlsUser hlsUser = new HlsUser(userId, uuid);

        while(infos.hasNext()){
            FfmpegInfo ffmpegInfo = infos.next();

            if (ffmpegInfo.getUserSet().contains(hlsUser)) {
                ffmpegInfo.getUserSet().remove(hlsUser);

                if (CollectionUtils.isEmpty(ffmpegInfo.getUserSet())) {
                    infos.remove();
                    ffmpegInfo.getProcess().destroy();
                    logger.info("释放ffmpeg资源:{" + ffmpegInfo.getPid() + "}成功");
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

@EqualsAndHashCode
class FfmpegInfo{

    //获取流的用户
    private Set<HlsUser> userSet;

    //进程
    private Process process;

    //进程PID
    private Long pid;

    //命令行
    private String command;

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

    Set<HlsUser> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<HlsUser> userSet) {
        this.userSet = userSet;
    }

    Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    FfmpegInfo(String userId, String uuid, Process process, String command) {
        this.userSet = new HashSet<>();
        this.userSet.add(new HlsUser(userId, uuid));
        this.process = process;
        this.pid = PidUtil.getPid(process);
        this.command = command;
    }
}

class HlsUser {

    //用户编号
    private String userId;

    //UUID
    private String uuid;

    public HlsUser(String userId, String uuid) {
        this.userId = userId;
        this.uuid = uuid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HlsUser hlsUser = (HlsUser) o;
        return Objects.equals(userId, hlsUser.userId) &&
                Objects.equals(uuid, hlsUser.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, uuid);
    }
}

class PrintStream extends Thread {
    java.io.InputStream __is = null;

    PrintStream(java.io.InputStream is) {
        __is = is;
    }

    @Override
    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(__is));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
//                System.out.print(line + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                __is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}