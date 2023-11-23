package com.mesdevis.service.impl;

import com.mesdevis.entity.Client;
import com.mesdevis.entity.dto.ClientDTO;
import com.mesdevis.repository.ClientRepository;
import com.mesdevis.service.ClientService;
import com.mesdevis.service.mapper.ClientMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private static final Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Optional<ClientDTO>> findAll() {
        log.info("find all Client");
        List<Optional<ClientDTO>> resultList = new ArrayList<>();
        List<Client> ClientList = clientRepository.findAll();
        for (Client client : ClientList) {
            if (client != null) {
                resultList.add(Optional.of(clientMapper.toDto(client)));
            }
        }
        return resultList;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ClientDTO> findById(long id) {
        log.info("find Client by Id");
        try {
            Optional<Client> client = clientRepository.findById(id);
            if (client.isPresent()) {
                return Optional.of(clientMapper.toDto(client.get()));
            }
        } catch (Exception e) {
            log.info("Client with id {} not found", id);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<ClientDTO> ajouter(ClientDTO clientDTO) {
        Client client = clientRepository.saveAndFlush(clientMapper.toEntity(clientDTO));
        return Optional.of(clientMapper.toDto(client));
    }

    @Override
    @Transactional
    public Optional<ClientDTO> modifier(ClientDTO clientDTO) {
        Client client = clientRepository.saveAndFlush(clientMapper.toEntity(clientDTO));
        return Optional.of(clientMapper.toDto(client));
    }

    @Override
    @Transactional
    public void supprimer(ClientDTO clientDTO) {
        clientRepository.deleteById(clientDTO.getId());
    }
}