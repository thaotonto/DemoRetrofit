package com.example.tonto.demoretrofit.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tonto.demoretrofit.R;
import com.example.tonto.demoretrofit.adapters.TaskAdapter;
import com.example.tonto.demoretrofit.networks.RetrofitFactory;
import com.example.tonto.demoretrofit.networks.getalltasks.GetAllTasksResponse;
import com.example.tonto.demoretrofit.networks.getalltasks.GetAllTasksService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllTasksFragment extends Fragment {
    private String accessToken;
    List<GetAllTasksResponse> responseList;
    @BindView(R.id.rv_task_list)
    RecyclerView recyclerView;

    public AllTasksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_tasks, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        getAllTasks();
//        setupUI();
    }

    private void setupUI() {
        TaskAdapter taskAdapter = new TaskAdapter(responseList, getContext());
        taskAdapter.notifyDataSetChanged();

        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setAdapter(taskAdapter);
        recyclerView.setLayoutManager(manager);
    }

    private void getAllTasks() {
        responseList = new ArrayList<>();
        System.out.println(accessToken);
        RetrofitFactory.getInstance().createService(GetAllTasksService.class)
                .getAllTasks(accessToken)
                .enqueue(new Callback<List<GetAllTasksResponse>>() {
                    @Override
                    public void onResponse(Call<List<GetAllTasksResponse>> call, Response<List<GetAllTasksResponse>> response) {
                        if (response.code() == 200) {
                            responseList = response.body();
                            setupUI();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<GetAllTasksResponse>> call, Throwable t) {
                        Toast.makeText(getActivity(), "No connection", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public AllTasksFragment setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }
}
