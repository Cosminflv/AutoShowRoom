package com.example.autoshowroom.utils;

import android.content.Context;
import android.util.Log;

import com.example.autoshowroom.R;
import com.example.autoshowroom.entities.CarBrand;
import com.example.autoshowroom.entities.CarModel;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XMLCarBrandParser {
    private ArrayList<CarBrand> carBrands = new ArrayList<>();

    public XMLCarBrandParser(Context context) {
        try {
            // Open the XML file from raw resources (ensure it's named 'cars.xml' and placed in res/raw)
            InputStream stream = context.getResources().openRawResource(R.raw.input);
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document xmlDoc = builder.parse(stream);

            // Get all the CarBrand elements (assuming each car brand is enclosed in a <CarBrand> tag)
            NodeList brandNodes = xmlDoc.getElementsByTagName("CarBrand");

            // Loop through each CarBrand node
            for (int i = 0; i < brandNodes.getLength(); i++) {
                Element brandElement = (Element) brandNodes.item(i);

                // Extract basic car brand info
                String name = brandElement.getElementsByTagName("Name").item(0).getTextContent();
                String imagePath = brandElement.getElementsByTagName("ImagePath").item(0).getTextContent();
                int foundedYear = Integer.parseInt(brandElement.getElementsByTagName("FoundedYear").item(0).getTextContent());
                String country = brandElement.getElementsByTagName("Country").item(0).getTextContent();
                String url = brandElement.getElementsByTagName("Url").item(0).getTextContent();

                // Parse the nested CarModel nodes within the <Cars> element
                NodeList carsList = brandElement.getElementsByTagName("Cars");
                List<CarModel> carModels = new ArrayList<>();
                if (carsList.getLength() > 0) {
                    Element carsElement = (Element) carsList.item(0);
                    NodeList carModelNodes = carsElement.getElementsByTagName("CarModel");

                    for (int j = 0; j < carModelNodes.getLength(); j++) {
                        Element carModelElement = (Element) carModelNodes.item(j);

                        String modelName = carModelElement.getElementsByTagName("Name").item(0).getTextContent();
                        String modelImagePath = carModelElement.getElementsByTagName("ImagePath").item(0).getTextContent();
                        String description = carModelElement.getElementsByTagName("Description").item(0).getTextContent();

                        // Create a new CarModel object and add it to the list
                        CarModel model = new CarModel(modelName, modelImagePath, description);
                        carModels.add(model);
                    }
                }

                // Create the CarBrand object with all parsed values and add it to the list
                CarBrand brand = new CarBrand(name, imagePath, foundedYear, country, url, carModels);
                carBrands.add(brand);
            }
        } catch (Exception e) {
            Log.d("DEBUG++>", "CANNOT PARSE: " + e.getMessage());
        }
    }

    public ArrayList<CarBrand> getCarBrands() {
        return carBrands;
    }
}
