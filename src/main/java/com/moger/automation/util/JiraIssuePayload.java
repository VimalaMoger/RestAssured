package com.moger.automation.util;

public class JiraIssuePayload {

    //Jira issue details
    public static String payload () {

        return "{\n" +
                "  \"fields\": {\n" +
                "  \"project\": {\n" +
                "  \"key\": \"SCRUM\"\n" +
                "  },\n" +
                "  \"summary\": \"Adding the test results screenshot.\",\n" +
                "  \"issuetype\": {\n" +
                "  \"name\": \"Bug\"\n" +
                "  }\n" +
                "  }\n" +
                "}";
    }

    //Update payload
    public static String updatePayload () {

        return "{\n" +
                "  \"fields\": {\n" +
                "  \"project\": {\n" +
                "  \"key\": \"SCRUM\"\n" +
                "  },\n" +
                "  \"summary\": \"Updating issue summary.\",\n" +
                "  \"issuetype\": {\n" +
                "  \"name\": \"Bug\"\n" +
                "  }\n" +
                "  }\n" +
                "}";
    }
}
