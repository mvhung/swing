import javax.swing.*;
import java.awt.*;

public class SolutionTable extends JFrame {
    int WIDTH = 1000;
    int HEIGHT = 1000;

    SolutionPanel solutionPanel;
    public SolutionTable(int[] weights, int[] values, int capacity) throws HeadlessException {
        solutionPanel = new SolutionPanel(weights,values,capacity);
        this.add(solutionPanel);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setSize(WIDTH,HEIGHT);
        this.setVisible(true);
    }
}

class SolutionPanel extends JPanel{

    static int screen_width = 600;
    static int screen_height = 600;
    static int unit = 25;
    int[] weights;
    int[] values;
    int capcity;
    SolutionPanel(int[] weights, int[] values, int capacity) {
        this.weights = weights;
        this.values = values;
        this.capcity = capacity;
        this.setPreferredSize(new Dimension(screen_height, screen_width));
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
        int squareY = i*unit;
        int squareSize = unit;
        g.fillRect(squareX, squareY, squareSize, squareSize);
        g.setColor(Color.gray);
        g.drawString(value, squareX+4, squareY+16);
    }



    public void paint(Graphics g) {
        for (int i = 0; i < capcity; i++) {
            g.drawLine(i * unit, 0, i * unit, screen_height);

        }
        for (int i = 0; i < values.length; i++) {
            g.drawLine(0, i * unit, screen_width, i * unit);
        }
        knapsack(weights, values,capcity,g);

//        setColorCell(2,2,"9",g);
    }

    public void knapsack(int[] ws, int[] vs, int cap,Graphics g){
        int n = ws.length;
        int [][] dp = initDp(ws,cap);

        for(int i = 1; i <= n; i++) {
            for (int j = 0; j <= cap; j++){
                if (ws[i-1] <= j) {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-ws[i]] + vs[i]);
                    this.setColorCell(i,j,Integer.toString(dp[i][j]),g);
                }
                else{
                    dp[i][j] = dp[i-1][j];
                    this.setColorCell(i,j,Integer.toString(dp[i][j]),g);
                }
            }
        }
    }

    int [][] initDp(int[] weights, int capacity){
        int n = weights.length;
        int[][] dp = new int[n][capacity + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < capacity + 1; j++) {
                dp[i][j] = 0;
            }
        }
        return dp;
    }


}
