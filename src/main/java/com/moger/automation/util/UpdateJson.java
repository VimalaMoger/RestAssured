package com.moger.automation.util;

public class UpdateJson {

    public static String buildJson() {

        return "{\n" +
                "  \"title\": \"Walden\",\n" +
                "  \"image_url\": \"https://images.gr-assets.com/books/1465675526l/16902.jpg\",\n" +
                "  \"publicationYear\": 1854,\n" +
                "  \"publishers\": [\n" +
                "    {\n" +
                "      \"name\": \"Wilder\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"authors\": [\n" +
                "    {\n" +
                "      \"name\": \"Henry David Thoreau\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"genre\": \"PHILOSOPHY\",\n" +
                "  \"amazonRating\": 5\n" +
                "}";

    }
}
