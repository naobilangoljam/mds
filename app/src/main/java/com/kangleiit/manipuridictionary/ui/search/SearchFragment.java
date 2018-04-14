package com.kangleiit.manipuridictionary.ui.search;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kangleiit.manipuridictionary.R;

import java.util.ArrayList;

import static com.kangleiit.manipuridictionary.ui.search.RecyclerViewType.LINEAR_VERTICAL;

public class SearchFragment extends Fragment {
    private static final String KEYWORD = "keyword";
    protected static final String RECYCLER_VIEW_TYPE = "recycler_view_type";
    private RecyclerViewType recyclerViewType;
    private RecyclerView recyclerView;
    android.support.v4.view.ViewPager ViewPager;
    ArrayList<Integer> arrayImages = new ArrayList<>();
    // TODO: Rename and change types of parameters
    private String mKeyword;

    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(KEYWORD, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mKeyword = getArguments().getString(KEYWORD);
            Toast.makeText(getActivity(), "" + mKeyword, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        ViewPager = (android.support.v4.view.ViewPager) v.findViewById(R.id.ViewPager);
        TabLayout tabLayout = (TabLayout) v.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(ViewPager, true);
        arrayImages.add(R.drawable.lakec);
        arrayImages.add(R.drawable.lakeb);
        arrayImages.add(R.drawable.lakec);
        arrayImages.add(R.drawable.cute);
        //recyclerViewType = (RecyclerViewType) getIntent().getSerializableExtra(RECYCLER_VIEW_TYPE);

        PagerAdapter pagerAdapter = new PhotosAdapter(getActivity(), arrayImages);
        ViewPager.setAdapter(pagerAdapter);
        setUpRecyclerView(v);
        populateRecyclerView();
        return v;
    }


    //setup recycler view
    private void setUpRecyclerView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.sectioned_recycler_view);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    //populate recycler view
    private void populateRecyclerView() {
        ArrayList<SectionModel> sectionModelArrayList = new ArrayList<>();
        //for loop for sections
        for (int i = 1; i <= 5; i++) {
            ArrayList<String> itemArrayList = new ArrayList<>();
            //for loop for items
            for (int j = 1; j <= 10; j++) {
                itemArrayList.add("Item " + j);
            }

            //add the section and items to array list
            sectionModelArrayList.add(new SectionModel("Section " + i, itemArrayList));
        }

        SectionRecyclerViewAdapter adapter = new SectionRecyclerViewAdapter(getActivity(), LINEAR_VERTICAL, sectionModelArrayList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
