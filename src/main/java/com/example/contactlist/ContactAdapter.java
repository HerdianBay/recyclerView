package com.example.contactlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private List<ContactModel> contactList;
    private Context context;
    private static ClickListener clickListener;

    public ContactAdapter(List<ContactModel> contactList, Context context) {
        this.contactList = contactList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ContactModel contactModel = contactList.get(position);
        holder.contact_name.setText(contactModel.getNama());
        holder.contact_number.setText(contactModel.getNomor());
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        RelativeLayout contact_layout;
        ImageView image_contact;
        TextView contact_name, contact_number;
        Button button_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            contact_layout = itemView.findViewById(R.id.contactLayout);
            image_contact = itemView.findViewById(R.id.imageContact);
            contact_name = itemView.findViewById(R.id.contactName);
            contact_number = itemView.findViewById(R.id.contactNumber);
            button_delete = itemView.findViewById(R.id.delete);

            button_delete.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAdapterPosition(),  itemView);
        }
    }
    public interface ClickListener {
        void onItemClick(int position, View v);
    }
    public void setOnItemClickListener(ContactAdapter.ClickListener clickListener){
        ContactAdapter.clickListener = clickListener;
    }
}
