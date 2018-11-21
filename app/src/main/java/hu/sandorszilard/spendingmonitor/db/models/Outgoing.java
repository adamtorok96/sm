package hu.sandorszilard.spendingmonitor.db.models;


import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.orm.Database;
import com.orm.SugarApp;
import com.orm.SugarRecord;

import hu.sandorszilard.spendingmonitor.App;

public class Outgoing extends SugarRecord<Outgoing> {
    int category;

    String name;

    long value;

    public Outgoing() {
        this.value = 0;
    }

    public Outgoing(int category, String name, long value) {
        this.category = category;
        this.name = name;
        this.value = value;
    }

    public int getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public long getValue() {
        return value;
    }

    public static long getSumOfCategory(int category) {
        Database db = ((App)SugarApp.getSugarContext()).getDB();

        SQLiteDatabase sqLiteDatabase = db.getDB();
        SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement("SELECT SUM(value) FROM outgoing WHERE category = " + category);

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
