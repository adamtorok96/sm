package hu.sandorszilard.spendingmonitor.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import hu.sandorszilard.spendingmonitor.R;
import hu.sandorszilard.spendingmonitor.db.models.Income;

public class IncomesAdapter extends RecyclerView.Adapter<IncomesAdapter.IncomeViewHolder> {

    private List<Income> mIncomes;

    public IncomesAdapter() {
        queryItems();
    }

    private void queryItems() {
        mIncomes = Income.listAll(Income.class);
    }

    @NonNull
    @Override
    public IncomeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.income, viewGroup, false);

        return new IncomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IncomeViewHolder viewHolder, int pos) {
        final Income income = mIncomes.get(pos);

        viewHolder.tvName.setText(income.getName());
        viewHolder.tvValue.setText(String.format(Locale.getDefault(), "%d Ft", income.getValue()));

        viewHolder.getView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                income.delete();

                reload();

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mIncomes.size();
    }

    public void reload() {
        queryItems();

        notifyDataSetChanged();
    }

    class IncomeViewHolder extends RecyclerView.ViewHolder {
        private View mView;

        private TextView tvName, tvValue;

        IncomeViewHolder(@NonNull View view) {
            super(view);

            mView = view;

            tvName = view.findViewById(R.id.tvName);
            tvValue = view.findViewById(R.id.tvValue);
        }

        public View getView() {
            return mView;
        }
    }
}
