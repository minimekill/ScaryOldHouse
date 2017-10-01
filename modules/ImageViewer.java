package old80house.modules;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageViewer {

    public void showImage(String imageName) {

        JFrame frame = new JFrame();
        ImageIcon icon = new ImageIcon(imageName);
        JLabel label = new JLabel(icon);
        frame.add(label);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

}
