Feature: Get users name

    Scenario:
        Given a user goes to (domain)/users/456/name
        When the user enters a valid API_KEY
        Then the user gets the name of the user with the ID 456 which is "Alice"