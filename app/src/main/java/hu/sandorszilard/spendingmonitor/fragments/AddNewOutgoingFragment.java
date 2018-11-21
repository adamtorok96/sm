package hu.sandorszilard.spendingmonitor.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import hu.sandorszilard.spendingmonitor.R;
import hu.sandorszilard.spendingmonitor.adapters.CategoriesAdapter;
import hu.sandorszilard.spendingmonitor.db.models.Outgoing;
import hu.sandorszilard.spendingmonitor.interfaces.OnBalanceChangedListener;

public class AddNewOutgoingFragment extends DialogFragment {

    private OnBalanceChangedListener mOnBalanceChangedListener;
    private Spinner spCategories;
    private EditText etName, etValue;

    public static AddNewOutgoingFragment newInstance(OnBalanceChangedListener onItemAddedListener) {
        AddNewOutgoingFragment fragment = new AddNewOutgoingFragment();
        fragment.setOnBalanceChangedListener(onItemAddedListener);

        return fragment;
    }

    private void setOnBalanceChangedListener(OnBalanceChangedListener onItemAddedListener) {
        mOnBalanceChangedListener = onItemAddedListener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_new_outgoing_fragment, container, false);

        spCategories = view.findViewById(R.id.spCategories);
        etName = view.findViewById(R.id.etName);
        etValue = view.findViewById(R.id.etValue);

        spCategories.setAdapter(new CategoriesAdapter(getContext()));

        view.findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveOutgoing();
            }
        });

        return view;
    }

    private void saveOutgoing() {
        if( etName.getText().toString().isEmpty() ) {
            Toast.makeText(getContext(), R.string.error_empty_name, Toast.LENGTH_SHORT).show();

            return;
        }

        if( etValue.getText().toString().isEmpty() ) {
            Toast.makeText(getContext(), R.string.error_empty_value, Toast.LENGTH_SHORT).show();

            return;
        }

        int category = spCategories.getSelectedItemPosition();
        String name = etName.getText().toString();
        long value = Long.valueOf(etValue.getText().toString());

        Outgoing outgoing = new Outgoing(category, name, value);
        outgoing.save();

        if( mOnBalanceChangedListener != null )
            mOnBalanceChangedListener.onAdded();

        dismiss();
    }
}
