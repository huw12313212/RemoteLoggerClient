package remotelogapi;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

public class RemoteLogAPI {
	private URI logServerURI;
	private WebSocketClient webSocket;
	
	private List<String> datas = new ArrayList<String>();
	
	public RemoteLogAPI(String url)
	{
		try {
			logServerURI = new URI(url);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Close()
	{
		if(webSocket!=null)webSocket.close();
		webSocket = null;
	}
	
	 SimpleDateFormat sdFormat = new SimpleDateFormat("dd HH:mm:ss.SSS");
	
	public void Start()
	{
		if(webSocket!=null)webSocket.close();
		webSocket = null;
		
		webSocket = new WebSocketClient(logServerURI) {

		    @Override
		    public void onMessage(String s) {
		    	System.out.println("Remote Log onMessage:"+s);
		    }

		    @Override
		    public void onClose(int i, String s, boolean b) {
		    	System.out.println("Remote Log onClose:"+s);
		    }

		    @Override
		    public void onError(Exception e) {
		    	System.out.println("Remote Log onError:");
		    	e.printStackTrace();
		    }

			@Override
			public void onOpen(ServerHandshake handshakedata) {
				// TODO Auto-generated method stub
				System.out.println("Remote Log onOpen:");
			}
		  };
		 
		  webSocket.connect();
		  
		}
	
		public void Log(String str)
		{
			String line = "["+ sdFormat.format(new Date()) +"]" + str;
			
			 if(webSocket.isOpen())
			 {
				 for(String data : datas)
				 {
					 webSocket.send(data);
				 }
				 datas.clear();
				 
				 webSocket.send(line);
			 }
			 else
			 {
				 datas.add(line);
				
				 System.out.println("socket is not open");
				 Start();
			 }
		}
}
