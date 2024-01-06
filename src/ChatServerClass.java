import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

// Implementasi dari ChatServer interface
public class ChatServerClass extends UnicastRemoteObject implements ChatServer {

    private List<ChatClient> clients = new ArrayList<>();

    public ChatServerClass() throws RemoteException {
        super();
    }

    // Metode untuk mendaftarkan klien yang baru terhubung
    @Override
    public void registerClient(ChatClient client) throws RemoteException {
        clients.add(client);
        System.out.println("New client registered.");
    }

    // Metode untuk mengirim pesan ke semua klien yang terhubung
    @Override
    public void sendMessage(String message) throws RemoteException {
        System.out.println("Received message: " + message);

        // Broadcast message to all connected clients
        for (ChatClient client : clients) {
            client.receiveMessage(message);
        }
    }

    // Metode utama untuk menjalankan server
    public static void main(String[] args) {
        try {
            // Membuat objek ChatServerImpl dan mengeksposnya sebagai servis RMI
            ChatServer chatServer = new ChatServerClass();
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            java.rmi.registry.Registry registry = java.rmi.registry.LocateRegistry.getRegistry();
            registry.rebind("ChatServer", chatServer);
            System.out.println("ChatServer is ready.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
