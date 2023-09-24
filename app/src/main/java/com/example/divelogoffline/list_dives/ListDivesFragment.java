package com.example.divelogoffline.list_dives;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.divelogoffline.Dive;
import com.example.divelogoffline.R;
import com.example.divelogoffline.databinding.FragmentListDivesBinding;
import org.reactivestreams.Subscription;
import java.util.List;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class ListDivesFragment extends Fragment {

    public static final String TAG = "List Dives Fragment";
    private FragmentListDivesBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentListDivesBinding.inflate(inflater,container,false);

        setHasOptionsMenu(true);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Activity activity = requireActivity();


        //Initialise viewModel
        ListDivesViewModelFactory factory = new ListDivesViewModelFactory(activity.getApplication());
        ListDivesViewModel viewModel = new ViewModelProvider(this, factory).get(ListDivesViewModel.class);

        //Set up recyclerview
        ListDivesAdapter adapter = new ListDivesAdapter(new ListDivesAdapter.DiveDiff());
        binding.listDivesRecyclerview.setAdapter(adapter);

        //Create subscriber and subscribe to Flowable in viewModel
        listDivesSubscriber subscriber = new listDivesSubscriber(adapter);
        viewModel.getAllDives().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                Dive dive = adapter.getCurrentDive(viewHolder.getAbsoluteAdapterPosition());
                Log.d(TAG, "dive id: " + dive.id);
                viewModel.deleteDive(dive.id);

        }}).attachToRecyclerView(binding.listDivesRecyclerview);

    }


    //Subscriber for Flowable. Recycler adapter passed as argument and updated in onNext
    private static class listDivesSubscriber implements FlowableSubscriber<List<Dive>> {

        private final ListDivesAdapter adapter;

         listDivesSubscriber(ListDivesAdapter adapter) {
            this.adapter = adapter;
        }
        @Override
        public void onSubscribe(Subscription s) {
            Log.d(TAG, "onSubscribe");
            s.request(Long.MAX_VALUE);
        }
        @Override
        public void onNext(List<Dive> dives) {
            Log.d(TAG, dives.size() + "");
            adapter.submitList(dives);
        }
        @Override
        public void onError(Throwable t) {
            Log.d(TAG, "onError");
        }
        @Override
        public void onComplete() {
            Log.d(TAG, "onComplete");
        }
    }


    //Inflate menu to add dive
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
    }

    //Navigate to Add Dive Fragment
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add) {// User chose the "Settings" item, show the app settings UI...
            Navigation.findNavController(requireView()).navigate(R.id.action_listDivesFragment_to_addDiveFragment);
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
