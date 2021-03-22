package com.co.app.auth.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientAlreadyExistsException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.ClientRegistrationService;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.stereotype.Service;

import com.co.app.auth.domain.CustomClientDetails;
import com.co.app.auth.repository.AuthClientDetailsRepository;
import com.co.app.commons.domain.AuthClientDetails;
import com.co.app.commons.exception.RegisterNotFoundException;
import com.co.app.message.constants.MessageConstants;

/**
 * 
 * @author alobaton
 *
 */
@Service
public class CustomClientDetailsService implements ClientDetailsService, ClientRegistrationService {

	@Autowired
	private AuthClientDetailsRepository repository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.oauth2.provider.ClientRegistrationService#
	 * addClientDetails(org.springframework.security.oauth2.provider.ClientDetails)
	 */
	@Override
	public void addClientDetails(ClientDetails clientDetails) throws ClientAlreadyExistsException {
		if (repository.existsById(clientDetails.getClientId())) {
			throw new ClientAlreadyExistsException(
					String.format("Client with id %s already existed", clientDetails.getClientId()));
		}

		AuthClientDetails authClientDetails = CustomClientDetails.CONVERTER_ENTITY.apply(clientDetails);
		repository.save(authClientDetails);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.oauth2.provider.ClientRegistrationService#
	 * updateClientDetails(org.springframework.security.oauth2.provider.
	 * ClientDetails)
	 */
	@Override
	public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {
		AuthClientDetails authClientDetails = CustomClientDetails.CONVERTER_ENTITY.apply(clientDetails);
		repository.save(authClientDetails);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.oauth2.provider.ClientRegistrationService#
	 * updateClientSecret(java.lang.String, java.lang.String)
	 */
	@Override
	public void updateClientSecret(String clientId, String secret) throws NoSuchClientException {
		AuthClientDetails details = repository.findById(clientId)
				.orElseThrow(() -> new RegisterNotFoundException(MessageConstants.ERROR_GENERAL_REGISTER_NOT_FOUND,
						MessageConstants.AUTH_CLIENT_DETAILS_NAME, clientId));
		details.setClientSecret(secret);

		repository.save(details);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.oauth2.provider.ClientRegistrationService#
	 * removeClientDetails(java.lang.String)
	 */
	@Override
	public void removeClientDetails(String clientId) throws NoSuchClientException {
		repository.deleteById(clientId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.oauth2.provider.ClientRegistrationService#
	 * listClientDetails()
	 */
	@Override
	public List<ClientDetails> listClientDetails() {
		return repository.findAll().stream().map(CustomClientDetails::new).collect(Collectors.<ClientDetails>toList());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.oauth2.provider.ClientDetailsService#
	 * loadClientByClientId(java.lang.String)
	 */
	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		return repository.findById(clientId).map(CustomClientDetails::new)
				.orElseThrow(() -> new RegisterNotFoundException(MessageConstants.ERROR_GENERAL_REGISTER_NOT_FOUND,
						MessageConstants.AUTH_CLIENT_DETAILS_NAME, clientId));
	}

}
