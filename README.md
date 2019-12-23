# Analyzer
###Solved test task for Luxoft

API for analyzing financial data.

Used technologies:
    Java 8,
    JUnit 5,
    OpenTest4j 1.2
 
Avialable commands:
 * help    - Prints all available commands
 * open    - Opens file for reading. Expected - path to file.
 * result  - Saves analyzed data to file. Expected - path to file
 * analyze - Opens, analyzes and saves results to file. Expected - paths to source and target files
 * exit    - Shutdowns API
 
Example:
 * analyze /home/serhii/Downloads/test/stocks.txt /home/serhii/Downloads/test/results.txt

Examples of source and result files: 
~/resources/stocks.txt
~/resources/myResults.txt

Compiled code (analyzer.jar):
https://github.com/SerhiiMatiushko/analyzer/blob/master/analyzer.jar