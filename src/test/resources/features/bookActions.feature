Feature: Book CRUD operations
    As an API consumer
    I want to create, read, update, and delete books
    So that I can manage book data

    @addBook
    Scenario: Verify if a book successfully added using add book service API
        Given Add Book payload
        When Send a POST HTTP request
        Then Receive a success response with status code "200"
        And "title" in response body is "Beginning Python"

    @updateBook
    Scenario: Verify if a book successfully updated using update book service API
        Given Add Update Book payload and Id
        When Send a PUT HTTP request with <id>
        Then Receive a success response with status code "200"

        Examples:
                 | id |
                 | 5 |

    @getBook
    Scenario Outline: Send a valid request to get book details
        Given Send Book with <id>
        When Send a GET HTTP request with <id>
        Then Receive a success response with status code "<statusCode>"
        And "title" in response body is "<title>"

        Examples:
                  | id       | statusCode          | title                                |
                  | 1        | 200                 | Walden                               |
                  | 2        | 200                 | Self-Reliance and Other Essays       |

    @deleteBook
    Scenario Outline: Verify if a book successfully deleted using delete book service API
        Given Send Book with <id>
        When Send a DELETE HTTP request with <id>
        Then Receive a success response with status code "<statusCode>"
        And "message" in response body is "<message>"

        Examples:
                   | id  | statusCode | message                    |
                   | 3   | 200        | Book with id 3 is deleted  |
                   | 4   | 200        | Book with id 4 is deleted  |
