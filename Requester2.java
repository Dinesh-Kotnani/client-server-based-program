
import java.io.*;
import java.net.*;
public class Requester2 {
    public static void main(String[] args){
        try{
            Socket s=new Socket("localhost",3333);
            DataInputStream din=new DataInputStream(s.getInputStream());
            DataOutputStream dout=new DataOutputStream(s.getOutputStream());
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

            while(true){
                System.out.println("Enter service number");
                String serviceNumber=br.readLine();
                dout.writeUTF(serviceNumber);
                dout.flush();
                String decision=din.readUTF();
                String condition=din.readUTF();
                System.out.println(decision);
                if (condition.equals("True")){
                    System.out.println("Enter 1 to start provider service");
                    br.readLine();
                    Socket s1=new Socket("localhost",8000+Integer.parseInt(serviceNumber));
                    DataInputStream din1=new DataInputStream(s1.getInputStream());
                    DataOutputStream dout1=new DataOutputStream(s1.getOutputStream());
                    String question="can u provide service";
                    System.out.println(question);
                    dout1.writeUTF(question);
                    System.out.println(din1.readUTF());
                    break;
                }
            }
            System.out.println("session ended");
        }catch(Exception e){System.out.println(e);
        }
    }
}