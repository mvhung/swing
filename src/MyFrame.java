import javax.swing.*;

public class MyFrame extends JFrame {
    MyPanel panel;
    public MyFrame() {
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setTitle("");
        panel = new MyPanel();

        this.add(panel);
        this.pack();
        this.setVisible(true);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
    }
}
