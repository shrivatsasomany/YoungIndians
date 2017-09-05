package Utils;

/**
 * Created by shrivatsa on 14/08/17.
 */

public class ServerInfo {

    private static ServerInfo serverInfo = new ServerInfo();

    private String serverBase = "http://34.232.139.32:3000/";
    private String users_path = serverBase+"users.json";
    private String events_path = serverBase+"events.json";
    private String announcements_path = serverBase+"/announcements";
    private String user_path = serverBase+"user/";
    private String event_path = serverBase+"event/";
    private String announcement_path = serverBase+"announcement/";

    private ServerInfo() {

    }

    public static ServerInfo getInstance() {
        return serverInfo;
    }

    public String getServerBase() {
        return serverBase;
    }

    public String getUsers_path() {
        return users_path;
    }

    public String getEvents_path() {
        return events_path;
    }

    public String getAnnouncements_path() {
        return announcements_path;
    }

    public String getUser_path(String user) {
        return user_path+user+".json";
    }

    public String getEvent_path(String event) {
        return event_path+event+".json";
    }

    public String getAnnouncement_path(String announcement) {
        return announcement_path+announcement+".json";
    }
}
