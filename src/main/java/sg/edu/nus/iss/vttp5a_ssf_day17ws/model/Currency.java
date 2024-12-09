package sg.edu.nus.iss.vttp5a_ssf_day17ws.model;

public class Currency {
    private String currencyId;
    private String currencyName;
    private String currencySymbol;
    
    public Currency() {
    }

    public Currency(String currencyid, String currencyName, String currencySymbol) {
        this.currencyId = currencyid;
        this.currencyName = currencyName;
        this.currencySymbol = currencySymbol;
    }
    
    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }


    
}
