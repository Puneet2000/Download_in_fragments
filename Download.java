package Download;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.FileOutputStream; 
import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
public class Download{
	String url;
	String lowbyte;
	String highbyte;
	int length;
	InputStream inputStream;
	public Download(String url,int lowbyte,int highbyte)
	{
		this.url=url;
		this.lowbyte=Integer.toString(lowbyte);
		this.highbyte=Integer.toString(highbyte);
		this.length= highbyte-lowbyte+1;
	}

	public InputStream download()
	{
		try {
                URL Url = new URL(url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) Url.openConnection();
               String range ="bytes="+lowbyte+"-"+highbyte+"";
                httpURLConnection.setRequestMethod("GET");
               httpURLConnection.setRequestProperty("Range",range);
               
               httpURLConnection.connect();
               int responseCode = httpURLConnection.getResponseCode();
                
                if (responseCode == 206) {
                	
              
            	String fileName = "";
            	String disposition = httpURLConnection.getHeaderField("Content-Disposition");
           
 
            	if (disposition != null) {
                
                int index = disposition.indexOf("filename=");
                if (index > 0) {
                    fileName = disposition.substring(index + 10,
                            disposition.length() - 1);
                }
           	} else {
               
                fileName = url.substring(url.lastIndexOf("/") + 1,
                        url.length());
            }
 
            
            
            inputStream = httpURLConnection.getInputStream();
          	
            
              return inputStream;
            
           
        	} else {
            	System.out.println("Unable to download due to error " + responseCode);
            	
            	
        	}
        	
               httpURLConnection.disconnect(); 


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
		
           return null;
	}
}