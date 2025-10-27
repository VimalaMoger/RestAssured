package com.moger.automation.util;

public class BuildJson {

    //with IDs'
    public static String buildJsonWithId() {
        return "{\n" +
                "  \"id\": 1,\n" +
                "  \"title\": \"Walden\",\n" +
                "  \"image_url\": \"https://images.gr-assets.com/books/1465675526l/16902.jpg\",\n" +
                "  \"publicationYear\": 1854,\n" +
                "  \"publishers\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"Wilder\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"authors\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"Henry David Thoreau\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"genre\": \"PHILOSOPHY\",\n" +
                "  \"amazonRating\": 4.3\n" +
                "}";
    }

    //without IDs'
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
                "  \"amazonRating\": 4.3\n" +
                "}";
    }
}
