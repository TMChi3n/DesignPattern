import javax.swing.SwingUtilities;

import Frame.view;
import SanhSu.viewSS;
import ThucPham.viewTP;
import DienMay.viewDM;

public class MainView {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new view().setVisible(true);
            }
        });
    }
}
