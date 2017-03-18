package examples.json.cassandra;

import com.datastax.driver.core.*;
import com.datastax.driver.core.policies.ConstantReconnectionPolicy;
import com.datastax.driver.core.policies.DowngradingConsistencyRetryPolicy;
import com.datastax.driver.core.policies.RoundRobinPolicy;
import com.datastax.driver.extras.codecs.jdk8.InstantCodec;
import com.datastax.driver.extras.codecs.jdk8.ZonedDateTimeCodec;
import com.typesafe.config.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static javax.security.auth.login.Configuration.getConfiguration;

/**
 * Provides and manages connections to Cassandra.
 */
public class CassandraConnector {

    private static final Logger LOG = LoggerFactory.getLogger(CassandraConnector.class);

    private static CassandraConnector instance = null;
    private Cluster cluster;
    private Session session;

    private CassandraConnector() {
        String HOSTS_STRING = "localhost";
        String KEY_SPACE = "cassandra_examples";
        int PORT = 9411;
        LOG.info("cassandra -> host: {} port: {} with keySpace: {}", HOSTS_STRING, PORT, KEY_SPACE);

        Cluster.Builder builder = Cluster.builder()
                .withLoadBalancingPolicy(new RoundRobinPolicy())
                .withRetryPolicy(DowngradingConsistencyRetryPolicy.INSTANCE)
                .withReconnectionPolicy(new ConstantReconnectionPolicy(100L));

        for (String host : HOSTS_STRING.split(",")) {
            cluster = builder.addContactPoint(host).withPort(PORT).build();
        }

        TupleType tupleType = cluster.getMetadata()
                .newTupleType(DataType.timestamp(), DataType.varchar());

        cluster.getConfiguration().getCodecRegistry()
                .register(InstantCodec.instance);
//                .register(new ZonedDateTimeCodec(tupleType));

        session = cluster.connect(KEY_SPACE);

        Metadata metadata = cluster.getMetadata();
        LOG.info("cassandra -> cluster: {}", metadata.getClusterName());
        for (Host host : metadata.getAllHosts()) {
            LOG.info("cassandra -> data center: {}; Host: {}; Rack: {}", host.getDatacenter(), host.getAddress(), host
                    .getRack());
        }
    }

    /**
     * get new or existing cassandra instance
     *
     * @return CassandraConnector
     */
    public static CassandraConnector getInstance() {
        if (instance == null) {
            instance = new CassandraConnector();
        }
        return instance;
    }

    /**
     * get cassandra session
     *
     * @return Session
     */
    public Session getSession() {
        return session;
    }
}