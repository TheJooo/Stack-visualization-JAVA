import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StackGUI extends JFrame {
    private StaticStack<Integer> stack;
    private DefaultListModel<Integer> listModel;
    private JList<Integer> stackList;
    private StackPanel stackPanel;

    public StackGUI(int size) {
        stack = new StaticStack<>(size);
        listModel = new DefaultListModel<>();
        stackList = new JList<>(listModel);
        stackPanel = new StackPanel();

        setTitle("Static Stack Visualization");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 4));

        JTextField inputField = new JTextField();
        JButton pushButton = new JButton("Push");
        JButton popButton = new JButton("Pop");
        JButton clearButton = new JButton("Clear");

        panel.add(inputField);
        panel.add(pushButton);
        panel.add(popButton);
        panel.add(clearButton);

        pushButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int value = Integer.parseInt(inputField.getText());
                    if (stack.isFull()) {
                        JOptionPane.showMessageDialog(null, "Stack is full. Reconsider life decisions, do you really need more numbers?", "Warning", JOptionPane.WARNING_MESSAGE);
                    } else {
                        stack.push(value);
                        listModel.addElement(value);
                        inputField.setText("");
                        stackPanel.repaint();  
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        popButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int poppedValue = stack.pop();
                    listModel.removeElementAt(listModel.size() - 1);
                    JOptionPane.showMessageDialog(null, "Popped: " + poppedValue);
                    stackPanel.repaint();  
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stack.clear();
                listModel.clear();
                JOptionPane.showMessageDialog(null, "All values have been cleared.");
                stackPanel.repaint();  
            }
        });

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(stackList), BorderLayout.CENTER);
        add(stackPanel, BorderLayout.SOUTH);  
    }

    private class StackPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int panelWidth = getWidth();
            int panelHeight = getHeight();
            int elementHeight = 40;
            int x = panelWidth / 4;  
            int y = panelHeight - elementHeight;
    
            Object[] stackData = stack.getData();  
            for (int i = 0; i < stackData.length; i++) {
                g.setColor(Color.GREEN);
                g.fillRect(x, y - (i * elementHeight), panelWidth / 2, elementHeight);
                g.setColor(Color.BLACK);
                g.drawRect(x, y - (i * elementHeight), panelWidth / 2, elementHeight);
                g.drawString(String.valueOf(stackData[i]), x + panelWidth / 4, y - (i * elementHeight) + 20);
            }
        }
    
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(400, 400);
        }
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StackGUI gui = new StackGUI(10);
            gui.setVisible(true);
        });
    }
}
