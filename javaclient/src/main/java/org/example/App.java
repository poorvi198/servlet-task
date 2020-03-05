package org.example;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    private static HashMap<String,String > parameters = new HashMap<>();
    public static void main( String[] args ) throws IOException {
        saveStudent();
        getStudent();
    }


    private static void saveStudent() throws IOException {
        URL url = new URL("http://localhost:8090/javaservlet/student");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("POST");

        //add parameters to
        urlConnection.setDoOutput(true);

        DataOutputStream dataOutputStream = new DataOutputStream(urlConnection.getOutputStream());
        parameters.put("rollno","RN2");
        parameters.put("name","manisha");
        parameters.put("university","manit");

        dataOutputStream.write(addParameters(parameters).getBytes());
        dataOutputStream.flush();
        dataOutputStream.close();
        System.out.println(urlConnection.getResponseCode());
        if(urlConnection.getResponseCode()==HttpURLConnection.HTTP_OK)
        {
            String s="";
            String line="";
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            while((s = bufferedReader.readLine())!=null){
                line=line+s;
            }
            System.out.println(line);
        }
        urlConnection.disconnect();
    }

    private static void getStudent() throws IOException {

        URL url = new URL("http://localhost:8090/javaservlet/getStudent?rollno=RN1");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestProperty("accept","application/xml");
        urlConnection.setRequestMethod("GET");

         int responseCode = urlConnection.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK)
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println(response.toString());
        }
        else {
            System.out.println("fail to get data");
        }
        urlConnection.disconnect();
    }

    private static String addParameters(HashMap<String,String> parameters) throws UnsupportedEncodingException {

        StringBuilder queryString = new StringBuilder();
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            queryString.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            queryString.append("=");
            queryString.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            queryString.append("&");
        }
        String resultString = queryString.toString();
        return resultString.length() > 0
                ? resultString.substring(0, resultString.length() - 1)
                : resultString;
    }
}
