package com.ecommerce.r1000.rakshit.drynutties;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link loginViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link loginViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class loginViewFragment extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public RecyclerView mRecyclerView;
    public RecyclerView.LayoutManager mLayoutManager;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment loginViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static loginViewFragment newInstance(String param1, String param2) {
        loginViewFragment fragment = new loginViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public loginViewFragment() {
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
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_login_view, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view7);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mLayoutManager = new LinearLayoutManager(getActivity());

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(new loginAdapter(getActivity()));

        return rootView;
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
    public class loginAdapter extends RecyclerView.Adapter<loginAdapter.CustomViewHolder>

    {

        private ArrayList<String> name;
        private Context mContext;
        public ImageView imageView;
        public TextView tv1;
        public  TextView tv2;
        public View view;
        public Bitmap b;
        public EditText ed;
        public EditText ed2;
        Button btn;
        Button btn2;


        public loginAdapter(Context context)
        {

            this.mContext = context;
        }

        public class CustomViewHolder extends RecyclerView.ViewHolder {

            public CustomViewHolder(View view) {
                super(view);
                tv1= (TextView) view.findViewById(R.id.login);
ed=(EditText)view.findViewById(R.id.login_id);
                ed2=(EditText)view.findViewById(R.id.passwo);
                btn=(Button)view.findViewById(R.id.button1);
                btn2=(Button)view.findViewById(R.id.button2);
            }
        }

        public  CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
        {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.login_card, null);


            CustomViewHolder viewHolder = new CustomViewHolder(view);

            return viewHolder;
        }



        @Override
        public void onBindViewHolder(CustomViewHolder customViewHolder, int i)
        {
         ed.focusSearch(View.FOCUS_LEFT);
btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String st=ed.getText().toString();
        String s=ed2.getText().toString();
        if(!(st.isEmpty()&&s.isEmpty()))
        {
            new logCheckApi(getActivity(), st, s).execute();

        }else
            Toast.makeText(getActivity(),"Wrong Credentials",Toast.LENGTH_LONG).show();
    }
});
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getActivity(). getSupportFragmentManager().beginTransaction().replace(R.id.container, new registerFragment())
                            .commit();
                }
            });
        }



        @Override
        public int getItemCount()
        {
            return 1;
        }



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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
