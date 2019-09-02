package com.example.lab3_1;

import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;


public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {

    //Dữ liệu hiển thị là danh sách sinh viên
    public List mList;

    //Lưu Context để dễ dàng truy cập
    private Context mContext;

    //  Bắt đầu với item đầu tiên được chọn
    private  int focusedItem = 0;

    private  static AdapterView.OnClickListener onClickListener;

    public EmployeeAdapter(List mList, Context mContext){
        this.mList = mList;
        this.mContext = mContext;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvHoTen, tvChucVu, tvPhongBan, tvMoTa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHoTen =itemView.findViewById(R.id.tvName);
            tvChucVu = itemView.findViewById(R.id.tvPosition);
            tvPhongBan = itemView.findViewById(R.id.tvDepartment);
            tvMoTa = itemView.findViewById(R.id.tvDescribe);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Intent intent = new Intent(mContext, thong_tin_nhan_vien.class);
                    employee obj = (employee) mList.get(getLayoutPosition());
                    intent.putExtra("chi tiết nhân viên", (Serializable) obj);
                    mContext.startActivity(intent);
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Nạp layout cho View biểu diễn phần tử
        //Tùy thuộc viewType của phần tử
        View view = null;
        view = inflater.inflate(R.layout.item_employee,parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        employee obj = (employee) mList.get(position);

        holder.tvHoTen.setText(obj.getName());
        holder.tvChucVu.setText(obj.getChucVu());
        holder.tvPhongBan.setText(obj.getPhongBan());
        holder.tvMoTa.setText(obj.getMoTa());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        // xử lý di chuyển lên và xuống và di chuyển item
        recyclerView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int KeyCode, KeyEvent event) {
                RecyclerView.LayoutManager lm = recyclerView.getLayoutManager();


             // Trả về false nếu cuộn đến giới hạn và cho phép focus di chuyển khỏi danh sách
                if (event.getAction() == KeyEvent.ACTION_DOWN){
                    if (KeyCode == KeyEvent.KEYCODE_DPAD_DOWN){
                        return  tryMoveSelection(lm, 1);
                    }else  if (KeyCode == KeyEvent.KEYCODE_DPAD_UP){
                        return  tryMoveSelection(lm, -1);
                    }
                }
                return  false;

            }
        });
    }
    private  boolean tryMoveSelection(RecyclerView.LayoutManager lm, int direction){
        int tryFocusItem = focusedItem + direction;

        // Nếu vẫn trong giới hạn hợp lệ, di chuyển vùng chọn, thông báo để vẽ lại và cuộn
        if (tryFocusItem >= 0 && tryFocusItem < getItemCount()){
            notifyItemChanged(focusedItem);
            focusedItem = tryFocusItem;
            notifyItemChanged(focusedItem);
            lm.scrollToPosition(focusedItem);
            return true;
        }
        return  false;
    }
}
