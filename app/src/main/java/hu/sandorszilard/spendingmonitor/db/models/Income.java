package hu.sandorszilard.spendingmonitor.db.models;

import com.orm.SugarRecord;

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
}
