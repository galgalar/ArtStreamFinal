import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.*;

public class Mytag {

    static int count = 0;
     static ArrayList <String> names = new ArrayList<>();
     static ArrayList <String> types = new ArrayList<>();
     static ArrayList <String> details = new ArrayList<>();

        public Mytag(String name, String type, String detail)
        {
            names.add(name);
            types.add(type);
            details.add(detail);
     
            try (FileWriter writer = new FileWriter("artsrem22-main\\Artstrem-21-main\\ArtStream20-main\\DashboardAdmin.txt", true);
            PrintWriter pw = new PrintWriter(writer)) {

            pw.println(name + "\n"+type +"\nDetail: "+detail);
            pw.println("\n");

            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Failed to save data to DashboardAdmin.txt");
            }


                
        }

}
