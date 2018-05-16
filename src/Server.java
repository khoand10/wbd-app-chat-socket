import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                try {
                    ServerSocket serverSocket = new ServerSocket(5000);
                    while (true) {
                        Socket socket = serverSocket.accept();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        String data = bufferedReader.readLine();
                        System.out.println("Blue said: " + data);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();

        Thread thread = new Thread(){
            @Override
            public void run() {
                while (true) {
                    try {
                        Scanner sc = new Scanner(System.in);
                        String data = sc.nextLine();
                        Socket socket = new Socket("localhost", 5001);
                        PrintStream ps = new PrintStream(socket.getOutputStream());
                        ps.println(data);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        try {
            thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.start();
    }
}
