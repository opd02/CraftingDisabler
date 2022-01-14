package me.opd02.cd.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;

import me.opd02.cd.CraftingDisablerPlugin;

public class JSONUtils {

	public static void saveDisableList(ArrayList<String> list, CraftingDisablerPlugin plugin){
		
		Gson gson = new Gson();
		
		String data = gson.toJson(list);
		
		  try {
			  File file = new File(plugin.getDataFolder().getAbsoluteFile() + "/data.json");
			  
			   FileWriter writer = new FileWriter(file);
			   writer.write(data);
			   writer.close();
			 
			  } catch (IOException e) {
			   e.printStackTrace();
			   System.out.println("There was an error writing to file data.json");
			  }
	}
	
	public static ArrayList<String> readSavedList(CraftingDisablerPlugin plugin){
		
		Gson gson = new Gson();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(plugin.getDataFolder().getAbsoluteFile() + "/data.json"));
			@SuppressWarnings("unchecked")
			ArrayList<String> list = gson.fromJson(br, ArrayList.class);
			return list;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("There was an error reading file data.json");
		}
		
		return null;
	}
	
	public static void makeFile(CraftingDisablerPlugin plugin){
		File file = new File(plugin.getDataFolder().getAbsoluteFile() + "/data.json");
		
		if(file.exists()){
			return;
		}
		try {
			file.getParentFile().mkdir();
			file.createNewFile();
			   FileWriter writer = new FileWriter(file);
			   writer.write("[]");
			   writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
