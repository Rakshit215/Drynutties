package com.ecommerce.r1000.rakshit.drynutties;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link aboutUsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link aboutUsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class aboutUsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public RecyclerView mRecyclerView,r;
    public RecyclerView.LayoutManager mLayoutManager,m;
    public String name[]={"Aviral Sharma","Anush Swaminathan"};
    public String post[]={"Founder","Co-Founder"};
    public String cont[]={"The Main aim of Opening Drynutties was to Provide High Quality Dry Fruits all over India at a reasonable rate.","Dry Fruits Consumed by almost Everyone in India lacks the Presence of a Market Providing High Quality Products at Low Rates."};
    String fontPath = "fonts/at.ttf";
    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment aboutUsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static aboutUsFragment newInstance(String param1, String param2) {
        aboutUsFragment fragment = new aboutUsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public aboutUsFragment() {
        // Required empty public constructor
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
        View root=inflater.inflate(R.layout.activity_about_us, container, false);
        mRecyclerView = (RecyclerView)root.findViewById(R.id.about);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(new aboutUsAdapter(getActivity()));



        r = (RecyclerView)root.findViewById(R.id.about2);
        r.setHasFixedSize(true);
        r.setLayoutManager(new LinearLayoutManager(getActivity()));
        m = new LinearLayoutManager(getActivity());
        r.setLayoutManager(m);

        r.setAdapter(new aboutAdapter(getActivity()));
        return r;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */


    public class aboutUsAdapter extends RecyclerView.Adapter<aboutUsAdapter.CustomViewHolder>

    {

        private Context mContext;
        public TextView title;
        public TextView po;
        public TextView matter;
        public LinearLayout lm;


        public aboutUsAdapter(Context context)
        {

            this.mContext = context;
        }

        public class CustomViewHolder extends RecyclerView.ViewHolder {

            public CustomViewHolder(View view) {
                super(view);
                Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), fontPath);

                // Applying font
                title=(TextView)view.findViewById(R.id.head);
                po=(TextView)view.findViewById(R.id.post);
                matter=(TextView)view.findViewById(R.id.matter);
                matter.setTypeface(tf);

                lm=(LinearLayout)view.findViewById(R.id.back);

            }
        }

        public  CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
        {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.about_single, null);

            CustomViewHolder viewHolder = new CustomViewHolder(view);

            return viewHolder;
        }



        @Override
        public void onBindViewHolder(CustomViewHolder customViewHolder, int i)
        {
            lm.setBackgroundColor(Color.parseColor("#ff4c18"));
            title.setText(name[i]);
            po.setText(post[i]);

            matter.setText(cont[i]);

        }



        @Override
        public int getItemCount()
        {
            return 1;
        }



    }
    public class aboutAdapter extends RecyclerView.Adapter<aboutAdapter.CustomViewHolder>

    {

        private Context mContext;
        public TextView title;
        public TextView po;
        public TextView matter;
        public LinearLayout lm;


        public aboutAdapter(Context context)
        {

            this.mContext = context;
        }

        public class CustomViewHolder extends RecyclerView.ViewHolder {

            public CustomViewHolder(View view) {
                super(view);
                Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), fontPath);


                title=(TextView)view.findViewById(R.id.head);
                po=(TextView)view.findViewById(R.id.post);
                matter=(TextView)view.findViewById(R.id.matter);
                matter.setTypeface(tf);

                lm=(LinearLayout)view.findViewById(R.id.back);

            }
        }

        public  CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
        {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.about_single, null);

            CustomViewHolder viewHolder = new CustomViewHolder(view);

            return viewHolder;
        }



        @Override
        public void onBindViewHolder(CustomViewHolder customViewHolder, int i)
        {
            lm.setBackgroundColor(Color.parseColor("#4ba321"));

            title.setText(name[i+1]);
            po.setText(post[i+1]);
            matter.setText(cont[i+1]);
        }



        @Override
        public int getItemCount()
        {
            return 1;
        }



    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
