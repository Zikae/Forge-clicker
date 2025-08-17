import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AutoClickerGUI {
    private static boolean enabled = false;
    private static boolean breakBlocks = false;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("AutoClicker Demo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 250);
            frame.setLayout(new FlowLayout());

            // Main button
            JButton button = new JButton("AutoClicker");
            JPopupMenu menu = new JPopupMenu();

            // Dropdown item
            JCheckBoxMenuItem breakBlocksItem = new JCheckBoxMenuItem("Break Blocks");
            breakBlocksItem.addActionListener(e -> {
                breakBlocks = breakBlocksItem.isSelected();
                System.out.println("Break blocks: " + breakBlocks);
            });
            menu.add(breakBlocksItem);

            // Slider for CPS
            JSlider sliderMin = new JSlider(0, 20, 5);
            JSlider sliderMax = new JSlider(0, 20, 10);
            sliderMin.setMajorTickSpacing(5);
            sliderMax.setMajorTickSpacing(5);
            sliderMin.setPaintTicks(true);
            sliderMax.setPaintTicks(true);
            sliderMin.setPaintLabels(true);
            sliderMax.setPaintLabels(true);

            JLabel rangeLabel = new JLabel("CPS Range: 5 - 10");

            sliderMin.addChangeListener(e -> {
                rangeLabel.setText("CPS Range: " + sliderMin.getValue() + " - " + sliderMax.getValue());
            });
            sliderMax.addChangeListener(e -> {
                rangeLabel.setText("CPS Range: " + sliderMin.getValue() + " - " + sliderMax.getValue());
            });

            // Left click = toggle on/off
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        enabled = !enabled;
                        button.setText(enabled ? "AutoClicker (ON)" : "AutoClicker (OFF)");
                        System.out.println("AutoClicker enabled: " + enabled);
                    } else if (SwingUtilities.isRightMouseButton(e)) {
                        menu.show(button, e.getX(), e.getY());
                    }
                }
            });

            // Add everything to frame
            frame.add(button);
            frame.add(new JLabel("Min CPS"));
            frame.add(sliderMin);
            frame.add(new JLabel("Max CPS"));
            frame.add(sliderMax);
            frame.add(rangeLabel);

            frame.setVisible(true);
        });
    }
}
