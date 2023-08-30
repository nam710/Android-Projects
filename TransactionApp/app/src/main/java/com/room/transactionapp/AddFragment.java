package com.room.transactionapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.room.transactionapp.databinding.FragmentAddBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddFragment extends Fragment {
    FragmentAddBinding binding;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddFragment newInstance(String param1, String param2) {
        AddFragment fragment = new AddFragment();
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
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = this.getArguments();

        if (bundle != null) {
            int i = bundle.getInt("id");
            String type = bundle.getString("type","add");
            String title = bundle.getString("title");
            String amount = bundle.getString("amount");
            int requestCode = bundle.getInt("requestcode");
            TxViewModel txViewModel = new ViewModelProvider(getActivity(), (ViewModelProvider.Factory)
                    ViewModelProvider.AndroidViewModelFactory
                    .getInstance(getActivity().getApplication())).get(TxViewModel.class);
            if(requestCode==2 && type.equals("update")){
                binding.edtTxTitle.setText(title);
                binding.edtTxAmt.setText(amount);

                binding.fab.setText("Update");
                binding.fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TxEntity txEntity = new TxEntity(binding.edtTxTitle.getText().toString(),binding.edtTxAmt.getText().toString());
                        txEntity.setId(i);
                        txViewModel.edit(txEntity);
                        Toast.makeText(getActivity(),"Transaction updated",Toast.LENGTH_SHORT).show();
                    }
                });
            }
            if(requestCode==1 && type.equals("add")){

                binding.fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TxEntity txEntity = new TxEntity(binding.edtTxTitle.getText().toString(),binding.edtTxAmt.getText().toString());
                        txViewModel.insert(txEntity);
                        Toast.makeText(getActivity(),"Transaction Added",Toast.LENGTH_SHORT).show();

                    }
                });
            }
        }

    }
}