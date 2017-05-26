package com.ecommerce.r1000.rakshit.drynutties;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;


/**
 * A simple {@link android.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ElementFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ElementFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ElementFragment extends android.support.v4.app.Fragment implements RecyclerView.OnItemTouchListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ElementsApi ob;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public Bitmap icon;
    public ImageView iv;
    private RecyclerView mRecyclerView;
    private File cacheDir;
    private File cacheFile;
    private LruCache<String, Bitmap> mMemoryCache;
    private FileInputStream fileInputStream;
    public RecyclerView.LayoutManager mLayoutManager;

    public int position;
    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ElementFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ElementFragment newInstance(String param1, String param2) {
        ElementFragment fragment = new ElementFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    public ElementFragment() {
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
        String tabName=getArguments().getString("tab");
        View rootView=inflater.inflate(R.layout.fragment_element, container, false);
        cacheDir = new File(getActivity().getCacheDir(), "thumbnails");
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        Log.e("Position",tabName);

        //Using 1/8th of the available memory for this memory cache
        final int cacheSize = maxMemory / 16;

        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {

            @Override
            protected int sizeOf(String key, Bitmap bitmap1) {

                //The cache size will be measured in kb rather than number of items
                return bitmap1.getByteCount() / 1024;
            }
        };

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mLayoutManager = new GridLayoutManager(getActivity(), 2);

        mRecyclerView.setLayoutManager(mLayoutManager);
        ob=new ElementsApi(getActivity(),mRecyclerView,tabName);
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // TODO Handle item click
                      int i =view .getId();
                       TextView tv=(TextView) view.findViewById(R.id.textV1);
                        String str=tv.getText().toString();
                      //  Toast.makeText(getActivity(),"Item Click :"+String.valueOf(i)+"\n string "+str.substring(0,str.indexOf('\n')), Toast.LENGTH_SHORT).show();
                        Intent in=new Intent(getActivity(),ItemDisplayActivity.class);
                        in.putExtra("item",str.substring(0,str.indexOf('\n')));
                        startActivity(in);
                    }
                })
        );

        ob.execute();
        ob.adapter = new EventsAdapter(getContext(), ob.name);


        return rootView;

    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.CustomViewHolder>

    {

        private ArrayList<String> name;
        private Context mContext;
        public ImageView imageView;
        public TextView tv1;
        public  TextView tv2;
        public View view;
        public Bitmap b;


        public EventsAdapter(Context context,ArrayList<String> feedItemList)
        {
            this.name = feedItemList;
            this.mContext = context;
        }

        public class CustomViewHolder extends RecyclerView.ViewHolder {

            public CustomViewHolder(View view) {
                super(view);
                imageView = (ImageView) view.findViewById(R.id.cartImg);
                tv1= (TextView) view.findViewById(R.id.textV1);
            }
        }

        public  CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
        {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.event_list, null);


            CustomViewHolder viewHolder = new CustomViewHolder(view);
            return viewHolder;
        }



        @Override
        public void onBindViewHolder(CustomViewHolder customViewHolder, int i)
        {
            String s=ob.wgt.get(i);
            tv1.setText(ob.name.get(i));

            tv1.append("\nStarting at");
            tv1.append("\n₹"+ob.price.get(i)+"("+s.substring(0,s.indexOf('.'))+"g)");
           loadBitmap(ob.img.get(i), imageView);


           /* new AsyncTask<String,Void,Bitmap>()
            {
                protected void onPreExecute()
                {
                    super.onPreExecute();
                }

                @Override
                protected Bitmap doInBackground(String... params)
                {
                    String u=params[0];

                    try
                    {
                        InputStream in=new java.net.URL(u).openStream();
                        icon= BitmapFactory.decodeStream(in);

                    }catch(Exception e)
                    {

                    }
                    return icon;

                }

                protected void onPostExecute(Bitmap result)
                {
                    imageView.setImageBitmap(result);

                }
            }.execute(ob.img.get(i));

            Log.e("Msj",ob.img.get(i).toString());
*/

        }



        @Override
        public int getItemCount()
        {
            return ob.name.size();
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

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
