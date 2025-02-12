package minesweeper;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class GridGui extends JFrame {
    private int safeTiles = 0;

    public GridGui() {
        Grid grid = new Grid();
      //layout
        setLayout(new GridLayout(grid.getNumRows(), grid.getNumColumns(), 10, 10));

        JButton[][] bombButtons = new JButton[grid.getNumRows()][grid.getNumColumns()];

        // Updated resource paths
        Icon bombIcon = new ImageIcon(getClass().getResource("/bomb.png"));
        Icon flagIcon = new ImageIcon(getClass().getResource("/flag.png"));

        // BombGrid buttons
        for (int row = 0; row < grid.getNumRows(); ++row) {
            for (int col = 0; col < grid.getNumRows(); ++col) {

                final int ID = row; // button ID
                final int ID2 = col;

                bombButtons[ID][ID2] = new JButton();

                bombButtons[ID][ID2].addMouseListener(new MouseListener() {

                    @Override
                    public void mousePressed(MouseEvent e) {

                        if (SwingUtilities.isRightMouseButton(e) && bombButtons[ID][ID2].isEnabled()) {
                            // Mark as FLAGGED
                            bombButtons[ID][ID2].setIcon(flagIcon);
                        }

                        if (grid.isBombAtLocation(ID, ID2) && SwingUtilities.isLeftMouseButton(e)) {

                            bombButtons[ID][ID2].setPressedIcon(bombIcon);
                            bombButtons[ID][ID2].setEnabled(false);

                            bombButtons[ID][ID2].setText(String.valueOf((grid.getCountAtLocation(ID, ID2))));

                            bombButtons[ID][ID2].setSelected(true);

                            for (int x = 0; x < grid.getNumRows(); ++x) {
                                for (int y = 0; y < grid.getNumRows(); ++y) {
                                    bombButtons[x][y].setText((grid.getBombGrid()[x][y]) ? ""
                                            : String.valueOf((grid.getCountAtLocation(x, y))));
                                    bombButtons[x][y].setIcon((grid.getBombGrid()[x][y]) ? bombIcon : flagIcon);
                                    bombButtons[x][y].setEnabled(false);
                                }
                            }

                            loseScreen();

                        } else if (!grid.isBombAtLocation(ID, ID2) && SwingUtilities.isLeftMouseButton(e)) {
                            revealTiles(grid, bombButtons, ID, ID2);
                        }

                        safeTiles++;
                        if (safeTiles == (grid.getNumRows() * grid.getNumColumns() - grid.getNumBombs())) {

                            for (int x = 0; x < grid.getNumRows(); ++x) {
                                for (int y = 0; y < grid.getNumRows(); ++y) {
                                    bombButtons[x][y].setText((grid.getBombGrid()[x][y]) ? ""
                                            : String.valueOf((grid.getCountAtLocation(x, y))));
                                    bombButtons[x][y].setIcon((grid.getBombGrid()[x][y]) ? bombIcon : flagIcon);
                                    bombButtons[x][y].setEnabled(false);
                                }
                            }
                            winScreen();
                        }

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                    }

                    @Override
                    public void mouseClicked(MouseEvent e) {
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }
                });

                add(bombButtons[ID][ID2]);
            }
        }

        setTitle("Minesweeper");
        setSize(800, 800); 
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void revealTiles(Grid grid, JButton[][] bombButtons, int ID, int ID2) {
        bombButtons[ID][ID2].setEnabled(false);

        if (ID >= 0 && ID2 >= 0 && ID < grid.getNumRows() && ID2 < grid.getNumColumns()) {
            bombButtons[ID][ID2].setText(String.valueOf(grid.getCountAtLocation(ID, ID2)));
            if (ID + 1 < grid.getNumRows()) {
                bombButtons[ID + 1][ID2].setText(String.valueOf(grid.getCountAtLocation(ID + 1, ID2)));
            }
            if (ID + 1 < grid.getNumRows() && ID2 + 1 < grid.getNumColumns()) {
                bombButtons[ID + 1][ID2 + 1].setText(String.valueOf(grid.getCountAtLocation(ID + 1, ID2 + 1)));
            }
            if (ID2 - 1 >= 0) {
                bombButtons[ID][ID2 - 1].setText(String.valueOf(grid.getCountAtLocation(ID, ID2 - 1)));
            }
            if (ID - 1 >= 0) {
                bombButtons[ID - 1][ID2].setText(String.valueOf(grid.getCountAtLocation(ID - 1, ID2)));
            }
            if (ID - 1 >= 0 && ID2 - 1 >= 0) {
                bombButtons[ID - 1][ID2 - 1].setText(String.valueOf(grid.getCountAtLocation(ID - 1, ID2 - 1)));
            }
            if (ID2 + 1 < grid.getNumColumns() && ID - 1 >= 0) {
                bombButtons[ID - 1][ID2 + 1].setText(String.valueOf(grid.getCountAtLocation(ID - 1, ID2 + 1)));
            }
            if (ID + 1 < grid.getNumRows() && ID2 - 1 >= 0) {
                bombButtons[ID + 1][ID2 - 1].setText(String.valueOf(grid.getCountAtLocation(ID + 1, ID2 - 1)));
            }
            if (ID2 + 1 < grid.getNumColumns()) {
                bombButtons[ID][ID2 + 1].setText(String.valueOf(grid.getCountAtLocation(ID, ID2 + 1)));
            }
        }

    }

    public void winScreen() {
        int choice = JOptionPane.showConfirmDialog(null, "You Win!!!\nWould you like to try again?", "Minesweeper",
                JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.NO_OPTION) {
            System.exit(0);
        } else {
            new GridGui();
        }
    }

    public void loseScreen() {
        int choice = JOptionPane.showConfirmDialog(null, "Boom! You lose!\nWould you like to try again?", "Minesweeper",
                JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.NO_OPTION) {
            System.exit(0);
        } else {
            new GridGui();
        }
    }

    public static void main(String[] args) {
        new GridGui();
    }
}
