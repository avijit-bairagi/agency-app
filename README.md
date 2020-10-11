## agency-app
context path: http://YOUR_PORT:8999/agency/ <br />
[DB Script](https://github.com/avijit-bairagi/agency-app/blob/main/db/agency.sql) <br />
[Release - 1.0.0](https://github.com/avijit-bairagi/agency-app/releases/tag/1.0.0)

## bonus works: 
    - Pagination
    - Unpin post
    - Global error handler
    - Global error page
    
## Environment: 
    - Server: tomcat 
    - JDK: 11
    - MYSQL

# Instructions Step 1
   1. first find **agency-app\src\main\resources\application.yml** and replace the db credentials (db port, bd server url/ip, user and password)
   2. execute the shared sql script
   3. import the project then build and run the project
# Instructions Step 2
   1. find war file at release tag or create war using **gradlew war** command
   2. before deploy the war file run the db script and replace db crediential at **agency\WEB-INF\classes\application.yml** (db port, bd server port/ip, user and password)

# ER-diagram

 ![ER-Diagram](er-diagram.png)
 
 # UI

 Login
 ![Login](img-ui/login.jpg)
 
 Register
 ![Registration](img-ui/register.jpg)
  
  Create post
 ![Create Post](img-ui/create-post.jpg)
 
 Edit post
 ![Edit Post](img-ui/edit-post.jpg)
  
  Profile
 ![Profile](img-ui/profile.jpg)
 
 Public posts
 ![Public posts](img-ui/public-posts.jpg)
