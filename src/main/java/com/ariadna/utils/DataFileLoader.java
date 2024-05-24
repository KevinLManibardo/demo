package com.ariadna.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ariadna.domain.repositories.EventsRepository;
import com.ariadna.infrastructure.models.EventModel;
import com.ariadna.infrastructure.models.SourceModel;

@Component
public class DataFileLoader {
    private final EventsRepository eventsRepository;
    @Autowired
    public DataFileLoader(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    public void loadFiles(String type, String path) {
        final File folder = new File(path);
        loadFilesForFolder(folder, type);
    }

    private void loadFilesForFolder(final File folder, String type) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isFile()) {
                new Thread() {
                    public void run() {
                        System.out.println("Loading " + fileEntry + "...");
                        if(type == "event") loadEventFile(fileEntry);
                        if(type == "source") loadSourceFile(fileEntry);
                        System.out.println("Finished loading " + fileEntry + "!");
                    };
                }.start();
            }
        }
    }  
    
    private void loadEventFile(File file) {
        List<EventModel> eventList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                EventModel event = new EventModel(
                    Long.parseLong(data[0]),
                    Long.parseLong(data[1]),
                    Long.parseLong(data[2]),
                    Long.parseLong(data[3])
                    );
                eventList.add(event);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        eventsRepository.saveEvents(eventList);
    }

    private void loadSourceFile(File file) {
        List<SourceModel> sourceList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                SourceModel source = new SourceModel(
                    Long.parseLong(data[0]),
                    data[1]
                );
                sourceList.add(source);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        eventsRepository.saveSources(sourceList);
    }
}
