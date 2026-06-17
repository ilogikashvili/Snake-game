import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    // Screen dimensions (fixed game window size)
    static final int screen_width = 600;
    static final int screen_height = 600;

    // Size of each grid unit (snake segments and apple size)
    static final int unit_size = 25;

    // Total number of possible grid positions (used for arrays)
    static final int game_units = (screen_width * screen_height) / unit_size;

    // Game speed (lower = faster snake movement)
    static final int delay = 75;

    // Snake position arrays (each index = one body segment)
    final int[] x = new int[game_units];
    final int[] y = new int[game_units];

    // Current snake length (number of active segments)
    int bodyParts = 6;

    // Score tracking (number of apples collected)
    int applesEaten = 0;

    // Apple position
    int appleX;
    int appleY;

    // Current movement direction of the snake
    char direction = 'R';

    // Game state flag (true = running, false = game over)
    boolean running = false;

    // Swing timer controlling game loop updates
    Timer timer;

    // Random generator for apple placement
    Random random;

    GamePanel() {
        random = new Random();

        // Set panel size and appearance
        this.setPreferredSize(new Dimension(screen_width, screen_height));
        this.setBackground(Color.black);

        // Enable keyboard input for this component
        this.setFocusable(true);

        // Register a key listener for direction control
        this.addKeyListener(new MyKeyAdapter());

        startGame();
    }

    /**
     * Initializes game state and starts the game loop.
     */
    public void startGame() {
        newApple();
        running = true;

        timer = new Timer(delay, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    /**
     * Renders all game objects (grid, snake, apple, UI).
     */
    public void draw(Graphics g) {

        if (running) {

            // Draw grid lines for a visual structure
            for (int i = 0; i < screen_height / unit_size; i++) {
                g.drawLine(i * unit_size, 0, i * unit_size, screen_height);
                g.drawLine(0, i * unit_size, screen_width, i * unit_size);
            }

            // Draw apple
            g.setColor(Color.red);
            g.fillOval(appleX, appleY, unit_size, unit_size);

            // Draw snake
            for (int i = 0; i < bodyParts; i++) {

                if (i == 0) {
                    // Head of snake
                    g.setColor(Color.green);
                } else {
                    // Body segments
                    g.setColor(new Color(45, 180, 0));
                }

                g.fillRect(x[i], y[i], unit_size, unit_size);
            }

            // Draw score
            g.setColor(Color.red);
            g.setFont(new Font("Ink Free", Font.BOLD, 40));

            FontMetrics metrics = getFontMetrics(g.getFont());

            g.drawString(
                    "Score: " + applesEaten,
                    (screen_width - metrics.stringWidth("Score: " + applesEaten)) / 2,
                    g.getFont().getSize()
            );

        } else {
            gameOver(g);
        }
    }

    /**
     * Spawns a new apple at a random grid position.
     */
    public void newApple() {
        appleX = random.nextInt(screen_width / unit_size) * unit_size;
        appleY = random.nextInt(screen_height / unit_size) * unit_size;
    }

    /**
     * Updates snake position based on the current direction.
     */
    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (direction) {
            case 'U' -> y[0] -= unit_size;
            case 'D' -> y[0] += unit_size;
            case 'L' -> x[0] -= unit_size;
            case 'R' -> x[0] += unit_size;
        }
    }

    /**
     * Checks if snake head collides with apple.
     * If yes, increases score and grows snake.
     */
    public void checkApple() {
        if (x[0] == appleX && y[0] == appleY) {
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }

    /**
     * Checks for collisions with:
     * - snake body
     * - screen boundaries
     */
    public void checkCollisions() {

        // Self collision
        for (int i = bodyParts; i > 0; i--) {
            if (x[0] == x[i] && y[0] == y[i]) {
                running = false;
                break;
            }
        }

        // Wall collisions
        if (x[0] < 0 || x[0] > screen_width ||
                y[0] < 0 || y[0] > screen_height) {
            running = false;
        }

        if (!running) {
            timer.stop();
        }
    }

    /**
     * Displays game over screen and final score.
     */
    public void gameOver(Graphics g) {

        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));

        FontMetrics metrics1 = getFontMetrics(g.getFont());

        g.drawString(
                "Game Over",
                (screen_width - metrics1.stringWidth("Game Over")) / 2,
                screen_height / 2
        );

        g.setFont(new Font("Ink Free", Font.BOLD, 40));

        FontMetrics metrics2 = getFontMetrics(g.getFont());

        g.drawString(
                "Score: " + applesEaten,
                (screen_width - metrics2.stringWidth("Score: " + applesEaten)) / 2,
                g.getFont().getSize()
        );
    }

    /**
     * Main game loop update (called by Swing Timer).
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }

    /**
     * Handles keyboard input for snake direction control.
     */
    public class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            switch (e.getKeyCode()) {

                case KeyEvent.VK_LEFT:
                    if (direction != 'R') direction = 'L';
                    break;

                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') direction = 'R';
                    break;

                case KeyEvent.VK_UP:
                    if (direction != 'D') direction = 'U';
                    break;

                case KeyEvent.VK_DOWN:
                    if (direction != 'U') direction = 'D';
                    break;
            }
        }
    }
}