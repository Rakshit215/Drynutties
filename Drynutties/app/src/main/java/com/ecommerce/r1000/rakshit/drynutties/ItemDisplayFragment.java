package com.ecommerce.r1000.rakshit.drynutties;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;


/**
 * A simple {@link android.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ItemDisplayFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ItemDisplayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ItemDisplayFragment extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView mRecyclerView;
    private File cacheDir;
    private File cacheFile;
    private LruCache<String, Bitmap> mMemoryCache;
    private FileInputStream fileInputStream;
    public RecyclerView.LayoutManager mLayoutManager;
    public int flag=0;
    public     JSONArray ja;
public     JSONObject jo;
    public ImageView imageView;
    public TextView tv1;
    public  TextView tv2;
    public View view;
    public Bitmap b;
    public Spinner s1;
    ItemAdapter adp;
    public String na;
    public TextView pri;
public View main;
public int elem=0;
public SharedPreferences sp;
    public SharedPreferences.Editor ed;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    DisplayItemApi obj;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ItemDisplayFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ItemDisplayFragment newInstance(String param1, String param2) {
        ItemDisplayFragment fragment = new ItemDisplayFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ItemDisplayFragment() {
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
        na=getArguments().getString("base");
        Log.e("Element 2",na);

        View rootView=inflater.inflate(R.layout.fragment_item_display, container, false);
        cacheDir = new File(getActivity().getCacheDir(), "thumbnails");
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        //Using 1/8th of the available memory for this memory cache
        final int cacheSize = maxMemory / 16;
        obj=new DisplayItemApi(getActivity(),na);
obj.execute();
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {

            @Override
            protected int sizeOf(String key, Bitmap bitmap1) {

                //The cache size will be measured in kb rather than number of items
                return bitmap1.getByteCount() / 1024;
            }
        };



adp=new ItemAdapter(getContext());
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view2);
       mRecyclerView.setHasFixedSize(true);
       mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
       mLayoutManager = new LinearLayoutManager(getActivity());

       mRecyclerView.setLayoutManager(mLayoutManager);

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
public class DisplayItemApi extends AsyncTask<String,String,String>
{
    Activity con;
    RecyclerView r;
    String itemName;
    StringBuilder result;
    String link="http://www.drynutties.com/Android/DisplayItem_api.php";
    public String u="http://www.drynutties.com/images/product/";


    public ArrayList<String> id=new ArrayList<String>();
    public ArrayList<String> img=new ArrayList<String>();
    public ArrayList<String>     wgt=new ArrayList<String>();
    public ArrayList<String>     price=new ArrayList<String>();
    public ArrayList<String>     sdesc=new ArrayList<String>();
    public ArrayList<String>     rate=new ArrayList<String>();
    public ArrayList<String>     qual=new ArrayList<String>();
    public ArrayList<String>     siz=new ArrayList<String>();
    public ArrayList<String>     hard=new ArrayList<String>();
    public ArrayList<String>     taste=new ArrayList<String>();


    DisplayItemApi(Activity c,String s)
    {
        this.con=c;
Log.e("Inner ",s);
        this.itemName=s;
        result=new StringBuilder();
        try {
            result.append("&");
            result.append(URLEncoder.encode("item", "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(s, "UTF-8"));

        }
        catch (UnsupportedEncodingException e)
        {
            Log.e("Error","Detected");
        }

    }

    @Override
    protected String doInBackground(String... strings)
    {

        try
        {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //connection.setRequestProperty("User-Agent","Mozilla/5.0");
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);


            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(result.toString());
            writer.flush();
            writer.close();
            os.close();
            connection.connect();
            Log.e("item Request Send","Yooooo");
            int i=connection.getResponseCode();

            Log.e("Input Line 0", String.valueOf(i));

            Log.e("Input Line","Hello");

            InputStream inputStream = connection.getInputStream();

            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
            String line = "abc";
            StringBuilder br=new StringBuilder();

            while ((line=rd.readLine()) != null)
            {
                Log.e("input drg Line", line);
                br.append(line);

                //Toast.makeText(getApplicationContext(), "I enter here", Toast.LENGTH_LONG).show();
            }
            ja=new JSONArray(br.toString());

            Log.e("Item Display", "two");
            //in=new java.net.URL(link).openStream();
            //icon= BitmapFactory.decodeStream(in);


            connection.disconnect();
        }catch (MalformedURLException e)
        {
            Log.e("Error 1", "Hello");

        }
        catch (IOException e)
        {
            // writing exception to log
            e.printStackTrace();
            Log.e("Error 2", "world");

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Error 3", "End");

        }
        //Log.e("Final String" , ja.toString());



        return null;
    }

    @Override
    protected void onPostExecute(String s)
    {
        super.onPostExecute(s);
String str;
        Float f;
        try {
            for (int i = 0; i < ja.length(); i++)
            {

                jo = ja.getJSONObject(i);
                Log.e("String", jo.toString());
                id.add(i, jo.getString("id"));
 str=jo.getString("weight");
           if(Float.parseFloat(str)<1000.00)
           {
               wgt.add(i, String.valueOf((int)Float.parseFloat(str))+"g");
           }
                else
           {
               f=Float.parseFloat(str);
               f/=1000;
               wgt.add(i, String.valueOf((int)Float.parseFloat(String.valueOf(f))+"Kg"));
           }
                img.add(i, u+jo.getString("img"));
                price.add(i,jo.getString("price"));
                sdesc.add(i, jo.getString("short_desc"));
                if(jo.getString("rating")!=null)
                {
                    rate.add(i,jo.getString("rating"));
                }else
                {
                    rate.add(i,jo.getString("-1"));

                }
                if(jo.getString("hardness")!=null)
                {
                    hard.add(i, jo.getString("hardness"));
                }else
                {
                    hard.add(i,jo.getString("-1"));

                }
                if(jo.getString("quality")!=null)
                {
                    qual.add(i,jo.getString("quality"));
                }else
                {
                   qual.add(i,jo.getString("-1"));

                }
                if(jo.getString("taste")!=null)
                {
                    taste.add(i, jo.getString("taste"));
                }else
                {
                    taste.add(i,jo.getString("-1"));

                }

                if(jo.getString("size")!=null)
                {
                    siz.add(i,jo.getString("size"));
                }else
                {
                    siz.add(i,jo.getString("-1"));

                }





            }
        }catch (JSONException e)
        {

        }
        mRecyclerView.setAdapter(adp);





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
    public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.CustomViewHolder>

    {

        private Context mContext;
        public View view;
        public Bitmap b;

        private RecyclerView mView;
        public RecyclerView.LayoutManager mManager;

        public TextView rate1;
        public RatingBar r1;
        public TextView shl;
        public ProgressBar pb;
        public TextView hrd;
        public TextView sft;
        public TextView size1;
        public TextView size2;
        public TextView taste1;
        public Button add;
        public Button sub;
        public Button cart;
public TextView qnty;
        public TextView taste2;
        int pos;



        public ItemAdapter(Context context)
        {
            this.mContext = context;
        }

        public class CustomViewHolder extends RecyclerView.ViewHolder {

            public CustomViewHolder(View view) {
                super(view);
                imageView = (ImageView) view.findViewById(R.id.img);
                tv1= (TextView) view.findViewById(R.id.textV3);
                s1=(Spinner)view.findViewById(R.id.spinner1);
                pri=(TextView)view.findViewById(R.id.price);
                rate1= (TextView) view.findViewById(R.id.rate);
                r1= (RatingBar) view.findViewById(R.id.ratingBar);
                shl=(TextView)view.findViewById(R.id.shell);
                pb=(ProgressBar)view.findViewById(R.id.progressBar2);
                hrd=(TextView)view.findViewById(R.id.textView3);
                sft=(TextView)view.findViewById(R.id.textView4);
                size1=(TextView)view.findViewById(R.id.size);
                size2=(TextView)view.findViewById(R.id.ksize);
                taste1=(TextView)view.findViewById(R.id.taste);
                taste2=(TextView)view.findViewById(R.id.taste2);
                add=(Button)view.findViewById(R.id.add);
                sub=(Button)view.findViewById(R.id.dec);
                cart=(Button)view.findViewById(R.id.button);
qnty=(TextView)view.findViewById(R.id.qnty2);



            }
        }

        public  CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
        {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.display_item, null);

/* main=view;
            xadp=new ExtendedItemAdapter(getContext(),obj,0);
          mView = (RecyclerView) main.findViewById(R.id.recycler_view3);
        mView.setHasFixedSize(true);
      mView.setLayoutManager(new LinearLayoutManager(getActivity()));
   mManager = new LinearLayoutManager(getActivity());

  mView.setLayoutManager(mManager);
            mView.setAdapter(xadp);
   */
            CustomViewHolder viewHolder = new CustomViewHolder(view);

            return viewHolder;
        }



        @Override
        public void onBindViewHolder(CustomViewHolder customViewHolder, int i)
        {
            tv1.setText(na);
            sp = getActivity().getSharedPreferences("detail", getActivity().MODE_PRIVATE);
            String[] stockArr = new String[obj.wgt.size()];
            stockArr = obj.wgt.toArray(stockArr);
            ArrayAdapter<String> ap= new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,stockArr);
            ap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            s1.setAdapter(ap);
            pri.setText(obj.price.get(elem));
