package com.ecommerce.r1000.rakshit.drynutties;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.citrus.sample.UIActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;


/**
 * A simple {@link android.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link cartFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link cartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class cartFragment extends android.support.v4.app.Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public RadioGroup rg;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
Button cop;
    public  int fla;
    public String str;
public    int f=0;
    private OnFragmentInteractionListener mListener;
    private RecyclerView mRecyclerView;
    private File cacheDir;
    private File cacheFile;
    private LruCache<String, Bitmap> mMemoryCache;
    private FileInputStream fileInputStream;
    public RecyclerView.LayoutManager mLayoutManager;
    public int flag=0;
    public JSONArray ja;
    public JSONObject jo;
public   String userId;
    public CartItemApi obj;
    SharedPreferences sp;
    SharedPreferences.Editor ed;
public int j=0;
    public View rootView;
    public Button pur;
    public TextView cart_sub;
    public LinearLayout app;
    public LinearLayout pay;
public int val=-1;
    public TextView disc,descri;
    public String updtval;
    public String updtid;
public Button aplybtn;
    public TextView uniquantity;
public String finamt;
    public TextView tv1;
public EditText cpned;
    public TextView cartsub;
    public TextView gto;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment cartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static cartFragment newInstance(String param1, String param2)
    {
        cartFragment fragment = new cartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public cartFragment() {
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
       rootView=inflater.inflate(R.layout.fragment_cart,container, false);
        cop=(Button) rootView.findViewById(R.id.cpn1);
        app=(LinearLayout)rootView.findViewById(R.id.apply);
        pay=(LinearLayout)rootView.findViewById(R.id.payment);
        sp= getActivity().getSharedPreferences("detail",getActivity().MODE_PRIVATE);
        rg=(RadioGroup)rootView.findViewById(R.id.radioSex);
ed=sp.edit();
        cpned=(EditText)rootView.findViewById(R.id.cpned);
        aplybtn=(Button)rootView.findViewById(R.id.applybtn);
cartsub=(TextView)rootView.findViewById(R.id.csubt);
        gto=(TextView)rootView.findViewById(R.id.gtotal);
        tv1=(TextView)rootView.findViewById(R.id.gtotal);
disc=(TextView)rootView.findViewById(R.id.discn);
        descri=(TextView)rootView.findViewById(R.id.discndet);


        cop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
String str=cpned.getText().toString();
   new discountApi(getActivity(),str,rootView).execute();
     }
        });


        cacheDir = new File(getActivity().getCacheDir(), "thumbnails");
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        //Using 1/8th of the available memory for this memory cache
        final int cacheSize = maxMemory / 16;

        userId=sp.getString("userId","1");

        LinearLayout linearLayout = (LinearLayout)rootView.findViewById(R.id.linearLayout);
       pur=(Button)rootView.findViewById(R.id.buy);
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {

            @Override
            protected int sizeOf(String key, Bitmap bitmap1) {

                //The cache size will be measured in kb rather than number of items
                return bitmap1.getByteCount() / 1024;
            }
        };

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view4);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mLayoutManager = new LinearLayoutManager(getActivity());

        mRecyclerView.setLayoutManager(mLayoutManager);
        obj=new CartItemApi(getActivity(),userId,new CartItemAdapter(getContext()),mRecyclerView,linearLayout,rootView);

        obj.execute();
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // TODO Handle item click
                        val=position;
                        uniquantity=(TextView)view.findViewById(R.id.qntity);

                    }
                })
        );

pur.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
      //Intent in=new Intent(getActivity(), UIActivity.class);
        //startActivity(in);
     str=tv1.getText().toString();
        int ind=str.indexOf("₹");

        ed.putString("amt",str.substring(ind+1));
        int selectedId = rg.getCheckedRadioButtonId();
        if(selectedId==R.id.cod)
        {
            ed.putString("opt","0");
        }
        else if(selectedId==R.id.citrusopt)
        {
            ed.putString("opt","1");
        }else if(selectedId==R.id.payu)
        {
            ed.putString("opt","2");
        }


        ed.commit();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new addressFragment())
                .commit();

    }
});

aplybtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
if(f==0)
{
    getActivity().getSupportFragmentManager().beginTransaction()
            .replace(R.id.container, new cartFragment())
            .commit();

}
else
{
    Toast.makeText(getActivity(), "Updating Cart...", Toast.LENGTH_LONG).show();
    new updateCartApi(getActivity(), updtval, userId, updtid).execute();
}   }
});
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
    public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.CustomViewHolder>

    {

        private Context mContext;
        public View view;
        public Bitmap b;

        private RecyclerView mView;
        public RecyclerView.LayoutManager mManager;
        public ImageView cartImg;
        public TextView title;
        public TextView wegt;
        public TextView pr;
        public TextView quantity;
        public TextView total;
        public ImageView remove;
        public Button inc;
        public Button dec;


        public  View childView;


        public CartItemAdapter(Context context)
        {
            this.mContext = context;
        }

        public class CustomViewHolder extends RecyclerView.ViewHolder {

            public CustomViewHolder(View view) {
                super(view);
                cartImg= (ImageView) view.findViewById(R.id.cartImg);
                title= (TextView) view.findViewById(R.id.tle);
                wegt=(TextView)view.findViewById(R.id.wgt11);
                pr= (TextView) view.findViewById(R.id.pri);
                quantity= (TextView) view.findViewById(R.id.qntity);
                total= (TextView) view.findViewById(R.id.total);
                remove=(ImageView)view.findViewById(R.id.dusbin);
inc=(Button)view.findViewById(R.id.add);
                dec=(Button)view.findViewById(R.id.dec);
            }
        }

        public  CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
        {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_single_item, null);

            CustomViewHolder viewHolder = new CustomViewHolder(view);

            return viewHolder;
        }



        @Override
        public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {

//val=i;
            title.setText(obj.name.get(i));
           String wt=obj.wgt.get(i);
            float n=Float.parseFloat(wt);
            n*=1000;
            int w=(int)n;
            if(w<1000)
            {
                wegt.setText(String.valueOf(w)+"g");
            }
            else
            {
                wegt.setText(String.valueOf(w/1000)+"Kg");

            }
            pr.setText("₹"+obj.price.get(i));
            quantity.setText(obj.qnty.get(i));
            int amt = (int) (Float.parseFloat(obj.price.get(i).toString()));
            amt *= Integer.parseInt(quantity.getText().toString());
            total.setText("₹"+String.valueOf(amt)+".0");

            loadBitmap(obj.img.get(i), cartImg);
            remove.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View view) {
                                              childView=view;
                                              AlertDialog.Builder builderSingle = new AlertDialog.Builder(getActivity());
                                              builderSingle.setIcon(R.drawable.welcome);
                                              builderSingle.setTitle("You want to delete the product");
                                              builderSingle.setPositiveButton(
                                                      "Delete",
                                                      new DialogInterface.OnClickListener() {
                                                          @Override
                                                          public void onClick(DialogInterface dialog, int which) {
new deleteCartItem(getActivity(),obj.pid.get(val),userId).execute();
                                                              Toast.makeText(getActivity(), "Removed"+String.valueOf(val), Toast.LENGTH_LONG).show();
                                                          }
                                                      });
                                              builderSingle.setNegativeButton(
                                                      "Cancel",
                                                      new DialogInterface.OnClickListener() {
                                                          @Override
                                                          public void onClick(DialogInterface dialog, int which) {
                                                              dialog.dismiss();
                                                          }
                                                      });
                                              builderSingle.show();


                                          }
                                      }
            );

            inc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(f==0)
                    {
                        f=1;
                        fla=val;
                        app.setVisibility(View.VISIBLE);
                        pay.setVisibility(View.GONE);

                    }
                    if(fla==val)
                    {
                        String s = uniquantity.getText().toString();
                        int value = Integer.valueOf(s);
                        value++;
                        uniquantity.setText(String.valueOf(value));
                        updtval = String.valueOf(value);
                        updtid=obj.pid.get(val);
                    }
                    else
                        Toast.makeText(getActivity(),"Apply Changes for next alteration",Toast.LENGTH_LONG).show();

                }
            });
            dec.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(f==0)
                    {
                        f=1;
                        fla=val;
                        app.setVisibility(View.VISIBLE);
                        pay.setVisibility(View.GONE);

                    }
                    if(fla==val) {

                        String s = uniquantity.getText().toString();
                        int value = Integer.valueOf(s);
                        if (value > 1)
                            value--;

                        uniquantity.setText(String.valueOf(value));
                        updtval=String.valueOf(value);
                        updtid=obj.pid.get(val);
                    }
                    else
                        Toast.makeText(getActivity(),"Apply Changes for next alteration",Toast.LENGTH_SHORT).show();

                }
            });
        }
        @Override
        public int getItemCount()
        {
            return obj.name.size();
        }



    }



    public void loadBitmap(String imgURL, ImageView imageView)
    {

        try
        {
            cacheFile = new File(cacheDir, ""+imgURL.hashCode());
            fileInputStream = new FileInputStream(cacheFile);
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
            Log.d("cacheURL", "File path not found");
        }

        final Bitmap bitmap1 = getBitmapFromMemCache(imgURL);
        final Bitmap bitmap2 = BitmapFactory.decodeStream(fileInputStream);

        //Checking for cache in disk
        if (bitmap2 != null)
        {
            imageView.setImageBitmap(bitmap2);
        }
        else
        {
            //Checking for cache in memory
            if (bitmap1 != null)
            {
                imageView.setImageBitmap(bitmap1);
            }
            else
            {

                if (cancelPotentialWork(imgURL, imageView))
                {
                    final LoadImage task = new LoadImage(imageView);
                    final AsyncDrawable asyncDrawable = new AsyncDrawable(imgURL, task);
                    imageView.setImageDrawable(asyncDrawable);
                    task.execute(imgURL);
                }
            }
        }
    }

    public static Bitmap decodeSampledBitmapFromURL(String imgURL, int reqWidth, int reqHeight)
    {
        Bitmap bitmap1 = null;

        //First decode with inJustDecodeBounds set to true to get dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        try
        {
            InputStream inputStream = new java.net.URL(imgURL).openStream();
            bitmap1 = BitmapFactory.decodeStream(inputStream, null, options);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        //Calculating the inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        //Decode bimap with inSampleSize set
        options.inJustDecodeBounds = false;
        try
        {
            InputStream inputStream = new java.net.URL(imgURL).openStream();
            bitmap1 = BitmapFactory.decodeStream(inputStream, null, options);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return bitmap1;
    }


    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight)
    {

        //Getting raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if(height > reqHeight || width > reqWidth)
        {

            final int halfHeight = height/2;
            final int halfWidth = width/2;

            //Calculate the largest inSampleSize value that is power of 2 and keeps both
            //height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
    private class LoadImage extends AsyncTask<String, Void, Bitmap>
    {
        private final WeakReference<ImageView> imageViewWeakReference;
        private String data = null;

        public LoadImage(ImageView imageView) {
            //Using weak reference so that imageView can be garbage collected
            imageViewWeakReference = new WeakReference<ImageView>(imageView);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(String... strings) {

            data = strings[0];
            final Bitmap bitmap1 = decodeSampledBitmapFromURL(data, 150, 150);
            //Adding bitmap to memory cache
            addBitmapToMemoryCache(data, bitmap1);
            //Adding bitmap as cache image
            putBitmapInDiskCache(data, bitmap1);
            return bitmap1;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap1) {
            if (isCancelled()) {
                bitmap1 = null;
            }

            if(imageViewWeakReference != null && bitmap1 !=null) {
                final ImageView imageView = imageViewWeakReference.get();
                final LoadImage bitmapWorkerTask = getBitmapWorkerTask(imageView);

                if (this == bitmapWorkerTask && imageView != null) {
                    imageView.setImageBitmap(bitmap1);
                }
            }
        }
    }
    private static LoadImage getBitmapWorkerTask(ImageView imageView) {
        if(imageView != null) {
            final Drawable drawable = imageView.getDrawable();
            if (drawable instanceof AsyncDrawable) {
                final AsyncDrawable asyncDrawable = (AsyncDrawable) drawable;
                return asyncDrawable.getBitmapWorkerTask();
            }
        }
        return null;
    }

    static class AsyncDrawable extends BitmapDrawable {
        private final WeakReference<LoadImage> bitmapWorkerTaskReference;

        public AsyncDrawable(String imgURL, LoadImage bitmapWorkerTask) {
            //super(imgURL, bitmap1);
            bitmapWorkerTaskReference = new WeakReference<LoadImage>(bitmapWorkerTask);
        }

        public LoadImage getBitmapWorkerTask() {
            return bitmapWorkerTaskReference.get();
        }
    }

    public static boolean cancelPotentialWork(String data, ImageView imageView) {
        final LoadImage bitmapWorkerTask = getBitmapWorkerTask(imageView);

        if(bitmapWorkerTask != null) {
            final String bitmapData = bitmapWorkerTask.data;
            //If the bitmap is not yet set or differs from new data
            if (bitmapData == null || bitmapData != data) {
                //Cancel previous task
                bitmapWorkerTask.cancel(true);
            }
            else {
                //The same work is already in progress
                return false;
            }
        }
        //No task is associated with the ImageView, or existing task was cancelled
        return true;
    }
    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if(getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }

    /*
    * Using disk cache
    */
    private void putBitmapInDiskCache(String key, Bitmap bitmap) {

        cacheDir.mkdir();
        //Create a path in that dir for a file
        cacheFile = new File(cacheDir, ""+key.hashCode());
        try {
            //Create a file at the path, and open it for writing, obtaining the output stream
            cacheFile.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(cacheFile);
            //Write the bitmap to the output stream, in PNG format
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            //Flush and close the output stream
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("cachePng", "Error while saving image to cache");
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
