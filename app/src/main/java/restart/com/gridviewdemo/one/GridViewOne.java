package restart.com.gridviewdemo.one;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import restart.com.gridviewdemo.R;

public class GridViewOne extends AppCompatActivity {

    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view_one);
        //数据源
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            stringList.add("慕课网"+i);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,stringList);

        gridView = findViewById(R.id.gridview);
        gridView.setAdapter(arrayAdapter);



    }
}
