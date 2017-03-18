package examples.json.QuartzJob;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by wilson on 3/14/17.
 */
public class JobScheduler {
    public static void main(String[] args) {

        try {

            // specify the job' s details..
            JobDetail job = JobBuilder.newJob(TestJob.class)
                    .withIdentity("testJob")
                    .build();

            // specify the running period of the job
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withIntervalInSeconds(30)
                            .repeatForever())
                    .build();

            //schedule the job
            SchedulerFactory schFactory = new StdSchedulerFactory();
            Scheduler sch = schFactory.getScheduler();
            sch.start();
            sch.scheduleJob(job, trigger);

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }


}
