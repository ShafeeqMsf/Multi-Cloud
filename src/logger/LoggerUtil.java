package logger;

import constants.Constants;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtil {

    private Logger logger = LoggerFactory.getLogger(LoggerUtil.class);

    public LoggerUtil() {
        super();
    }

    public void addLog(String someString) {
        configureProp();
        //logger.info(String.format(someString));
        if (logger.isDebugEnabled()) {
            logger.debug(String.format(someString));
        } else if (logger.isInfoEnabled()) {
            logger.info(String.format(someString));
        } else if (logger.isErrorEnabled()) {
            logger.error(String.format(someString));
        }
    }

    public void configureProp() {
        PropertyConfigurator.configure(LoggerUtil.class.getResourceAsStream(Constants.LOG_FILE_NAME));
    }
}