/*
            xadp=new ExtendedItemAdapter(getContext(),obj,0);
            mView = (RecyclerView) main.findViewById(R.id.recycler_view3);
            mView.setHasFixedSize(true);
            mView.setLayoutManager(new LinearLayoutManager(getActivity()));

            mView.setAdapter(xadp);
  */
            if(!obj.rate.get(elem).equalsIgnoreCase("null"))
            {
                Log.e("condition",obj.rate.get(elem));
                rate1.setVisibility(View.VISIBLE);
                r1.setVisibility(View.VISIBLE);
                r1.setRating((Float.parseFloat(obj.rate.get(elem)))/10*5);
            }
            if(!obj.hard.get(elem).equalsIgnoreCase("null"))
            {
                Log.e("condition",obj.hard.get(elem));
                shl.setVisibility(View.VISIBLE);
                pb.setVisibility(View.VISIBLE);
                pb.setMax(100);
                pb.setProgress((int) (Float.parseFloat(obj.hard.get(elem)) * 10));
                hrd.setVisibility(View.VISIBLE);
                sft.setVisibility(View.VISIBLE);

            }
            if(!obj.siz.get(elem).equalsIgnoreCase("null"))
            {
                Log.e("condition",obj.siz.get(elem));
                size1.setVisibility(View.VISIBLE);
                size2.setVisibility(View.VISIBLE);
                size2.setText(obj.siz.get(elem));
            }

            if(!obj.taste.get(elem).equalsIgnoreCase("null"))
            {
                Log.e("condition",obj.taste.get(elem));
                taste1.setVisibility(View.VISIBLE);
                taste2.setVisibility(View.VISIBLE);
                taste2.setText(obj.taste.get(elem));
            }


            if (flag == 0) {
                loadBitmap(obj.img.get(i), imageView);
                flag=1;
            }

            s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                                         {

                                             @Override
                                             public void onItemSelected(AdapterView arg0, View arg1,
                                                                        int arg2, long arg3)
                                             {
                                     //            xadp=new ExtendedItemAdapter(getContext(),obj,arg2);
                                       //          mView = (RecyclerView) main.findViewById(R.id.recycler_view3);
                                         //        mView.setHasFixedSize(true);
                                           //      mView.setLayoutManager(new LinearLayoutManager(getActivity()));
                                           //      mView.setAdapter(xadp);

                                                 pri.setText(obj.price.get(arg2));
                                                 elem=arg2;
                                                 }

                                             @Override
                                             public void onNothingSelected(AdapterView arg0)
                                             {

                                             }

                                         }
            );

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                String s=qnty.getText().toString();
                    int val=Integer.valueOf(s);
                    val++;
                    qnty.setText(String.valueOf(val));
                }
            });
            sub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String s=qnty.getText().toString();
                    int val=Integer.valueOf(s);
                    if(val>1)
                    val--;

                    qnty.setText(String.valueOf(val));

                }
            });
cart.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Toast.makeText(getActivity(),"Added to cart",Toast.LENGTH_LONG).show();
        String quantity=qnty.getText().toString();
        String userId=sp.getString("userId","-1");
        String productId=obj.id.get(elem);
        new addCartApi(getActivity(),quantity,userId,productId).execute();
    }
});
        }



        @Override
        public int getItemCount()
        {
            return 1;
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
            InputStream inputStream = new URL(imgURL).openStream();
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
            InputStream inputStream = new URL(imgURL).openStream();
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
