/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducdm.listener;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author MinhDuc
 */
public class MyContextServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //get ServletContext 
        ServletContext context = sce.getServletContext();
        Map<String, String> siteMap = null;
        //get real path. This real path is the path of web application in Server
        String realPath = context.getRealPath("/");

        String key = null;
        String value = null;
        try {
            File dataFile = new File(realPath + "WEB-INF\\siteMap.txt");
            Scanner readFile = new Scanner(dataFile);
            while (readFile.hasNext()) {
                //get data of a line in text file 
                String temp = readFile.nextLine();
                //seperate key and value through "=" character 
                String[] keyAndValue = temp.split("=");
                for (int i = 0; i < keyAndValue.length; i++) {
                    key = keyAndValue[i];
                    value = keyAndValue[++i];
                }

                if (siteMap == null) {
                    siteMap = new HashMap<>();
                }//end if siteMap is null  

                //Add key, value to siteMap
                siteMap.put(key, value);
            }//end while 
            readFile.close();
            // puts SITE_MAP attribute into Context Scope
            context.setAttribute("SITE_MAP", siteMap);

        } catch (FileNotFoundException ex) {
            context.log("MyContextServletListener_FILE_NOT_FOUND: " + ex.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
