import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class MyPanel extends JPanel {
    static int WIDTH_SCREEN = 1200;
    static int HEIGHT_SCREEN = 500;
    int unitSize = 20;
    int boundX = WIDTH_SCREEN / unitSize;
    int boundY = HEIGHT_SCREEN / unitSize;
    private final Font font = new Font("Arial", Font.BOLD, 15);

    JTextField weights = new JTextField();
    JTextField values = new JTextField();
    JTextField capacity = new JTextField();

    JButton runBtn = new JButton();
    JRadioButton greedBtn = new JRadioButton("skhfdsiuduafiududohiadud");
    JRadioButton dpBtn = new JRadioButton();

    ButtonGroup buttonGroup =new ButtonGroup();
    int [] valueAr;
    int [] weightAr;
    int cap;
    public  MyPanel() {
        setupWeightsField();
        setupValuesField();
        setupCapacityField();
        setupRunBtn();
        setupButtonGroup();
        setupGreedBtn();
        setupDpBtn();

        this.add(weights);
        this.add(greedBtn);
        this.add(dpBtn);
        this.add(values);
        this.add(capacity);
        this.add(runBtn);

        this.setLayout(null);
        this.setPreferredSize(new Dimension(WIDTH_SCREEN, HEIGHT_SCREEN + 70));
    }
    void setupButtonGroup(){
        buttonGroup.add(this.greedBtn);
        buttonGroup.add(this.dpBtn);
    }
    void setupGreedBtn(){
        greedBtn.setFont(font);
        greedBtn.setText("greedy");
//        greedBtn.set
        greedBtn.setBounds(WIDTH_SCREEN/2 -50, HEIGHT_SCREEN/2 , 20, 20);
    }
    void setupDpBtn(){
        dpBtn.setFont(font);
        dpBtn.setText("greedy");
        dpBtn.setBounds(WIDTH_SCREEN/2 -30, HEIGHT_SCREEN/2 , 20, 20);
    }
    void setupWeightsField() {
        weights.setFont(font);
        weights.setText("2,2,1,1");
        weights.setBounds(25, HEIGHT_SCREEN + 20, 150, 40);
    }

    void setupValuesField(){
        values.setFont(font);
        values.setText("3,2,2,1");
        values.setBounds(25, HEIGHT_SCREEN - 100, 150, 40);
    }
    void setupCapacityField(){
        capacity.setFont(font);
        capacity.setText("4");
        capacity.setBounds(25, HEIGHT_SCREEN - 140, 150, 40);
    }

    void setupRunBtn(){
        runBtn.setFont(font);
        runBtn.setText("Run");
        runBtn.setBounds(WIDTH_SCREEN -150, HEIGHT_SCREEN - 140, 150, 40);

        runBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String v = values.getText();
                String w = weights.getText();
                cap = Integer.parseInt(capacity.getText());

                valueAr = getData(v);
                weightAr = getData(w);

                new SolutionTable(weightAr,valueAr,cap+1);
            }
        });
    }

    int[] getData(String str){
        String[] ar = str.split(",");
        int [] res= new int[ar.length];
        for (int i = 0; i<ar.length; i++) {
            res[i] = Integer.parseInt(ar[i].trim());
        }
        return res;
    }


}
