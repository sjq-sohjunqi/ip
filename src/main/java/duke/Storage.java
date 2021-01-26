package duke;

import java.util.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

public class Storage {
	
	private File f;
	
	public Storage(String filename) {
		f = new File(filename);
	}
	
	public TaskList loadTaskList() {
		
		try {
			if (f.createNewFile()) {
				return new TaskList();
			} else {
				
				BufferedReader br = new BufferedReader(new FileReader(f));
				
				TaskList tl = new TaskList();
				
				String line;
				while((line = br.readLine()) != null) {
					String[] split = line.split("\\|");
					
					// Create new task for each line
					char type = split[0].charAt(0);
					
					boolean done = false;
					if (split[1].equals("D")) {
						done = true;
					}
					
					String name = split[2];
					
					if (type == 'D' || type == 'E') {
						LocalDate dateTime = LocalDate.parse(split[3]);
						
						Task t = new Task(name, type, dateTime);
						if (done)
							t.mark();
						
						tl.add(t);
						
					} else {
						Task t = new Task(name);
						if (done)
							t.mark();
						
						tl.add(t);
					}
				}
				
				return tl;
					
				
			}
			
		} catch(IOException e) {
			System.out.println(e.toString());
			return new TaskList();
		}
		
	}
	
	public void saveTaskList(TaskList tl){
		
		try {
			FileWriter fw = new FileWriter(f, false);
			
			for (int i = 0; i < tl.count(); i++) {
				
				Task t = tl.getTask(i);
				
				String line = "" + t.getType() + "|";
				if (t.getDone()) {
					line += "D|";
				} else {
					line += "ND|";
				}
				
				line += t.getName();
				
				if (t.getType() == 'D' | t.getType() == 'E') {
					line += "|" + t.getDate();
				}
				
				line += "\n";
				
				fw.write(line);
				
			}
			
			fw.flush();
			fw.close();
			
		} catch (IOException e) {
			System.out.println("Error writing to file!");
			
		}
		
	}
	
}