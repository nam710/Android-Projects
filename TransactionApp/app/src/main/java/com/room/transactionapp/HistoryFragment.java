package com.room.transactionapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.room.transactionapp.databinding.ActivityMainBinding;
import com.room.transactionapp.databinding.FragmentHistoryBinding;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoryFragment extends Fragment {
    FragmentHistoryBinding binding;
    ActionBar actionBar;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HistoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HistoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HistoryFragment newInstance(String param1, String param2) {
        HistoryFragment fragment = new HistoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setHasFixedSize(true);
        RvAdapter adapter = new RvAdapter();
        binding.recyclerView.setAdapter(adapter);
        TxViewModel txViewModel = new ViewModelProvider(getActivity(), (ViewModelProvider.Factory)
                ViewModelProvider.AndroidViewModelFactory
                        .getInstance(getActivity().getApplication())).get(TxViewModel.class);
        txViewModel.getAllNotes().observe(getActivity(), new Observer<List<TxEntity>>() {
            @Override
            public void onChanged(List<TxEntity> txEntities) {
                adapter.submitList(txEntities);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if (direction == ItemTouchHelper.RIGHT) {
                    txViewModel.delete(adapter.getTx(viewHolder.getAdapterPosition()));
                    Toast.makeText(getActivity(), "Note deleted", Toast.LENGTH_SHORT).show();
                } else {
                    actionBar.setTitle("Update transaction");
                    AddFragment fragment = new AddFragment();
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content, fragment, "");
                    fragmentTransaction.commit();
                    Bundle bundle = new Bundle();
                    bundle.putString("type", "update");
                    bundle.putString("title", adapter.getTx(viewHolder.getAdapterPosition()).getTitle());
                    bundle.putString("amount", adapter.getTx(viewHolder.getAdapterPosition()).getAmount());
                    bundle.putInt("id", adapter.getTx(viewHolder.getAdapterPosition()).getId());
                    bundle.putInt("requestcode", 2);
                    fragment.setArguments(bundle);
//                    Intent intent=new Intent(MainActivity.this,DataInsertActivity.class);
//                    intent.putExtra("type","update");
//                    intent.putExtra("title",adapter.getNote(viewHolder.getAdapterPosition()).getTitle());
//                    intent.putExtra("desc",adapter.getNote(viewHolder.getAdapterPosition()).getDesc());
//                    intent.putExtra("id",adapter.getNote(viewHolder.getAdapterPosition()).getId());
//                    startActivityForResult(intent,2);
                }

            }
        }).attachToRecyclerView(binding.recyclerView);
    }
}