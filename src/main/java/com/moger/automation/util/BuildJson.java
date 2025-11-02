package com.moger.automation.util;

public class BuildJson {

    //Invalid Rating
    public static String buildJsonRatingInvalid() {
        return "{\n" +
                "  \"title\": \"Beginning Python\",\n" +
                "  \"image_url\": \"https://i0.wp.com/www.jamesrobertpayne.com/wp-content/uploads/2018/02/beginning-python-james-payne.jpg?fit=399%2C500&ssl=1\",\n" +
                "  \"publicationYear\": 2010,\n" +
                "  \"publishers\": [\n" +
                "    {\n" +
                "      \"name\": \"Wiley Publishing, Inc\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"authors\": [\n" +
                "    {\n" +
                "      \"name\": \"James Payne\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"genre\": \"TECHNICAL\",\n" +
                "  \"amazonRating\": 0.3\n" +
                "}";
    }

    //Empty Title, when IDs set to zero
    public static String buildJsonEmptyTitle() {
        return "{\n" +
                "  \"title\": \"\",\n" +
                "  \"image_url\": \"https://i0.wp.com/www.jamesrobertpayne.com/wp-content/uploads/2018/02/beginning-python-james-payne.jpg?fit=399%2C500&ssl=1\",\n" +
                "  \"publicationYear\": 2010,\n" +
                "  \"publishers\": [\n" +
                "    {\n" +
                "      \"name\": \"Wiley Publishing, Inc\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"authors\": [\n" +
                "    {\n" +
                "      \"name\": \"James Payne\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"genre\": \"TECHNICAL\",\n" +
                "  \"amazonRating\": 4.3\n" +
                "}";
    }

    //with IDs'
    public static String buildJsonWithId() {
        return "{\n" +
                "  \"id\": 1,\n" +
                "  \"title\": \"Beginning Python\",\n" +
                "  \"image_url\": \"https://i0.wp.com/www.jamesrobertpayne.com/wp-content/uploads/2018/02/beginning-python-james-payne.jpg?fit=399%2C500&ssl=1\",\n" +
                "  \"publicationYear\": 2010,\n" +
                "  \"publishers\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"Wiley Publishing, Inc\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"authors\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"James Payne\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"genre\": \"TECHNICAL\",\n" +
                "  \"amazonRating\": 4.3\n" +
                "}";
    }

    //without IDs'
    public static String buildJson() {
        return "{\n" +
                "  \"title\": \"Beginning Python\",\n" +
                "  \"image_url\": \"https://i0.wp.com/www.jamesrobertpayne.com/wp-content/uploads/2018/02/beginning-python-james-payne.jpg?fit=399%2C500&ssl=1\",\n" +
                "  \"publicationYear\": 2010,\n" +
                "  \"publishers\": [\n" +
                "    {\n" +
                "      \"name\": \"Wiley Publishing, Inc\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"authors\": [\n" +
                "    {\n" +
                "      \"name\": \"James Payne\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"genre\": \"TECHNICAL\",\n" +
                "  \"amazonRating\": 4.3\n" +
                "}";
    }

    //request parameter is empty
    public static String buildJsonForAnyFieldEmpty() {
        return "{\n" +
                "  \"id\": 1,\n" +
                "  \"title\": \"Beginning Python\",\n" +
                "  \"image_url\": \"https://i0.wp.com/www.jamesrobertpayne.com/wp-content/uploads/2018/02/beginning-python-james-payne.jpg?fit=399%2C500&ssl=1\",\n" +
                "  \"publicationYear\": 2010,\n" +
                "  \"publishers\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"Wiley Publishing, Inc\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"authors\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"genre\": \"TECHNICAL\",\n" +
                "  \"amazonRating\": 4.3\n" +
                "}";
    }
}
