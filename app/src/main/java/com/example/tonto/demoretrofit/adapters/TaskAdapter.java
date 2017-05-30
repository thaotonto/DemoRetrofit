package com.example.tonto.demoretrofit.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tonto.demoretrofit.R;
import com.example.tonto.demoretrofit.networks.getalltasks.GetAllTasksResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tonto on 5/29/2017.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder> {

    private List<GetAllTasksResponse> taskList;
    private Context context;

    public TaskAdapter(List<GetAllTasksResponse> taskList, Context context) {
        this.taskList = taskList;
        this.context = context;
    }

    @Override
    public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_task, parent, false);
        return new TaskHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskHolder holder, int position) {
        holder.setData(taskList.get(position));
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class TaskHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.tv_id)
        TextView tvId;
        @BindView(R.id.tv_payment)
        TextView tvPayment;
        @BindView(R.id.iv_check)
        CheckBox ivCheck;
        @BindView(R.id.tv_local_id)
        TextView tvLocalId;
        @BindView(R.id.item_tasks_layout)
        LinearLayout linearLayout;

        public TaskHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(GetAllTasksResponse task) {
            tvName.setText(task.getName());
            tvDate.setText(task.getDue_date());
            tvPayment.setText(task.getPayment_per_hour() + "");
            tvId.setText(task.getId());
            if (task.isDone()) {
                ivCheck.setChecked(true);
            } else ivCheck.setChecked(false);
            if (task.getColor() != (null))
            linearLayout.setBackgroundColor(Color.parseColor(task.getColor()));
            tvLocalId.setText(task.getLocal_id());
        }
    }
}