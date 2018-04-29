package co.devhack.musicapp.helpers;

import java.util.List;

import co.devhack.musicapp.domain.model.Image;

/**
 * Created by krlosf on 25/04/18.
 */

public class LastFmUtilities {

    public static class ImageSize {
        public static final String SMALL = "small";
        public static final String MEDIUM = "medium";
        public static final String LARGE = "large";
    }

    public static Image getImageBySize(List<Image> lstImages, String size) {
        if(lstImages != null) {
            for (Image image : lstImages) {
                if (image.getSize().equals(size)) {
                    return image;
                }
            }
        }

        return null;
    }

}
