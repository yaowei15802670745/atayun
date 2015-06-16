package com.atayun.hgs.modle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.graphics.Bitmap;
import android.os.Environment;

public class BitmapUtils {
	   /**
     * 
     * @param b Bitmap
     * @return ͼƬ�洢��λ��
     * @throws FileNotFoundException 
     */
    public static String saveImg(Bitmap b,String name) throws Exception{
            String path = Environment.getExternalStorageDirectory().getPath()+File.separator+"/temp_images";
            File mediaFile = new File(path + File.separator + name + ".jpg");
            if(mediaFile.exists()){
                    mediaFile.delete();
                    
            }
            if(!new File(path).exists()){
                    new File(path).mkdirs();
            }
            mediaFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(mediaFile);
            b.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
            b.recycle();
            b = null;
            System.gc();
            return mediaFile.getPath();
    }


}
