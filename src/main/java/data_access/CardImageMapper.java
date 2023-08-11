package data_access;

import javax.swing.*;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

public class CardImageMapper {

    private final Map<String, ImageIcon> nameToImage = new LinkedHashMap<>();

    public CardImageMapper(String folderPath) {
        File folder = new File(folderPath);
        if (!folder.isDirectory()) {
            throw new IllegalArgumentException("The provided path is not a valid directory.");
        }

        // A compact way to get all the images as individual File objects
        File[] imageFiles = folder.listFiles((dir, name) -> {
            String lowercaseName = name.toLowerCase();
            return lowercaseName.endsWith(".png");
        });

        if (imageFiles == null) {
            throw new RuntimeException("An error occurred while listing the image files.");
        }

        for (File imageFile : imageFiles) {
            String imageName = imageFile.getName();
            ImageIcon imageIcon = new ImageIcon(imageFile.getAbsolutePath());

            // Remove the .png extension
            String name = imageName.substring(0, imageName.length() - 4);
            // Make the name match those in the JSON files
            name = name.replace('-', ' ');
            // This is an awkward case due to our name choices, for now we just leave it here
            if (name.equals("Demons Amulet")) {
                name = "Demon's Amulet";
            }
            nameToImage.put(name, imageIcon);
        }
    }

    public ImageIcon getImageByName(String name) {
        return nameToImage.get(name);
    }
}
