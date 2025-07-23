package com.example.sansieutoc.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sansieutoc.AdminManagementActivity;
import com.example.sansieutoc.DataHelper.AppDatabase;
import com.example.sansieutoc.Entity.User;
import com.example.sansieutoc.R;

import java.util.List;
import java.util.concurrent.Executors;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<User> users;
    private Context context;

    public UserAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User user = users.get(position);
        holder.tvName.setText("Tên: " + user.name);
        holder.tvPhone.setText("Phone: " + user.phone);
        holder.tvRole.setText("Role: " + user.role);
        holder.tvStatus.setText("Status: " + (user.isBanned ? "Banned" : "Active"));
        holder.tvStatus.setTextColor(user.isBanned ? 0xFFFF0000 : 0xFF00FF00);

        holder.btnBanUnban.setText(user.isBanned ? "Unban" : "Ban");
        holder.btnBanUnban.setBackgroundColor(user.isBanned ? 0xFF00FF00 : 0xFFFF0000);

        holder.btnBanUnban.setOnClickListener(v -> {
            boolean newStatus = !user.isBanned;
            Executors.newSingleThreadExecutor().execute(() -> {
                AppDatabase db = AppDatabase.getInstance(context);
                db.userDao().updateBanStatus(user.id, newStatus);
                user.isBanned = newStatus;
                ((AdminManagementActivity) context).runOnUiThread(() -> {  // Gọi runOnUiThread từ Activity
                    notifyItemChanged(position);
                    Toast.makeText(context, (newStatus ? "Đã ban" : "Đã unban") + " người dùng", Toast.LENGTH_SHORT).show();
                });
            });
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPhone, tvRole, tvStatus;
        Button btnBanUnban;

        public UserViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvPhone = itemView.findViewById(R.id.tv_item_phone);
            tvRole = itemView.findViewById(R.id.tv_item_role);
            tvStatus = itemView.findViewById(R.id.tv_item_status);
            btnBanUnban = itemView.findViewById(R.id.btn_ban_unban);
        }
    }
}
