
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Provider {
    static int portNumber;
    public static void main(String[] args){
        while(true){
            try{
                BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Enter port number");
                portNumber=Integer.parseInt(br.readLine());

                new Thread(new Server1()).start();
            }catch(Exception e){System.out.println(e);

            }

        }
    }
}
class Server1 implements Runnable{
    public void run(){
        try{
            ServerSocket ss=new ServerSocket(Provider.portNumber);
            Socket s=ss.accept();
            System.out.println("connection established sucessfully");
            DataInputStream din=new DataInputStream(s.getInputStream());
            DataOutputStream dout=new DataOutputStream(s.getOutputStream());
            while(true){
                din.readUTF();
                dout.writeUTF("yes");
            }
        }catch(Exception e){System.out.println(e);
        }}}