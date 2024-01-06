import java.rmi.Remote;
import java.rmi.RemoteException;

// Interface untuk ChatServer, yang akan digunakan oleh klien untuk berinteraksi dengan server
public interface ChatServer extends Remote {
    void registerClient(ChatClient client) throws RemoteException;
    void sendMessage(String message) throws RemoteException;
}
