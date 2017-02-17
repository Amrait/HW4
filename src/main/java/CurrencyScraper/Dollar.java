package CurrencyScraper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by java-1-05 on 13.02.2017.
 */
public class Dollar {

    public static void main(String[] args) throws Exception {
        int c;
        URL url = new URL("http://minfin.com.ua/currency/banks/?gclid=CLHB3-KditICFUjgGQodgNkIvg");
        URLConnection connection = url.openConnection();

        //get data
        long d = connection.getDate();
        if (d == 0) {
            System.out.println("No date information");
        } else {
            System.out.println(new Date(d));
        }

        System.out.println("Content type: " + connection.getContentType());

        d = connection.getExpiration();
        if (d == 0) {
            System.out.println("No date information about duration");
        } else {
            System.out.println("Outdated: " + new Date(d));
        }

        d = connection.getLastModified();
        if (d == 0) {
            System.out.println("No date information about last modified");
        } else {
            System.out.println("Last modified: " + new Date(d));
        }

        long len = connection.getContentLengthLong();
        if (len == -1) {
            System.out.println("Content length is not available");
        } else {
            System.out.println("Content length: " + len);
        }

        if (len != 0) {
            System.out.println("----Content----");
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF8"));
            String line = "";
            boolean goOff = false;
            ArrayList<String> result = new ArrayList<>();
            while ((line = br.readLine())!=null) {
                if (line.contains("<td class=\"mfm-text-nowrap\">")){
                    while ((line = br.readLine())!=null){
                        if(!line.contains("</td>")){
                            result.add(line);
                        }
                        else {
                            goOff=true;
                            break;
                        }
                    }
                }
                if (goOff){
                    break;
                }
            }
            br.close();
            StringBuilder sb = new StringBuilder("Долар (покупка/продаж): ");
            for (String str: result) {
                if (!str.contains("<")){
                    sb.append(str.trim());
                }
            }
            System.out.println(sb.toString());
        } else {
            System.out.println("Content isn't available");
        }

    }
}

