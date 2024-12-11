
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DashboardAdmin extends JFrame {

    private JTable requestsTable;
    private JTable acceptedTable;
    private JTable doneTable;
    

    public DashboardAdmin() {
        initComponents();

        String filePath = "artsrem22-main\\Artstrem-21-main\\ArtStream20-main\\DashboardAdmin.txt"; 

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            StringBuilder dataBlock = new StringBuilder();
            StringBuilder details = new StringBuilder(); 

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                   
                    if (dataBlock.length() > 0) {
                      
                        String[] parts = dataBlock.toString().split("\n");

                        if (parts.length >= 2) { 
                            String name = parts[0].trim(); 
                            String type = parts[1].trim(); 
                          
                            
                            String fullDetails = details.toString().trim();                
                            addData(name, type, fullDetails);

                            
                            dataBlock.setLength(0);
                            details.setLength(0);
                        }
                    }
                } else {
                  
                    if (dataBlock.length() == 0 || dataBlock.toString().split("\n").length < 2) {
                        dataBlock.append(line).append("\n");
                    } else {
                       
                        details.append(line).append("\n");
                    }
                }
            }

            // if (dataBlock.length() > 0) {
            //     String[] parts = dataBlock.toString().split("\n");
            //     if (parts.length >= 2) {
            //         String name = parts[0].trim();
            //         String type = parts[1].trim();
            //         String fullDetails = details.toString().trim();

                    
            //     }
            // }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String filePathAccepted = "artsrem22-main\\Artstrem-21-main\\ArtStream20-main\\AcceptedCommissions.txt"; 

        try (BufferedReader reader = new BufferedReader(new FileReader(filePathAccepted))) {
            String line;
            StringBuilder dataBlock = new StringBuilder();
            StringBuilder details = new StringBuilder(); 

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                   
                    if (dataBlock.length() > 0) {
                      
                        String[] parts = dataBlock.toString().split("\n");

                        if (parts.length >= 2) { 
                            String name = parts[0].trim(); 
                            String type = parts[1].trim(); 
                            System.out.println(name);
                            System.out.println(type);
                          
                            
                            String fullDetails = details.toString().trim();                        
                            addDataAccepted(name, type, fullDetails);
                            
                            dataBlock.setLength(0);
                            details.setLength(0);
                        }
                    }
                } else {
                  
                    if (dataBlock.length() == 0 || dataBlock.toString().split("\n").length < 2) {
                        dataBlock.append(line).append("\n");
                    } else {
                       
                        details.append(line).append("\n");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filePathDone = "artsrem22-main\\Artstrem-21-main\\ArtStream20-main\\DoneCommissions.txt"; 

        try (BufferedReader reader = new BufferedReader(new FileReader(filePathDone))) {
            String line;
            StringBuilder dataBlock = new StringBuilder();
            StringBuilder details = new StringBuilder(); 

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                   
                    if (dataBlock.length() > 0) {
                      
                        String[] parts = dataBlock.toString().split("\n");

                        if (parts.length >= 2) { 
                            String name = parts[0].trim(); 
                            String type = parts[1].trim(); 
                          
                            
                            String fullDetails = details.toString().trim();                        
                            addDataDone(name, type, fullDetails);
                            
                            dataBlock.setLength(0);
                            details.setLength(0);
                        }
                    }
                } else {
                  
                    if (dataBlock.length() == 0 || dataBlock.toString().split("\n").length < 2) {
                        dataBlock.append(line).append("\n");
                    } else {
                       
                        details.append(line).append("\n");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {

        JButton deleteRequestButton = new JButton("Delete Request");
        JButton acceptRequestButton = new JButton("Accept Request");
        JButton moveToDoneButton = new JButton("Move to Done");
        JButton deleteDoneButton = new JButton("Delete Done");
        JButton logoutButton = new JButton("Logout");
        JTabbedPane tabbedPane = new JTabbedPane();

        requestsTable = new JTable(new DefaultTableModel(new Object[][] {}, new String[] {"Name", "Type of Art", "Commission Summary"})) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2; 
            }
        };

        requestsTable.getColumnModel().getColumn(2).setCellRenderer(new ButtonRenderer());
        requestsTable.getColumnModel().getColumn(2).setCellEditor(new ButtonEditor(new JCheckBox(), requestsTable));

        JScrollPane requestsScrollPane = new JScrollPane(requestsTable);

        JPanel requestsPanel = new JPanel();
        requestsPanel.setLayout(new BorderLayout());
        requestsPanel.add(requestsScrollPane, BorderLayout.CENTER);

        JPanel requestsButtonPanel = new JPanel();
        requestsButtonPanel.add(deleteRequestButton);
        requestsButtonPanel.add(acceptRequestButton);
        requestsPanel.add(requestsButtonPanel, BorderLayout.SOUTH);

        tabbedPane.addTab("Requests", requestsPanel);

        acceptedTable = new JTable(new DefaultTableModel(new Object[][] {}, new String[] {"Name", "Type of Art", "Commission Summary"})) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2; 
            }
        };

        acceptedTable.getColumnModel().getColumn(2).setCellRenderer(new ButtonRenderer());
        acceptedTable.getColumnModel().getColumn(2).setCellEditor(new ButtonEditor(new JCheckBox(), acceptedTable));

        JScrollPane acceptedScrollPane = new JScrollPane(acceptedTable);

        JPanel acceptedPanel = new JPanel();
        acceptedPanel.setLayout(new BorderLayout());
        acceptedPanel.add(acceptedScrollPane, BorderLayout.CENTER);

        JPanel acceptedButtonPanel = new JPanel();
        acceptedButtonPanel.add(moveToDoneButton);
        acceptedPanel.add(acceptedButtonPanel, BorderLayout.SOUTH);

        tabbedPane.addTab("Accepted", acceptedPanel);

        doneTable = new JTable(new DefaultTableModel(new Object[][] {}, new String[] {"Name", "Type of Art", "Commission Summary"})) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2;
            }
        };

        doneTable.getColumnModel().getColumn(2).setCellRenderer(new ButtonRenderer());
        doneTable.getColumnModel().getColumn(2).setCellEditor(new ButtonEditor(new JCheckBox(), doneTable));

        JScrollPane doneScrollPane = new JScrollPane(doneTable);

        JPanel donePanel = new JPanel();
        donePanel.setLayout(new BorderLayout());
        donePanel.add(doneScrollPane, BorderLayout.CENTER);

        JPanel doneButtonPanel = new JPanel();
        doneButtonPanel.add(deleteDoneButton);
        donePanel.add(doneButtonPanel, BorderLayout.SOUTH);

        tabbedPane.addTab("Done", donePanel);

        JLabel adminLabel = new JLabel("Admin Dashboard");

        logoutButton.addActionListener(evt -> logoutActionPerformed());
        deleteRequestButton.addActionListener(evt -> deleteRequestActionPerformed());
        acceptRequestButton.addActionListener(evt -> acceptRequestActionPerformed());
        moveToDoneButton.addActionListener(evt -> moveToDoneActionPerformed());
        deleteDoneButton.addActionListener(evt -> deleteDoneActionPerformed());

        setLayout(new BorderLayout());
        add(adminLabel, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);
        add(logoutButton, BorderLayout.SOUTH);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 600);
    }

    private void deleteRequestActionPerformed() {
        
        int selectedRow = requestsTable.getSelectedRow();
        if (selectedRow >= 0) {
            DefaultTableModel model = (DefaultTableModel) requestsTable.getModel();
    
         
            String name = model.getValueAt(selectedRow, 0).toString();
            String type = model.getValueAt(selectedRow, 1).toString();
    
            
            model.removeRow(selectedRow);
    
            
            String filePath = "artsrem22-main\\Artstrem-21-main\\ArtStream20-main\\DashboardAdmin.txt";
            updateFileAfterRowDeletion(filePath, name, type);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.");
        }

    }
    
        private void updateFileAfterRowDeletion(String filePath, String nameToRemove, String typeToRemove) {
        try {
           
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            StringBuilder updatedContent = new StringBuilder();
            String line;
            boolean skipBlock = false;
            StringBuilder currentBlock = new StringBuilder();
    
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                   
                    if (!skipBlock) {
                        updatedContent.append(currentBlock).append(System.lineSeparator());
                    }
                    currentBlock.setLength(0);
                    skipBlock = false;
                } else {
                    currentBlock.append(line).append(System.lineSeparator());
                    String[] blockLines = currentBlock.toString().split("\n");
                    if (blockLines.length >= 2) {
                        String name = blockLines[0].trim();
                        String type = blockLines[1].trim();
                        if (name.equals(nameToRemove) && type.equals(typeToRemove)) {
                            skipBlock = true; 
                        }
                    }
                }
            }  
            
            if (!skipBlock) {
                updatedContent.append(currentBlock);
            }
    
            reader.close();
    
            
            java.nio.file.Files.write(java.nio.file.Paths.get(filePath), updatedContent.toString().getBytes());
    
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating the file: " + e.getMessage());
        }
    }
    
    private void acceptRequestActionPerformed() {
        int selectedRow = requestsTable.getSelectedRow();
        if (selectedRow >= 0) {
            DefaultTableModel requestsModel = (DefaultTableModel) requestsTable.getModel();
            DefaultTableModel acceptedModel = (DefaultTableModel) acceptedTable.getModel();
    
            Object[] rowData = new Object[requestsModel.getColumnCount()];
            for (int i = 0; i < requestsModel.getColumnCount(); i++) {
                rowData[i] = requestsModel.getValueAt(selectedRow, i);
            }
    
            acceptedModel.addRow(rowData);

            String name = requestsModel.getValueAt(selectedRow, 0).toString();
            String type = requestsModel.getValueAt(selectedRow, 1).toString();
            requestsModel.removeRow(selectedRow);
    
 
            String filePath = "artsrem22-main\\Artstrem-21-main\\ArtStream20-main\\DashboardAdmin.txt";
            updateFileAfterRowDeletion(filePath, name, type);
    
         
            try (FileWriter writer = new FileWriter("artsrem22-main\\Artstrem-21-main\\ArtStream20-main\\AcceptedCommissions.txt", true);
                 PrintWriter pw = new PrintWriter(writer)) {
    
                pw.println(rowData[0]); 
                pw.println(rowData[1]); 
                pw.println(rowData[2]); 
                pw.println(); 
    
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Failed to save data to AcceptedCommissions.txt");
            }
    
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to accept.");
        }
    }
    

    private void moveToDoneActionPerformed() {
        int selectedRow = acceptedTable.getSelectedRow();
        if (selectedRow >= 0) {
            DefaultTableModel acceptedModel = (DefaultTableModel) acceptedTable.getModel();
            DefaultTableModel doneModel = (DefaultTableModel) doneTable.getModel();

            Object[] rowData = new Object[acceptedModel.getColumnCount()];
            for (int i = 0; i < acceptedModel.getColumnCount(); i++) 
            {
                rowData[i] = acceptedModel.getValueAt(selectedRow, i);
            }
            doneModel.addRow(rowData);

            String name = acceptedModel.getValueAt(selectedRow, 0).toString();
            String type = acceptedModel.getValueAt(selectedRow, 1).toString();
            acceptedModel.removeRow(selectedRow);

            String filePath = "artsrem22-main\\Artstrem-21-main\\ArtStream20-main\\AcceptedCommissions.txt";
            updateFileAfterRowDeletion(filePath, name, type);
    
                
                try (FileWriter writer = new FileWriter("artsrem22-main\\Artstrem-21-main\\ArtStream20-main\\DoneCommissions.txt", true);
                PrintWriter pw = new PrintWriter(writer)) {

                    pw.println(rowData[0]); 
                    pw.println(rowData[1]); 
                    pw.println(rowData[2]); 
                    pw.println(); 

                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Failed to save data to DashboardAdmin.txt");
                }
            // acceptedModel.removeRow(selectedRow);

     } else {
            JOptionPane.showMessageDialog(this, "Please select a row to mark as done.");
        }
    }

    private void deleteDoneActionPerformed() {
        int selectedRow = doneTable.getSelectedRow();
        if (selectedRow >= 0) {
            DefaultTableModel model = (DefaultTableModel) doneTable.getModel();

            Object[] rowData = new Object[doneTable.getColumnCount()];
            for (int i = 0; i < doneTable.getColumnCount(); i++) 
            {
                rowData[i] = doneTable.getValueAt(selectedRow, i);
            }
         
            String name = model.getValueAt(selectedRow, 0).toString();
            String type = model.getValueAt(selectedRow, 1).toString();
          
            model.removeRow(selectedRow);

        
            String filePath = "artsrem22-main\\Artstrem-21-main\\ArtStream20-main\\DoneCommissions.txt";
            updateFileAfterRowDeletion(filePath, name, type);
    
            try (FileWriter writer = new FileWriter("artsrem22-main\\Artstrem-21-main\\ArtStream20-main\\CommissionHistory.txt", true);
            PrintWriter pw = new PrintWriter(writer)) {


                pw.println("Commissions History: \n"+rowData[0]); 
                pw.println(rowData[1]); 
                pw.println(rowData[2]); 
                pw.println(); 

            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Failed to save data to DashboardAdmin.txt");
            }
    
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.");
        }
    }

    private void logoutActionPerformed() {
        JOptionPane.showMessageDialog(this, "Logging out.");
        new SetupAccountAdmin();
        dispose();
    }

    public void addData(String name, String type, String commission) {
        DefaultTableModel model = (DefaultTableModel) requestsTable.getModel();
        model.addRow(new Object[]{name, type, commission});
    }

    public void addDataAccepted(String name, String type, String commission) {
        DefaultTableModel model = (DefaultTableModel) acceptedTable.getModel();
        model.addRow(new Object[]{name, type, commission});
    }
    public void addDataDone(String name, String type, String commission) {
        DefaultTableModel model = (DefaultTableModel) doneTable.getModel();
        model.addRow(new Object[]{name, type, commission});
    }

    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText("View Details");
            return this;
        }
    }

    class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private String label;
        private boolean clicked;
        private JTable parentTable;

        public ButtonEditor(JCheckBox checkBox, JTable parentTable) {
            super(checkBox);
            this.parentTable = parentTable;
            button = new JButton();
            button.setOpaque(true);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            String commissionDetails = parentTable.getValueAt(row, 2).toString();
            label = commissionDetails;
            button.setText(label);
            clicked = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (clicked) {
                int row = parentTable.getSelectedRow();
                if (row >= 0) {
                    String commissionDetails = parentTable.getValueAt(row, 2).toString();
                    JOptionPane.showMessageDialog(button, "Commission Details: " + commissionDetails);
                }
            }
            clicked = false;
            return label;
        }

        @Override
        public boolean stopCellEditing() {
            clicked = false;
            return super.stopCellEditing();
        }

        @Override
        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }

}
