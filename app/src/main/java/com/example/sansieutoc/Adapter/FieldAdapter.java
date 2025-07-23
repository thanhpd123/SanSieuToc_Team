package com.example.sansieutoc.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sansieutoc.CrudFieldActivity;
import com.example.sansieutoc.DataHelper.AppDatabase;
import com.example.sansieutoc.Entity.Field;
import com.example.sansieutoc.R;
import com.example.sansieutoc.UpdateFieldActivity;

import java.util.List;
import java.util.concurrent.Executors;

public class FieldAdapter extends RecyclerView.Adapter<FieldViewHolder> {

    private Context context;
    private List<Field> fields;
    private AppDatabase db;

    public FieldAdapter(Context context, List<Field> fields) {
        this.context = context;
        this.fields = fields;
        db = AppDatabase.getInstance(context);
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
        notifyDataSetChanged();
    }



    @Override
    public FieldViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_field, parent, false);
        return new FieldViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FieldViewHolder holder, int position) {
        Field field = fields.get(position);
        holder.bind(field);



        // Update
        holder.btnUpdate.setOnClickListener(v -> {
            Intent intent = new Intent(context, UpdateFieldActivity.class);
            intent.putExtra("field_id", field.id);
            context.startActivity(intent);
        });

        // Delete
        holder.btnDelete.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Xóa sân")
                    .setMessage("Bạn có chắc chắn muốn xóa sân này?")
                    .setPositiveButton("Xóa", (dialog, which) -> {
                        Executors.newSingleThreadExecutor().execute(() -> {
                            db.fieldDao().delete(field);
                            ((CrudFieldActivity) context).runOnUiThread(() -> {
                                fields.remove(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position, fields.size());
                            });
                        });
                    })
                    .setNegativeButton("Hủy", null)
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return fields == null ? 0 : fields.size();
    }
}
