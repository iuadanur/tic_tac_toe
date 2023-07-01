import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class tic_tac_toe implements ActionListener {

    private JFrame frame;
    private JPanel panel;
    private JButton[][] buttons = new JButton[3][4];
    private boolean xTurn = true;
    private int xScore = 0;
    private int oScore = 0;
    private String player1;
    private String player2;

    public tic_tac_toe(){
        frame = new JFrame("Tic-Tac-Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        ImageIcon image = new ImageIcon("tic-tac-toe.jpeg");
        frame.setIconImage(image.getImage());

        // Player Names
        player1 = JOptionPane.showInputDialog(frame, "Enter Player 1 name:");
        player2 = JOptionPane.showInputDialog(frame, "Enter Player 2 name:");

        panel = new JPanel();
        panel.setLayout(new GridLayout(3, 4));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for(int i=0; i<3; i++){
            for(int j=0; j<4; j++){
                if(j < 3){
                    buttons[i][j] = new JButton();
                    buttons[i][j].setFont(new Font("Lucida Bright", Font.BOLD, 40));
                    buttons[i][j].addActionListener(this);
                    panel.add(buttons[i][j]);
                }
                else{
                    buttons[i][j] = new JButton();
                    buttons[i][j].setFont(new Font("Lucida Bright", Font.BOLD, 16));
                    panel.add(buttons[i][j]);
                }
            }
        }

        buttons[0][3].setText(player1+" - "+xScore);
        buttons[1][3].setText(player2+" - "+oScore);
        buttons[2][3].setText("Reset");
        buttons[2][3].addActionListener(e -> resetScore());

        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(600, 450);
        frame.setVisible(true);
    }
    public void resetScore(){
        resetGame();
        setxScore(0);
        setoScore(0);
        buttons[0][3].setText(player1+" - "+xScore);
        buttons[1][3].setText(player2+" - "+oScore);
    }
    public void setxScore(int xScore) {
        this.xScore = xScore;
    }

    public void setoScore(int oScore) {
        this.oScore = oScore;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if (xTurn)
            button.setText("X");
        else
            button.setText("O");
        button.setEnabled(false);
        xTurn = !xTurn;

        checkForWinner();
    }

    public void checkForWinner() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(buttons[i][1].getText()) && buttons[i][0].getText().equals(buttons[i][2].getText()) && !buttons[i][0].isEnabled()) {
                String winner = buttons[i][0].getText();
                if(winner.equals("X"))
                    JOptionPane.showMessageDialog(frame, player1 + " wins!");
                else
                    JOptionPane.showMessageDialog(frame, player2 + " wins!");
                updateScore(winner);
                resetGame();
                return;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (buttons[0][i].getText().equals(buttons[1][i].getText()) && buttons[0][i].getText().equals(buttons[2][i].getText()) && !buttons[0][i].isEnabled()) {
                String winner = buttons[0][i].getText();
                if(winner.equals("X"))
                    JOptionPane.showMessageDialog(frame, player1 + " wins!");
                else
                    JOptionPane.showMessageDialog(frame, player2 + " wins!");
                updateScore(winner);
                resetGame();
                return;
            }
        }

        // Check diagonals
        if (buttons[0][0].getText().equals(buttons[1][1].getText()) && buttons[0][0].getText().equals(buttons[2][2].getText()) && !buttons[0][0].isEnabled()) {
            String winner = buttons[0][0].getText();
            if(winner.equals("X"))
                JOptionPane.showMessageDialog(frame, player1 + " wins!");
            else
                JOptionPane.showMessageDialog(frame, player2 + " wins!");
            updateScore(winner);
            resetGame();
            return;
        }
        if (buttons[0][2].getText().equals(buttons[1][1].getText()) && buttons[0][2].getText().equals(buttons[2][0].getText()) && !buttons[0][2].isEnabled()) {
            String winner = buttons[0][2].getText();
            if(winner.equals("X"))
                JOptionPane.showMessageDialog(frame, player1 + " wins!");
            else
                JOptionPane.showMessageDialog(frame, player2 + " wins!");
            updateScore(winner);
            resetGame();
            return;
        }

        // Check for tie
        boolean tie = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].isEnabled()) {
                    tie = false;
                    break;
                }
            }
            if (!tie) {
                break;
            }
        }
        if (tie) {
            JOptionPane.showMessageDialog(frame, "Tie game!");
            resetGame();
        }
    }

    public void updateScore(String winner) {
        if (winner.equals("X")) {
            xScore++;
            buttons[0][3].setText(player1+" - "+ xScore);
        } else if (winner.equals("O")) {
            oScore++;
            buttons[1][3].setText(player2+" - "+ oScore);
        }
    }

    public void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
        }
        xTurn = true;
    }

    public static void main(String[] args) {
        new tic_tac_toe();
    }
}





