import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class CommissionLandscape extends JFrame implements ActionListener, ItemListener {

    private Container con = getContentPane();
    private JPanel[] panelsBorder = new JPanel[2];
    private JLabel header = new JLabel("Art Commission Request Form");
    // private JLabel name = new JLabel("Name");
    // private JTextField nameField = new JTextField();
    private JLabel email = new JLabel("Email");
    private JTextField emailField = new JTextField();
    private JLabel contact = new JLabel("Contact");
    private JTextField contactField = new JTextField();
    private JLabel artDescription = new JLabel("Describe what you want");
    private JTextArea artDescriptionText = new JTextArea();

    private String[] sizes = {
        "Select",
        "Small - 1200.00",
        "Medium - 2200.00",
        "Large - 3500.00"
    };
    private JLabel sizeLabel = new JLabel("Select Size");
    private JComboBox<String> sizeOfArt = new JComboBox<>(sizes);

    protected String[] medium = {
        "Select",
        "Oil Paint - 1900.00",
        "Acrylic Paint - 3900.00",
        "Watercolor - 1000.00"
    };
    private JLabel mediumLabel = new JLabel("Select Medium");
    protected JComboBox<String> mediumBox = new JComboBox<>(medium);

    private ButtonGroup frameOrNot = new ButtonGroup();
    private JLabel frame = new JLabel("I would like it");
    private JRadioButton frameButton = new JRadioButton("Frame-1000");
    private JRadioButton notFrameButton = new JRadioButton("Unframed-No Charge");

    private JLabel artistMessage = new JLabel("Message the artist");
    private JTextArea artistMessageText = new JTextArea();
    private JButton submit = new JButton("Submit");
    private JLabel totalLabel = new JLabel();
    private String artistName;
    private double total = 0;
    private String selectOption = "";
    private String clientName;


    public CommissionLandscape(String artistName, String clientName) {
        super("Commission Details");
        setSize(500, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.clientName = clientName;

        con.setLayout(new BorderLayout());

        frameOrNot.add(frameButton);
        frameOrNot.add(notFrameButton);

        for (int i = 0; i < panelsBorder.length; ++i) {
            if (i == 0) {
                panelsBorder[i] = new JPanel();
                panelsBorder[i].setLayout(new FlowLayout(FlowLayout.CENTER)); 
                panelsBorder[i].add(header);
                con.add(panelsBorder[i], BorderLayout.NORTH);
            } else {

                panelsBorder[i] = new JPanel();
                panelsBorder[i].setLayout(new BoxLayout(panelsBorder[i], BoxLayout.Y_AXIS));

                
                panelsBorder[i].add(createLeftAlignedLabel("Request Commission To: " + artistName));
                panelsBorder[i].add(Box.createRigidArea(new Dimension(10, 10)));
                // panelsBorder[i].add(createLeftAlignedComponent(name));
                // panelsBorder[i].add(Box.createRigidArea(new Dimension(10, 10)));
                // nameField.setMaximumSize(new Dimension(200, 30));
                // panelsBorder[i].add(createLeftAlignedComponent(nameField));
                panelsBorder[i].add(Box.createRigidArea(new Dimension(10, 10)));
                panelsBorder[i].add(createLeftAlignedComponent(email));
                panelsBorder[i].add(Box.createRigidArea(new Dimension(10, 10)));
                emailField.setMaximumSize(new Dimension(200, 30));
                panelsBorder[i].add(createLeftAlignedComponent(emailField));
                panelsBorder[i].add(Box.createRigidArea(new Dimension(10, 10)));
                panelsBorder[i].add(createLeftAlignedComponent(contact));
                panelsBorder[i].add(Box.createRigidArea(new Dimension(10, 10)));
                contactField.setMaximumSize(new Dimension(200, 30));
                panelsBorder[i].add(createLeftAlignedComponent(contactField));
                panelsBorder[i].add(Box.createRigidArea(new Dimension(10, 10)));
                panelsBorder[i].add(createLeftAlignedComponent(artDescription));
                panelsBorder[i].add(Box.createRigidArea(new Dimension(10, 10)));
                artDescriptionText.setPreferredSize(new Dimension(100, 60));
                artDescriptionText.setMaximumSize(new Dimension(200, 60));
                panelsBorder[i].add(createLeftAlignedComponent(artDescriptionText));
                panelsBorder[i].add(Box.createRigidArea(new Dimension(10, 10)));
                panelsBorder[i].add(createLeftAlignedComponent(sizeLabel));
                panelsBorder[i].add(Box.createRigidArea(new Dimension(10, 10)));
                sizeOfArt.setMaximumSize(new Dimension(200, 30));
                panelsBorder[i].add(createLeftAlignedComponent(sizeOfArt));
                panelsBorder[i].add(Box.createRigidArea(new Dimension(10, 10)));
                panelsBorder[i].add(createLeftAlignedComponent(mediumLabel));
                panelsBorder[i].add(Box.createRigidArea(new Dimension(10, 10)));
                mediumBox.setMaximumSize(new Dimension(200, 30));
                panelsBorder[i].add(createLeftAlignedComponent(mediumBox));
                panelsBorder[i].add(Box.createRigidArea(new Dimension(10, 10)));
                panelsBorder[i].add(createLeftAlignedComponent(frame));
                panelsBorder[i].add(Box.createRigidArea(new Dimension(10, 10)));
                panelsBorder[i].add(createLeftAlignedComponent(frameButton));
                panelsBorder[i].add(Box.createRigidArea(new Dimension(10, 10)));
                panelsBorder[i].add(createLeftAlignedComponent(notFrameButton));
                panelsBorder[i].add(Box.createRigidArea(new Dimension(10, 10)));
                panelsBorder[i].add(createLeftAlignedComponent(artistMessage));
                panelsBorder[i].add(Box.createRigidArea(new Dimension(10, 10)));
                artistMessageText.setPreferredSize(new Dimension(200, 60));
                artistMessageText.setMaximumSize(new Dimension(200, 60));
                panelsBorder[i].add(createLeftAlignedComponent(artistMessageText));
                panelsBorder[i].add(Box.createRigidArea(new Dimension(10, 10)));
                submit.setMaximumSize(new Dimension(200, 30));
                panelsBorder[i].add(createLeftAlignedComponent(submit));
                panelsBorder[i].add(Box.createRigidArea(new Dimension(10, 10)));
                panelsBorder[i].add(createLeftAlignedComponent(totalLabel));
                JPanel main = new JPanel(new FlowLayout());
                main.add(panelsBorder[i]);
                
                this.artistName = artistName;

                submit.addActionListener(this);
                sizeOfArt.addItemListener(this);
                mediumBox.addItemListener(this);
                frameButton.addItemListener(this);
                notFrameButton.addItemListener(this);

                con.add(main, BorderLayout.CENTER);
            }
        }

        setVisible(true);
    }

    private JComponent createLeftAlignedComponent(JComponent component) {
        component.setAlignmentX(Component.LEFT_ALIGNMENT);
        return component;
    }


    private JLabel createLeftAlignedLabel(String text) {
        JLabel label = new JLabel(text);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    public String[] getSetMedium() {
        return medium;
    }

    public boolean validateEmail(String email)
    {
        if (email.contains("@") && email.endsWith(".com")) {
            
            String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
            return email.matches(emailRegex);
        } else {
            return false;  
        }
        
    }

    public boolean validateContact() {
        String contact = contactField.getText().trim();
        return contact.matches("\\d{11}"); 
    }
    public boolean isNotNumeric(String str) {
        try {
            Integer.parseInt(str); 
            return false; 
        } catch (NumberFormatException e) {
            return true; 
        }
    }

    public boolean isEmpty()
    {
        boolean isEmpty = false;
            if(emailField.getText().isEmpty() || contactField.getText().isEmpty() || artDescriptionText.getText().isEmpty() || (!frameButton.isSelected() && !notFrameButton.isSelected()) || artistMessageText.getText().isEmpty())
        isEmpty = true;

        return isEmpty;
    }
    public void setThemEmpty()
    {
       
        emailField.setText("");
        contactField.setText("");
        artDescriptionText.setText("");
        sizeOfArt.setSelectedItem("Select");
        mediumBox.setSelectedItem("Select");
        artistMessageText.setText("");
        frameOrNot.clearSelection();
        totalLabel.setText("");

    }

    @Override

 public void actionPerformed(ActionEvent e) {
    Object source = e.getSource();  
    
    if (source == submit) {
        if (isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in the required fields");
        } else if (!validateEmail(emailField.getText())) {
            JOptionPane.showMessageDialog(null, "Please enter a valid email");
        } else if (!validateContact()) {
            JOptionPane.showMessageDialog(null, "Please enter a valid contact (11 digits)");
        } else if(sizeOfArt.getSelectedItem().equals("Select") || mediumBox.getSelectedItem().equals("Select")) {
            JOptionPane.showMessageDialog(null, "Please fill in the required fields");

        } else {          
            int select = JOptionPane.showConfirmDialog(null,
                "\nSend to: " + artistName + "\nType of art: Landscape" + 
                "\nName: " +clientName+ 
                "\nEmail: " + emailField.getText() + 
                "\nContact: " + contactField.getText() + 
                "\nDescribe art: " + artDescriptionText.getText() + 
                "\nSize: " + sizeOfArt.getSelectedItem() + 
                "\nMedium: " + mediumBox.getSelectedItem() + 
                "\nFrame or not: " + selectOption + 
               "\nMessage: "+ artistMessageText.getText() +
                "\nTotal: " + total,     
                "Commission Summary",
                JOptionPane.YES_NO_OPTION);
             if (select == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "Request sent successfully!");

                String details =        
                "\nSend to: " + artistName + 
                "\nType of art: Landscape" + 
                "\nName: " + clientName + 
                "\nEmail: " + emailField.getText() + 
                "\nContact: " + contactField.getText() + 
                "\nDescribe art: " + artDescriptionText.getText() + 
                "\nSize: " + sizeOfArt.getSelectedItem() + 
                "\nMedium: " + mediumBox.getSelectedItem() + 
                "\nMessage: "+ artistMessageText.getText() +
                "\nTotal: " + total;
                System.out.print(details);
                new Mytag(clientName, "Landscape", details);
                setThemEmpty();
                dispose();
                
             }
        }
    }
}

    @Override
    public void itemStateChanged(ItemEvent e)
    {
        Object source = e.getSource();
        if(source == sizeOfArt)
        {
            if(e.getStateChange() == ItemEvent.SELECTED)
            {
                String selected = (String) e.getItem();
                switch (selected) {
                    case "Small - 1200.00":
                        total += 1200.00;
                    break;
                    case "Medium - 2200.00":
                        total += 2200.00;
                    break;
                    case "Large - 3500.00":
                        total += 3500.00;
                    break;
                    default:
                        break;
                }
                
            }else if(e.getStateChange() == ItemEvent.DESELECTED)
            {
                String selected = (String) e.getItem();
                switch (selected) {
                    case "Small - 1200.00":
                    total -= 1200.00;
                    break;
                    case "Medium - 2200.00":
                        total -= 2200.00;
                    break;
                    case "Large - 3500.00":
                        total -= 3500.00;
                    break;
                    default:
                    break;
                }
            }

        }else if(source == mediumBox)
        {
            if(e.getStateChange() == ItemEvent.SELECTED)
            {
                String selected = (String) e.getItem();
                
                switch (selected) {
                    case "Oil Paint - 1900.00":
                        total += 1900.00;
                    break;
                    case "Acrylic Paint - 3900.00":
                        total += 3900.00;
                    break;
                    case "Watercolor - 1000.00":
                        total += 1000.00;
                    break;
                    default:
                        break;
                }


            }else if(e.getStateChange() == ItemEvent.DESELECTED)
            {
                String selected = (String) e.getItem();
                switch (selected) {
                    case "Oil Paint - 1900.00":
                    total -= 1900.00;
                break;
                case "Acrylic Paint - 3900.00":
                    total -= 3900.00;
                break;
                case "Watercolor - 1000.00":
                    total -= 1000.00;
                break;
                default:
                    break;
                }
            }
        }else if(source == frameButton)
        {
            if(e.getStateChange() == ItemEvent.SELECTED)
            {   
                selectOption = frameButton.getText();
                total += 1000;
            }else
            {
                selectOption = "";
                total -= 1000;
            }

        }else if(source == notFrameButton)
        {
            if(e.getStateChange() == ItemEvent.SELECTED)
            {
                selectOption = notFrameButton.getText();
                total += 0;
            }else if(e.getStateChange() == ItemEvent.DESELECTED)
            {
                selectOption = "";
                total -= 0;
            }
        }


        
    }

}


