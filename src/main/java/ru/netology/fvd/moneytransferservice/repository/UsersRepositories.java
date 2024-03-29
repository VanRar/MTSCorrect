package ru.netology.fvd.moneytransferservice.repository;

import org.springframework.stereotype.Repository;
import ru.netology.fvd.moneytransferservice.model.Amount;
import ru.netology.fvd.moneytransferservice.model.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Repository
public class UsersRepositories {
    private Map<String, User> usersRepositories;

    public UsersRepositories() {

        this.usersRepositories = new ConcurrentHashMap<String, User>();
        User user1 = new User("1111111111111111", "11/23", "111", new Amount(100_000, "RUR"));
        User user2 = new User("1111111111112222", "12/24", "111", new Amount(200_000, "RUR"));
        User user3 = new User("1111111122222222", "12/25", "111", new Amount(300_000, "RUR"));
        User user4 = new User("1111222211112222", "12/26", "111", new Amount(400_000, "RUR"));

        usersRepositories.put(user1.getCardNumber(), user1);
        usersRepositories.put(user2.getCardNumber(), user2);
        usersRepositories.put(user3.getCardNumber(), user3);
        usersRepositories.put(user4.getCardNumber(), user4);
    }

    public User getUser(String cardNumber) {
        return usersRepositories.get(cardNumber);
    }

    public void setUser(User user) {
        usersRepositories.put(user.getCardNumber(), user);
    }

    public Map<String, User> getUsers() {
        return usersRepositories;
    }
}
