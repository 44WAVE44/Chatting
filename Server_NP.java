import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server_NP {
    public static void main(String[] args) throws Exception {
        
        ServerSocket socket = new ServerSocket(1500);
        ServerSocket socket2 = new ServerSocket(1501);
        
        FilterMessege filter = new FilterMessege();

        while (true) {
            Socket clientSocketOne = socket.accept();
            Socket clientSocketTwo = socket2.accept();

            mThreads1 sMThreads1 = new mThreads1(clientSocketOne, clientSocketTwo, filter);
            Thread sThread1 = new Thread(sMThreads1);
            sThread1.start();

            mThreads2 sMThreads2 = new mThreads2(clientSocketOne, clientSocketTwo, filter);
            Thread sThread2 = new Thread(sMThreads2);
            sThread2.start();
        }
    }
}

class mThreads1 implements Runnable {

    private Socket clientSocketOne;
    private Socket clientSocketTwo;
    private FilterMessege filter;

    public mThreads1(Socket clientSocketOne, Socket clientSocketTwo, FilterMessege filter) {
        this.clientSocketOne = clientSocketOne;
        this.clientSocketTwo = clientSocketTwo;
        this.filter = filter;
    }
    
    @Override
    public void run() {


			try {
				System.out.println("Thread 1 is running");
	
				BufferedReader msg = new BufferedReader(new InputStreamReader(clientSocketOne.getInputStream()));
				String str = msg.readLine();
				String filteredMsg = filter.check_messege(str);
				System.out.println("Client: " + filteredMsg);
				PrintWriter pw = new PrintWriter(clientSocketTwo.getOutputStream(), true);
				pw.println(filteredMsg);

				msg.close();
				pw.close();
				clientSocketOne.close();
				clientSocketTwo.close();
	
			} catch (Exception e) {}
			
        
    }
}

class mThreads2 implements Runnable {

    private Socket clientSocketOne;
    private Socket clientSocketTwo;
    private FilterMessege filter;

    public mThreads2(Socket clientSocketOne, Socket clientSocketTwo, FilterMessege filter) {
        this.clientSocketOne = clientSocketOne;
        this.clientSocketTwo = clientSocketTwo;
        this.filter = filter;
    }

    @Override
    public void run() {


			try {
				System.out.println("Thread 2 is running");
	
				BufferedReader msg2 = new BufferedReader(new InputStreamReader(clientSocketTwo.getInputStream()));
				String str2 = msg2.readLine();
				String filteredMsg2 = filter.check_messege(str2);
				System.out.println("Client_2: " + filteredMsg2);
				PrintWriter pw2 = new PrintWriter(clientSocketOne.getOutputStream(), true);
				pw2.println(filteredMsg2);

				msg2.close();
				pw2.close();
				clientSocketOne.close();
				clientSocketTwo.close();
	
			} catch (Exception e) {}
			
        
    }
}