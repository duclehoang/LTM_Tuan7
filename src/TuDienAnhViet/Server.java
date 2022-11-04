package TuDienAnhViet ;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class Server {
    private static ServerSocket server = null;
    private static Socket socket = null;
    private static BufferedReader in = null;
    private static BufferedWriter out = null;

    public static void main(String[] args) {
        try {
            BufferedWriter bufferedWriter;

            server = new ServerSocket(5000);
            System.out.println("Server started...");
            socket = server.accept();
            System.out.println("Client " + socket.getInetAddress() + " connected...");
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));


            StringTokenizer tokenizer;
            StringTokenizer tokenizer2;

            while (true) {
                // Server nhận dữ liệu từ client qua stream
                FileInputStream fileInputStream = new FileInputStream("src\\TuDienAnhViet\\Dictionary.txt");

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                File file = new File("src\\TuDienAnhViet\\Dictionary.txt");



                String line = in.readLine();

                tokenizer = new StringTokenizer(line, ";", false);

                    String check = tokenizer.nextToken();
//                String word_add = tokenizer.nextToken();
//                String mean_word = tokenizer.nextToken();
                    // System.out.println(check + word_add + mean_word);
                    if (check.equals("ADD")) {
                        bufferedWriter = new BufferedWriter(new FileWriter("src\\TuDienAnhViet\\Dictionary.txt", true));
                        bufferedWriter.write(tokenizer.nextToken() + ";" + tokenizer.nextToken());
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                        out.write("ADD complete");
                        out.newLine();
                        out.flush();

                    } else if (check.equals("DEL")) {

                        String arr[] = new String[5];

                        boolean append1=false;

                        String check5 = tokenizer.nextToken();

                       
                        int i = 0;
                        String line1 = bufferedReader.readLine();
                        String word = " ", word2 = " ", word3 = " ",kq;
                        while (line1 != null) {
                            //   System.out.println(line1);


                            arr[i++] = line1;


                            line1 = bufferedReader.readLine();
                            //     System.out.println(kq);

                        }


                        for (int m=0;m< arr.length;m++) {
                            tokenizer2 = new StringTokenizer(arr[m], ";", true);
                            word = tokenizer2.nextToken();
                            word2 = tokenizer2.nextToken();
                            word3 = tokenizer2.nextToken();


                            //System.out.println(arr.length);



                                //  System.out.println("check5:" + check5);
                                if (!check5.equals(word)) {

                                    bufferedWriter = new BufferedWriter(new FileWriter("src\\TuDienAnhViet\\Dictionary.txt", append1));
                                    System.out.println(word);
                                    bufferedWriter.write(arr[m]);
                                    bufferedWriter.newLine();
                                    bufferedWriter.flush();
                                    append1 = true;



                            }
                        }

                            out.write("Del complete");
                            out.newLine();
                            out.flush();









                    } else if (line.equals("bye"))
                        break;
                        //   System.out.println("Server received: " + line);

                    else {
                        String line1 = bufferedReader.readLine();

                        while (line1 != null) {
                            System.out.println(line1);

                            tokenizer2 = new StringTokenizer(line1, ";", true);
                            String word = tokenizer2.nextToken();
                            String word2 = tokenizer2.nextToken();
                            String word3 = tokenizer2.nextToken();
                            if (line.toLowerCase().equals(word)) {
                                System.out.println(word3);
                                out.write(word3);
                                out.newLine();
                                out.flush();
                            }
                            line1 = bufferedReader.readLine();
                        }


                    }
                }


            System.out.println("Server closed connection");
            // Đóng kết nối
            in.close();
            out.close();
            socket.close();
            server.close();


        } catch (IOException e) { System.err.println(e); }
    }

}