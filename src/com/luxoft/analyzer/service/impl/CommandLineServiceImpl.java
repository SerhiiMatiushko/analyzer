package com.luxoft.analyzer.service.impl;

import com.luxoft.analyzer.service.CommandLineService;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import static com.luxoft.analyzer.Main.analyze;
import static com.luxoft.analyzer.Main.parseToStockEntity;
import static com.luxoft.analyzer.Main.readDataFromFile;
import static com.luxoft.analyzer.Main.saveResult;

public class CommandLineServiceImpl implements CommandLineService {
    private final static Map<String, String> commands = new HashMap<>();
    private final static List<String> examples = new LinkedList<>();

    public CommandLineServiceImpl() {
        init();
    }

    private void init(){
        commands.put("help", "Prints all available commands");
        commands.put("open", "Opens file for reading. Expected - path to file");
        commands.put("result", "Saves analyzed data to file. Expected - path to file");
        commands.put("analyze", "Opens, analyzes and saves results to file. Expected - paths to source and target files");
        commands.put("exit", "Shutdowns API");

        examples.add("open /home/serhii/Downloads/test/stocks.txt");
        examples.add("result /home/serhii/Downloads/test/stockResults.txt");
        examples.add("analyze /home/serhii/Downloads/test/stocks.txt /home/serhii/Downloads/test/results.txt");
    }

    public void run(){
        String inputCommand = "";
        while (!inputCommand.equals("exit")){
            System.out.println();
            System.out.println("Enter help to see available commands");
            System.out.println("Enter command:");
            readCommand(getEnteredCommand());
        }
    }

    private void readCommand(String[] commands){
        switch (commands[0]) {
            case "help":
                printHelp();
                break;
            case "open":
                parseToStockEntity(readDataFromFile(commands[1]));
                break;
            case "result":
                saveResult(commands[1]);
                break;
            case "analyze":
                analyze(commands[1], commands[2]);
                break;
            case "exit":
                System.out.println("See you soon, raccoon :)");
                System.exit(0);
            default:
                break;
        }
    }

    private String[] getEnteredCommand(){
        return new StringBuilder(new Scanner(System.in).nextLine()).toString().split(" ");
    }
    private void printHelp(){
        System.out.println("Available commands:");
        commands.forEach((key, value)-> System.out.println(" | "+key+" | "+" - "+value));
        System.out.println();
        System.out.println("Examples:");
        examples.forEach(System.out::println);
    }
}