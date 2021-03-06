package net.md_5.bungee.api.plugin;

import java.util.logging.LogRecord;
import java.util.logging.Logger;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

public class PluginLogger extends Logger
{

    private final org.apache.logging.log4j.Logger logger;
    private final String pluginName;

    protected PluginLogger(Plugin plugin)
    {
        super( plugin.getClass().getCanonicalName(), null );
        pluginName = "[" + plugin.getDescription().getName() + "] ";
        logger = LogManager.getLogger( plugin.getClass().getCanonicalName() );
        setLevel(java.util.logging.Level.ALL);
    }

    @Override
    public void log(LogRecord logRecord)
    {
        int lvl = logRecord.getLevel().intValue();
        Level level;

        if ( lvl == Integer.MAX_VALUE )
        {
            level = Level.OFF;
        } else if ( lvl == Integer.MIN_VALUE )
        {
            level = Level.ALL;
        } else if ( lvl >= 1000 )
        {
            level = Level.ERROR;
        } else if ( lvl >= 900 )
        {
            level = Level.WARN;
        } else if ( lvl >= 800 )
        {
            level = Level.INFO;
        } else if ( lvl >= 500 )
        {
            level = Level.DEBUG;
        } else
        {
            level = Level.TRACE;
        }

        logger.log( level, pluginName + logRecord.getMessage(), logRecord.getThrown() );
    }
}
