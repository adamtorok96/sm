package hu.sandorszilard.spendingmonitor.db.models;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.orm.Database;
import com.orm.SugarApp;
import com.orm.SugarRecord;

import hu.sandorszilard.spendingmonitor.App;

public class Income extends SugarRecord<Income> {
    String name;

    long value;

    public Income() {
        this.value = 0;
    }

    public Income(String name, long value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public long getValue() {
        return value;
    }

    public static long getSum() {
        Database db = ((App)SugarApp.getSugarContext()).getDB();

        SQLiteDatabase sqLiteDatabase = db.getDB();
        SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement("SELECT SUM(value) FROM income");

        long sum = 0;

        try {
            sum = sqLiteStatement.simpleQueryForLong();
        } catch (Exception var16) {
            var16.printStackTrace();
        } finally {
            sqLiteStatement.close();
        }

        return sum;
    }
}
