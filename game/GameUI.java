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
    private int goodPlayersCount;
    private int evilPlayersCount;
    private List<Integer> goodPlayersHistory;
    private List<Integer> evilPlayersHistory;
    private boolean isUpdating;
    private Timer timer;

    public GameUI() {
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
        Board board = new Board(40, 40);
        GameLogic gl = new GameLogic(board, 20, 1100);

        // Timer for updating the counts
        timer = new Timer(1, e -> {
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
        this.goodPlayersCount = goodPlayers;
        this.evilPlayersCount = evilPlayers;
        goodPlayersLabel.setText("Good Players: " + goodPlayers);
        evilPlayersLabel.setText("Evil Players: " + evilPlayers);

        goodPlayersHistory.add(goodPlayers);
        evilPlayersHistory.add(evilPlayers);

        repaint();
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
            int width = getWidth();
            int height = getHeight();
            int padding = 50;

            // Draw axes
            g.drawLine(padding, height - padding, width - padding, height - padding); // X-axis
            g.drawLine(padding, padding, padding, height - padding); // Y-axis

            if (goodPlayersHistory.isEmpty())
                return;

            int maxPoints = goodPlayersHistory.size();
            int maxPlayers = Math.max(goodPlayersHistory.stream().max(Integer::compareTo).orElse(0),
                    evilPlayersHistory.stream().max(Integer::compareTo).orElse(0));

            // Plot points
            for (int i = 1; i < maxPoints; i++) {
                int x1 = padding + (i - 1) * (width - 2 * padding) / maxPoints;
                int y1Good = height - padding - goodPlayersHistory.get(i - 1) * (height - 2 * padding) / maxPlayers;
                int y1Evil = height - padding - evilPlayersHistory.get(i - 1) * (height - 2 * padding) / maxPlayers;

                int x2 = padding + i * (width - 2 * padding) / maxPoints;
                int y2Good = height - padding - goodPlayersHistory.get(i) * (height - 2 * padding) / maxPlayers;
                int y2Evil = height - padding - evilPlayersHistory.get(i) * (height - 2 * padding) / maxPlayers;

                g.setColor(Color.BLUE);
                g.drawLine(x1, y1Good, x2, y2Good);

                g.setColor(Color.RED);
                g.drawLine(x1, y1Evil, x2, y2Evil);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameUI ui = new GameUI();
            ui.setVisible(true);
        });
    }
}