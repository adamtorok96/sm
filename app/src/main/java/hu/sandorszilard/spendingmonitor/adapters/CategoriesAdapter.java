package hu.sandorszilard.spendingmonitor.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import hu.sandorszilard.spendingmonitor.R;

public class CategoriesAdapter extends ArrayAdapter<String> {

    public CategoriesAdapter(Context context) {
        super(
                context,
                android.R.layout.simple_spinner_item,
                context.getResources().getStringArray(R.array.categories)
        );

        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }
}