package org.example.springboot.service.log;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

@Component
public class LogManage {

    private static BlockingDeque<String> blockingDeque = new LinkedBlockingDeque<>();
    private static final String filePath = "d:/Work/Project/collect.log";
    public LogManage() {
        new LogThread().start();
    }

    public static void addLog(String msg) {
        blockingDeque.add(msg);
    }

    class LogThread extends Thread {
        @Override
        public void run() {
            while (true) {
                String log = blockingDeque.poll();
                if (!StringUtils.isEmpty(log)) {
                    // 将该log写入到磁盘中
                    FileUtils.writeText(filePath, log, true);
                }
            }
        }
    }
}
