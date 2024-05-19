package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class GameUI extends JFrame {
    private JLabel goodPlayersLabel;
    private JLabel evilPlayersLabel;
    private List<Integer> goodPlayersHistory;
    private List<Integer> evilPlayersHistory;
    private boolean isUpdating;
    private Timer timer;
    private static final int MAX_HISTORY_SIZE = 1000;

    public GameUI(int boardDimension, int playersNumber, int foodAmount) {
        setTitle("Good vs Evil Players");
        setSize(800, 600);
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
        add(labelPanel, BorderLayout.NORTH);
        add(graphPanel, BorderLayout.CENTER);

        isUpdating = true;

        // Timer for updating the counts
        Board board = new Board(boardDimension, boardDimension);
        GameLogic gl = new GameLogic(board, playersNumber, foodAmount);

        // Timer for updating the counts
        timer = new Timer(0, e -> {
            if (isUpdating) {
                int goodPlayers = gl.getSocial();
                int evilPlayers = gl.getGreedy();
                gl.nextTurn();
                updateCounts(goodPlayers, evilPlayers);
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

    class GraphPanel extends JPanel {
        public GraphPanel() {
            addMouseListener(new MouseAdapter() {

                public void mouseClicked(MouseEvent e) {
                    isUpdating = !isUpdating;
                }
            });
        }

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
}