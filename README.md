# Tests for Reddit API

These are tests for some of the methods in the Reddit API, specifically for `/api/v1/me`, `/api/v1/me/karma`
and `/api/v1/me/prefs`.

A little disclaimer:

In the real project I would go about testing quite differently. For instance, I would automate the whole process
starting with creating a new user and all needed testing data, so that every test would start from the blank slate and
not utilize preexisting data.

### Test execution

#### Components

- Open JDK 11 (for example, Coretto: https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html)
- Maven (https://maven.apache.org/install.html)

#### Preparations

To run these tests you'll need a properties file named `config.properties` in the project's root directory. 

There is a template file named `example.properties` which you'll need to rename to `config.properties` and fill with your own data. In order to do that you'll need a Reddit account credentials plus client-id and client-secret for the Reddit script app. You can create an app here https://www.reddit.com/prefs/apps

#### Execution

To execute all the tests, run the following command in the project's root directory:

```
    mvn clean test
```

To run tests marked with certain tags, specify it by using the following prompt:

```
    mvn clean test -Dcucumber.filter.tags=@test
```

where:

`-Dcucumber.filter.tags=@positive` runs only the tests with `@positive` tag;

`-Dcucumber.filter.tags='@positive or @prefs'` runs all tests that have either `@positive` or `@prefs` tag;

`-Dcucumber.filter.tags='@positive and @prefs'` runs only the tests that have `@positive` and `@prefs` tags at the same time.

#### Tags

All tests have at least one tag that allow you to run specific groups of tests.

- `@test` - all tests
- `@user-info` - tests in *GetUserInfo* feature
- `@smoke` - most basic positive tests to verify that everything works at least to some extent
- `@positive` - positive tests
- `@negative` - negative tests
- `@me` - tests for */api/v1/me*
- `@karma` - tests for */api/v1/me/karma*
- `@prefs` - tests for */api/v1/me/prefs*

#### Reports

You can access test reports by executing the following prompt in the project's root directory:

```
    mvn allure:serve
```

The report wil be opened automatically in your default browser.

Apart from that you can find data regarding the response times of the latest test run in the log file. Logs are stored in the *target* directory in *test.log* file.