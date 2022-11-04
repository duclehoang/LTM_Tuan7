import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class random {
    public static void main(String args[]){




        try (
                PrintWriter file = new PrintWriter(
                new BufferedWriter(
                        new FileWriter("data.txt")));
        ) {

            Random ran = new Random();
            int number = 0;

            for (int x = 0; x <100000; x++) {
                number = ran.nextInt(100000)+1;
                //System.out.println(number);
                file.println(number);

            }



        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("File test1.txt has been created!");


    }

}
