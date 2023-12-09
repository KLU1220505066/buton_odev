# Proje Adı

Bu proje [GraphQLButton](URL) sınıfını içerir.

## İçindekiler

- [Proje Hakkında](#proje-hakkında)
- [Nasıl Kullanılır](#nasıl-kullanılır)
- [Örnek Kullanım](#örnek-kullanım)


## Proje Hakkında

`GraphQLButton` sınıfı, Swing kütüphanesi kullanılarak oluşturulmuş bir Java sınıfıdır. Her bir buton, bir GraphQL şema adresini temsil eder ve kullanıcı bu butonlara tıklayarak seçim yapabilir.

## Nasıl Kullanılır

`GraphQLButton` sınıfını kendi projenize eklemek için aşağıdaki adımları takip edebilirsiniz:

1. `GraphQLButton.java` dosyasını projenizin kaynak dizinine ekleyin.
2. Projenizde Swing kütüphanesini kullanabilmek için gerekli bağımlılıkları kontrol edin.

## Örnek Kullanım

```java
import javax.swing.*;
import java.awt.*;

public class Main {
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
