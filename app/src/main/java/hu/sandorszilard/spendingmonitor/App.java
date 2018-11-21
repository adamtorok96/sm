package hu.sandorszilard.spendingmonitor;

import com.orm.Database;
import com.orm.SugarApp;

public class App extends SugarApp {
    public Database getDB(){
        return getDatabase();
    }
}
