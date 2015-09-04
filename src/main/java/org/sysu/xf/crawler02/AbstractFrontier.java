package org.sysu.xf.crawler02;

import java.io.File;

import com.sleepycat.bind.serial.StoredClassCatalog;
import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;

/*
 * 封装BerkeleyDB的操作
 */
public abstract class AbstractFrontier<K,V> {
	
	private Environment env;
	
	private static final String CLASS_CATALOG = "java_class_catalog";
	
	protected StoredClassCatalog javaCatalog;
	
	protected Database catalogdatabase; //用来存储类信息的数据库
	
	protected Database database; //存储实际内容的数据库
	
	public AbstractFrontier(String homeDirectory) throws DatabaseException
	{
		System.out.println("Opening enviroment in: "+homeDirectory);
		//打开env
		EnvironmentConfig envConfig = new EnvironmentConfig();
		envConfig.setTransactional(true)
				.setAllowCreate(true);
		File envHome = new File(homeDirectory);
		if(!envHome.exists())
			envHome.mkdirs();
		env = new Environment(envHome, envConfig);
		//配置并创建database
		DatabaseConfig dbConfig = new DatabaseConfig();
		dbConfig.setTransactional(true)
				.setAllowCreate(true);
		catalogdatabase = env.openDatabase(null, CLASS_CATALOG, dbConfig);
		javaCatalog = new StoredClassCatalog(catalogdatabase);
		DatabaseConfig dbConfig0 = new DatabaseConfig();
		dbConfig0.setTransactional(true)
				.setAllowCreate(true);
		database = env.openDatabase(null, "URL", dbConfig0);
		
	}
	
	public void close() throws Exception
	{
		database.close();
		javaCatalog.close();
		env.close();
	}
	
	protected abstract void put(K key,V value);
	
	protected abstract V get(K key);
	
	protected abstract V delete(K key);
}
