package use_cases.win_loss_use_case;

public class WinLossResponseModel {

    private String winnerName;

    public WinLossResponseModel(String winnerName) {
        this.winnerName = winnerName;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }
}
