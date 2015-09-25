package android.esteban.nyc.popularmovies;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Spoooon on 8/23/2015.
 */
public class PropertyReader {


    public static String readProperty (Context context){

        final String PROPERTIES_KEY_FOR_API = "key";
        String apiProperties = null;

        Properties properties = new Properties();
        AssetManager assetManager = context.getAssets();
        try {
            InputStream inputStream = assetManager.open("API.properties");
            properties.load(inputStream);
            apiProperties = properties.getProperty(PROPERTIES_KEY_FOR_API);
        } catch (IOException e) {
            e.printStackTrace();
        }

    return apiProperties;
    }
}
