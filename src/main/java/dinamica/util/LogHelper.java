package dinamica.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;
import org.apache.log4j.Logger;
public class LogHelper {

	static Logger logger = Logger.getLogger(LogHelper.class);
	
	public static void logMap(String head,Map map)
	{
		StringBuffer sb=new StringBuffer();
		Iterator iter = map.entrySet().iterator();
		while (iter.hasNext())
		{
			Map.Entry entry = (Map.Entry) iter.next();
			Object key = entry.getKey();
			sb.append((String) key + ":"+map.get(key)+"   ");
		}
		logger.error(head+sb.toString());
	}
	
	public static String getTrace(Throwable t) {
        StringWriter stringWriter= new StringWriter();
        PrintWriter writer= new PrintWriter(stringWriter);
        t.printStackTrace(writer);
        StringBuffer buffer= stringWriter.getBuffer();
        return buffer.toString();
    }
	
	
	public static void log(String msg) {
         logger.info(msg);
    }
	
}
