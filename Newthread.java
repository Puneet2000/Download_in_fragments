package Newthread;
import java.io.InputStream;
import java.io.IOException;
import Download.Download;



public class Newthread implements Runnable {
	String url;
	int lbyte;
	int hbyte;
	String tname;
	public InputStream inputStream;
	public Thread thread;

	public Newthread(String url , int lbyte , int hbyte , String tname )
	{
		this.url =url;
		this.lbyte = lbyte;
		this.hbyte = hbyte;
		this.tname= tname;
		thread = new Thread(this,tname);
		System.out.println("Downloading part "+ tname);
		thread.start();
	}

	public void run()
	{
	   Download dn = new Download (url,lbyte,hbyte);
	   inputStream = dn.download();
	   

	}

	
}