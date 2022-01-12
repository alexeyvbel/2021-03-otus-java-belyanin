import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.cachehw.HwListener;
import ru.otus.cachehw.MyCache;
import ru.otus.core.repository.DataTemplate;
import ru.otus.core.repository.DataTemplateHibernate;
import ru.otus.core.repository.HibernateUtils;
import ru.otus.core.sessionmanager.TransactionManager;
import ru.otus.core.sessionmanager.TransactionManagerHibernate;
import ru.otus.crm.dbmigrations.MigrationsExecutorFlyway;
import ru.otus.crm.model.Client;
import ru.otus.crm.service.DbServiceClientImpl;

import java.util.Optional;

public class CacheTest {

    private static Logger log = LoggerFactory.getLogger(CacheTest.class);
    private static final String HIBERNATE_CFG_FILE = "hibernate.cfg.xml";
    private static final Configuration configuration = new Configuration().configure(HIBERNATE_CFG_FILE);;
    private static SessionFactory sessionFactory;
    private static TransactionManager transactionManager;
    private static DataTemplate<Client> clientTemplate;

    @BeforeAll
    static void setUp() {
        new MigrationsExecutorFlyway(configuration).executeMigrations();
        sessionFactory = HibernateUtils.buildSessionFactory(configuration, Client.class);
        transactionManager = new TransactionManagerHibernate(sessionFactory);
        clientTemplate = new DataTemplateHibernate<>(Client.class);
    }

    @Test
    void testCacheDb() {

        var cache = new MyCache<Long, Client>();
        var listener = new HwListener<Long, Client>() {
            @Override
            public void notify(Long key, Client value, String action) {
                log.info("key:{}, value:{}, action: {}", key, value, action);
            }
        };
        cache.addListener(listener);

        var dbServiceClient = new DbServiceClientImpl(transactionManager, clientTemplate, cache);

        for (int i = 0; i <= 10000; i++) {
            long id = dbServiceClient.saveClient(new Client("name" + i)).getId();
            Optional<Client> client = dbServiceClient.getClient(id);
            log.info("Used cache: {}", id);
        }
        log.info("Cache size: {} ", cache);
        cache.removeListener(listener);
    }

    @Test
    void testDbNoCache() {

        var dbServiceClient = new DbServiceClientImpl(transactionManager, clientTemplate, null);

        for (int i = 0; i <= 10000; i++) {
            long id = dbServiceClient.saveClient(new Client("name" + i)).getId();
            Optional<Client> client = dbServiceClient.getClient(id);
            log.info("No cache: {}", id);
        }
    }
}
