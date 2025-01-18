import javax.swing.*;

public class PopupExample {
    public static void main(String[] args) {
        // Show an input dialog
        String input = JOptionPane.showInputDialog(null, "Enter your name:", "Input", JOptionPane.QUESTION_MESSAGE);
        
        if (input != null) {
            JOptionPane.showMessageDialog(null, "Hello, " + input + "!", "Greeting", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No input received.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
