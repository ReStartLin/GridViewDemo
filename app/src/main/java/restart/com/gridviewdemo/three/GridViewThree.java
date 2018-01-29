package restart.com.gridviewdemo.three;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import restart.com.gridviewdemo.R;

public class GridViewThree extends AppCompatActivity {

    private GridView gridview;
    private List<String> imgList;
    private List<ImageInfo> imageInfos;
    private GridViewAdapterThree gridViewAdapterThree;
    private ImageLoadTask imageLoadTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view_three);
        gridview = findViewById(R.id.gridview_three);
        initData();
        gridview.setAdapter(gridViewAdapterThree);
        imageLoadTask = new ImageLoadTask(this, gridViewAdapterThree);
        imageLoadTask.execute();

    }

    private void initData() {
        imgList=new ArrayList<String>();
        imgList.add("http://img5.duitang.com/uploads/item/201406/26/20140626164837_dzKds.jpeg");
        imgList.add("http://img2.imgtn.bdimg.com/it/u=3980629563,3881837630&fm=21&gp=0.jpg");
        imgList.add("http://img5q.duitang.com/uploads/item/201505/08/20150508155052_XJaNW.jpeg");
        imgList.add("http://img4.duitang.com/uploads/item/201407/02/20140702105736_FdN5P.jpeg");
        imgList.add("http://img2.imgtn.bdimg.com/it/u=2866652161,3841912673&fm=21&gp=0.jpg");
        imgList.add("http://img4.imgtn.bdimg.com/it/u=883757693,2063816225&fm=21&gp=0.jpg");
        imgList.add("http://cdn.duitang.com/uploads/item/201309/26/20130926110955_QtUdX.jpeg");
        imgList.add("http://zjimg.5054399.com/allimg/160815/14_160815161625_9.jpg");
        imgList.add("http://i-7.vcimg.com/trim/09ce7067d2467c54cf05bbd271ee3ec8430415/trim.jpg");
        imageInfos = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            ImageInfo imageInfo = new ImageInfo();
            imageInfo.setImagePath(imgList.get(i));
            imageInfos.add(imageInfo);
        }
        gridViewAdapterThree = new GridViewAdapterThree(this, imageInfos);

    }

    public Bitmap getImagefromNetWork(String path) {
        try {
            URL url = new URL(path);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setConnectTimeout(10000);
            InputStream inputStream = urlConnection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
            return bitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public class ImageLoadTask extends AsyncTask<String,Void,Void>{
        private GridViewAdapterThree gridViewAdapterThree;
        private Context context;

        ImageLoadTask(Context context,GridViewAdapterThree gridViewAdapterThree) {
            this.gridViewAdapterThree = gridViewAdapterThree;
            this.context = context;
        }

        @Override
        protected Void doInBackground(String... strings) {
            for (int i = 0; i < gridViewAdapterThree.getCount(); i++) {
                ImageInfo imageInfo = (ImageInfo) gridViewAdapterThree.getItem(i);
                String path = imageInfo.getImagePath();
                Bitmap bitmap = getImagefromNetWork(path);
                imageInfo.setBitmap(bitmap);
                publishProgress();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            gridViewAdapterThree.notifyDataSetChanged();
        }
    }



    class GridViewAdapterThree extends BaseAdapter {
        private Context context;
        private List<ImageInfo> infos;

        public GridViewAdapterThree(Context context, List<ImageInfo> infos) {
            this.context = context;
            this.infos = infos;
        }

        @Override
        public int getCount() {
            return infos.size();
        }

        @Override
        public Object getItem(int position) {
            return infos.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.item_tow, null);
                viewHolder = new ViewHolder();
                viewHolder.imageView = convertView.findViewById(R.id.app_appIcon);
                viewHolder.textView = convertView.findViewById(R.id.app_name);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            ImageInfo info = infos.get(position);
            viewHolder.textView.setText(info.getText());
            if (info.getBitmap() == null) {
                viewHolder.imageView.setImageResource(R.mipmap.ic_launcher);
            } else {
                viewHolder.imageView.setImageBitmap(info.getBitmap());
            }
            return convertView;
        }

        class ViewHolder {
            ImageView imageView;
            TextView textView;
        }
    }
}
