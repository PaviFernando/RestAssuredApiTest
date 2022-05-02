Feature: Validate User Emails

  @validateUser
  Scenario Outline: Validate user emails
    Given User search for the user "<username>"
    And User fetch the user Id
    And User fetch the posts written by the user
    And User fetch the post Ids for each post
    When User fetch the comments for each post
    Then User validate the email in the comment are valid emails

    Examples:
      |username|
      |Delphine|

