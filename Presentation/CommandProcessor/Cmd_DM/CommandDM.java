package Presentation.CommandProcessor.Cmd_DM;

import Domain.Model.DienMay;

public abstract class CommandDM {

    protected DienMay dienMayRemote;

    public void setDienMayRemote(DienMay dienMayRemote) {
        this.dienMayRemote = dienMayRemote;
    }

    public abstract void execute();

    public CommandDM(DienMay dienMayRemote) {
        this.dienMayRemote = dienMayRemote;
    }
}
