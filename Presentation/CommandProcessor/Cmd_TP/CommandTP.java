package Presentation.CommandProcessor.Cmd_TP;

import Domain.Model.ThucPham;

public abstract class CommandTP {

    protected ThucPham thucPhamRemote;

    public void setThucPhamRemote(ThucPham thucPhamRemote) {
        this.thucPhamRemote = thucPhamRemote;
    }

    public abstract void execute();

    public CommandTP(ThucPham thucPhamRemote) {
        this.thucPhamRemote = thucPhamRemote;
    }
}
