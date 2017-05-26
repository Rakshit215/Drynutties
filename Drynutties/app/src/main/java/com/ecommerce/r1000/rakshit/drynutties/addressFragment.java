package com.ecommerce.r1000.rakshit.drynutties;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.citrus.sample.UIActivity;
import com.payu.paymentgateway.payu;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link addressFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link addressFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class addressFragment extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
public int val=0;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public prevAddressCheckApi ob;
    public TextView tv1;

    private OnFragmentInteractionListener mListener;
    public View rootView;
    public RecyclerView mRecyclerView;
    public RecyclerView.LayoutManager mLayoutManager;
public SharedPreferences sp;
    public SharedPreferences.Editor ed;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment addressFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static addressFragment newInstance(String param1, String param2) {
        addressFragment fragment = new addressFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public addressFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.fragment_address, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view8);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mLayoutManager = new GridLayoutManager(getActivity(),2);
sp=getActivity().getSharedPreferences("detail",getActivity().MODE_PRIVATE);
        mRecyclerView.setLayoutManager(mLayoutManager);
        ed=sp.edit();


ob=new prevAddressCheckApi(getActivity(),sp.getString("userId","1"),mRecyclerView,new addressAdapter(getContext()));
        ob.execute();
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // TODO Handle item click
                        val=position;

                    }
                })
        );
        return  rootView;}

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
    public void onResume() {

        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_BACK){

                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, new cartFragment())
                            .commit();

                    return true;

                }

                return false;
            }
        });
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
    public class addressAdapter extends RecyclerView.Adapter<addressAdapter.CustomViewHolder>

    {

        public TextView na;
        public TextView email;
        public TextView phn;
        public TextView ship_add;
        public TextView ship_city;
        public TextView ship_state;
        public TextView ship_zip;
        public Button del;

        public addressAdapter(Context context)
        {


        }

        public class CustomViewHolder extends RecyclerView.ViewHolder {

            public CustomViewHolder(View view) {
                super(view);

                na= (TextView) view.findViewById(R.id.name);
                phn= (TextView) view.findViewById(R.id.phno);
                ship_add= (TextView) view.findViewById(R.id.ship_add);
                ship_city= (TextView) view.findViewById(R.id.ship_city);
                ship_state=(TextView) view.findViewById(R.id.ship_state);
                ship_zip= (TextView) view.findViewById(R.id.ship_zip);
                del= (Button) view.findViewById(R.id.button4);





            }
        }

        public  CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
        {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.new_address, null);

            CustomViewHolder viewHolder = new CustomViewHolder(view);

            return viewHolder;
        }



        @Override
        public void onBindViewHolder(CustomViewHolder customViewHolder, int i)
        {
            na.setText(ob.obj.name.get(i));
            phn.setText(ob.obj.phno.get(i));
           ship_city.setText(ob.obj.ship_city.get(i));
            ship_add.setText(ob.obj.ship_ad.get(i));
           ship_state.setText(ob.obj.ship_state.get(i));
            ship_zip.setText(ob.obj.ship_zip.get(i));

del.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view)
    {
        ed.putString("name",ob.obj.name.get(val));
        ed.putString("email",ob.obj.email.get(val));
        ed.putString("phn",ob.obj.phno.get(val));
        ed.putString("ship_add",ob.obj.ship_ad.get(val));
        ed.putString("ship_city",ob.obj.ship_city.get(val));
        ed.putString("ship_state",ob.obj.ship_state.get(val));
        ed.putString("ship_zip",ob.obj.ship_zip.get(val));
        ed.commit();


        if(sp.getString("opt","0").equalsIgnoreCase("1"))
        {
            Intent in = new Intent(getActivity(), UIActivity.class);
            ed.remove("opt").commit();
            getActivity().finish();

            startActivity(in);
        }
        else if(sp.getString("opt","0").equalsIgnoreCase("2"))
        {
            Intent in = new Intent(getActivity(),payu.class);
            ed.remove("opt").commit();
            getActivity().finish();

            startActivity(in);
        }
        else
        {
            Toast.makeText(getActivity(),"Updating Cart....",Toast.LENGTH_LONG).show();
            new codApi(getActivity()).execute();
            ed.remove("opt").commit();
        }
    }
});
        }



        @Override
        public int getItemCount()
        {
            return ob.obj.name.size();
        }



    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.address_menu, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_filter){
            //Do whatever you want to do
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container,new newAddressFragment())
                    .commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
