
In the folder **des_files** in the project path **"src/main/resources/des_files"** there are three mysql scripts: <br>
1.**schema.sql** - represents sql script for creating database and table for the application. <br>
    To run this script go to your mysql command line interface using command_prompt or terminal and execute command between quotation marks "mysql -u {username} -p <{path to the file}\schema.sql". <br>
    Value {username}  replace with your database username  and {path to the file} replace with path to the file on your computer then execute command. <br>
    Executing this command you will create database aurora_new and all tables.<br>
2.**procedure.sql** 
    To run this script go to your mysql command line interface using command_prompt or terminal and execute command between quotation marks "mysql -u {username} -p aurora_new <{path to the file}\procedure.sql". <br>
    Value {username}  replace with your database username  and {path to the file} replace with path to the file on your computer then execute command. <br>
    Executing this command you will create procedures in aurora_new database. <br>
    
3.**report.sql** <br>
To run this script go to your mysql command line interface using command_prompt or terminal and execute command between quotation marks "mysql -u {username} -p aurora_new <{path to the file}\report.sql". <br>
    Value {username}  replace with your database username  and {path to the file} replace with path to the file on your computer then execute the command. <br>
    Executing this command you will return query values <br>

-Executing the sql scripts can also be done in the GUI interface by opening and separately executing the contents of the files. <br>
-Files schema.sql and procedure.sql are integrated into application and they will be executed when starting the application. <br>

**Prerequisites for running application:** <br>
    -Downloaded application code from repository <br>
    -Installed and configured Java JDK 17 <br>
    -MySql server with created database under name "aurora_new", with or without tables and procedures (tables and procedures will be created from sql script on application start) <br>
    -Database and username can be changed in code in file application.properties (path:scr/main/resources/application.properties). Parameter containing database username is "spring.datasource.username",
    parameter containing database password "spring.datasource.password".
**Creating an executable application file:** navigate to a folder that contains application code (in my case AuroraTest-master) through command line interface and execute command between quotation marks "mvn clean package". <br>
After that in the same folder is created a new folder under name "target", in the target folder is created executable java jar file. <br>
To start the application navigate to the target folder and  run the command under quotation marks "java -jar {jar_file_name}.jar", command should contain jar file extension.
You can find postman collection for using application endpoints under name test.postman_collection.json <br>
On the git repository is stored application made by requested specifications: <br>
    **Mandatory endpoints:** <br>
        -_Distribute prize_ (call: localhost:8082/api/tournament/distributePrize?tournament_id=1) to call the endpoint you should have the application up and running . Table tournaments should have row for created tournament,
         table players should have at least one player created, table bets should contain at least one bet for created player and turnament.Example of the endpoint call is in the provided Postman collection under name DistributePrize.
          Endpoint receive one parameter tournament_id and starts distribution procedure. <br>
         - _GetPlayerRanking_ (call: localhost:8082/api/player/getPlayerRanking) to call end point you should have the application up and running . Table players should have at least one row with player values. <br>
   ** Other endpoints:**
        -_Scoreboar_d (call: localhost:8082/api/scoreboard?tournament_id=11) Endpoint returns player scoreboard, endpoint accepts one parameter tournament_id and returns player scoreboard for that tournament.<br>
        -_GetPlayerById_ (call: localhost:8082/api/player?player_id=1) Endpoint returns player data and accepts one parameter player_id. <br>
        -_GetTournamentById_ (call: localhost:8082/api/tournament?tournament_id=1) Endpoint accepts one parameter tournament_id and returns tournament data. <br>
        -_CreateTournament_ (call: localhost:8082/api/tournament/create) Endpoint accepts json body containing data about tournament, on successful call  response  endpoint returns data about created tournament. <br>
        -Bet (call:localhost:8082/api/bet) endpoint accepts json body containing data about bet,on successful call  response endpoint returns data about created bet. <br>
        -_CreatePlayer_ (call:localhost:8082/api/player/create) endpoint accepts json body containing data about player,on successful call  endpoint response  returns data about created player. <br>

-When creating the platform, in addition to the mandatory tables, I created two more tables, the bet and the tournament_scoreboard table. <br>
The bet table is a table that contains all the transactions or bets that the player makes, data from this table is used for evaluating the player rank when distributing prices.
As this table has a large amount of inserts and can become very large in size, the tournament_scoreboard table was created, which contains historical data on the ranking of players in the tournament. <br>
Once the tournament is finished, after the prize distribution player and their rankings are inserted in the tournament_scoreboard table. This table is used for querying data, if tournament is finished data will be pulled from this table
and if the tournament is ongoing and player ranking is constantly changed data will be calculated and pulled from the bet table. <br>
-When creating the query and stored procedure i tried to make it simple, if i had a larger task as prize distribution i made a few smaller subtasks like checking if parameter value exists in database, if tournament prizes are already distributed. <br>
After that, through the transaction, I ensured that in the event of an error, the process will not be executed partially and will be terminated, ranking data are generated and inserted in tournament_scoreboard table
and from that table data players account_balance is increased by the prize won in the tournament. In tournaments table prize_distributed flag value is set to  1 indicating that prize distribution is finished successfully. <br>

-While writing queries using CTE-s i learned about their impact on query readability, separating parts of the query in the CTE makes it easier to read and understand. CTE-s are reusable, once written CTE can be used/joined multiple time in query
or can be used in other CTE-s, this is very helpful when writing larger more complex query with more joined tables or when you need to join one or more tables multiple times. 
CTE replaces subquery making query readable and makes a positive impact on query performance. <br>
-While using Window functions I learned about the possibility of generating data for each row over a specific window/set of rows, ordered by some value or values. Window functions are used to generate ranking data as i used to generate ranking report for Players, also you can use Aggregate functions with them calculating sum or average value in the specific set of data. <br>
 Window functions  make query simpler requiring less subquery-s and self joins which leads to less complex query, performing better when working on larger amounts of data.
 <br>
-While writing queries/stored_procedures i tried to improve their performance by selecting only necessary columns, join only needed tables using defined relations between tables and indexing columns used for ordering or filtering data or have potential for future use in filtering or indexing.  <br>
I learned that at the time of designing the structure of the tables, it is necessary to pay attention to both the inclusion of the necessary data and their use. It is necessary to determine the amount as well as the way of using the data stored in the tables in order to create an optimal structure according to their purpose. <br>


