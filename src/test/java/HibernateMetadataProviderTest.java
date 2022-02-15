import org.junit.Test;
import ru.sfedu.crm.lab1.api.HibernateMetadataProvider;

import static org.junit.jupiter.api.Assertions.*;
import static ru.sfedu.crm.Constants.*;

public class HibernateMetadataProviderTest {
    HibernateMetadataProvider hmp = new HibernateMetadataProvider();
    @Test
    public void getSchemas() {
        assertNotNull(hmp.getRecords(SQL_ALL_SCHEMAS));
    }

    @Test
    public void getUsers() {
        assertNotNull(hmp.getRecords(SQL_ALL_USERS));
    }

    @Test
    public void getTables() {
        assertNotNull(hmp.getRecords(SQL_ALL_TABLES));
    }

    @Test
    public void getColumns() {
        assertNotNull(hmp.getRecords(SQL_ALL_COLUMNS));
    }
}