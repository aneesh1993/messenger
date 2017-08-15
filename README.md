# messenger
Messaging REST API - Maintains User Profiles, User Messages, Comments and Shares. MySQL Database for data storage. JAX-RS framework. 

Database name = messenger
  - Table = profiles
    - Schema => id | profile_name | first_name | last_name | created
    
  - Table = messages
    - Schema => id | message | created | author_id | author
