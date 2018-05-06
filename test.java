import Newthread.Newthread;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.IOException; 
import java.io.SequenceInputStream;

class test{
	public static void main(String args[] )
	{   

		try{
		
		Newthread thread1 = new Newthread("https://www.sample-videos.com/video/mp4/720/big_buck_bunny_720p_1mb.mp4",0,499,"One");
		Newthread thread2 = new Newthread("https://www.sample-videos.com/video/mp4/720/big_buck_bunny_720p_1mb.mp4",500,1055735,"Two");
		
		try {
				System.out.println("Waiting for threads to finish.");
				thread1.thread.join();
				thread2.thread.join();
				InputStream inputstream1 = thread1.inputStream;
				InputStream inputstream2 = thread2.inputStream;
				SequenceInputStream sequenceInputStream = new SequenceInputStream(inputstream1, inputstream2);
				 
				FileOutputStream outputStream = new FileOutputStream("tutorial.mp4");
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