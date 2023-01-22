package Threads;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class consumer extends Thread {
    buffer buf;
    String dest;


    public consumer(buffer buf,String dd) {
        this.buf = buf;
        this.dest=dd;

    }
    public void run() {
        File file = new File(dest);
        FileWriter fw;
        try {
            fw = new FileWriter(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        PrintWriter pw = new PrintWriter(fw);
        int z=buf.consume();
        int i=0;
        while(z!=0){
            z=buf.consume();
            if(z!=0){
                System.out.println(z);
                pw.print(z+" ");

            }
            if(++i%10==0){
                pw.println(" ");
                i=0;

            }
        }
        pw.close();
    }


}
