package Presentation.CommandProcessor.Cmd_SS;

import Domain.Model.SanhSu;

public abstract class CommandSS {

    protected SanhSu sanhSuRemote;

    public void setSanhSuRemote(SanhSu sanhSuRemote) {
        this.sanhSuRemote = sanhSuRemote;
    }

    public abstract void execute();

    public CommandSS(SanhSu sanhSuRemote) {
        this.sanhSuRemote = sanhSuRemote;
    }
}
