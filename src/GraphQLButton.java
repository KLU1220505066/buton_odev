import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphQLButton extends JButton {
    private static GraphQLButton lastClickedButton;
    private String graphqlSchemaAddress;
    private Color activeColor;
    private Color passiveColor;
    private Icon activeIcon;
    private Icon passiveIcon;
    private int row;
    private int column;

    public GraphQLButton(int row, int column, String graphqlSchemaAddress, Color activeColor, Color passiveColor, Icon activeIcon, Icon passiveIcon) {
        this.row = row;
        this.column = column;
        this.graphqlSchemaAddress = graphqlSchemaAddress;
        this.activeColor = activeColor;
        this.passiveColor = passiveColor;
        this.activeIcon = activeIcon;
        this.passiveIcon = passiveIcon;

        this.setBackground(passiveColor);
        this.setIcon(passiveIcon);

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleClick((GraphQLButton) e.getSource());
            }
        });
    }

    public String getGraphqlSchemaAddress() {
        return graphqlSchemaAddress;
    }

    public void setGraphqlSchemaAddress(String graphqlSchemaAddress) {
        this.graphqlSchemaAddress = graphqlSchemaAddress;
    }

    public Color getActiveColor() {
        return activeColor;
    }

    public void setActiveColor(Color activeColor) {
        this.activeColor = activeColor;
    }

    public Color getPassiveColor() {
        return passiveColor;
    }

    public void setPassiveColor(Color passiveColor) {
        this.passiveColor = passiveColor;
    }

    public Icon getActiveIcon() {
        return activeIcon;
    }

    public void setActiveIcon(Icon activeIcon) {
        this.activeIcon = activeIcon;
    }

    public Icon getPassiveIcon() {
        return passiveIcon;
    }

    public void setPassiveIcon(Icon passiveIcon) {
        this.passiveIcon = passiveIcon;
    }

    private void handleClick(GraphQLButton button) {
        if (lastClickedButton == null) {
            // İlk butona tıklandığında sadece onu aktif yap.
            button.setBackground(button.activeColor);
            button.setIcon(button.activeIcon);
            lastClickedButton = button;
        } else {
            // İlk tıklanan buton dışındaki butonları pasif hale getir.
            if (lastClickedButton != button) {
                for (Component component : getRootPane().getContentPane().getComponents()) {
                    if (component instanceof GraphQLButton) {
                        GraphQLButton otherButton = (GraphQLButton) component;
                        if (otherButton != button) {
                            otherButton.setBackground(otherButton.passiveColor);
                            otherButton.setIcon(otherButton.passiveIcon);
                        }
                    }
                }
            }

            // Tıklanan butonun durumunu değiştir (aktiften pasife veya pasiften aktife).
            if (button.getBackground() == button.passiveColor) {
                button.setBackground(button.activeColor);
                button.setIcon(button.activeIcon);
            } else {
                button.setBackground(button.passiveColor);
                button.setIcon(button.passiveIcon);
            }

            // Tıklanan butonu son tıklanan olarak kaydet.
            lastClickedButton = button;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("GraphQL Button Panel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel(new GridLayout(4, 4, 5, 5));

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    GraphQLButton button = new GraphQLButton(
                            i, j,
                            "https://example.com/graphql/" + ((i * 4) + j + 1),
                            Color.RED, Color.LIGHT_GRAY,
                            new ImageIcon("active_icon.png"), new ImageIcon("passive_icon.png")
                    );
                    panel.add(button);
                }
            }

            frame.getContentPane().add(panel);
            frame.setSize(400, 400);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}