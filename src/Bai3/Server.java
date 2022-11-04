package Bai3;


import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.print.DocFlavor;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.StringTokenizer;

class UDPServer{
    public DatagramSocket datagramSocket;
    public DatagramPacket datagramPacketRecevice, datagramPacketSend;
    private  int buffSize=512;
    private int port=1234;
    public void Controller_UDPServer(){
        try{
            datagramSocket=new DatagramSocket(1234);
            datagramPacketRecevice=new DatagramPacket(new byte[buffSize],buffSize);
            StringTokenizer tokenizer;
            while (true){
                datagramSocket.receive(datagramPacketRecevice);
                String tmp=new String(datagramPacketRecevice.getData(),0,datagramPacketRecevice.getLength());
                System.out.println("Server recived: "+ tmp + "from "+ datagramPacketRecevice.getAddress().getHostAddress()
                        + "at port "+ datagramSocket.getLocalPort());

                tokenizer=new StringTokenizer(tmp,":");
                String check= tokenizer.nextToken();
                String check2=tokenizer.nextToken();
                String check3=tokenizer.nextToken();
                System.out.println(check+" "+check2+" "+check3);

                String api="https://networkcalc.com/api/binary/"+check3+"?from="+check+"&to="+check2;
                System.out.println(api);

                Document doc= Jsoup.connect(api).method(Connection.Method.GET).ignoreContentType(true).execute().parse();
                JSONObject jsonObject=new JSONObject(doc.text());
                 tmp= (String) jsonObject.get("converted");
               // System.out.println(tmp);







                if (tmp.equals("bye")){
                    System.out.println("Server socket close: ");
                    datagramSocket.close();
                    break;
                }


               // StringBuilder str=new StringBuilder(tmp);
              //  tmp=str.reverse().toString();
                datagramPacketSend=new DatagramPacket(tmp.getBytes(),tmp.getBytes().length,datagramPacketRecevice.getAddress(),datagramPacketRecevice.getPort());
                System.out.println("Server sent back : "+ tmp+ " to client");
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
