package ru.netology.fvd.moneytransferservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private String cardNumber;
    private String cardValid;
    private String cardCVV;
    private Amount amount;

    public void setAmount(int amount) {
        this.getAmount().setValue(amount);
    }
}
