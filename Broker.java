

public class Broker {
    static Queue<Integer> q = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        Timer timer = new Timer();
        timer.schedule(new Add(), 0, 15000);
        timer.schedule(new Delete(), 0, 30000);
        ServerSocket ss = new ServerSocket(3333);
        while(true){
            new Thread(new Server(ss)).start();
        }

    }
    static void add(int n){
        q.add(n);
        System.out.println("added service number: "+ n);
        System.out.println(q);
    }
    static void delete(){
        System.out.println("Deleated service number: "+ q.peek());
        q.remove();
        System.out.println(q);
    }
    static Boolean contains(int n){
        return q.contains(n);
    }
}
class Server implements Runnable{
    ServerSocket ss;
    public Server(ServerSocket ss){
        this.ss=ss;

    }
    @Override
    public void run() {
        try{
            Socket s = ss.accept();
            DataInputStream din=new DataInputStream(s.getInputStream());
            DataOutputStream dout=new DataOutputStream(s.getOutputStream());
            while(true){
                String serviceNUmber=din.readUTF();
                if(Broker.contains(Integer.parseInt(serviceNUmber))){
                    int portNumber=8000+Integer.parseInt(serviceNUmber);
                    dout.writeUTF("service number available \nservice number: "+ serviceNUmber+ "\nportnumber: "+ portNumber);
                    dout.writeUTF("True");
                }
                else{
                    dout.writeUTF("service number not available");
                    dout.writeUTF("False");
                }
                dout.flush();
            }
        }catch(Exception e){System.out.println(e);}
    }
}
class Add extends TimerTask {
    @Override
    public void run() {
        Random rand = new Random();
        int selected = rand.nextInt(100);
        Broker.add(selected);
    }
}
class Delete extends TimerTask {
    @Override
    public void run() {
        Broker.delete();
    }
}

