package Presentation.CommandProcessor.Cmd_SS;

import javax.swing.JOptionPane;

import Domain.Model.SanhSu;

public class VAT_SS_Cmd extends CommandSS{

    public VAT_SS_Cmd(SanhSu sanhSuRemote){
        super(sanhSuRemote);
    }

    @Override
    public void execute() {

        double vatSS = sanhSuRemote.VAT();

        JOptionPane.showMessageDialog(null, "VAT for " + sanhSuRemote.getName() + " is: " + vatSS);
    }
    
}
