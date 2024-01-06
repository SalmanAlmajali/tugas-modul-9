import java.rmi.Remote;
import java.rmi.RemoteException;

// Interface untuk ChatClient, yang akan digunakan oleh server untuk berinteraksi dengan klien
public interface ChatClient extends Remote {
    void receiveMessage(String message) throws RemoteException;
}
