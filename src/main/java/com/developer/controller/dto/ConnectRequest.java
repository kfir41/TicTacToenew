package com.developer.controller.dto;

import com.developer.model.Player;
import lombok.Data;

@Data
public class ConnectRequest {
    private Player player;
    private String gameId;
}
