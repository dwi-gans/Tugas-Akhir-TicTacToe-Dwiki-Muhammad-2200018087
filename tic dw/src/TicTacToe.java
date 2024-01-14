import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame {
    private JButton[][] buttons;
    private char currentPlayer;
    private boolean vsComputer;

    public TicTacToe(boolean vsComputer) {
        this.vsComputer = vsComputer;
        buttons = new JButton[3][3];
        currentPlayer = 'X';

        initializeUI();
    }

    private void initializeUI() {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new GridLayout(3, 3));
        setFont(new Font("Arial", Font.PLAIN, 20));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].setBackground(new Color(220, 220, 220));
                buttons[i][j].addActionListener(new ButtonClickListener());
                add(buttons[i][j]);
            }
        }

        setLocationRelativeTo(null);
        setVisible(true);

        if (vsComputer && currentPlayer == 'O') {
            playComputerTurn();
        }
    }

    private boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if ((buttons[i][0].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[i][1].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[i][2].getText().equals(String.valueOf(currentPlayer))) ||
                    (buttons[0][i].getText().equals(String.valueOf(currentPlayer)) &&
                            buttons[1][i].getText().equals(String.valueOf(currentPlayer)) &&
                            buttons[2][i].getText().equals(String.valueOf(currentPlayer)))) {
                return true;
            }
        }

        if ((buttons[0][0].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][2].getText().equals(String.valueOf(currentPlayer))) ||
                (buttons[0][2].getText().equals(String.valueOf(currentPlayer)) &&
                        buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
                        buttons[2][0].getText().equals(String.valueOf(currentPlayer)))) {
            return true;
        }

        return false;
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setBackground(new Color(220, 220, 220));
            }
        }
        currentPlayer = 'X';

        if (vsComputer && currentPlayer == 'O') {
            playComputerTurn();
        }
    }

    private void playComputerTurn() {
        int row, col;
        do {
            row = (int) (Math.random() * 3);
            col = (int) (Math.random() * 3);
        } while (!buttons[row][col].getText().equals(""));

        buttons[row][col].setText(String.valueOf(currentPlayer));
        buttons[row][col].setBackground(new Color(173, 216, 230));

        if (checkWin()) {
            JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " wins!");
            resetGame();
        } else if (isBoardFull()) {
            JOptionPane.showMessageDialog(null, "It's a tie!");
            resetGame();
        } else {
            switchPlayer();
        }
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clickedButton = (JButton) e.getSource();
            if (clickedButton.getText().equals("")) {
                clickedButton.setText(String.valueOf(currentPlayer));
                clickedButton.setBackground(new Color(173, 216, 230));

                if (checkWin()) {
                    JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " wins!");
                    resetGame();
                } else if (isBoardFull()) {
                    JOptionPane.showMessageDialog(null, "It's a tie!");
                    resetGame();
                } else {
                    switchPlayer();
                    if (vsComputer && currentPlayer == 'O') {
                        playComputerTurn();
                    }
                }
            }
        }
    }
}
