package restart.com.gridviewdemo.three;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2018/1/20.
 */

public class ImageInfo {
    private String imagePath;
    private Bitmap bitmap;
    private String text;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
