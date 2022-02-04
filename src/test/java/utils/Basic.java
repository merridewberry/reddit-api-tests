package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.lang.invoke.MethodHandles;

import static utils.PropertiesManager.getProperty;

public class Basic {

    protected static final Logger log = (Logger) LogManager.getLogger(MethodHandles.lookup().lookupClass());
    protected static final Long responseTime = Long.valueOf(getProperty("responseTime"));

}
