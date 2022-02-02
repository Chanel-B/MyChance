package com.controller;

import com.model.Player;
import com.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PlayerController {


    @Autowired
    private PlayerRepository playerRepository;

    private List<String> players = new ArrayList<>();

    @GetMapping("/player")
    public String handleGetRequest(Model model) {
        model.addAttribute("players", players);
        return "index";
    }

    @PostMapping("/player")
    public String handlePostRequest(String playerName) {
        //list of players
        players.add(playerName.toString());

        //save player in db
        setPlayerName(playerName);

        //redirect the home page to it self to show
        return "redirect:/player";
    }

    private void setPlayerName(String playerName) {
        Player player = new Player();
        player.setPlayerName(playerName);
        playerRepository.save(player);
    }

}
