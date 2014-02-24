package ool.com.orientdb.client;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

import ool.com.orientdb.utils.Config;
import ool.com.orientdb.utils.ConfigImpl;
import ool.com.orientdb.utils.Definition;

import java.sql.SQLException;

import org.apache.log4j.Logger;

public class ConnectionManager {
	
	private static final Logger logger = Logger.getLogger(ConnectionManager.class);

    private static ConnectionManager dbAccessManager = null;
    
    private static ODatabaseDocumentTx database;
    
    /**
     * @param config
     */
    private ConnectionManager() {
    }

    /**
     * @param config
     * @throws SQLException 
     */
    private static void initialize(Config config) throws SQLException {
    	if (logger.isDebugEnabled()) {
    		logger.debug(String.format("initialize(config=%s) - start", config));
    	}
    	
        String user = config.getString(Definition.CONFIG_KEY_DB_USER);
        String password = config.getString(Definition.CONFIG_KEY_DB_PASSWORD);
        String url = config.getString(Definition.CONFIG_KEY_DB_URL);
        
        try {
        	database = new ODatabaseDocumentTx(url).open(user, password);
        	if (database == null) {
        		String message = "failed to load database.";
        		throw new RuntimeException(message);
        	}
        } catch (RuntimeException re) {
        	throw new SQLException(re.getMessage());
        }
    	if (logger.isDebugEnabled()) {
    		logger.debug("initialize() - end");
    	}
    }

    /**
     * @return instance
     * @throws SQLException 
     */
    synchronized public static ConnectionManager getInstance() throws SQLException {
    	if (logger.isDebugEnabled()) {
    		logger.debug("getInstance() - start");
    	}
        if (dbAccessManager == null) {
            dbAccessManager = new ConnectionManager();
        }
        if (database == null || database.isClosed()) {
        	initialize(new ConfigImpl());
        }
    	if (logger.isDebugEnabled()) {
    		logger.debug(String.format("getInstance(dbAccessManager=%s) - end", dbAccessManager));
    	}
        return dbAccessManager;
    }
    
    /**
     * @param config
     * @return instance
     * @throws SQLException 
     */
    synchronized public static ConnectionManager getInstance(Config config) throws SQLException {
    	if (logger.isDebugEnabled()) {
    		logger.debug(String.format("getInstance(config=%s) - start", config));
    	}
        if (dbAccessManager == null) {
            dbAccessManager = new ConnectionManager();
        }
        if (database == null || database.isClosed()) {
        	initialize(config);
        }
    	if (logger.isDebugEnabled()) {
    		logger.debug(String.format("getInstance(dbAccessManager=%s) - end", dbAccessManager));
    	}
        return dbAccessManager;
    }

    /**
     * return database
     * @return database object
     */
    synchronized public ODatabaseDocumentTx getDatabase() {
    	if (logger.isDebugEnabled()) {
    		logger.debug("getDatabase() - start");
    		logger.debug(String.format("getDatabase(database=%s) - end", database));
    	}
        return database;
    }
    
    /**
     * commit
     * 
     * @param database
     * @throws SQLException
     */
    synchronized public void commit(ODatabaseDocumentTx database) throws SQLException {
    }

    /**
     * rollback
     * 
     * @param database
     * @throws SQLException
     */
    synchronized public void rollback(ODatabaseDocumentTx database) throws SQLException {
    }
    
    /**
     * close database
     * 
     * @param database
     */
    synchronized public void close(ODatabaseDocumentTx database) {
    	if (logger.isDebugEnabled()) {
    		logger.debug(String.format("close(database=%s) - start", database));
    	}
        if (database != null && !database.isClosed()) {
            database.close();
        }
    	if (logger.isDebugEnabled()) {
    		logger.debug("close() - end");
    	}
    }

}
