Feature: Search for a product

    Scenario:
        Given a user goes to (domain)/search?=smartphone by typing in smartpone in search box
        When the user clicks the search button
        Then the user is redirected to endpoint results


