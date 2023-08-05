package Presentation.CommandProcessor.Cmd_DM;

import javax.swing.JOptionPane;

import Domain.Model.DienMay;

public class VAT_DM_Cmd extends CommandDM{
    
    public VAT_DM_Cmd(DienMay dienMayRemote){
        super(dienMayRemote);
    }

    @Override
    public void execute() {
        double vatDM = dienMayRemote.VAT();
        JOptionPane.showMessageDialog(null, "VAT for " + dienMayRemote.getName() + " is: " + vatDM);
    }
}
