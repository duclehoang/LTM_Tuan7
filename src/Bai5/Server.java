package Bai5;




import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.print.DocFlavor;
import java.io.IOException;
import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

                System.out.println(check+" "+check2+" ");
                if (check.toUpperCase().equals("ENC")){
                    tmp=getMD5(check2);


                }
                if (check.toUpperCase().equals("DEC")){
                    tmp=getMD5(check2);
                  //  String api=
                  //  Document doc= Jsoup.connect(api).method(Connection.Method.GET).ignoreContentType(true).execute().parse();
//                JSONObject jsonObject=new JSONObject(doc.text());
//                tmp= (String) jsonObject.get("converted");
                    // System.out.println(tmp);


                }


//                Document doc= Jsoup.connect(api).method(Connection.Method.GET).ignoreContentType(true).execute().parse();
//                JSONObject jsonObject=new JSONObject(doc.text());
//                tmp= (String) jsonObject.get("converted");
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

    public String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            return convertByteToHex1(messageDigest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    public  String convertByteToHex1(byte[] data) {
        BigInteger number = new BigInteger(1, data);
        String hashtext = number.toString(16);
        // Now we need to zero pad it if you actually want the full 32 chars.
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        return hashtext;
    }
}



public class Server {
    public static  void main(String args[]){
        UDPServer ud=new UDPServer();
        ud.Controller_UDPServer();

    }
}

