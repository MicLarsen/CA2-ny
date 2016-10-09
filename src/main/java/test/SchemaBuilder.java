package test;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.Persistence;

/**
 *
 * @author Michael
 */
public class SchemaBuilder {
     public static void main(String[] args) {
        
        Map<String, String> prorperties = new HashMap();
//        prorperties.put("javax.persistence.sql-load-script-source", "SQL/populate.sql");        
        Persistence.generateSchema("CA2ny", prorperties);
        
    }
}
