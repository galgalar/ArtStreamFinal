import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;

public class Requests extends JPanel {

    private JPanel detailsContainer;

    public Requests() {
        setBackground(Color.BLACK);
        setLayout(new BorderLayout());

        
        JLabel headerLabel = new JLabel("Request Commissions");
        headerLabel.setFont(new Font("Monospaced", Font.BOLD, 20));
        headerLabel.setForeground(Color.WHITE);
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.BLACK);
        headerPanel.add(headerLabel);

        
        detailsContainer = new JPanel();
        detailsContainer.setLayout(new GridLayout(0, 1, 10, 10)); 
        detailsContainer.setBackground(Color.GRAY);

        
        add(headerPanel, BorderLayout.NORTH);
        add(new JScrollPane(detailsContainer), BorderLayout.CENTER); 
    }

    
    public void addCommissionDetails(DigitalCommissionDetails details) {
        details.setPreferredSize(new java.awt.Dimension(500, 300)); 
        detailsContainer.add(details);
        detailsContainer.revalidate(); 
        detailsContainer.repaint();    
    }
}
