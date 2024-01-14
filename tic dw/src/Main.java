import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        int choice = JOptionPane.showOptionDialog(null, "Choose game mode", "Tic Tac Toe",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                new Object[]{"Player vs Player", "Player vs Computer"}, "Player vs Player");

        boolean vsComputer = (choice == JOptionPane.NO_OPTION);
        new TicTacToe(vsComputer);
    }
}
