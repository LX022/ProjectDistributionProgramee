import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;

public class serverMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Enumeration<NetworkInterface> allNetworkInterfaces;
		Enumeration<InetAddress> allInetAddresses;
		InetAddress serverAddress = null;

		ServerSocket mySkserver;
		Socket srvSocket;

		// initialisation du port1
		int port1 = 45000;

		// définition de l'address du server
		try {
			allNetworkInterfaces = NetworkInterface.getNetworkInterfaces();

			while (allNetworkInterfaces.hasMoreElements()) {
				NetworkInterface ni = allNetworkInterfaces.nextElement();

				allInetAddresses = ni.getInetAddresses();

				while (allInetAddresses.hasMoreElements()) {
					InetAddress ia = allInetAddresses.nextElement();

					if (!ia.isLoopbackAddress()) {
						if (!ia.isLinkLocalAddress()) {
							serverAddress = ia;
						}
					}
				}
			}

			System.out.println("Démarrage du serveur " + serverAddress + " sur le port " + port1);

			mySkserver = new ServerSocket(port1, 5, serverAddress);
			usersList usersList = new usersList();
			
			while (true) {
				Socket clientSocket = mySkserver.accept();
				Thread multiClients = new Thread(new threadMultiClients(clientSocket, usersList));

				multiClients.start();

			}

			// mySkserver.close();
			// clientSocket.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}

