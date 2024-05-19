package game;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class GameUI extends JFrame {
    private final JLabel goodPlayersLabel;
    private final JLabel evilPlayersLabel;
    private final List<Integer> goodPlayersHistory;
    private final List<Integer> evilPlayersHistory;
    private boolean isUpdating;
    private final Timer timer;
    private static final int MAX_HISTORY_SIZE = 1000;

    private final UIBoard UIboard;
    private final Board board;

    public GameUI(int boardDimension, int playersNumber, int foodAmount) {
        setTitle("Good vs Evil Players");
        setSize(1200, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        goodPlayersLabel = new JLabel("Good Players: 0");
        evilPlayersLabel = new JLabel("Evil Players: 0");

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(1, 2));
        labelPanel.add(goodPlayersLabel);
        labelPanel.add(evilPlayersLabel);

        goodPlayersHistory = new ArrayList<>();
        evilPlayersHistory = new ArrayList<>();

        GraphPanel graphPanel = new GraphPanel();
        UIboard = new UIBoard(boardDimension, boardDimension);
        UIboard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                isUpdating = !isUpdating;
            }
        });

        // Use a GridLayout to ensure equal sizes for graphPanel and UIboard
        JPanel mainPanel = new JPanel(new GridLayout(1, 2));
        mainPanel.add(graphPanel);
        mainPanel.add(UIboard);

        add(labelPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        isUpdating = true;

        // Initialize the board
        this.board = new Board(boardDimension, boardDimension);
        GameLogic gl = new GameLogic(board, playersNumber, foodAmount);

        timer = new Timer(50, e -> {
            if (isUpdating) {
                int goodPlayers = gl.getSocial();
                int evilPlayers = gl.getGreedy();
                gl.nextTurn();
                updateCounts(goodPlayers, evilPlayers);
                updateBoard(gl);
            }
        });

        timer.start();
    }

    public void updateCounts(int goodPlayers, int evilPlayers) {
        goodPlayersLabel.setText("Good Players: " + goodPlayers);
        evilPlayersLabel.setText("Evil Players: " + evilPlayers);

        addValueToHistory(goodPlayersHistory, goodPlayers);
        addValueToHistory(evilPlayersHistory, evilPlayers);

        repaint();
    }

    private void addValueToHistory(List<Integer> history, int value) {
        if (history.size() >= MAX_HISTORY_SIZE) {
            history.remove(0);
        }
        history.add(value);
    }

    private void updateBoard(GameLogic gl) {
        // Assume gl provides a way to get the current state of the board
        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getColumns(); col++) {
                int state = board.getCell(row, col).getState();
                boolean hasFood = board.getCell(row, col).hasFood();
                UIboard.setCellState(row, col, state);
                UIboard.setCellHasFood(row, col, hasFood);
            }
        }
        UIboard.repaint();
    }

    class GraphPanel extends JPanel {
        public GraphPanel() {
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    isUpdating = !isUpdating;
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();
            int padding = 50;

            // Draw axes
            g2d.drawLine(padding, height - padding, width - padding, height - padding); // X-axis
            g2d.drawLine(padding, padding, padding, height - padding); // Y-axis

            if (goodPlayersHistory.isEmpty())
                return;

            int maxPoints = goodPlayersHistory.size();
            int maxPlayers = Math.max(goodPlayersHistory.stream().max(Integer::compareTo).orElse(0),
                    evilPlayersHistory.stream().max(Integer::compareTo).orElse(0));

            int[] xPoints = new int[maxPoints + 2];
            int[] yPointsGood = new int[maxPoints + 2];
            int[] yPointsEvil = new int[maxPoints + 2];

            xPoints[0] = padding;
            yPointsGood[0] = height - padding;
            yPointsEvil[0] = height - padding;

            for (int i = 0; i < maxPoints; i++) {
                xPoints[i + 1] = padding + i * (width - 2 * padding) / maxPoints;
                yPointsGood[i + 1] = height - padding - goodPlayersHistory.get(i) * (height - 2 * padding) / maxPlayers;
                yPointsEvil[i + 1] = height - padding - evilPlayersHistory.get(i) * (height - 2 * padding) / maxPlayers;
            }

            xPoints[maxPoints + 1] = padding + (maxPoints - 1) * (width - 2 * padding) / maxPoints;
            yPointsGood[maxPoints + 1] = height - padding;
            yPointsEvil[maxPoints + 1] = height - padding;

            // Draw the filled areas
            g2d.setColor(new Color(173, 216, 230, 150)); // Light blue with some transparency
            g2d.fillPolygon(xPoints, yPointsGood, maxPoints + 2);

            g2d.setColor(new Color(255, 182, 193, 150)); // Light red with some transparency
            g2d.fillPolygon(xPoints, yPointsEvil, maxPoints + 2);

            // Draw the lines
            g2d.setColor(Color.BLUE);
            for (int i = 1; i < maxPoints; i++) {
                int x1 = padding + (i - 1) * (width - 2 * padding) / maxPoints;
                int y1Good = height - padding - goodPlayersHistory.get(i - 1) * (height - 2 * padding) / maxPlayers;
                int x2 = padding + i * (width - 2 * padding) / maxPoints;
                int y2Good = height - padding - goodPlayersHistory.get(i) * (height - 2 * padding) / maxPlayers;
                g2d.drawLine(x1, y1Good, x2, y2Good);
            }

            g2d.setColor(Color.RED);
            for (int i = 1; i < maxPoints; i++) {
                int x1 = padding + (i - 1) * (width - 2 * padding) / maxPoints;
                int y1Evil = height - padding - evilPlayersHistory.get(i - 1) * (height - 2 * padding) / maxPlayers;
                int x2 = padding + i * (width - 2 * padding) / maxPoints;
                int y2Evil = height - padding - evilPlayersHistory.get(i) * (height - 2 * padding) / maxPlayers;
                g2d.drawLine(x1, y1Evil, x2, y2Evil);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameUI gameUI = new GameUI(40, 20, 1000); // Example parameters
            gameUI.setVisible(true);
        });
    }
}

