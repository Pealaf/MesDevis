package com.mesdevis.service.mapper;

import com.mesdevis.entity.Client;
import com.mesdevis.entity.dto.ClientDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ClientMapper {

    private static final Logger log = LoggerFactory.getLogger(ClientMapper.class);

    public ClientDTO toDto (Client client) {
        if (client == null) { return null; }
        ClientDTO clientDTO = new ClientDTO();

        clientDTO.setId(client.getId());
        clientDTO.setUtilisateur(client.getUtilisateur());

        return clientDTO;
    }

    public Client toEntity (ClientDTO clientDTO) {
        if (clientDTO == null) {
            return null;
        }
        Client client = new Client(clientDTO.getId(), clientDTO.getUtilisateur());
        return client;
    }
}
