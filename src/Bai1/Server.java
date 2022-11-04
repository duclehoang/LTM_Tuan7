package Bai1;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

class UDPServer{
   public DatagramSocket datagramSocket;
    public DatagramPacket datagramPacketRecevice, datagramPacketSend;
    private  int buffSize=512;
    private int port=1234;
    public void Controller_UDPServer(){
        try{
            datagramSocket=new DatagramSocket(1234);
            datagramPacketRecevice=new DatagramPacket(new byte[buffSize],buffSize);
            while (true){
                datagramSocket.receive(datagramPacketRecevice);
                String tmp=new String(datagramPacketRecevice.getData(),0,datagramPacketRecevice.getLength());
                System.out.println("Server recived: "+ tmp + "from "+ datagramPacketRecevice.getAddress().getHostAddress()
                + "at port "+ datagramSocket.getLocalPort());
                if (tmp.equals("bye")){
                    System.out.println("Server socket close: ");
                    datagramSocket.close();
                    break;
                }

                StringBuilder str=new StringBuilder(tmp);
                tmp=str.reverse().toString();
                datagramPacketSend=new DatagramPacket(tmp.getBytes(),tmp.getBytes().length,datagramPacketRecevice.getAddress(),datagramPacketRecevice.getPort());
                System.out.println("Server sent back : "+ tmp+ "to client");
                datagramSocket.send(datagramPacketSend);
            }


        }catch (IOException e){
            System.out.println(e);

        }
    }
}

public class Server {
    public static  void main(String args[]){
        UDPServer ud=new UDPServer();
        ud.Controller_UDPServer();

    }
}
