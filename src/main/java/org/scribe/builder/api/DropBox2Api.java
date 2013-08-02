package org.scribe.builder.api;


import org.scribe.builder.api.DefaultApi20;
import org.scribe.extractors.AccessTokenExtractor;
import org.scribe.extractors.JsonTokenExtractor;
import org.scribe.model.OAuthConfig;
import org.scribe.model.Verb;
import org.scribe.utils.OAuthEncoder;
import org.scribe.utils.Preconditions;

public class DropBox2Api extends DefaultApi20
{

	private static final String AUTHORIZE_URL = "https://www.dropbox.com/1/oauth2/authorize?client_id=%s&redirect_uri=%s&response_type=code";

	@Override
	public String getAccessTokenEndpoint()
	{
		return "https://api.dropbox.com/1/oauth2/token?grant_type=authorization_code";
	}

	@Override
	public String getAuthorizationUrl(OAuthConfig config)
	{
		Preconditions.checkValidUrl(config.getCallback(), "Must provide a valid url as callback. Dropbox does not support OOB");
		return String.format(AUTHORIZE_URL, config.getApiKey(), OAuthEncoder.encode(config.getCallback()));
	}

	@Override
	public Verb getAccessTokenVerb()
	{
		return Verb.POST;
	}

	@Override
	public AccessTokenExtractor getAccessTokenExtractor()
	{
		return new JsonTokenExtractor();
	}
}
