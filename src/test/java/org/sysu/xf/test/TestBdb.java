package org.sysu.xf.test;

import java.io.File;
import java.util.concurrent.TimeUnit;

import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseEntry;
import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;

public class TestBdb{
	
	private Environment env = null;
	
	private DatabaseConfig dbConfig = null;
	
	private Database myDatabase = null;
	
	private String fileName = "./testBdb";
	
	private String dbName = "test";
	
	public static void main(String[] args)
	{
		
	}
	
	public void openDatabase()
	{
		try
		{
			EnvironmentConfig envConfig = new EnvironmentConfig();
			envConfig.setAllowCreate(true).setTransactional(true)
				.setReadOnly(false).setTxnTimeout(10000, TimeUnit.MILLISECONDS)
				.setLockTimeout(10000, TimeUnit.MILLISECONDS);
			File file = new File(fileName);
			if(!file.exists())
				file.mkdirs();
			env = new Environment(file, envConfig);
			
			dbConfig = new DatabaseConfig().setAllowCreate(true)
					 .setTransactional(true).setReadOnly(false);
			
			if(myDatabase == null)
				myDatabase = env.openDatabase(null, dbName, dbConfig);
			System.out.println(dbName+"数据库中数据个数： "+myDatabase.count());
			 
		}catch(DatabaseException e)
		{
			e.printStackTrace();
		}
	}
	
	
}