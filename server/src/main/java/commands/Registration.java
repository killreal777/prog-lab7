package commands;

import data.dao.Dao;

public class Registration extends ArguedServerCommand<String> {
    public Registration(Dao dao) {
        super(dao);
    }

    @Override
    public void execute() {
        if (isUserExists()) {
            setBadResult("Акаунт с таким именем уже существует");
        } else {
            dao.add(userName, commandArgument);
            setGoodResult(String.format("Вы успешно зарегестрировались и вошли как %s", userName));
        }
    }
}
