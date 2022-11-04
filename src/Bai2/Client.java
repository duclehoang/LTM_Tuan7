package Bai2;




import javax.imageio.IIOException;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

class UDP_Client{
    public int destPort=1234;
    public String hostName="localhost";
    private DatagramPacket datagramPacketReceive,DatagramPacketSend;
    private DatagramSocket datagramSocket;
    private InetAddress add;
    private Scanner stdin;
    public void Controller_UDPClient(){
        try {
            add=InetAddress.getByName(hostName);
            datagramSocket=new DatagramSocket();
            stdin=new Scanner(System.in);
            while (true){
                System.out.println("Chient input: ");
                String str=stdin.nextLine();
                byte[]data=str.getBytes();
                DatagramPacketSend=new DatagramPacket(data, data.length,add,destPort);
                datagramSocket.send(DatagramPacketSend);
                if (str.equals("bye")){
                    System.out.println("Client socket closed");
                    stdin.close();
                    datagramSocket.close();
                    break;
                }




                // nhận phản hồi từ server
                datagramPacketReceive=new DatagramPacket(new byte[512],512);
                datagramSocket.receive(datagramPacketReceive);
               String tmp=new String(datagramPacketReceive.getData(),0,datagramPacketReceive.getLength());
                System.out.println("Số lượng các số có tổng = "+str+" là : "+ tmp );

            }


        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
public class Client {
    public  static void main(String args[]){
        UDP_Client udp_client=new UDP_Client();
        udp_client.Controller_UDPClient();

    }
}

