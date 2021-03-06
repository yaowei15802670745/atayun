package com.atayun.hgs.modle;

import java.io.FileNotFoundException;

import android.util.Log;

import com.alibaba.sdk.android.oss.OSSService;
import com.alibaba.sdk.android.oss.OSSServiceProvider;
import com.alibaba.sdk.android.oss.callback.GetFileCallback;
import com.alibaba.sdk.android.oss.callback.SaveCallback;
import com.alibaba.sdk.android.oss.model.AccessControlList;
import com.alibaba.sdk.android.oss.model.OSSException;
import com.alibaba.sdk.android.oss.model.ResumableTaskOption;
import com.alibaba.sdk.android.oss.storage.OSSBucket;
import com.alibaba.sdk.android.oss.storage.OSSFile;
import com.alibaba.sdk.android.oss.util.OSSLog;


/**
 * @author: zhouzhuo<yecan.xyc@alibaba-inc.com>
 * Apr 3, 2015
 *
 */
public class GetAndUploadFileDemo {

	private static String TAG = "GetAndUploadFileDemo";
	boolean flag=false;
	private OSSService ossService;
	private OSSBucket bucket;
	public void show() {
//	public boolean show(String picurl,String object) {
		ossService = OSSServiceProvider.getService();
		bucket = ossService.getOssBucket("hgs");

		// 文件的常规操作如普通上传、下载、拷贝、删除等，与Data类一致，故这里只给出断点下载和断点上传的demo
		//		resumableDownloadWithSpecConfig();
		//		delay();
//		boolean ss=resumableUpload(picurl,object);
//	
//		delay();
				resumableDownload();
//				delay();
//		return ss;
	}

	public void delay() {
		try {
			Thread.sleep(30 * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 断点上传
	public boolean resumableUpload(String picurl,String object) {
//		boolean flag=false;
//		String object="portraitImages"+"/"+picname;
		OSSFile bigfFile = ossService.getOssFile(bucket, object);
		try {
		
			bigfFile.setUploadFilePath(picurl, "jpg");
			bigfFile.ResumableUploadInBackground(new SaveCallback() {

				@Override
				public void onSuccess(String objectKey) {
					Log.d(TAG, "[onSuccess] - " + objectKey + " upload success!");

                                flag=true;
                       Log.d("asasa", "图片上传成功");         

				}

				@Override
				public void onProgress(String objectKey, int byteCount, int totalSize) {
					Log.d(TAG, "[onProgress] - current upload " + objectKey + " bytes: " + byteCount + " in total: " + totalSize);
				}

				@Override
				public void onFailure(String objectKey, OSSException ossException) {
					Log.d("aaa", "sssssssssssssssssss");
					Log.e(TAG, "[onFailure] - upload " + objectKey + " failed!\n" + ossException.toString());
					ossException.printStackTrace();
					ossException.getException().printStackTrace();
				}
			});
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return flag;
	}

	// 断点下载
	public void resumableDownload() {
		OSSFile bigFile = ossService.getOssFile(bucket, "portraitImages/MT2015061459070.jpg");
		bigFile.ResumableDownloadToInBackground("/sdcard/atayun/tou/test.jpg", new GetFileCallback() {

			@Override
			public void onSuccess(String objectKey, String filePath) {
				Log.d(TAG, "[onSuccess] - " + objectKey + " storage path: " + filePath);
				Log.d("ddd", "图片下载成功");
			}

			@Override
			public void onProgress(String objectKey, int byteCount, int totalSize) {
				Log.d(TAG, "[onProgress] - current download: " + objectKey + " bytes:" + byteCount + " in total:" + totalSize);
			}

			@Override
			public void onFailure(String objectKey, OSSException ossException) {
				Log.e(TAG, "[onFailure] - download " + objectKey + " failed!\n" + ossException.toString());
				ossException.printStackTrace();
			}
		});
	}

	// 设置相关参数的断点续传
	public void resumableDownloadWithSpecConfig() {
		OSSFile bigFile = ossService.getOssFile(bucket, "bigFile.dat");
		ResumableTaskOption option = new ResumableTaskOption();
		option.setAutoRetryTime(2); // 默认为2次，最大3次
		option.setThreadNum(2); // 默认并发3个线程，最大5个
		bigFile.ResumableDownloadToInBackground("/storage/sdcard0/src_file/bigFile.dat", new GetFileCallback() {

			@Override
			public void onSuccess(String objectKey, String filePath) {
				Log.d(TAG, "[onSuccess] - " + objectKey + " storage path: " + filePath);
			}

			@Override
			public void onProgress(String objectKey, int byteCount, int totalSize) {
				Log.d(TAG, "[onProgress] - current download: " + objectKey + " bytes:" + byteCount + " in total:" + totalSize);
			}

			@Override
			public void onFailure(String objectKey, OSSException ossException) {
				Log.e(TAG, "[onFailure] - download " + objectKey + " failed!\n" + ossException.toString());
				ossException.printStackTrace();
			}
		});
	}
}
