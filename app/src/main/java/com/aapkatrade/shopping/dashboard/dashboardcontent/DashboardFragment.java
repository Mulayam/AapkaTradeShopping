package com.aapkatrade.shopping.dashboard.dashboardcontent;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aapkatrade.shopping.R;
import com.aapkatrade.shopping.discover.DiscoverActivity;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import it.carlom.stikkyheader.core.StikkyHeaderBuilder;
import it.carlom.stikkyheader.core.animator.AnimatorBuilder;
import it.carlom.stikkyheader.core.animator.HeaderStikkyAnimator;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment implements View.OnClickListener
{


    private RecyclerView recyclerView_Commom;
    private GridLayoutManager layoutManagerCommom;
    Context context;
    private DashboardAdapter adapterCommom;
    private StikkyHeaderBuilder.RecyclerViewBuilder stikkyHeader;
    private SwipyRefreshLayout mSwipyRefreshLayout;

    public DashboardFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
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
        view.findViewById(R.id.buttonDiscover).setOnClickListener(this);

    }

    private void refreshItem()
    {
        try
        {
            Thread.sleep(2000);
            mSwipyRefreshLayout.setRefreshing(false);
        }
        catch (InterruptedException e)
        {
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

    private void setupRecycleCommom(View view) {

        recyclerView_Commom = (RecyclerView) view.findViewById(R.id.recycler);
        layoutManagerCommom = new GridLayoutManager(context, 2);
        recyclerView_Commom.setLayoutManager(layoutManagerCommom);
        adapterCommom = new DashboardAdapter(context, null);
        recyclerView_Commom.setAdapter(adapterCommom);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonDiscover:
                Intent intent=new Intent(context, DiscoverActivity.class);
                startActivity(intent);
                break;
        }
    }

    private class ParallaxStikkyAnimator extends HeaderStikkyAnimator {
        @Override
        public AnimatorBuilder getAnimatorBuilder() {
            View mHeader_image = getHeader().findViewById(R.id.relativeLayout);
            return AnimatorBuilder.create().applyVerticalParallax(mHeader_image);
        }
    }

}
