import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SolutionTable extends JFrame {
    int WIDTH = 1000;
    int HEIGHT = 1000;

    SolutionPanel solutionPanel;
    public SolutionTable(int[] weights, int[] values, int capacity) throws HeadlessException {
        solutionPanel = new SolutionPanel(weights,values,capacity);
        this.add(solutionPanel);
        //this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setSize(WIDTH,HEIGHT);
        this.setVisible(true);
    }
}

class SolutionPanel extends JPanel implements ActionListener{

    static int screen_width = 600;
    static int screen_height = 600;
    static int unit = 25;
    int[] weights;
    int[] values;
    int capcity;
    int [][]dp;
    Timer timer;
    final int SPEED = 1000;

    // Running state: -1: Not run yet, 0: running, 1: finish running
    int run = -1;
    SolutionPanel(int[] weights, int[] values, int capacity) {
        this.weights = weights;
        this.values = values;
        this.capcity = capacity;
        this.dp = initDp(this.weights, this.capcity);
        this.setPreferredSize(new Dimension(screen_height, screen_width));

        getStart(SPEED);
        //this.setBackground(Color.black);
        this.setFocusable(true);
        this.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paint(g);
    }

    public void setColorCell(int i, int j, String value, Graphics g){
        g.setColor(Color.yellow);
        int squareX = i*unit;
        int squareY = j*unit;
        int squareSize = unit;
        g.fillRect(squareX, squareY, squareSize, squareSize);
        g.setColor(Color.gray);
        g.drawString(value, squareX+4, squareY+16);
    }



    public void paint(Graphics g) {
        for (int i = 0; i < capcity; i++) {
            g.drawLine(i * unit, 0, i * unit, capcity*unit);

        }
        for (int i = 0; i < values.length; i++) {
            g.drawLine(0, i * unit, values.length*unit, i * unit);
        }
        knapsack(weights, values,capcity,g);

        //setColorCell(2,2,"9",g);
        //painting(g);
    }

    int testNum = 0;
    public void testPeform(){
        for (int i = 0; i < 4; i++) {
            this.testNum++;
        }

    }
//    public void knapsack(int[] ws, int[] vs, int cap,Graphics g){
//        int n = ws.length;
//        int [][] dp = initDp(ws,cap);
//
//
//        for (int i = 0; i < cap; i++) {
//            setColorCell(i,0,"0",g);
//        }
//        for (int i = 0; i <= vs.length; i++) {
//            setColorCell(0,i,"0",g);
//        }
//        int m = cap;
//        for (int i = 1; i <= n; ++i) {
//            for (int j  = 1; j < m; ++j) {
//              if (ws[i-1] <= j) {
//                dp[i][j] = Math.max(dp[i-1][j], vs[i-1] + dp[i-1][j - ws[i-1]]);
//                setColorCell(j,i,Integer.toString(dp[i][j]),g);
//              }else{
//                dp[i][j] = dp[i-1][j];
//                setColorCell(j,i,Integer.toString(dp[i][j]),g);
//              }
//            }
//        }
//    }

    public void knapsack(){
        int n = this.weights.length;

        int m = this.capcity;
        for (int i = 1; i <= n; ++i) {
            for (int j  = 1; j < m; ++j) {
                if (weights[i-1] <= j) {
                    dp[i][j] = Math.max(dp[i-1][j], this.values[i-1] + dp[i-1][j - weights[i-1]]);
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
    }

    public void knapsack1() {

    }
    public void sleep() {
        long start = System.currentTimeMillis();

        long elapsedTime = System.currentTimeMillis()- start;
        while (elapsedTime < 1000){
            elapsedTime = System.currentTimeMillis() - start;
        }

    }
    int [][] initDp(int[] weights, int capacity){
        int n = weights.length;
        int[][] dp = new int[n+1][capacity + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < capacity + 1; j++) {
                dp[i][j] = 0;
            }
        }
        return dp;
    }
    public void getStart(int delay) {
        timer = new Timer(delay, this);
        timer.start();
    }
    public void stop() {
        if (run == 1) {
            timer.stop();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (run == -1) {
            // Activate clicking beginning point
            this.run = 0;

        } else{
            if(this.testNum < 4) {
                int n = this.weights.length;

                int m = this.capcity;
                for (int i = 1; i <= n; ++i) {
                    for (int j  = 1; j < m; ++j) {
                        if (weights[i-1] <= j) {
                            dp[i][j] = Math.max(dp[i-1][j], this.values[i-1] + dp[i-1][j - weights[i-1]]);
                        }else{
                            dp[i][j] = dp[i-1][j];
                        }
                    }
                }
            }else {
                stop();
            }

        }


    }


    // void painting(Graphics g){
    //     for (int i = 0; i < values.length; i++) {
    //         for (int j = 0; j < values.length; j++) {
    //             System.out.println(i+ " "+ j);
    //         }
    //     }
    // }
}
