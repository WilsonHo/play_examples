package examples.json.QuartzJob;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import org.apache.log4j.Logger;


/**
 * Created by wilson on 3/14/17.
 */
public class TestJob implements Job {

    private Logger log = Logger.getLogger(TestJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.debug("TestJob run successfully...");
        System.out.println("TestJob run successfully...");
    }
}
