package restart.com.gridviewdemo.tow;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import restart.com.gridviewdemo.R;

public class GridViewTow extends AppCompatActivity {

    private GridView gridView;
    private List<PackageInfo> appinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view_tow);
        gridView = findViewById(R.id.gridview_tow);
        appinfo = getAppList();
        GridViewAdapterTow adapterTow = new GridViewAdapterTow(GridViewTow.this,appinfo);
        gridView.setAdapter(adapterTow);


    }

    public List<PackageInfo> getAppList() {
        PackageManager pm = getPackageManager();
        List<PackageInfo> infos = pm.getInstalledPackages(0);
        List<PackageInfo> appinfo = new ArrayList<>();
        for (PackageInfo info : infos) {
            if ((info.applicationInfo.flags & info.applicationInfo.FLAG_SYSTEM) == 0) {
                appinfo.add(info);
            }
        }


        return appinfo;

    }

    class GridViewAdapterTow extends BaseAdapter{
        private Context context;
        private  List<PackageInfo> infos;

        public GridViewAdapterTow(Context context, List<PackageInfo> infos) {
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
                viewHolder.app_icon = convertView.findViewById(R.id.app_appIcon);
                viewHolder.app_name = convertView.findViewById(R.id.app_name);
                convertView.setTag(viewHolder);
            }else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            PackageInfo info = infos.get(position);
            viewHolder.app_icon.setImageDrawable(info.applicationInfo.loadIcon(getPackageManager()));
            viewHolder.app_name.setText(info.applicationInfo.loadLabel(getPackageManager()));
            return convertView;
        }

        class ViewHolder{
            ImageView app_icon;
            TextView app_name;
        }
    }
}
