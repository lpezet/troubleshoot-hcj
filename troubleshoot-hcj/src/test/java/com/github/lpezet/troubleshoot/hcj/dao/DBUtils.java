/**
 * 
 */
package com.github.lpezet.troubleshoot.hcj.dao;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author luc
 *
 */
public class DBUtils {
	
	private JdbcTemplate mTemplate;
	private static final Object LOCK = new Object();
	
	public void setDataSource(DataSource pDS) {
		mTemplate = new JdbcTemplate(pDS);
	}

	public synchronized void validateDatabase() {
		synchronized (LOCK) {	
			//int oCount = mTemplate.queryForObject("SELECT count(*) FROM user_codes", Integer.class);
			//if (oCount > 0)  return;
			LineNumberReader oReader = null;
	        //BufferedReader oReader = null;
			FileReader oFR = null;
			try {
				oFR = new FileReader("src/test/db/testdata.sql");
				oReader = new LineNumberReader(oFR);
				String oLine = null;
				StringBuffer oCommand = new StringBuffer();
				String oDelimiter = ";";	        
				while ((oLine = oReader.readLine()) != null) {
					String oCleanLine = clean(oLine);				
					if (oCleanLine.length() < 1) {
						// nop
					} else if (oCleanLine.startsWith("--") || oCleanLine.startsWith("#") || oCleanLine.startsWith("//")) {
	                    //logger.debug(trimmedLine);
	                } else if (oCleanLine.matches("^DELIMITER .*")) {                	
	                	oDelimiter = oCleanLine.substring(10).trim();                	
	                } else {
	                    if (oCleanLine.endsWith(oDelimiter)) {
	                    	oCommand.append(oCleanLine.substring(0, oCleanLine.lastIndexOf(oDelimiter)));
	                    	mTemplate.update(oCommand.toString());
	                    	oCommand = new StringBuffer();
	                    } else {
	                    	oCommand.append(oCleanLine).append(" ");
	                    }
	                }
				}
				System.out.println(">> Database initialized...");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (oReader != null) try { oReader.close(); } catch (Exception e) {} 
				if (oFR != null) try { oFR.close(); } catch (Exception e) {}
			}
		}
	}

	private String clean(String pLine) {
		String oResult = pLine.replaceAll("-- .*", "");
		return oResult.trim();
	}
}