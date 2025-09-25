Feature: new user

    Scenario: Create a new user through web form
        Given a user goes to the endpoint (domain)/create/user
        When the user enters form data
        Then the forms gets transformed into an object and then put into the DB