package org.example.springboot.service.log;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 异步日志管理
 * 使用阻塞队列 + 后台线程将日志异步写入磁盘文件
 * 避免日志 I/O 阻塞业务线程
 */
@Component
public class LogManage {

    /** 日志消息阻塞队列 */
    private static BlockingDeque<String> blockingDeque = new LinkedBlockingDeque<>();
    /** 日志文件存储路径 */
    private static final String filePath = "d:/Work/Project/collect.log";

    public LogManage() {
        // 启动后台线程持续消费日志队列
        new LogThread().start();
    }

    /** 添加日志消息到队列 */
    public static void addLog(String msg) {
        blockingDeque.add(msg);
    }

    /** 后台日志写入线程，不断从队列取出消息并写入文件 */
    class LogThread extends Thread {
        @Override
        public void run() {
            while (true) {
                String log = blockingDeque.poll();
                if (!StringUtils.isEmpty(log)) {
                    FileUtils.writeText(filePath, log, true);
                }
            }
        }
    }
}
