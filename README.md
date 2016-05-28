# Infection

This project looks at students and teachers (collectively dubbed ‘participants’ for our purposes) who are taking courses on a website. When the website version changes, the change is slowly rolled out to participants via one of three types of “infections”. In this project’s User class, all newly created users default to seeing website version “1”. If a user is infected with a new version of the website, their version number changes to “2”. (Courses have a website version number as well, but this is used as a marker to note which courses already have at least some users who have been infected.)
The User and Course classes contain parameters for those objects, while the Creator class creates new users, new courses, and pairs up all users with all of the courses they are studying or teaching. “User” has two inherited classes, “Teacher” and “Student”, which are not fleshed out in this program because the infection methods treat them as being the same.
The Infection class presents three ways which a user may be “infected” by a new website version: 
•	“Total Infection”, where we start with a random user, infect him, all the courses he is taking (or teaching), everyone in each of those courses, all the courses those users are taking/teaching, so forth, ending when all of that user’s connections have been infected.
•	In “Limited Infection”, we take a random user, infect him, all the courses he is taking, everyone in each of those courses, so forth, until we hit a desired number of infected users, after which the method finishes and returns. (There is also a note about how we might complete a less-abrupt limited infection: once we hit a target infection number, allow the method to move through the rest of the course currently being infected, before shutting off.)
•	 “Limited Infection Percent” is a supporting method which allows us to infect a percentage of our total number of users, rather than an integer.
These methods can be called from the “main” class (where all three currently appear but two are commented off). The number “2” in each of these method calls refers to the website number that we are infecting users with. The other numbers in the calls represent the total number of users we want to infect (limitedInfection) or the percentage of users we want to infect (limitedInfectionPercent). (totalInfection automatically infects all of a user’s connections, and so does not need a target infection number in order to run. It does, however, return the total number of people who end up infected.)
The Infection Test class creates mock users, courses, and user-course pairings. It tests:
•	Total infection: a generic total infection starting from a random user
•	Total infection of a user who is not connected to any other user 
•	Total infection when the starting user is null 
•	Total infection when the starting user takes no classes
•	Limited infection: a generic limited infection starting from a random user
•	Limited infection when the starting user is null 
•	Limited infection when the starting user takes no classes
•	Limited infection which affects no users
•	Limited infection which affects one user (the starting user)
•	Limited infection which affects all of the starting user’s connections 
•	Limited infection which affects every single website user 
•	Limited infection in which the target number is greater than the total number of users
•	Limited infection percent: infect 100% of total users
•	Limited infection percent: infect 50% of total users
•	Limited infection percent: infect 25% of total users
