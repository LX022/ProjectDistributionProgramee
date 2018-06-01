import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Scanner;

public class clientMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Enumeration<NetworkInterface> allNetworkInterfaces;
		Enumeration<InetAddress> allInetAddresses;
		InetAddress ip = null;

		Socket clSocket;

		// le client cherche la même address ip que le server
		try {
			allNetworkInterfaces = NetworkInterface.getNetworkInterfaces();

			while (allNetworkInterfaces.hasMoreElements()) {
				NetworkInterface ni = allNetworkInterfaces.nextElement();

				allInetAddresses = ni.getInetAddresses();

				while (allInetAddresses.hasMoreElements()) {
					InetAddress ia = allInetAddresses.nextElement();

					if (!ia.isLoopbackAddress()) {
						if (!ia.isLinkLocalAddress()) {
							ip = ia;
						}
					}
				}
			}
			clSocket = new Socket(ip, 45000);
			// reception du message de connexion
			BufferedReader buffin = new BufferedReader(new InputStreamReader(clSocket.getInputStream()));
			String messageConnexion;
			messageConnexion = buffin.readLine();
			System.out.println(messageConnexion);
			System.out.println("Votre choix :");
			// envoi du choix de connexion
			PrintWriter pout = new PrintWriter(clSocket.getOutputStream());
			Scanner sc = new Scanner(System.in);
			String choiceConnexion = sc.nextLine();
			pout.println(choiceConnexion);
			pout.flush();
			// identification ou enregistrement de l'id
			boolean controleID = true;
			do {
				String messageId = buffin.readLine();
				System.out.println(messageId);
				String choiceId = sc.nextLine();
				pout.println(choiceId);
				pout.flush();
				String bool = buffin.readLine();
				if (bool.equals("true")) {
					controleID = true;
				} else {
					controleID = false;
				}
			} while (controleID == false);
			// identification ou enregistrement du mot de passe
			String messagePass = buffin.readLine();
			System.out.println(messagePass);
			String pass = sc.nextLine();
			pout.println(pass);
			pout.flush();
			// envoie de l'ip au serveur en String
			String ipString = ip.toString();
			pout.println(ipString);
			pout.flush();

			// lecture du message statut connexion connecté/pas connecté
			String connectionStatus;
			connectionStatus = buffin.readLine();
			System.out.println(connectionStatus);
			// lecture du choix de l'utilisateur ou déconnecté
			String messageChoice = buffin.readLine();
			System.out.println(messageChoice);

			if (messageChoice
					.equals("1 pour uploader un fichier | 2 pour downloader un fichier | autre touche pour quitter")) {
				String choice = sc.nextLine();
				pout.println(choice);
				pout.flush();

				if (choice.equals("1")) {
					String messagePath = buffin.readLine();
					System.out.println(messagePath);
					String path = sc.nextLine();
					pout.println(path);
					pout.flush();
				} else {
					if (choice.equals("2")) {

//						int x = buffin.read();
//						System.out.println(x);
						// reception des fichiers téléchargeable
						String[][] downloadableFiles = new String[4][3];

						System.out.println("Voici la liste des fichiers disponibles :");
						System.out.println();

						for (int i = 0; i < downloadableFiles.length; i++) {
							for (int j = 0; j < downloadableFiles[i].length; j++) {
								String a = buffin.readLine();
								downloadableFiles[i][j] = a;
							}
						}

						// impression des fichier téléchargable
						for (int i = 0; i < downloadableFiles.length; i++) {
							for (int j = 0; j < downloadableFiles[i].length; j++) {
								System.out.print(downloadableFiles[i][j] + "\t"  +"|" +"\t");
							}
							System.out.println();
							System.out.println("--------------------------------------------------");
						}

						System.out.println();
						System.out.println("Entrer le numéro du fichier que vous voulez télécharger :");
						String downloadFile = sc.nextLine();
						pout.println(downloadFile);
						pout.flush();

						// reception de l'ip et du chemin pour le téléchargement
						String downloadIp = buffin.readLine();
						String downloadPath = buffin.readLine();

						// ouverture de la connexion

					}
				}
			}

			clSocket.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