class UIBoard extends JPanel {
    private int rows;
    private int cols;
    private BoardCell[][] cells;

    public UIBoard(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.cells = new BoardCell[rows][cols];

        setLayout(new GridLayout(rows, cols));

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                cells[row][col] = new BoardCell(BoardCell.EMPTY, false);
                add(cells[row][col]);
            }
        }
    }

    public void setCellState(int row, int col, int state) {
        if (isValidCell(row, col)) {
            cells[row][col].setState(state);
        }
    }

    public void setCellHasFood(int row, int col, boolean hasFood) {
        if (isValidCell(row, col)) {
            cells[row][col].setHasFood(hasFood);
        }
    }

    private boolean isValidCell(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }
}

class BoardCell extends JPanel {
    public static final int EMPTY = 0;
    public static final int RED = 1;
    public static final int BLUE = 2;

    private int state;
    private boolean hasFood;

    public BoardCell(int state, boolean hasFood) {
        this.state = state;
        this.hasFood = hasFood;
        setPreferredSize(new Dimension(20, 20));
    }

    public void setState(int state) {
        this.state = state;
        repaint();
    }

    public void setHasFood(boolean hasFood) {
        this.hasFood = hasFood;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        switch (state) {
            case RED:
                g.setColor(Color.RED);
                break;
            case BLUE:
                g.setColor(Color.BLUE);
                break;
            default:
                g.setColor(Color.WHITE);
                break;
        }

        g.fillRect(0, 0, getWidth(), getHeight());

        if (hasFood) {
            g.setColor(Color.GREEN);
            g.fillOval(getWidth() / 4, getHeight() / 4, getWidth() / 2, getHeight() / 2);
        }
    }
}

