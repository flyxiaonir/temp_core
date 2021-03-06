package com.lf.core;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.lf.core.api.TempAPI;
import com.lf.tempcore.tempModule.tempDebuger.Debug;
import com.lf.tempcore.tempModule.tempRemotComm.TempRemotAPIConnecter;
import com.lf.tempcore.tempResponse.TempResponse;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ActProgressTest.class));
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });

        /*ActivityInfo info= null;
        try {
            info = this.getPackageManager()
                    .getActivityInfo(getComponentName(),
                            PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String msg =info.metaData.getString("data_Name");*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();

            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            if (getArguments().getInt(ARG_SECTION_NUMBER)==1){

                final  ImageView image = (ImageView) rootView.findViewById(R.id.main_image1);
                Button main_loadImage = (Button) rootView.findViewById(R.id.main_loadImage);
                main_loadImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Debug.info("加载gif");
                        Glide.with( getActivity() )
                                .load( "http://love.doghouse.com.tw/image/wallpaper/011102/bf1554.jpg")
                                .transform( new BlurTransformation( getActivity() ) )
                                .bitmapTransform( new BlurTransformation( getActivity() ) ) // this would work too!
                                .into( image );
                    }
                });
                File media = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/DCIM/Camear/20151111_080709_71191.mp4");
//            Glide.with(getActivity()).load("http://c.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=33e67285d343ad4ba67b4ec4b7327699/574e9258d109b3de76576b48cebf6c81800a4c22.jpg") .into(image);
//            Glide.with(this).load(Uri.fromFile(media)).into(image);
//            Glide.with(this).load(R.mipmap.ic_launcher).into(image);
                Glide.with( this )
                        .load( "http://love.doghouse.com.tw/image/wallpaper/011102/bf1554.jpg")
//                    .transform( new BlurTransformation( getActivity() ) )
                        //.bitmapTransform( new BlurTransformation( context ) ) // this would work too!
                        .into( image );
                textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));

                TempRemotAPIConnecter.INSTANCE.executeRemotAPI(TempRemotAPIConnecter.INSTANCE.createRemoteApi(TempAPI.class).userLogin("17783617732", "123456"), new Callback<TempResponse>() {
                    @Override
                    public void onResponse(Call<TempResponse> call, Response<TempResponse> response) {
                        Debug.info("MainActivity", response.body().toString());

                    }

                    @Override
                    public void onFailure(Call<TempResponse> call, Throwable t) {
                        Debug.info("MainActivity", "获取数据失败" + t.toString());
                    }
                });
            }else if (getArguments().getInt(ARG_SECTION_NUMBER)==3){

            }


            return rootView;
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

        }
    }
    public static class BlurTransformation extends BitmapTransformation {

        private RenderScript rs;

        public BlurTransformation(Context context) {
            super( context );

            rs = RenderScript.create( context );
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            Bitmap blurredBitmap = toTransform.copy( Bitmap.Config.ARGB_8888, true );

            // Allocate memory for Renderscript to work with
            Allocation input = Allocation.createFromBitmap(
                    rs,
                    blurredBitmap,
                    Allocation.MipmapControl.MIPMAP_FULL,
                    Allocation.USAGE_SHARED
            );
            Allocation output = Allocation.createTyped(rs, input.getType());

            // Load up an instance of the specific script that we want to use.
            ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
            script.setInput(input);

            // Set the blur radius
            script.setRadius(10);

            // Start the ScriptIntrinisicBlur
            script.forEach(output);

            // Copy the output to the blurred bitmap
            output.copyTo(blurredBitmap);

            toTransform.recycle();

            return blurredBitmap;
        }

        @Override
        public String getId() {
            return "blur";
        }
    }
    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }
}
