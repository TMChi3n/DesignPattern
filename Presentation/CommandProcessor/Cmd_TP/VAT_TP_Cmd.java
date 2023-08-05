package Presentation.CommandProcessor.Cmd_TP;

import javax.swing.JOptionPane;

import Domain.Model.ThucPham;

public class VAT_TP_Cmd extends CommandTP{

    public VAT_TP_Cmd(ThucPham thucPhamRemote) {
        super(thucPhamRemote);
    }

    @Override
    public void execute() {
        double vatTP = thucPhamRemote.VAT();
        JOptionPane.showMessageDialog(null, "VAT for " + thucPhamRemote.getName() + " is: " + vatTP);
    }
    
}
