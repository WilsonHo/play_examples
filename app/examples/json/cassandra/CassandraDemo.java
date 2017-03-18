package examples.json.cassandra;

import com.datastax.driver.core.*;
import com.datastax.driver.core.exceptions.NoHostAvailableException;

import java.net.InetAddress;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wilson on 3/3/17.
 */
public class CassandraDemo {
    private Session session;

    public CassandraDemo() {
        this.session = CassandraConnector.getInstance().getSession();
    }

    public Session getSession() {
        return session;
    }

    public static void main(String[] args) {
        CassandraDemo cassandra = new CassandraDemo();
//        SimpleStatement statement = new SimpleStatement("select now() from system.local");
//        statement.setConsistencyLevel(ConsistencyLevel.ALL);
//        statement.enableTracing();
//        ResultSet results = cassandra.getSession().execute(statement);
//        System.out.println("1.----- " + results);

//        Instant now = Instant.now();
//        String query = "INSERT INTO cassandra_examples.time_example (id, time_ex1)\n" +
//                "VALUES (now(),'" + now + "');";
//        ResultSet results = cassandra.getSession().execute(query);

        cassandra.getSession().execute("INSERT INTO example (id, t) VALUES (?, ?)", 2,
                Instant.now());

        System.out.println("2.----- " );

        cassandra.getSession().close();
    }

    private boolean healthCheckCassandra() {
        try {
            SimpleStatement statement = new SimpleStatement(
                    "select now() from system.local");
            statement.setConsistencyLevel(ConsistencyLevel.ALL);
            statement.enableTracing();
            ResultSet results = CassandraConnector.getInstance().getSession().execute(statement);
            final ExecutionInfo executionInfo = results.getExecutionInfo();
            QueryTrace queryTrace = executionInfo.getQueryTrace();
            Set<Host> hosts = CassandraConnector.getInstance().getSession().getCluster().getMetadata().getAllHosts();

            if (queryTrace != null) {
                final Set<InetAddress> missingHosts = new HashSet<>(hosts.size());
                for (Host host : hosts) {
                    missingHosts.add(host.getSocketAddress().getAddress());
                }
                for (QueryTrace.Event event : queryTrace.getEvents()) {
                    missingHosts.remove(event.getSource());
                }
            }
        } catch (NoHostAvailableException e) {
            return false;
        }
        return true;
    }
}
