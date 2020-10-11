## agency-app
context path: http://YOUR_PORT:8999/agency/ <br />
[DB Script](https://github.com/avijit-bairagi/agency-app/blob/main/db/agency.sql)

## bonus works: 
    - Pagination
    - Unpin post
    - Global error handler
    - Global error page
    
## Environment: 
    - Server: tomcat 
    - JDK: 11
    - MYSQL

# Instructions
    ### Step 1
        - replace db crediential at application.yml file (db port, bd server url/ip, user and password)
        - execute the shared sql script
        - import and run the project
    ### Step 2
        - find the shared war file
        - before deploy the war file run the db script and replace db crediential (db port, bd server port/ip, user and password)

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
