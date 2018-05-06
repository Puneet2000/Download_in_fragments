import Newthread.Newthread;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.IOException; 
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.SequenceInputStream;

class singlefile{
	public static int contentLength (String url)
	{
		try {
                URL Url = new URL(url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) Url.openConnection();
                return httpURLConnection.getContentLength();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
                
            return -1;    
	}

	public static boolean validurl(String url)
	{
          return true;
	}

	public static String filename (String url)
	{
		String fileName="";
		fileName = url.substring(url.lastIndexOf("/") + 1,
                        url.length());
         return fileName;
	}
	public static void main(String args[] )
	{    
		String url = args[0];
		String fname = filename(url);
	    int length = contentLength(url);
	    
		try{
		
		Newthread thread1 = new Newthread(url,0,length/2,"One");
		Newthread thread2 = new Newthread(url,(length/2)+1,length-1,"Two");
		
		try {
				System.out.println("Waiting for threads to finish.");
				thread1.thread.join();
				thread2.thread.join();
				InputStream inputstream1 = thread1.inputStream;
				InputStream inputstream2 = thread2.inputStream;
				SequenceInputStream sequenceInputStream = new SequenceInputStream(inputstream1, inputstream2);
				 
				FileOutputStream outputStream = new FileOutputStream(fname);
				int bytesRead = -1;
            byte[] buffer = new byte[4096];
            while ((bytesRead = sequenceInputStream.read(buffer)) != -1) {
                 outputStream.write(buffer, 0, bytesRead);
             }
            
 
             outputStream.close();
             inputstream1.close();
             inputstream2.close();
             sequenceInputStream.close();
            
				
			} catch (InterruptedException e) {
				System.out.println("Main thread Interrupted");
				}
				
		System.out.println("Main thread exiting.");
	}
	catch (IOException e){
		System.out.println("Main thread Interrupted");

	}
		
}
}