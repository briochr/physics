import java.awt.*;
import java.awt.event.*;

public class Test extends Frame {
    
    private TextField textField;
    private Button button;

    public Test() {
        setLayout(new FlowLayout());
        
        textField = new TextField(20);
        button = new Button("Click Me");
        
        add(textField);
        add(button);
        
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText();
                System.out.println("Button clicked! Text entered: " + text);
            }
        });
        
        setTitle("Simple GUI");
        setSize(300, 200);
        setVisible(true);
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        new Test();
    }
}
