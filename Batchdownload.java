import Batchthread.Batchthread;
class Batchdownload {
 	
 	public static void main (String args [])
 	{
 		String url = args[0];
 		String low = args[1];
 		String high = args[2];
 		int l =  Integer.parseInt(low);
 		int h = Integer.parseInt(high);

 		Batchthread [] threads = new Batchthread [h-l+1];

 		for (int i=0;i<h-l+1;i++)
 		{
 			threads[i] = new Batchthread(url.replace("*", Integer.toString(i+l)), Integer.toString(i+1));
 		}		
 	}
}