package com.questglobal.SmartHome.services;

import com.questglobal.SmartHome.models.Command;
import com.questglobal.SmartHome.repositories.CommandRepository;
import com.questglobal.SmartHome.services.impl.ICommandService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommandService implements ICommandService {
    private final CommandRepository commandRepository;

    public CommandService(CommandRepository commandRepository) {
        this.commandRepository = commandRepository;
    }

    @Transactional
    public List<String> getAllTopics() {

        List<Command> allCommandsFromDB = commandRepository.findAll();

        List<String> allCommandTopics = allCommandsFromDB.stream().map(Command::getTopic).collect(Collectors.toList());

        return allCommandTopics;

    }
}