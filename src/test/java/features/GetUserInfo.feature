@test @user-info
Feature: Getting user info

  Background:
    Given I have an authorization token

  @smoke @positive @me
  Scenario: Getting user info
    When I request user info
    Then I get valid user info

  @negative @me
  Scenario: Getting user info without an access token results in an error
    When I request user info without an access token
    Then I receive a response with status code 403

  @negative @me
  Scenario: Getting user info with an invalid access token results in an error
    When I request user info with access token equal to 123WrongToken
    Then I receive a response with status code 401

  @smoke @positive @karma
  Scenario: Getting user karma info
    When I request user karma info
    Then I get valid user karma info

    #This one will require an existing subreddit
  @positive @karma
  Scenario: Getting user karma info for user with karma
    When I request user karma info
    Then I get valid user karma info, where on sub subreddit comment karma equals 0 and link karma equals 0

  @negative @karma
  Scenario: Getting user karma info without an access token results in an error
    When I request user karma info without an access token
    Then I receive a response with status code 403

  @negative @karma
  Scenario: Getting user karma info with an invalid access token results in an error
    When I request user karma info with access token equal to 123WrongToken
    Then I receive a response with status code 401

  @smoke @positive @prefs
  Scenario: Getting user preferences info
    When I request user preferences info
    Then I get valid user preferences info

  @negative @prefs
  Scenario: Getting user preferences info without an access token results in an error
    When I request user preferences info without an access token
    Then I receive a response with status code 403

  @negative @prefs
  Scenario: Getting user preferences info with an invalid access token results in an error
    When I request user preferences info with access token equal to 123WrongToken
    Then I receive a response with status code 401

    #    There are a lot of different valid options in some of the preferences, but I don't see any point in testing them all in this particular project - so I cut them down to make this feature file shorter

  @smoke @positive @prefs
  Scenario: Sending request to edit user preferences without any data
    When I send a request to edit user preferences with no data
    Then I get valid user preferences info

  @positive @prefs
  Scenario: Sending request to edit all the fields in the user preferences with a valid value
    When I send a request to edit user preferences with the following data:
      | accept_pms               | everyone |
      | activity_relevant_ads    | true     |
      | allow_clicktracking      | false    |
      | bad_comment_autocollapse | off      |
      | beta                     | false    |
