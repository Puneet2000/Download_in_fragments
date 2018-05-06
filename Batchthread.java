package Batchthread;
import java.io.IOException;
import FullDownload.FullDownload;



public class Batchthread implements Runnable {
	String url;
	String tname;
	public Thread thread;

	public Batchthread(String url , String tname )
	{
		this.url =url;
		this.tname= tname;
		thread = new Thread(this,tname);
		thread.start();
	}

	public void run()
	{
	   FullDownload dn = new FullDownload (url);
	   try {
	   dn.downloadFile();
	}catch(IOException e){
	   
         e.printStackTrace();
	
	}
	}

	
}