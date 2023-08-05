import javax.swing.SwingUtilities;

import Presentation.App.ManagementApp;

public class MainView {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ManagementApp().setVisible(true);
            }
        });
    }
}
