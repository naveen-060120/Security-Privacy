import org.cloudbus.cloudsim.core.CloudSim; 
import java.util.*; 

public class SecureFileSharingSimulation { 
public static void main(String[] args) { 

	long startTime = System.currentTimeMillis(); 
	CloudSim.init(1, Calendar.getInstance(), false); 

	String fileData = "CONFIDENTIAL DATA"; 
	String encryptedData = encrypt(fileData); 
	System.out.println("File uploaded securely: " + encryptedData); 

        String decryptedData = decrypt(encryptedData); 
        System.out.println("File downloaded securely: " + decryptedData); 
 
        long endTime = System.currentTimeMillis(); 
        double totalTime = (endTime - startTime) / 1000.0; 
        System.out.println("Total simulation time: " + totalTime + " seconds"); 
    } 
    static String encrypt(String data) { 
        return new StringBuilder(data).reverse().toString(); 
    } 
    static String decrypt(String data) { 
        return new StringBuilder(data).reverse().toString(); 
    } 
} 