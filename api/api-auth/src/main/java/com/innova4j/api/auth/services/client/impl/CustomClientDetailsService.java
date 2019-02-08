package com.innova4j.api.auth.services.client.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientAlreadyExistsException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.ClientRegistrationService;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.stereotype.Service;

import com.innova4j.api.auth.dao.AuthClientDetailsRepository;
import com.innova4j.api.auth.domain.AuthClientDetails;
import com.innova4j.api.commons.exception.RegisterNotFoundException;

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

		AuthClientDetails details = new AuthClientDetails();

		details.setClientId(clientDetails.getClientId());
		details.setResourceIds(clientDetails.getResourceIds());
		details.setSecretRequired(clientDetails.isSecretRequired());
		details.setClientSecret(clientDetails.getClientSecret());
		details.setScoped(clientDetails.isScoped());
		details.setScope(clientDetails.getScope());
		details.setAuthorizedGrantTypes(clientDetails.getAuthorizedGrantTypes());
		details.setRegisteredRedirectUri(clientDetails.getRegisteredRedirectUri());
		details.setAuthorities(clientDetails.getAuthorities());
		details.setAccessTokenValiditySeconds(clientDetails.getAccessTokenValiditySeconds());
		details.setRefreshTokenValiditySeconds(clientDetails.getRefreshTokenValiditySeconds());
		details.setAutoApprove(clientDetails.isAutoApprove(StringUtils.EMPTY));
		details.setAdditionalInformation(clientDetails.getAdditionalInformation());

		repository.save(details);
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
		AuthClientDetails details = new AuthClientDetails();

		details.setClientId(clientDetails.getClientId());
		details.setResourceIds(clientDetails.getResourceIds());
		details.setSecretRequired(clientDetails.isSecretRequired());
		details.setClientSecret(clientDetails.getClientSecret());
		details.setScoped(clientDetails.isScoped());
		details.setScope(clientDetails.getScope());
		details.setAuthorizedGrantTypes(clientDetails.getAuthorizedGrantTypes());
		details.setRegisteredRedirectUri(clientDetails.getRegisteredRedirectUri());
		details.setAuthorities(clientDetails.getAuthorities());
		details.setAccessTokenValiditySeconds(clientDetails.getAccessTokenValiditySeconds());
		details.setRefreshTokenValiditySeconds(clientDetails.getRefreshTokenValiditySeconds());
		details.setAutoApprove(clientDetails.isAutoApprove(StringUtils.EMPTY));
		details.setAdditionalInformation(clientDetails.getAdditionalInformation());

		repository.save(details);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.oauth2.provider.ClientRegistrationService#
	 * updateClientSecret(java.lang.String, java.lang.String)
	 */
	@Override
	public void updateClientSecret(String clientId, String secret) throws NoSuchClientException {
		AuthClientDetails details = repository.findById(clientId).orElseThrow(
				() -> new RegisterNotFoundException(AuthClientDetails.class, AuthClientDetails.CLIENT_ID, clientId));
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
		return repository.findAll().stream().map(det -> (ClientDetails) det)
				.collect(Collectors.<ClientDetails>toList());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.oauth2.provider.ClientDetailsService#
	 * loadClientByClientId(java.lang.String)
	 */
	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		return repository.findById(clientId).orElseThrow(
				() -> new RegisterNotFoundException(AuthClientDetails.class, AuthClientDetails.CLIENT_ID, clientId));
	}

}
