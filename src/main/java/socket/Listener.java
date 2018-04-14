package socket;

import example.Brick;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Listener {
    private int port;

    public Listener(int portNumber) {
        port = portNumber;
    }

    public Socket listen() throws IOException {
		ServerSocket socket = new ServerSocket(port);
		return socket.accept();
	}

	public List<Brick> listenForInstructions() throws IOException {
	    ServerSocket serverSocket = new ServerSocket(port);
	    Socket clientSocket = serverSocket.accept();
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String jsonPackage = inputReader.readLine();
        return BricksFromJson.getListOfBricksFromJson(jsonPackage);
    }
}