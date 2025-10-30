package com.moger.automation.util;

public class BuildJson {

    //Invalid Rating
    public static String buildJsonRatingInvalid() {
        return "{\n" +
                "  \"id\": 0,\n" +
                "  \"title\": \"\",\n" +
                "  \"image_url\": \"https://images.gr-assets.com/books/1465675526l/16902.jpg\",\n" +
                "  \"publicationYear\": 1854,\n" +
                "  \"publishers\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"Wilder\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"authors\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"Henry David Thoreau\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"genre\": \"PHILOSOPHY\",\n" +
                "  \"amazonRating\": 0.3\n" +
                "}";
    }

    //Empty Title, when IDs set to zero
    public static String buildJsonEmptyTitle() {
        return "{\n" +
                "  \"id\": 0,\n" +
                "  \"title\": \"\",\n" +
                "  \"image_url\": \"https://images.gr-assets.com/books/1465675526l/16902.jpg\",\n" +
                "  \"publicationYear\": 1854,\n" +
                "  \"publishers\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"Wilder\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"authors\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"Henry David Thoreau\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"genre\": \"PHILOSOPHY\",\n" +
                "  \"amazonRating\": 4.3\n" +
                "}";
    }

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

    //without IDs'
    public static String buildJsonWithoutTitle() {
        return "{\n" +
                "  \"title\": \"\",\n" +
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

    //request parameter is empty
    public static String buildJsonForAnyFieldEmpty() {
        return "{\n" +
                "  \"id\": 0,\n" +
                "  \"title\": \"Walden\",\n" +
                "  \"image_url\": \"https://images.gr-assets.com/books/1465675526l/16902.jpg\",\n" +
                "  \"publicationYear\": 1854,\n" +
                "  \"publishers\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"Wilder\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"authors\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"genre\": \"PHILOSOPHY\",\n" +
                "  \"amazonRating\": 0.1\n" +
                "}";
    }
}
