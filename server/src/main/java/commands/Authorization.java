package commands;

import data.dao.Dao;

public class Authorization extends ArguedServerCommand<String> {
    public Authorization(Dao dao) {
        super(dao);
    }

    @Override
    public void execute() {
        if (!isUserExists()) {
            setBadResult("Пользователь не зарегестрирован");
        } else if (!dao.getPasswordByUserName(userName).equals(commandArgument)) {
            setBadResult("Неверный паорль");
        } else {
            setGoodResult(String.format("Вы вошли как %s", userName));
        }
    }
}
