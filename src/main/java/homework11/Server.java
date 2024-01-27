package homework11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8081);
        Socket clientSocket = serverSocket.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        while (true) {
            String message = in.readLine();
            String pattern = "[ыёэъ]";
            Pattern ptrn = Pattern.compile(pattern);
            Matcher matcher = ptrn.matcher(message);
            if (message.equals("привет") || matcher.find()) {
                System.out.println(message);
                out.println("що таке паляниця?");
                out.flush();
                message = in.readLine();
                System.out.println(message);
                if (message.equals("хліб")) {
                    out.println(new StringBuilder().append(LocalDateTime.now()).append(" bye!"));
                    out.flush();
                    break;
                } else {
                    out.println("disconnected!");
                    out.flush();
                    break;
                }
            }
            System.out.println(message);
            break;
        }

        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}
