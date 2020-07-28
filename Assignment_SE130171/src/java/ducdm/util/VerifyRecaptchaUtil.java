/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducdm.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

/**
 *
 * @author MinhDuc
 */
public class VerifyRecaptchaUtil {

    public static final String URL_VERIFY = "https://www.google.com/recaptcha/api/siteverify";
    public static final String SECRET_KEY = "6LeCQrYZAAAAAMZ4YXJbp9Ep-P6YWc4ew9e6wXJX";

    public static boolean isVerified(String reCaptchaResponse)
            throws IOException {
        if (reCaptchaResponse != null && (!reCaptchaResponse.trim().equals(""))) {
            String url = URL_VERIFY;
            String params = "secret=" + SECRET_KEY.trim()
                    + "&response=" + reCaptchaResponse.trim();
            //Make connect to google api to verify
            URL connectLink = new URL(url);
            HttpURLConnection http
                    = (HttpURLConnection) connectLink.openConnection();
            http.setDoOutput(true);
            http.setRequestMethod("POST");
            http.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded; charset=UTF-8");

            OutputStream getOutput = http.getOutputStream();
            getOutput.write(params.getBytes("UTF-8"));
            getOutput.flush();
            getOutput.close();

            InputStream inputStream = http.getInputStream();
            InputStreamReader inputStreamReader
                    = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferReader
                    = new BufferedReader(inputStreamReader);
            StringBuilder responseContent = new StringBuilder();
            int responseCode = 0;
            while ((responseCode = bufferReader.read()) != -1) {
                responseContent.append((char) responseCode);
            }
            JSONObject json = new JSONObject(responseContent.toString());
            inputStream.close();
            return json.getBoolean("success");
        }
        return false;
    }
}
