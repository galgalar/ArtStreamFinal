import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Client extends Informations implements Validation {
    public static int noOfClient = 0;

    public Client(String username, String password) {
        super(username, password);

        // Save the new client account to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("clientaccs.txt", true))) {
            writer.write(username + ":" + password);
            writer.newLine();
            
            ++noOfClient;
        } catch (IOException e) {
           JOptionPane.showMessageDialog(null,"Error writing to file: " + e.getMessage());
        }
    }

    public Client() {
        super("Unknown", "LOL");
    }

    @Override
    public boolean validateLogin(String name, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("clientaccs.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] credentials = line.split(":");
                if (credentials.length == 2 && name.equals(credentials[0]) && password.equals(credentials[1])) {
                    return true; 
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Error reading file: " + e.getMessage());
        }
        return false; 
    }

    @Override
    public boolean validateRegister(String name, String password) {
        
        try (BufferedReader reader = new BufferedReader(new FileReader("clientaccs.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] credentials = line.split(":");
                if (credentials.length > 0 && name.equals(credentials[0])) {
                    return false; 
                }
            }
        } catch (IOException e) {
            
        }

        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("clientaccs.txt", true))) {
            writer.write(name + ":" + password);
            writer.newLine();
        } catch (IOException e) {
            
        }
        return true;
    }

    public void displayDashboard(String username) {
        
        DashboardClient dashboard = new DashboardClient(username);
        dashboard.setVisible(true); 
    }
}
