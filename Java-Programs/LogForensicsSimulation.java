import org.cloudbus.cloudsim.core.CloudSim; 
import java.util.*; 
class LogEntry { 
int id; 
String time; 
String message;

LogEntry(int id, String time, String message) { 
this.id = id; 
this.time = time;  
        this.message = message; 
    } 
} 
 
public class LogForensicsSimulation { 
    public static void main(String[] args) { 
        CloudSim.init(1, Calendar.getInstance(), false); 
 
        List<LogEntry> logs = new ArrayList<>(); 
        logs.add(new LogEntry(1, "2023-06-01 10:23:45", "User login uccessful")); 
        logs.add(new LogEntry(2, "2023-06-01 10:25:10", "Failed login attempt")); 
        logs.add(new LogEntry(3, "2023-06-01 10:30:05", "Unauthorized access detected")); 
        logs.add(new LogEntry(4, "2023-06-01 10:35:20", "Normal file access")); 
 
        System.out.println("Suspicious Log Entries:"); 
        for (LogEntry log : logs) { 
            if (log.message.contains("Failed") 
                    || log.message.contains("Unauthorized")) { 
                System.out.println( 
                        "ID: " + log.id + 
                        ", Time: " + log.time + 
                        ", Message: " + log.message 
                ); 
            } 
        } 
    } 
} 