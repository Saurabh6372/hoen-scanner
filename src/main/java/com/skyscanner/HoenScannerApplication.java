package com.skyscanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HoenScannerApplication extends Application<HoenScannerConfiguration> {

    public static void main(final String[] args) throws Exception {
        new HoenScannerApplication().run(args);
    }

    @Override
    public String getName() {
        return "hoen-scanner";
    }

    @Override
    public void initialize(final Bootstrap<HoenScannerConfiguration> bootstrap) {

    }

    @Override
    public void run(final HoenScannerConfiguration configuration, final Environment environment) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<SearchResult> carResults = Arrays.asList(
                mapper.readValue(
                        getClass().getClassLoader().getResource("rental_cars.json"),
                        SearchResult[].class
                )
        );
        List<SearchResult> hotelResults = Arrays.asList(
                mapper.readValue(
                        getClass().getClassLoader().getResource("hotels.json"),
                        SearchResult[].class
                )
        );
        List<SearchResult> searchResults = new ArrayList<>();
        // The source files only carry "city" and "title", so tag each result's
        // "kind" based on the file it came from.
        for (SearchResult car : carResults) {
            searchResults.add(new SearchResult(car.getCity(), car.getTitle(), "rental car"));
        }
        for (SearchResult hotel : hotelResults) {
            searchResults.add(new SearchResult(hotel.getCity(), hotel.getTitle(), "hotel"));
        }
        final SearchResource resource = new SearchResource(searchResults);
        environment.jersey().register(resource);
    }

}
