import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedImage image = ImageIO.read(new File("ursinho_pooh.png"));

        ImagePanel imagePanel = new ImagePanel(image);

        JFrame frame = new JFrame("Flood Fill");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setSize(image.getWidth(), image.getHeight());
        frame.add(imagePanel);
        frame.setVisible(true);

        Thread.sleep(300);

        FloodFill ff = new FloodFill(image, imagePanel, 350, 400);
//        ff.fillWithQueue();
        ff.fillWithStack();
    }
}
