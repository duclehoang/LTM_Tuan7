package Bai2;




import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class UDPServer{
    public DatagramSocket datagramSocket;
    public DatagramPacket datagramPacketRecevice, datagramPacketSend;
    private  int buffSize=512;
    private int port=1234;
    public void Controller_UDPServer(){
        try{
            int count=0;
            datagramSocket=new DatagramSocket(1234);
            datagramPacketRecevice=new DatagramPacket(new byte[buffSize],buffSize);
            BufferedReader bufferedReader=new BufferedReader(new FileReader("data.txt"));
            while (true){
                datagramSocket.receive(datagramPacketRecevice);
                String tmp=new String(datagramPacketRecevice.getData(),0,datagramPacketRecevice.getLength());
                System.out.println("Server recived: "+ tmp + " from "+ datagramPacketRecevice.getAddress().getHostAddress()
                        + "at port "+ datagramSocket.getLocalPort());
                if (tmp.equals("bye")){
                    System.out.println("Server socket close: ");
                    datagramSocket.close();
                    break;
                }

                String line=bufferedReader.readLine();
                while (line!=null){
                  //  System.out.println(line);
                    int kq=TongCacChuSo(Integer.parseInt(line));
                    if (Integer.parseInt(tmp)==kq){
                       count++;

                    }
                    line=bufferedReader.readLine();
                }


                String ka2=String.valueOf(count);
                System.out.println(ka2);
                datagramPacketSend=new DatagramPacket(ka2.getBytes(),ka2.getBytes().length,datagramPacketRecevice.getAddress(),datagramPacketRecevice.getPort());
                System.out.println("Server sent back : "+ ka2+ " to client");
                datagramSocket.send(datagramPacketSend);

            }


        }catch (IOException e){
            System.out.println(e);

        }
    }



    public int TongCacChuSo(int n){
        int soDu,tong=0;
        while (n > 0) {
            soDu = n % 10;
            n = n / 10;
            tong += soDu;
        }
        return tong;
    }
}

public class Server {
    public static  void main(String args[]){
        UDPServer ud=new UDPServer();
        ud.Controller_UDPServer();

    }
}

