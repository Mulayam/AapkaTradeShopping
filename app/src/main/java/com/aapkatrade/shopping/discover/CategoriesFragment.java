package com.aapkatrade.shopping.discover;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.aapkatrade.shopping.R;
import com.aapkatrade.shopping.jsonclass.AndroidVersion;
import com.aapkatrade.shopping.jsonclass.DataAdapter;
import com.aapkatrade.shopping.jsonclass.JSONResponse;
import com.aapkatrade.shopping.jsonclass.RequestInterface;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.ArrayList;
import java.util.Arrays;

import it.carlom.stikkyheader.core.StikkyHeaderBuilder;
import it.carlom.stikkyheader.core.animator.AnimatorBuilder;
import it.carlom.stikkyheader.core.animator.HeaderStikkyAnimator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoriesFragment extends Fragment
{

    private RecyclerView recyclerView_Commom;
    Context context;
    private ArrayList<AndroidVersion> data;
    private DataAdapter adapter;
    private GridLayoutManager layoutManagerCommom;
    private CategoryAdapter adapterCommom;
    private StikkyHeaderBuilder.RecyclerViewBuilder stikkyHeader;
    private SwipyRefreshLayout mSwipyRefreshLayout;



    public CategoriesFragment()
    {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        context = getActivity();
        initView(view);
        setupRecycleCommom(view);
        return view;
    }

    private void initView(View view)
    {
        mSwipyRefreshLayout = (SwipyRefreshLayout) view.findViewById(R.id.swipyrefreshlayout);
        mSwipyRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction)
            {
                refreshItem();
            }
        });

    }

    private void refreshItem()
    {
        try {
            Thread.sleep(2000);
            mSwipyRefreshLayout.setRefreshing(false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        stikkyHeader = StikkyHeaderBuilder.stickTo(recyclerView_Commom);

        stikkyHeader.setHeader(R.id.header, (ViewGroup) getView())
                .minHeightHeaderDim(R.dimen.min_height_header)
                .animator(new ParallaxStikkyAnimator())
                .build();
    }

    private void setupRecycleCommom(View view)
    {

        recyclerView_Commom = (RecyclerView) view.findViewById(R.id.recycler);
        layoutManagerCommom = new GridLayoutManager(context, 2);
        recyclerView_Commom.setLayoutManager(layoutManagerCommom);

        //loadJSON();
        adapterCommom = new CategoryAdapter(context, null);
        recyclerView_Commom.setAdapter(adapterCommom);
    }


    private void loadJSON()
    {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.learn2crack.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<JSONResponse> call = request.getJSON();
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response)
            {

                JSONResponse jsonResponse = response.body();
                data = new ArrayList<>(Arrays.asList(jsonResponse.getAndroid()));
                adapter = new DataAdapter(data);
                recyclerView_Commom.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t)
            {
                Log.d("Error", t.getMessage());
            }
        });
    }
    private class ParallaxStikkyAnimator extends HeaderStikkyAnimator
    {

        @Override
        public AnimatorBuilder getAnimatorBuilder()
        {
            View mHeader_image = getHeader().findViewById(R.id.relativeLayout);
            return AnimatorBuilder.create().applyVerticalParallax(mHeader_image);
        }



    }
}
