import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

// Implementasi dari ChatClient interface
public class ChatClientClass extends UnicastRemoteObject implements ChatClient {

    protected ChatClientClass() throws RemoteException {
        super();
    }

    // Metode untuk menerima pesan dari server
    @Override
    public void receiveMessage(String message) throws RemoteException {
        System.out.println("Received message: " + message);
    }

    // Metode utama untuk menjalankan klien
    public static void main(String[] args) {
        try {
            // Membuat objek ChatClientImpl dan mendapatkan referensi ke ChatServer dari RMI registry
            ChatClientClass chatClient = new ChatClientClass();
            java.rmi.registry.Registry registry = java.rmi.registry.LocateRegistry.getRegistry("localhost", 1099);
            ChatServer chatServer = (ChatServer) registry.lookup("ChatServer");
            chatServer.registerClient(chatClient);

            Scanner scanner = new Scanner(System.in);

            // Terus meminta pengguna untuk memasukkan pesan dan mengirimkannya ke server
            while (true) {
                System.out.print("Enter message: ");
                String message = scanner.nextLine();
                chatServer.sendMessage(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
