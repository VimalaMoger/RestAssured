Feature: Add a Book

Scenario: Verify if a book successfully added using addBook API

    Given book payload
    When user requests AddBook API with Post http request
    Then The API call got success with status code 200
    And "title" in response body is "Beginning Python"