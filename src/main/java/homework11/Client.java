package homework11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket("localhost", 8081);
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("hello");

        while (true) {
            String message = reader.readLine().toLowerCase();
            out.write(message + "\n");
            out.flush();
            String serverMessage = in.readLine();
            if (serverMessage != null) {
                System.out.println(serverMessage);
            } else {
                break;
            }
        }

        reader.close();
        in.close();
        out.close();
        clientSocket.close();
    }
}

