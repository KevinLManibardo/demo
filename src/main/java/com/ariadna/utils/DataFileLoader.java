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

    public void loadFiles(String type, String path, boolean asynchronous) {
        final File folder = new File(path);
        loadFilesForFolder(folder, type, asynchronous);
    }

    private void loadFilesForFolder(final File folder, String type, boolean asynchronous) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isFile()) {
                if(asynchronous) {
                    new Thread() {
                        public void run() {
                            loadDataFiles(fileEntry, type);
                        };
                    }.start();
                } else loadDataFiles(fileEntry, type);
            }
        }
    }  

    private void loadDataFiles(File fileEntry, String type) {
        System.out.println("Loading " + fileEntry + "...");
        if(type == "event") loadEventFile(fileEntry);
        if(type == "source") loadSourceFile(fileEntry);
        System.out.println("Finished loading " + fileEntry + "!");
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
