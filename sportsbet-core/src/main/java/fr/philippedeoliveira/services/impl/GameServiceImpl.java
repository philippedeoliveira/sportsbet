package fr.philippedeoliveira.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.philippedeoliveira.beans.Game;
import fr.philippedeoliveira.dao.IGameDAO;
import fr.philippedeoliveira.services.IGameService;

@Service
public class GameServiceImpl implements IGameService {

    @Inject private IGameDAO gameDAO; 
    
    @Override
    @Transactional
    public List<Game> getAllGames() {
        return gameDAO.findGamesAndSort();
    }

    @Override
    public List<String> getAllTeams() {
        return gameDAO.findAllTeams();
    }

    public void setGameDAO(IGameDAO gameDAO) {
        this.gameDAO = gameDAO;
    }

}
