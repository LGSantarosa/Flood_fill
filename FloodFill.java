import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import Structures.Queue;
import Structures.Stack;

public class FloodFill {
    BufferedImage image;
    ImagePanel panel;
    int newColor = new Color(255, 200, 50).getRGB();
    Point startPoint;

        public FloodFill(BufferedImage image, ImagePanel panel, int x, int y){
            this.image = image;
            this.panel = panel;
            this.startPoint = new Point(x, y);
        }

        public void fillWithQueue() throws InterruptedException {
            int backgroundColor = this.image.getRGB(startPoint.x, startPoint.y);
            Queue<Point> pixelQueue = new Queue<>();
            pixelQueue.enqueue(startPoint);

            int width = image.getWidth();
            int height = image.getHeight();

            if(backgroundColor == newColor) return;

            while(!pixelQueue.isEmpty()) {
                Point pixel = pixelQueue.dequeue();
                int x = pixel.x;
                int y = pixel.y;

                if(x < 0 || x >= width || y < 0 || y >= height ||
                        image.getRGB(x, y) != backgroundColor) {
                    continue;
                }

                image.setRGB(x, y, newColor);
                panel.repaint();
                Thread.sleep(1);

                pixelQueue.enqueue(new Point(x, y - 1));
                pixelQueue.enqueue(new Point(x, y + 1));
                pixelQueue.enqueue(new Point(x - 1, y));
                pixelQueue.enqueue(new Point(x + 1, y));
            }

            try {
                ImageIO.write(image, "png", new File("resultado_queue.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void fillWithStack() throws InterruptedException {
            int backgroundColor = this.image.getRGB(startPoint.x, startPoint.y);
            Stack<Point> pixelStack = new Stack<>();
            pixelStack.push(startPoint);

            int width = image.getWidth();
            int height = image.getHeight();

            if(backgroundColor == newColor) return;

            while(!pixelStack.isEmpty()) {
                Point pixel = pixelStack.pop();
                int x = pixel.x;
                int y = pixel.y;

                if(x < 0 || x >= width || y < 0 || y >= height ||
                        image.getRGB(x, y) != backgroundColor) {
                    continue;
                }

                image.setRGB(x, y, newColor);
                panel.repaint();
                Thread.sleep(1);

                pixelStack.push(new Point(x, y - 1));
                pixelStack.push(new Point(x, y + 1));
                pixelStack.push(new Point(x - 1, y));
                pixelStack.push(new Point(x + 1, y));
            }

            try {
                ImageIO.write(image, "png", new File("resultado_stack.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
