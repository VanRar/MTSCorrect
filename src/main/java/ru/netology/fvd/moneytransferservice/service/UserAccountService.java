package ru.netology.fvd.moneytransferservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.netology.fvd.moneytransferservice.model.Operation;
import ru.netology.fvd.moneytransferservice.model.Response;
import ru.netology.fvd.moneytransferservice.model.TransferMoneyRequest;
import ru.netology.fvd.moneytransferservice.model.User;
import ru.netology.fvd.moneytransferservice.repository.UsersRepositories;
import ru.netology.fvd.moneytransferservice.util.LoggerUtil;
import ru.netology.fvd.moneytransferservice.util.ResponseUtil;


@Slf4j
@Service
public class UserAccountService {
    private final UsersRepositories usersRepositories;

    public UserAccountService(UsersRepositories usersRepositories) {
        this.usersRepositories = usersRepositories;
    }

    public Response validateTransferMoneyRequest(TransferMoneyRequest transferMoneyRequest) {
        User userFrom = getUser(transferMoneyRequest.getCardFromNumber());
        User userTo = getUser(transferMoneyRequest.getCardToNumber());

        if (userFrom == null || userTo == null) {
            return ResponseUtil.getResponse(HttpStatus.BAD_REQUEST, "Error input data 1 " + userFrom + "   из запроса getCardFromNumber" + userTo + "  " + transferMoneyRequest.getCardFromNumber(), 0);
        }

        if (!(transferMoneyRequest.getCardFromNumber().equals(userFrom.getCardNumber()) &
                transferMoneyRequest.getCardFromValidTill().equals(userFrom.getCardValid()) &
                transferMoneyRequest.getCardFromCVV().equals(userFrom.getCardCVV())) &
                transferMoneyRequest.getCardToNumber().equals(userTo.getCardNumber())) {
            return ResponseUtil.getResponse(HttpStatus.BAD_REQUEST, "Error input data2", 0);
        }

        if (transferMoneyRequest.getAmount().getValue() > userFrom.getAmount().getValue())
            return ResponseUtil.getResponse(HttpStatus.BAD_REQUEST, "Insufficient funds", 0);

        return ResponseUtil.getResponse(HttpStatus.OK, "Success validation", 0);
    }

    public Response transferMoney(Operation operation) {
        String cardNumberFrom = operation.getTransferMoneyRequest().getCardFromNumber();
        String cardNumberTo = operation.getTransferMoneyRequest().getCardToNumber();
        int amount = operation.getTransferMoneyRequest().getAmount().getValue();

        User userFrom = getUser(cardNumberFrom);
        User userTo = getUser(cardNumberTo);

        synchronized (userFrom) {
            synchronized (userTo) {
                if (userFrom.getAmount().getValue() < amount) {
                    return ResponseUtil.getResponse(HttpStatus.BAD_REQUEST, "Insufficient funds", 0);
                }

                userFrom.setAmount(userFrom.getAmount().getValue() - amount);
                setUser(userFrom);

                userTo.setAmount(userTo.getAmount().getValue() + amount);
                setUser(userTo);

                log.info(LoggerUtil.transferMoneyLog(operation));
            }
        }
        return ResponseUtil.getResponse(HttpStatus.OK, "Success transfer", 0);
    }

    public User getUser(String cardNumber) {
        return usersRepositories.getUser(cardNumber);
    }

    public void setUser(User user) {
        usersRepositories.setUser(user);
    }
}
