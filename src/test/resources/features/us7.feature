@us7
Feature: Books module
  As a students, I should be able to borrow book

  @ui @db
  Scenario: Student borrow new book
    Given I login as a "student"
    And I navigate to "Books" page
    And I search book name called "Kasimir Stewart"
    When I click Borrow Book
    Then  verify that book is shown in "Borrowing Books" page
    And  verify logged student has same book in database