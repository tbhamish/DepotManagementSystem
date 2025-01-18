package Managment;

public class Log 
{
    private static Log instance;
    private StringBuilder logBuffer = new StringBuilder();

    private Log() {}

    public static Log getInstance() 
    {
        if (instance == null) {
            instance = new Log();
        }
        return instance;
    }

    public void addEvent(String event) 
    {
        logBuffer.append(event).append("\n");
    }

    public void writeLogToFile(String filename) 
    {
        try (java.io.FileWriter writer = new java.io.FileWriter(filename)) 
        {
            writer.write(logBuffer.toString());
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
