package game_ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This screen is just for fun.
 * It plays some intro-text on the game's startup.
 * Can be removed and won't affect the architecture.
 */
public class SplashScreen extends JPanel {

    private final String[] sentences = {
            "The remnants of the Ancient War resurfaced",
            "The beasts of the past returned from their slumber",
            "The War has not ended...",
            "And now, they are given the new name...",
            "Ancient Brawlers"
    };

    private int currentSentenceIndex = 0;
    private final Timer textTimer;
    private final Timer backgroundTimer;

    private Color currentBackgroundColor = Color.BLACK;
    private Color targetBackgroundColor = Color.WHITE;

    public SplashScreen() {
        setBackground(Color.BLACK);
        setLayout(new BorderLayout());

        textTimer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentSentenceIndex < sentences.length) {
                    repaint();
                    currentSentenceIndex++;
                } else {
                    textTimer.stop();
                    backgroundTimer.start(); // Start background fading after texts are displayed
                }
            }
        });

        textTimer.start();

        backgroundTimer = new Timer(50, new ActionListener() { // Adjust the fading speed
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentBackgroundColor.equals(targetBackgroundColor)) {
                    backgroundTimer.stop();
                } else {
                    currentBackgroundColor = blendColors(currentBackgroundColor, targetBackgroundColor, 0.01f); // Increase blending ratio
                    repaint();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(currentBackgroundColor);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        g2d.setColor(Color.WHITE);

        Font regularFont = new Font("Trajan Pro", Font.PLAIN, 20);
        Font lastSentenceFont = new Font("Herculanum", Font.BOLD, 50);

        int totalHeight = 0;
        for (int i = 0; i < sentences.length; i++) {
            Font font = i == sentences.length - 1 ? lastSentenceFont : regularFont;
            totalHeight += font.getSize() + 30; // Spacing between sentences
        }

        int startY = getHeight() / 2 - totalHeight / 2; // Center vertically

        for (int i = 0; i < currentSentenceIndex; i++) {
            String sentence = sentences[i];
            Font font = i == sentences.length - 1 ? lastSentenceFont : regularFont;
            g2d.setFont(font);

            int stringWidth = g2d.getFontMetrics().stringWidth(sentence);
            int startX = getWidth() / 2 - stringWidth / 2;
            int lineY = startY;

            g2d.drawString(sentence, startX, lineY);

            startY += font.getSize() + 30; // Spacing between sentences
        }
    }

    private Color blendColors(Color color1, Color color2, float ratio) {
        int red = (int) (color1.getRed() * (1 - ratio) + color2.getRed() * ratio);
        int green = (int) (color1.getGreen() * (1 - ratio) + color2.getGreen() * ratio);
        int blue = (int) (color1.getBlue() * (1 - ratio) + color2.getBlue() * ratio);
        return new Color(red, green, blue);
    }
}
