package br.com.qualidadedesoftware.configuration;

import java.io.File;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;

public class Log extends Suporte 
{	
    public void log4j() {
		token = spd.format(data);
    	PropertyConfigurator.configure("log4j.properties");
		logger = Logger.getLogger("");
		logger.setLevel(Level.ALL);
		logger.addAppender(createNewAppender());
	}
    
    private static FileAppender createNewAppender() {
    	appender = new FileAppender();
        appender.setName(token);
        appender.setLayout(new PatternLayout("%d{dd-MM-yyyy HH:mm:ss} - %F || %M || %L :: %m%n"));
        appender.setFile(createTempFile().getAbsolutePath());
        appender.setAppend(true);
        appender.setThreshold(Level.INFO);
        appender.activateOptions();
        return appender;
    }
    
    public static File createTempFile(){
    	File f = null;
        try{
           f = File.createTempFile("tmp", ".txt", new File(diretorioLog));
           f.deleteOnExit();
        }catch(Exception e){
        	logger.error(e.fillInStackTrace());
        }
        return f;
    }
}
