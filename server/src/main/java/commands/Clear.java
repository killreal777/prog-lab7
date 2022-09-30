package commands;

import data.dao.Dao;

public class Clear extends ServerCommand {
    public Clear(Dao dao) {
        super(dao);
        this.name = "clear";
    }

    @Override
    public void execute() {
        if (!isUserExists()) {
            setBadResult("Пользователь не зарегестрирован");
        } else {
            dao.removeAllByOwnerLogin(userName);
            setGoodResult("Удалены все объекты, владельцем которых вы являлись");
        }
    }
}
