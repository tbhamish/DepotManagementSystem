package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Classes.Customer;
import Classes.Parcel;
import java.awt.event.ActionEvent;


public class DepotGUI
{
    private JFrame frame;
    private JMenuBar menuBar;
    private JTable customersTable;
    private JTable parcelTable;
    private JTextField parcelIdField;
    private JComboBox<String> parcelFilter;
    private JLabel target;
    private JButton findParcelButton; 
    private JButton filterParcel;
    private JButton collectParcel;
    private JButton calculateButton;
    private  JButton clearButton;

    private JMenuItem addCustomerItem;
    private JMenuItem addParcelItem;
    private JMenuItem closeItem;

    public DepotGUI() 
    {

        frame = new JFrame("Parcel Depot Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLayout(new BorderLayout());
        
        menuBar = new JMenuBar();

        JMenu options = new JMenu("Options");

        JMenu addMenu = new JMenu("Add");
        addCustomerItem = new JMenuItem("Add Customer");
        addParcelItem = new JMenuItem("Add Parcel");

        addMenu.add(addCustomerItem);
        addMenu.add(addParcelItem);

        closeItem = new JMenuItem("Exit & Write Log");

        options.add(addMenu);
        options.add(closeItem);

        menuBar.add(options);

        frame.setJMenuBar(menuBar);


        String[] parcelColumnNames = {"Parcel ID", "Days", "Collected",  "Dimensions"};
        DefaultTableModel parcelTableModel = new DefaultTableModel(parcelColumnNames, 0);
        parcelTable = new JTable(parcelTableModel);
        parcelTable.setEnabled(false);
        JScrollPane parcelScrollPane = new JScrollPane(parcelTable);

        String[] cusColumnNames = {"Queue Num", "Customer Name", "Parcel ID"};
        DefaultTableModel cusTableModel = new DefaultTableModel(cusColumnNames, 0);
        customersTable = new JTable(cusTableModel);
        customersTable.setEnabled(false);
        JScrollPane customersScrollPane = new JScrollPane(customersTable);

        JPanel topPanel = new JPanel(new BorderLayout());
        parcelFilter = new JComboBox<>(new String[]{"All Parcels", "Uncollected Parcels", "Collected Parcels"});
        topPanel.add(parcelFilter, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2,10,10));
        centerPanel.add(parcelScrollPane); 
        centerPanel.add(customersScrollPane);  

        JPanel bottomPanel = new JPanel();
        parcelIdField = new JTextField(10);

        findParcelButton = new JButton("Find Parcel");
        filterParcel = new JButton("Filter");
        collectParcel = new JButton("Collect");
        calculateButton = new JButton("Calculate");
        clearButton = new JButton("Clear");

        bottomPanel.add(new JLabel("Parcel ID:"));
        bottomPanel.add(parcelIdField);
        bottomPanel.add(findParcelButton);
        bottomPanel.add(filterParcel);
        bottomPanel.add(collectParcel);
        bottomPanel.add(calculateButton);
        bottomPanel.add(clearButton);

        target = new JLabel("Selected Parcel ID:");
        bottomPanel.add(target);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);

        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }


    public JTextField getParcelIdField() 
    {
        return parcelIdField;
    }

    public JFrame getFrame() 
    {
        return frame;
    }

    public JLabel getTarget()
    {
        return target;
    }

    public JComboBox<String> getParcelFilter() 
    {
        return parcelFilter;
    }

    public JButton getFindParcelButton()
    {
        return findParcelButton;
    } 
    public JButton getFilterParcelButton()
    {
        return filterParcel;
    } 
    public JButton getCollectParcelButton()
    {
        return collectParcel;
    } 
    public JButton getCalculateButton()
    {
        return calculateButton;
    } 

    public JButton getClearButton()
    {
        return clearButton;
    }

    public JMenuItem getAddCustomerItem()
    {
        return addCustomerItem;
    }

    public JMenuItem getAddParcelItem()
    {
        return addParcelItem;
    }

    public JMenuItem getCloseItem()
    {
        return closeItem;
    }
    

    public void addCustomerToTable(Customer customer) 
    {
        DefaultTableModel model = (DefaultTableModel) customersTable.getModel();
        model.addRow(new Object[]{customer.getQueueNumber(), customer.getCustomerName(), customer.getParcelID()});
    }

    public void clearCusTable() 
    {
        DefaultTableModel model = (DefaultTableModel) customersTable.getModel();
        model.setRowCount(0);
    }

    public void removeCustomerFromTable(Customer customer) 
    {
        DefaultTableModel model = (DefaultTableModel) customersTable.getModel();

        for (int i = 0; i < model.getRowCount(); i++) 
        {
            if (model.getValueAt(i, 2).equals(customer.getParcelID())) 
            {
                model.removeRow(i);
                break;
            }
        }
    }

    public void removeParcelFromTable(Parcel parcel) 
    {
        DefaultTableModel model = (DefaultTableModel) parcelTable.getModel();

        for (int i = 0; i < model.getRowCount(); i++) 
        {
            if (model.getValueAt(i, 0).equals(parcel.getParcelID())) 
            {
                model.removeRow(i);
                break;
            }
        }
    }

    public void addParcelToTable(Parcel parcel) 
    {
        String[] parcelColumnNames = {"Parcel ID", "Days", "Collected",  "Dimensions"};

        DefaultTableModel model = (DefaultTableModel) parcelTable.getModel();
        model.addRow(new Object[]{parcel.getParcelID(), parcel.getDaysInDepot(), parcel.getCollected(), parcel.getDimensions()});
    }

    public void clearParcelTable() 
    {
        DefaultTableModel model = (DefaultTableModel) parcelTable.getModel();
        model.setRowCount(0);
    }

    public Parcel parcelPopUp() 
    {
        JDialog dialog = new JDialog();

        dialog.setTitle("Enter Parcel Details");
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(null);
        dialog.setModal(true);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10)); 

        JLabel label1 = new JLabel("Parcel ID:");
        JTextField textField1 = new JTextField(15);

        JLabel label2 = new JLabel("Days Stored:");
        JTextField textField2 = new JTextField(15);

        JLabel label3 = new JLabel("Weight:");
        JTextField textField3 = new JTextField(15);

        JLabel label4 = new JLabel("Width:");
        JTextField textField4 = new JTextField(15);

        JLabel label5 = new JLabel("Height:");
        JTextField textField5 = new JTextField(15);

        JLabel label6 = new JLabel("Length:");
        JTextField textField6 = new JTextField(15);

        panel.add(label1);
        panel.add(textField1);
        panel.add(label2);
        panel.add(textField2);
        panel.add(label3);
        panel.add(textField3);
        panel.add(label4);
        panel.add(textField4);
        panel.add(label5);
        panel.add(textField5);
        panel.add(label6);
        panel.add(textField6);

        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("Add");
        JButton cancelButton = new JButton("Cancel");

        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        final Parcel[] result = new Parcel[1];

        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String stringValue = textField1.getText();
                int intValue = 0;
                double doubleValue1 = 0, doubleValue2 = 0, doubleValue3 = 0, doubleValue4 = 0;

                try {
                    intValue = Integer.parseInt(textField2.getText());
                    doubleValue1 = Double.parseDouble(textField3.getText());
                    doubleValue2 = Double.parseDouble(textField4.getText());
                    doubleValue3 = Double.parseDouble(textField5.getText());
                    doubleValue4 = Double.parseDouble(textField6.getText());

                    result[0] = new Parcel(stringValue, intValue, doubleValue1, doubleValue2, doubleValue3, doubleValue4);

                    dialog.dispose(); 

                } catch (NumberFormatException ex) {
                    // Show error message if there is invalid input
                    JOptionPane.showMessageDialog(dialog, "Please enter valid values.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);

        return result[0];
    }

    public String[] customerPopUp() 
    {
        JDialog dialog = new JDialog();

        dialog.setTitle("Enter Customer Details");
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(null);
        dialog.setModal(true);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10)); 

        JLabel label1 = new JLabel("Parcel ID:");
        JTextField textField1 = new JTextField(15);

        JLabel label2 = new JLabel("Customer Name:");
        JTextField textField2 = new JTextField(15);

        panel.add(label1);
        panel.add(textField1);
        panel.add(label2);
        panel.add(textField2);


        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("Add");
        JButton cancelButton = new JButton("Cancel");

        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        final String[] result = new String[2];

        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ID = textField1.getText();
                String Name = textField2.getText();
                try {
                    
                    result[0] = Name;
                    result[1] = ID;

                    dialog.dispose(); 

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "Please enter valid values.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);

        return result;
    }
    
}