#    ...and so on
    Then I get valid user preferences info with changed data

  @positive @prefs
  Scenario Outline: Sending request to edit "accept_pms" setting in the user preferences with a valid value
    When I send request to change accept_pms setting in the user preferences to <value>
    Then I get valid user preferences info with changed data

    Examples:
      | value       |
      | everyone    |
      | whitelisted |

  @negative @prefs
  Scenario: Sending request to edit "accept_pms" setting in the user preferences with an invalid value results in an error
    When I send request to change accept_pms setting in the user preferences to noSuchValue
    Then I receive a response with status code 400

  @positive @prefs
  Scenario Outline: Sending request to edit "bad_comment_autocollapse" setting in the user preferences with a valid value
    When I send request to change bad_comment_autocollapse setting in the user preferences to <value>
    Then I get valid user preferences info with changed data

    Examples:
      | value  |
      | off    |
      | low    |
      | medium |
      | high   |

  @negative @prefs
  Scenario: Sending request to edit "bad_comment_autocollapse" setting in the user preferences with an invalid value results in an error
    When I send request to change bad_comment_autocollapse setting in the user preferences to noSuchValue
    Then I receive a response with status code 400

  @positive @prefs
  Scenario Outline: Sending request to edit "country_code" setting in the user preferences with a valid value
    When I send request to change country_code setting in the user preferences to <value>
    Then I get valid user preferences info with changed data

    Examples:
      | value |
      | WF    |
      | JP    |
      | JM    |
      | JO    |
      | WS    |

  @negative @prefs
  Scenario: Sending request to edit "country_code" setting in the user preferences with an invalid value results in an error
    When I send request to change country_code setting in the user preferences to noSuchValue
    Then I receive a response with status code 400

  @positive @prefs
  Scenario Outline: Sending request to edit "default_comment_sort" setting in the user preferences with a valid value
    When I send request to change default_comment_sort setting in the user preferences to <value>
    Then I get valid user preferences info with changed data

    Examples:
      | value         |
      | confidence    |
      | top           |
      | new           |
      | controversial |
      | old           |
      | random        |
      | qa            |
      | live          |

  @negative @prefs
  Scenario: Sending request to edit "default_comment_sort" setting in the user preferences with an invalid value results in an error
    When I send request to change default_comment_sort setting in the user preferences to noSuchValue
    Then I receive a response with status code 400

  @positive @prefs
  Scenario Outline: Sending request to edit "lang" setting in the user preferences with a valid value
    When I send request to change lang setting in the user preferences to <value>
    Then I get valid user preferences info with changed data

    Examples:
      | value |
      | en    |
      | es    |
      | et    |
      | eu    |

  @negative @prefs
  Scenario: Sending request to edit "lang" setting in the user preferences with an invalid value results in an error
    When I send request to change lang setting in the user preferences to noSuchValue
    Then I receive a response with status code 400

  @positive @prefs
  Scenario Outline: Sending request to edit "media" setting in the user preferences with a valid value
    When I send request to change media setting in the user preferences to <value>
    Then I get valid user preferences info with changed data

    Examples:
      | value     |
      | on        |
      | off       |
      | subreddit |

  @negative @prefs
  Scenario: Sending request to edit "media" setting in the user preferences with an invalid value results in an error
    When I send request to change media setting in the user preferences to noSuchValue
    Then I receive a response with status code 400

  @positive @prefs
  Scenario Outline: Sending request to edit "media_preview" setting in the user preferences with a valid value
    When I send request to change media_preview setting in the user preferences to <value>
    Then I get valid user preferences info with changed data

    Examples:
      | value     |
      | on        |
      | off       |
      | subreddit |

  @negative @prefs
  Scenario: Sending request to edit "media_preview" setting in the user preferences with an invalid value results in an error
    When I send request to change media_preview setting in the user preferences to noSuchValue
    Then I receive a response with status code 400

  @positive @prefs
  Scenario Outline: Sending request to edit "min_comment_score" setting in the user preferences with a valid value
    When I send request to change min_comment_score setting in the user preferences to <value>
    Then I get valid user preferences info with changed data

    Examples:
      | value |
      | -100  |
      | 0     |
      | 100   |

  @negative @prefs
  Scenario Outline: Sending request to edit "min_comment_score" setting in the user preferences with an invalid value results in an error
    When I send request to change min_comment_score setting in the user preferences to <value>
    Then I receive a response with status code 400

    Examples:
      | value |
      | -101  |
      | 101   |

  @positive @prefs
  Scenario Outline: Sending request to edit "min_link_score" setting in the user preferences with a valid value
    When I send request to change min_link_score setting in the user preferences to <value>
    Then I get valid user preferences info with changed data

    Examples:
      | value |
      | -100  |
      | 0     |
      | 100   |

  @negative @prefs
  Scenario Outline: Sending request to edit "min_link_score" setting in the user preferences with an invalid value results in an error
    When I send request to change min_link_score setting in the user preferences to <value>
    Then I receive a response with status code 400

    Examples:
      | value |
      | -101  |
      | 101   |

  @positive @prefs
  Scenario Outline: Sending request to edit "num_comments" setting in the user preferences with a valid value
    When I send request to change num_comments setting in the user preferences to <value>
    Then I get valid user preferences info with changed data

    Examples:
      | value |
      | 1     |
      | 500   |

  @negative @prefs
  Scenario Outline: Sending request to edit "num_comments" setting in the user preferences with an invalid value results in an error
    When I send request to change num_comments setting in the user preferences to <value>
    Then I receive a response with status code 400

    Examples:
      | value |
      | -1    |
      | 0     |
      | 501   |

  @positive @prefs
  Scenario Outline: Sending request to edit "numsites" setting in the user preferences with a valid value
    When I send request to change numsites setting in the user preferences to <value>
    Then I get valid user preferences info with changed data

    Examples:
      | value |
      | 1     |
      | 100   |

  @negative @prefs
  Scenario Outline: Sending request to edit "numsites" setting in the user preferences with an invalid value results in an error
    When I send request to change numsites setting in the user preferences to <value>
    Then I receive a response with status code 400

    Examples:
      | value |
      | -1    |
      | 0     |
      | 101   |

  @negative @prefs
  Scenario: Patching user preferences info without an access token results in an error
    When I send request to patch user preferences info without an access token
    Then I receive a response with status code 403

  @negative @prefs
  Scenario: Patching user preferences info with an invalid access token results in an error
    When I send request to patch user preferences info with access token equal to 123WrongToken
    Then I receive a response with status code 401