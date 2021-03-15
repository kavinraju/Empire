package com.example.empire.ui.civilization;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.empire.R;
import com.example.empire.databinding.RecyclerItemCivilizationBinding;
import com.example.empire.ui.civilization.model.CivilizationModel;

import java.util.Objects;

public class CivilizationAdapter extends ListAdapter<CivilizationModel, RecyclerView.ViewHolder> {

    private static final int ITEM_VIEW_TYPE_NORMAL = 1;

    public CivilizationAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEM_VIEW_TYPE_NORMAL:
                return createCivilizationViewHolder(parent);
            default:
                throw new ClassCastException("Unknown viewType " + viewType);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        // Check if the holder of CivilizationViewHolder type and update the view holder views.
        if (holder instanceof CivilizationViewHolder) {
            CivilizationViewHolder viewHolder = (CivilizationViewHolder) holder;
            viewHolder.bind(getItem(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        return ITEM_VIEW_TYPE_NORMAL;
    }

    public static final DiffUtil.ItemCallback<CivilizationModel> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<CivilizationModel>() {
                @Override
                public boolean areItemsTheSame(
                        @NonNull CivilizationModel oldCivilizationModel, @NonNull CivilizationModel newCivilizationModel) {
                    // CivilizationModel properties may have changed if reloaded from the DB, but ID is fixed
                    return oldCivilizationModel.getId() == newCivilizationModel.getId();
                }

                @Override
                public boolean areContentsTheSame(
                        @NonNull CivilizationModel oldCivilizationModel, @NonNull CivilizationModel newCivilizationModel) {
                    // NOTE: if you use equals, your object must properly override Object#equals()
                    // Incorrectly returning false here will result in too many animations.
                    return Objects.equals(oldCivilizationModel, newCivilizationModel);
                }
            };

    /**
     * CivilizationViewHolder is the ViewHolder
     */
    public class CivilizationViewHolder extends RecyclerView.ViewHolder {

        public RecyclerItemCivilizationBinding binding;

        public CivilizationViewHolder(RecyclerItemCivilizationBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        /**
         * Helper method to set the RecyclerItemCivilizationBinding object with the required data.
         *
         * @param model
         */
        public void bind(CivilizationModel model) {
            binding.setModel(model);
            binding.executePendingBindings();
        }
    }

    /**
     * Helper method to create the CivilizationViewHolder object.
     *
     * @param parent
     * @return CivilizationViewHolder
     */
    public CivilizationViewHolder createCivilizationViewHolder(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RecyclerItemCivilizationBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.recycler_item_civilization, parent, false
        );
        return new CivilizationViewHolder(binding);
    }
}
