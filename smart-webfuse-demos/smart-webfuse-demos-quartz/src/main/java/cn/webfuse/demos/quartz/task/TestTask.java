package cn.webfuse.demos.quartz.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestTask {
    /**
     * 日志对象
     */
    private static final Logger LOG = LoggerFactory.getLogger(TestTask.class);

    public void run() {
        if (LOG.isInfoEnabled()) {
            LOG.info("测试任务线程开始执行");

            //new ScheduleJobService().getScheduleJob();
        }
    }
}
