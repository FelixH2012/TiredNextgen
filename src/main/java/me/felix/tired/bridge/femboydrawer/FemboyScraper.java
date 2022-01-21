package me.felix.tired.bridge.femboydrawer;

import java.util.ArrayList;

import org.apache.http.client.methods.HttpGet;
import org.json.JSONObject;
import org.json.XML;


public class FemboyScraper {

    public static void getAndDownloadPosts(int postAmount) {

        String request = "";
        try {
            request = NetworkManager.getNetworkManager().sendGet(new HttpGet("https://rule34.xxx/index.php?page=dapi&s=post&q=index&tags=femboy+-furry&limit=" + postAmount));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        request = XML.toJSONObject(request).toString();

        for (int i = 0; i< postAmount; i++) {

            final int threadSafeI = i;
            final String threadSafeRequest = request;

            new Thread("Femboy scraper and downloader thread") {

                public void run() {

                    try {

                        // Gets and downloads full image
                        Downloader.download(new JSONObject(new JSONObject(new JSONObject(threadSafeRequest).toString()).getJSONObject("posts").getJSONArray("post").get(threadSafeI).toString()).getString("file_url"));


                    } catch (Exception e) {

                        try {

                            // Gets and downloads sample image if the full image fails
                            Downloader.download(new JSONObject(new JSONObject(new JSONObject(threadSafeRequest).toString()).getJSONObject("posts").getJSONArray("post").get(threadSafeI).toString()).getString("sample_url"));

                        } catch (Exception e2) {

                        }

                    }

                }

            }.start();

        }

    }

}