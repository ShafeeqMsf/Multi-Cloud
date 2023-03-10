/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package constants;

/**
 *
 * @author Arun kumar
 */
public interface Constants {
    
    public static final String APP_KEY = "hvelikmsojsd1qx";
    public static final String APP_SECRET = "uon1rt9oym9gjgu";
    
    public String DB_DRIVER = "";
    public String DB_URL = "";
    public String DB_HOST = "";
    public String DB_PORT = "";
    public String DB_NAME = "";
    public String DB_USER_NAME = "";
    public String DB_USER_PWD = "";
    public String LOG_FILE_NAME = "/constants/log.properties";

    public static final String MAIN_SERVER_IP = "localhost";
    public static final String SERVER1_IP = "localhost";
    public static final String SERVER2_IP = "localhost";
    public static final String SERVER3_IP = "localhost";
    public static final String SERVER4_IP = "localhost";

    public static final int MAIN_SERVER_PORT = 2500;
    public static final int SERVER1_PORT = 2501;
    public static final int SERVER2_PORT = 2502;
    public static final int SERVER3_PORT = 2503;
    public static final int SERVER4_PORT = 2504;

    public static final int FILE_PARTS = 8;

    public static final String FILE_LOCATION = "D:\\temp\\"; //Used by main server to save the file
    public static final String LOGIN_IMAGE = "/resources/images/Login1.jpg";
    public static final String CLOUD_IMAGE_1 = "/resources/images/Cloud1.jpg";
    public static final String CLOUD_IMAGE_3 = "/resources/images/Cloud3.jpg";
    
    public static final String RS1 = "RS1";
    public static final String RS2 = "RS2";
    public static final String RS3 = "RS3";
    public static final String RS4 = "RS4";
    
    public static final String CS1 = "CS1";
    public static final String CS2 = "CS2";
    public static final String CS3 = "CS3";
    public static final String CS4 = "CS4";
    
    public static final String XOR = "XOR Server";
    
    public static final String GOOGLE_CLOUD = "GOOGLE_CLOUD";
    
    public static final String KEY_SIZE = "6";
    
    public static final String FILE_DOWNLOAD_LOCATION = "D:\\TEMP\\";
    public static final String MODE[]={"HIGH","MEDIUM","LOW"};
    public static final String SINGLE_FILE="D:\\TEMP\\STEM\\";
    
    public static final String FILE_HADOOP_OUT_LOCATION = "D:\\TEMP\\result.csv";
    public static final String FILE_HADOOP_IN_LOC = "D:\\temp\\in.csv";
}
