package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Listener {
	public Socket listen(int portNumber) throws IOException {
		ServerSocket socket = new ServerSocket(portNumber);
		return socket.accept();
	}

	public List<Brick> listenForInstructions(int portNumber) throws IOException {
	    ServerSocket serverSocket = new ServerSocket(portNumber);
	    Socket clientSocket = serverSocket.accept();
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String jsonPackage = inputReader.readLine();
        return BricksFromJson.getListOfBricksFromJson(jsonPackage);
    }
}